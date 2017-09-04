package com.zph.view;

/**
 * Created by apple on 17-8-11.
 */

public interface MvpMainView extends MvpLoadingView {

    void showToast(String msg);
    void updateView();


}
