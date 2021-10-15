package nl.hu.dp.ovchip.data;

import nl.hu.dp.ovchip.domain.OVChipkaart;
import nl.hu.dp.ovchip.domain.Product;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOHibernate extends BaseDAOHibernate implements ProductDAO{

    public ProductDAOHibernate(Session session){
        super(session);
    }

    @Override
    public boolean save(Product product) {
        try {
            return executeInsideTransaction(session -> sess.save(product));
        } catch (Exception e){
            System.out.println("Something went wrong saving the product: ");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try {
            return executeInsideTransaction(session -> sess.update(product));
        } catch (Exception e){
            System.out.println("Something went wrong updating the product: ");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        try {
            return executeInsideTransaction(session -> sess.delete(product));
        } catch (Exception e){
            System.out.println("Something went wrong deleting the product: ");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Product findByProductnummer(int productnummer) {
        try {
            Product product = sess.get(Product.class, productnummer);
            return product;
        } catch (Exception e){
            System.out.println("Something went wrong finding the product by number: ");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) {
        try {
            List<Product> results = sess.createQuery("select pr from Product pr join pr.ovChipkaarten ov where ov.kaartnummer = :kaart_nummer")
                    .setParameter("kaart_nummer", ovChipkaart.getKaartnummer()).list();
            return results;
        } catch (Exception e){
            System.out.println("Something went wrong finding the products by OV-Chipcard: ");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        try {
            List<Product> results = sess.createQuery("from Product").list();
            return results;
        } catch (Exception e){
            System.out.println("Something went wrong finding the products: ");
            System.out.println(e);
            return null;
        }
    }
}
