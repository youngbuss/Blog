package com.yang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Type {

    private Long id;
    private String name;

    //一个分类可有多个博客 onetomany
    //private List<Blog> blogs = new ArrayList<>();


}
