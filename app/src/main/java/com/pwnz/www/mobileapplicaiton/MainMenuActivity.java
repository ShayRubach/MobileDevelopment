package com.pwnz.www.mobileapplicaiton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainMenuActivity extends Activity implements View.OnClickListener {

    private TextView result,opDisplay ;
    private MathOperation operation;
    Button btnAdd, btnSub, btnDiv, btnMult, btnEq, btnAc;
    Double numA = null, numB = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate: called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        init();
    }

    @Override
    public void onClick(View view) {
        System.out.println("onClick: called");
        if(isOperator(view) == true){
            switch(view.getId()){
                case R.id.operEq:
                    result.setText(calc(operation).toString());

                    break;
                case R.id.operAc:
                    resetNums();
                    result.setText("0");
                    opDisplay.setText("");

                    break;
                case R.id.operPlus:
                    operation = MathOperation.ADD;
                    opDisplay.setText("+");
                    break;
                case R.id.operMin:
                    operation = MathOperation.SUB;
                    opDisplay.setText("-");
                    break;
                case R.id.operMult:
                    operation = MathOperation.MULT;
                    opDisplay.setText("*");
                    break;
                case R.id.operDiv:
                    opDisplay.setText("/");
                    operation = MathOperation.DIV;
                    break;
            }
        }
        else {
            Button btn = (Button)view;
            Double pressedVal = Double.parseDouble(btn.getText().toString());
            System.out.println("pressed: " + pressedVal);
            result.setText(pressedVal.toString());

            if(numA == null)
                numA = pressedVal;
            else
                numB = pressedVal;

        }
    }

    private Double calc(MathOperation op) {
        switch (op){
            case ADD:
                return numA+numB;
        }

        return 0.0;
    }

    private void resetNums() {
        numA = null;
        numB = null;
    }

    private boolean isOperator(View view) {
        if(view.getId() == R.id.operAc ||
           view.getId() == R.id.operDiv ||
           view.getId() == R.id.operEq ||
           view.getId() == R.id.operMin ||
           view.getId() == R.id.operPlus ||
           view.getId() == R.id.operMult) {
            return true;
        }
        return false;
    }


    private void init() {
        System.out.println("init: called");
        bindBtnsToId();
        setBtnsListeners();
    }


    private void bindBtnsToId() {
        result = findViewById(R.id.resultView);
        opDisplay = findViewById(R.id.opView);

        btnAdd = findViewById(R.id.operPlus);
        btnSub = findViewById(R.id.operMin);
        btnDiv = findViewById(R.id.operDiv);
        btnMult = findViewById(R.id.operMult);
        btnEq = findViewById(R.id.operEq);
        btnAc = findViewById(R.id.operAc);
    }

    private void setBtnsListeners() {

        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnEq.setOnClickListener(this);
        btnAc.setOnClickListener(this);


        for (int i = 0; i <= 9; i++) {
            int id = getResources().getIdentifier("digit"+i, "id", getPackageName());
            findViewById(id).setOnClickListener(this);
        }

    }
}
