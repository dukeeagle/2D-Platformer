package com.lukeigel.game.gamestate;

import java.awt.Graphics;

import com.lukeigel.game.entities.Player;

public class Level1State extends GameState {
	
	private Player player;
	
	public Level1State(GameStateManager gsm){
		super(gsm);
	}

	
	public void init() {
		player = new Player(30, 30);
	}

	
	public void tick() {
		player.tick();
	}

	
	public void draw(Graphics g) {
		player.draw(g);
	}

	
	public void keyPressed(int k) {		
		player.keyPressed(k);
	}

	
	public void keyReleased(int k) {		
		player.keyPressed(k);
	}
}
