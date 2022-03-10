package com.sso.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data  //getter or setter
@NoArgsConstructor//无参构造器
@AllArgsConstructor//全参构造器
@Accessors(chain = true)// 链式结构
public class User {
    private Integer id;
    private String username;
    private String password;
}
