package com.bowlinggame.util;

import java.util.List;

import com.bowlinggame.model.Frame;

public final class GameUtil {
	public static boolean isStrike(int knockedPins) {
		if (knockedPins == 10) {
			return true;
		}
		return false;
	}
	
	public static boolean isSpare(int firstRollKnockedPins, int secondRollKnockedPins) {
		if (firstRollKnockedPins + secondRollKnockedPins == 10) {
			return true;
		}
		return false;
	}
	
	public static boolean isMiss(int knockedPins) {
		if (knockedPins == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isGameOver(List<Frame> scoreList) {
		if(scoreList==null) {
			return false;
		} 
		if(scoreList.size()==10 && scoreList.get(9).getHits()!=null) {
			return true;
		}
		
		return false;
	}
	
	public static int calculateFramesCount(int rollIndex, int[] rollPins) {
		int frameCount = 0;
		for(int i=0;i<rollIndex;i++) {
			if(isStrike(rollPins[i])) {
				frameCount += 1;
			} else if(i <20 && isSpare(rollPins[i], rollPins[i+1])) {
				frameCount += 1;
				i++;
			} else {
				frameCount += 1;
				i++;
			}
		}
		if(frameCount > 10) {
			return 10;
		}
		return frameCount;
	}
}