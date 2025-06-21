/*
 * TestDifficulte.java                                             19 mai 2021
 * IUT info1 2020-2021 groupe 3, pas de copyright, pas de droit d'auteur
 */
package JeuDemineur.choixDifficulte.test;
import static JeuDemineur.outils.glg.Assertions.echec;

import JeuDemineur.choixDifficulte.Difficulte;

/** TODO commenter la responsabilité de cette classe
 * @author morgan
 *
 */
public class TestDifficulte {
    
    /* Jeu de données des 4 noms de difficultés uniquement valides */
    private static final String[] NOM_VALIDE = {
            "Defaut",
            "Debutant",
            "Confirme",
            "Expert"
    };
    
    /* Jeu de données de dimensions lignes-colonnes valides */
    private static final int[][] DIMENSION_VALIDE = {
            {26, 26},   //maximum de lignes et colonnes 
            {1, 1},     //minimum de lignes et colonnes 
            {8, 11}     //nombre de lignes et de colonnes différents
    };
    
    /* Jeu de données sur des nombres de bombes considérés comme valides */
    private static final int[] NB_BOMBES_VALIDE = {
            1,          //minimum de bombes
            676,        //maximum de bombes
            10,
    };
    
    /* Jeu de données de patronymes de difficultés invalides */
    private static final int[][] DIMENSION_INVALIDE = {
            null,
            {0, 0},     //terrain de dimension 0x0 ou terrain vide
            {-1, 12},   //nombre de lignes négatif
            {12, -1},   //nombre de colonnes négatif
            {27, 12},   //nombre de lignes trop grand
            {12, 29},   //nombre de colonnes trop grand
    };
    
    /* Jeu de données sur des nombres de bombes considérés comme invalides */
    private static final int[] NB_BOMBES_INVALIDE = {
            0,          //nombre de bombe nul donc un jeu sans intérêt
            680,        //nombre dépassant le maximum de bombes (676 bombes)
            -1          //nombre de bombes négatif
    };

    
    
    
    /**
     * Test du constructeur avec des jeux de données valides et invalides
     *
     */
/*    public void testDifficulte() {

        for ( String nom : NOM_VALIDE) {
            for (int[] dimension : DIMENSION_VALIDE) {
                for (int nbBombes : NB_BOMBES_VALIDE) {
                    if ( nbBombes > dimension[0]*dimension[1] ) {
                        try {
                            new Difficulte(nom, dimension, nbBombes);
                            echec();
                        } catch ( IllegalArgumentException ok ) {
                            //test OK
                        }
                    } else {
                        try {
                            new Difficulte(nom, dimension, nbBombes);
                            //test OK
                        } catch ( IllegalArgumentException erreur ) {
                            echec();
                        }
                    }
                }
                for (int nbBombesIncorrect : NB_BOMBES_INVALIDE ) {
                    try {
                        new Difficulte(nom, dimension, nbBombesIncorrect);
                        echec();
                    } catch ( IllegalArgumentException ok ) {
                        //test OK
                    }
                }
            }
            for (int[] dimensionIncorrecte : DIMENSION_INVALIDE) {
                for (int nbBombes : NB_BOMBES_VALIDE) {
                    try {
                        new Difficulte(nom, dimensionIncorrecte, nbBombes);
                        echec();
                    } catch ( IllegalArgumentException ok ) {
                        //test OK
                    }
                }
                for (int nbBombesIncorrect : NB_BOMBES_INVALIDE) {
                    try {
                        new Difficulte(nom, dimensionIncorrecte, nbBombesIncorrect);
                        echec();
                    } catch ( IllegalArgumentException ok ) {
                        //test OK
                    }
                }
            }
        }
        
    }
*/    
    
    
    
    /**
     * lance automatiquement les méthodes de test
     * @param args non utilisé
     */
    public void main(String[] args) {
        //testDifficulte();
    }

}
