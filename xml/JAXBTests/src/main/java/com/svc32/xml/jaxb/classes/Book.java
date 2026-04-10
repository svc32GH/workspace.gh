package com.svc32.xml.jaxb.classes;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.Date;

@XmlRootElement(name = "book")
@XmlType(propOrder = { "id", "name", "date" })
public class Book {
    private Long id;
    private String name;
    private String author;
    private Date date;

    public Long getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "title")
    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    @XmlTransient
    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setDate(Date date) {
        this.date = date;
    }

    public String toString() {
        return "{"
                +"id: " + id
                +", name: " + name
                +", author: " + author
                +", date: " + getDate()
        +"}";
    }

//    private KeyStore keyStore(String file, char[] password) throws Exception {
//        KeyStore keyStore = KeyStore.getInstance("PKCS12");
//        File key = ResourceUtils.getFile(file);
//        try (InputStream in = new FileInputStream(key)) {
//            keyStore.load(in, password);
//        }
//        return keyStore;
//    }
}
