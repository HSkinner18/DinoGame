package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.Random;

public class JumpingGame extends ApplicationAdapter {
	ShapeRenderer sr;
	SpriteBatch batch;
	Texture playerTexture;
	Floor floor;
	Player player;
	CactusSmall[] cactusSmall = new CactusSmall[5];

	float gravity = -1000f; // Adjust the gravity to control the jump height
	Random rand = new Random();
	int upperbound = 2000;
	int lowerbound = 1100;


	@Override
	public void create () {
		sr = new ShapeRenderer();
		batch = new SpriteBatch();
		//batch = new SpriteBatch();
		//playerTexture = new Texture("player.png"); // Replace with your player texture
		floor = new Floor(0, 0, Gdx.graphics.getWidth(), 100, Color.BLUE);
		player = new Player(220, 100, 120, 160, Color.BLACK);
		playerTexture = new Texture("Dinosaur.png");

		for(int i = 0; i < cactusSmall.length; i++){
			cactusSmall[i] = new CactusSmall((Gdx.graphics.getWidth() - 300) - (i*260), 100, 250, 100, Color.WHITE);
		}
	}

	@Override
	public void render () {

		ScreenUtils.clear(0, 0, 0, 1);

		handleInput();
		update();
		cactusMovement();

		cactusRespawn();

		super.render();



		sr.begin(ShapeRenderer.ShapeType.Filled);
		floor.draw(sr);;
		for(int i = 0; i < cactusSmall.length; i++) {
			cactusSmall[i].draw(sr);
		}
		batch.begin();
		batch.draw(playerTexture, player.getX(), player.getY());
		batch.end();
		sr.end();
	}

	private void handleInput() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			if (player.getY() == 100) { // Check if the player is on the ground before jumping
				player.setPlayerYvelocity(900); // Set the initial velocity for jumping
			}
		}
	}

	private void update() {
		float deltaTime = Gdx.graphics.getDeltaTime();

		// Apply gravity to the player's velocity
		player.setPlayerYvelocity((int) (player.getplayerYvelocity() + gravity * deltaTime));

		// Update player's position based on velocity
		player.setY((int) (player.getY() + player.getplayerYvelocity() * deltaTime));

		// Keep the player above the ground
		if (player.getY() < 100) {
			player.setY(100);
			player.setPlayerYvelocity(0);
		}
	}

	public void cactusMovement() {
		float delta = Gdx.graphics.getDeltaTime();
		float carSpeed = 300; // Set your desired speed

		for (int i = 0; i < cactusSmall.length; i++) {
			cactusSmall[i].setX((int) (cactusSmall[i].getX() + -(carSpeed * delta)));

			if (cactusSmall[i].getX() + cactusSmall[i].getW() < 0) {
				// Reset the cactus to the right side of the screen
				cactusSmall[i].setX(Gdx.graphics.getWidth());
			}
		}

		// Move cactusRespawn call outside the loop
	}

	public void cactusRespawn() {
		int random_integer = rand.nextInt(upperbound-lowerbound) + lowerbound;

		for (int i = 0; i < cactusSmall.length; i++) {
			if (cactusSmall[i].getX() > Gdx.graphics.getWidth()) {
				// Reset the cactus to the right side of the screen
				cactusSmall[i].setX(Gdx.graphics.getWidth() + random_integer + cactusSmall[i].getW() + 200);
			}
		}
	}


	@Override
	public void dispose () {
		batch.dispose();
		playerTexture.dispose();
	}
}