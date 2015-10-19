package com.example.ash.pokertracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView showProfitText;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void addNewResultClicked(View view)
    {
        Intent intent = new Intent(this, AddResult.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        showProfitText = (TextView) findViewById(R.id.profitTextView);
        dbHandler = new MyDBHandler(this, null, null, 1);
        int profit = dbHandler.calculateProfit();
        showProfitText.setText(Integer.toString(profit));


    }
}
