package com.example.fitnessproject3fall;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessproject3fall.model.FitnessDAO;
import com.example.fitnessproject3fall.model.FitnessDB;
import com.example.fitnessproject3fall.model.GroupChat;
import com.example.fitnessproject3fall.model.Links;

import java.util.List;

public class NutritionActivity extends AppCompatActivity {
    List<Links> links;
    FitnessDAO dao = FitnessDB.getFitnessDB(this).dao();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

       /* TextView msg = findViewById(R.id.links_urls);

        String link_url = "";
        for(int i =0; i < links.size();i++){
            link_url += links.get(i).getLink_url() + "\n";
        }
        msg.setText(link_url);*/



        Button back_button = findViewById(R.id.backButtonNutri);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        links = dao.searchLinks(LoginActivity.GROUP_ID);
        Log.d("NutritonActivity", "Links's" + links.size());
        RecyclerView rv = findViewById(R.id.nutrition_list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new Adapter());
    }


    private class Adapter extends RecyclerView.Adapter<ItemHolder> {

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(NutritionActivity.this);
            return new ItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            holder.bind(links.get(position));
        }

        @Override
        public int getItemCount() {
            return links.size();
        }

    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        public ItemHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.linksitem, parent, false));
        }

        public void bind(Links f) {
            TextView item = itemView.findViewById(R.id.link_item);
            ImageView img = itemView.findViewById(R.id.imageLink);


            item.setText(f.toString());

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(f.getLink_url()));
                    startActivity(i);
                }
            });
        }
    }

   }