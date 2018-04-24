package com.ssm.po;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */

//这里是用ItemQueryVo作为Items的包装对象
public class ItemsQueryVo  {
    //商品信息
    private Items items;

    //为了系统可扩展性，对原始生成的po进行扩展
    private ItemsCustom itemsCustom;

    //    批量商品信息
    private List<ItemsCustom> itemsCustomList;


    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public ItemsCustom getItemsCustom() {
        return itemsCustom;
    }

    public void setItemsCustom(ItemsCustom itemsCustom) {
        this.itemsCustom = itemsCustom;
    }
}
