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

public class HomeActivity extends AppCompatActivity {
    ListView raffleList;
    String[] rafflename = {"Bombero", "Tesla", "SpaceX", "SolarCity", "OpenAI"};
    String[] raffleDescruption = {"Buy or Burn", "Changing the way you drive", "Help us help earth", "To a brighter future", "Mans new best friend"};
    Integer[] imgid = {R.drawable.firefighter, R.drawable.tesla, R.drawable.spacex, R.drawable.solarcity, R.drawable.openai};
    private static final String URL_SERVICE_API = "http://159.203.79.251/app_dev.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        getRaffles();
        raffleList = findViewById(R.id.raffle_list);
       CustomRaffleList customRaffleList = new CustomRaffleList(this, rafflename, raffleDescruption, imgid);
       raffleList.setAdapter(customRaffleList);
       raffleClicks();
    }

    public void raffleClicks(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,rafflename);
        raffleList.setAdapter(adapter);
        raffleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(HomeActivity.this,RaffleDetailsActivity.class));
            }
        });

    }



    private void getRaffles() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SERVICE_API+"/raffles",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String respuestaRecibida) {
                        try {
                            JSONObject respuestaJson = new JSONObject(respuestaRecibida);
                            JSONArray array = respuestaJson.getJSONArray("hydra:member");

                            for (int i = 0;  i < array.length() ; i++){
                                JSONObject aux = array.getJSONObject(i);

                                String name = aux.getString("name");
                                if(name!=null){
                                    //TODO: deberia hacerse con un arraylist
                                    rafflename[i] = name;
                                }
                            }
//                            CustomRaffleList customRaffleList = new CustomRaffleList(this, rafflename, raffleDescruption, imgid);
//                            raffleList.setAdapter(customRaffleList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
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



