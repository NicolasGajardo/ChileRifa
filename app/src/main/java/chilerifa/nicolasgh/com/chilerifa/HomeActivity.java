package chilerifa.nicolasgh.com.chilerifa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    ListView raffleList;
    int[] raffleIds = {1, 2, 3, 4, 5};
    String[] raffleName = {"Bombero", "Tesla", "SpaceX", "SolarCity", "OpenAI"};
    String[] raffleDescription = {"Buy or Burn", "Changing the way you drive", "Help us help earth", "To a brighter future", "Mans new best friend"};

    Integer[] imgid = {R.drawable.firefighter, R.drawable.tesla, R.drawable.spacex, R.drawable.solarcity, R.drawable.openai};
    private static final String URL_SERVICE_API = "http://159.203.79.251/app_dev.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        raffleList = findViewById(R.id.raffle_list);
       CustomRaffleList customRaffleList = new CustomRaffleList(this, raffleName, raffleDescription, imgid);
       raffleList.setAdapter(customRaffleList);
       raffleClicks();
    }

    public void raffleClicks(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,raffleName);
        raffleList.setAdapter(adapter);
        raffleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(HomeActivity.this,RaffleDetailsActivity.class));
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.raffle_list:
                Intent intent = new Intent(this, RaffleDetailsActivity.class);
                intent.replaceExtras(this.getIntent().getExtras());
                startActivity(intent);
                break;
        }
    }

    public void onItemClick(AdapterView<?> var1, View var2, int var3, long var4){
        Intent intent = new Intent(this, RaffleDetailsActivity.class);
        intent.replaceExtras(this.getIntent().getExtras());
        startActivity(intent);
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