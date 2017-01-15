package xyz.triangularcubicle.paintwall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class helpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setTitle("Help");
    }


}
