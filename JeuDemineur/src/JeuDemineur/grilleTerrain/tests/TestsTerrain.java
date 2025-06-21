/* TestsTerrain.java                                             17 mai 2021
 * IUT info1 2020-2021 groupe 3, pas de copyright, pas de droit d'auteur
 */
package JeuDemineur.grilleTerrain.tests;
import static JeuDemineur.outils.glg.Assertions.echec;

import JeuDemineur.blocZone.Zone;
import JeuDemineur.grilleTerrain.Terrain;

/** TODO commenter la responsabilité de cette classe
 *  Test du constructeur de la classe Terrain
 * @author Morgan Nayet
 *
 */
public class TestsTerrain {
    
    /* Jeu de données de patronymes de noms valides*/
    private final String[] NOM_VALIDE = {
            "Jeu de Morgan",
            "Jeu de Kenzo",
            "Nouveau jeu"
    };
    
    
    
    /**TODO finir le jeu de données de LISTE_ZONES_VALIDE */
    /* Jeu de données de listeZones valides*/
    private final Zone[][][] LISTE_ZONES_VALIDE = {
            {
                {new Zone("A1"), new Zone("A2")},
                {new Zone("B1"), new Zone("B2")}
            }
    };
    
    /* Jeu de données de noms considérés comme invalides par le programme */
    private final String[] NOM_INVALIDE = {
            null,
            "",
            " "
    };
    
    /**TODO finir le jeu de données de LISTE_ZONES_INVALIDE */
    /* Jeu de données de listeZones invalides*/
    private final Zone[][][] LISTE_ZONES_INVALIDE = {
             {
                 //absence d'une ligne ou présence d'une ligne nulle
                 {new Zone("A1"), new Zone("A2")},
                 {null},
                 {new Zone("C1"), new Zone("C2")}
             },
             {
                 // absence d'une colonne ou présence d'une colonne nulle
                 {new Zone("A1"), null, new Zone("A3")},
                 {new Zone("B1"), null, new Zone("B3")}
             },
             {
                 //Zone placée au mauvaise endroit dans une ligne
                 {new Zone("A1"), new Zone("B2")},
                 {new Zone("B1"), new Zone("A2")}
             },
             {
                 //Zone placée au mauvaise endroit dans une colonne
                 {new Zone("A22"), new Zone("A1")},
                 {new Zone("B1"), new Zone("B2")}
             }
    };
    
    /* Jeu de données de listes de Zones invalides */
//    private final {
//    };
    
    /**
     * Test avec un jeu de donnée de constructeurs valides
     * et un jeu de données de constructeurs invalides
     */
    public void testTerrain() { 
        for ( String nom : NOM_VALIDE) {
            try {
//                new Terrain(nom);
                //test OK
            } catch ( IllegalArgumentException erreur ) {
                echec();
            }
        } 
        for (String nomIncorrect : NOM_INVALIDE ) {            
            try {
//                new Terrain(nomIncorrect);
                echec();
            } catch ( IllegalArgumentException ok ) {
                //test OK
            }
        }
    }
    
    /**
     * TODO commenter le rôle de la méthode
     *
     */
    public void testCreationListeZones (int[] dimension) {
        
    }
}