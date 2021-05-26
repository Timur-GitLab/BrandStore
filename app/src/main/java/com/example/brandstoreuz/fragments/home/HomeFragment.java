package com.example.brandstoreuz.fragments.home;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.brandstoreuz.R;
import com.example.brandstoreuz.fragments.home.Adapters.GridBrandsRecyclerAdapter;
import com.example.brandstoreuz.fragments.home.Adapters.GridRecyclerAdapter;
import com.example.brandstoreuz.fragments.home.Adapters.HorizontalSliderAdapter;
import com.example.brandstoreuz.fragments.home.Adapters.Models.HorizontalModel;
import com.example.brandstoreuz.fragments.home.Adapters.Models.VerticalModel;
import com.example.brandstoreuz.fragments.home.Adapters.VerticalRecyclerViewAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    static List<Drawable> drawables = new ArrayList<>();
    static List<String> strings = new ArrayList<>();
    ArrayList<VerticalModel> arrayListVertical;

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        //установка адаптера на слайдер
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPagerImageSlide);
        drawables = loadImageFromAsset("TopImageSlider");
        viewPager2.setAdapter(new HorizontalSliderAdapter(drawables));
        autoImageSlider(drawables.size(),viewPager2);

        //установка адаптера на вертикальный список
        RecyclerView verticalRecyclerView = (RecyclerView) view.findViewById(R.id.mainRecyclerview);
        verticalRecyclerView.setHasFixedSize(true);
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        arrayListVertical = new ArrayList<>();
        verticalRecyclerView.setAdapter(new VerticalRecyclerViewAdapter(getActivity(), arrayListVertical));
        setData();

        //установка адаптера на GridLayout, популярные категории
        RecyclerView gridRecyclerView = view.findViewById(R.id.gridRecyclerView);
        gridRecyclerView.setHasFixedSize(true);
        gridRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        drawables = loadImageFromAsset("PopularCategory");
        strings = loadStringFromAsset(R.array.PopularCategory);
        gridRecyclerView.setAdapter(new GridRecyclerAdapter(drawables,strings));

        //установка ячеек брендов
        RecyclerView gridBrandsRecyclerView = view.findViewById(R.id.brandsRecyclerview);
        gridBrandsRecyclerView.setHasFixedSize(true);
        gridBrandsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        drawables = loadImageFromAsset("Brands");
        gridBrandsRecyclerView.setAdapter(new GridBrandsRecyclerAdapter(getActivity(),drawables));
        super.onViewCreated(view, savedInstanceState);
    }


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,container,false);
    }

    int currentPage = 0;
    public void autoImageSlider(int Num_Pages, ViewPager2 viewPager2){
        long DELAY_MS = 2000;
        long PERIOD_MS = 2000;
        final Handler handler = new Handler();
        final Runnable update = () -> {
            if(currentPage == Num_Pages){
                currentPage = 0;
            }
            viewPager2.setCurrentItem(currentPage++, true);
        };

        Timer timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_MS,PERIOD_MS);
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

                arrayListHorizontal.add(horizontalModel);
            }
            verticalModel.setArrayList(arrayListHorizontal);

            arrayListVertical.add(verticalModel);
        }
    }

    public List<Drawable> loadImageFromAsset(String path){
        List<Drawable> images = new ArrayList<>();
        try {
            for (int i = 1; i <= getActivity().getAssets().list(path).length; i++) {
                images.add(Drawable.createFromStream(getActivity().getAssets().open(path + "/" + i + ".png"), null));
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
