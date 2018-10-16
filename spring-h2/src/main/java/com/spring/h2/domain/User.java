package com.spring.h2.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @Auther: zcx
 * @Date: 2018/10/15 15:50
 * @Description:
 */

@Entity
@Data
@Table(name="t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String url;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
