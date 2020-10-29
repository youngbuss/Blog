package com.yang.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserQuery {
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar; //头像
    private Integer type;
}
