package edu.gvsu.cis.deadvastation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoadoutActivity extends AppCompatActivity {
    // Options dropdown selections
    public static int GAME_RESULT = 1;
    public static int OPTIONS_RESULT = 2;
    public static int SHOP_RESULT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadout);
    }




}