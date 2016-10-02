package com.tpms.calculater;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tpms.calculater.model.Calculater;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends Activity {


    private TextView textBoxField;
    private Button equalsButton;
    private Button cleanButton;

    private List<Button> numericButtons = new LinkedList<>();
    private List<Button> mrButtons = new LinkedList<>();
    private List<Button> operationButtons = new LinkedList<>();

    private View.OnClickListener numberButtonListener;
    private View.OnClickListener mrButtonListener;
    private View.OnClickListener operationButtonListener;

    private Float previouse;
    private Calculater calculater = new Calculater();
    private Button previuseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textBoxField = (TextView) findViewById(R.id.textBoxField);
        equalsButton = (Button) findViewById(R.id.button_equals);
        cleanButton = (Button) findViewById(R.id.button_mr_3);

        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Float currentValue = Float.parseFloat(textBoxField.getText().toString());
                try {
                    Float result = calculater.calculater(previouse, currentValue, previuseButton.getText().toString());
                    textBoxField.setText(result.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Input correct number! " + e.toString(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        cleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textBoxField.setText("");
            }
        });
        setingListeners();
        loadButtons();
    }

    private void setingListeners() {
        numberButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textBoxField.append(((Button) v).getText() );
            }
        };

        mrButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float currentValue = Float.parseFloat(textBoxField.getText().toString());
                Float result = null;
                try {
                    result = calculater.calculater(currentValue, ((Button) v).getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Input correct number! " + e.toString(), Toast.LENGTH_SHORT);
                    toast.show();
                }
                textBoxField.setText(result.toString());
            }
        };

        operationButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Float currentValue = Float.parseFloat(textBoxField.getText().toString());
                    textBoxField.setText("");
                    previuseButton = (Button) v;
                    if(previouse == null) {
                        previouse = new Float(currentValue.floatValue());
                        return;
                    }
                    Float result = calculater.calculater(previouse, currentValue, ((Button) v).getText().toString());
                    if(result != null)
                        textBoxField.setText(result.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Input correct number! " + e.toString(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        };
    }

    protected void loadButtons() {


        for (int i = 0; i < 11; i++) {
            int id = getResources().getIdentifier("button_"+i, "id", getPackageName());
            Button button = (Button) findViewById(id);
            button.setOnClickListener(numberButtonListener);
            numericButtons.add(button);

        }

        for (int i = 1; i < 3; i++) {
            int id = getResources().getIdentifier("button_mr_"+i, "id", getPackageName());
            Button button = (Button) findViewById(id);
            button.setOnClickListener(mrButtonListener);
            mrButtons.add((Button) findViewById(id));
        }

        for (int i = 1; i < 5; i++) {
            int id = getResources().getIdentifier("button_op_"+i, "id", getPackageName());
            Button button = (Button) findViewById(id);
            button.setOnClickListener(operationButtonListener);
            operationButtons.add((Button) findViewById(id));
        }
    }


}
