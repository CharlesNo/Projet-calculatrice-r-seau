/*---------------------------------------------------------------*/
/**
 * Fichier : Division.java
 * 
 * créé le 23 janv. 2014 à 08:14:39
 * 
 * Auteurs : Léo Riera & Vincent Voyer
 */
package metier;

/*---------------------------------------------------------------*/
/**
 * The Class Division.
 * 
 * @author vivoyer
 *         chneau
 */
public class Division implements Operation
{
	/*---------------------------------------------------------------*/
	/**
	 * Operation.
	 * 
	 * @param operande1
	 *            the operande1
	 * @param operande2
	 *            the operande2
	 * @return the float
	 * @see metier.Operation#operation(float, float)
	 */
	@Override
	public float operation(final float operande1, final float operande2)
	{
		return (operande2 == 0 ? 0 : operande1 / operande2);
	}
}
/*---------------------------------------------------------------*/
/*
 * Fin du fichier Division.java
 * /*---------------------------------------------------------------
 */
