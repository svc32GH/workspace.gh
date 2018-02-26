package com.svc32.ocpj8se.ch1;

/**
 * Created by Sergey Chudakov [svc32.simai@gmail.com] on 28.12.2017.
 **/
public class Book {
    private int ISBN;

    public int hashCode() {
        return ISBN;
    }

    @Override
    public boolean equals(Object obj) {
        if ( !(obj instanceof Book))
            return false;
        Book other = (Book) obj;
        return this.ISBN == other.ISBN;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

}
