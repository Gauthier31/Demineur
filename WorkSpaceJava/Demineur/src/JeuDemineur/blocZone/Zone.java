/*
 * Zone.java                                                    25 mai 2021
 * IUT info1 2020-2021, Morgan NAYET - Relba KOMPAORE - Kenzo FAHEM -
 *                      Alban MARION - Gauthier GUIROLA-BO�
 */
package JeuDemineur.blocZone;

import JeuDemineur.Demineur;

/**
 * Case du terrain de jeu du D�mineur
 * @author Gauthier GUIROLA-BO�
 * @author Morgan NAYET
 */
public class Zone {
    
    /** Nom de la case (une lettre suivit d'un nombre)  */
    private String nom;
    
    /** �tat de la case ('C' pour cach�e, 'M' pour march�e et 'X' pour bloqu�e) */
    private char etat;
    
    /** indique si la case est une bombe ou non */
    private boolean bombe;
    
    /** nombre de bombe a proximit� de la case */
    private int bombesAProximite;
    
    /** Etat de la case par d�faut */
    public char INITIALISATION_ETAT = 'C';

    /** Aucune bombe lors de l'initialisation */
    public static boolean INITIALISATION_BOMBE = false; 

    /** Aucune bombe � proximit� lors de l'initialisation */
    public static int INITIALISATION_BOMBE_A_PROXIMITE = 0;
    
    /** Etat de la zone si cach�e */
    public static final char ETAT_CACHEE = 'C';
    
    /** Etat de la zone si march�e */
    public static final char ETAT_MARCHEE = 'M';
    
    /** Etat de la zone si bloqu�e */
    public static final char ETAT_BLOQUEE = 'X';
    
    /**
     * Constructeur qui initialise les caract�ristiques de la Zone avec 
     * les valeurs en arguments
     * @param nom nom de la Zone
     * @param etat �tat de la Zone(cach�, march�, bloqu�)
     * @param bombe indique si la bombe est min� ou non
     */
    public Zone(String nom, char etat, boolean bombe) {
    	super();
        if (!nomValide(nom) 
        		|| (etat != ETAT_CACHEE && etat != ETAT_MARCHEE & etat != ETAT_BLOQUEE)) {
            throw new IllegalArgumentException("la case est invalide");
        }
        this.nom = nom;
        this.etat = etat;
        this.bombe = bombe;
        this.bombesAProximite = INITIALISATION_BOMBE_A_PROXIMITE;
    	
    }
    
    
    /** 
     * Constructeur qui initialise les caract�ristiques de la Zone avec 
     * les valeurs en arguments
     * @param nom nom de la Case
     */
    public Zone(String nom) {
        super();
        if ( !nomValide(nom) ) {
            throw new IllegalArgumentException("la case est invalide");
        }
        this.nom = nom;
        this.etat = INITIALISATION_ETAT;
        this.bombe = INITIALISATION_BOMBE;
        this.bombesAProximite = INITIALISATION_BOMBE_A_PROXIMITE;
    }
    
    
    
    /**
     * D�finie si le nom de la case est valide
     * @param nom
     * @return vrai si le nom est correcte
     *          faux si il ne l'est pas
     */
    private boolean nomValide(String nom) {
        
        String numeroChaine = nom.substring(1,nom.length()); 
        
        int numero = Integer.parseInt(numeroChaine);
        
        return (2 <= nom.length() && nom.length() <= 3
                && 'A' <= nom.charAt(0) 
                && nom.charAt(0) <= 'Z'
                && 1 <= numero && numero <= 16);
    }

    /**
     * Accesseur sur le nom
     * @return nom la valeur de nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Accesseur sur l'etat de la case
     * @return etat un caract�re contenant l'�tat de la case
     */
    public char getEtat() {
        return etat;
    }
    
    /**
     * Modifie l'�tat de la zone de cach�e � march�e avec gestion d'erreur
     * @return true si la zone a �t� march�e
     *         false si la zone n'a pas pu �tre march�e
     */
    public boolean marcher() {
        if (etat == ETAT_CACHEE) {
            etat = ETAT_MARCHEE;
            Demineur.nbZonesMarchees++;
            return true;
        } else {
            System.out.println("Vous ne pouvez pas marcher sur une Zone "
                    + "bloquee ou marchee\n");
            return false;
        }
    }
    
    /**
     * Modifie l'�tat de la zone de cach�e � bloqu�e avec gestion d'erreur
     * @return true si la zone s'est bloqu�e
     *         false si la zone n'a pas pu �tre bloqu�e
     */
    public boolean bloquer() {
        if (etat == ETAT_CACHEE) {
            etat = ETAT_BLOQUEE;
            return true;
        } else {
            System.out.println("Vous ne pouvez pas bloquer une Zone deja "
                               + "bloquee ou marchee\n");
            return false;
        }
    }
    
    /**
     * Modifie l'�tat de la zone de bloqu�e � d�bloqu�e avec gestion d'erreur
     * @return true si la zone a �t� d�bloqu�e
     *         false si la zone n'a pas pu �tre debloqu�e
     */
    public boolean debloquer() {
        if (etat == ETAT_BLOQUEE) {
            etat = ETAT_CACHEE;
            return true;
        } else {
            System.out.println("Vous ne pouvez pas debloquer une Zone qui n'est"
                    + " pas bloquee\n");
            return false;
        }
    }
    
    /**
     * Accesseur sur l'etat de la case
     * @return une boolean �gal a vrai si la case est une bombe ou
     *                            faux si la case n'est pas une bombe
     */
    public boolean getBombe() {
        return bombe;
    }
    
    /**
     * Modificateur pour l'�tat de la case 
     * @param bombePresent  
     */
    public void setBombe(boolean bombePresent) {
        bombe = bombePresent;
    }
    
    /**
     * Accesseur sur le nombre de bombe a proximit�
     * @return la valeur de bombesAProximit�
     */
    public int getBombesAProximite() {
        return bombesAProximite;
    }
    
    /**
     * Modificateur pour le nombre de bombe � proximit�
     * @param zoneAProximite un tableau de r�f�rences des zones entourant la zone
     */
    public void setBombeAProximite(Zone[] zoneAProximite) {
        
        for( int i = 0 ; i < zoneAProximite.length ; i++ ) {
            if (zoneAProximite[i].getBombe()) {
                this.bombesAProximite = this.bombesAProximite + 1;
            }
        }
        
    }

    
    /* non javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        String nbBombeAProximite = String.valueOf(getBombesAProximite());
        String chaineRetour;
        
        chaineRetour = "";
        if (this.bombe && this.etat==ETAT_MARCHEE) {
            chaineRetour = "@";
        } else if (!this.bombe && this.etat==ETAT_MARCHEE) {
            chaineRetour = nbBombeAProximite;
        } else if (this.etat==ETAT_BLOQUEE) {
            chaineRetour = "X";
        } else if (this.etat==ETAT_CACHEE) {
            chaineRetour = "-";
        }
        return chaineRetour;
    }

    
}