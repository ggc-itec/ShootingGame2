package edu.ggc.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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
    private BitmapFont blackHoleFont;
    
    private Music menuMusic;
    
    private Label heading;
    
    private ShootingGame game;
    
    public MainMenu(ShootingGame game){
    	this.game = game;
    }
    
    /**
     * Called when g.setScreen() is called.
     */
    @Override
    public void show()
    {
        stage = new Stage();
        
        Gdx.input.setInputProcessor(stage);
        
        atlas = new TextureAtlas("ui/button.pack");
        skin = new Skin(atlas);
        blackHoleFont = new BitmapFont(Gdx.files.internal("font/blackhole.fnt"));
        
        Image background = new Image(new Texture(Gdx.files.internal("background.jpg")));//TODO maybe a better way to display background
        
        table = new Table(skin);
        table.setBackground(background.getDrawable());
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());//Table fills the whole screen
        
        setUpButton();
        setupCreditsButton();
        
        //create the Label to display the game name
        LabelStyle labelStyle = new LabelStyle(blackHoleFont, Color.BLUE);
        heading = new Label("Shooting Game 2", labelStyle);
        heading.setFontScale(2.0f);
        
        table.add(heading).spaceBottom(50).row();
        table.add(exitButton).row();
        table.add(creditsButton);
        table.debug();//Turns on debug lines
        stage.addActor(table);
        
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("music/menu.mp3"));
        menuMusic.setLooping(true);
        menuMusic.play();
    }
    
    private void setUpButton()
    {
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button_up");//button_up is defined in ui/button.pack
        textButtonStyle.down = skin.getDrawable("button_down");//button_down is defined in ui/button.pack
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = blackHoleFont;
        textButtonStyle.fontColor = Color.BLUE;
        
        creditsButton = new TextButton("Exit", textButtonStyle);
        creditsButton.pad(10);//Padding around the button's text
        creditsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                Gdx.app.exit();
            }
        });
    }

    //setup Credits Button
    private void setupCreditsButton(){
    	TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button_up");//button_up is defined in ui/button.pack
        textButtonStyle.down = skin.getDrawable("button_down");//button_down is defined in ui/button.pack
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = blackHoleFont;
        textButtonStyle.fontColor = Color.BLUE;
        
        exitButton = new TextButton("Credits", textButtonStyle);
        exitButton.pad(10);//Padding around the button's text
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(game.creditScreen);
            }
        });
    }
    
    @Override
    public void render(float delta)
    {
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        System.out.println("FPS: " + Gdx.graphics.getFramesPerSecond());
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
        blackHoleFont.dispose();
        menuMusic.dispose();
    }

}
