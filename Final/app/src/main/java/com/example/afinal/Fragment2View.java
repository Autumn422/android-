package com.example.afinal;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.afinal.data.Record;

import java.util.List;

public class Fragment2View extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<List<Record>> liveData=new MutableLiveData<>();

    public MutableLiveData<List<Record>> getLiveData(){
        return this.liveData;
    }

    private  static Fragment2View INSTANCE=null;
    public static Fragment2View getINSTANCE(Fragment fragment){
        if(INSTANCE==null){
            INSTANCE=new ViewModelProvider(fragment).get(Fragment2View.class);
        }
        return INSTANCE;
    }

    public void setLiveData(List<Record> list){
        getLiveData().setValue(list);
    }
}
