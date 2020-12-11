package com.example.rpsgame;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    SharedPreferences mySharedPref;
    public Context context;
    public SharedPreferences.Editor editor;

    public SharedPref(Context context){
        this.context = context;
        mySharedPref = context.getSharedPreferences("filename", Context.MODE_PRIVATE);
        editor = mySharedPref.edit();
    }

//    //    this method will save the nightmode State : True or False
//    public void setNightModeState(Boolean state){
//        editor.putBoolean("NightMode", state);
//        editor.commit();
//    }
//
//    //    this method will load the night mode state
//    public Boolean loadNightModeState(){
//        Boolean state = mySharedPref.getBoolean("NightMode", false);
//        return state;
//    }

    public void put_ScoreSet1(int scoreSet1){
        editor.putInt("scoreSet1", scoreSet1);
        editor.commit();
    }

    public int get_ScoreSet1(){
        int scoreSet1 = mySharedPref.getInt("scoreSet1",0);
        return scoreSet1;
    }

    public void put_ScoreSet2(int scoreSet2){
        editor.putInt("scoreSet2", scoreSet2);
        editor.commit();
    }

    public int get_ScoreSet2(){
        int scoreSet2 = mySharedPref.getInt("scoreSet2",0);
        return scoreSet2;
    }
}
