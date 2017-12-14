package chilerifa.nicolasgh.com.chilerifa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CarFireFighterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_fire_fighter);
        backButton();
    }

    protected void backButton(){
        Button backButtonCar = findViewById(R.id.btn_back_car);
        backButtonCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
