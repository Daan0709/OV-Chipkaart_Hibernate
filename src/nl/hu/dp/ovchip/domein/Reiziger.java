package nl.hu.dp.ovchip.domein;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reiziger {
    @Id
    @Column(name = "reiziger_id")
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;

    @OneToOne(mappedBy = "reiziger")
    private Adres adres;

    @OneToMany(mappedBy = "reiziger")
    private List<OVChipkaart> OVChipkaarten = new ArrayList<>();

    public Reiziger(){
    }



    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<OVChipkaart> getOVChipkaarten() {
        return OVChipkaarten;
    }

    public void setOVChipkaarten(List<OVChipkaart> OVChipkaarten) {
        this.OVChipkaarten = OVChipkaarten;
    }

    public void addOVChipkaart(OVChipkaart ovChipkaart){
        this.OVChipkaarten.add(ovChipkaart);
    }

    public void removeOVChipkaart(OVChipkaart ovChipkaart){
        this.OVChipkaarten.remove(ovChipkaart);
    }

    public String getNaam(){
        if (tussenvoegsel == null || tussenvoegsel.equals("")) {
            return voorletters + ". " + achternaam;
        }
        return voorletters + ". " + tussenvoegsel + " " + achternaam;
    }

    public String toString(){
        return "#" + id + ": " + getNaam() + " (" + geboortedatum + ") Adres {" + adres + "} Met ov-chipkaarten: " + getOVChipkaarten();
    }
}
