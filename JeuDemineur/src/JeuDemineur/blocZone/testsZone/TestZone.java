/*
 * testsZone.java                                                    14 mai 2021
 * IUT info1 2020-2021, Morgan NAYET - Relba KOMPAORE - Kenzo FAHEM -
 *                      Alban MARION - Gauthier GUIROLA-BOË
 */
package JeuDemineur.blocZone.testsZone;

import JeuDemineur.blocZone.Zone;


/** 
 * Test unitaires de la classe Zone
 * @author Gauthier GUIROLA-BOË
 */
public class TestZone {
    
    
    /** jeux de tests de Zones par défaut valides */
    @SuppressWarnings("unused")
    private final static Zone[] ZONE_DEFAUT_VALIDES = {
            new Zone("A1"),
            new Zone("F15"),
            new Zone("J9"),
            
            new Zone("H12"),
            new Zone("Q7"),
            new Zone("U11"),
            
            new Zone("P2"),
            new Zone("G16"),
            new Zone("L6"),
            
            new Zone("M14"),
            new Zone("B11"),
            new Zone("W8"),
            
            new Zone("E3"),
            new Zone("D9"),
            new Zone("N13"),
            
            new Zone("K4"),
            new Zone("O5"),
            new Zone("I10")
    };
    
    /** jeux de tests de Zones valides */
    @SuppressWarnings("unused")
    private final static Zone[] ZONE_VALIDES = {
            // Zone cachée et miné
            new Zone("A1", 'C', true),
            new Zone("F15", 'C', false),
            new Zone("J9", 'C', true),

            // Zone cachée et non miné
            new Zone("H12", 'C', false),
            new Zone("Q7", 'C', true),
            new Zone("U11", 'C', false),

            // Zone marchée et miné
            new Zone("P2", 'M', true),
            new Zone("G16", 'M', false),
            new Zone("L6", 'M', true),

            // Zone marchée et non miné
            new Zone("M14", 'M', false),
            new Zone("B11", 'M', true),
            new Zone("W8", 'M', false),

            // Zone bloquée et miné
            new Zone("E3", 'X', true),
            new Zone("D9", 'X', false),
            new Zone("N13", 'X', true),

            // Zone bloquée et non miné
            new Zone("K4", 'X', false),
            new Zone("O5", 'X', true),
            new Zone("I10", 'X', false)
    };

    
    /** 
     * tests unitaires de Zone(nom)
     */
    private static void testZoneString() {      
        
        /* String invalides */
        String[] nomInvalides = {
                // Chiffre < 1
                "A0" , "K-1", "P-15", "F-23", "G-4",
                // Chiffre > 16 
                "J17" , "R28", "C139", "M199", "N34",
                // Lettre en minuscule
                "f12" , "h8", "d3", "o14","x16",
                // Nom et chiffre dans le mauvais ordre
                "10T", "2L", "13R", "0O", "16Z"
        };       
        
        /* tests des combinaisons invalides sur l'etat */
        for (int i=0; i<nomInvalides.length; i++) {
            try {
                new Zone(nomInvalides[i]);
                System.err.println("");
                throw new RuntimeException("Erreur sur Zone incorrecte");
            } catch(IllegalArgumentException levee) {
            /* test Ok */
            }
        }  
    }
    
    
    /** 
     * tests unitaires de Zone(nom, état, bombe)
     */
    private static void testZoneStringCharBoolean() {      
        
        /* tableau nom valides */
        String[] nomValides = {
                // Chiffre < 1
                "A12" , "K1", "P15", "F3", "G14",
                // Chiffre > 16 
                "J7" , "R2", "C9", "M11", "N3",
                // Lettre en minuscule
                "F" , "H8", "D3", "O14","X16",
                // Nom et chiffre dans le mauvais ordre
                "T10", "L2", "R13", "O1", "Z16"
        };  
        
        /* tableau nom invalides */
        String[] nomInvalides = {
                // Chiffre < 1
                "A0" , "K-1", "P-15", "F-23", "G-4",
                // Chiffre > 16 
                "J17" , "R28", "C139", "M199", "N34",
                // Lettre en minuscule
                "f12" , "h8", "d3", "o14","x16",
                // Nom et chiffre dans le mauvais ordre
                "10T", "2L", "13R", "0O", "16Z"
        };
        
        /* tableau état valides */
        char[] etatValides = {
                // Chiffre < 1
                'C' , 'M', 'X', 'M', 'X',
                // Chiffre > 16 
                'X', 'C', 'M', 'C', 'X',
                // Lettre en minuscule
                'C' , 'X', 'M', 'C','M',
                // Nom et chiffre dans le mauvais ordre
                'X', 'M', 'C', 'X', 'M'
        };
        
        /* tableau état invalides */
        char[] etatInvalides = {
                // Chiffre < 1
                'A', 'B', 'D', 'E', 'F',
                // Chiffre > 16 
                'G', 'H', 'I', 'J', 'K',
                // Lettre en minuscule
                'L', 'N', 'O', 'P','Q',
                // Nom et chiffre dans le mauvais ordre
                'R', 'S', 'T', 'U', 'Z'
        };   
        
        /* boolean valide */
        boolean[] bombe = {true, false};
        
        
        
        /* tests des combinaisons invalides du constructeur Zone */
        for (int i=0; i<nomInvalides.length; i++) {
            try {
                new Zone(nomInvalides[i],etatValides[i], bombe[i%2]);
                System.err.println("Erreur sur : Zone("
                                    + nomInvalides[i] + ", "
                                    + etatValides[i] + ", "
                                    + bombe[i%2] + ", ");
                throw new RuntimeException("Erreur sur Zone incorrecte");
            } catch(IllegalArgumentException levee) {
            /* test Ok */
            }            
        } 
        
        
        /* tests des combinaisons invalides du constructeur Zone */
        for (int i=0; i<etatInvalides.length; i++) {
            try {
                new Zone(nomValides[i],etatInvalides[i], bombe[i%2]);
                System.err.println("Erreur sur : Zone( "
                                    + nomInvalides[i] + ", "
                                    + etatValides[i] + ", "
                                    + bombe[i%2] + ")");
                throw new RuntimeException("Erreur sur Zone incorrecte");
            } catch(IllegalArgumentException levee) {
            /* test Ok */
            }            
        }
    }
    
