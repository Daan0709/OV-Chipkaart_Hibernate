package nl.hu.dp.ovchip;

import nl.hu.dp.ovchip.data.*;
import nl.hu.dp.ovchip.domain.Adres;
import nl.hu.dp.ovchip.domain.OVChipkaart;
import nl.hu.dp.ovchip.domain.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {
//        testFetchAll();
        testDAOHibernate();
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {
        Session session = getSession();
        try {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        } finally {
            session.close();
        }
    }

    private static void testDAOHibernate() {
        System.out.println(" -----TESTING DAOS---- \n");
        Session session = getSession();
        AdresDAOHibernate adao = new AdresDAOHibernate(session);
        ReizigerDAOHibernate rdao = new ReizigerDAOHibernate(session);
        OVChipkaartDAOHibernate odao = new OVChipkaartDAOHibernate(session);
        ProductDAOHibernate pdao = new ProductDAOHibernate(session);

        System.out.println(" -----TESTING REIZIGER DAO---- \n");

        // Create a new traveller to persist into the database
        Reiziger newReiziger = new Reiziger();
        newReiziger.setId(80);
        newReiziger.setVoorletters("N");
        newReiziger.setAchternaam("Reiziger");
        newReiziger.setGeboortedatum(Date.valueOf("2002-12-03"));

        System.out.println("\n All travellers in the database with dob 2002-12-03 before rdao.save():");
        List<Reiziger> reizigersOpGbdatum = rdao.findByGbdatum(Date.valueOf("2002-12-03"));
        for (Reiziger reiziger : reizigersOpGbdatum){
            System.out.println(reiziger);
        }

        rdao.save(newReiziger);

        System.out.println("\n All travellers in the database with dob 2002-12-03 after rdao.save():");
        List<Reiziger> newReizigersOpGbdatum = rdao.findByGbdatum(Date.valueOf("2002-12-03"));
        for (Reiziger reiziger : newReizigersOpGbdatum){
            System.out.println(reiziger);
        }

        System.out.println(" \n-----TESTING ADRES DAO---- \n");

        List<Adres> adressen = adao.findAll();
        System.out.println("All addresses in the database before adao.save():");
        for (Adres adres : adressen){
            System.out.println(adres);
        }
        // Create new address to persist into database
        Adres newAdres = new Adres();
        newAdres.setAdresId(50);
        newAdres.setHuisnummer("501");
        newAdres.setPostcode("9999XX");
        newAdres.setWoonplaats("Leiden");
        newAdres.setStraat("Leidscheweg");
        newAdres.setReiziger(newReiziger);

        // Save the address into the database
        adao.save(newAdres);

        System.out.println("\nAll addresses in the database after adao.save():");
        List<Adres> newAdressen = adao.findAll();
        for (Adres adres : newAdressen){
            System.out.println(adres);
        }

        // Delete the address from the database
        adao.delete(newAdres);

        System.out.println("\nAll addresses in the database after adao.delete():");
        List<Adres> deleteAdressen = adao.findAll();
        for (Adres adres : deleteAdressen){
            System.out.println(adres);
        }

        System.out.println(" \n-----TESTING OVCHIPKAART DAO---- \n");

        System.out.println("Alle OV-Chipkaarten op naam van B. van Rijn vòòr odao.save():");
        Reiziger vanRijn = rdao.findById(2);

        for (OVChipkaart ovChipkaart : odao.findByReiziger(vanRijn)){
            System.out.println(ovChipkaart);
        }

        // Create a new OV-Chipcard to be persisted into the database
        OVChipkaart newOv = new OVChipkaart();
        newOv.setKaartnummer(22222);
        newOv.setKlasse(1);
        newOv.setReiziger(vanRijn);
        newOv.setSaldo(50);
        newOv.setVerloopdatum(Date.valueOf("2025-10-10"));

        // Persist the new ov into the database
        odao.save(newOv);

        System.out.println("\nAll OV-Chipcards of B. van Rijn after odao.save():");
        for (OVChipkaart ovChipkaart : odao.findByReiziger(vanRijn)){
            System.out.println(ovChipkaart);
        }
        odao.delete(newOv);
        newOv.setReiziger(newReiziger);
        odao.save(newOv);

        System.out.println(" \n-----TESTING PRODUCT DAO---- \n");
        System.out.println("All products by OV-Chipcard #35283");
        pdao.findByOVChipkaart(
                        new OVChipkaart(35283,Date.valueOf("2018-05-31"),2,25.50, null)
                ).forEach(System.out::println);

        // Delete all the new objects to maintain database

        odao.delete(newOv);
        rdao.delete(newReiziger);
    }
}
