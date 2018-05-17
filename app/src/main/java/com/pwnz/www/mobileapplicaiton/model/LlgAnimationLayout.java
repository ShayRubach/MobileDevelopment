package com.pwnz.www.mobileapplicaiton.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.pwnz.www.mobileapplicaiton.R;

public class LlgAnimationLayout extends SurfaceView implements Runnable {

    private boolean canPlay = false;
    private Thread mPlayThread = null;
    private Bitmap mTtpBitmap, mNyanBitmap, bg;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private boolean goingUp = true;
    public boolean isInMenuScreen = true;

    private int ttpYPos, ttpXPos;
    private int ttpWidth, ttpHeight;

    private int smallNyanYPos, smallNyanXPos;
    private int mainsmall, smallNyanHeight;

    public static int ttpYDir = 1;
    public static final int TTP_X = 280;
    public static final int TTP_Y = 700;
    public static final int MAX_MOVEMENT_EFFECT = -40;

    public static int sNyanYDir = 0;
    public static int sNyanXDir = 1;
    public static final int SNYAN_X = 100;
    public static final int SNYAN_Y = 100;



    public LlgAnimationLayout(Context context) {
        super(context);
        ttpXPos = TTP_X;
        ttpYPos = TTP_Y;
        surfaceHolder = getHolder();
        bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    @Override
    public void run() {

        while(canPlay) {

            if(!surfaceHolder.getSurface().isValid()){
                continue;
            }


            canvas = surfaceHolder.lockCanvas();
            canvas.drawBitmap(bg,0,0,null);



            //todo: play logic goes here
            if(isInMenuScreen){
                drawPlayButton(canvas);
                drawFlyingNyanCat(canvas);
            }
            else {
                mTtpBitmap = null;

            }

            surfaceHolder.unlockCanvasAndPost(canvas);

        }
    }

    public void pause(){
        Toast.makeText(getContext(),"PAUSE called",Toast.LENGTH_SHORT).show();
        canPlay = false;

        while (true) {
            try {
                mPlayThread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //reset the thread
        mPlayThread = null;
    }

    public void resume() {
        Toast.makeText(getContext(),"RESUME called",Toast.LENGTH_SHORT).show();
        canPlay = true;
        mPlayThread = new Thread(this);
        mPlayThread.start();

    }



    private void drawFlyingNyanCat(Canvas canvas) {
        mNyanBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nyan_cat);
        canvas.drawBitmap(mNyanBitmap, 100,  100, null);

        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.nyan_cat, option);
        smallNyanHeight = option.outHeight;
        mainsmall = option.outWidth;

//        if(ttpYPos <= TTP_Y + MAX_MOVEMENT_EFFECT + ttpHeight)
        if(ttpYPos == TTP_Y - MAX_MOVEMENT_EFFECT - ttpHeight){
            goingUp = false;
        }
        if(ttpYPos == TTP_Y + MAX_MOVEMENT_EFFECT + ttpHeight){
            goingUp = true;
        }
        if(goingUp && ttpYPos > TTP_Y - MAX_MOVEMENT_EFFECT - ttpHeight){
            ttpYPos -= ttpYDir;
        }
        if(!goingUp && ttpYPos < TTP_Y + MAX_MOVEMENT_EFFECT + ttpHeight ){
            ttpYPos += ttpYDir;
        }

    }

    private void drawPlayButton(Canvas canvas) {

        mTtpBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tap_to_play);
        canvas.drawBitmap(mTtpBitmap, ttpXPos,  ttpYPos, null);

        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.tap_to_play, option);
        ttpHeight = option.outHeight;
        ttpWidth = option.outWidth;

//        if(ttpYPos <= TTP_Y + MAX_MOVEMENT_EFFECT + ttpHeight)
        if(ttpYPos == TTP_Y - MAX_MOVEMENT_EFFECT - ttpHeight){
            goingUp = false;
        }
        if(ttpYPos == TTP_Y + MAX_MOVEMENT_EFFECT + ttpHeight){
            goingUp = true;
        }
        if(goingUp && ttpYPos > TTP_Y - MAX_MOVEMENT_EFFECT - ttpHeight){
            ttpYPos -= ttpYDir;
        }
        if(!goingUp && ttpYPos < TTP_Y + MAX_MOVEMENT_EFFECT + ttpHeight ){
            ttpYPos += ttpYDir;
        }
    }



}
