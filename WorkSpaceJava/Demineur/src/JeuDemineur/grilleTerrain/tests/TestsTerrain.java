/* TestsTerrain.java                                             17 mai 2021
 * IUT info1 2020-2021 groupe 3, pas de copyright, pas de droit d'auteur
 */
package JeuDemineur.grilleTerrain.tests;
import static JeuDemineur.outils.glg.Assertions.echec;

import JeuDemineur.blocZone.Zone;
import JeuDemineur.grilleTerrain.Terrain;

/** TODO commenter la responsabilit� de cette classe
 *  Test du constructeur de la classe Terrain
 * @author Morgan Nayet
 *
 */
public class TestsTerrain {
    
    /* Jeu de donn�es de patronymes de noms valides*/
    private final String[] NOM_VALIDE = {
            "Jeu de Morgan",
            "Jeu de Kenzo",
            "Nouveau jeu"
    };
    
    
    
    /**TODO finir le jeu de donn�es de LISTE_ZONES_VALIDE */
    /* Jeu de donn�es de listeZones valides*/
    private final Zone[][][] LISTE_ZONES_VALIDE = {
            {
                {new Zone("A1"), new Zone("A2")},
                {new Zone("B1"), new Zone("B2")}
            }
    };
    
    /* Jeu de donn�es de noms consid�r�s comme invalides par le programme */
    private final String[] NOM_INVALIDE = {
            null,
            "",
            " "
    };
    
    /**TODO finir le jeu de donn�es de LISTE_ZONES_INVALIDE */
    /* Jeu de donn�es de listeZones invalides*/
    private final Zone[][][] LISTE_ZONES_INVALIDE = {
             {
                 //absence d'une ligne ou pr�sence d'une ligne nulle
                 {new Zone("A1"), new Zone("A2")},
                 {null},
                 {new Zone("C1"), new Zone("C2")}
             },
             {
                 // absence d'une colonne ou pr�sence d'une colonne nulle
                 {new Zone("A1"), null, new Zone("A3")},
                 {new Zone("B1"), null, new Zone("B3")}
             },
             {
                 //Zone plac�e au mauvaise endroit dans une ligne
                 {new Zone("A1"), new Zone("B2")},
                 {new Zone("B1"), new Zone("A2")}
             },
             {
                 //Zone plac�e au mauvaise endroit dans une colonne
                 {new Zone("A22"), new Zone("A1")},
                 {new Zone("B1"), new Zone("B2")}
             }
    };
    
    /* Jeu de donn�es de listes de Zones invalides */
//    private final {
//    };
    
    /**
     * Test avec un jeu de donn�e de constructeurs valides
     * et un jeu de donn�es de constructeurs invalides
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
     * TODO commenter le r�le de la m�thode
     *
     */
    public void testCreationListeZones (int[] dimension) {
        
    }
}