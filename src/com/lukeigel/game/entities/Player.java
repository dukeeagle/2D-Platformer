package com.lukeigel.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
//import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.lukeigel.game.gamestate.GameState;
import com.lukeigel.game.main.GamePanel;
import com.lukeigel.game.objects.Block;

import come.lukeigel.game.physics.Collision;

public class Player {
	
	//movement booleans
	private boolean right = false, left = false, jumping = false, falling = false;
	
	//bounds
	private double x, y;
	public int width, height;
	
	//move speed
	private double moveSpeed = 2.5;
	
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
	
	public void tick(Block[] b){
		
		for(int i = 0; i < b.length; i++){
			
			int iX = (int)x;
			int iY = (int)y;
			
			//right
			if(Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset, iY + (int)GameState.yOffset), b[i])
					|| Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset, iY + height + (int)GameState.yOffset), b[i])){
				right = false;
			}
			
			//left
			if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset, iY + (int)GameState.yOffset), b[i])
					|| Collision.playerBlock(new Point(iX + (int)GameState.xOffset, iY + height + (int)GameState.yOffset), b[i])){
				left = false;
			}
			
			//top
			if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset, iY + (int)GameState.yOffset), b[i])
					|| Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset, iY + (int)GameState.yOffset), b[i])){
				jumping = false;
				falling = true;
			}
			
			//bottom
			if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset, iY + height + (int)GameState.yOffset), b[i])
					|| Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset, iY + height + (int)GameState.yOffset), b[i])){
				falling = false;
				break;
			} else {
				falling = true;
			}
			
		}
		
		
		if(right){
			GameState.xOffset += moveSpeed;
		}
		if(left){
			GameState.xOffset -= moveSpeed;
		}
		if(jumping){
			GameState.yOffset -= currentJumpSpeed;
			
			currentJumpSpeed -= 0.1;
			
			//establishes deceleration when approaching peak during jump
			if(currentJumpSpeed <= 0){
				currentJumpSpeed = jumpSpeed;
				jumping = false;
				falling = true;
			}
		}
		if(falling){
			GameState.yOffset += currentFallSpeed;
			
			if(currentFallSpeed < maxFallSpeed){
				currentFallSpeed += 0.1;
			}
		}
		if(!falling){
			currentFallSpeed = 0.1;
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
