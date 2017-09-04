package com.zph.phone;

/**
 * Created by apple on 17-8-11.
 */
/*
*           MVP中的 modle层
**/
public class Phone {

    String phone;       //查询号码
    String province;    //手机地址
    String type;         //运营商
    String carrier;     //运营商归属地

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
}

