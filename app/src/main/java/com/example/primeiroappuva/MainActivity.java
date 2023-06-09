package com.example.primeiroappuva;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText txtVal1, txtVal2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVal1 =   findViewById(R.id.editTextNumber);
        txtVal2 =   findViewById(R.id.editTextNumber2);

        limitEditText(txtVal1, 0, 10000);
        limitEditText(txtVal2, 0, 10000);
    }

    public void operar(View view) {

        TextView txtResult1, txtResult2, txtResult3, txtResult4;
        double x, y;

        txtResult1   =   findViewById(R.id.textResposta1);
        txtResult2   =   findViewById(R.id.textResposta2);
        txtResult3   =   findViewById(R.id.textResposta3);
        txtResult4   =   findViewById(R.id.textResposta4);

        txtResult1.setText("");
        txtResult2.setText("");
        txtResult3.setText("");
        txtResult4.setText("");

        String txt1 = String.valueOf(txtVal1.getText());
        String txt2 = String.valueOf(txtVal2.getText());

        if(TextUtils.isEmpty(txt1) || TextUtils.equals(txt1, "."))
        {
            txtVal1.setError("Digite um número");

        } else if (TextUtils.isEmpty(txt2) || TextUtils.equals(txt2, ".")){

            txtVal2.setError("Digite um número");

        } else {

            x = Double.parseDouble(txtVal1.getText().toString());
            y = Double.parseDouble(txtVal2.getText().toString());

            Operacoes op    =   new Operacoes(x,y);

            txtResult1.setText(op.somar());
            txtResult2.setText(op.subtrair());
            txtResult3.setText(op.multiplicar());
            txtResult4.setText(op.dividir());

        }

    }

    //Esconde o Soft Keyboard ao clicar fora de um campo de input
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText ) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    //Esconde o teclado
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    public abstract class MinMaxTextWatcher implements TextWatcher {
        int min, max;
        public MinMaxTextWatcher(int min, int max) {
            super();
            this.min = min;
            this.max = max;
        }

    }
    private void limitEditText(final EditText ed, int min, int max) {
        ed.addTextChangedListener(new MinMaxTextWatcher(min, max) {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                float n = 0;
                try {
                    n = Float.parseFloat(str);
                    if (str.equals(max + ".")) {
                        ed.setText(Integer.toString(max));
                        Toast.makeText(getApplicationContext(), "Número máximo permitido é " + max, Toast.LENGTH_SHORT).show();
                        ed.setSelection(2);

                    } else if (n > max) {
                        ed.setText(Integer.toString(max));
                        Toast.makeText(getApplicationContext(), "Número máximo permitido é " + max, Toast.LENGTH_SHORT).show();
                        ed.setSelection(Integer.toString(max).length());

                    } else if (s.charAt(0) == '0' && n >= 1) { // remover 0`s à esquerda
                        ed.setText(Float.toString(n));
                        ed.setSelection(Float.toString(n).length());
                    }
                }
                catch(NumberFormatException nfe) {

                }
            }
        });
    }

}

