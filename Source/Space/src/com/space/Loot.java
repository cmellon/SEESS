package com.space;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Loot {
private int orbType; // 0 if fire, 1 if lightning, 2 if wind
private Texture orbImg; 
private Rectangle orb;

public Loot(Rectangle destroyedShip, Texture orbImg, int orbType){
	this.orbImg = orbImg;
	this.orbType = orbType;
	
	setRect(new Rectangle());
	getRect().width = 32;
	getRect().height = 32;
	getRect().x = destroyedShip.x;
	getRect().y = destroyedShip.y;
}

public int getOrbType(){
	return orbType;
}
public Rectangle getRect() {
	return orb;
}

public void setRect(Rectangle orb) {
	this.orb = orb;
}

public Texture getOrbImg() { 
	// TODO Auto-generated method stub
	return orbImg;
}
}
