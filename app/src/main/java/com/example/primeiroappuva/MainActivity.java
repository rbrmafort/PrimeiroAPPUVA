package com.example.primeiroappuva;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void operar(View view) {

        EditText txtVal1, txtVal2;
        TextView txtResult1, txtResult2, txtResult3, txtResult4;
        double x, y;

        txtVal1 =   findViewById(R.id.editTextNumber);
        txtVal2 =   findViewById(R.id.editTextNumber2);
        txtResult1   =   findViewById(R.id.textResposta1);
        txtResult2   =   findViewById(R.id.textResposta2);
        txtResult3   =   findViewById(R.id.textResposta3);
        txtResult4   =   findViewById(R.id.textResposta4);

        txtResult1.setText("");
        txtResult2.setText("");
        txtResult3.setText("");
        txtResult4.setText("");

        if(TextUtils.isEmpty(txtVal1.getText()))
        {
            txtVal1.setError("Digite um número");

        } else if (TextUtils.isEmpty(txtVal2.getText())){

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

}

