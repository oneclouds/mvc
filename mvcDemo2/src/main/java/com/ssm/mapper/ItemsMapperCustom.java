package com.ssm.mapper;

import com.ssm.po.ItemsCustom;
import com.ssm.po.ItemsQueryVo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */
public interface ItemsMapperCustom {
    //商品的查询列表 : 根据ItemsMapperCustom.xml文件中的select的id
    // 即findItemList
    public List<ItemsCustom> findItemList(ItemsQueryVo itemsQueryVo) throws Exception;
}
