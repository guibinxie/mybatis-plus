package com.suneee.service;

import com.suneee.dynamicDataSource.TargetDataSource;
import com.suneee.mapper.ItemMapper;
import com.suneee.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate ;




    @Autowired
    private ItemMapper itemMapper;

    @Cacheable(cacheNames = "item",key = "{#key}")
    @TargetDataSource(name = "ds2")
    public List<Item> findAll(String key){
        int age =20;
        return  itemMapper.selectList(null);
    }
    @TargetDataSource(name = "ds2")
    public int save() {
        Item item = new Item();
        item.setName("修文");
        int insert = itemMapper.insert(item);
        if(insert ==1){
            redisTemplate.opsForValue().set("item::"+item.getId(),item);
        }
        return  insert;
    }
    @TargetDataSource(name = "ds2")
    @Cacheable(cacheNames = "cluster",key = "{#key}")
    public  String setRedisKey(String key){
        return key;
    }

    public Object getRedisKey(String key) {
         Object o= null;
         o = redisTemplate.opsForValue().get("item::haowuliao");
        return  o;
    }

    @TargetDataSource(name = "ds2")
    public Object getItem(String key) {
        Object o= null;
        o = redisTemplate.opsForValue().get("item::"+key);
        return  o;
    }

}
