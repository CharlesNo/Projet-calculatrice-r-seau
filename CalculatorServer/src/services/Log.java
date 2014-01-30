/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : Log.java
 * 
 * Créé le 30 janv. 2014 à 08:59:07
 * 
 * Auteur : Charles NEAU
 */
package services;

/* _________________________________________________________ */
/**
 * @author Charles NEAU
 * 
 */
public class Log
{
	/** The Constant SEP. */
	private static final String	SEP	= "|";

	/* _________________________________________________________ */
	/**
	 * D.
	 * 
	 * @param class1
	 *            the string
	 * @param message
	 *            the string2
	 */
	public static void d(final String class1, final String message)
	{
		System.out.println(SEP + class1 + SEP + message + SEP);
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier Log.java.
 * /*_________________________________________________________
 */
