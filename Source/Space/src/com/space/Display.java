package com.space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/* Purpose: 
 This class will contain the members and components that make up the heads up display on the screen
*/

public class Display {
	Rectangle background1;
	   Rectangle background2;
	   Texture backgroundImg;
	   
	   public Display(Texture backgroundImg){
		   this.backgroundImg = backgroundImg;
		   
		   background1 = new Rectangle();
		      background1.x = 0;
		      background1.y = 0;
		      background1.width = 1000;
		      background1.height = 480;
		      
		      background2 = new Rectangle();
		      background2.x = 1000;
		      background2.y = 0;
		      background2.width = 1000;
		      background2.height = 480;
	   }

	public void progressBackground() { 
		// TODO Auto-generated method stub
		background1.x -= 20 * Gdx.graphics.getDeltaTime();
	     background2.x -= 20 * Gdx.graphics.getDeltaTime();
	     if(background1.x < -1000)background1.x=1000;
	     if(background2.x < -1000)background2.x=1000;
	}

}
