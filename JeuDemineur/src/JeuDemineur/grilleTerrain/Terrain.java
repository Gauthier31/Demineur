/* Terrain.java                                                      25 mai 2021
 * IUT Info1 2020-2021, pas de copyright, pas de droit d'auteur
 */
package JeuDemineur.grilleTerrain;
import JeuDemineur.blocZone.Zone;
import JeuDemineur.choixDifficulte.Difficulte;
import JeuDemineur.Demineur;

/**
 * Instancie un objet de type Terrain avec un nom, une difficult� et 
 * un tableau � 2 dimension.
 *  
 * @author Morgan Nayet
 * 
 */
public class Terrain {
    
    /** Le nombre de ligne maximal du JeuDemineur (de A � Z) */
    public static final int NB_LIGNE_MAX = 26;
    
    /** Le nombre de colonne maximal du JeuDemineur (de 1 � 26) */
    public static final int NB_COLONNE_MAX = 26;
    
    /** Dimensions du JeuDemineur par les lignes et les colonnes  */
    public static final int[] NB_LIGNES_COLONNES = new int[] {8,8};
    
    /** Contient le code caract�re de la premi�re ligne du terrain */
    public static final char PREMIERE_LIGNE_TERRAIN = 'A';
    
    /** Contient le code caract�re de la premi�re colonne du terrain */
    public static final int PREMIERE_COLONNE_TERRAIN = '1';
        
    /** Identifiant du terrain */ 
    private String nom;
    
    /** Dimensions du terrain*/
    private int[] dimension;
    
    /** Nombre de bombes au total dans le terrain */
    private int nbBombes;
    
    /** tableau � 2 dimensions qui r�f�rence les Zones du tableau
     *  Les lignes du tableau sont les lettres de A � Z
     *  (il peut y avoir moins de lignes mais au moins une. 
     *  Elles doivent uniquement suivre l'ordre alphab�tique)
     *  
     *  Les colonnes sont les nombre de 1 � 26
     *  (il peut y avoir moins de colonnes mais au moins une. 
     *  L'odre des colonnes doit �tre celui des nombres entiers naturels)
     */
    private Zone[][] listeZones;
    
    /**
     * Cr�� un objet Terrain associ� � une partie de jeu unique
     * @param difficulte
     */
    public Terrain(Difficulte difficulte) {
        this.dimension = difficulte.getDimension();
        this.listeZones = creationListeZones(this.dimension);
        this.nbBombes = difficulte.getNbBombes();
        
        repartitionBombes(this.listeZones, this.nbBombes);
        repartitionBombesAProximite();
    }

    /**
     * @return la valeur de nom
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * @return la valeur de nbBombes
     */
    public int getNbBombes() {
        return nbBombes;
    }

    /**
     * @return la valeur de listeZones
     */
    public Zone[][] getListeZones() {
        return listeZones;
    }
    
    /**
     * @return la valeur de dimension
     */
    public int[] getDimension() {
        return dimension;
    }
    
    /**
     * Affiche le terrain  du Demineur et son etat a un instant T
     * @return grille retourne un visuel des differentes zones � un instant donn�
     */
    public String toString() {
        
        String grille = "     |  ";
        
        for ( int l = 0 ; l < this.listeZones[0].length ; l++ ) {
            grille += (l+1);
            grille += "  |  ";
        }
        grille += "\n";
        for ( int l = 0 ; l < this.listeZones[0].length + 1 ; l++ ) {
            grille += "-----|";
        }
        
        for (int i = 0 ; i < this.listeZones.length ; i++) {
            grille += "\n";
            grille += "  " + this.listeZones[i][0].getNom().charAt(0) + "  |  ";
            for (int y = 0 ; y < this.listeZones[0].length ; y++ ) {
                grille += this.listeZones[i][y].toString();
                grille += "  |  ";
            }
            grille += "\n";
            for (int y = 0 ; y < this.listeZones[0].length + 1 ; y++ ) {
                grille += "-----|";
            }
        }
        
        return grille;
        
    }
    
