/*---------------------------------------------------------------*/
/**
 * Fichier : ClientReceiver.java
 * 
 * créé le 23 janv. 2014 à 08:28:12
 * 
 * Auteurs : Léo Riera & Vincent Voyer
 */
package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

/*---------------------------------------------------------------*/
/**
 * The Class ClientReceiver.
 * 
 * @author vivoyer
 *         chneau
 */
public class ClientReceiver extends Observable implements Runnable
{
	/** The m client info. */
	private final ClientInfo		clientInfo;
	/** The m in. */
	private final BufferedReader	reader;
	/** The thread. */
	private final Thread			thread;

	/**
	 * Instantiates a new client listener.
	 * 
	 * @param aClientInfo
	 *            the a client info
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ClientReceiver(final ClientInfo aClientInfo) throws IOException
	{
		thread = new Thread(this);
		clientInfo = aClientInfo;
		reader = new BufferedReader(new InputStreamReader(aClientInfo
				.getSocket().getInputStream()));
	}

	/* _________________________________________________________ */
	/**
	 * Interrupt.
	 * 
	 * @see java.lang.Thread#interrupt()
	 */
	public void interrupt()
	{
		thread.interrupt();
	}

	/* _________________________________________________________ */
	/**
	 * Checks if is interrupted.
	 * 
	 * @return true, if is interrupted
	 * @see java.lang.Thread#isInterrupted()
	 */
	public boolean isInterrupted()
	{
		return thread.isInterrupted();
	}

	/* _________________________________________________________ */
	/**
	 * Notify observers.
	 * 
	 * @param object
	 *            the arg
	 * @see java.util.Observable#notifyObservers(java.lang.Object)
	 */
	@Override
	public void notifyObservers(final Object object)
	{
		setChanged();
		super.notifyObservers(object);
	}

	/**
	 * Until interrupted, reads messages from the client socket, forwards them
	 * to the server dispatcher's queue and notifies the server dispatcher.
	 */
	@Override
	public void run()
	{
		try
		{
			while (!isInterrupted())
			{
				final String message = reader.readLine();
				if (message == null)
				{
					break;
				}
				notifyObservers(message);
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		clientInfo.interrupt();
	}

	/* _________________________________________________________ */
	/**
	 * Start.
	 * 
	 * @see java.lang.Thread#start()
	 */
	public void start()
	{
		thread.start();
	}
}
