package com.suneee.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suneee.model.Item;

import java.util.List;

public interface ItemMapper  extends BaseMapper<Item> {

    List<Item> selectList(Wrapper<Item> queryWrapper);

    int insert(Item item);
}
