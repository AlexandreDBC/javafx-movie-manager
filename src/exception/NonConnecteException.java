package exception;

/**
 * Exception levée quand il y a un problème avec le compte d'un utilisateur ou
 * sa connexion.
 *
 * @author Alexandre De Brito
 */
public class NonConnecteException extends Exception {
  
  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = -2876441299971092712L;
  
  /**
   * Constructeur sans message spécifique.
   */
  public NonConnecteException() {
    super("Utilisateur non connecté.");
  }
}
