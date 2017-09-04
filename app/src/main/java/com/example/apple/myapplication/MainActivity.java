package com.example.apple.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zph.impl.MainPresenter;
import com.zph.phone.Phone;
import com.zph.view.MvpMainView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,MvpMainView{


    EditText input_phone;
    Button btn_search;
    TextView result_phone;
    TextView result_provice;
    TextView result_boss;
    TextView result_bossprovice;
    MainPresenter mainPresenter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input_phone = (EditText) findViewById(R.id.input_phone);

        result_phone = (TextView) findViewById(R.id.result_phone);
        result_provice = (TextView) findViewById(R.id.result_provice);
        result_boss = (TextView) findViewById(R.id.result_Type);
        result_bossprovice = (TextView) findViewById(R.id.result_carrier);
        btn_search = (Button)findViewById(R.id.btn_search);

        btn_search.setOnClickListener(this);

        mainPresenter = new MainPresenter(this);
        mainPresenter.attach(this);
    }

    public void onClick(View view) {

        mainPresenter.searchPhoneInfo(input_phone.getText().toString());
    }
    //mainmvpview接口的方法
    public void showLoading() {

        if(progressDialog == null)
        {
            progressDialog = ProgressDialog.show(this,"","正在加载...",true,false);
        }else  if(progressDialog.isShowing())
        {
            progressDialog.setTitle("");
            progressDialog.setMessage("正在加载...");

        }
        progressDialog.show();
    }


    public void hideLoading() {

        if(progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
    }


    public void showToast(String msg) {

        Toast.makeText(this,msg, Toast.LENGTH_LONG).show();
    }


    public void updateView() {

        Phone phone = mainPresenter.getPhoneInfo();
        result_phone.setText("手机号码："+phone.getPhone().toString());
        result_provice.setText("省份："+phone.getProvince().toString());
        result_boss.setText("运营商："+phone.getType().toString());
        result_bossprovice.setText("运营商归属地："+phone.getCarrier().toString());
    }



}
