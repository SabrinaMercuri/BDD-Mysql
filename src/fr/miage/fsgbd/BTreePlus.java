package fr.miage.fsgbd;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * @author Galli Gregory, Mopolo Moke Gabriel
 * @param <Type>
 */
public class BTreePlus<Type> implements Serializable {
    private Noeud<Type> racine;

    public BTreePlus(int u, Executable e) {
        racine = new Noeud<Type>(u, e, null);
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

    /**
     * Recherche index
     * Complexité : O(log(n)) le temps dépendra du logarithme du nombre d'enregistrement
     * @param id ,index que l'on recherche
     * @param tree ,arbre dans lequel on cherche l'index
     * @return la personne correspondant à l'index ou null si non trouvé
     */
    public Personne searchIndex(Noeud<Type> tree,Type id) {
        
        Personne res = new Personne();
        if (tree.keys.contains(id)) {
            return cherchePersonne(tree.p,id);
        }
        else if(tree.fils.size()!=0) {
            for (Noeud<Type> node : tree.fils) {
                if (node.keys.contains(id)) {
                    res = cherchePersonne(node.p, id);
                }
                else {
                    res = searchIndex(node, id);
                }
            }
        }

        return res;
    }

    /**
     * Recherche Séquentielle
     * Complexité : O(n) le temps dépendra du nombre d'enregistrement
     * @param id ,index que l'on recherche
     * @param tree ,arbre dans lequel on cherche l'index
     * @return la personne correspondant à l'index ou null si non trouvé
     */
    public Personne searchSeq(Noeud<Type> tree,Type id) {
        Personne res = new Personne();
        if (tree.keys.contains(id)) {
            res = cherchePersonne(tree.p,id);
        }
        else if(tree.fils.size() != 0) {
            for (Noeud<Type> n : tree.fils) {
                res = cherchePersonne(n.p,id);
                if(res == null) 
                    res = searchSeq(n, id);
            }
        }
        return res;
    }

    /**
     * Recherche la personne en fonction de l'index
     * @param p , liste des personnes dans le noeud
     * @param index , index que l'on recherche
     * @return la personne correspondant à l'index ou null si non trouvé
     */
    private Personne cherchePersonne(ArrayList<Personne> p, Type index) {
        for(Personne pers : p){
            if(pers.key==(int)index) return pers;
        }
        return null;
    }

    public Noeud<Type> getRacine() {
        return racine;
    }
}
