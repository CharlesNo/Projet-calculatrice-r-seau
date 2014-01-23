/*---------------------------------------------------------------*/
/**
 * Fichier : Server.java
 * 
 * créé le 23 janv. 2014 à 08:26:32
 * 
 * Auteurs : Léo Riera & Vincent Voyer
 */
package application;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import services.ClientInfo;

/*---------------------------------------------------------------*/
/**
 * The Class Server.
 * 
 * @author vivoyer
 */
public class Server implements Runnable
{
	/** The Constant LISTENING_PORT. */
	private static final int		LISTENING_PORT	= 5042;
	/** The clients. */
	private List<ClientInfo>		clients;
	/** The server socket. */
	private ServerSocket			serverSocket;
	/** The start. */
	private final GregorianCalendar	start;
	/** The thread. */
	private final Thread			thread;

	/**
	 * Instantiates a new server.
	 */
	public Server()
	{
		super();
		start = new GregorianCalendar();
		clients = new ArrayList<ClientInfo>();
		thread = new Thread(this);
		clients = new Vector<>();
		serverSocket = null;
		try
		{
			serverSocket = new ServerSocket(LISTENING_PORT);
			System.out.println("Server started on port " + LISTENING_PORT);
		}
		catch (final IOException se)
		{
			System.err.println("Can not start listening on port "
					+ LISTENING_PORT);
			se.printStackTrace();
			System.exit(-1);
		}
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ start.
	 * 
	 * @return la valeur du champ start.
	 */
	public final GregorianCalendar getStart()
	{
		return start;
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
	 * Run.
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		while (!isInterrupted())
		{
			try
			{
				final ClientInfo client = new ClientInfo(serverSocket.accept());
				clients.add(client);
			}
			catch (final IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
		for (final ClientInfo client : clients)
		{
			client.interrupt();
		}
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
/*---------------------------------------------------------------*/
/*
 * Fin du fichier Server.java
 * /*---------------------------------------------------------------
 */
