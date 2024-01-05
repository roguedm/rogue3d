package com.roguedm.r3d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Rogue3d extends Game {

	@Override
	public void create () {
		setScreen(new MainScreen(this));
	}

	@Override
	public void dispose () {
		if (this.getScreen() != null) {
			this.getScreen().dispose();
		}
	}

}
