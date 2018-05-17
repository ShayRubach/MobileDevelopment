package com.pwnz.www.mobileapplicaiton.model;

import android.content.Context;
import android.view.View;

import com.pwnz.www.mobileapplicaiton.R;

public class LlgAnimationLayout extends View implements Runnable {

    private boolean canPlay = false;
    private Thread mPlayThread = null;

    public LlgAnimationLayout(Context context) {
        super(context);
        setBackgroundResource(R.drawable.llg_bg);
    }

    @Override
    public void run() {

        while(canPlay) {
            //todo: play logic goes here
        }
    }

    public void pause(){
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
        canPlay = true;
        mPlayThread = new Thread(this);
        mPlayThread.start();

    }


}
