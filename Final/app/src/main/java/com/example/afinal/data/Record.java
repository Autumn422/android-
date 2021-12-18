package com.example.afinal.data;


//抽卡记录
public class Record {
    private String rid;
    private int uid;  //记录角色id
    private double catchCount;  //记录出一个的抽数
    private String wishTime;

    public String getRid() {
        return rid;
    }

    public double getCatchCount() {
        return catchCount;
    }

    public int getUid() {
        return uid;
    }

    public String getWishTime() {
        return wishTime;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public void setCatchCount(double catchCount) {
        this.catchCount = catchCount;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setWishTime(String wishTime) {
        this.wishTime = wishTime;
    }
}
