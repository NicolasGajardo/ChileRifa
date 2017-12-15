package chilerifa.nicolasgh.com.chilerifa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RaffleDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String URL_SERVICE_API = "http://159.203.79.251/app_dev.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raffle);
        configureRaffleButtonClicks();
    }

    private void configureRaffleButtonClicks() {
        Button prizeButton = findViewById(R.id.btn_prizes);
        Button purchaseButton = findViewById(R.id.btn_purchase);

        prizeButton.setOnClickListener(this);
        purchaseButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_purchase:

                break;
            case R.id.btn_prizes:
                startActivity(new Intent(RaffleDetailsActivity.this,PrizesActivity.class ));
                break;
        }
    }



    private void init() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String url = URL_SERVICE_API + "/raffles/1";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String inputResponse) {
                        try {
                            JSONObject jsonRequest = new JSONObject(inputResponse);

                            JSONArray array = jsonRequest.getJSONArray("hydra:member");

                            for (int i = 0;  i < array.length() ; i++){
                                JSONObject aux = array.getJSONObject(i);

                            }

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

    private void purchase() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String url = URL_SERVICE_API + "";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String inputResponse) {
                        try {
                            JSONObject jsonRequest = new JSONObject(inputResponse);

                            JSONArray array = jsonRequest.getJSONArray("hydra:member");

                            for (int i = 0;  i < array.length() ; i++){
                                JSONObject aux = array.getJSONObject(i);

                            }

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
