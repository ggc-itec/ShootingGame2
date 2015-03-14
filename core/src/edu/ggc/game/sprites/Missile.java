package edu.ggc.game.sprites;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Missile extends CustomSprite {

	private int x;
	private int y;
	private int width = 20;
	private int height = 50;
	private Pixmap pixmap;
	private Texture texture;
	private Sprite sprite;
	
	public Missile(int x, int y) {
		this.x = x;
		this.y = y;
		pixmap = createPixmap();
		texture = new Texture(pixmap);
		sprite = new Sprite(texture);
	}
	
	private Pixmap createPixmap() {
		Pixmap map = new Pixmap(width,height,Format.RGBA8888);
		// set color of missile to green, fully opaque
		map.setColor(0,1,0,1f);
		map.fill();
		map.drawRectangle(0, 0, width, height);
		return map;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(getSprite(),x,y++);
	}
}
