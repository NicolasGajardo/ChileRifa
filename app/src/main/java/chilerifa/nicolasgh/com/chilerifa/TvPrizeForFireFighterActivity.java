package chilerifa.nicolasgh.com.chilerifa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TvPrizeForFireFighterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_prize);
        configureBackClick();
    }
    private void configureBackClick() {
        Button backButtonTv = findViewById(R.id.back_btn_tv);

        backButtonTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
