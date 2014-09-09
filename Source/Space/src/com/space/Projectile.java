package com.space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Projectile {
private Enemy enemy;
private double damage;
private Texture projectileImg;
public Rectangle rectangle;
private long fireRate;
private int speed;
public double angle;
private Space imageLib;


private int projectileType; 
/** 
 * Projectile Type:
 * 0 - laser
 * 1 - fireball
 * 2 - lightningBolt
 * 3 - windBlade
 * 
 * Enemies
 * 11 - enemyLaser
 * 
 * 
 * @param player
 */


public Projectile(Space imageLibSource, float x, float y, int projectileType){
	this.imageLib = imageLibSource;
	this.projectileType = projectileType;
	 
	rectangle = new Rectangle();
	rectangle.y = y;
    rectangle.x = x;
    
	if (projectileType == 0){// default laser
		projectileImg = imageLib.laserImg;

		damage = 1;
		
	    rectangle.width = 26;
	    rectangle.height = 7;
	    
	    speed = 600;
	}
	else if (projectileType == 1){// fireball
		projectileImg = imageLib.fireballImg;

		damage = 8;
		
		rectangle.width = 32;
	    rectangle.height = 20;
	    
	    speed = 200;
	}
	else if (projectileType == 2){// lightning
		projectileImg = imageLib.lightningBoltImg;

		damage = 1;
		
		rectangle.width = 96;
	    rectangle.height = 30;
	    
	    speed = 1200;
	}
	else if (projectileType == 3){
		projectileImg = imageLib.windBladeImg;
		damage = 1;
		rectangle.width = 32;
	    rectangle.height = 32;
	    speed = 600;
	}
	else if (projectileType == 11){
		projectileImg = imageLib.enemyLaserImg;

		damage = 10;
		
	    rectangle.width = 26;
	    rectangle.height = 7;
	    
	    speed = 200;
	}
}

public Projectile (Space imageLibSource, float x, float y, int projectileType, double angleSource){
	this.angle = angleSource;
	this.projectileType = projectileType;
	this.imageLib = imageLibSource;
	projectileImg = imageLib.laserImg;
	rectangle = new Rectangle();
	rectangle.y = y;
    rectangle.x = x;
	damage = 1;
	
    rectangle.width = 26;
    rectangle.height = 7;
    
    speed = 400;
}
public double getDamage(){
	return damage;
}
public int getSpeed(){
	return speed;
}
public Rectangle getRect(){
	return rectangle;
}

public Texture getProjectileImg(){
	return projectileImg;
}
}
