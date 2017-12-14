package chilerifa.nicolasgh.com.chilerifa;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Paola on 12/13/2017.
 */

public class CustomRaffleList extends ArrayAdapter<String> {
    private String[] rafflename;
    private String[] description;
    private Integer[] imgid;
    private Activity context;

    //Make a constructor
    public CustomRaffleList(Activity context, String[] rafflename,String[] description,Integer[] imgid) {
        super(context, R.layout.rafflel_list_layout,rafflename);
        this.context = context;
        this.rafflename=rafflename;
        this.description = description;
        this.imgid = imgid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r=convertView;
        ViewHolder viewHolder= null;
        if(r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.rafflel_list_layout,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder)r.getTag();
        }
        viewHolder.ivw.setImageResource(imgid[position]);
        viewHolder.tvw1.setText(rafflename[position]);
        viewHolder.tvw2.setText(description[position]);

        return r;
    }
//Optimize
    class ViewHolder
    {
        TextView tvw1;
        TextView tvw2;
        ImageView ivw;
        ViewHolder(View v)
        {
            tvw1 = v.findViewById(R.id.tvRaffelName);
            tvw2 = v.findViewById(R.id.tvDescription);
            ivw = v.findViewById(R.id.imageView);

        }

    }
}
