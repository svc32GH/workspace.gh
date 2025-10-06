package com.svc32.common.hiber.demo;

import com.svc32.common.hiber.demo.com.svc32.common.hiber.HibernateUtil;
import com.svc32.common.hiber.demo.com.svc32.common.hiber.UserInfo;
import org.hibernate.Session;

public class HibernateDemo {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        try {
            UserInfo user = new UserInfo("Alice", "alice@email.com");
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();

            System.out.println("User saved with ID = " + user.getId());
        } finally {
            HibernateUtil.closeSession();
        }


        System.out.println("Initializing Hibernate...");

    }
}
