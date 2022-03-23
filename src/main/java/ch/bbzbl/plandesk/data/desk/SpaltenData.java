package ch.bbzbl.plandesk.data.desk;

import ch.bbzbl.plandesk.dto.desk.VorgangDto;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import java.util.ArrayList;

public class SpaltenData {

    private int ID;
    private String name;
    private String postition;
    private ArrayList<VorgangData> vorgaenge = new ArrayList();

    public SpaltenData(int ID, String name, String postition) {
        this.ID = ID;
        this.name = name;
        this.postition = postition;
    }

    public void addToVorgangArray(VorgangData vorgangData){
        vorgaenge.add(vorgangData);
    }
    public void removeFromVorgangArray(VorgangData vorgangData){
        vorgaenge.remove(vorgangData);
    }


    @Id
    @GenericGenerator(name="id" , strategy="increment")
    @GeneratedValue
    @Column(unique = true, nullable = false)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostition() {
        return postition;
    }

    public void setPostition(String postition) {
        this.postition = postition;
    }


    public ArrayList<VorgangData> getVorgaenge() {
        return vorgaenge;
    }

}
