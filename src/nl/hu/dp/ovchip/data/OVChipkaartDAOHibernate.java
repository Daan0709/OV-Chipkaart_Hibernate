package nl.hu.dp.ovchip.data;

import nl.hu.dp.ovchip.domain.OVChipkaart;
import nl.hu.dp.ovchip.domain.Reiziger;
import org.hibernate.Session;

import javax.transaction.Transactional;
import java.util.List;

public class OVChipkaartDAOHibernate extends BaseDAOHibernate implements OVChipkaartDAO{

    public OVChipkaartDAOHibernate(Session session){
        super(session);
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        try {
            return executeInsideTransaction(session -> sess.save(ovChipkaart));
        } catch (Exception e){
            System.out.println("Something went wrong saving the OV-Chipcard: ");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) {
        try {
            return executeInsideTransaction(session -> sess.update(ovChipkaart));
        } catch (Exception e){
            System.out.println("Something went wrong updating the OV-Chipcard: ");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        try {
            return executeInsideTransaction(session -> sess.delete(ovChipkaart));
        } catch (Exception e){
            System.out.println("Something went wrong deleting the OV-Chipcard: ");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        try {
            List<OVChipkaart> results = sess.createQuery("from ov_chipkaart where reiziger_id = :reizigerId").setParameter("reizigerId", reiziger.getId()).list();
            return results;
        } catch (Exception e){
            System.out.println("Something went wrong finding the OV-Chipcards by traveller: ");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<OVChipkaart> findAll() {
        try {
            List<OVChipkaart> results = sess.createQuery("from ov_chipkaart").list();
            return results;
        } catch (Exception e){
            System.out.println("Something went wrong finding all the OV-Chipcards:");
            System.out.println(e);
            return null;
        }
    }

    @Override
    public OVChipkaart findByKaartnummer(int kaartnummer) {
        try {
            OVChipkaart ovChipkaart = sess.get(OVChipkaart.class, kaartnummer);
            return ovChipkaart;
        } catch (Exception e){
            System.out.println("Something went wrong finding the OV-Chipcard by cardnumber:");
            System.out.println(e);
            return null;
        }
    }
}
