package com.suneee.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("item")
public class Item {
    @IndexField(index = 0)
    private long id;
    @IndexField(index = 1)
    private String name;
}
