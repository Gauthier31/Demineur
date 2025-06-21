/*
 * Assertions.java                                             7 avr. 2021
 * IUT info1 2020-2021 groupe 3, pas de copyright, pas de droit d'auteur
 */
package JeuDemineur.outils.glg;

/**
 * Assertions � utiliser pour �crire des tests unitaires
 * qui propagent EchecTest en cas de test insatisfait
 * @author info1 2020-2021
 */
public class Assertions {
    
    /**
     * Assertion de test r�ussissant si une expression bool�enne est vraie
     * @param condition l'expression bool�enne � tester
     */
    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new EchecTest();
        }
    }

    /** 
     * Assertion de test r�ussissant si la valeur obtenue est �quivalente � 
     * la valeur attendue au sens de equals (@see java.lang.object.equals)
     * @param attendu r�sultat attendu � comparer avec obtenu
     * @param obtenu  r�sultat obtenu  � comparer avec attendu
     */
    public static void assertEquivalent(Object attendu, Object obtenu) {
        assertTrue(attendu.equals(obtenu)); 
    }
    
    /** 
     * Assertion de test r�ussissant si la valeur d'une expression enti�re 
     * obtenue est �gale (==) � la valeur attendue 
     * @param attendu r�sultat attendu � comparer avec obtenu
     * @param obtenu  r�sultat obtenu  � comparer avec attendu
     */
    public static void assertEquivalent(int attendu, int obtenu) {
        assertTrue(attendu == obtenu); 
    }
    
    /** 
     * Assertion en �chec syst�matique
     * pour signaler qu'une m�thode de test est incompl�te ou erronn�e
     */
    public static void echec() {
        assertTrue(false);
    }
}
