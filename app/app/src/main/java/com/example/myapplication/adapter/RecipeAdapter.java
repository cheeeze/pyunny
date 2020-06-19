package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.vo.Recipe;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Recipe> list;

    public RecipeAdapter(Context mContext, ArrayList<Recipe> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View converterView = LayoutInflater.from(mContext).inflate(R.layout.recipe_card_view,parent,false);
        return new ViewHolder(converterView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = list.get(position);
        holder.txt_recipe_item_title.setText(recipe.getTitle());
        holder.txt_recipe_item_writer.setText(recipe.getWriter());
        holder.txt_recipe_item_date.setText(recipe.getDate());
        Glide.with(mContext).load(recipe.getMain_image()).into(holder.btn_recipe_item_main_image);




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageButton btn_recipe_item_main_image;
        private TextView txt_recipe_item_title;
        private TextView txt_recipe_item_writer;
        private TextView txt_recipe_item_date;
        public ViewHolder(@NonNull View view){
            super(view);
            btn_recipe_item_main_image = view.findViewById(R.id.btn_recipe_item_main_image);
            txt_recipe_item_title = view.findViewById(R.id.txt_recipe_item_title);
            txt_recipe_item_writer = view.findViewById(R.id.txt_recipe_item_writer);
            txt_recipe_item_date = view.findViewById(R.id.txt_recipe_item_date);
        }
    }
}
