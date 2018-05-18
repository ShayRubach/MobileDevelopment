package com.pwnz.www.mobileapplicaiton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.pwnz.www.mobileapplicaiton.model.LlgAnimationLayout;

public class LlgAnimationActivity extends AppCompatActivity {

    private LlgAnimationLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLayout = new LlgAnimationLayout(this);
        setContentView(mLayout);

        //todo: moving from side to side
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //ignore double actions (UP & DOWN)
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                handleClickEvent(event);
                break;
            default:
                break;
        }

        return super.onTouchEvent(event);
    }

    private void handleClickEvent(MotionEvent event) {
        if(mLayout.isInMenuScreen){
            //Toast.makeText(this,"Let's Go!",Toast.LENGTH_LONG).show();
            mLayout.isInMenuScreen = false;
        }
        else {
            //listen to game clicks.
            //Toast.makeText(this,"listening to game clicks.",Toast.LENGTH_LONG).show();

            if(mLayout.isValidPosition((int)event.getRawX(), (int)event.getRawY())) {
                mLayout.addCatAtPos((int)event.getRawX(), (int)event.getRawY());
            }
            else {
                if(mLayout.getCats().size() == LlgAnimationLayout.MAX_CATS_ON_SCREEN){
                    Toast.makeText(this,"No more cats for you!",Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(this,"Please stop spawning cats on me.",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLayout.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLayout.resume();
    }

}
