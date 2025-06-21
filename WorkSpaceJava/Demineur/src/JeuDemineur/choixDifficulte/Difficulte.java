/*
 * Difficulte.java                                             19 mai 2021
 * IUT info1 2020-2021 groupe 3, pas de copyright, pas de droit d'auteur
 */
package JeuDemineur.choixDifficulte;

/** 
 * Difficult� associ�e � une partie de jeu
 * 
 * @author morgan
 */
public class Difficulte {
    
    /** Liste des seules difficult�s autoris�es */
    public static final String[] LISTE_DIFFICULTE = {
            "Defaut",
            "Debutant",
            "Confirme",
            "Expert"
    };
    
    /** Liste des dimensions selon la difficult� */
    public static final int[][] LISTE_DIMENSION = {
            {8, 8},     //Dimension de la difficult� "Defaut"
            {8, 8},     //Dimension de la difficult� "Debutant"
            {10, 15},   //Dimension de la difficult� "Confirme"
            {16, 16}    //Dimension de la difficult� "Expert"
    };

    /** Liste du nombre de bombes dans le terrain selon la difficult� */
    public static final int[] LISTE_NOMBRE_BOMBES = {
            10,         //Nombre de bombes pour la difficult� "Defaut"
            10,         //Nombre de bombes pour la difficult� "Debutant"
            30,         //Nombre de bombes pour la difficult� "Confirme"
            40          //Nombre de bombes pour la difficult� "Expert"
    };
    
    /** Identifiant nominatif de la difficult� */
    private String nom;
    
    /** Dimension du terrain sous forme lignes-colonnes */
    private int[] dimension;
    
    /** Nombre de bombes cach�es dans le terrain */
    private int nbBombes;
    
    /**
     * Cr�� un objet Diffculte qui instancie ses attribues selon la difficult�
     * souhait�e par l'utilisateur
     * 
     * @param noDifficulte
     */
    public Difficulte( int noDifficulte) {
        
        /** Test sur la validit� du num�ro de rang r�cup�r� */
        if ( 0 > noDifficulte && noDifficulte > ( LISTE_DIFFICULTE.length - 1)) {
            throw new IllegalArgumentException("Erreur saisie! L'argument doit "
                                               + "etre un entier positif et "
                                               + "etre compris entre 0 et "
                                               + ( LISTE_DIFFICULTE.length - 1));
        }
        
        this.nom = LISTE_DIFFICULTE[ noDifficulte ];
        this.dimension = LISTE_DIMENSION[ noDifficulte ];
        this.nbBombes = LISTE_NOMBRE_BOMBES[ noDifficulte ];
    }

    /**
     * @return la valeur de dimension
     */
    public int[] getDimension() {
        return dimension;
    }

    /**
     * @return la valeur de nbBombes
     */
    public int getNbBombes() {
        return nbBombes;
    }
}
