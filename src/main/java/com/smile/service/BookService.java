package com.smile.service;

import com.smile.dao.BookDao;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@ToString
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;
}
