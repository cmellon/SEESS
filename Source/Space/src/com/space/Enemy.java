package com.space;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Enemy {
	public Space imageLib;
	private Texture shipImg;
	public Rectangle ship; 
	public double health;
	public long timeSinceLastShot;
	private long second = 100000000;
	public double collideDamage;
	
	public double fireRate; // in number per second
	public int projectileType;
	
	public Texture projectileImg;  
	public int enemyType;

	
	
	public Enemy(Space imageLibSource,int enemyTypeSource, int currentWave){
		this.imageLib = imageLibSource;
		this.enemyType = enemyTypeSource;
		if(enemyType == 0){
			this.shipImg = imageLib.enemyImg;
			projectileType = 11;
			
			health = 4;
			fireRate = .05;
			collideDamage = 33.3;
			
			ship = new Rectangle();
		    ship.x = 850;
		    ship.y = TimeUtils.nanoTime()%440;
		    ship.width = 30;
		    ship.height = 40;
		}
		else {
		this.projectileImg = new Texture(Gdx.files.internal("windBlade.png"));
		this.shipImg = new Texture(Gdx.files.internal("explosion.png"));
		
		projectileType = 0;
		
		ship = new Rectangle();
	    ship.x = 850;
	    ship.y = TimeUtils.nanoTime()%440;
	    ship.width = 36;
	    ship.height = 40;
		}
	}

	public void shoot(){
		// TODO fix them shooting back... -_-'
		//shots.add(new Projectile(this,ship.x,ship.y,11));
		//System.out.println("firing");
		
	}

	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return ship;
	}


	public Texture getImg() {
		// TODO Auto-generated method stub

		return shipImg;
	}

	public Texture getProjectileImg(){
		System.out.println("Returning Image");

		return projectileImg;
	}

	public int getProjectileType() {
		// TODO Auto-generated method stub
		return projectileType;
	}
	
	
}
