package nl.hu.dp.ovchip.data;

import nl.hu.dp.ovchip.domain.OVChipkaart;
import nl.hu.dp.ovchip.domain.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductDAO {
    public boolean save(Product product) throws SQLException;
    public boolean update(Product product) throws SQLException;
    public boolean delete(Product product) throws SQLException;

    public Product findByProductnummer(int productnummer) throws SQLException;
    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) throws SQLException;
    public List<Product> findAll() throws SQLException;
}
