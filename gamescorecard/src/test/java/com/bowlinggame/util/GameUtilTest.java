package com.bowlinggame.util;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.bowlinggame.model.Frame;

public class GameUtilTest {

	@Test
	public void testIsStrike() {
		Assert.assertTrue(GameUtil.isStrike(10));
		Assert.assertFalse(GameUtil.isStrike(3));
	}
	
	@Test
	public void testIsSpare() {
		Assert.assertFalse(GameUtil.isSpare(2, 3));
		Assert.assertTrue(GameUtil.isSpare(6, 4));
	}	
	
	@Test
	public void testIsMiss() {
		Assert.assertFalse(GameUtil.isMiss(5));
		Assert.assertTrue(GameUtil.isMiss(0));
	}	
	
	@Test
	public void testIsGameOver() {
		List<Frame> gameOverList = new ArrayList<Frame>();
		Frame goFrame = null;
		for(int i=0;i<10;i++) {
			goFrame = new Frame();
			goFrame.setId(i+1);
			goFrame.setScore(0);
			goFrame.setMisses(2);
			goFrame.setHits("0---0");
			gameOverList.add(goFrame);
		}
		
		List<Frame> gameNotOverList = new ArrayList<Frame>();
		Frame gnoFrame1 = new Frame();
		gnoFrame1.setId(1);
		gnoFrame1.setScore(0);
		gnoFrame1.setMisses(2);
		gnoFrame1.setHits("0---0");
		gameNotOverList.add(gnoFrame1);
		
		Assert.assertFalse(GameUtil.isGameOver(gameNotOverList));
		Assert.assertTrue(GameUtil.isGameOver(gameOverList));
	}	
	
	@Test
	public void testCalculateFramesCount() {
		Assert.assertFalse(GameUtil.isSpare(2, 3));
		Assert.assertTrue(GameUtil.isSpare(6, 4));
	}		
}