    /**
     * A partir d'une zone choisie par l'utilisateur, cette m�thode regarde si 
     * cette zone ne poss�de aucune bombes � proximit�. Dans ce cas, elle change 
     * automatiquement l'�tat des zones adjacente � "machee".
     * Dans le cas o� cette zone au moins une bombe � proximit� 
     * (ou poss�de elle-m�me une bombe), la m�thode change uniquement l'�tat de
     * cette case par "marchee".
     * 
     * @param zoneAMarcher
     * @return true si la/les zone(s) ont �t� march�e(s)
     *         false si la/les zone(s) n'ont pas pu �tre march�e(s)
     */
    public boolean marcherAutomatiquement( Zone zoneAMarcher ) {
        Zone[] zonesAlentours;
        boolean aMarchee;
        
        aMarchee = zoneAMarcher.marcher();
        if ( !zoneAMarcher.getBombe() && zoneAMarcher.getBombesAProximite() == 0) {
            
            zonesAlentours = trouverZonesAlentours(zoneAMarcher);
            for ( int i = 0 ; i < zonesAlentours.length ; i++ ) {
                
                if ( !zonesAlentours[i].getBombe() && zonesAlentours[i].getEtat() != 'M') {
                    if (zonesAlentours[i].getBombesAProximite() == 0) {
                        marcherAutomatiquement( zonesAlentours[i] );
                    } else {
                        zonesAlentours[i].marcher();
                    }
                }
            }
        }
        return aMarchee;
        
    }
    
    /**
     * D�finie la longueur du tableau renvoy� selon l'emplacement de de la zone
     * donn�e en argument.
     * Ins�re les zones alentours � la zone donn�e en argument dans un tableau
     * selon l'ordre du Z-order.
     * 
     * @param zoneSpecifique
     * @return zonesAlentours un tableau de Zone de longueur correspondant au 
     *                        nombre de zones entourant la zoneSpecifique 
     */
    public Zone[] trouverZonesAlentours(Zone zoneSpecifique) {
        
        Zone[] zonesAlentours;
        int i;
        int y;
        String nomZone = zoneSpecifique.getNom();
        
        i = nomZone.charAt(0) - PREMIERE_LIGNE_TERRAIN;
        y = Integer.parseInt( nomZone.substring( 1, nomZone.length() ) ) - 1;
        
        if ( (i==0 && y==0)
             || (i==dimension[0]-1 && y==0)
             || (i==0 && y==dimension[1]-1) 
             || (i==dimension[0]-1 && y == dimension[1]-1 ) ) {
            
            /*Initialise le tableau de 3 rang si la zone est un coin du terrain*/
            zonesAlentours = new Zone[3];
            
            
        } else if ( (i==0) || (i==dimension[0]-1) || (y==0) 
                    || (y==dimension[1]-1) ) {
            
            /*Initialise le tableau de 5 rang si la zone 
             * est sur une bordure du terrain mais n'est pas dans un coin 
             */
            zonesAlentours = new Zone[5]; 
        } else {
            
            /* Initialise le tableau de 8 rang 
             * si la zone n'est pas sur une bordure du terrain 
             */
            zonesAlentours = new Zone[8];
        }
        
        for( int indice = 0 ; indice < zonesAlentours.length ; indice++ ) {
            
         // en haut a gauche
            if (indice < zonesAlentours.length && i!=0 && y!=0) {
                zonesAlentours[indice] = this.listeZones[i-1][y-1];
                indice++;
            }

            // en haut
            if (indice < zonesAlentours.length && i!=0) {
                zonesAlentours[indice] = this.listeZones[i-1][y];
                indice++;
            }

            // en haut a droite
            if (indice < zonesAlentours.length && i!=0 && y!=(dimension[1] - 1) ) {
                zonesAlentours[indice] = this.listeZones[i-1][y+1];
                indice++;
            }
            
            // a gauche
            if (indice < zonesAlentours.length && y!=0) {
                zonesAlentours[indice] = this.listeZones[i][y-1];
                indice++;
            }

            // a droite
            if (indice < zonesAlentours.length && y!=(dimension[1] - 1)) {
                zonesAlentours[indice] = this.listeZones[i][y+1];
                indice++;
            }
            
            // en bas a gauche
            if (indice < zonesAlentours.length && i!=(dimension[0] - 1) && y!=0) {
                zonesAlentours[indice] = this.listeZones[i+1][y-1];
                indice++;
            }
            
            // en bas
            if (indice < zonesAlentours.length && i!=(dimension[0] - 1)) {
                zonesAlentours[indice] = this.listeZones[i+1][y];
                indice++;
            }

            // en bas a droite
            if (indice < zonesAlentours.length && i!=(dimension[0] - 1) && y!=(dimension[1] - 1)) {
                zonesAlentours[indice] = this.listeZones[i+1][y+1];
                indice++;
            }
        }
        
        return zonesAlentours;
    }
    
