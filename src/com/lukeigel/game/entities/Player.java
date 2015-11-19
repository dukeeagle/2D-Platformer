package com.lukeigel.game.entities;

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.lukeigel.game.main.GamePanel;

public class Player {
	
	//movement booleans
	private boolean right = false, left = false, jumping = false, falling = false;
	
	//bounds
	private double x, y;
	public int width, height;
	
	//jump speed
	private double jumpSpeed = 5;
	private double currentJumpSpeed = jumpSpeed; 
	
	//fall speed
	private double maxFallSpeed = 5;
	private double currentFallSpeed = 0.1;
	
	public Player(int width, int height){
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
		this.width= width;
		this.height = height;
		
	}
	
	public void tick(){
		if(right){
			x++;
		}
		if(left){
			x--;
		}
		if(jumping){
			y -= currentJumpSpeed;
			
			currentJumpSpeed -= 0.1;
			
			//establishes deceleration when approaching peak during jump
			if(currentJumpSpeed <= 0){
				currentJumpSpeed = jumpSpeed;
				jumping = false;
				falling = true;
			}
		}
		if(falling){
			y += currentFallSpeed;
			
			if(currentFallSpeed < maxFallSpeed){
				currentFallSpeed += 0.1;
			}
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect((int)x, (int)y, width, height);
	}
	
	public void keyPressed(int k){
		if(k == KeyEvent.VK_D) right = true;
		if(k == KeyEvent.VK_A) left = true;
		if(k == KeyEvent.VK_SPACE) jumping = true;
	}
	
	public void keyReleased(int k){
		if(k == KeyEvent.VK_D) right = false;
		if(k == KeyEvent.VK_A) left = false;
	}
}
