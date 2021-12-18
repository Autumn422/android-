package com.example.afinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.data.Record;

import java.util.List;


public class Fragment2Adapter extends RecyclerView.Adapter<Fragment2Adapter.MyViewHolder> {
    private List<Record> recordList=null;
    private Fragment2View viewModel;
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
    String[] chaList=new String[] {"荒泷一斗","刻晴","莫娜","七七","迪卢克","琴","五郎","香菱","芭芭拉","九条裟罗","早柚","托马","烟绯","罗莎莉亚","辛焱","砂糖",
            "迪奥娜","重云","诺艾尔","班尼特","菲谢尔","凝光","行秋","北斗","雷泽","弓藏","祭礼弓","绝弦","西风猎弓","糟心",
            "祭礼残章","赌狗乐章","西风秘典","西风长枪","匣里灭辰","雨裁","祭礼大剑","钟剑","西风大剑","匣里龙吟",
            "祭礼剑","笛剑","西风剑","弹弓","神射手之誓","鸦羽弓","翡玉法球","讨龙英杰谭","魔导绪论","黑缨枪","以理服人",
            "沐浴龙血的剑","铁影阔剑","飞天御剑","黎明神剑","冷刃"};
    /*摆放规则：0为当期五星（荒泷一斗），1-5是大家喜闻乐见的常驻五虎（莫娜迪卢克刻晴七七琴，
    6-8是当期Up的三个四星，后面是剩下的四星，后面是3星武器*/

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View item=inflater.inflate(R.layout.singlecard,parent,false);
        return new MyViewHolder(item);
    }

    public Fragment2Adapter(Fragment2View viewModel){
        this.viewModel=viewModel;
        this.recordList=viewModel.getLiveData().getValue();


    }
    public void setList(List<Record> list) {
        this.recordList = list;
    }
    //绑定
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        if(recordList!=null)
        {


            final Record record = recordList.get(position);
            double count=record.getCatchCount();
            holder.singleCount.setText("零命："+String.format("%.2f",count)+"抽");
            double stone=160*count;
            holder.singleStone.setText("零命："+String.format("%.2f",stone)+"原石");
            double rmb=stone*648/8080;
            holder.singleMoney.setText("零命："+String.format("%.2f",rmb)+"RMB");
            holder.fullCount.setText("满命："+String.format("%.2f",7*count)+"抽");
            holder.fullStone.setText("满命："+String.format("%.2f",7*stone)+"原石");
            holder.fullMoney.setText("满命："+String.format("%.2f",7*rmb)+"RMB");
            holder.wishTime.setText("祈愿时间："+record.getWishTime());
            holder.chaName.setText(chaList[record.getUid()]);
            holder.character.setImageResource(idList[record.getUid()]);


        }
    }


    @Override
    public int getItemCount() {
        return recordList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView singleCount,singleStone,singleMoney;  //抽到一个
        TextView fullCount,fullStone,fullMoney;  //满命
        TextView wishTime;  //祈愿时间
        TextView chaName;  //角色名字
        ImageView character;  //角色图片


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            singleCount=itemView.findViewById(R.id.singleCount);
            singleStone=itemView.findViewById(R.id.singleStone);
            singleMoney=itemView.findViewById(R.id.singleMoney);
            fullCount=itemView.findViewById(R.id.fullCount);
            fullStone=itemView.findViewById(R.id.fullStone);
            fullMoney=itemView.findViewById(R.id.fullMoney);
            wishTime=itemView.findViewById(R.id.wishTime);
            chaName=itemView.findViewById(R.id.characterName);
            character=itemView.findViewById(R.id.character);


        }
    }




}