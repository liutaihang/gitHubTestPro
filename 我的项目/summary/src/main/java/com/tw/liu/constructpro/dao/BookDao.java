package com.tw.liu.constructpro.dao;

import com.tw.liu.constructpro.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookDao extends JpaRepository<Book, String> {

//    @Modifying
    @Query(value = "SELECT id, book_name as value, book_no FROM book_stock WHERE book_name like'%生%'", nativeQuery = true)
    List<Book> search(String value);
}
