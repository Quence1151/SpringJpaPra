package com.springjpapra.controller;

import com.springjpapra.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateItem() throws Exception {
        Book book = em.find(Book.class, 1L);

        //TX
        book.setName("asdf");

        //변경감지 == dirty checking
        //TX commit

    }

}