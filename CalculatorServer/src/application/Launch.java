/*---------------------------------------------------------------*/
/**
 * Fichier : Launch.java
 * 
 * créé le 23 janv. 2014 à 08:25:58
 * 
 * Auteurs : Léo Riera & Vincent Voyer
 */
package application;

/*---------------------------------------------------------------*/
/**
 * The Class Launch.
 * 
 * @author vivoyer
 *         chneau
 */
public class Launch
{
	/*---------------------------------------------------------------*/
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args)
	{
		final Server server = new Server(5042);
		server.start();
		try
		{
			server.join();
		}
		catch (final InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
/*---------------------------------------------------------------*/
/*
 * Fin du fichier Launch.java
 * /*---------------------------------------------------------------
 */
