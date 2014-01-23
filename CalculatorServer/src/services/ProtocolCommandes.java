/*---------------------------------------------------------------*/
/** Fichier : ProtocolCommandes.java
 *
 * créé le 23 janv. 2014 à 08:35:13
 *
 * Auteurs : Léo Riera & Vincent Voyer
 */
package services;

/*---------------------------------------------------------------*/
/**
 * @author vivoyer
 *
 */
public enum ProtocolCommandes
{
	ADD("ADD"),
	MIN("MIN"),
	DIV("DIV"),
	MUL("MUL"),
	OP1("OP1"),
	OP2("OP2"),
	RES("RES"),
	KIT("KIT");
	
	private final String value;
	
	private ProtocolCommandes(final String value)
	{
		this.value= value;
	}
	/*---------------------------------------------------------------*/
	/**
	 * @return
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString()
	{
		return value;
	}
}


/*---------------------------------------------------------------*/
/* Fin du fichier ProtocolCommandes.java
/*---------------------------------------------------------------*/