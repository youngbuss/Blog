package com.yang.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Blog {

    private Long id;
    private String title;
    private String content;
    private String picture;
    private String flag; //标记 原创 转载...
    private Integer views;  //浏览次数
    private boolean appreciation;   //是否赞赏
    private boolean share;  //版权声明开启
    private boolean commentabled;   //评论是否开启
    private boolean published;     //是否发布 还是草稿
    private boolean recommend;     //是否推荐
    private String description;
    private long type; //只有一个对象
    private long user; //一个博客一个用户


    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;





    //一个博客可以有多个标签
    //private List<Tag> tags = new ArrayList<>();



    //一个博客可以有多条评论
    //private List<Comment> comments = new ArrayList<>();

    private String tagids;
}
