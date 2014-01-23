/*---------------------------------------------------------------*/
/** Fichier : Operation.java
 *
 * créé le 23 janv. 2014 à 08:11:29
 *
 * Auteurs : Léo Riera & Vincent Voyer
 */
package metier;

/*---------------------------------------------------------------*/
/**
 * @author vivoyer
 *
 */
public interface Operation
{
	/*---------------------------------------------------------------*/
	/**
	 * Effectue une opération avec les opérandes passées en paramètre.
	 * @param operande1 opérande 1.
	 * @param operande2 opérande 2.
	 * @return le résultat de l'opération.
	 */
	public float operation(float operande1, float operande2);
}


/*---------------------------------------------------------------*/
/* Fin du fichier Operation.java
/*---------------------------------------------------------------*/