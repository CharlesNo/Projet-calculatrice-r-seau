/*---------------------------------------------------------------*/
/** Fichier : Division.java
 *
 * créé le 23 janv. 2014 à 08:14:39
 *
 * Auteurs : Léo Riera & Vincent Voyer
 */
package metier;

/*---------------------------------------------------------------*/
/**
 * @author vivoyer
 *
 */
public class Division implements Operation
{

	/*---------------------------------------------------------------*/
	/**
	 * @param operande1
	 * @param operande2
	 * @return
	 * @see metier.Operation#operation(float, float)
	 */
	@Override
	public float operation(float operande1, float operande2)
	{
		return (operande2 == 0 ? 0 : operande1 / operande2);
	}

}


/*---------------------------------------------------------------*/
/* Fin du fichier Division.java
/*---------------------------------------------------------------*/