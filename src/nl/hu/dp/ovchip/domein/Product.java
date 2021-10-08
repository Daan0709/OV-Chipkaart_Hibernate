package nl.hu.dp.ovchip.domein;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @Column(name = "product_nummer")
    private int productnummer;
    private String naam;
    private String beschrijving;
    private double prijs;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ov_chipkaart_product",
            joinColumns = {@JoinColumn(name = "product_nummer")},
            inverseJoinColumns = {@JoinColumn(name = "kaart_nummer")})
    private List<OVChipkaart> ovChipkaarten = new ArrayList<>();

    public Product(){}

    public int getProductnummer() {
        return productnummer;
    }

    public void setProductnummer(int productnummer) {
        this.productnummer = productnummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public void setOvChipkaarten(List<OVChipkaart> ovChipkaarten) {
        this.ovChipkaarten = ovChipkaarten;
    }

    public List<OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }

    public void addOvChipkaart(OVChipkaart ovChipkaart){
        ovChipkaarten.add(ovChipkaart);
    }

    public void removeOvChipkaart(OVChipkaart ovChipkaart){
        ovChipkaarten.remove(ovChipkaart.getKaartnummer());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productnummer=" + productnummer +
                ", naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                "} Staat op de volgende OV-Chipkaarten: " + ovChipkaarten;
    }
}
