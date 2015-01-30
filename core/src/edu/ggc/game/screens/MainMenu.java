package edu.ggc.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Represents the Main Menu for the game.
 * 
 * A Screen represents menu, settings, game screen
 */
public class MainMenu implements Screen
{
    //2D scene containing a hierarchy of actors
    private Stage stage = new Stage();
    
    private Table table = new Table();
    
    //Resource for UI components
    private Skin skin;
    
    private TextButton exitButton;
    
    private Music menuMusic;
        
    public MainMenu()
    {
        setUpSkin();
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("menu.mp3"));
        menuMusic.setLooping(true);
    }
    
    public void setUpSkin()
    {
        skin = new Skin();
        
        //TODO Fix Me look at this tutorial http://www.toxsickproductions.com/libgdx/libgdx-basics-skins-json-and-bitmapfonts/
        Pixmap pixmap = new Pixmap(150, 150, Format.RGBA8888);
        pixmap.setColor(Color.WHITE);//Foreground color
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        
        BitmapFont font = new BitmapFont();
        font.scale(1.5f);
        skin.add("simple", font);
        
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.GREEN);//When this button shows up
        textButtonStyle.down = skin.newDrawable("white", Color.YELLOW);//When the button is clicked and held
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);//After being clicked and released
        textButtonStyle.over = skin.newDrawable("white", Color.RED);//When mouse hovers over it
        textButtonStyle.font = skin.getFont("simple");
        skin.add("simple", textButtonStyle);
        
        exitButton=new TextButton("Exit",textButtonStyle);
        exitButton.setPosition(0, 0);
    }
    
    @Override
    public void show()
    {
        table.add(exitButton).row();//Add button to table
        table.setFillParent(true);
        stage.addActor(table);//Add table to stage
        Gdx.input.setInputProcessor(stage);
        menuMusic.play();
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
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
        stage.dispose();
        skin.dispose();
    }

}
