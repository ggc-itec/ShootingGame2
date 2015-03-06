package edu.ggc.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.ggc.game.Assets;
import edu.ggc.game.ShootingGame;

/**
 * Represents the Main Menu for the game.
 * 
 * A Screen represents menu, settings, game screen
 */
public class MainMenu implements Screen
{
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton exitButton;
    private TextButton creditsButton;
    private TextButton playButton;
    
    private Music menuMusic;
    
    private Label heading;
    
    /**
     * Called when g.setScreen() is called.
     */
    @Override
    public void show()
    {
        stage = new Stage();
        
        Gdx.input.setInputProcessor(stage);
        
        atlas = new TextureAtlas(Assets.BUTTON_TEXTURE);
        skin = new Skin(Gdx.files.internal(Assets.MENU_SKIN), atlas);
        
        Image background = new Image(new Texture(Gdx.files.internal(Assets.BACKGROUND)));//TODO maybe a better way to display background
                
        table = new Table(skin);
        table.setBackground(background.getDrawable());
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());//Table fills the whole screen
        
        setUpButton();
        setupCreditsButton();
        setupPlayButtons();
        
        //create the Label to display the game name
        heading = new Label(Assets.TITLE, skin);
        heading.setFontScale(2.0f);
        
        table.add(heading).spaceBottom(50).row();
        table.add(exitButton).spaceBottom(5).row();
        table.add(creditsButton).spaceBottom(5).row();
        table.add(playButton);
//        table.debug();//Turns on debug lines
        stage.addActor(table);
        
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal(Assets.MENU_MUSIC));
        menuMusic.setLooping(true);
        menuMusic.play();
    }
    
    private void setUpButton()
    {        
        exitButton = new TextButton("Exit", skin);
        exitButton.pad(10);//Padding around the button's text
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                Gdx.app.exit();
            }
        });
    }

    //setup Credits Button
    private void setupCreditsButton(){
        creditsButton = new TextButton("Credits", skin);
        creditsButton.pad(10);//Padding around the button's text
        creditsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new Credits());
            }
        });
    }
    
    private void setupPlayButtons() {
        
        playButton = new TextButton("Play", skin);
        playButton.pad(10);//Padding around the button's text
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            }
        });
    }
    
    @Override
    public void render(float delta)
    {
        stage.act(delta);
        stage.draw();
//        System.out.println("FPS: " + Gdx.graphics.getFramesPerSecond());
    }

    @Override
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause()
    {
        if(menuMusic.isPlaying())
            menuMusic.pause();
    }

    @Override
    public void resume()
    {
        if(!menuMusic.isPlaying())
            menuMusic.play();
    }

    /**
     * This is called automatically.
     */
    @Override
    public void hide()
    {
        if(menuMusic.isPlaying())
            menuMusic.pause();
    }

    /**
     * This method must be called manually.
     */
    @Override
    public void dispose()
    {
        stage.dispose();
        atlas.dispose();
        skin.dispose();
        menuMusic.dispose();
    }

}
