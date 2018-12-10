package edu.gvsu.cis.deadvastation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import edu.gvsu.cis.deadvastation.dummy.ShopContent;

import static java.lang.Integer.valueOf;

public class LoadoutActivity extends AppCompatActivity {
    // Options dropdown selections
    public static int GAME_RESULT = 1;
    public static int OPTIONS_RESULT = 2;
    public static int SHOP_RESULT = 3;
    public static int HIGH_SCORES_RESULT = 4;

    // Loadout views
//    private ImageButton easyButton;
//    private ImageButton normalButton;
//    private ImageButton hardButton;
    private TextView currentHighScore;
    private Button playButton;
    private Button highScoresButton;
    private ImageView gearChoice;
    private TextView gearName;
    private Button saveButton;

    // Game state variables (may want in own class)
    // todo share variables with game to control state
    public Integer itemNumber = 0;
    public String itemName = "Pistol";
    public enum loadout {PISTOL, RIFLE, SNIPER};
    public loadout loadout;
//    public int ammoCount = 0;  // account for this?
    public Integer score = 100;  // todo put back at 0 after testing
    public Integer highScore = 100;  // todo retrieve from db
    public Integer health = 100;  // start at 100


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadout);

        // Wire up the views from the layout.
//        easyButton = findViewById(R.id.easyButton);
//        normalButton = findViewById(R.id.normalButton);
//        hardButton = findViewById(R.id.hardButton);
        currentHighScore = findViewById(R.id.loadoutHighScoreView);
        playButton = findViewById(R.id.playButton);
//        highScoresButton = findViewById(R.id.highScoresButton);
        gearChoice = findViewById(R.id.gearImage);
        gearName = findViewById(R.id.gearName);
        saveButton = findViewById(R.id.saveButton);

        //todo if user logged in, get the high score from the db

        // update scores
        if (score >= highScore) {
            highScore = score;
            currentHighScore.setText(score.toString());
        }

        playButton.setOnClickListener(v -> {
            // todo transition to game activity
            Intent intent = new Intent(LoadoutActivity.this, GameActivity.class);
            intent.putExtra("item", itemNumber);
            intent.putExtra("score", score);
            intent.putExtra("health", health);
//            intent.putExtra("ammo", ammoCount);
            startActivityForResult(intent, GAME_RESULT);
        });

        saveButton.setOnClickListener(v -> {
            // todo save data to db

        });

        // these are reached only through options drop down
//        shopButton.setOnClickListener(v -> {
//
//        });

//        highScoresButton.setOnClickListener(v -> {
//
//        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // todo update the game data

        // todo update the display views
        // update scores
        if (score >= highScore) {
            highScore = score;
            currentHighScore.setText(score.toString());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.loadout_menu, menu);
        return true;  // make visible
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_game) {
            Intent intent = new Intent(LoadoutActivity.this, GameActivity.class);
            intent.putExtra("item", itemNumber);
            intent.putExtra("score", score);
            intent.putExtra("health", health);
//            intent.putExtra("ammo", ammoCount);
            startActivityForResult(intent, GAME_RESULT);
            return true;
        } else if(item.getItemId() == R.id.action_options) {
            Intent intent = new Intent(LoadoutActivity.this, OptionsActivity.class);

            startActivityForResult(intent, OPTIONS_RESULT);
            return true;
        } else if(item.getItemId() == R.id.action_shop) {
            Intent intent = new Intent(LoadoutActivity.this, ShopActivity.class);
            intent.putExtra("score", score);
            startActivityForResult(intent, SHOP_RESULT);
            return true;
        } else if(item.getItemId() == R.id.action_high_scores) {
            Intent intent = new Intent(LoadoutActivity.this, HighScoresActivity.class);

            startActivityForResult(intent, HIGH_SCORES_RESULT);
            return true;
        }


        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == GAME_RESULT) {
            // todo update data from the game

        } else if (resultCode == OPTIONS_RESULT) {
            // todo adjust the volume based on user choice

        } else if (resultCode == SHOP_RESULT) {
            // todo update chosen item from shop
            String[] vals = data.getStringArrayExtra("item");
            this.itemNumber = Integer.parseInt(vals[0]);
            // update the image based on the chosen loadout
            int id;
            switch (this.itemNumber) {
                case 0:
                    id = getResources().getIdentifier("ic_handgun", "drawable", getPackageName());
                    this.gearChoice.setImageResource(id);

                    break;
                case 1:
                    id = getResources().getIdentifier("ic_automatic_weapon", "drawable", getPackageName());
                    this.gearChoice.setImageResource(id);
                    break;
                case 2:
                    id = getResources().getIdentifier("ic_sniper_rifle", "drawable", getPackageName());
                    this.gearChoice.setImageResource(id);
                    break;
            }

            this.itemName = vals[1];
            gearName.setText(this.itemName);

        } else if (resultCode == HIGH_SCORES_RESULT) {
            // todo update text of high score


        }

//        public void updateScores() {
//            if (score > highScore) {
//                highScore = score;
//                currentHighScore.setText(score.toString());
//            }
//        }

    }



}
