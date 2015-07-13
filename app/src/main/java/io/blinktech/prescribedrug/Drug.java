package io.blinktech.prescribedrug;

/**
 * Created by mayank on 7/12/15.
 */
public class Drug {
    private int id;
    private String name;

    public Drug(){

    }

    public Drug(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
