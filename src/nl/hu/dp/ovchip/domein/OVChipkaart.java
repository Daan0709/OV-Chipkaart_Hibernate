package nl.hu.dp.ovchip.domein;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ov_chipkaart")
public class OVChipkaart {
    @Id
    @Column(name = "kaart_nummer")
    private int kaartnummer;
    @Column(name = "geldig_tot")
    private Date verloopdatum;
    private int klasse;
    private double saldo;
    @ManyToOne
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ov_chipkaart_product",
            joinColumns = {@JoinColumn(name = "kaart_nummer")},
            inverseJoinColumns = {@JoinColumn(name = "product_nummer")})
    private List<Product> producten = new ArrayList<>();

    public OVChipkaart(){}

    public int getKaartnummer() {
        return kaartnummer;
    }

    public void setKaartnummer(int kaartnummer) {
        this.kaartnummer = kaartnummer;
    }

    public Date getVerloopdatum() {
        return verloopdatum;
    }

    public void setVerloopdatum(Date verloopdatum) {
        this.verloopdatum = verloopdatum;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public void setProducten(List<Product> producten) {
        this.producten = producten;
    }

    public List<Product> getProducten() {
        return producten;
    }

    public void addProduct(Product product){
        product.addOvChipkaart(this);
        producten.add(product);
    }

    public void removeProduct(Product product){
        producten.remove(product);
    }

    public String toString(){
        return String.format("#%s, verloopt op %s. Klasse: %s, saldo: %s Op naam van: %s.", kaartnummer, verloopdatum, klasse, saldo, reiziger.getNaam());
    }
}
