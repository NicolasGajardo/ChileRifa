package chilerifa.nicolasgh.com.chilerifa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    EditText etPassword;
    EditText etUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnLogin = findViewById(R.id.btnLogin);
        etPassword = findViewById(R.id.etPassword);
        etUsername = findViewById(R.id.etUserName);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                if(this.validateFields()){
                    allowEntry(etUsername.getText().toString(), etPassword.getText().toString());
                    startActivity(new Intent(LogInActivity.this,HomeActivity.class ));
                } else {
                    Toast.makeText(this, "Incorrect values", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private boolean validateFields(){
        return etUsername.getText() != null && !etUsername.getText().toString().isEmpty()
                && etPassword.getText() != null && !etPassword.getText().toString().isEmpty();
    }


    private void allowEntry(String username, String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        boolean resolution = false;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SERVICE_API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String respuestaRecibida) {
                        try {
                            JSONObject respuestaJson = new JSONObject(respuestaRecibida);
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
