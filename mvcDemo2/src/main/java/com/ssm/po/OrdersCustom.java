package com.ssm.po;

/**
 通过此类映射订单和用户查询的结果，让此类继承包括字段较多的pojo类，
 也就是说，当需要关联表查询时，需要扩展类时，让扩展类继承字段较多的类，这样自己手写的字段就少了
 */
public class OrdersCustom extends Orders {
    //根据sql进行添加需要增加的属性
    /*
    user.username
    user.sex
    user.address
    */
    private String username;
    private String sex;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
