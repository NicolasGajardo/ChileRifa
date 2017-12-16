package chilerifa.nicolasgh.com.chilerifa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
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

import java.util.ArrayList;
import java.util.List;

import chilerifa.nicolasgh.com.chilerifa.entity.Raffle;

public class HomeActivity extends AppCompatActivity {
    ListView raffleList;
    int[] raffleIds = {1, 2, 3, 4, 5};
    String[] raffleName = {};
    String[] raffleDescription = {"Buy or Burn", "Changing the way you drive", "Help us help earth", "To a brighter future", "Mans new best friend"};
    private static final String URL_SERVICE_ACCOUNTS_API = "http://159.203.79.251/app_dev.php";
    Integer[] imgid = {R.drawable.firefighter, R.drawable.tesla, R.drawable.spacex, R.drawable.solarcity, R.drawable.openai};
    private static final String URL_SERVICE_API = "http://159.203.79.251/app_dev.php";
    private Intent intent;
    List<String> listRaffleName = new ArrayList<String>();
    List<Raffle> listRaffles = new ArrayList<Raffle>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        intent = new Intent(this, RaffleDetailsActivity.class);
        Bundle bundle = this.getIntent().getExtras();
        intent.replaceExtras(bundle);

        //intent.putExtra("account_id",bundle.getInt("account_id"));

        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        raffleList = findViewById(R.id.raffle_list);
        getRaffles(this);


    }

    public void raffleClicks(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,raffleName);
        raffleList.setAdapter(adapter);
        raffleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String raffleName = ((AppCompatTextView) view).getText().toString();
                for (Raffle r : listRaffles) {
                    if(r.getName().equals(raffleName)){
                        intent.putExtra("raffle", r);
                        startActivity(intent);
                    }
                }
            }
        });

    }

    private void getRaffles(final HomeActivity homeActivity){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String url = URL_SERVICE_ACCOUNTS_API + "/raffles";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String inputResponse) {
                        try {
                            JSONObject jsonRequest = new JSONObject(inputResponse);

                            JSONArray array = jsonRequest.getJSONArray("hydra:member");
                            for (int i = 0;  i < array.length() ; i++){
                                Raffle raffle = new Raffle();
                                JSONObject aux = array.getJSONObject(i);
                                raffle.setId(aux.getInt("id"));
                                listRaffleName.add(aux.getString("name"));
                                raffle.setName(aux.getString("name"));
                                raffle.setDescription(aux.getString("description"));
                                raffle.setCost(aux.getInt("cost"));
                                raffle.setQuantity(aux.getInt("quantity"));
                                listRaffles.add(raffle);
                            }
                            raffleName = listRaffleName.toArray(new String[0]);
                            CustomRaffleList customRaffleList = new CustomRaffleList(homeActivity, raffleName, raffleDescription, imgid);
                            raffleList.setAdapter(customRaffleList);
                            raffleClicks();
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