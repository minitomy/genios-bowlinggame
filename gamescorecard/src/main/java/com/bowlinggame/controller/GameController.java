package com.bowlinggame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bowlinggame.model.Frame;
import com.bowlinggame.service.GameService;

@Controller
public class GameController {
	@Autowired
	GameService gameService;
 
	/**
	 * The landing page
	 * @return
	 */
	@RequestMapping(value = "/playgame")
	public String welcome() {
		return "playgame";
	}
	
	/**
	 * The method when the player begins the game
	 * @param pins
	 * @return
	 */
	@RequestMapping(value="/playgame", method=RequestMethod.POST)
	public ModelAndView playGame(@RequestParam("pins") int pins) {
		ModelAndView model = new ModelAndView("playgame");
		
		//Check if the Game is over, if Yes, simple display a message alongwith the score
		if(gameService.isGameOver()) {
			model.addObject("message", "No more rolls possible as the Game is over. Click Restart button to play the Game again.");
			model.addObject("scoreboard", gameService.getScorecardList());
		} else {
			if(pins > 10) {
				model.addObject("message", "Invalid number of pins. The value needs to be less than 10.");
				model.addObject("scoreboard", gameService.getScorecardList());
			} else {
				List<Frame> list = gameService.scoreGame(pins);
				model.addObject("scoreboard", list);
			}
		}
		return model;
	}
	
	/**
	 * To reset the stats on the game
	 * @return
	 */
	@RequestMapping(value="/restart", method=RequestMethod.POST)
	public String restartPlay() {
		gameService.restartGame();
		return "playgame";
	}	
}
