package com.pwnz.www.mobileapplicaiton.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.pwnz.www.mobileapplicaiton.BirthdayListActivity;
import com.pwnz.www.mobileapplicaiton.R;

import java.util.concurrent.ThreadLocalRandom;

public class LlgAnimationLayout extends SurfaceView implements Runnable {

    private static final String RB_PREFIX = "nc_sprite1_rb_0";
    public static final int MIN_RB_POSTFIX = 0;
    public static final int MAX_RB_POSTFIX = 5;
    private static final int MIN_FRAMES_PER_RANDOMIZAION = 20 ;


    private boolean canPlay = false;
    private Thread mPlayThread = null;
    private Bitmap mTtpBitmap, mNyan1Bitmap, mNyan2Bitmap, mRainbow1, mRainbow2, gameTitle, bg;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private boolean goingUp = true;

    public boolean isInMenuScreen = true;

    private int ttpYPos, ttpXPos;
    private int ttpWidth, ttpHeight;



    private int smallNyanYPos, smallNyanXPos;
    private int smallNyanWidth, smallNyanHeight;



    private static int randInterval = 0;
    private int currRainbow;

    public static int ttpYDir = 1;
    public static final int TTP_X = 280;
    public static final int TTP_Y = 780;
    public static final int MAX_MOVEMENT_EFFECT = -40;

    public static int sNyanYDir = 0;
    public static int sNyanXDir = 10;
    public static final int SNYAN_X = 0;
    public static final int SNYAN_Y = 400;
    private int GAME_TITLE_X = 10;
    private int GAME_TITLE_Y = 30;


    public LlgAnimationLayout(Context context) {
        super(context);
        ttpXPos = TTP_X;
        ttpYPos = TTP_Y;
        smallNyanXPos = SNYAN_X;
        smallNyanYPos = SNYAN_Y;
        surfaceHolder = getHolder();
        bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        currRainbow = R.drawable.nc_sprite1_rb_00;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
                drawGameTitle(canvas);
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



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawFlyingNyanCat(Canvas canvas) {

        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.nyan_cat_right, option);
        smallNyanHeight = option.outHeight;
        smallNyanWidth = option.outWidth;

        mNyan1Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nyan_cat_right);
        mNyan2Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nyan_cat_left);
        mRainbow1 = BitmapFactory.decodeResource(getResources(), randRainbow());
        mRainbow2 = BitmapFactory.decodeResource(getResources(), randRainbow());


        if(smallNyanXPos == canvas.getWidth() ) {
            smallNyanXPos = 0;
        }
        canvas.drawBitmap(mNyan1Bitmap, smallNyanXPos,  smallNyanYPos, null);
        canvas.drawBitmap(mNyan2Bitmap, canvas.getWidth() - smallNyanXPos - smallNyanWidth,  smallNyanYPos + 100, null);


        canvas.drawBitmap(mRainbow1, smallNyanXPos - smallNyanWidth*2,  smallNyanYPos, null);
        canvas.drawBitmap(mRainbow2, canvas.getWidth() - smallNyanXPos + smallNyanWidth,  smallNyanYPos + 100, null);
        smallNyanXPos += sNyanXDir;

    }

    private void drawPlayButton(Canvas canvas) {

        mTtpBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tap_to_play);
        canvas.drawBitmap(mTtpBitmap, ttpXPos,  ttpYPos, null);

        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.tap_to_play, option);
        ttpHeight = option.outHeight;
        ttpWidth = option.outWidth;

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

    private void drawGameTitle(Canvas canvas){
        gameTitle = BitmapFactory.decodeResource(getResources(), R.drawable.arc_title);
        canvas.drawBitmap(gameTitle, canvas.getWidth() / 4,  GAME_TITLE_Y, null);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private int randRainbow() {

        if(++randInterval == MIN_FRAMES_PER_RANDOMIZAION){
            randInterval=0;
            int randomAvatar = ThreadLocalRandom.current().nextInt(MIN_RB_POSTFIX, MAX_RB_POSTFIX);
            currRainbow = BirthdayListActivity.getDrawables(getContext(), RB_PREFIX + String.valueOf(randomAvatar));

        }
        return currRainbow;
    }


}
