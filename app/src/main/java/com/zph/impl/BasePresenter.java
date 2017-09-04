package com.zph.impl;

import android.content.Context;

/**
 * Created by apple on 17-8-11.
 */

public class BasePresenter {

    Context mContext;
    public void attach(Context context){

        mContext = context;
    }
    public void onPause(){};
    public void onResume(){};
    public void onDestroy(){

        mContext = null;
    }

}
