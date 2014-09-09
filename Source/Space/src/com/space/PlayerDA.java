package com.space;


import java.io.*;
import java.util.Scanner;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class PlayerDA  {
	private Player player;
	public PlayerDA (){}
	String fname = new String("saveGame.txt"); 
	Scanner in;
	boolean writeInitialized;
	PrintWriter out;

	public int save(){
			try {
				if(Gdx.files.local(fname).exists()){//if its already there
					FileHandle file = Gdx.files.local(fname);
					file.delete();//delete it
				}
				out = new PrintWriter(new FileWriter(fname, true));//Create a new one

				out.println(Long.toString(Space.player.laserFireRate));
				out.println(Integer.toString(Space.player.shipLvl));
				out.println(Integer.toString(Space.player.baseSpeed));
				out.println(Double.toString(Space.player.health));
				out.println(Double.toString(Space.player.healthMax));
				out.println(Double.toString(Space.player.laserDamage));
				out.println(Long.toString(Space.player.laserFireRate));

				out.println(Integer.toString(Space.player.healthCost));
				out.println(Integer.toString(Space.player.laserDamageCost));
				out.println(Integer.toString(Space.player.laserFireRateCost));
				out.println(Integer.toString(Space.player.velocityCost));

				//Fire
				out.println(Integer.toString(Space.player. fireFormLvl));
				out.println(Long.toString(Space.player.fireFormDuration));
				out.println(Integer.toString(Space.player.fireballDamage));
				out.println(Integer.toString(Space.player.fireballVelocity));
				out.println(Long.toString(Space.player.fireballFireRate));//second divided by number of projectiles per second
				out.println(Integer.toString(Space.player.fireCapacity)); // number of orbs necessary to transform

				out.println(Integer.toString(Space.player.fireFormDurationCost));//Upgrade costs
				out.println(Integer.toString(Space.player.fireballDamageCost));
				out.println(Integer.toString(Space.player.fireballFireRateCost));
				out.println(Integer.toString(Space.player.fireballVelocityCost));
				out.println(Integer.toString(Space.player.fireCapacityCost)); 

				//Lightning
				out.println(Integer.toString(Space.player.lightningFormLvl));
				out.println(Double.toString(Space.player. lightningBoltDamage));
				out.println(Integer.toString(Space.player.lightningBoltVelocity));
				out.println(Long.toString(Space.player.lightningFormDuration));
				out.println(Long.toString(Space.player.lightningBoltFireRate));
				out.println(Integer.toString(Space.player.lightningCapacity));

				out.println(Integer.toString(Space.player.lightningFormDurationCost ));
				out.println(Integer.toString(Space.player.lightningDamageCost));

				//Wind
				out.println(Integer.toString(Space.player.windFormLvl));
				out.println(Long.toString(Space.player.windFormDuration));
				out.println(Long.toString(Space.player.windBladeFireRate));
				out.println(Integer.toString(Space.player.windCapacity));
				out.println(Integer.toString(Space.player.windFormVelocityBoost));

				out.println(Integer.toString(Space.player.windFormDurationCost));
				out.println(Integer.toString(Space.player.windBladeDamageCost));
				out.println(Integer.toString(Space.player.windBladeFireRateCost));
				out.println(Integer.toString(Space.player.windFormVelocityCost));
				
				out.println(Integer.toString(Space.player.shipXp));
				out.println(Integer.toString(Space.player.fireFormXp));
				out.println(Integer.toString(Space.player.windFormXp));
				out.println(Integer.toString(Space.player.lightningFormXp));
				out.println(Integer.toString(Space.player.careerKills));

				out.println(Integer.toString(Space.player.genericOrbs));
				out.println(Integer.toString(Space.player.spendableFireOrbs));
				out.println(Integer.toString(Space.player.spendableWindOrbs));
				out.println(Integer.toString(Space.player.spendableLightningOrbs));
				out.println(Integer.toString(Space.player.fireOrbs));
				out.println(Integer.toString(Space.player.windOrbs));
				out.println(Integer.toString(Space.player.lightningOrbs));
				
				out.close();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				return 1;
			} //Catch for saving
			return 0;
	}
	public int load(){
		
		try {// try opening file and starting scanner
			System.out.println("Loading Values...");
	    	File f = new File(fname); 
			in = new Scanner(f);
			Space.player.laserFireRate = Long.parseLong(in.nextLine());
			Space.player.shipLvl = Integer.parseInt(in.nextLine());
			Space.player.baseSpeed = Integer.parseInt(in.nextLine());
			Space.player.health = Double.parseDouble(in.nextLine());
			Space.player.healthMax = Double.parseDouble(in.nextLine());
			Space.player.laserDamage = Double.parseDouble(in.nextLine());
			Space.player.laserFireRate = Long.parseLong(in.nextLine());

			Space.player.healthCost = Integer.parseInt(in.nextLine());
			Space.player.laserDamageCost = Integer.parseInt(in.nextLine());
			Space.player.laserFireRateCost = Integer.parseInt(in.nextLine());
			Space.player.velocityCost = Integer.parseInt(in.nextLine());

			//Fire
			Space.player. fireFormLvl = Integer.parseInt(in.nextLine());
			Space.player.fireFormDuration = Long.parseLong(in.nextLine());
			Space.player.fireballDamage = Integer.parseInt(in.nextLine());
			Space.player.fireballVelocity = Integer.parseInt(in.nextLine());
			Space.player.fireballFireRate = Long.parseLong(in.nextLine());
			Space.player.fireCapacity = Integer.parseInt(in.nextLine());

			Space.player.fireFormDurationCost = Integer.parseInt(in.nextLine());
			Space.player.fireballDamageCost = Integer.parseInt(in.nextLine());
			Space.player.fireballFireRateCost = Integer.parseInt(in.nextLine());
			Space.player.fireballVelocityCost = Integer.parseInt(in.nextLine());
			Space.player.fireCapacityCost = Integer.parseInt(in.nextLine()); 

			//Lightning
			Space.player.lightningFormLvl = Integer.parseInt(in.nextLine());
			Space.player. lightningBoltDamage = Double.parseDouble(in.nextLine());
			Space.player.lightningBoltVelocity = Integer.parseInt(in.nextLine());
			Space.player.lightningFormDuration = Long.parseLong(in.nextLine());
			Space.player.lightningBoltFireRate = Long.parseLong(in.nextLine());
			Space.player.lightningCapacity = Integer.parseInt(in.nextLine());

			Space.player.lightningFormDurationCost = Integer.parseInt(in.nextLine());
			Space.player.lightningDamageCost = Integer.parseInt(in.nextLine());

			//Wind
			Space.player.windFormLvl = Integer.parseInt(in.nextLine());
			Space.player.windFormDuration = Long.parseLong(in.nextLine());
			Space.player.windBladeFireRate = Long.parseLong(in.nextLine());
			Space.player.windCapacity = Integer.parseInt(in.nextLine());
			Space.player.windFormVelocityBoost = Integer.parseInt(in.nextLine());

			Space.player.windFormDurationCost = Integer.parseInt(in.nextLine());
			Space.player.windBladeDamageCost = Integer.parseInt(in.nextLine());
			Space.player.windBladeFireRateCost = Integer.parseInt(in.nextLine());
			Space.player.windFormVelocityCost = Integer.parseInt(in.nextLine());
			
			//Stats
			Space.player.shipXp = Integer.parseInt(in.nextLine());
			Space.player.fireFormXp = Integer.parseInt(in.nextLine());
			Space.player.windFormXp = Integer.parseInt(in.nextLine());
			Space.player.lightningFormXp = Integer.parseInt(in.nextLine());
			Space.player.careerKills = Integer.parseInt(in.nextLine());

			Space.player.genericOrbs = Integer.parseInt(in.nextLine());
			Space.player.spendableFireOrbs = Integer.parseInt(in.nextLine());
			Space.player.spendableWindOrbs = Integer.parseInt(in.nextLine());
			Space.player.spendableLightningOrbs = Integer.parseInt(in.nextLine());
			Space.player.fireOrbs = Integer.parseInt(in.nextLine());
			Space.player.windOrbs = Integer.parseInt(in.nextLine());
			Space.player.lightningOrbs = Integer.parseInt(in.nextLine());
			/*
			//Printout
			System.out.println(Space.player.laserFireRate);
			System.out.println(Space.player.shipLvl);
			System.out.println(Space.player.baseSpeed);
			System.out.println(Space.player.health);
			System.out.println(Space.player.healthMax);
			System.out.println(Space.player.laserDamage);
			System.out.println(Space.player.laserFireRate);

			System.out.println(Space.player.healthCost);
			System.out.println(Space.player.laserDamageCost);
			System.out.println(Space.player.laserFireRateCost);
			System.out.println(Space.player.velocityCost);

			//Fire
			System.out.println(Space.player. fireFormLvl);
			System.out.println(Space.player.fireFormDuration);
			System.out.println(Space.player.fireballDamage);
			System.out.println(Space.player.fireballVelocity);
			System.out.println(Space.player.fireballFireRate);
			System.out.println(Space.player.fireCapacity);

			System.out.println(Space.player.fireFormDurationCost);
			System.out.println(Space.player.fireballDamageCost);
			System.out.println(Space.player.fireballFireRateCost);
			System.out.println(Space.player.fireballVelocityCost);
			System.out.println(Space.player.fireCapacityCost); 

			//Lightning
			System.out.println(Space.player.lightningFormLvl);
			System.out.println(Space.player. lightningBoltDamage);
			System.out.println(Space.player.lightningBoltVelocity);
			System.out.println(Space.player.lightningFormDuration);
			System.out.println(Space.player.lightningBoltFireRate);
			System.out.println(Space.player.lightningCapacity);

			System.out.println(Space.player.lightningFormDurationCost);
			System.out.println(Space.player.lightningDamageCost);

			//Wind
			System.out.println(Space.player.windFormLvl);
			System.out.println(Space.player.windFormDuration);
			System.out.println(Space.player.windBladeFireRate);
			System.out.println(Space.player.windCapacity);
			System.out.println(Space.player.windFormSpeedBoost);

			System.out.println(Space.player.windFormDurationCost);
			System.out.println(Space.player.windBladeDamageCost);
			System.out.println(Space.player.windBladeFireRateCost);
			System.out.println(Space.player.windFormVelocityCost);
			
			
			System.out.println(Space.player.shipXp);
			System.out.println(Space.player.fireFormXp);
			System.out.println(Space.player.windFormXp);
			System.out.println(Space.player.lightningFormXp);
			System.out.println(Space.player.careerKills);

			System.out.println(Space.player.genericOrbs);
			System.out.println(Space.player.spendableFireOrbs);
			System.out.println(Space.player.spendableWindOrbs);
			System.out.println(Space.player.spendableLightningOrbs);
			System.out.println(Space.player.fireOrbs);
			System.out.println(Space.player.windOrbs);
			System.out.println(Space.player.lightningOrbs);
			*/
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    in.close();
			return 1;
		}
	    in.close();
	    System.out.println("Load Complete!");
	    return 0;
	}
	
}


