package chilerifa.nicolasgh.com.chilerifa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    ListView raffleList;
    String[] rafflename = {"Bombero", "Tesla", "SpaceX", "SolarCity", "OpenAI"};
    String[] raffleDescruption = {"Buy or Burn", "Changing the way you drive", "Help us help earth", "To a brighter future", "Mans new best friend"};
    Integer[] imgid = {R.drawable.firefighter, R.drawable.tesla, R.drawable.spacex, R.drawable.solarcity, R.drawable.openai};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toast.makeText(this, "Welcome", Toast.LENGTH_LONG).show();

        raffleList = findViewById(R.id.raffle_list);
        CustomRaffleList customRaffleList = new CustomRaffleList(this, rafflename, raffleDescruption, imgid);
        raffleList.setAdapter(customRaffleList);

    }
}































/*

    //Making a method for giving the button a action
    //For now using a button to chage between activities
        private void configureRaffelButtonClicks(){
            //Declare all the raffels (Buttons "For now")
            Button fireFighterRaffel_btn = (Button)findViewById(R.id.btn_bombmero);
            Button teslaRaffel_btn = (Button)findViewById(R.id.btn_tesla);
            Button spaceXRaffel_btn = (Button)findViewById(R.id.btn_spaceX);
            TextView welcomeMessage = (TextView)findViewById(R.id.tvWelcome);

            fireFighterRaffel_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this,RaffleDetailsActivity.class));
                }
            });
            teslaRaffel_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this,TeslaRaffelActivity.class));
                }
            });
            spaceXRaffel_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this,SpaceXRaffelActivity.class));
                }
            });
*/



