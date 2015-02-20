package edu.ggc.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen
{
    private SpriteBatch batch;
    private OrthographicCamera camera;//This is needed for rendering
    private TextureAtlas spriteSheet;
    private Array<Sprite> arrow;
    
    private int currentFrame = 0;
    private final float frameLength = 0.1f;
    private float animationElapsed = 0.0f;

    @Override
    public void show()
    {
        batch = new SpriteBatch();
        spriteSheet = new TextureAtlas("test/spritesheet.txt");
        arrow = spriteSheet.createSprites("arrow");
        
        for(int i = 0; i < arrow.size; i++)
        {
            arrow.get(i).setSize(3.0f, 3.0f);
            arrow.get(i).setPosition(-1.5f, -1.5f);
        }
    }
    
    private Pixmap createPixmap(int width, int height)
    {
        Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
        pixmap.setColor(1, 0, 0, 0.5f);
        pixmap.fill();
        
        pixmap.setColor(1, 1, 0, 1);
        pixmap.drawLine(0, 0, width, height);
        pixmap.drawLine(width, 0, 0, height);
        
        pixmap.setColor(0, 1, 1, 1);
        pixmap.drawRectangle(0, 0, width, height);

        return pixmap;
    }

    @Override
    public void render(float delta)
    {
        System.out.println("Delta: " + delta);
        animationElapsed += delta;
        System.out.println("Elapsed Time: " + animationElapsed);
        while(animationElapsed > frameLength) {
            animationElapsed -= frameLength;
            currentFrame = (currentFrame == arrow.size - 1)? 0 : ++currentFrame;
        }
        
        GL20 gl = Gdx.graphics.getGL20();
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        arrow.get(currentFrame).draw(batch);
        batch.end();
        
    }

    @Override
    public void resize(int width, int height)
    {
        float camWidth = 10.f;
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
