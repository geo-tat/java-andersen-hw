package com.andersen.hw.storage;

import com.andersen.hw.config.SessionFactoryProvider;
import com.andersen.hw.model.Ticket;
import com.andersen.hw.util.IdGenerator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TicketStorageDao implements TicketStorage {
    private final int classId;

    public TicketStorageDao() {
        this.classId = IdGenerator.generateId();
    }


    @Override
    public void addTicket(Ticket ticket) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ticket);
        transaction.commit();
        session.close();
    }

    @Override
    public Ticket getById(Integer id) {
        return SessionFactoryProvider.getSessionFactory().openSession().get(Ticket.class, id);
    }

    @Override
    public List<Ticket> getAll() {
        return SessionFactoryProvider
                .getSessionFactory()
                .openSession()
                .createNativeQuery("SELECT * FROM ticket", Ticket.class)
                .getResultList();
    }

    @Override
    public void deleteById(Integer id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Ticket ticket = session.get(Ticket.class, id);
        if (ticket != null) {
            session.remove(ticket);
        }
        transaction.commit();
    }

    @Override
    public void updateTicket(Ticket ticket) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(ticket);
        transaction.commit();
        session.close();
    }
}
