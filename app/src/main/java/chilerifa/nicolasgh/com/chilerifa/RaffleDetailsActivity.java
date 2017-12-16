package chilerifa.nicolasgh.com.chilerifa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import chilerifa.nicolasgh.com.chilerifa.entity.Raffle;

public class RaffleDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String URL_SERVICE_API_RAFFLE = "http://159.203.79.251/app_dev.php";
    private Raffle rafflePersisted;
    private Button purchaseButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_raffle);
        configureRaffleButtonClicks();

        rafflePersisted = (Raffle) getIntent().getSerializableExtra("raffle");

    }

    private void configureRaffleButtonClicks() {
        Button prizeButton = findViewById(R.id.btn_prizes);
        purchaseButton = findViewById(R.id.btn_purchase);

        prizeButton.setOnClickListener(this);
        purchaseButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_purchase:
                purchaseButton.setEnabled(false);
                    this.purchase(rafflePersisted);
                break;
            case R.id.btn_prizes:
                startActivity(new Intent(RaffleDetailsActivity.this,PrizesActivity.class ));
                break;
        }
    }


    private void purchase(Raffle raffle) {
        final String url = URL_SERVICE_API_RAFFLE + "/raffles/";
        Integer balance = getIntent().getIntExtra("balance",0);

        if(raffle.getQuantity() > 0 && balance >= raffle.getCost()){
            JSONObject objetoJsonEnvio = new JSONObject();
            try {
                objetoJsonEnvio.put("quantity",raffle.getQuantity()-1);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            final String dataToSend = objetoJsonEnvio.toString();

            RequestQueue queueRequestVolley = Volley.newRequestQueue(this);

            StringRequest stringReq = new StringRequest(Request.Method.PUT, url + raffle.getId(),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String respuestaRecibida) {
                            try {
                                JSONObject json = new JSONObject(respuestaRecibida);
                                rafflePersisted.setQuantity(json.getInt("quantity"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            accountBalance();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            })
            {
                @Override
                public Map<String, String> getHeaders()  {
                    Map<String,String> headers=new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    headers.put("Accept", "application/json");
                    headers.put("Accept-Encoding", "utf-8");
                    return headers;
                }
                @Override
                public byte[] getBody() {
                    try {
                        return dataToSend == null ? null : dataToSend.getBytes("utf-8");
                    } catch (UnsupportedEncodingException ex) {
                        return null;
                    }
                }
                @Override
                public String getBodyContentType() {
                    return "application/json";
                }
            };
            queueRequestVolley.add(stringReq);
            Toast.makeText(this, "Has comprado esta rifa, tu saldo es: "+ balance , Toast.LENGTH_SHORT).show();
        } else {
            purchaseButton.setEnabled(true);
            Toast.makeText(this, "Saldo insuficiente. Tu saldo es: " + balance , Toast.LENGTH_SHORT).show();
        }
    }

    private void accountBalance(){
        final String url = URL_SERVICE_API_RAFFLE + "/accounts/";
        Integer accountId = getIntent().getIntExtra("account_id",0);
        Integer balance = getIntent().getIntExtra("balance",0);

        JSONObject objetoJsonEnvio = new JSONObject();

        try {
            objetoJsonEnvio.put("balance",balance - rafflePersisted.getCost());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String dataToSend = objetoJsonEnvio.toString();

        RequestQueue queueRequestVolley = Volley.newRequestQueue(this);

        StringRequest stringReq = new StringRequest(Request.Method.PUT, url + accountId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String respuestaRecibida) {
                        try {
                            JSONObject json = new JSONObject(respuestaRecibida);
                            getIntent().putExtra("balance",json.getInt("balance"));
                            purchaseButton.setEnabled(true);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        })
        {
            @Override
            public Map<String, String> getHeaders()  {
                Map<String,String> headers=new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");
                headers.put("Accept-Encoding", "utf-8");
                return headers;
            }
            @Override
            public byte[] getBody() {
                try {
                    return dataToSend == null ? null : dataToSend.getBytes("utf-8");
                } catch (UnsupportedEncodingException ex) {
                    return null;
                }
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        queueRequestVolley.add(stringReq);
    }
}
