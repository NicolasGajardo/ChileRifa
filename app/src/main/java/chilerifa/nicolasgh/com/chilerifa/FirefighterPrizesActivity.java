package chilerifa.nicolasgh.com.chilerifa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirefighterPrizesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firefighter_prizes);
        configurePrizesClick();
    }

    private void configurePrizesClick() {
        Button prizeTVButton = findViewById(R.id.btn_prize_tv);
        Button prizeCarButton = findViewById(R.id.btn_prize_car);
        Button prizeTripButton = findViewById(R.id.btn_prize_trip);

        prizeTVButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirefighterPrizesActivity.this,TvPrizeForFireFighterActivity.class ));
            }
        });
        prizeCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirefighterPrizesActivity.this,CarFireFighterActivity.class));
            }
        });
        prizeTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirefighterPrizesActivity.this,TripPrizeFireFighterActivity.class));
            }
        });

    }
}
