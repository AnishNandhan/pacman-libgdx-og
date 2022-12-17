package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Entities.Pacman;
import com.mygdx.game.Enums.Direction;
import com.mygdx.game.Enums.TileType;
import com.mygdx.game.GameMap;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.TiledGameMap;

public class GameScreen implements Screen {
    GameMap gameMap;
    public static final int SIZE_PIXEL = 16;
    public static final int SIZE = SIZE_PIXEL * 3;
    OrthographicCamera camera;
    Texture texture;
    int frame;
    boolean animLoop;
    float x;
    float y;

    MyGdxGame game;
    Pacman hero;

    public GameScreen(MyGdxGame game) {
        this.game = game;
        y = 200;
        x = MyGdxGame.WIDTH / 2 - SIZE / 2;

        gameMap = new TiledGameMap();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 28, 36);
//        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        hero = new Pacman(Gdx.graphics.getWidth() / 2 - SIZE / 2, 200, 200, Direction.LEFT);

    }

    @Override
    public void show() {
        texture = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        // rendering map
        gameMap.render(camera);

        animLoop = true;

        // Border checking
        if(x >= Gdx.graphics.getWidth() - SIZE) {
            x = Gdx.graphics.getWidth() - SIZE;
            animLoop = false;
        }
        if(x <= 0) {
            x = 0;
            animLoop = false;
        }
        if(y >= Gdx.graphics.getHeight() - SIZE) {
            y = Gdx.graphics.getHeight() - SIZE;
            animLoop = false;
        }
        if(y <= 0) {
            y = 0;
            animLoop = false;
        }

        if(Gdx.input.isTouched()) {
            Vector3 pos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            TileType type = gameMap.getTileTypeByLocation(0, pos.x, pos.y);

            if(type != null) {
                System.out.println("Tile: " + type.getName());
            }
        }

        hero.render(game.batch);
        hero.update(Gdx.graphics.getDeltaTime());
        hero.move();



        // render code
        game.batch.begin();

//        game.batch.draw(moveAnimation[frame].getKeyFrame(stateTime, animLoop), x, y, SIZE, SIZE);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
