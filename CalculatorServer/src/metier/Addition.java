/*---------------------------------------------------------------*/
/**
 * Fichier : Addition.java
 * 
 * créé le 23 janv. 2014 à 08:12:58
 * 
 * Auteurs : Léo Riera & Vincent Voyer
 */
package metier;

/*---------------------------------------------------------------*/
/**
 * The Class Addition.
 * 
 * @author vivoyer
 *         chneau
 */
public class Addition implements Operation
{
	/*---------------------------------------------------------------*/
	/**
	 * Operation.
	 * 
	 * @param operande1
	 *            the operande1
	 * @param operande2
	 *            the operande2
	 * @return le résultat de l'addition.
	 * @see metier.Operation#operation(float, float)
	 */
	@Override
	public float operation(final float operande1, final float operande2)
	{
		return operande1 + operande2;
	}
}
/*---------------------------------------------------------------*/
/*
 * Fin du fichier Addition.java
 * /*---------------------------------------------------------------
 */
