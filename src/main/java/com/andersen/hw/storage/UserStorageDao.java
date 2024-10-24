package com.andersen.hw.storage;

import com.andersen.hw.config.SessionFactoryProvider;
import com.andersen.hw.model.User;
import com.andersen.hw.util.IdGenerator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserStorageDao implements UserStorage {
    private final int classId;

    public UserStorageDao() {
        this.classId = IdGenerator.generateId();
    }


    @Override
    public void addUser(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
    }

    @Override
    public User getById(Integer id) {
        return SessionFactoryProvider.getSessionFactory().openSession().get(User.class, id);
    }

    @Override
    public List<User> getAll() {

        return SessionFactoryProvider
                .getSessionFactory()
                .openSession()
                .createNativeQuery("SELECT * FROM user_info", User.class)
                .getResultList();
    }

    @Override
    public void deleteById(Integer id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        if (user != null) {
            session.remove(user);
        }
        transaction.commit();
    }

    @Override
    public void updateUser(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
        session.close();
    }
}
