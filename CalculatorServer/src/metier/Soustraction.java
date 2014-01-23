/*---------------------------------------------------------------*/
/**
 * Fichier : Soustraction.java
 * 
 * créé le 23 janv. 2014 à 08:16:31
 * 
 * Auteurs : Léo Riera & Vincent Voyer
 */
package metier;

/*---------------------------------------------------------------*/
/**
 * The Class Soustraction.
 * 
 * @author vivoyer
 *         chneau
 */
public class Soustraction implements Operation
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
		return operande1 - operande2;
	}
}
/*---------------------------------------------------------------*/
/*
 * Fin du fichier Soustraction.java
 * /*---------------------------------------------------------------
 */
