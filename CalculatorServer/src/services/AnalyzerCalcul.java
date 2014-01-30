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
import metier.Addition;
import metier.Division;
import metier.Multiplication;
import metier.Operation;
import metier.Soustraction;

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
			if (object instanceof String)
			{
				final String string = (String) object;
				final String answer = parserIn(string, clientInfo);
				clientInfo.getClientSender().sendMessage(answer);
			}
		}
	}

	/* _________________________________________________________ */
	/**
	 * Parser.
	 * Le message respecte ce méta-langage :
	 * OPERANDE OP1 <op1> OP2 <op2>.
	 * 
	 * @param message
	 *            the string
	 * @param clientInfo
	 *            the client info
	 * @return the string
	 */
	private String parserIn(final String message, final ClientInfo clientInfo)
	{
		float resultat = 0;
		String id = null;
		if (message != null)
		{
			final String[] split = message.split(" ");
			Operation operation = null;
			if (split.length >= 6)
			{
				int i = 0;
				id = split[i];
				final String operande = split[++i];
				final String op1 = split[++i];
				final String op1Nombre = split[++i];
				final String op2 = split[++i];
				final String op2Nombre = split[++i];
				if (operande.contains(ProtocolCommandes.ADD.toString()))
				{
					operation = new Addition();
				}
				else if (operande.contains(ProtocolCommandes.MUL.toString()))
				{
					operation = new Multiplication();
				}
				else if (operande.contains(ProtocolCommandes.MIN.toString()))
				{
					operation = new Soustraction();
				}
				else if (operande.contains(ProtocolCommandes.DIV.toString()))
				{
					operation = new Division();
				}
				resultat = operation.operation(Float.parseFloat(op1Nombre),
						Float.parseFloat(op2Nombre));
			}
		}
		return parserOut(id, resultat);
	}

	/* _________________________________________________________ */
	/**
	 * Parser out.
	 * 
	 * @param id
	 *            the id
	 * @param resultat
	 *            the resultat
	 * @return the string
	 */
	private String parserOut(final String id, final float resultat)
	{
		return id + ProtocolCommandes.SEP + Float.toString(resultat);
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier AnalyzerCalcul.java.
 * /*_________________________________________________________
 */
