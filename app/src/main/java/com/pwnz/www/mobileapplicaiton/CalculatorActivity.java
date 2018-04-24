package com.pwnz.www.mobileapplicaiton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.pwnz.www.mobileapplicaiton.MathOperation.*;


public class CalculatorActivity extends Activity implements View.OnClickListener {

    public static Double INIT_VAL = 0.0;
    public static Double DEFAULT_VAL = 0.0;

    private TextView result,opDisplay ;
    private MathOperation operation;

    Button btnAdd, btnSub, btnDiv, btnMult, btnEq, btnAc;
    Double numA = INIT_VAL, numB = INIT_VAL ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate: called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        init();
    }

    @Override
    public void onClick(View view) {
        System.out.println("onClick: called");
        if(isOperator(view) == true){
            switch(view.getId()){
                case R.id.operEq:
                    result.setText(calc(operation).toString());
                    opDisplay.setText("=");
                    setOperation(EQAULS);
                    break;
                case R.id.operAc:
                    resetNums();
                    result.setText("0");
                    opDisplay.setText("");
                    setOperation(EQAULS);
                    break;
                case R.id.operPlus:
                    setOperation(ADD);
                    opDisplay.setText("+");
                    break;
                case R.id.operMin:
                    setOperation(SUB);
                    opDisplay.setText("-");
                    break;
                case R.id.operMult:
                    setOperation(MULT);
                    opDisplay.setText("*");
                    break;
                case R.id.operDiv:
                    opDisplay.setText("/");
                    setOperation(DIV);
                    break;
            }
        }
        else {
            Button btn = (Button)view;
            Double pressedVal = Double.parseDouble(btn.getText().toString());

            if(getOperation() == EQAULS) {
                numA = (numA * 10) + pressedVal;
                result.setText(numA.toString());
            }
            else {
                numB = (numB * 10) + pressedVal;
                result.setText(numB.toString());
            }

        }
    }

    private Double calc(MathOperation op) {
        switch (op){
            case ADD:
                return numA+numB;
            case SUB:
                return numA-numB;
            case MULT:
                return numA*numB;
            case DIV:
                if(numB != 0)
                    return numA/numB;
        }

        return DEFAULT_VAL;
    }

    private void resetNums() {
        numA = INIT_VAL;
        numB = INIT_VAL;
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
        setOperation(EQAULS);
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

    public void setOperation(MathOperation operation) {
        this.operation = operation;
    }

    public MathOperation getOperation() {
        return operation;
    }
}
