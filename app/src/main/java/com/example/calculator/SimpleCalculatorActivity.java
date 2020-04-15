package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleCalculatorActivity extends AppCompatActivity {
    Button btnBksp, btnC, btnChangeSign, btnDivision, btnMultiply, btnMinus, btnPlus, btnEqual,btnDot,btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    TextView userResult;
    String operation="";
    double numberInMemory = 0;
    String decimalNumbers=".";//zmienić nazwę zmiennej; przypadek, gdy kropka jest ostatnim znakiem i chcę zrobić równość
    boolean isDecimalNumber = false;
    boolean secondNumberActive = false;
    boolean newNumber = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);

        userResult =findViewById(R.id.tv_userResult);
        userResult.setText("0");

        btnBksp = findViewById(R.id.btn_Bksp);
        btnBksp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(userResult.getText().length()>1){
                    if(!isDecimalNumber){
                        userResult.setText(userResult.getText().toString().substring(0, userResult.getText().toString().length()-1));
                    }else{
                        if(decimalNumbers.length()>1){
                            decimalNumbers = decimalNumbers.substring(0, decimalNumbers.length()-1);
                            userResult.setText(userResult.getText().toString().substring(0, userResult.getText().toString().length()-1));
                        }else{
                            isDecimalNumber = false;
                            userResult.setText(userResult.getText().toString().substring(0, userResult.getText().toString().length()-1));
                        }
                    }
                }else{
                    //userResult.setText("0");
                    deleteAllData();
                }
            }
        });

        btnC = findViewById(R.id.btn_Clear);
        btnC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                deleteAllData();
            }
        });

        btnChangeSign = findViewById(R.id.btn_ChangeSign);
        btnChangeSign.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {//To jest źle, nie chciało mi się już robić
                if(userResult.getText().toString().startsWith("-"))
                    userResult.setText(userResult.getText().toString().substring(1));
                else
                    userResult.setText("-".concat(userResult.getText().toString()));
            }
        });

        btnDivision = findViewById(R.id.btn_Division);
        btnDivision.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!secondNumberActive){
                    secondNumberActive = true;
                    numberInMemory = Double.parseDouble(userResult.getText().toString());
                }else{
                    calculateResult();//z tym calculateResult jest problem
                }
                operation = "/";
                decimalNumbers=".";
                isDecimalNumber=false;
                newNumber = true;
            }
        });

        btnMultiply = findViewById(R.id.btn_Multiply);
        btnMultiply.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!secondNumberActive){
                    secondNumberActive = true;
                    numberInMemory = Double.parseDouble(userResult.getText().toString());
                }else{
                    calculateResult();//z tym calculateResult jest problem
                }
                operation = "*";
                decimalNumbers=".";
                isDecimalNumber=false;
                newNumber = true;
            }
        });

        btnMinus = findViewById(R.id.btn_Minus);
        btnMinus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!secondNumberActive){
                    secondNumberActive = true;
                    numberInMemory = Double.parseDouble(userResult.getText().toString());
                }else{
                    calculateResult();//z tym calculateResult jest problem
                }
                operation = "-";
                decimalNumbers=".";
                isDecimalNumber=false;
                newNumber = true;
            }
        });

        btnPlus = findViewById(R.id.btn_Plus);//Zatrzymałem się na tym; przypadek, gdy plus kliknę od razu po odpaleniu programu!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        btnPlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!secondNumberActive){
                    secondNumberActive = true;
                    numberInMemory = Double.parseDouble(userResult.getText().toString());
                }else{
                    calculateResult();//z tym calculateResult jest problem
                }
                operation = "+";
                decimalNumbers=".";
                isDecimalNumber=false;
                newNumber = true;
            }
        });

        btnEqual = findViewById(R.id.btn_Equal);
        btnEqual.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                calculateResult();
                displayNumberInMemory();
                newNumber = true;//?
                secondNumberActive=false;
            }
        });

        btnDot = findViewById(R.id.btn_Dot);//gdy nie ma żadnej liczby i wcisnę kropkę zrobić 0.
        btnDot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(userResult.getText().length()<8&&!isDecimalNumber){//Długość musi być o jeden mniejsza
                    userResult.append(".");
                    isDecimalNumber = true;
                }
            }
        });

        btn0 = findViewById(R.id.btn_Zero);
        btn0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String number = "0";
                if(newNumber == true){
                    newNumber = false;
                    userResult.setText(number);
                }else{
                    if(userResult.getText().length()<9&&!userResult.getText().toString().equals("0")){
                        if(!isDecimalNumber){
                            userResult.append(number);
                        }else{
                            decimalNumbers = decimalNumbers.concat(number);
                            userResult.append(number);
                        }
                    }
                }
            }
        });

        btn1 = findViewById(R.id.btn_One);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String number = "1";
                if(newNumber == true){
                    newNumber = false;
                    userResult.setText(number);
                }else{
                    if(userResult.getText().toString().equals("0")){
                        userResult.setText(number);
                    }else if(userResult.getText().length()<9){
                        if(!isDecimalNumber){
                            userResult.append(number);
                        }else{
                            decimalNumbers = decimalNumbers.concat(number);
                            userResult.append(number);
                        }
                    }
                }
            }
        });

        btn2 = findViewById(R.id.btn_Two);
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String number = "2";
                if(newNumber == true){
                    newNumber = false;
                    userResult.setText(number);
                }else{
                    if(userResult.getText().toString().equals("0")){
                        userResult.setText(number);
                    }else if(userResult.getText().length()<9){
                        if(!isDecimalNumber){
                            userResult.append(number);
                        }else{
                            decimalNumbers = decimalNumbers.concat(number);
                            userResult.append(number);
                        }
                    }
                }
            }
        });

        btn3 = findViewById(R.id.btn_Three);
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String number = "3";
                if(newNumber == true){
                    newNumber = false;
                    userResult.setText(number);
                }else{
                    if(userResult.getText().toString().equals("0")){
                        userResult.setText(number);
                    }else if(userResult.getText().length()<9){
                        if(!isDecimalNumber){
                            userResult.append(number);
                        }else{
                            decimalNumbers = decimalNumbers.concat(number);
                            userResult.append(number);
                        }
                    }
                }
            }
        });

        btn4 = findViewById(R.id.btn_Four);
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String number = "4";
                if(newNumber == true){
                    newNumber = false;
                    userResult.setText(number);
                }else{
                    if(userResult.getText().toString().equals("0")){
                        userResult.setText(number);
                    }else if(userResult.getText().length()<9){
                        if(!isDecimalNumber){
                            userResult.append(number);
                        }else{
                            decimalNumbers = decimalNumbers.concat(number);
                            userResult.append(number);
                        }
                    }
                }
            }
        });

        btn5 = findViewById(R.id.btn_Five);
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String number = "5";
                if(newNumber == true){
                    newNumber = false;
                    userResult.setText(number);
                }else{
                    if(userResult.getText().toString().equals("0")){
                        userResult.setText(number);
                    }else if(userResult.getText().length()<9){
                        if(!isDecimalNumber){
                            userResult.append(number);
                        }else{
                            decimalNumbers = decimalNumbers.concat(number);
                            userResult.append(number);
                        }
                    }
                }
            }
        });

        btn6 = findViewById(R.id.btn_Six);
        btn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String number = "6";
                if(newNumber == true){
                    newNumber = false;
                    userResult.setText(number);
                }else{
                    if(userResult.getText().toString().equals("0")){
                        userResult.setText(number);
                    }else if(userResult.getText().length()<9){
                        if(!isDecimalNumber){
                            userResult.append(number);
                        }else{
                            decimalNumbers = decimalNumbers.concat(number);
                            userResult.append(number);
                        }
                    }
                }
            }
        });

        btn7 = findViewById(R.id.btn_Seven);
        btn7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String number = "7";
                if(newNumber == true){
                    newNumber = false;
                    userResult.setText(number);
                }else{
                    if(userResult.getText().toString().equals("0")){
                        userResult.setText(number);
                    }else if(userResult.getText().length()<9){
                        if(!isDecimalNumber){
                            userResult.append(number);
                        }else{
                            decimalNumbers = decimalNumbers.concat(number);
                            userResult.append(number);
                        }
                    }
                }
            }
        });

        btn8 = findViewById(R.id.btn_Eight);
        btn8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String number = "8";
                if(newNumber == true){
                    newNumber = false;
                    userResult.setText(number);
                }else{
                    if(userResult.getText().toString().equals("0")){
                        userResult.setText(number);
                    }else if(userResult.getText().length()<9){
                        if(!isDecimalNumber){
                            userResult.append(number);
                        }else{
                            decimalNumbers = decimalNumbers.concat(number);
                            userResult.append(number);
                        }
                    }
                }
            }
        });

        btn9 = findViewById(R.id.btn_Nine);
        btn9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String number = "9";
                if(newNumber == true){
                    newNumber = false;
                    userResult.setText(number);
                }else{
                    if(userResult.getText().toString().equals("0")){
                        userResult.setText(number);
                    }else if(userResult.getText().length()<9){
                        if(!isDecimalNumber){
                            userResult.append(number);
                        }else{
                            decimalNumbers = decimalNumbers.concat(number);
                            userResult.append(number);
                        }
                    }
                }
            }
        });
    }

    public void calculateResult(){
        if(secondNumberActive){
            String result=null;
            if(operation.equals("+")){
                numberInMemory = numberInMemory + Double.parseDouble(userResult.getText().toString());
                result = String.valueOf(numberInMemory);
                System.out.println(numberInMemory);
                //userResult.setText(result);
            }else if(operation.equals("-")){
                numberInMemory = numberInMemory - Double.parseDouble(userResult.getText().toString());
                result = String.valueOf(numberInMemory);
                //userResult.setText(result);
            }else if(operation.equals("*")){
                numberInMemory = numberInMemory * Double.parseDouble(userResult.getText().toString());
                result = String.valueOf(numberInMemory);
                //userResult.setText(result);
            }else if(operation.equals("/")){
                numberInMemory = numberInMemory / Double.parseDouble(userResult.getText().toString());
                result = String.valueOf(numberInMemory);
                //userResult.setText(result);
            }

            //Sprawdzenie warunku, czy wynik jest w ogóle liczbą dziesiętną
            if(result.contains(".")){
                decimalNumbers=result.substring(result.indexOf("."));
                isDecimalNumber = true;
            }else{
                decimalNumbers=".";
                isDecimalNumber=false;
            }
        }
    }

    public void displayNumberInMemory(){
        userResult.setText(String.valueOf(numberInMemory));//substring na wypadek wyniku np. 1.40000000000000001; złe rozwiązanie, co jak dam 999999999 * 2?
        /*Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
        if(String.valueOf(numberInMemory).indexOf(".")>=9){
            deleteAllData();
            Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
        }*/
        //zrobić coś z wy
    }

    public void deleteAllData(){
        userResult.setText("0");
        operation="";
        numberInMemory=0;
        decimalNumbers=".";
        isDecimalNumber=false;
        secondNumberActive=false;
        newNumber=false;
    }
}
