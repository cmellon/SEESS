package com.space;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.TimeUtils;


public class Player{
	
public Space imageLib;
private Texture shipImg;
private Texture blankImg;
private Texture explosionImg;
private Texture laserImg, fireballImg, lightningBoltImg, windBladeImg;
private Texture fireFormImg, lightningFormImg, windFormImg;
public Rectangle ship; 
private long second = 1000000000;


//Upgrades
//Ship
public int shipLvl = 1;
public int baseSpeed = 100;
public double health;
public double healthMax = 100;
public double laserDamage = 1;
public long laserFireRate= (long)(second/5); 

public int healthCost = 40;
public int laserDamageCost = 100;
public int laserFireRateCost = 50;
public int velocityCost = 20;

//Fire
public int  fireFormLvl = 1;
public long fireFormDuration = 5*second;
public int fireballDamage = 8;
public int fireballVelocity = 300;
public long fireballFireRate = (long)(second/4);//second divided by number of projectiles per second
public int fireCapacity = 5; // number of orbs necessary to transform

public int fireFormDurationCost = 25;//Upgrade costs
public int fireballDamageCost = 30;
public int fireballFireRateCost = 15;
public int fireballVelocityCost = 10;
public int fireCapacityCost = 5; 

//Lightning
public int lightningFormLvl = 1;
public double lightningBoltDamage = .3;
public int lightningBoltVelocity = 1200;
public long lightningFormDuration =  5*second;
public long lightningBoltFireRate = (long)(second/10);
public int lightningCapacity = 5;

public int lightningFormDurationCost = 25;
public int lightningDamageCost = 50;

//Wind
public int windFormLvl = 1;
public double windBladeDamage = 1;
public long windFormDuration = 5*second; 
public long windBladeFireRate = (long)(second/8);
public int windCapacity = 5;
public int windFormVelocityBoost = 100;

public int windFormDurationCost = 25;
public int windBladeDamageCost = 30;
public int windBladeFireRateCost = 10;
public int windFormVelocityCost = 15;

//Class Mechanics Variables
public double healthBarWidth = 329;
public double healthBarPosMod; //Add this from the initial position of the health bar on the screen Range 0 to -329
public double healthBarPosModTarget = 329;

public double timerBarWidth = 142;
public int timerBarPosMod;
public double timerBarPosModTarget;
public long timeLeft;

public long currentFormDuration;

private boolean firing = false;

private ArrayList<Projectile> shots = new ArrayList<Projectile>();
private ArrayList<Projectile> angledShots = new ArrayList<Projectile>();


public boolean fireFormEnabled, lightningFormEnabled, windFormEnabled;

public int currentForm = -1; //-1 if default, 0 if fire, 1 if lightning, 2 if wind, 4 if destroyed
public int currentFrame;// keeps track of current frame in second. Incremented every time getShipImg() is called.
public long timeSinceTransform = 0;
private long timeSinceLastShot;
private int touchX;
private int touchY;

//Stats
public int shipXp =0;
public int fireFormXp=0;
public int windFormXp=0;
public int lightningFormXp=0;
public int careerKills=0;

public int genericOrbs = 942;
public int spendableFireOrbs = 0;
public int spendableWindOrbs = 0;
public int spendableLightningOrbs = 0;
public int fireOrbs = 0;
public int windOrbs = 0;
public int lightningOrbs = 0;



public int speed = baseSpeed;

PlayerDA dataAccess = new PlayerDA();
public boolean gameSaved = false;
public boolean gameLoaded = false;


public Player(Space imageLibSource){
	this.imageLib = imageLibSource; 
	this.shipImg = imageLib.shipImg;
	this.blankImg = imageLib.blankImg;
	this.explosionImg = imageLib.explosionImg;
	this.fireFormImg = imageLib.fireFormImg;
	this.lightningFormImg = imageLib.lightningFormImg;
	this.windFormImg = imageLib.windFormImg;
	
	this.laserImg = imageLib.laserImg;
	this.fireballImg = imageLib.fireballImg;
	this.lightningBoltImg = imageLib.lightningBoltImg;
	this.windBladeImg = imageLib.windBladeImg;

	health = healthMax;
	
	ship = new Rectangle();
    ship.x = 75 - 36 / 2;
    ship.y = 480/2 - 40/2;
    ship.width = 30;
    ship.height = 40;
    

}
public void moveLeft(){
	
	if(currentForm!=4)ship.x -= speed * Gdx.graphics.getDeltaTime();
	if (ship.x<0)ship.x=0;
}
public void moveRight() {
	// TODO Auto-generated method stub
	if(currentForm!=4)ship.x += speed * Gdx.graphics.getDeltaTime();
    if(ship.x > 800 - 48) ship.x = 800 - 48;

}
public void moveUp() {
	// TODO Auto-generated method stub
	if(currentForm!=4)ship.y += speed * Gdx.graphics.getDeltaTime();
    if(ship.y > 480 - 48) ship.y = 480 - 48;

}
public void moveDown() {
	// TODO Auto-generated method stub
	if(currentForm!=4)ship.y -= speed * Gdx.graphics.getDeltaTime();
    if(ship.y < 0) ship.y = 0;

}
public void fire(){ 
	if (currentForm!=4){
		
		if(currentForm==-1){
			if(timeSinceLastShot>laserFireRate){
				timeSinceLastShot = 0;
				shots.add(new Projectile(imageLib,(ship.x + 5),(ship.y + ship.height)-13,0));
				shots.add(new Projectile(imageLib, ship.x + 5, ship.y+7, 0));
				}
			}
			else if(currentForm == 0){
			if(timeSinceLastShot>fireballFireRate){
				timeSinceLastShot = 0;
				shots.add(new Projectile(imageLib,(ship.x),(ship.y),1));
				}
			}
			else if(currentForm == 1){
			if(timeSinceLastShot>lightningBoltFireRate){
				timeSinceLastShot = 0;
				shots.add(new Projectile(imageLib,(ship.x),(ship.y),2));
				}
			}
			else if(currentForm == 2){
			if(timeSinceLastShot>windBladeFireRate){
				timeSinceLastShot = 0;
				shots.add(new Projectile(imageLib,(ship.x),(ship.y),3));
			}
		}
		firing = true;
		}
      }
public void stopFiring() {
	// TODO Auto-generated method stub
	firing = false;
}
public void moveToTouch(OrthographicCamera camera) {
	// TODO Auto-generated method stub
	// The bloody camera and touch position is in a different orientation than the batch.draw orientation. It's a nightmare.
	if(currentForm!=4){
	Vector3 touchPos = new Vector3();
    touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
    camera.unproject(touchPos);
    int moveBuffer= 2;
    
    if(Gdx.input.getX()<ship.x && touchPos.x-ship.x<moveBuffer*-1)ship.x-=speed*Gdx.graphics.getDeltaTime();
	else if(Gdx.input.getX()>ship.x && touchPos.x-ship.x>moveBuffer)ship.x += speed*Gdx.graphics.getDeltaTime();
    
    if(touchPos.y-16<ship.y && (touchPos.y-16)-ship.y<moveBuffer*-1)ship.y +=(speed*Gdx.graphics.getDeltaTime()*-1);
    else if(touchPos.y-16>ship.y && (touchPos.y-16)-ship.y>moveBuffer)ship.y-=(speed*Gdx.graphics.getDeltaTime()*-1);
    
	}
}
public Rectangle getShip() {
	// TODO Auto-generated method stub
	return ship;
}
public ArrayList<Projectile> getShots() {
	return shots;  
}
public Texture getShipImg() { 
	//increment necessary time and frame trackers
	currentFrame = (currentFrame+1)%60;
	timeSinceTransform += Gdx.graphics.getDeltaTime()*second;
    timeSinceLastShot += (long) (second*Gdx.graphics.getDeltaTime());

	//Update time left for transformation
	timeLeft = currentFormDuration - timeSinceTransform;
	
	//Update the health bar position
	healthBarPosModTarget = ((health*healthBarWidth)/healthMax)-healthBarWidth;
	if (healthBarPosModTarget<healthBarPosMod) healthBarPosMod -= 3;// make this number larger to make it go faster
	if (healthBarPosModTarget>healthBarPosMod) healthBarPosMod += 3;// make this number larger to make it go faster

	//Update the timer bar position
	if(currentForm==-1||currentForm ==4)timerBarPosMod=-142;//move it off screen if dead or in default form
	else{
	timerBarPosModTarget = ((timeLeft*timerBarWidth)/currentFormDuration)-timerBarWidth;
	if (timerBarPosModTarget<timerBarPosMod) timerBarPosMod -= 3;// make this number larger to make it go faster
	if (timerBarPosModTarget>timerBarPosMod) timerBarPosMod = (int) timerBarPosModTarget;// make this number larger to make it go faster
	}
	
	if(currentForm == 4) return blankImg;//explosionImg;
	else if (currentForm == 0){ if(timeSinceTransform>fireFormDuration){System.out.println("Transforming Back!"); currentForm = -1; speed = baseSpeed;}}
	else if (currentForm == 1){ if(timeSinceTransform>lightningFormDuration) {System.out.println("Transforming Back!"); currentForm = -1;speed = baseSpeed;}}
	else if (currentForm == 2){ if(timeSinceTransform>windFormDuration){System.out.println("Transforming Back!"); currentForm = -1;speed = baseSpeed;}}
	
	if (currentForm == 0) return fireFormImg;
	else if (currentForm == 1) return lightningFormImg;
	else if (currentForm == 2) return windFormImg;
	else return shipImg;
}
public void die(){
	currentForm = 4;
}
public void pickup(Loot loot) {
	// TODO Auto-generated method stub
	gameSaved = false;
	genericOrbs++;
	if (loot.getOrbType()==0){
		fireOrbs++;
		spendableFireOrbs++;
		System.out.println("Fire orbs collected: "+fireOrbs);

		if (fireOrbs>=fireCapacity){
			fireOrbs = fireCapacity;
			fireFormEnabled = true;
			System.out.println("At Fire Capacity!");
		}
	}
	else if (loot.getOrbType()==1){
		lightningOrbs++;
		spendableLightningOrbs++;
		System.out.println("Lightning orbs collected: "+lightningOrbs);

		if (lightningOrbs>=lightningCapacity){
			lightningOrbs = lightningCapacity;
			lightningFormEnabled = true;
			System.out.println("Lightning Capacity Reached!");
		}
	}
	else if (loot.getOrbType()==2){
		windOrbs++;
		spendableWindOrbs++;
		System.out.println("Wind orbs collected: "+windOrbs);

		if (windOrbs>=windCapacity){
			windOrbs = windCapacity;
			windFormEnabled = true;
			System.out.println("Wind Capacity Reached!");
		}
	}

}
public void transform(int form){
	// TODO change form based upon input from user
	if(currentForm!=4){
		timeSinceTransform = 0;
	if(form == 0){
		if (fireFormEnabled){
			currentForm = 0;
			fireOrbs = 0;
			currentFormDuration = fireFormDuration;
			fireFormEnabled = false;
		}
	}
	else if (form == 1){
		if (lightningFormEnabled){
			currentForm = 1;
			lightningOrbs = 0;
			currentFormDuration = lightningFormDuration;

			lightningFormEnabled = false;
		}
	}
	else if (form == 2){
		if (windFormEnabled){
			currentForm = 2;
			windOrbs = 0;
			currentFormDuration = windFormDuration;

			windFormEnabled = false;
			
			speed += windFormVelocityBoost;
		}
	}
	}
	
}

public void gainXp(int xpGain){
	gameSaved = false;
	careerKills++;
	if(currentForm == -1) shipXp+=xpGain;
	else if (currentForm == 0){ fireFormXp+=xpGain;}
	else if (currentForm == 1){ lightningFormXp+=xpGain;}
	else if (currentForm == 2){ windFormXp+=xpGain;}
	
	System.out.println("Ship XP: " +shipXp);
	System.out.println("Fire XP: " +fireFormXp);
	System.out.println("Lightning XP: " +lightningFormXp);
	System.out.println("Wind XP: " +windFormXp);
	
	if(shipXp>=(shipLvl*250+shipLvl*shipLvl*shipLvl))levelUp(0);
	if(fireFormXp>=(fireFormLvl*50+fireFormLvl*fireFormLvl*fireFormLvl))levelUp(1);
	if(lightningFormXp>=(lightningFormLvl*50+lightningFormLvl*lightningFormLvl*lightningFormLvl))levelUp(2);
	if(windFormXp>=(windFormLvl*50+windFormLvl*windFormLvl*windFormLvl))levelUp(3);


}

public void levelUp(int form){
	//TODO something
	if (form == 0){
	baseSpeed+=4; // upgrade speed is +=20 with 5 upgrades // max with levels and upgrades is 400 (50 levels)
	health+=10; // ugrade is +=50 
	shipLvl++;
	}
	if (form == 1){
		fireFormDuration += .5*second;//levelup adds .5 to duration // each upgrade adds 1second // max with upgrades and levelups (20 levels) is 20 seconds
		fireballDamage += .5; //levelup adds .5 to damage // each upgrade adds 1 damage
		fireFormLvl++;
	}
	if (form == 2){
		lightningFormDuration += .5*second;//levelup adds .5 to duration // each upgrade adds 1second // max with upgrades and levelups (20 levels) is 20 seconds
		lightningBoltDamage += .2; 
		lightningFormLvl++;
	}
	if (form == 2){
		windFormDuration += .5*second;//levelup adds .5 to duration // each upgrade adds 1second // max with upgrades and levelups (20 levels) is 20 seconds
		//TODO 
		windFormVelocityBoost+= 10;
		lightningFormLvl++;
	}
}
public void saveGame(){
	if(dataAccess.save()==0)gameSaved = true;
	//write(write(this.toJson()));
}
public void loadGame(){
	if(dataAccess.load()==0)gameLoaded = true;  
	if(gameLoaded)System.out.println("Game Loaded");
	else System.out.println("Load not completed");
}
//Ship Upgrade Methods
public void upgradeHealth(){
	if(genericOrbs-healthCost>0){
		genericOrbs = genericOrbs-healthCost;
	healthMax +=50;
	health = healthMax;
	healthCost+=40;
	}
}
public void upgradeLaserDamage(){
	if(genericOrbs-laserDamageCost>0){
		genericOrbs =genericOrbs-laserDamageCost;
	laserDamage++;
	laserDamageCost+=50;
	}
}
public void upgradeLaserFireRate(){
	if(genericOrbs-laserFireRateCost>0){
		genericOrbs =genericOrbs-laserFireRateCost;
	laserFireRate-=laserFireRate/5;
	laserFireRateCost+=75;
	}
}
public void upgradeVelocity(){
	if(genericOrbs-velocityCost>0){
		genericOrbs =genericOrbs-velocityCost;
	baseSpeed+=20;
	speed = baseSpeed;
	velocityCost+=20;
	}
}
//Fire Form Upgrade Methods
public void upgradeFireDuration(){
	if(genericOrbs-fireFormDurationCost>0){
		genericOrbs = genericOrbs-fireFormDurationCost;
	fireFormDuration +=second;
	fireFormDurationCost+=40;
	}
}
public void upgradeFireballDamage(){
	if(genericOrbs-fireballDamageCost>0){
		genericOrbs =genericOrbs-fireballDamageCost;
	fireballDamage++;
	fireballDamageCost+=50;
	}
}
public void upgradeFireballFireRate(){
	if(genericOrbs-fireballFireRateCost>0){
		genericOrbs =genericOrbs-fireballFireRateCost;
	fireballFireRate-=fireballFireRate/5;
	fireballFireRateCost+=75;
	}
}
public void upgradeFireballVelocity(){
	if(genericOrbs-fireballVelocityCost>0){
		genericOrbs =genericOrbs-fireballVelocityCost;
	fireballVelocity += 50;
	speed = baseSpeed;
	fireballVelocityCost+=20;
	}
}

//Lightning Form Upgrade Methods
public void upgradeLightningDuration(){
	if(genericOrbs-lightningFormDurationCost>0){
		genericOrbs = genericOrbs-lightningFormDurationCost;
	lightningFormDuration +=second;
	lightningFormDurationCost+=40;
	}
}
public void upgradeLightningDamage(){
	if(genericOrbs-lightningDamageCost>0){
		genericOrbs =genericOrbs-lightningDamageCost;
	lightningBoltDamage++;
	lightningDamageCost+=50;
	}
}
//Wind Form Upgrade Methods
public void upgradeWindDuration(){
	if(genericOrbs-windFormDurationCost>0){
		genericOrbs = genericOrbs-windFormDurationCost;
	windFormDuration +=second;
	windFormDurationCost+=40;
	}
}
public void upgradeWindBladeDamage(){
	if(genericOrbs-windBladeDamageCost>0){
		genericOrbs =genericOrbs-windBladeDamageCost;
	windBladeDamage++;
	windBladeDamageCost+=50;
	}
}
public void upgradeWindBladeFireRate(){
	if(genericOrbs-windBladeFireRateCost>0){
		genericOrbs =genericOrbs-windBladeFireRateCost;
	windBladeFireRate-=windBladeFireRate/5;
	windBladeFireRateCost+=75;
	}
}
public void upgradeWindFormVelocity(){
	if(genericOrbs-fireballVelocityCost>0){
		genericOrbs =genericOrbs-windFormVelocityCost;
	windFormVelocityBoost += 50;
	speed = baseSpeed;
	windFormVelocityCost+=20;
	}
}
}