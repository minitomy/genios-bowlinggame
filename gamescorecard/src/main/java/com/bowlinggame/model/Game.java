package com.bowlinggame.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bowlinggame.util.GameUtil;

@Component
public class Game {		
	int[] rollPins = new int[21];
	int rollIndex = 0;

	List<Frame> scorecardList;

	public int getRollIndex() {
		return rollIndex;
	}

	public void setRollIndex(int rollIndex) {
		this.rollIndex = rollIndex;
	}

	public void addPins(int knockedPins) {
		this.rollPins[rollIndex++] = knockedPins;
	}
	
	public int[] getRollPins() {
		return rollPins;
	}
	
	public List<Frame> getScorecardList() {
		return scorecardList;
	}

	public void setScorecardList(List<Frame> scorecardList) {
		this.scorecardList = scorecardList;
	}

	public void resetFrames() {
		this.rollPins = new int[21];
		this.rollIndex = 0;
		this.scorecardList = null;
	}
}
