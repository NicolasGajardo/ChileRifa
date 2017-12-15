package chilerifa.nicolasgh.com.chilerifa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RaffleDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raffle);
        configureRaffleButtonClicks();
    }

    private void configureRaffleButtonClicks() {
        Button prizeButton = findViewById(R.id.btn_prizes);
        Button purchaseButton = findViewById(R.id.btn_purchase);


        prizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RaffleDetailsActivity.this,PrizesActivity.class ));
            }
        });
        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Some Logic for the purchase
            }
        });

    }
}
