package edu.ggc.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.ggc.game.ShootingGame;

public class Credits implements Screen
{
    private ShootingGame g;
    
    private Skin skin;
    
    private Pixmap pixmap;
    private Pixmap rect;
    
    private TextButton backButton;
    
    private Stage stage = new Stage();
    
    private Sprite sprite;
    
    public Credits(ShootingGame g)
    {
        this.g = g;
        setUpSkin();
        stage.addActor(backButton);
        addListener();
        
        rect = new Pixmap(50, 50, Format.RGBA8888);
        rect.setColor(Color.PINK);
        rect.fill();
//        
//        sprite = new Sprite(new Texture(rect));
//        sprite.setSize(1, 1);
//        sprite.setOrigin(0, 0);
//        sprite.setPosition(50, 50);
        
//        Actor act = new Actor();
        Image img = new Image(new Texture(rect));
        img.setPosition(50, 50);
        stage.addActor(img);
    }
    
    private void setUpSkin()
    {
        skin = new Skin();
        
        //TODO Fix Me look at this tutorial http://www.toxsickproductions.com/libgdx/libgdx-basics-skins-json-and-bitmapfonts/
        pixmap = new Pixmap(150, 150, Format.RGBA8888);//This object must call dispose()
        pixmap.setColor(Color.WHITE);//Foreground color
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        
        BitmapFont font = new BitmapFont();
        font.scale(1.5f);
        skin.add("simple", font);
        
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.GREEN);//When this button shows up
        textButtonStyle.down = skin.newDrawable("white", Color.YELLOW);//When the button is clicked and held
//        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);//After being clicked and released
        textButtonStyle.over = skin.newDrawable("white", Color.RED);//When mouse hovers over it
        textButtonStyle.font = skin.getFont("simple");
        
        backButton = new TextButton("Back", textButtonStyle);
        backButton.setPosition(0,  0);
    }
    
    private void addListener()
    {
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                g.setScreen(g.menuScreen);
            }
        });
    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
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
        pixmap.dispose();
        rect.dispose();
    }

}
