package dao.concretes;

import dao.abstracts.ICustomerDao;
import core.HibernateSession;
import entities.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;

import java.util.List;

public class CustomerDao implements ICustomerDao {

    @Override
    public void save(Customer customer) {
        try (Session session = HibernateSession.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer findById(int id) {
        try (Session session = HibernateSession.getSession()) {
            return session.get(Customer.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Customer customer) {
        try (Session session = HibernateSession.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Customer customer) {
        try (Session session = HibernateSession.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(customer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> findAll() {
        try (Session session = HibernateSession.getSession()) {
            return session.createSelectionQuery("FROM Customer", Customer.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Customer findByMail(String mail) {
        try (Session session = HibernateSession.getSession()) {
            SelectionQuery<Customer> query = session.createSelectionQuery("FROM Customer WHERE mail = :mail", Customer.class);
            query.setParameter("mail", mail);
            return query.getSingleResultOrNull();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}