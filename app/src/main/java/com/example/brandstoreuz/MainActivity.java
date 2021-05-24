package com.example.brandstoreuz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Adapters.Models.HorizontalModel;
import Adapters.Models.VerticalModel;
import Adapters.VerticalRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    static List<Drawable> drawables = new ArrayList<>();
    static List<String> strings = new ArrayList<>();

    ArrayList<VerticalModel> arrayListVertical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_main);

        //установка адаптера на слайдер
        ViewPager2 viewPager2 = findViewById(R.id.viewPagerImageSlide);
        drawables = loadImageFromAsset("TopImageSlider");
        viewPager2.setAdapter(new HorizontalSliderAdapter(drawables));

        //установка адаптера на вертикальный список
        RecyclerView verticalRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerview);
        verticalRecyclerView.setHasFixedSize(true);
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        arrayListVertical = new ArrayList<>();
        verticalRecyclerView.setAdapter(new VerticalRecyclerViewAdapter(this, arrayListVertical));
        setData();

        //установка адаптера на GridLayout, популярные категории
        RecyclerView gridRecyclerView = findViewById(R.id.gridRecyclerView);
        gridRecyclerView.setHasFixedSize(true);
        gridRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        drawables = loadImageFromAsset("PopularCategory");
        strings = loadStringFromAsset(R.array.PopularCategory);
        gridRecyclerView.setAdapter(new GridRecyclerAdapter(drawables,strings));

        //установка ячеек брендов
        RecyclerView gridBrandsRecyclerView = findViewById(R.id.brandsRecyclerview);
        gridBrandsRecyclerView.setHasFixedSize(true);
        gridBrandsRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        drawables = loadImageFromAsset("Brands");
        gridBrandsRecyclerView.setAdapter(new GridBrandsRecyclerAdapter(this,drawables));
    }

    private void setData() {
        List<String> categories = loadStringFromAsset(R.array.Categories);

        for(int i = 0; i < categories.size(); i++){
            VerticalModel verticalModel = new VerticalModel();
            verticalModel.setTitle(categories.get(i));
            ArrayList<HorizontalModel> arrayListHorizontal = new ArrayList<>();

            drawables = loadImageFromAsset("MainRecycleView/Accessories");
            List<String> description = loadStringFromAsset(R.array.Description);
            List<String> installment = loadStringFromAsset(R.array.Installment);
            List<String> price = loadStringFromAsset(R.array.Prices);

            for(int j = 0; j < 5; j++){
                HorizontalModel horizontalModel = new HorizontalModel();
                horizontalModel.setIcon(drawables.get(j));
                horizontalModel.setDescription(description.get(j));
                horizontalModel.setInstallment(installment.get(j));
                horizontalModel.setPrice(price.get(j));
                horizontalModel.setChildTitle(String.valueOf(j+1));

                arrayListHorizontal.add(horizontalModel);
            }
            verticalModel.setArrayList(arrayListHorizontal);

            arrayListVertical.add(verticalModel);
        }
    }

    public List<Drawable> loadImageFromAsset(String path){
        List<Drawable> images = new ArrayList<>();
        try {
            for (int i = 1; i <= getAssets().list(path).length; i++) {
                images.add(Drawable.createFromStream(getAssets().open(path + "/" + i + ".png"), null));
            }
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        return images;
    }
    public List<String> loadStringFromAsset(int path){
        return Arrays.asList(getResources().getStringArray(path));
    }
}