package chilerifa.nicolasgh.com.chilerifa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PrizesActivity extends AppCompatActivity {
    ListView prizeList;
    String[] prizename = {"Tv","Car","Trip","Laptop","Pony"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prizes);

        prizeList = findViewById(R.id.prize_list);
        PrizeClicks();

    }


    public void PrizeClicks(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,prizename);
        prizeList.setAdapter(adapter);
        prizeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(PrizesActivity.this,PrizeDetailsActivity.class));
            }
        });

    }



    }

