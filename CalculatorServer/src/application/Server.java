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
import services.Log;

// TODO: Auto-generated Javadoc
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
	/** The run. */
	private boolean					run;
	/** The server socket. */
	private ServerSocket			serverSocket;
	/** The start. */
	private final GregorianCalendar	start;
	/** The thread. */
	private Thread					thread;
	/** The time start. */
	private long					timeStart;
	/** The instance. */
	private static Server			instance;

	/**
	 * Instantiates a new server.
	 * 
	 * @param port
	 *            the port
	 */
	private Server(final int port)
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
			System.exit(-1);
		}
	}

	/**
	 * Gets the single instance of Server.
	 * 
	 * @param port
	 *            the port
	 * @return single instance of Server
	 */
	public static Server getInstance(final int port)
	{
		if (instance == null)
		{
			instance = new Server(port);
		}
		return instance;
	}

	/**
	 * Gets the instance.
	 * 
	 * @return the instance
	 */
	public static Server getInstance()
	{
		return instance;
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
				Log.d("Server", "run()");
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
			timeStart = System.currentTimeMillis();
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

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ nbRequest.
	 * 
	 * @return la valeur du champ nbRequest.
	 */
	public int getNbRequest()
	{
		int nbRequest = 0;
		for (final ClientInfo clientInfo : clients)
		{
			nbRequest += clientInfo.getNbRequest();
		}
		return nbRequest;
	}

	/**
	 * Gets the time.
	 * 
	 * @return the time
	 */
	public long getTime()
	{
		return System.currentTimeMillis() - timeStart;
	}
}
/*---------------------------------------------------------------*/
/*
 * Fin du fichier Server.java
 * /*---------------------------------------------------------------
 */
