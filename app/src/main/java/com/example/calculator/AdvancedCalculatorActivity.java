package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdvancedCalculatorActivity extends AppCompatActivity {
    Button btnBksp, btnC, btnChangeSign, btnDivision, btnMultiply, btnMinus, btnPlus, btnEqual,btnDot,btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    Button btnSin, btnCos, btnTan, btnLn, btnSqrt, btnSquare, btnPower, btnLog;
    TextView displayNumber;
    String operation="";//przechowuje informację o rodzaju działania
    double numberInMemory = 0;//liczba w pamięci
    String fractionalPart =".";//część po przecinku liczby; string jest pomocniczy przy wykonywaniu działań na liczbach z cyframi po przecinku
    boolean hasFractionalPart = false;//informacja o tym, czy aktualnie wprowadzana liczba ma cyfry po przecinku
    boolean numberInMemoryActive = false;//informacja o tym, czy w pamięci jest zapisana jakaś liczba
    boolean newNumber = false;//informacja o tym, czy aktualnie jest wprowadzana nowa liczba, czy cały czas ta sama (ważne przy wpisywaniu nowej liczby po wyświetleniu wyniku)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calculator);
        displayNumber =findViewById(R.id.display_number);
        displayNumber.setText("0");

        btnBksp = findViewById(R.id.btn_Bksp);
        btnBksp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(displayNumber.getText().length()>1){
                    if(!hasFractionalPart){
                        displayNumber.setText(displayNumber.getText().toString().substring(0, displayNumber.getText().toString().length()-1));
                    }else{
                        if(fractionalPart.length()>1){
                            fractionalPart = fractionalPart.substring(0, fractionalPart.length()-1);
                            displayNumber.setText(displayNumber.getText().toString().substring(0, displayNumber.getText().toString().length()-1));
                        }else{
                            hasFractionalPart = false;
                            displayNumber.setText(displayNumber.getText().toString().substring(0, displayNumber.getText().toString().length()-1));
                        }
                    }
                }else{
                    deleteAllData();
                }
            }
        });

        btnC = findViewById(R.id.btn_Clear);
        btnC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                displayNumber.setText("0");
                fractionalPart=".";
                hasFractionalPart = false;
            }
        });

        btnC = findViewById(R.id.btn_AllClear);
        btnC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                deleteAllData();
            }
        });

        btnChangeSign = findViewById(R.id.btn_ChangeSign);
        btnChangeSign.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(displayNumber.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }
                if(Double.parseDouble(displayNumber.getText().toString())==0.0)
                    return;

                if(displayNumber.getText().toString().startsWith("-"))
                    displayNumber.setText(displayNumber.getText().toString().substring(1));
                else
                    displayNumber.setText("-".concat(displayNumber.getText().toString()));
            }
        });

        btnDivision = findViewById(R.id.btn_Division);
        btnDivision.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(displayNumber.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }

                numberInMemoryActive = true;
                calculateResult();
                operation = "/";
                fractionalPart =".";
                hasFractionalPart =false;
                newNumber = true;
            }
        });

        btnMultiply = findViewById(R.id.btn_Multiply);
        btnMultiply.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(displayNumber.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }

                numberInMemoryActive = true;
                calculateResult();
                operation = "*";
                fractionalPart =".";
                hasFractionalPart =false;
                newNumber = true;
            }
        });

        btnMinus = findViewById(R.id.btn_Minus);
        btnMinus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(displayNumber.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }

                numberInMemoryActive = true;
                calculateResult();
                operation = "-";
                fractionalPart =".";
                hasFractionalPart =false;
                newNumber = true;
            }
        });

        btnPlus = findViewById(R.id.btn_Plus);
        btnPlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(displayNumber.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }

                numberInMemoryActive = true;
                calculateResult();
                operation = "+";
                fractionalPart =".";
                hasFractionalPart =false;
                newNumber = true;
            }
        });

        btnEqual = findViewById(R.id.btn_Equal);
        btnEqual.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(displayNumber.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }

                calculateResult();
                displayNumberInMemory();
                operation="";
                newNumber = true;
                numberInMemoryActive =false;
            }
        });

        btnDot = findViewById(R.id.btn_Dot);
        btnDot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(displayNumber.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }

                if(displayNumber.getText().length()<8&&!hasFractionalPart&&!newNumber){
                    displayNumber.append(".");
                    hasFractionalPart = true;
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
                    displayNumber.setText(number);
                }else{
                    if(displayNumber.getText().length()<9&&!displayNumber.getText().toString().equals("0")){
                        if(!hasFractionalPart){
                            displayNumber.append(number);
                        }else{
                            fractionalPart = fractionalPart.concat(number);
                            displayNumber.append(number);
                        }
                    }
                }
            }
        });

        btn1 = findViewById(R.id.btn_One);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                displayNewDigit("1");
            }
        });

        btn2 = findViewById(R.id.btn_Two);
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                displayNewDigit("2");
            }
        });

        btn3 = findViewById(R.id.btn_Three);
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                displayNewDigit("3");
            }
        });

        btn4 = findViewById(R.id.btn_Four);
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                displayNewDigit("4");
            }
        });

        btn5 = findViewById(R.id.btn_Five);
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                displayNewDigit("5");
            }
        });

        btn6 = findViewById(R.id.btn_Six);
        btn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                displayNewDigit("6");
            }
        });

        btn7 = findViewById(R.id.btn_Seven);
        btn7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                displayNewDigit("7");
            }
        });

        btn8 = findViewById(R.id.btn_Eight);
        btn8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                displayNewDigit("8");
            }
        });

        btn9 = findViewById(R.id.btn_Nine);
        btn9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                displayNewDigit("9");
            }
        });

        btnSin = findViewById(R.id.btn_Sin);
        btnSin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayNumber.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }
                operation = "";
                numberInMemory = Math.sin(Double.parseDouble(displayNumber.getText().toString()));

            if(String.valueOf(numberInMemory).contains(".")){
                fractionalPart =String.valueOf(numberInMemory).substring(String.valueOf(numberInMemory).indexOf("."));
                hasFractionalPart = true;
            }else{
                fractionalPart =".";
                hasFractionalPart =false;
            }

                displayNumberInMemory();
                newNumber = true;
                numberInMemoryActive =false;
            }
        });

        btnCos = findViewById(R.id.btn_Cos);
        btnCos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayNumber.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }
                operation = "";
                numberInMemory = Math.cos(Double.parseDouble(displayNumber.getText().toString()));

                if(String.valueOf(numberInMemory).contains(".")){
                    fractionalPart =String.valueOf(numberInMemory).substring(String.valueOf(numberInMemory).indexOf("."));
                    hasFractionalPart = true;
                }else{
                    fractionalPart =".";
                    hasFractionalPart =false;
                }

                displayNumberInMemory();
                newNumber = true;
                numberInMemoryActive =false;
            }
        });

        btnTan = findViewById(R.id.btn_Tan);
        btnTan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayNumber.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }
                operation = "";
                numberInMemory = Math.tan(Double.parseDouble(displayNumber.getText().toString()));

                if(String.valueOf(numberInMemory).contains(".")){
                    fractionalPart =String.valueOf(numberInMemory).substring(String.valueOf(numberInMemory).indexOf("."));
                    hasFractionalPart = true;
                }else{
                    fractionalPart =".";
                    hasFractionalPart =false;
                }

                displayNumberInMemory();
                newNumber = true;
                numberInMemoryActive =false;
            }
        });

        btnLn = findViewById(R.id.btn_Ln);
        btnLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayNumber.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }
                operation = "";
                numberInMemory = Math.log(Double.parseDouble(displayNumber.getText().toString()));
                if(String.valueOf(numberInMemory).equals("NaN")){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }

                if(String.valueOf(numberInMemory).contains(".")){
                    fractionalPart =String.valueOf(numberInMemory).substring(String.valueOf(numberInMemory).indexOf("."));
                    hasFractionalPart = true;
                }else{
                    fractionalPart =".";
                    hasFractionalPart =false;
                }

                displayNumberInMemory();
                newNumber = true;
                numberInMemoryActive =false;
            }
        });

        btnSqrt = findViewById(R.id.btn_Sqrt);
        btnSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayNumber.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }
                operation = "";

                if(Double.parseDouble(displayNumber.getText().toString())<0){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }
                numberInMemory = Math.sqrt(Double.parseDouble(displayNumber.getText().toString()));

                if(String.valueOf(numberInMemory).contains(".")){
                    fractionalPart =String.valueOf(numberInMemory).substring(String.valueOf(numberInMemory).indexOf("."));
                    hasFractionalPart = true;
                }else{
                    fractionalPart =".";
                    hasFractionalPart =false;
                }

                displayNumberInMemory();
                newNumber = true;
                numberInMemoryActive =false;
            }
        });

        btnSquare = findViewById(R.id.btn_Square);
        btnSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayNumber.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }
                operation = "";
                numberInMemory = Double.parseDouble(displayNumber.getText().toString())*Double.parseDouble(displayNumber.getText().toString());

                if(String.valueOf(numberInMemory).contains(".")){
                    fractionalPart =String.valueOf(numberInMemory).substring(String.valueOf(numberInMemory).indexOf("."));
                    hasFractionalPart = true;
                }else{
                    fractionalPart =".";
                    hasFractionalPart =false;
                }

                displayNumberInMemory();
                newNumber = true;
                numberInMemoryActive =false;
            }
        });

        btnPower = findViewById(R.id.btn_Power);
        btnPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayNumber.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }

                numberInMemoryActive = true;
                calculateResult();
                operation = "^";
                fractionalPart =".";
                hasFractionalPart =false;
                newNumber = true;
            }
        });

        btnLog = findViewById(R.id.btn_Log);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayNumber.getText().toString().equals("-")){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }
                operation = "";
                numberInMemory = Math.log10(Double.parseDouble(displayNumber.getText().toString()));

                if(String.valueOf(numberInMemory).equals("NaN")){
                    Toast.makeText(getApplicationContext(),"Error: Invalid number", Toast.LENGTH_SHORT).show();
                    deleteAllData();
                    return;
                }

                if(String.valueOf(numberInMemory).contains(".")){
                    fractionalPart =String.valueOf(numberInMemory).substring(String.valueOf(numberInMemory).indexOf("."));
                    hasFractionalPart = true;
                }else{
                    fractionalPart =".";
                    hasFractionalPart =false;
                }

                displayNumberInMemory();
                newNumber = true;
                numberInMemoryActive =false;
            }
        });

        if(savedInstanceState != null){
            String numberOnScreen = savedInstanceState.getString("numberOnScreen");
            displayNumber.setText(numberOnScreen);
            operation = savedInstanceState.getString("operation");
            numberInMemory = savedInstanceState.getDouble("numberInMemory");
            fractionalPart = savedInstanceState.getString("decimalNumbers");
            hasFractionalPart = savedInstanceState.getBoolean("isDecimalNumber");
            numberInMemoryActive = savedInstanceState.getBoolean("secondNumberActive");
            newNumber = savedInstanceState.getBoolean("newNumber");
        }
    }

    public void calculateResult(){
        if(operation.isEmpty()){
            numberInMemory = Double.parseDouble(displayNumber.getText().toString());
            hasFractionalPart = false;//?
            fractionalPart = ".";
            return;
        }

        if(numberInMemoryActive){
            String result=null;
            if(operation.equals("+")){
                numberInMemory = numberInMemory + Double.parseDouble(displayNumber.getText().toString());
                result = String.valueOf(numberInMemory);
                System.out.println(numberInMemory);
            }else if(operation.equals("-")){
                numberInMemory = numberInMemory - Double.parseDouble(displayNumber.getText().toString());
                result = String.valueOf(numberInMemory);
            }else if(operation.equals("*")){
                numberInMemory = numberInMemory * Double.parseDouble(displayNumber.getText().toString());
                result = String.valueOf(numberInMemory);
            }else if(operation.equals("/")){
                numberInMemory = numberInMemory / Double.parseDouble(displayNumber.getText().toString());
                result = String.valueOf(numberInMemory);
            }else if(operation.equals("^")){
                double number = numberInMemory;
                for(int i = 0; i<(Double.parseDouble(displayNumber.getText().toString())-1); i++){
                    numberInMemory = numberInMemory*number;
                }
                result = String.valueOf(numberInMemory);
            }

            if(result.contains(".")){
                fractionalPart =result.substring(result.indexOf("."));
                hasFractionalPart = true;
            }else{
                fractionalPart =".";
                hasFractionalPart =false;
            }
        }
    }

    public void displayNumberInMemory(){
        displayNumber.setText(String.valueOf(numberInMemory));
    }

    public void deleteAllData(){
        displayNumber.setText("0");
        operation="";
        numberInMemory=0;
        fractionalPart =".";
        hasFractionalPart =false;
        numberInMemoryActive =false;
        newNumber=false;
    }

    public void displayNewDigit(String number){
        if(newNumber == true){
            newNumber = false;
            displayNumber.setText(number);
        }else{
            if(displayNumber.getText().toString().equals("0")){
                displayNumber.setText(number);
            }else if(displayNumber.getText().length()<9){
                if(!hasFractionalPart){
                    displayNumber.append(number);
                }else{
                    fractionalPart = fractionalPart.concat(number);
                    displayNumber.append(number);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("operation", operation);
        outState.putDouble("numberInMemory", numberInMemory);
        outState.putString("decimalNumbers", fractionalPart);
        outState.putBoolean("isDecimalNumber", hasFractionalPart);
        outState.putBoolean("secondNumberActive", numberInMemoryActive);
        outState.putBoolean("newNumber", newNumber);
        outState.putString("numberOnScreen", displayNumber.getText().toString());
    }
}
