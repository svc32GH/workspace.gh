package com.svc32.ocpj8se.ch1;

import org.junit.Test;

/**
 * Created by Sergey Chudakov [svc32.simai@gmail.com] on 28.12.2017.
 **/
public class TestCh1 {

    @Test
    public void testBook() {
        Book book = new Book();
        System.out.println("book      = " + book);
        System.out.println("book.ISBN = " + book.getISBN());
    }

    @Test
    public void testEnum() {

    }
}
