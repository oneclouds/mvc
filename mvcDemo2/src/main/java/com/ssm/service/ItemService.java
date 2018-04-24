package com.ssm.service;

import com.ssm.po.ItemsCustom;
import com.ssm.po.ItemsQueryVo;

import java.util.List;

//这里是service层：业务层
public interface ItemService {
    // 商品列表查询
    public  List<ItemsCustom> findItemList(ItemsQueryVo itemsQueryVo) throws Exception;

    //    根据id查询商品信息:这里使用扩展类(对返回的类进行扩展)，
    // 为了为扩展性方便，通常是返回扩展类，这样对呈现到页面的数据会更好处理，更加灵活
    public ItemsCustom findItemsById(Integer id) throws Exception;

    //    修改商品信息:也是使用扩展类进行操作
    public void updateItems(Integer id ,ItemsCustom itemsCustom) throws Exception;

}
