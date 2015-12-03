package com.example.ash.pokertracker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView showProfitText, mostRecentResultText;
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

    public void viewHistoryClicked(View view)
    {
        //Intent intent = new Intent(this, AddResult.class);
        //startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        showProfitText = (TextView) findViewById(R.id.profitTextView);
        mostRecentResultText = (TextView) findViewById(R.id.recentResultTextView);
        dbHandler = new MyDBHandler(this, null, null, 1);
        int profit = dbHandler.calculateProfit();
        if(profit < 0)
        {
            showProfitText.setText("-£"+Integer.toString(Math.abs(profit)));
            showProfitText.setTextColor(Color.parseColor("#fe0319"));
        }else
        {
            showProfitText.setText("£"+Integer.toString(profit));
            showProfitText.setTextColor(Color.parseColor("#449458"));
        }

        mostRecentResultText.setText(dbHandler.getMostRecentResultData());




    }
}
