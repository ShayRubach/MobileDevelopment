package com.pwnz.www.mobileapplicaiton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pwnz.www.mobileapplicaiton.model.LlgAnimationLayout;

public class LlgAnimationActivity extends AppCompatActivity {

    LlgAnimationLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLayout = new LlgAnimationLayout(this);
        setContentView(mLayout);
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
