package chilerifa.nicolasgh.com.chilerifa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String URL_SERVICE_API = "http://192.168.109.129/app_dev.php";

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogin){
            startActivity(new Intent(LogInActivity.this,HomeActivity.class ));
        }
    }


    private void consultarDatos(int idConsulta) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        boolean resolution = false;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SERVICE_API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String respuestaRecibida) {
                        //En caso de éxito en la solicitud. Aquí se gestionan los datos de la respuesta.
                        //En este caso se extrae un valor de la respuesta, convirtiendola primero a un objeto JSON.

                        try {
                            JSONObject respuestaJson = new JSONObject(respuestaRecibida);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //En caso de error en la solicitud
            }
        });
        requestQueue.add(stringRequest);

        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();

    }

}
