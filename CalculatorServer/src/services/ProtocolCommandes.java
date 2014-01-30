/*---------------------------------------------------------------*/
/**
 * Fichier : ProtocolCommandes.java
 * 
 * créé le 23 janv. 2014 à 08:35:13
 * 
 * Auteurs : Léo Riera & Vincent Voyer
 */
package services;

/*---------------------------------------------------------------*/
/**
 * The Enum ProtocolCommandes.
 * 
 * @author vivoyer
 *         chneau
 */
public enum ProtocolCommandes
{
	/** The add. */
	ADD("ADD"),
	/** The min. */
	MIN("MIN"),
	/** The div. */
	DIV("DIV"),
	/** The mul. */
	MUL("MUL"),
	/** The O p1. */
	OP1("OP1"),
	/** The O p2. */
	OP2("OP2"),
	/** The res. */
	RES("RES"),
	/** The quit. */
	QUIT("QUIT"),
	/** The tim. */
	TIM("TIM"),
	/** The req. */
	REQ("REQ"),
	/** The stat. */
	STAT("STAT"),
	/** The sep. */
	SEP(" ");
	/** The value. */
	private final String	value;

	/**
	 * Instantiates a new protocol commandes.
	 * 
	 * @param value
	 *            the value
	 */
	private ProtocolCommandes(final String value)
	{
		this.value = value;
	}

	/*---------------------------------------------------------------*/
	/**
	 * To string.
	 * 
	 * @return the string
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString()
	{
		return value;
	}
}
/*---------------------------------------------------------------*/
/*
 * Fin du fichier ProtocolCommandes.java
 * /*---------------------------------------------------------------
 */
