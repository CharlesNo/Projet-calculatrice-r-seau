/*---------------------------------------------------------------*/
/**
 * Fichier : ClientInfo.java
 * 
 * créé le 23 janv. 2014 à 08:26:59
 * 
 * Auteurs : Léo Riera & Vincent Voyer
 */
package services;

import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

/*---------------------------------------------------------------*/
/**
 * The Class ClientInfo.
 * 
 * @author vivoyer
 *         chneau
 */
public class ClientInfo extends Observable implements Observer
{
	/** The m client listener. */
	private ClientReceiver	clientReceiver	= null;
	/** The m client sender. */
	private ClientSender	clientSender	= null;
	/** The socket. */
	private final Socket	socket;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new client info.
	 * 
	 * @param socket
	 *            the accept
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ClientInfo(final Socket socket) throws IOException
	{
		this.socket = socket;
		clientReceiver = new ClientReceiver(this);
		clientSender = new ClientSender(this);
		clientReceiver.start();
		clientSender.start();
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
	 * Equals.
	 * 
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final ClientInfo other = (ClientInfo) obj;
		if (socket == null)
		{
			if (other.socket != null)
			{
				return false;
			}
		}
		else if (!socket.equals(other.socket))
		{
			return false;
		}
		return true;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ clientReceiver.
	 * 
	 * @return la valeur du champ clientReceiver.
	 */
	public final ClientReceiver getClientReciever()
	{
		return clientReceiver;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ clientSender.
	 * 
	 * @return la valeur du champ clientSender.
	 */
	public final ClientSender getClientSender()
	{
		return clientSender;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ socket.
	 * 
	 * @return la valeur du champ socket.
	 */
	public final Socket getSocket()
	{
		return socket;
	}

	/* _________________________________________________________ */
	/**
	 * Hash code.
	 * 
	 * @return the int
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((socket == null) ? 0 : socket.hashCode());
		return result;
	}

	/* _________________________________________________________ */
	/**
	 * Interrupt.
	 * 
	 * @see ClientSender#interrupt()
	 * @see ClientReceiver#interrupt()
	 */
	public void interrupt()
	{
		clientSender.interrupt();
		clientReceiver.interrupt();
	}

	/* _________________________________________________________ */
	/**
	 * Notify observers.
	 * 
	 * @param object
	 *            the object
	 * @see java.util.Observable#notifyObservers(java.lang.Object)
	 */
	@Override
	public void notifyObservers(final Object object)
	{
		setChanged();
		super.notifyObservers(object);
	}

	/* _________________________________________________________ */
	/**
	 * Update.
	 * 
	 * @param observable
	 *            the observable
	 * @param object
	 *            the object
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(final Observable observable, final Object object)
	{
		if (observable instanceof ClientReceiver)
		{
			Log.d("ClientInfo", "update() " + object);
			notifyObservers(object);
		}
	}
}
