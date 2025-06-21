/*
 * Assertions.java                                             7 avr. 2021
 * IUT info1 2020-2021 groupe 3, pas de copyright, pas de droit d'auteur
 */
package JeuDemineur.outils.glg;

/**
 * Assertions à utiliser pour écrire des tests unitaires
 * qui propagent EchecTest en cas de test insatisfait
 * @author info1 2020-2021
 */
public class Assertions {
    
    /**
     * Assertion de test réussissant si une expression booléenne est vraie
     * @param condition l'expression booléenne à tester
     */
    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new EchecTest();
        }
    }

    /** 
     * Assertion de test réussissant si la valeur obtenue est équivalente à 
     * la valeur attendue au sens de equals (@see java.lang.object.equals)
     * @param attendu résultat attendu à comparer avec obtenu
     * @param obtenu  résultat obtenu  à comparer avec attendu
     */
    public static void assertEquivalent(Object attendu, Object obtenu) {
        assertTrue(attendu.equals(obtenu)); 
    }
    
    /** 
     * Assertion de test réussissant si la valeur d'une expression entière 
     * obtenue est égale (==) à la valeur attendue 
     * @param attendu résultat attendu à comparer avec obtenu
     * @param obtenu  résultat obtenu  à comparer avec attendu
     */
    public static void assertEquivalent(int attendu, int obtenu) {
        assertTrue(attendu == obtenu); 
    }
    
    /** 
     * Assertion en échec systématique
     * pour signaler qu'une méthode de test est incomplète ou erronnée
     */
    public static void echec() {
        assertTrue(false);
    }
}
