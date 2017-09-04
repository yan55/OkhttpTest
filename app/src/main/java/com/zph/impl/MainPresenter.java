package com.zph.impl;

import com.zph.business.HttpUntil;
import com.zph.phone.Phone;
import com.zph.view.MvpMainView;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by apple on 17-8-11.
 */

public class MainPresenter extends BasePresenter {

    String mUrl = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm";
    MvpMainView mvpMainView;
    Phone mPhone;

    public MainPresenter(MvpMainView mainView)
    {
        mvpMainView = mainView;
    }


    public Phone getPhoneInfo()
    {
        return mPhone;
    }
    public void searchPhoneInfo(String phone)
    {
        if(!isMobile(phone))
        {
            mvpMainView.showToast("请输入正确的手机号！");
            return;
        }
        mvpMainView.showLoading();
        //http请求的处理逻辑
        sendHttp(phone);
    }

    public static boolean isMobile(String str) {
        Pattern p = Pattern.compile("^((134[0-9])|(135[0-9])|(136[0-9])|" +
                "(137[0-9])|(138[0-9])|(139[0-9])|" +
                "(150[0-9])|(151[0-9])|(152[0-9])|" +
                "(157[0-9])|(158[0-9])|(159[0-9])|" +
                "(181[0-9])|(182[0-9])|(183[0-9])|" +
                "(187[0-9])(188[0-9])|" +
                "(130[0-9])|(131[0-9])|(132[0-9])|" +
                "(155[0-9])|(156[0-9])|(185[0-9])|" +
                "(186[0-9])|(133[0-9])|(153[0-9])|" +
                "(180[0-9])|(189[0-9])|)\\d{7}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }



    private  void sendHttp(String phone)
    {
        Map<String,String>map = new HashMap<String, String>();
        map.put("tel",phone);
        HttpUntil httpUntil = new HttpUntil(new HttpUntil.HttpResponse() {
            @Override
            public void onSuccess(Object object) {

                System.out.print("str is:"+object);
                String json = object.toString();
                System.out.print("json is:"+json);
                int index   = json.indexOf("{");
                json = json.substring(index,json.length());

                //JSONObject
                mPhone = parseModelWithOrgJson(json);
                mvpMainView.hideLoading();
                mvpMainView.updateView();

            }

            @Override
            public void onFail(String error) {
                mvpMainView.showToast(error);
                mvpMainView.hideLoading();
            }
        });
        httpUntil.sendGetHttp(mUrl,map);
    }

    private Phone parseModelWithOrgJson(String json)
    {

        Phone phone = new Phone();
        try {
            org.json.JSONObject jsonObject = new org.json.JSONObject(json);

            String value = jsonObject.getString("telString");
            phone.setPhone(value);

            value = jsonObject.getString("province");
            phone.setProvince(value);

            value = jsonObject.getString("catName");
            phone.setType(value);

            value = jsonObject.getString("carrier");
            phone.setCarrier(value);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return phone;
    }

}
