/*
 * Casse.java                                                    25 mai 2021
 * IUT info1 2020-2021, pas de copyright 
 *                      
 */
package JeuDemineur;
import JeuDemineur.grilleTerrain.Terrain;
import JeuDemineur.choixDifficulte.Difficulte;
import JeuDemineur.blocZone.Zone;
import java.util.Scanner;

/**
 * Cette classe contient différentes méthodes outils correspondant à l'interface
 * utilisateur de l'application "Démineur".
 *    
 * @author Kenzo FAHEM
 * @author Relba KOMPAORE
 * @author Morgan NAYET 
 * @version 1.0
 *
 */
public class Demineur {

    /** Valeur assciée à la diffficulté par défaut */
    public static final int DIFFICULTE_DEFAUT = 0;

    /** Valeur assciée à la diffficulté "Débutant" */
    public static final int DIFFICULTE_DEBUTANT = 1;
    
    /** Valeur assciée à la diffficulté "Confirmé" */
    public static final int DIFFICULTE_CONFIRME = 2;
    
    /** Valeur assciée à la diffficulté "Expert" */
    public static final int DIFFICULTE_EXPERT = 3;
    
    /**
     * Interface menu principal
     */
    public static final String MENU_DEMINEUR =
            """
                            -----------------------------
                            |          Demineur         |
                            -----------------------------

                            1) Nouvelle partie
                            2) Quitter le jeu
                            
                            Veuillez saisir votre choix:  """;
    
    /** Interface menu nouvelle partie */
    public static final String MENU_NOUVELLE_PARTIE =
            """
                            -----------------------------
                            |      Nouvelle Partie      |
                            -----------------------------

                            1) Lancer une nouvelle partie
                            2) Choisir une difficulte
                            3) Revenir au menu Demineur
                            
                            Veuillez saisir votre choix: """;

    /** Interface menu choix difficulte */
    public static final String MENU_CHOIX_DIFFICULTE = 
            """
                            -----------------------------
                            |      Choix Difficulte     |
                            -----------------------------
                            
                            Choisissez un mode de difficulte : 
                            1) Debutant
                            2) Confirme
                            3) Expert
                            4) Revenir au menu Nouvelle Partie
                            
                            Veuillez saisir votre choix: """;

    /** Interface menu choix de jeu */
    public static final String MENU_CHOIX_JEU = 
            """
                            Quel est votre prochain coup? : 
                            1) marcher
                            2) bloquer
                            3) debloquer
                            4) Quitter la partie

                            Veuillez saisir votre choix: """;
    
    /** Variable qui indique le nombre de zones qui ont été marchées sur le terrain
     * à un instant T */
    public static int nbZonesMarchees = 0;
    
    /** Saisie de l'utilisateur */
    public static Scanner entree = new Scanner(System.in);
    
    /**  */
    public static Terrain terrainJeu;
    
    /** Variable qui mettra fin à la méthode "jeu()" et qui fermera le jeu */
    public static boolean sortieJeu = false;
    
    /** Variable qui indique si le joueur a subi un arrêt de jeu forcé dû à une bombe */
    public static boolean gameOver = false;
    
    /** Variable qui indique si le joueur a gagné le jeu */
    public static boolean victoire = false;

    /** Affiche la grille à l'instant T du jeu */
    public static void afficherGrille() {
        
        System.out.println("\n");
        System.out.println(terrainJeu.toString() );
    }

    /** Affiche le menu principal du jeu Demineur */
    public static void afficherMenuDemineur() {
        System.out.print(MENU_DEMINEUR);
    }
    
    /** Affiche le menu Nouvelle partie du jeu Demineur */
    public static void afficherMenuNouvellePartie() {
        System.out.print(MENU_NOUVELLE_PARTIE);
    }

    /** Affiche les différentes options de saisie pour le choix de la difficulté */
    public static void afficherMenuDifficultee() {
        System.out.print(MENU_CHOIX_DIFFICULTE);
    }

    /**
     * Affiche les différentes options de jeu
     */
    public static void afficherMenuChoixJeu() {
        System.out.print(MENU_CHOIX_JEU);
    }
    
    /**
     * Gestion d'erreur sur la saisie 
     * @return zoneSaisie retourne le nom d'une zone existante 
     */
    public static String saisieZone() {
        
        String zoneSaisie;
        boolean saisieOk;
        
        do {
            System.out.print("Sur quelle case voulez-vous realiser l'action: ");
            zoneSaisie = entree.next();
            
            if ( terrainJeu.trouverZone( zoneSaisie ) == null ) {
                saisieOk = false;
                System.out.println("La zone souhaitee n'existe pas. Recommencer.\n");
            } else {
                saisieOk = true;
            }
            entree.nextLine();
            
        } while ( !saisieOk );
        return zoneSaisie;
    }
    
