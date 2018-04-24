package com.ssm.serviceImp;

import com.ssm.mapper.ItemsMapper;
import com.ssm.mapper.ItemsMapperCustom;
import com.ssm.po.Items;
import com.ssm.po.ItemsCustom;
import com.ssm.po.ItemsQueryVo;
import com.ssm.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */
@Service
public class ItemServiceImpl implements ItemService {


    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public ItemsCustom findItemsById(Integer id) throws Exception {

        Items items = itemsMapper.selectByPrimaryKey(id);
//中间对商品信息进行业务处理
//       ....
//        返回ItemsCustom
        ItemsCustom itemsCustom = null;
        if (itemsCustom!=null) {
            itemsCustom=new ItemsCustom();
//        将items的属性值 拷贝到itemsCustom
        BeanUtils.copyProperties(items, itemsCustom);
        }
        return itemsCustom;
    }

    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
//添加业务校验，通常在在service接口对关键字进行校验
//        交易id是否为空，如果为空则抛出异常
//
//        更新商品信息使用updateByPrimaryWithBlobs根据id更新items表中的所有字段，包括大文本类型字段
//        updateByPrimaryWithBlobs要求必须转入id
        itemsCustom.setId(id);
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
    }

    //    这里使用自动注入的方式，因为在ApplicationContext.xml中使用自动扫描的形式，
//    ItemsMapperCustom.java和ItemsMapperCustom.xml加载进spring的容器中了，
// 所以这里直接使用自动注入形式
    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    @Override
    public List<ItemsCustom> findItemList(ItemsQueryVo itemsQueryVo) throws Exception {
//        通过ItemsMapperCustom查询数据库
        return itemsMapperCustom.findItemList(itemsQueryVo);

    }
}
