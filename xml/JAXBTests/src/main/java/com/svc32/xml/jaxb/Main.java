package com.svc32.xml.jaxb;

import com.svc32.xml.jaxb.classes.Book;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try {
            marshal();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void marshal() throws JAXBException, IOException {
        Book book = new Book();
        book.setId(1L);
        book.setName("Book1");
        book.setAuthor("Author1");
        book.setDate(new Date());

        JAXBContext context = JAXBContext.newInstance(Book.class);
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(book, new File("./book.xml"));

        Book book2 = unmarshall();
        System.out.println("book2 = " + book2);
    }

    public Book unmarshall() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Book.class);
        return (Book) context.createUnmarshaller()
                .unmarshal(new FileReader("./book.xml"));
    }
}
