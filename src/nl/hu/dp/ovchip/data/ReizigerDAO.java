package nl.hu.dp.ovchip.data;

import nl.hu.dp.ovchip.domain.Reiziger;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {

    public boolean save(Reiziger reiziger) throws SQLException;
    public boolean update(Reiziger reiziger) throws SQLException;
    public boolean delete(Reiziger reiziger) throws SQLException;

    public Reiziger findById(int id) throws SQLException;
    public List<Reiziger> findByGbdatum(Date datum) throws SQLException;
    public List<Reiziger> findAll() throws SQLException;
}
