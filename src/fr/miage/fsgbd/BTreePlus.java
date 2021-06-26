package fr.miage.fsgbd;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.Serializable;


/**
 * @author Galli Gregory, Mopolo Moke Gabriel
 * @param <Type>
 */
public class BTreePlus<Type> implements Serializable {
    private Noeud<Type> racine;

    public BTreePlus(int u, Executable e) {
        racine = new Noeud<Type>(u, e, null, null);
    }

    public void afficheArbre() {
        racine.afficheNoeud(true, 0);
    }

    /**
     * Méthode récursive permettant de récupérer tous les noeuds
     *
     * @return DefaultMutableTreeNode
     */
    public DefaultMutableTreeNode bArbreToJTree() {
        return bArbreToJTree(racine);
    }

    private DefaultMutableTreeNode bArbreToJTree(Noeud<Type> root) {
        StringBuilder txt = new StringBuilder();
        for (Type key : root.keys)
            txt.append(key.toString()).append(" ");

        DefaultMutableTreeNode racine2 = new DefaultMutableTreeNode(txt.toString(), true);
        for (Noeud<Type> fil : root.fils)
            racine2.add(bArbreToJTree(fil));

        return racine2;
    }


    public boolean addValeur(Type valeur) {
        System.out.println("Ajout de la valeur : " + valeur.toString());
        if (racine.contient(valeur) == null) {
            Noeud<Type> newRacine = racine.addValeur(valeur);
            if (racine != newRacine)
                racine = newRacine;
            return true;
        }
        return false;
    }


    public void removeValeur(Type valeur) {
        System.out.println("Retrait de la valeur : " + valeur.toString());
        if (racine.contient(valeur) != null) {
            Noeud<Type> newRacine = racine.removeValeur(valeur, false);
            if (racine != newRacine)
                racine = newRacine;
        }
    }

    public Personne searchIndex(Type index,Noeud<Type> arbre) {
        System.out.println("Je suis passé dans searchIndex");

        for(Type key : arbre.keys) {
            if((int) key == (int) index) {
                return arbre.p;
            }
        }
        // need help TY je commence a avoir la flemme mdr ui
        return null;
    }

    public Personne searchSeq(Type index,Noeud<Type> arbre) {
        System.out.println("Je suis passé dans searchSeq");
        // si index contenu dans les fils alors return la personne dans le noeud fils
        if(arbre.p.key==(int)index){
            return arbre.p;
        }
        else{
            if(arbre.fils==null){
                return null;
            }
            for(Noeud<Type> f : arbre.fils){
                return searchSeq(index,f);
            }
        }
        return null;
    }

    public Noeud<Type> getRacine() {
        return racine;
    }
}
