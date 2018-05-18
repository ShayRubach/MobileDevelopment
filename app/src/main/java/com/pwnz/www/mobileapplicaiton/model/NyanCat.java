package com.pwnz.www.mobileapplicaiton.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class NyanCat {
    private Bitmap bmapImg;
    public int x,y;
    public int xDir, yDir;
    public int width, height;
    public static final int MOVEMENT_SPEED = 8;
    public static final int SAFE_OFFSET = 2;


    public NyanCat(Bitmap bmapImg) {
        this.bmapImg = bmapImg;
        xDir = 1;
        yDir = 1;
    }

    public NyanCat(Bitmap bmapImg, int x, int y) {
        this.bmapImg = bmapImg;
        this.x = x;
        this.y = y;
        xDir = 1;
        yDir = 1;
    }

    public void move(Canvas canvas, int obstaclePosX, int obstaclePosY, int obstacleWidth, int obstacleHeight){
        //swap directions
        if(isInRangeOf(x,y,obstaclePosX,obstaclePosY,obstacleWidth,obstacleHeight)){
            xDir *= (-1);
            yDir *= (-1);
            LlgAnimationLayout.isBigCatHit = true;
        }
        if(x >= canvas.getWidth() || x <= 0 ){
            xDir *= (-1);
        }
        if(y >= canvas.getHeight() || y <= 0 ){
            yDir *= (-1);
        }

        //if we hit the bit cat, perform a larger step back so it wont stuck inside
        if(LlgAnimationLayout.isBigCatHit){
            y += (yDir * MOVEMENT_SPEED);
            x += (xDir * MOVEMENT_SPEED);
        }
        else{
            y += (yDir * MOVEMENT_SPEED);
            x += (xDir * MOVEMENT_SPEED);
        }

    }

    private boolean isInRangeOf(int x, int y, int obstaclePosX, int obstaclePosY, int obstacleWidth, int obstacleHeight) {
        if(x >= obstaclePosX && y >= obstaclePosY && x <= obstaclePosX+obstacleWidth*2 && y <= obstaclePosY+obstacleHeight*2 ){
            return true;
        }
        return false;
    }

    public Bitmap getBmapImg() {
        return bmapImg;
    }

    public void draw(Canvas canvas, int obstaclePosX, int obstaclePosY, int obstacleWidth, int obstacleHeight) {
        move(canvas, obstaclePosX, obstaclePosY, obstacleWidth, obstacleHeight);
        canvas.drawBitmap(bmapImg, x, y,null);
    }
}
