package com.example.afinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.data.Record;
import com.example.afinal.service.Service;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {
    private Fragment2View mViewModel;
    private Service service;
    private Button clearRecord;
    private RecyclerView recyclerView;
    private Fragment2Adapter adapter;
    private boolean isGetData = false;
    List<Record> list=new ArrayList<>();
    int[] idList=new int[] {R.drawable.itto,R.drawable.keqing,R.drawable.mona,R.drawable.qiqi,R.drawable.diluc,
            R.drawable.jean,R.drawable.gorou,R.drawable.xiangling,R.drawable.barbara,R.drawable.jiutiao,R.drawable.sayou,
            R.drawable.toma,R.drawable.yanfei,R.drawable.luoshaliya,R.drawable.xinyan,R.drawable.shatang,R.drawable.diona,
            R.drawable.chongyun,R.drawable.nvpu,R.drawable.dianzange,R.drawable.feixieer,R.drawable.ningguang,R.drawable.xingqiu,
            R.drawable.beidou,R.drawable.razor,R.drawable.gongcang,R.drawable.jiligong,R.drawable.juexian,R.drawable.xifengliegong,R.drawable.zaoxin,
            R.drawable.jilicanzhang,R.drawable.dugouyuezhang,R.drawable.xifengmidian,R.drawable.xifengchangqiang,R.drawable.xialimiechen,
            R.drawable.yucai,R.drawable.jilidajian,R.drawable.zhongjian,R.drawable.xifengdajian,R.drawable.xialilonogyin,
            R.drawable.jilidajian,R.drawable.dijian,R.drawable.xifengjian,R.drawable.dangong,R.drawable.shensheshou,R.drawable.yayugong,
            R.drawable.cuiyufaqiu,R.drawable.taolongyingjietan,R.drawable.modaoxulun,R.drawable.heiyingqiang,R.drawable.yilifuren,
            R.drawable.longxuejian,R.drawable.tieyingkuojian,R.drawable.feitianyujian,R.drawable.limingshenjian,R.drawable.lengren};
    /*摆放规则：0为当期五星（荒泷一斗），1-5是大家喜闻乐见的常驻五虎（莫娜迪卢克刻晴七七琴，
    6-8是当期Up的三个四星，后面是剩下的四星，后面是3星武器*/

    public static Fragment2 newInstance() {
        return new Fragment2();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.history, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        clearRecord=getActivity().findViewById(R.id.clearRecord);
        clearRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service=new Service(getContext());
                service.clearWish();
                onResume();
                setData();
            }
        });
        setData();

    }

    @Override
    public void onResume() {
        if(!isGetData)
        {
            setData();
            isGetData=true;
        }
        super.onResume();


    }
    @Override
    public void onPause() {
        super.onPause();
        //跟随生命周期
        isGetData = false;
    }

    @Nullable
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //   进入当前Fragment
        if (enter && !isGetData) {
            isGetData = true;
            //   这里可以做网络请求或者需要的数据刷新操作 但不在这做 在onResume里
            setData();
        } else {
            isGetData = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    public void setData()
    {
        service=new Service(getContext());
        this.list=service.showAllRecord();
        mViewModel = Fragment2View.getINSTANCE(this);
        mViewModel.setLiveData(this.list);
        recyclerView =requireActivity().findViewById(R.id.listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapter=new Fragment2Adapter(mViewModel);



        recyclerView.setAdapter(adapter);
        mViewModel.getLiveData().observe(requireActivity(), new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> records) {
                adapter.setList(records);
                adapter.notifyDataSetChanged();
            }
        });


    }

}
