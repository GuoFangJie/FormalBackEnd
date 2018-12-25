package com.gugu.guguuser.util;

import java.util.ArrayList;

public class SerialUtil {

    private ArrayList<Integer> serialList=new ArrayList<>();

    SerialUtil(ArrayList<Integer> serialList){
        this.serialList=serialList;
    }

    public Integer getSerial(){
        if(serialList.size()==0){
            return 1;
        }
        else{
            for(int i=0;i<serialList.size();i++){
                if(!serialList.get(i).equals(i+1)){
                    return i;
                }
            }
        }
        return serialList.size()+1;
    }

}
