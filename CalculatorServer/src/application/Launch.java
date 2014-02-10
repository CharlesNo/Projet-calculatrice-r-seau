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
	// TODO LAURENCO
	/**
	 * The main method.
	 * Envoyer à : laurenco@isima.fr
	 * pour mardi à midi.
	 * Avec un mode d'emploi et le projet entier.
	 * Mode d'emploi = quoi tapper / comment l'utiliser.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args)
	{
		Server server = null;
		if (args.length > 0)
		{
			server = Server.getInstance(Integer.parseInt(args[0]));
		}
		else
		{
			server = Server.getInstance(5042);
		}
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
