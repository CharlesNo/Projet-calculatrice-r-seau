/*---------------------------------------------------------------*/
/**
 * Fichier : ClientSender.java
 * 
 * créé le 23 janv. 2014 à 08:28:25
 * 
 * Auteurs : Léo Riera & Vincent Voyer
 */
package services;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Vector;

/*---------------------------------------------------------------*/
/**
 * The Class ClientSender.
 * 
 * @author vivoyer
 *         chneau
 */
public class ClientSender implements Runnable
{
	/** The m client info. */
	private final ClientInfo		clientInfo;
	/** The m message queue. */
	private final Vector<String>	messages	= new Vector<>();
	/** The m out. */
	private final PrintWriter		out;
	/** The thread. */
	private final Thread			thread;

	/**
	 * Instantiates a new client sender.
	 * 
	 * @param aClientInfo
	 *            the a client info
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public ClientSender(final ClientInfo aClientInfo) throws IOException
	{
		thread = new Thread(this);
		clientInfo = aClientInfo;
		out = new PrintWriter(new OutputStreamWriter(aClientInfo.getSocket()
				.getOutputStream()));
	}

	/**
	 * Gets the next message from queue.
	 * 
	 * @return and deletes the next message from the message queue. If the queue
	 *         is empty, falls in sleep until notified for message arrival by
	 *         sendMessage
	 *         method.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	private synchronized String getNextMessageFromQueue()
			throws InterruptedException
	{
		while (messages.size() == 0)
		{
			wait();
		}
		final String message = messages.get(0);
		messages.removeElementAt(0);
		return message;
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

	/**
	 * Until interrupted, reads messages from the message queue
	 * and sends them to the client's socket.
	 */
	@Override
	public void run()
	{
		try
		{
			while (!isInterrupted())
			{
				final String message = getNextMessageFromQueue();
				sendMessageToClient(message);
			}
		}
		catch (final Exception e)
		{
			// Commuication problem
		}
		// Communication is broken. Interrupt both listener and sender threads
		clientInfo.getClientReciever().interrupt();
	}

	/**
	 * Adds given message to the message queue and notifies this thread
	 * (actually getNextMessageFromQueue method) that a message is arrived.
	 * sendMessage is called by other threads (ServeDispatcher).
	 * 
	 * @param aMessage
	 *            the a message
	 */
	public synchronized void sendMessage(final String aMessage)
	{
		messages.add(aMessage);
		notify();
	}

	/**
	 * Sends given message to the client's socket.
	 * 
	 * @param aMessage
	 *            the a message
	 */
	private void sendMessageToClient(final String aMessage)
	{
		out.println(aMessage);
		out.flush();
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
