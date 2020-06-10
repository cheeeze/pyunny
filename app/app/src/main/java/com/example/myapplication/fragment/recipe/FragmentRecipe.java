package com.example.myapplication.fragment.recipe;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.RecipeAdapter;
import com.example.myapplication.vo.Recipe;

import java.util.ArrayList;

public class FragmentRecipe extends Fragment {

    private Context mContext;

    private RecyclerView recyclerView;
    private EditText edt_recipe_search;
    private Button btn_recipe_search;
    private Spinner spinner_recipe_sort;

    private ArrayList<Recipe> recipeList = new ArrayList<>();
    private RecipeAdapter mRecipeAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_recipe,container,false);
        mContext = view.getContext();
        init(view);
        return view;
    }

    private void init(View view){
        recyclerView = view.findViewById(R.id.recyclerView_recipe);
        edt_recipe_search = view.findViewById(R.id.edt_recipe_search);
        btn_recipe_search = view.findViewById(R.id.btn_recipe_search);
        spinner_recipe_sort = view.findViewById(R.id.spinner_recipe_sort);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext,2);
        recyclerView.setLayoutManager(mLayoutManager);
        mRecipeAdapter = new RecipeAdapter(mContext,recipeList);

        recyclerView.setAdapter(mRecipeAdapter);
        getRecipeList();

    }

    private void getRecipeList(){
        recipeList.clear();
//        int id, int user_id, String ingredient, String date, String title, String content, String writer, String main_image
        recipeList.add(new Recipe(0,1,"","2020-06-11","마크정식","","편의뽕마스터","https://t1.daumcdn.net/liveboard/dailylife/3d08e19e89514cee8963b39fb4ed24dc.jpg"));
        recipeList.add(new Recipe(1,2,"","2020-06-10","궁극의 레시피","","미미광어","https://img1.daumcdn.net/thumb/R720x0/?fname=https%3A%2F%2Ft1.daumcdn.net%2Fliveboard%2Fbabshim%2Fc933c9b8237e47a0873232b42ee31a12.JPG"));
        recipeList.add(new Recipe(2,3,"","2020-06-09","JMT감자칩피자","","JMT그자체","https://img1.daumcdn.net/thumb/R720x0/?fname=https%3A%2F%2Ft1.daumcdn.net%2Fliveboard%2Fbabshim%2F232755146fb14e17a8c0c1bb395123bb.JPG"));
        recipeList.add(new Recipe(3,4,"","2020-05-31","벤읍정식","","벤읍읍","https://file3.instiz.net/data/file3/2018/04/16/8/4/f/84f14e3b4eece5ccfe31030fbc95cb06.jpg"));
        recipeList.add(new Recipe(4,5,"","2020-05-27","5G는 오지후라이","","5G마스터","https://file3.instiz.net/data/file3/2018/04/16/0/3/9/03960de3dbd9d33270568294fda854e6.jpg"));
    }
}
