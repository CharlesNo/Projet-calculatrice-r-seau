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

/*---------------------------------------------------------------*/
/**
 * @author vivoyer
 * 
 */
public class ClientInfo
{
	private final Socket	socket;

	public ClientInfo(final Socket socket) throws IOException
	{
		this.socket = socket;
	}

	/*---------------------------------------------------------------*/
	/**
	 * @return
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

	/*---------------------------------------------------------------*/
	/**
	 * @param obj
	 * @return
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
	 */
	public void interrupt()
	{
		// TODO Auto-generated method stub
	}
}
/*---------------------------------------------------------------*/
/*
 * Fin du fichier ClientInfo.java
 * /*---------------------------------------------------------------
 */
