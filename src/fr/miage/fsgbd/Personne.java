package fr.miage.fsgbd;

import java.io.Serializable;

public class Personne implements Serializable {

    int key;
    String nom;
    String prenom;

    // Constructeur
    public Personne(int key, String nom, String prenom) {
        this.key = key;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Personne() {

    }
    
    public String toString(){
        return "{ prenom : "+prenom+", nom : "+nom+" }";
    }
}
