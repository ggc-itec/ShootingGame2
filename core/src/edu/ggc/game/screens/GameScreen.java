package edu.ggc.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen
{
    private Animation arrowAnimation;
    private SpriteBatch batch;
    private float stateTime = 0.0f;
    
    private OrthographicCamera camera;//This is needed for rendering
    private TextureAtlas spriteSheet;
    
    private Array<Sprite> arrow;

    @Override
    public void show()
    {
        batch = new SpriteBatch();
        spriteSheet = new TextureAtlas("test/spritesheet.txt");
        
        arrow = spriteSheet.createSprites("arrow");
        arrowAnimation = new Animation(1/30.0f, arrow);
        setSize();
    }

    @Override
    public void render(float delta)
    {
        handleInput(delta);
        GL20 gl = Gdx.graphics.getGL20();
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();
        Sprite spr = (Sprite) arrowAnimation.getKeyFrame(stateTime, true);
        System.out.println("X: " +spr.getX() + " Y: " + spr.getY());
        
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
//        spr.draw(batch);
        batch.draw(spr, 50, 50);
        batch.end();
        
    }
    
    private void handleInput(float delta)
    {
        float moveSpeed = 3.0f * delta;
//        System.out.println("Move Speed: " + moveSpeed);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            moveSprite(-1 * moveSpeed, 0.0f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            moveSprite(moveSpeed, 0.0f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            moveSprite(0.0f, moveSpeed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            moveSprite(0.0f, -1 * moveSpeed);
        }
    }
    
    private void moveSprite(float x, float y)
    {
        for(Sprite s : arrow)
        {
            s.translate(x, y);
        }
    }
    
    private void setSize()
    {
        for(int i = 0; i < arrow.size; i++)
        {
            arrow.get(i).setSize(1.0f, 1.0f);
            arrow.get(i).setCenter(0, 0);
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
