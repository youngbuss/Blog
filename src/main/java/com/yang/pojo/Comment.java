package com.yang.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    private Long id;
    private String nickname; //昵称
    private String email;    //邮箱
    private String content;  //评论内容
    private String avatar;   //头像

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private Long blogid;
    private Long parentcommentid;
    private String parentcommentname;
    private Long rootid;
    private boolean admincomment;

    //private List<Comment> replyComments = new ArrayList<>();

   // private Comment parentComment;

    //private boolean adminComment;

}