    /** 
     * tests unitaires de Marcher()
     */
    private static void testMarcher() {
        
        // Test du changement d'état 
        // sur les Zone qui en on la possibilité
        final Zone[] ZONE_CACHEE_TO_MARCHER = {
                new Zone("A1", 'C', true),
                new Zone("A2", 'C', false),
                new Zone("A3", 'C', true),
                // Le constructeur Zone par défaut a l'état cachée
                new Zone("A4"),
                new Zone("A5")
        };
           
        int noJeu;   
//        do {
//            ZONE_CACHEE_TO_MARCHER[noJeu-1].marcher();
//            if (ZONE_CACHEE_TO_MARCHER[noJeu-1].getEtat() == 'M') {
//                noJeu++;
//            }
//        }while(noJeu<ZONE_CACHEE_TO_MARCHER.length
//                && ZONE_CACHEE_TO_MARCHER[noJeu-1].getEtat() == 'M');
        
        for(noJeu=0; noJeu<ZONE_CACHEE_TO_MARCHER.length; noJeu++) {    
            ZONE_CACHEE_TO_MARCHER[noJeu].marcher();
        }
        
        for(noJeu=0; noJeu<ZONE_CACHEE_TO_MARCHER.length
                && ZONE_CACHEE_TO_MARCHER[noJeu].getEtat() == 'M'; noJeu++) {    
        }
        
        // Test du refus du changement d'état 
        // sur les Zone qui n'en on pas la possibilité
        final Zone[] ZONE_CACHEE_TO_MARCHER_INVALIDES = {
                new Zone("A1", 'X', false),
                new Zone("A2", 'X', true),
                new Zone("A3", 'X', false)
        };
           
        int noJeu2;
        for(noJeu2=0; noJeu2<ZONE_CACHEE_TO_MARCHER_INVALIDES.length; noJeu2++) {    
            ZONE_CACHEE_TO_MARCHER_INVALIDES[noJeu2].marcher();
        }
        
        for(noJeu2=0; noJeu2<ZONE_CACHEE_TO_MARCHER_INVALIDES.length
                && ZONE_CACHEE_TO_MARCHER_INVALIDES[noJeu2].getEtat() != 'M'; noJeu2++) {    
        }
        
        // Affichage des résultats
        if (noJeu <ZONE_CACHEE_TO_MARCHER.length) {
            System.err.println("testMarcher() échec, sur les Zones transformable :"
                                + " erreur au jeu numéro " 
                                + noJeu);
        } else if (noJeu2 <ZONE_CACHEE_TO_MARCHER_INVALIDES.length) {
            System.err.println("testMarcher() échec, sur les Zones non transformable :"
                                + " erreur au jeu numéro " 
                                + noJeu2);
        } else {
            System.out.println("testMarcher() réussi");
        }       
    }
    
    
    /** 
     * tests unitaires de Bloquer()
     */
    private static void testBloquer() {
        
        // Test du refus du changement d'état 
        // sur les Zone qui en on la possibilité
        final Zone[] ZONE_CACHEE_TO_BLOQUER = {
                new Zone("A6", 'C', true),
                new Zone("A7", 'C', false),
                new Zone("A8", 'C', true),
                // Le constructeur Zone par défaut a l'état cachée
                new Zone("A9"),
                new Zone("A10")
        };
           
        int noJeu;
        for(noJeu=0; noJeu<ZONE_CACHEE_TO_BLOQUER.length; noJeu++) {    
            ZONE_CACHEE_TO_BLOQUER[noJeu].bloquer();
        }
        
        for(noJeu=0; noJeu<ZONE_CACHEE_TO_BLOQUER.length
                && ZONE_CACHEE_TO_BLOQUER[noJeu].getEtat() == 'X'; noJeu++) {    
        }
        
        
        // Test du refus du changement d'état 
        // sur les Zone qui n'en on pas la possibilité
        final Zone[] ZONE_CACHEE_TO_BLOQUER_INVALIDES = {
                new Zone("A6", 'M', true),
                new Zone("A7", 'M', false),
                new Zone("A8", 'M', true),
        };
        
        int noJeu2;
        for(noJeu2=0; noJeu2<ZONE_CACHEE_TO_BLOQUER_INVALIDES.length; noJeu2++) {    
            ZONE_CACHEE_TO_BLOQUER_INVALIDES[noJeu2].bloquer();
        }
        
        for(noJeu2=0; noJeu2<ZONE_CACHEE_TO_BLOQUER_INVALIDES.length
                && ZONE_CACHEE_TO_BLOQUER_INVALIDES[noJeu2].getEtat() != 'X'; noJeu2++) {    
        }
        
        
        // Affichage des résultats
        if (noJeu <ZONE_CACHEE_TO_BLOQUER.length) {
            System.err.println("testBloquer() echec, sur les Zone transformable :"
                                + " erreur au jeu numéro " 
                                + noJeu);
        } else if (noJeu2 <ZONE_CACHEE_TO_BLOQUER_INVALIDES.length) {
            System.err.println("testBloquer() échec, sur les Zones non transformable :"
                    + " erreur au jeu numéro " 
                    + noJeu2);
        } else {
            System.out.println("testBloquer() réussi");
        }
        
    }
    
    
    /** 
     * tests unitaires de Débloquer()
     */
    private static void testDébloquer() {
        
        // Test du refus du changement d'état 
        // sur les Zone qui en on la possibilité
        final Zone[] ZONE_BLOQUER_TO_DEBLOQUER = {
                new Zone("A11", 'X', true),
                new Zone("A12", 'X', false),
                new Zone("A13", 'X', true),
        };
           
        int noJeu;
        for(noJeu=0; noJeu<ZONE_BLOQUER_TO_DEBLOQUER.length; noJeu++) {    
            ZONE_BLOQUER_TO_DEBLOQUER[noJeu].debloquer();
        }
        
        for(noJeu=0; noJeu<ZONE_BLOQUER_TO_DEBLOQUER.length
                && ZONE_BLOQUER_TO_DEBLOQUER[noJeu].getEtat() == 'C'; noJeu++) {    
        }
        
        // Test du refus du changement d'état 
        // sur les Zone qui n'en on pas la possibilité
        final Zone[] ZONE_BLOQUER_TO_DEBLOQUER_INVALIDES = {
                new Zone("A6", 'M', true),
                new Zone("A7", 'M', false),
                new Zone("A8", 'M', true),
        };
        
        int noJeu2;
        for(noJeu2=0; noJeu2<ZONE_BLOQUER_TO_DEBLOQUER_INVALIDES.length; noJeu2++) {    
            ZONE_BLOQUER_TO_DEBLOQUER_INVALIDES[noJeu2].bloquer();
        }
        
        for(noJeu2=0; noJeu2<ZONE_BLOQUER_TO_DEBLOQUER_INVALIDES.length
                && ZONE_BLOQUER_TO_DEBLOQUER_INVALIDES[noJeu2].getEtat() != 'C'; noJeu2++) {    
        }
        
        
        // Affichage des résultats
        if (noJeu <ZONE_BLOQUER_TO_DEBLOQUER.length) {
            System.err.println("testDébloquer() échec, sur les Zones transformable :"
                                + " erreur au jeu numéro " 
                                + noJeu);
        } else if (noJeu2 <ZONE_BLOQUER_TO_DEBLOQUER_INVALIDES.length) {
            System.err.println("testDeloquer() échec, sur les Zones non transformable :"
                                + " erreur au jeu numéro " 
                                + noJeu2);
        } else {
            System.out.println("testDébloquer() réussi");
        }
        
    }
    
 
    
    /**
     * Lancement des méthodes de tests
     * @param args non utilisé
     */
    public static void main(String... args) {

        try {
            testZoneString();
            System.out.println("testZoneString réussi");
        } catch(Exception lancee) {
            System.err.println("Erreur sur testZoneString : "
                               + lancee.getMessage());
        }
        
        try {
            testZoneStringCharBoolean();
            System.out.println("testZoneStringCharBoolean réussi");
        } catch(Exception lancee) {
            System.err.println("Erreur sur testZoneStringCharBoolean : "
                               + lancee.getMessage());
        }
        
        testMarcher();
        testBloquer();
        testDébloquer();
    }    
}