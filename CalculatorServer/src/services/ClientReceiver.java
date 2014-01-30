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
import java.util.Observer;

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
	/** The run. */
	private boolean					run;
	/** The thread. */
	private Thread					thread;

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
		run = false;
		clientInfo = aClientInfo;
		addObserver(clientInfo);
		reader = new BufferedReader(new InputStreamReader(aClientInfo
				.getSocket().getInputStream()));
	}

	/* _________________________________________________________ */
	/**
	 * Adds the observer.
	 * 
	 * @param observer
	 *            the observer
	 * @see java.util.Observable#addObserver(java.util.Observer)
	 */
	@Override
	public synchronized void addObserver(final Observer observer)
	{
		super.addObserver(observer);
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
			while (!isInterrupted() && run)
			{
				Log.d("ClientReceiver", "run()");
				final String message = reader.readLine();
				Log.d("ClientReceiver", "run() + message = " + message);
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
		if (thread == null)
		{
			run = true;
			thread = new Thread(this);
			thread.setDaemon(true);
			thread.setName("ClientReceiver");
			thread.start();
			Log.d("ClientReceiver", "start()");
		}
	}
}
