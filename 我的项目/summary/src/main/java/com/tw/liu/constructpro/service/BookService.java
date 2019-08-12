package com.tw.liu.constructpro.service;

import com.tw.liu.constructpro.dao.BookDao;
import com.tw.liu.constructpro.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> search(String search){
        return bookDao.search(search);
    }
}
