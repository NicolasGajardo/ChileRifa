package chilerifa.nicolasgh.com.chilerifa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TripPrizeFireFighterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_prize_fire_fighter);
    backButtonTrip();
    }

    protected void backButtonTrip(){
        Button backButtonTrip = findViewById(R.id.btn_back_trip);
        backButtonTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
