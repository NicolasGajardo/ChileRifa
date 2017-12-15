package chilerifa.nicolasgh.com.chilerifa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText name = findViewById(R.id.etName);
        final EditText lastName = findViewById(R.id.etLastName);
        final EditText nickname = findViewById(R.id.etNickName);
        final EditText email = findViewById(R.id.etEmail);
        final EditText password = findViewById(R.id.etPassword);
        final Button registerButton = findViewById(R.id.btnRegister);
    }
}
