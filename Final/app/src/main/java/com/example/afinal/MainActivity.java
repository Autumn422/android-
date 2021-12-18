package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Window;


import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class  MainActivity extends AppCompatActivity {
    List<String> titles=new ArrayList<>();
    List<Fragment> fragments=new ArrayList<>();
    private ViewPager2 vp;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);





        vp=findViewById(R.id.fragment);

        fragments.add(new Fragment1());
        fragments.add(new Fragment2());


        //实例化适配器
        FragmentAdapter myAdapter=new FragmentAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
        //设置适配器
        vp.setAdapter(myAdapter);

        //TabLayout和Viewpager2进行关联
        //new TabLayoutMediator(tab, vp, (tab, position) -> tab.setText(titles.get(position))).attach();

    }
}