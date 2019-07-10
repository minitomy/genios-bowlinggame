package com.bowlinggame.controller;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.test.context.junit4.SpringRunner;
import com.bowlinggame.model.Frame;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameControllerTest {
	@Autowired
	GameController game;
	
	@Test
	public void testPlayGameSingleRoll() {
		game.restartPlay();
		ModelAndView model = game.playGame(10);

		List<Frame> scoreList = (List<Frame>) model.getModel().get("scoreboard");
		Assert.assertTrue(scoreList.get(0).getScore()==0);		
		Assert.assertTrue(model.getViewName().equals("playgame"));
	}

	@Test
	public void testPlayGameSingleFrame() {
		game.restartPlay();
		ModelAndView model = game.playGame(5);
		model = game.playGame(3);
		
		List<Frame> scoreList = (List<Frame>) model.getModel().get("scoreboard");
		Assert.assertTrue(scoreList.get(0).getScore()==8);		
		Assert.assertTrue(model.getViewName().equals("playgame"));
	}
	
	@Test
	public void testPlayGameAllStrikes() {
		game.restartPlay();
		ModelAndView model = null;
		for(int i=0;i<12;i++) {
			model = game.playGame(10);
		}
		
		List<Frame> scoreList = (List<Frame>) model.getModel().get("scoreboard");
		Assert.assertTrue(scoreList.get(9).getScore()==300);		
		Assert.assertTrue(model.getViewName().equals("playgame"));
	}
	
	@Test
	public void testPlayGameAllSpares() {
		game.restartPlay();
		ModelAndView model = null;
		for(int i=0;i<21;i++) {
			model = game.playGame(5);
		}
		
		
		List<Frame> scoreList = (List<Frame>) model.getModel().get("scoreboard");
		Assert.assertTrue(scoreList.get(9).getScore()==150);		
		Assert.assertTrue(model.getViewName().equals("playgame"));
	}
	
	@Test
	public void testPlayGameNoSparesNoStrikes() {
		game.restartPlay();
		ModelAndView model = null;
		for(int i=0;i<20;i++) {
			model = game.playGame(4);
		}
		
		
		List<Frame> scoreList = (List<Frame>) model.getModel().get("scoreboard");
		Assert.assertTrue(scoreList.get(9).getScore()==80);		
		Assert.assertTrue(model.getViewName().equals("playgame"));
	}
	
	@Test
	public void testPlayGameWithAllCombination() {
		game.restartPlay();
		ModelAndView model = null;
		model = game.playGame(10);
		model = game.playGame(7);
		model = game.playGame(3);
		model = game.playGame(9);
		model = game.playGame(0);
		model = game.playGame(10);
		model = game.playGame(0);
		model = game.playGame(8);
		model = game.playGame(7);
		model = game.playGame(1);
		model = game.playGame(4);
		model = game.playGame(4);
		model = game.playGame(10);
		model = game.playGame(10);
		model = game.playGame(10);
		model = game.playGame(10);
		model = game.playGame(10);
		
		
		List<Frame> scoreList = (List<Frame>) model.getModel().get("scoreboard");
		Assert.assertTrue(scoreList.get(9).getScore()==180);		
		Assert.assertTrue(model.getViewName().equals("playgame"));
	}
	
	@Test
	public void testIsGameOver() {
		game.restartPlay();
		ModelAndView model = null;
		for(int i=0;i<20;i++) {
			model = game.playGame(4);
		}
		model = game.playGame(4);
		
		List<Frame> scoreList = (List<Frame>) model.getModel().get("scoreboard");
		Assert.assertTrue(model.getModel().get("message").toString().contains("Game is over"));		
		Assert.assertTrue(scoreList.get(9).getScore()==80);		
		Assert.assertTrue(model.getViewName().equals("playgame"));
	}
	
	@Test
	public void testIsInvalidRoll() {
		game.restartPlay();
		ModelAndView model = game.playGame(12);
		
		List<Frame> scoreList = (List<Frame>) model.getModel().get("scoreboard");
		Assert.assertTrue(model.getModel().get("message").toString().contains("Invalid"));
		Assert.assertTrue(model.getViewName().equals("playgame"));
	}
}
