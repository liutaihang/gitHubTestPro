package com.tw.liu.constructpro.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_stock")
@Data
public class Book {
    @Id
    private String id;
    @Column(name = "value")
    private String value;
    @Column(name = "book_no")
    private String bookNo;

    public Book(String id, String value, String bookNo) {
        this.id = id;
        this.value = value;
        this.bookNo = bookNo;
    }

    public Book() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"value\":\"")
                .append(value).append('\"');
        sb.append(",\"bookNo\":\"")
                .append(bookNo).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
