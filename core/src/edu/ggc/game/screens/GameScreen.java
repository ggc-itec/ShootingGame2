package edu.ggc.game.screens;

import com.badlogic.gdx.Gdx;
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
        setSizeAndPosition();        
    }

    @Override
    public void render(float delta)
    {
        
        GL20 gl = Gdx.graphics.getGL20();
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();
        Sprite spr = (Sprite) arrowAnimation.getKeyFrame(stateTime, true);
        
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        spr.draw(batch);;
        batch.end();
        
    }
    
    private void setSizeAndPosition()
    {
        for(int i = 0; i < arrow.size; i++)
        {
            arrow.get(i).setSize(1.0f, 1.0f);
            arrow.get(i).setPosition(0.0f, 0.0f);
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
