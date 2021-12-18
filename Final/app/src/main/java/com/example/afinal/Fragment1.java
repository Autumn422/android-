package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.afinal.data.Record;
import com.example.afinal.service.Service;

import java.util.ArrayList;
import java.util.List;

import java.text.SimpleDateFormat;
import java.util.Random;

public class Fragment1 extends Fragment {
    private Menu mymenu;
    private Service service;
    List<String> five=new ArrayList<>();  //存放抽到的五星
    List<Integer> fiveNumber=new ArrayList<>();  //存放抽到的五星抽了几发
    List<Integer> fourNumber=new ArrayList<>();  //存放抽到的五星抽了几发
    List<String> four=new ArrayList<>();  //存放抽到的四星

    List<Integer> fiveid=new ArrayList<>(); //以uid的方式存放五星四星id，好转成图片
    List<Integer> fourid=new ArrayList<>();

    ImageView[] showCard=new ImageView[10];
    /*不会吧不会吧，不会还有人想统计自己抽到了多少本讨龙英杰谭和多少杆黑缨枪吧，
    这种就不存起来了，玩到世界8包里都一堆了，没啥意义。
    更别提那些翠玉法球，沐浴龙血的剑这种根本没人用的东西*/
    //先搞一个模板数组，后面搬着改
    String[] cardList=new String[] {"荒泷一斗","刻晴","莫娜","七七","迪卢克","琴"};  //6
    String[] fourlist=new String[] {"五郎","香菱","芭芭拉","九条裟罗","早柚","托马","烟绯","罗莎莉亚","辛焱","砂糖",
            "迪奥娜","重云","诺艾尔","班尼特","菲谢尔","凝光","行秋","北斗","雷泽","弓藏","祭礼弓","绝弦","西风猎弓","糟心",
            "祭礼残章","赌狗乐章","西风秘典","西风长枪","匣里灭辰","雨裁","祭礼大剑","钟剑","西风大剑","匣里龙吟",
            "祭礼剑","笛剑","西风剑"};  //37
    //三星武器
    String[] threelist=new String[] {"弹弓","神射手之誓","鸦羽弓","翡玉法球","讨龙英杰谭","魔导绪论","黑缨枪","以理服人",
            "沐浴龙血的剑","铁影阔剑","飞天御剑","黎明神剑","冷刃"};  //13
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


    int cardCount=0;  //抽了多少次
    int fivecount=0;  //统计抽到五星数目
    int fourcount=0;  //统计抽到四星数目
    int nofive=0;  //累计未抽到五星数目
    int nofour=0; //累计未抽到四星数目
    int isUp=0;  //看歪没有
    int isUp4=0;  //是否是up四星

    TextView wishCount,noFive,noFour,AdditionalFive,fourStar;



