package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Enums.Direction;

public class Pacman extends Entity {
    Animation<TextureRegion>[] moveAnimation;
    boolean animLoop;
    float stateTime;
    Texture texture;

    public Pacman(float x, float y, float speed, Direction DIRECTION) {
        super(x, y, speed, DIRECTION);
        texture = new Texture("sprites.png");


        // create 2d array of textures
        TextureRegion[][] frameSpriteSheet = TextureRegion.split(texture,
                texture.getWidth() / FRAME_COLS,
                texture.getHeight() / FRAME_ROWS);

        // create array of textures for animation loop(selected from above 2d array)
        TextureRegion[] moveFramesUP = new TextureRegion[]{
                frameSpriteSheet[0][2],
                frameSpriteSheet[2][1],
                frameSpriteSheet[2][0],
                frameSpriteSheet[2][1]
        };

        TextureRegion[] moveFramesDOWN = new TextureRegion[]{
                frameSpriteSheet[0][2],
                frameSpriteSheet[3][1],
                frameSpriteSheet[3][0],
                frameSpriteSheet[3][1]
        };

        TextureRegion[] moveFramesRIGHT = new TextureRegion[]{
                frameSpriteSheet[0][2],
                frameSpriteSheet[0][1],
                frameSpriteSheet[0][0],
                frameSpriteSheet[0][1]
        };

        TextureRegion[] moveFramesLEFT = new TextureRegion[]{
                frameSpriteSheet[0][2],
                frameSpriteSheet[1][1],
                frameSpriteSheet[1][0],
                frameSpriteSheet[1][1]
        };

        frame = 1;
        moveAnimation = new Animation[4];

        moveAnimation[0] = new Animation<TextureRegion>(ANIMATION_SPEED, moveFramesRIGHT);
        moveAnimation[1] = new Animation<TextureRegion>(ANIMATION_SPEED, moveFramesLEFT);
        moveAnimation[2] = new Animation<TextureRegion>(ANIMATION_SPEED, moveFramesUP);
        moveAnimation[3] = new Animation<TextureRegion>(ANIMATION_SPEED, moveFramesDOWN);
    }

    @Override
    public void render(SpriteBatch batch) {
        x += SPEED * Gdx.graphics.getDeltaTime() * CURRENT_DIRECTION.getxDirection();
        y += SPEED * Gdx.graphics.getDeltaTime() * CURRENT_DIRECTION.getyDirection();

        batch.begin();
        batch.draw(moveAnimation[frame].getKeyFrame(stateTime, animLoop), x, y, SIZE, SIZE);
        batch.end();
    }

    @Override
    public void update(float delta) {
        stateTime += delta;
    }

    @Override
    public void move() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            NEXT_DIRECTION = Direction.UP;
            frame = 2;
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            NEXT_DIRECTION = Direction.DOWN;
            frame = 3;
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            NEXT_DIRECTION = Direction.RIGHT;
            frame = 0;
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            NEXT_DIRECTION = Direction.LEFT;
            frame = 1;
        }
        CURRENT_DIRECTION = NEXT_DIRECTION;
    }

    private boolean checkChangeDirection() {

        return false;
    }
}
