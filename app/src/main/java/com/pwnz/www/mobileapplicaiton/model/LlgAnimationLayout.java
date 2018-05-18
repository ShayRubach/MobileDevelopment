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

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class LlgAnimationLayout extends SurfaceView implements Runnable {

    private static ArrayList<NyanCat> cats = new ArrayList<>();
    public static final int MAX_CATS_ON_SCREEN = 10;
    private static final String RB_PREFIX = "nc_sprite1_rb_0";
    private static final int MIN_RB_PREFIX = 0;
    private static final int MAX_RB_POSTFIX = 5;
    private static final int MIN_FRAMES_PER_RANDOMIZAION = 10 ;
    private static int BIG_NYAN_CAT_STEP = 0;
    private static int BIG_NYAN_CAT_MAX_STEPS = 4;
    public static final int BIG_NYAN_CAT_MOVEMENT = 2;
    public static boolean isBigCatHit = false;



    private boolean canPlay = false;
    private boolean goingUp = true;

    private Thread mPlayThread = null;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Bitmap mTtpBitmap, bg;
    private Bitmap mBigNyanBitmap, mNyan1Bitmap, mNyan2Bitmap;
    private Bitmap mRainbow1, mRainbow2, gameTitle;

    public boolean isInMenuScreen = true;

    private int ttpYPos, ttpXPos;
    private int ttpWidth, ttpHeight;

    private int smallNyanYPos, smallNyanXPos;
    private int smallNyanWidth, smallNyanHeight;

    private static int nextInterval = 0;
    private int currRainbow;
    private int rainbowChoice;

    public final int ttpYDir = toPxs(1);
    public final int TTP_X = toPxs(100);
    public final int TTP_Y = toPxs(400);
    public final int MAX_MOVEMENT_EFFECT = toPxs(10);

    public final int NYAN_CATS_PADDING = 150;
    public final int sNyanYDir = 0;
    public final int sNyanXDir = 10;
    public final int SNYAN_X = toPxs(0);
    public final int SNYAN_Y = toPxs(180);

    public final int BNYAN_X = toPxs(80);
    public final int BNYAN_Y = toPxs(220);

    public int bigNyanPosX, bigNyanPosY;
    public int bigNyanWidth, bigNyanHeight;

    private int GAME_TITLE_X = toPxs(10);
    private int GAME_TITLE_Y = toPxs(10);


    public LlgAnimationLayout(Context context) {
        super(context);
        ttpXPos = TTP_X;
        ttpYPos = TTP_Y;

        smallNyanXPos = SNYAN_X;
        smallNyanYPos = SNYAN_Y;

        bigNyanPosX = BNYAN_X;
        bigNyanPosY = BNYAN_Y;

        surfaceHolder = getHolder();
        bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        currRainbow = R.drawable.nc_sprite1_rb_00;
        rainbowChoice = MIN_RB_PREFIX;
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

            if(isInMenuScreen){
                //todo: convert dpToPxl for x-platform responsive look
                drawPlayButton(canvas);
                drawFlyingNyanCat(canvas);
                drawGameTitle(canvas);
            }
            else {
                //todo: play logic goes here
                drawBigNyanCat(canvas);
                displayAllCats();

            }

            surfaceHolder.unlockCanvasAndPost(canvas);

        }
    }

    private void displayAllCats() {
        for(NyanCat cat : cats){
            cat.draw(canvas, bigNyanPosX, bigNyanPosY, bigNyanWidth, bigNyanHeight);
        }
    }

    private void drawBigNyanCat(Canvas canvas) {

        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.nyan_cat_big_glasses, option);
        bigNyanHeight = option.outHeight;
        bigNyanWidth = option.outWidth;

        if(isBigCatHit){
            mBigNyanBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nyan_cat_big_glasses_burned);
        }
        else
            mBigNyanBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nyan_cat_big_glasses);

        canvas.drawBitmap(mBigNyanBitmap, bigNyanPosX, bigNyanPosY, null);

        if(isBigCatHit)
            shakeBigCat();
    }

    public void shakeBigCat() {

        if((++BIG_NYAN_CAT_STEP) % BIG_NYAN_CAT_MAX_STEPS == 0){
            BIG_NYAN_CAT_STEP = 0;
            isBigCatHit = false;
            bigNyanPosX = BNYAN_X;
            bigNyanPosY = BNYAN_Y;

        }
        switch (BIG_NYAN_CAT_STEP){
            case 1:
                bigNyanPosX += BIG_NYAN_CAT_MOVEMENT;
                bigNyanPosY -= BIG_NYAN_CAT_MOVEMENT;
                break;
            case 2:
                bigNyanPosX -= BIG_NYAN_CAT_MOVEMENT;
                bigNyanPosY -= BIG_NYAN_CAT_MOVEMENT;
                break;
            case 3:
                bigNyanPosX -= BIG_NYAN_CAT_MOVEMENT;
                bigNyanPosY += BIG_NYAN_CAT_MOVEMENT;
                break;
            case 4:
                bigNyanPosX += BIG_NYAN_CAT_MOVEMENT;
                bigNyanPosY += BIG_NYAN_CAT_MOVEMENT;
                break;
            default:
                break;
        }
    }


    public void pause(){
        //Toast.makeText(getContext(),"PAUSE called",Toast.LENGTH_SHORT).show();
        canPlay = false;
        cats.clear();
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
        //Toast.makeText(getContext(),"RESUME called",Toast.LENGTH_SHORT).show();
        canPlay = true;
        mPlayThread = new Thread(this);
        mPlayThread.start();

    }


    public void addCatAtPos(int x, int y) {
        if(cats.size() < MAX_CATS_ON_SCREEN){
            //draw a cat
            cats.add(new NyanCat(BitmapFactory.decodeResource(getResources(), R.drawable.nyan_cat_left), x, y));
        }
        else {
            cats.clear();
        }
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
        canvas.drawBitmap(mNyan2Bitmap, canvas.getWidth() - smallNyanXPos - smallNyanWidth,  smallNyanYPos + NYAN_CATS_PADDING, null);


        canvas.drawBitmap(mRainbow1, smallNyanXPos - smallNyanWidth*2,  smallNyanYPos, null);
        canvas.drawBitmap(mRainbow2, canvas.getWidth() - smallNyanXPos + smallNyanWidth,  smallNyanYPos + NYAN_CATS_PADDING, null);
        smallNyanXPos += sNyanXDir;

    }

    private void drawPlayButton(Canvas canvas) {

        mTtpBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tap_to_play);
        canvas.drawBitmap(mTtpBitmap, canvas.getWidth() / 4,  ttpYPos, null);

        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.tap_to_play, option);
        ttpHeight = toPxs(option.outHeight);
        ttpWidth = toPxs(option.outWidth);

        if(ttpYPos <= TTP_Y - MAX_MOVEMENT_EFFECT){
            goingUp = false;
        }
        if(ttpYPos >= TTP_Y + MAX_MOVEMENT_EFFECT){
            goingUp = true;
        }
        if(goingUp){
            ttpYPos -= ttpYDir;
        }
        if(!goingUp){
            ttpYPos += ttpYDir;
        }
    }

    private void drawGameTitle(Canvas canvas){
        gameTitle = BitmapFactory.decodeResource(getResources(), R.drawable.arc_title);
        canvas.drawBitmap(gameTitle, canvas.getWidth() / 4,  GAME_TITLE_Y, null);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private int randRainbow() {

        if(++nextInterval == MIN_FRAMES_PER_RANDOMIZAION){
            nextInterval =0;
            rainbowChoice =  (rainbowChoice + 1 )% MAX_RB_POSTFIX;
            currRainbow = BirthdayListActivity.getDrawables(getContext(), RB_PREFIX + String.valueOf(rainbowChoice));


        }
        return currRainbow;
    }

    public boolean isValidPosition(int x, int y){

        boolean isValid = true;
        if(x > bigNyanPosX && x < bigNyanPosX + bigNyanWidth){
            //x is invalid
            isValid = false;
        }
        if(y > bigNyanPosY && y < bigNyanPosY + bigNyanHeight){
            //y is invalid
            isValid = false;
        }
        return isValid;
    }

    private Integer[] getValidPosisition() {
        Integer validPos[] = new Integer[2];

        //get random (x,y) coord
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            validPos[0] = ThreadLocalRandom.current().nextInt(0, canvas.getWidth());
            validPos[1] = ThreadLocalRandom.current().nextInt(0, canvas.getHeight());
        }
        return validPos;
    }

    private int toPxs(int dps){
        return (int)(dps* getResources().getDisplayMetrics().density);
    }

    public ArrayList<NyanCat> getCats() {
        return cats;
    }
}
