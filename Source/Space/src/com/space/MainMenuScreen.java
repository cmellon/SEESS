package com.space;
/**
 * Main Menu Screen-
 * This screen only uses three buttons. It features a single image and displays a glow effect on all buttons when 
 * the keyboard is used to change the selection or the button is clicked on by the mouse.
 * 
 * 
 */
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

public class MainMenuScreen implements Screen {
	Texture glowImg;
	private Texture mainMenuImg;
	public Space main; 
	private boolean stillPressed = false;
	private int currentSelection = 0;
	private Rectangle selector;
	private Rectangle newGameRect;
	private Rectangle loadGameRect;
	private Rectangle optionsRect;
	private OrthographicCamera camera;
	private  SpriteBatch batch;
	
	  private Texture texture;
	    private Sprite sprite;
	    private BitmapFont font;
	    
	    

	public MainMenuScreen(Space game) { 
		main = game;
		mainMenuImg = main.mainMenuImg;
		glowImg = main.mainMenuScreenGlowImg;
		/**
		 * Rectangles:
		 * selector- miniscule rectangle that moves to wherever the user clicks on the screen. It is used to track
		 * what area was last clicked. 
		 * 
		 * newGameRect - 
		 */
		selector = new Rectangle();
		
		selector.width = 2; 
		selector.height = 2;
		selector.x = 400;
		selector.y = 240;
		
		newGameRect = new Rectangle();
		loadGameRect = new Rectangle();
		optionsRect = new Rectangle();
		
		newGameRect.width = 186;
		newGameRect.height = 50;
		newGameRect.x = 502+55;
		newGameRect.y = 200;
		
		loadGameRect.width = 186;
		loadGameRect.height = 50;
		loadGameRect.x = 502+55;
		loadGameRect.y = 200-75;
		
		optionsRect.width = 186;
		optionsRect.height = 50;
		optionsRect.x = 502+55;
		optionsRect.y = 200-150;
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

	        font = main.font;
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
			if (selector.overlaps(newGameRect))main.setScreen(main.gameScreen);
			if (selector.overlaps(loadGameRect)){
				System.out.println("Activated Load Event");
				if(!Space.player.gameLoaded){
					System.out.println("Starting Game Load");
					Space.player.loadGame();//main.setScreen(main.loadGameScreen);
					Space.player.speed = Space.player.baseSpeed;
					Space.player.health = Space.player.healthMax;
					main.setScreen(main.gameScreen);
				}
			}

		}
		
		if(Gdx.input.isKeyPressed(Keys.ENTER)){
			if (currentSelection == 0||selector.overlaps(newGameRect))main.setScreen(main.gameScreen);
			if (currentSelection == 1||selector.overlaps(loadGameRect))Space.player.loadGame();//main.setScreen(main.loadGameScreen);

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
		batch.draw(mainMenuImg,0,0);
		if (currentSelection == 0||selector.overlaps(newGameRect)){
			batch.draw(glowImg, 488+55, 182);
		}
		if (currentSelection == 1||selector.overlaps(loadGameRect)){
			batch.draw(glowImg,  488+55, 182-75);
		}
		if (currentSelection == 2||selector.overlaps(optionsRect)){
			batch.draw(glowImg,  488+55, 182-150);
		}
		//batch.draw(glowImg, 0, 240);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		
		selector.x = 400;
		selector.y = 240;
		stillPressed = true;
		currentSelection = -1;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		selector.x = 400;
		selector.y = 240;
		stillPressed = true;
		currentSelection = -1;
		Space.player.gameLoaded = false;
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
		selector.x = 400;
		selector.y = 240;
		stillPressed = true;
		currentSelection = 1;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	

}
