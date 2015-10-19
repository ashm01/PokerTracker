package com.example.ash.pokertracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class AddResult extends Activity {

    EditText nameInput, typeInput, buyInInput, returnInput;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_result);
        nameInput = (EditText) findViewById(R.id.resultNameTextField);
        typeInput = (EditText) findViewById(R.id.resultTypeTextField);
        buyInInput = (EditText) findViewById(R.id.resultBuyInTextField);
        returnInput = (EditText) findViewById(R.id.resultReturnTextField);
        dbHandler = new MyDBHandler(this, null, null, 1);

    }

    public void addResultClicked(View view)
    {

        String name = nameInput.getText().toString();
        String type = typeInput.getText().toString();
        int buyIn = Integer.parseInt(buyInInput.getText().toString());
        int returnAmount = Integer.parseInt(returnInput.getText().toString());

        ResultData result = new ResultData(name,type,buyIn,returnAmount);

        dbHandler.addResult(result);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }
}
