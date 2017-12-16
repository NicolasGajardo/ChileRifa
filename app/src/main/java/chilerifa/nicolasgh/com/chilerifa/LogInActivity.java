package chilerifa.nicolasgh.com.chilerifa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String URL_SERVICE_ACCOUNTS_API = "http://159.203.79.251/app_dev.php/accounts";

    Button btnLogin;
    EditText etPassword;
    EditText etUsername;
    Integer balance;
    boolean resolution = false;
    Intent intent;

    Integer accountId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        intent = new Intent();
        btnLogin = (Button) findViewById(R.id.btnLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etUsername = (EditText) findViewById(R.id.etUserName);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                if(this.validateFields()){
                    allowEntry();
                    if(resolution && accountId != null && accountId != 0){
                        intent = new Intent(this, HomeActivity.class);
                        intent.putExtra("username",etUsername.getText().toString());
                        intent.putExtra("password",etPassword.getText().toString());
                        intent.putExtra("balance",balance);
                        intent.putExtra("account_id", accountId);

                        startActivity(intent);
                    }
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

    private void allowEntry() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String url = URL_SERVICE_ACCOUNTS_API +"?nickname="+etUsername.getText().toString()+"&password="+etPassword.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String inputResponse) {
                        try {
                            JSONObject jsonRequest = new JSONObject(inputResponse);

                            JSONArray array = jsonRequest.getJSONArray("hydra:member");

                            for (int i = 0;  i < array.length() ; i++){
                                JSONObject aux = array.getJSONObject(i);

                                accountId = aux.getInt("id");
                                String nickname = aux.getString("nickname");
                                String password = aux.getString("password");


                                balance = aux.getInt("balance");
                                if(etUsername.getText().toString().equals(nickname)
                                        && etPassword.getText().toString().equals(password)){
                                    resolution = true;
                                    break;
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resolution = false;
            }
        });
        requestQueue.add(stringRequest);
    }
}
