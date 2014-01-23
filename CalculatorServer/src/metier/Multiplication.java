/*---------------------------------------------------------------*/
/** Fichier : Multiplication.java
 *
 * créé le 23 janv. 2014 à 08:14:07
 *
 * Auteurs : Léo Riera & Vincent Voyer
 */
package metier;

/*---------------------------------------------------------------*/
/**
 * @author vivoyer
 *
 */
public class Multiplication implements Operation
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
		return operande1 * operande2;
	}

}


/*---------------------------------------------------------------*/
/* Fin du fichier Multiplication.java
/*---------------------------------------------------------------*/