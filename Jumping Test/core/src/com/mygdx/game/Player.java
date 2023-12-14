package com.mygdx.game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {
    private int w,h;
    private int x,y;

    private Color col;
    private int playerYvelocity = 0;

    public Player(int x, int y, int w, int h,Color c){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;

        col=c;
    }
    public void draw(ShapeRenderer sr){
        sr.setColor(col);
        sr.rect(x,y,w,h);
    }
    public float getX() {
        return x;
    }

    public void setX(int x) {

            this.x = x;

    }

    public int getY() {
        return y;
    }

    public void setY(int y) {

            this.y = y;

    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Color getCol() {
        return col;
    }

    public void setCol(Color col) {
        this.col = col;
    }
    public float getplayerYvelocity() {
        return playerYvelocity;
    }
    public void setPlayerYvelocity(int playerYvelocity) {
        this.playerYvelocity = playerYvelocity;
    }
}
