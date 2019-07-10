package com.bowlinggame.model;

public class Frame {
	int id;
	int score;
	int strike;
	int spare;
	int misses;	
	String hits;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getStrike() {
		return strike;
	}
	public void setStrike(int strike) {
		this.strike = strike;
	}
	public int getSpare() {
		return spare;
	}
	public void setSpare(int spare) {
		this.spare = spare;
	}
	public int getMisses() {
		return misses;
	}
	public void setMisses(int misses) {
		this.misses = misses;
	}
	public String getHits() {
		return hits;
	}
	public void setHits(String hits) {
		this.hits = hits;
	}
}
