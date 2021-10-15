package nl.hu.dp.ovchip.data;

import nl.hu.dp.ovchip.domain.Adres;
import nl.hu.dp.ovchip.domain.Reiziger;
import org.hibernate.Session;

import java.util.List;

public class AdresDAOHibernate extends BaseDAOHibernate implements AdresDAO{

    public AdresDAOHibernate(Session session){
        super(session);
    }

    @Override
    public boolean save(Adres adres) {
        try {
            return executeInsideTransaction(session -> sess.save(adres));
        } catch (Exception e){
            System.out.println("Something went wrong saving the address: ");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        try {
            return executeInsideTransaction(session -> sess.update(adres));
        } catch (Exception e){
            System.out.println("Something went wrong updating the address: ");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        try {
            return executeInsideTransaction(session -> sess.delete(adres));
        } catch (Exception e){
            System.out.println("Something went wrong deleting the address: ");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        try {
            Adres adres = sess.get(Adres.class, reiziger.getId());
            return adres;
        } catch (Exception e){
            System.out.println("Something went wrong finding the address by traveller: ");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Adres> findAll() {
        try {
            List<Adres> results = sess.createQuery("from Adres").list();
            return results;
        } catch (Exception e){
            System.out.println("Something went wrong finding all the addresses: ");
            System.out.println(e);
            return null;
        }
    }
}