    /**
     * Gestion d'erreur sur la saisie et actions sur le terrain selon 
     * le choix de l'utilisateur 
     */
    public static void saisieMenuChoixJeu() {
        
        int choix;
        boolean saisieOk;
        Zone zoneSaisie;
        
        choix = -1; //stub
        do {
            saisieOk = entree.hasNextInt();
            if ( saisieOk ) {
                choix = entree.nextInt();
            }
            if (choix < 1 || choix > 4) {
                System.out.println("\nVeuillez entrer un nombre entre 1 et 4\n");
                afficherGrille();
                afficherMenuChoixJeu();
            }
            entree.nextLine();
            System.out.print("\n");
        } while ( choix < 1 || choix > 4 );
        
        switch (choix) {
            case 1 : zoneSaisie = terrainJeu.trouverZone( saisieZone() );
                     terrainJeu.marcherAutomatiquement(zoneSaisie);
                     gameOver = zoneSaisie.getBombe() 
                               && zoneSaisie.getEtat() =='M' ? true : false;
                     break;
            case 2 : terrainJeu.trouverZone( saisieZone() ).bloquer();
                     break;
            case 3 : terrainJeu.trouverZone( saisieZone() ).debloquer();
                     break;
            case 4 : sortieJeu = true;
                     break;
        }
        if ( nbZonesMarchees == 
            terrainJeu.getDimension()[0] * terrainJeu.getDimension()[1]
            - terrainJeu.getNbBombes() ) {
            victoire = true;
        }
    }
    
    /** Gestion d'erreur sur la saisie et redirection selon le choix utilisateur */
    public static void saisieMenuDifficulte() {
        
        int choix;
        boolean saisieOk;
        
        choix = -1; //stub
        do {
            saisieOk = entree.hasNextInt();
            if ( saisieOk ) {
                choix = entree.nextInt();
            }
            if (choix < 1 || choix > 4) {
                System.out.println("\nVeuillez entrer un nombre entre 1 et 4\n");
                afficherMenuDifficultee();
            }
            entree.nextLine();
            System.out.print("\n");
        } while ( choix < 1 || choix > 4 );
        
        switch (choix) {
            case 1 : jeu( DIFFICULTE_DEBUTANT );
                     break;
            case 2 : jeu( DIFFICULTE_CONFIRME );
                     break;
            case 3 : jeu( DIFFICULTE_EXPERT );
                     break;
            case 4 : menuNouvellePartie();
            break;
        }
    }
    
    /** Gestion d'erreur sur la saisie et redirection selon le choix utilisateur */
    public static void saisieMenuNouvellePartie() {
        
        int choix;
        boolean saisieOk;
        
        choix = -1; //stub
        do {
            saisieOk = entree.hasNextInt();
            if ( saisieOk ) {
                choix = entree.nextInt();
            }
            if (choix < 1 || choix > 3) {
                System.out.println("\nVeuillez entrer un nombre entre 1 et 3\n");
                afficherMenuNouvellePartie();
            }
            entree.nextLine();
            System.out.print("\n");
        } while ( choix < 1 || choix > 3 );
        
        switch (choix) {
            case 1 : jeu( DIFFICULTE_DEFAUT );
                     break;
            case 2 : menuDifficulte();
                     break;
            case 3 : menuDemineur();
                     break;
        }
    }
    
    /** Gestion d'erreur sur la saisie et redirection selon le choix utilisateur */
    public static void saisieMenuDemineur() {
        
        int choix;
        boolean saisieOk;
        
        choix = -1; //stub
        do {
            saisieOk = entree.hasNextInt();
            
            if ( saisieOk ) {
                choix = entree.nextInt();
            }
            if (choix < 1 || choix > 2) {
                System.out.println("\nVeuillez entrer un nombre entre 1 et 2\n");
                afficherMenuDemineur();
            }
            entree.nextLine();
            System.out.print("\n");
        } while ( choix < 1 || choix > 2 );
        
        switch (choix) {
            case 1 : menuNouvellePartie();
                     break;
            case 2 : break;
        }
    }
    
    /** Affiche le menu Choix de Jeu */
    public static void menuChoixJeu() {
        afficherMenuChoixJeu();
        saisieMenuChoixJeu();
    }

    /** Affiche le menu de difficulté */
    public static void menuDifficulte() {
        afficherMenuDifficultee();
        saisieMenuDifficulte();
    }
    
    /** Affiche le menu Nouvelle Partie 
     * et redirige l'utilisateur selon son choix */
    public static void menuNouvellePartie() {
        
        afficherMenuNouvellePartie();
        saisieMenuNouvellePartie();
    }

    /** Affiche le menu Démineur et redirige l'utilisateur selon son choix */
    public static void menuDemineur() {
        
        afficherMenuDemineur();
        saisieMenuDemineur();
    }
    
    /**
     * Affiche le jeu avec gestion d'arret du jeu selon si le joueur subit une 
     * defaite, obtient une victoire ou decide d'arrete simplement le jeu
     * 
     * @param noDifficulte 
     */
    public static void jeu(int noDifficulte) {
        
        Difficulte difficulteJeu = new Difficulte( noDifficulte );
        terrainJeu = new Terrain( difficulteJeu );
        
        /* Lancement du Jeu Démineur (fin du jeu lorsque la console reçoie gameOver) */
        do {
            afficherGrille();
            menuChoixJeu();
            
        } while ( !sortieJeu && !gameOver && !victoire );
        
        if ( gameOver ) {
            afficherGrille();
            System.out.println("Vous avez perdu, retentez votre chance.");
            
        } else if ( victoire ) {
            afficherGrille();
            System.out.println("Felicitation, vous avez gagnez! "
                               + "Retentez votre chance avec un niveau plus dur "
                               + "ou continuer a vous amuser.");
            
        }
    }

    /**
     * Lance le jeu et dirige l'utilisateur vers le menu Demineur.
     * Le jeu se ferme lorsque le joueur décide de quitter l'application
     * ou gagne ou perd le jeu
     * 
     */ 
    public static void main(String[] args) {

        menuDemineur();
        System.out.println("\nLe jeu va maintenant se fermer");
    }
}