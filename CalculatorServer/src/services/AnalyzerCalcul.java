/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : AnalyzerCalcul.java
 * 
 * Créé le 23 janv. 2014 à 09:34:19
 * 
 * Auteur : Charles NEAU
 */
package services;

import java.util.Observable;
import java.util.Observer;

/* _________________________________________________________ */
/**
 * The Class AnalyzerCalcul.
 * 
 * @author Charles NEAU
 */
public class AnalyzerCalcul implements Observer, Analyzer
{
	/** The instance. */
	private static AnalyzerCalcul	instance;

	/**
	 * Instantiates a new analyzer.
	 */
	private AnalyzerCalcul()
	{
		super();
	}

	/**
	 * Gets the single instance of AnalyzerCalcul.
	 * 
	 * @return single instance of AnalyzerCalcul
	 */
	public static AnalyzerCalcul getInstance()
	{
		if (instance == null)
		{
			instance = new AnalyzerCalcul();
		}
		return instance;
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
		if (observable instanceof ClientInfo)
		{
			final ClientInfo clientInfo = (ClientInfo) observable;
			// TODO Parser la trame
			if (object instanceof String)
			{
				final String string = (String) object;
				// TODO Faire l'opération qu'il faut quand il le faut
			}
		}
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier AnalyzerCalcul.java.
 * /*_________________________________________________________
 */
