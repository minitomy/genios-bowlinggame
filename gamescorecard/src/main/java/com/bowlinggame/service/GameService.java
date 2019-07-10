package com.bowlinggame.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bowlinggame.model.Frame;
import com.bowlinggame.model.Game;
import com.bowlinggame.util.GameUtil;

@Service
public class GameService {
	@Autowired
	private Game bowlingGame;
	
	/**
	 * Fetch the current Scorecard list
	 * @return
	 */
	public List<Frame> getScorecardList() {
		return bowlingGame.getScorecardList();
	}
	
	/**
	 * Check if the Game is over and all 10 frames have been rolled.
	 * @return
	 */
	public boolean isGameOver() {
		return GameUtil.isGameOver(getScorecardList());
	}
	
	/**
	 * Reset the statistics on the game to allow Player to play again.
	 */
	public void restartGame() {
		bowlingGame.resetFrames();
	}
	
	/**
	 * Initiate the scoring for the given game
	 * @param knockedPins
	 * @return
	 */
	public List<Frame> scoreGame(int knockedPins) {
		bowlingGame.addPins(knockedPins);
		int activeFrames = GameUtil.calculateFramesCount(bowlingGame.getRollIndex(), bowlingGame.getRollPins());
		List<Frame> scorecardList = null;
		if(getScorecardList()==null) {
			scorecardList = new ArrayList<Frame>();
		} else {
			scorecardList = bowlingGame.getScorecardList();			
		}
		List<Frame> list = this.calculateScore(activeFrames, scorecardList);
		
		return list;
	}
	
	/**
	 * Calculate the score for each frame.
	 * @param activeFrames
	 * @return
	 */
	public List<Frame> calculateScore(int activeFrames, List<Frame> scorecardList) {
		int frameScore = 0;
		Frame frameObject = null;
		int roll = 0;
		final int[] rolledPins = bowlingGame.getRollPins();
		
		//Iterate over each frame to calculate the score, hits, misses, strikes and spares
		for (int frame = 0; frame < activeFrames; frame++) {
			if(GameUtil.isStrike(rolledPins[roll])) {
				//Calculate total for Strike as current roll(value of 10) plus the value from the next 2 rolls
				frameScore = frameScore + 10 + rolledPins[roll+1] + rolledPins[roll+2];
				frameObject = setFrameForStrike(frameScore, roll, rolledPins, scorecardList, frame);
				
				//Increment roll by 1 as we need to continue to the next roll.
				roll++;
			} else if(GameUtil.isSpare(rolledPins[roll], rolledPins[roll+1])) {
				//Calculate total for Spare as current roll and the next (value of 10) plus the value from the next roll in the next frame
				frameScore = frameScore + 10 + rolledPins[roll+2];	
				frameObject = setFrameForSpare(frameScore, roll, rolledPins, scorecardList, frame);
				
				roll = roll + 2;
			} else {
				//to check if the total hits allowed per frame has been exceeded. 
				//If yes, remove the last added pins from the array and reduce the index
				int hitsInFrame = rolledPins[roll]+rolledPins[roll+1];
				if(hitsInFrame>10) {
					bowlingGame.getRollPins()[roll+1] = 0;
					bowlingGame.setRollIndex(bowlingGame.getRollIndex()-1);
					break;
				}
				//Calculate total for Strike as current roll plus the value from the next roll
				frameScore = frameScore + rolledPins[roll] + rolledPins[roll+1];
				frameObject = setFrameForOthers(frameScore, roll, rolledPins, scorecardList, frame);
				
				roll = roll + 2;
			}
			//If the frame object has already been added, simple update the properties in the object and replace it in the Arraylist.
			//Else add the new Frame object to the list.
			if(scorecardList.size() > frame && scorecardList.get(frame) !=null) {
				scorecardList.set(frame, frameObject);
			} else {
				scorecardList.add(frame, frameObject);
			}
		}
		bowlingGame.setScorecardList(scorecardList);
		return scorecardList;
	}
	
	/**
	 * When it is neither a strike nor a spare
	 * @param frameScore
	 * @param roll
	 * @param rolledPins
	 * @param frameObject
	 * @return
	 */
	public Frame setFrameForOthers(int frameScore, int roll, int[] rolledPins, List<Frame> scorecardList, int frame) {
		Frame frameObject = this.createFrame(scorecardList, frame);
		if(bowlingGame.getRollIndex() > (roll+1)) {
			frameObject.setScore(frameScore);
			frameObject.setStrike(0);
			frameObject.setSpare(0);
			frameObject.setHits(rolledPins[roll]+"---"+rolledPins[roll+1]);
		}
		if(bowlingGame.getRollIndex()> roll && rolledPins[roll] == 0)  {
			frameObject.setMisses(1);
		} 
		if(bowlingGame.getRollIndex() > (roll+1) && rolledPins[roll+1] == 0) {
			frameObject.setMisses(1);
		} 
		if(bowlingGame.getRollIndex() > (roll+1) && rolledPins[roll] == 0 && rolledPins[roll+1] == 0) {
			frameObject.setMisses(2);
		}
		return frameObject;
	}
	
	/**
	 * When a spare is rolled, set the data accordingly
	 * @param frameScore
	 * @param roll
	 * @param rolledPins
	 * @param frameObject
	 * @return
	 */
	public Frame setFrameForSpare(int frameScore, int roll, int[] rolledPins, List<Frame> scorecardList, int frame) {
		Frame frameObject = this.createFrame(scorecardList, frame);
		if(bowlingGame.getRollIndex() > (roll+2)) {
			frameObject.setScore(frameScore);
			frameObject.setHits(rolledPins[roll]+"---"+rolledPins[roll+1]);
		}
		frameObject.setStrike(0);
		frameObject.setSpare(1);
		if(bowlingGame.getRollIndex() > (roll+1) && rolledPins[roll] == 0) {
			frameObject.setMisses(0);
		}
		return frameObject;
	}
	
	/**
	 * When a strike is rolled, we need to set the data. If it is the last frame, then check the possibility of rolling 3 turns.
	 * @param frameScore
	 * @param roll
	 * @param rolledPins
	 * @param frameObject
	 * @param frame
	 * @return
	 */
	public Frame setFrameForStrike(int frameScore, int roll, int[] rolledPins, List<Frame> scorecardList, int frame) {
		Frame frameObject = this.createFrame(scorecardList, frame);
		frameObject.setStrike(1);
		frameObject.setSpare(0);
		frameObject.setMisses(0);	
		if(bowlingGame.getRollIndex() > (roll+2)) {
			frameObject.setScore(frameScore);
			frameObject.setHits("10 ");
		}
		if (frame == 9) {
			if(rolledPins[roll+1] == 10) {
				frameObject.setStrike(2);
			}
			if(rolledPins[roll+2] == 10) {
				frameObject.setStrike(3);
			}					
		}
		
		return frameObject;
	}
	
	/**
	 * Check if the frame has been created. If it has, simply fetch else create a new Frame object
	 * @param scorecardList
	 * @param frame
	 * @return
	 */
	public Frame createFrame(List<Frame> scorecardList, int frame) {
		Frame frameObject = null;	
		if(scorecardList.size() > frame && scorecardList.get(frame) !=null ) {
			frameObject = scorecardList.get(frame);
		} else {
			frameObject = new Frame();
			frameObject.setId(frame+1);
		}
		return frameObject;
	}
}
