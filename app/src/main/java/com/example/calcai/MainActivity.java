package com.example.calcai;

import androidx.appcompat.app.AppCompatActivity;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result, solution;

    MaterialButton C, lbracket, rbracket;
    MaterialButton div, multiply, add, subtract, answer;
    MaterialButton one, two, three, four, five, six, seven, nane, nine;
    MaterialButton ac, point;
    MaterialButton sin, cos, tan, log;

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalresult = context.evaluateString(scriptable, data, "javascript", 1, null).toString();
            if (finalresult.endsWith(".0")) {
                finalresult = finalresult.replace(".0", "");
            }
            return finalresult;
        } catch (Exception e) {
            return ("err");
        }
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button cam;
        cam=(Button)findViewById(R.id.camm) ;
        cam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, Camera.class);
                startActivity(intent);
            }

        });
        result = findViewById(R.id.result);
        solution = findViewById(R.id.solution);
        assignId(C, R.id.C);
        assignId(one, R.id.one);
        assignId(two, R.id.two);
        assignId(three, R.id.three);
        assignId(four, R.id.four);
        assignId(five, R.id.five);
        assignId(six, R.id.six);
        assignId(seven, R.id.seven);
        assignId(nane, R.id.nane);
        assignId(lbracket, R.id.lbracket);
        assignId(rbracket, R.id.rbracket);
        assignId(ac, R.id.ac);
        assignId(subtract, R.id.C);
        assignId(add, R.id.add);
        assignId(multiply, R.id.multiply);
        assignId(div, R.id.div);
        assignId(point, R.id.point);
        cam = (Button) findViewById(R.id.camm);
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Camera.class);
                startActivity(intent);
            }
        });

        tan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                calculateTrigonometricFunction("tan");
            }

        });
        sin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                calculateTrigonometricFunction("sin");
            }

        });
        cos.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                calculateTrigonometricFunction("cos");
            }

        });


    }

    private void calculateTrigonometricFunction(String tan) {

    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);

    }

    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        //Delete button
        String buttonText = button.getText().toString();
        if (!buttonText.isEmpty()) {
            String newText = buttonText.substring(0, buttonText.length() - 1);

        }
        //end of del buttons
        String dataToCalculate = solution.getText().toString();
        if (buttonText.equals("AC")) {
            solution.setText("");
            result.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solution.setText(result.getText());
            return;

        }
        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        solution.setText(dataToCalculate);


        solution.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("error")) {
            result.setText(finalResult);
        }


    }
 /* sin.setOnClickListener(new View.OnClickListener({
        public void onClick(View view){
            input.append("1");
            display.setText(input.toString());
        }

    });*/






}

