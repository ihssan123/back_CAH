package org.example;

import java.util.ArrayList;

public class Cluster {
    private ArrayList<String> villes;
    public Cluster(String s) {
        villes = new ArrayList<>();
        villes.add(s);
    }

    public Cluster(ArrayList<String> villes) {
        this.villes = villes;
    }

    public ArrayList<String> getVilles() {
        return villes;
    }

    public void setVilles(ArrayList<String> villes) {
        this.villes = villes;
    }


    public Cluster() {

    }
}
