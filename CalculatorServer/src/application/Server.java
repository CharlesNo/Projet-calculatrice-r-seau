/*---------------------------------------------------------------*/
/** Fichier : Server.java
 *
 * créé le 23 janv. 2014 à 08:26:32
 *
 * Auteurs : Léo Riera & Vincent Voyer
 */
package application;

import java.net.ServerSocket;
import java.util.GregorianCalendar;
import java.util.List;

import services.ClientInfo;

/*---------------------------------------------------------------*/
/**
 * @author vivoyer
 *
 */
public class Server implements Runnable
{
	private static final int port = 5042;
	
	private GregorianCalendar start;
	
	private List<ClientInfo> clients;
	
	private ServerSocket serverSocket;
	
	/*---------------------------------------------------------------*/
	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		// PENSER à IMPLEMENTER Auto-generated method stub

	}

}


/*---------------------------------------------------------------*/
/* Fin du fichier Server.java
/*---------------------------------------------------------------*/