    public static Fragment1 newInstance() {
        return new Fragment1();
    }
    public Fragment1()
    {
        setHasOptionsMenu(true);
    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        //添加右上角菜单
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.change_wish,menu);
//
//        mymenu=menu;                //变量全局化
//
//
//    }
//
//
//
//
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.saveWish:
//                service=new Service(getContext());
//                //算五星一个要多少抽，存起来
//                int next=-1;
//                int count=0;
//                int num=0;
//                for(int i=0;i<fiveid.size();i++)
//                {
//                    if(fiveid.get(i)==0)  //一斗
//                    {
//                        for(int j=next+1;j<=i;j++)
//                        {
//                            count+=fiveNumber.get(j);
//                        }
//                        next=i;
//                        num+=1;
//                    }
//                }
//                double oneCount=count/num;
//                Record record=new Record();
//                record.setUid(0);
//                record.setCatchCount(oneCount);
//                service.insertRecord(record);
//
//
//                break;
//            case R.id.resetWish:
//                resetWish();
//                break;
//
//            default:break;
//        }
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_card, container, false);
    }

    //添加单抽和十连的事件
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ImageView dan_chou=getActivity().findViewById(R.id.danchou);
        wishCount=getActivity().findViewById(R.id.wishcount);
        noFive=getActivity().findViewById(R.id.nofivestar);
        noFour=getActivity().findViewById(R.id.nofourstar);
        AdditionalFive=getActivity().findViewById(R.id.fivestar);
        fourStar=getActivity().findViewById(R.id.fourstar);
        ImageView myMenu1=getActivity().findViewById(R.id.mymenu1);



        showView();

        showCard[0]=getActivity().findViewById(R.id.card1);
        showCard[1]=getActivity().findViewById(R.id.card2);
        showCard[2]=getActivity().findViewById(R.id.card3);
        showCard[3]=getActivity().findViewById(R.id.card4);
        showCard[4]=getActivity().findViewById(R.id.card5);
        showCard[5]=getActivity().findViewById(R.id.card6);
        showCard[6]=getActivity().findViewById(R.id.card7);
        showCard[7]=getActivity().findViewById(R.id.card8);
        showCard[8]=getActivity().findViewById(R.id.card9);
        showCard[9]=getActivity().findViewById(R.id.card10);

    for(int i=0;i<10;i++)
    {
        showCard[i].setImageBitmap(null);
    }

        myMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(getContext(),view);
                popupMenu.getMenuInflater().inflate(R.menu.change_wish,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.saveWish:
                                storeWish(0,0);
                                storeWish(6,1);
                                storeWish(7,1);
                                storeWish(8,1);



                                break;
                            case R.id.resetWish:
                                resetWish();
                                break;

                            default:break;
                        }
                        return true;
                    }
                });
                popupMenu.show();

            }
        });




        dan_chou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWish(0);
                for(int i=1;i<10;i++)
                {
                    showCard[i].setImageBitmap(null);
                }
                System.out.println(cardList[0]);
            }
        });
        ImageView shi_lian=getActivity().findViewById(R.id.shilian);
        shi_lian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWishTen();
                System.out.println(cardList[1]);
            }
        });
        super.onActivityCreated(savedInstanceState);

    }





    public void getWish(int i)  //单抽函数
    {
        cardCount+=1;
        nofour+=1;
        nofive+=1;
        double percent=Math.random();
        System.out.println(percent);
        if(this.nofive<=73){
            if(percent<0.006)
            {
                isFive(i);
            }
            else{
                getFour(i);

            }

        }
        else{
            int addition=nofive-73;  //比73多了多少抽
            if(percent<(0.006+0.06*addition))
            {
                isFive(i);
            }
            else{
                getFour(i);
            }

        }

        showView();

    }

    public void getWishTen()  //十连函数
    {
        for(int i=0;i<10;i++)
        {
            getWish(i);  //用十次单抽，我懒
        }
    }
    public void showView()
    {
        String cardall="抽卡总数: "+String.valueOf(this.cardCount);
        String nof="已累计 "+String.valueOf(this.nofive)+" 抽未出五星";
        String nofo="已累计 "+String.valueOf(this.nofour)+" 抽未出四星";
        this.wishCount.setText(cardall);
        this.noFive.setText(nof);
        this.noFour.setText(nofo);

    }
    public void isFive(int i)
    {

        System.out.println("五星");
        //小保底
        if(isUp==0)
        {
            double wai=Math.random();  //计算歪没有
            if(wai<0.5)
            {

                five.add("荒泷一斗");
                fiveNumber.add(nofive);
                fiveid.add(0);
                showImage(i,0);
                isUp=0;
            }
            //歪了，出常驻五虎
            else if(wai<=0.6)
            {

                five.add("刻晴");
                fiveNumber.add(nofive);
                fiveid.add(1);
                showImage(i,1);
                isUp+=1;
            }
            else if(wai<=0.7)
            {

                five.add("莫娜");
                fiveNumber.add(nofive);
                fiveid.add(2);
                showImage(i,2);
                isUp+=1;
            }
            else if(wai<=0.8)
            {

                five.add("七七");
                fiveNumber.add(nofive);
                showImage(i,3);
                fiveid.add(3);
                isUp+=1;
            }
            else if(wai<=0.9)
            {

                five.add("迪卢克");
                fiveNumber.add(nofive);
                showImage(i,4);
                fiveid.add(4);
                isUp+=1;
            }
            else
            {

                five.add("琴");
                fiveNumber.add(nofive);
                showImage(i,5);
                fiveid.add(5);
                isUp+=1;
            }

        }
        //大保底
        else{

            five.add("荒泷一斗");
            fiveNumber.add(nofive);
            showImage(i,0);
            fiveid.add(0);
            isUp=0;

        }
        showAddiFive();
        nofive=0;  //出货，五星从0开始计算
    }

    public void showAddiFive()
    {
        String s="累计五星： ";
        for(int i=0;i<five.size();i++)
        {
            s=s+five.get(i)+"["+String.valueOf(fiveNumber.get(i))+"] ";
        }
        AdditionalFive.setText(s);
    }


    public void getFour(int i)
    {
        double fo=Math.random();
        if(nofour<=8)
        {
            if(fo<0.051)
            {
                System.out.println("四星");
                isFour(i);
                nofour=0;
            }
            else
            {
                getThree(i);
            }
        }
        else if(nofour==9)
        {
            if(fo<0.561)
            {
                System.out.println("四星");
                isFour(i);
                nofour=0;
            }
            else
            {
                getThree(i);
            }
        }
        else
        {
            System.out.println("四星");
            isFour(i);
            nofour=0;
        }

    }

    public void isFour(int i)  //歪不歪
    {
        if(isUp4==1)
        {
            double fo=Math.random();

            if(fo<0.33333333333333333333)
            {
                four.add("五郎");
                showImage(i,6);
                fourid.add(6);
                fourNumber.add(nofour);
            }
            else if(fo<0.66666666666666666666)
            {
                four.add("香菱");
                showImage(i,7);
                fourid.add(7);
                fourNumber.add(nofour);
            }
            else{
                four.add("芭芭拉");
                showImage(i,8);
                fourid.add(8);
                fourNumber.add(nofour);
            }
            isUp4=0;
        }
        else
        {
            double fo2=Math.random();

            if(fo2<0.5)  //没歪
            {
                double fo4=Math.random();

                if(fo4<0.33333333333333333333)
                {
                    four.add("五郎");
                    showImage(i,6);
                    fourid.add(6);
                    fourNumber.add(nofour);
                }
                else if(fo4<0.66666666666666666666)
                {
                    four.add("香菱");
                    showImage(i,7);
                    fourid.add(7);
                    fourNumber.add(nofour);
                }
                else{
                    four.add("芭芭拉");
                    showImage(i,8);
                    fourid.add(8);
                    fourNumber.add(nofour);
                }
                isUp4=0;
            }
            else{
                Random rand=new Random();
                int temp=rand.nextInt(fourlist.length-3);
                four.add(fourlist[temp+3]);
                fourid.add(temp+9);
                fourNumber.add(nofour);
                showImage(i,temp+9);
                isUp4=1;
            }
        }
        showAddiFour();
    }

    public void showAddiFour()
    {
        String s="累计四星： ";
        for(int i=0;i<four.size();i++)
        {
            s=s+four.get(i)+" ";
        }
        fourStar.setText(s);

    }

    //蓝天白云，再接再厉
    public void getThree(int i)
    {
        Random rand=new Random();
        int temp=rand.nextInt(threelist.length);
        String result=threelist[temp];
        showImage(i,43+temp);
    }

    public void showImage(int place,int p)  //根据抽到的物品展示图片
    {
        showCard[place].setImageResource(idList[p]);
    }

    public void resetWish()
    {
        cardCount=0;  //抽了多少次
        fivecount=0;  //统计抽到五星数目
        fourcount=0;  //统计抽到四星数目
        nofive=0;  //累计未抽到五星数目
        nofour=0; //累计未抽到四星数目
        isUp=0;  //看歪没有
        isUp4=0;  //是否是up四星
        five=new ArrayList<>();  //存放抽到的五星
        fiveNumber=new ArrayList<>();  //存放抽到的五星抽了几发
        four=new ArrayList<>();  //存放抽到的四星
        fourNumber=new ArrayList<>();
        fourid=new ArrayList<>();
        fiveid=new ArrayList<>();
        showView();
        showAddiFive();
        showAddiFour();
        for(int i=0;i<10;i++)
        {
            showCard[i].setImageBitmap(null);
        }
    }

    public void storeWish(int uid,int type)
    {
        service=new Service(getContext());
        //算五星一个要多少抽，存起来
        int next=-1;
        int count=0;
        int num=0;
        if(type==0)  //五星
        {
            for(int i=0;i<fiveid.size();i++)
            {
                if(fiveid.get(i)==uid)  //一斗
                {
                    for(int j=next+1;j<=i;j++)
                    {
                        count+=fiveNumber.get(j);
                    }
                    next=i;
                    num+=1;
                }
            }
        }
        else{
            for(int i=0;i<fourid.size();i++)
            {
                if(fourid.get(i)==uid)  //一斗
                {
                    for(int j=next+1;j<=i;j++)
                    {
                        count+=fourNumber.get(j);
                    }
                    next=i;
                    num+=1;
                }
            }

        }

        if(num!=0)
        {
            double oneCount=count/num;
            Record record=new Record();
            record.setUid(uid);
            record.setCatchCount(oneCount);
            service.insertRecord(record);
        }

    }




}
