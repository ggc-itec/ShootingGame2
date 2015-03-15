package edu.ggc.game.screens;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

import edu.ggc.game.sprites.CustomSprite;
import edu.ggc.game.sprites.Missile;

public class GameScreen implements Screen
{
    private Animation arrowAnimation;
    private SpriteBatch batch;
    private float stateTime = 0.0f;
    private OrthographicCamera camera;//This is needed for rendering
    private TextureAtlas spriteSheet;
    private Array<Sprite> arrow;
    private float arrowX;
    private float arrowY;
    private Sprite logo;
    private ArrayList<CustomSprite> sprites;
    private Random random= new Random();
    
    @Override
    public void show()
    {
        batch = new SpriteBatch();
        //Note: must set camera to batch in order to draw sprites
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.setProjectionMatrix(camera.combined);
        spriteSheet = new TextureAtlas("test/spritesheet.txt");        
        arrow = spriteSheet.createSprites("arrow");
        arrowAnimation = new Animation(1/30.0f, arrow);
        logo = new Sprite(new Texture("badlogic.jpg"));
        sprites = new ArrayList<CustomSprite>();
        sprites.add(new Missile(100,100));
    }

    @Override
    public void render(float delta)
    {
        handleInput(delta);
        GL20 gl = Gdx.graphics.getGL20();
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        drawSprite();
    }
    
    private void drawSprite() {
    	 stateTime += Gdx.graphics.getDeltaTime();
         Sprite spr = (Sprite) arrowAnimation.getKeyFrame(stateTime, true);
         batch.begin();
         batch.draw(spr, (int)arrowX, (int)arrowY);
         batch.draw(logo, (int) (-300*Math.cos(stateTime)), (int) (-100*Math.sin(stateTime)));
         for (CustomSprite c: sprites)
         {
        	 c.render(batch);
         }
         batch.end();
	}
    
    private void handleInput(float delta)
    {
        float moveSpeed = 30.0f * delta;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            arrowX = arrowX  - (moveSpeed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            arrowX = arrowX + (moveSpeed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            arrowY = arrowY + (moveSpeed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            arrowY = arrowY - (moveSpeed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
        	
        	sprites.add(new Missile(random.nextInt(Gdx.graphics.getWidth()),100));
        }
    }
    
    @Override
    public void resize(int width, int height)
    {
        float camWidth = 5.0f;
        float camHeight = camWidth * (float) height / (float) width;
        camera = new OrthographicCamera(camWidth, camHeight);
        camera.update();
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void hide()
    {
    }

    @Override
    public void dispose()
    {
    }
}
