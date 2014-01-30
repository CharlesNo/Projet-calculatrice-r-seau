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
import services.AnalyzerCalcul;
import services.ClientInfo;

/*---------------------------------------------------------------*/
/**
 * The Class Server.
 * 
 * @author vivoyer
 *         chneau
 */
public class Server implements Runnable
{
	/** The clients. */
	private List<ClientInfo>		clients;
	/** The port. */
	private final int				port;
	/** The server socket. */
	private ServerSocket			serverSocket;
	/** The start. */
	private final GregorianCalendar	start;
	/** The thread. */
	private Thread					thread;
	/** The run. */
	private boolean					run;

	/**
	 * Instantiates a new server.
	 * 
	 * @param port
	 *            the port
	 */
	public Server(final int port)
	{
		super();
		this.port = port;
		run = false;
		start = new GregorianCalendar();
		clients = new ArrayList<>();
		clients = new Vector<>();
		serverSocket = null;
		try
		{
			serverSocket = new ServerSocket(port);
			System.out.println("Server started on port " + port);
		}
		catch (final IOException se)
		{
			System.err.println("Can not start listening on port " + port);
			se.printStackTrace();
			System.exit(-1);
		}
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ port.
	 * 
	 * @return la valeur du champ port.
	 */
	public int getPort()
	{
		return port;
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
	 * Join.
	 * 
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @see java.lang.Thread#join()
	 */
	public final void join() throws InterruptedException
	{
		thread.join();
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
		while (run)
		{
			try
			{
				final ClientInfo client = new ClientInfo(serverSocket.accept());
				client.addObserver(AnalyzerCalcul.getInstance());
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
		if (thread == null)
		{
			run = true;
			thread = new Thread(this);
			thread.setDaemon(true);
			thread.setName("Server");
			thread.start();
		}
	}

	/**
	 * Stop.
	 * 
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public void stop() throws InterruptedException
	{
		if (thread != null)
		{
			run = false;
			thread.interrupt();
			join();
			thread = null;
		}
	}
}
/*---------------------------------------------------------------*/
/*
 * Fin du fichier Server.java
 * /*---------------------------------------------------------------
 */
