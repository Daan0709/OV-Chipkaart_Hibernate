package nl.hu.dp.ovchip.data;

import nl.hu.dp.ovchip.domain.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

public class ReizigerDAOHibernate extends BaseDAOHibernate implements ReizigerDAO{

    public ReizigerDAOHibernate(Session session){
        super(session);
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try {
            return executeInsideTransaction(session -> sess.save(reiziger));
        } catch (Exception e){
            System.out.println("Something went wrong saving the traveller: ");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            return executeInsideTransaction(session -> sess.update(reiziger));
        } catch (Exception e){
            System.out.println("Something went wrong updating the traveller: ");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            return executeInsideTransaction(session -> sess.delete(reiziger));
        } catch (Exception e){
            System.out.println("Something went wrong deleting the traveller: ");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        try {
            Reiziger reiziger = sess.get(Reiziger.class, id);
            return reiziger;
        } catch (Exception e){
            System.out.println("Something went wrong finding the traveller: ");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Reiziger> findByGbdatum(Date datum) {
        try {
            List<Reiziger> results = sess.createQuery("from Reiziger where geboortedatum = :gbdatum").setParameter("gbdatum", datum).list();
            return results;
        } catch (Exception e){
            System.out.println("Something went wrong finding the travellers by birthdate: ");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        try {
            List<Reiziger> results = sess.createQuery("from Reiziger").list();
            return results;
        } catch (Exception e){
            System.out.println("Something went wrong finding the travellers: ");
            System.out.println(e);
            return null;
        }
    }
}
