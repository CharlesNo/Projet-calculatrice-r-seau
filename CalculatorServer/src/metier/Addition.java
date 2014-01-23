/*---------------------------------------------------------------*/
/** Fichier : Addition.java
 *
 * créé le 23 janv. 2014 à 08:12:58
 *
 * Auteurs : Léo Riera & Vincent Voyer
 */
package metier;

import java.util.Set;

import javax.management.openmbean.OpenMBeanParameterInfo;
import javax.management.openmbean.OpenType;

/*---------------------------------------------------------------*/
/**
 * @author vivoyer
 *
 */
public class Addition implements Operation
{
	/*---------------------------------------------------------------*/
	/**
	 * @param operande1
	 * @param operande2
	 * @return le résultat de l'addition.
	 * @see metier.Operation#operation(float, float)
	 */
	@Override
	public float operation(float operande1, float operande2)
	{
		return operande1 + operande2;
	}

}


/*---------------------------------------------------------------*/
/* Fin du fichier Addition.java
/*---------------------------------------------------------------*/