    /**
     * cherche dans la liste de zones du terrain la zone recherch�e en argument
     * 
     * @param zoneATrouver cha�ne de caract�res portant le nom de la zone
     * @return zoneARenvoyer renvoie la r�f�rence de la zone rechech�e si la zone existe
     *                       renvoie null si la zone cherch�e n'existe pas
     */
    public Zone trouverZone(String zoneATrouver) {
        Zone zoneARenvoyer;
        
        zoneARenvoyer = null; //stub
        for (int i = 0 ; i < listeZones.length ; i++) {
            for (int y = 0 ; y <listeZones[0].length ; y++) {
                if (listeZones[i][y].getNom().equals(zoneATrouver) ) {
                    zoneARenvoyer = listeZones[i][y];
                }
            }
        }
        
        return zoneARenvoyer;
    }
    
    /**
     * La m�thode automatiquement les Zones du terrain selon les dimensions
     * donn�es en argument avec un nommage des Zones et un 
     * rangement de ces Zones dans un tableau par lignes (lettre de A � Z) 
     * et par colonnes (nombre de 1 � 26)
     * Gestion d'erreur dans les valeurs donn�es par les dimensions
     * ( les dimensions ne doivent pas �tre inf�rieures � 1x1 
     * et sup�rieures � 26x26 )
     * 
     * 
     * @param dimension dimension du terrain avec 
     *                  en premier rang le nombre de lignes et
     *                  en deuxi�me rang le nombre de colonnes du terrain 
     *                  
     * @return listeZones[][] renvoie un tableau � 2 dimensions contenant 
     *                        la r�f�rence des objets Zone cr��s.
     */
    public Zone[][] creationListeZones( int[] dimension ) {
        
        if ( NB_LIGNE_MAX < dimension[0] || NB_COLONNE_MAX < dimension[1]
//                || dimension[0] < PREMIERE_LIGNE_TERRAIN 
//                || dimension[1] < PREMIERE_COLONNE_TERRAIN 
                ){
            throw new IllegalArgumentException("dimension impossible");
        }
        Zone[][] listeZones = new Zone[ dimension[0] ][ dimension[1] ];
        String nomZone;  
        
        nomZone = ""; //stub
        for ( int i = 0 ; i < dimension[0] ; i++ ) {
            for ( int y = 0 ; y < dimension[1] ; y++ ) {
                
                nomZone = "" + (char)(PREMIERE_LIGNE_TERRAIN + i) + ( y + 1 );
                listeZones[i][y] = new Zone(nomZone);
            }
        }
        
        return listeZones;
    }
    
    
    /**
     * Change l'attribue bombe en false sur une zone du terrain dont
     * les coordonn�es ont �t� selectionn�es au hasard et si la zone n'est pas 
     * d�j� min�e.
     * L'op�ration se r�p�te jusqu'� ce que l'ensemble des bombes soit r�partie.
     * 
     * @param listeZones correspond � l'ensemble des zones du terrain.
     * @param nbBombes nombre de bombes � r�partir sur le terrain.
     */
    public void repartitionBombes(Zone[][] listeZones, int nbBombes) {
        int noLigne;
        int noColonne;
        
        for (int i = nbBombes ; 0 < i ; i-- ) {
            
            noLigne = (int)(Math.random() * 100000) % listeZones.length;
            noColonne = (int)(Math.random() * 100000) % listeZones[0].length;
            
            if (this.listeZones[noLigne][noColonne].getBombe()) {
                i++;
            } else {
                this.listeZones[noLigne][noColonne].setBombe(true);
            }
        }
    }
    
    /**
     * Initialise le nombre de bombes a proximite de chaque zone
     *
     */
    public void repartitionBombesAProximite() {
        Zone zoneSpecifique;
        
        for ( int i = 0 ; i < dimension[0] ; i++ ) {
            for (int y = 0 ; y < dimension[1] ; y++ ) {
                zoneSpecifique = this.listeZones[i][y];
                if ( !zoneSpecifique.getBombe() ) {
                    zoneSpecifique.setBombeAProximit�(trouverZonesAlentours(zoneSpecifique));
                }
            }
        }
    }

}
