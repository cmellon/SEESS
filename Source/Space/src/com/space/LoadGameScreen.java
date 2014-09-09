package com.space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
//If I want to use separate save/load slots I have built this class to handle loading and saving game when a slot is
//selected and the launch button is selected
public class LoadGameScreen implements Screen {
	
	private Texture glowImg;
	private Texture loadScreenImg;
	public Space main; 
	private boolean stillPressed = false;
	private int currentSelection = 0;
	private Rectangle selector;
	private Rectangle backRect;
	private Rectangle launchRect;
	private OrthographicCamera camera;
	private  SpriteBatch batch;
	
	  private Texture texture;
	    private Sprite sprite;
	    private BitmapFont font;
	    
	    

	public LoadGameScreen(Space game) { 
		main = game;
		loadScreenImg = main.loadScreenImg;
		glowImg = main.mainMenuScreenGlowImg;
        font = main.font;

		selector = new Rectangle();
		
		selector.width = 2;
		selector.height = 2;
		selector.x = 400;
		selector.y = 240;
		
		backRect = new Rectangle();
		launchRect = new Rectangle();
		
		backRect.width = 186;
		backRect.height = 50;
		backRect.x = 12;
		backRect.y = 14;
		
		launchRect.width = 186;
		launchRect.height = 50;
		launchRect.x = 603;
		launchRect.y = 14;
		
		camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      
	      batch = new SpriteBatch();
	      
	        texture = main.texture;
	        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

	        TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);

	        sprite = new Sprite(region);
	        sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
	        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
	        sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);


			
			
	}

	@Override
	public void render(float delta) {
		
		if(Gdx.input.isTouched()){
				currentSelection = -1;
				Vector3 touchPos = new Vector3();
			    touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			    camera.unproject(touchPos);
			    
			    selector.x = touchPos.x;
			    selector.y = touchPos.y;
		}
		if(!Gdx.input.isTouched()){
			if (selector.overlaps(backRect))main.setScreen(main.mainMenu);
			if (selector.overlaps(launchRect))main.setScreen(main.gameScreen);
		}
		
		if(Gdx.input.isKeyPressed(Keys.ENTER)){
			if (currentSelection == 0||selector.overlaps(backRect))main.setScreen(main.mainMenu);
			if (currentSelection == 1||selector.overlaps(launchRect))main.setScreen(main.gameScreen);

		}
		if(Gdx.input.isKeyPressed(Keys.UP)){
			if(!stillPressed)currentSelection = (currentSelection-1);
			if (currentSelection <= -1)currentSelection = 2;
			stillPressed = true;
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			if(!stillPressed)currentSelection = (currentSelection+1);
			if (currentSelection >= 3)currentSelection = 0;
			stillPressed = true;
		}
		if(!Gdx.input.isKeyPressed(Keys.DOWN)&&!Gdx.input.isKeyPressed(Keys.UP)){stillPressed = false;}
		
		batch.begin();
		batch.draw(loadScreenImg,0,0);
		if (currentSelection == 0||selector.overlaps(backRect)){
			batch.draw(glowImg, backRect.x-15, backRect.y-18);
		}
		if (currentSelection == 1||selector.overlaps(launchRect)){
			batch.draw(glowImg,  launchRect.x-15, launchRect.y-18);
		}

		//batch.draw(glowImg, 0, 240);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
