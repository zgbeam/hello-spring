package com.smile.dao;

import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

// 名字默认是类名首字母小写
@ToString
@Repository
public class BookDao {
    @Setter
    private String label = "1";
}
