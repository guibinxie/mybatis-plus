package com.suneee.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class User {
    @IndexField(index = 0)
    private Long id;
    @IndexField(index = 1)
    private String name;
    @IndexField(index = 2)
    private Integer age;
    @IndexField(index = 3)
    private String email;



    }

