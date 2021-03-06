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
import application.Server;

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
	 * Parser.
	 * Le message respecte ce méta-langage :
	 * OPERANDE OP1 <op1> OP2 <op2>.
	 * 
	 * @param message
	 *            the string
	 * @return the string
	 */
	private String parserIn(final String message)
	{
		float resultat = 0;
		String id = null;
		if (message != null)
		{
			final String[] split = message.split(ProtocolCommandes.SEP
					.toString());
			Operation operation = null;
			if (split.length >= 6)
			{
				int i = 0;
				id = split[i];
				final String operande = split[++i];
				// final String op1 = split[++i];
				++i;
				final String op1Nombre = split[++i];
				// final String op2 = split[++i];
				++i;
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
				if (operation != null)
				{
					resultat = operation.operation(Float.parseFloat(op1Nombre),
							Float.parseFloat(op2Nombre));
				}
			}
			else
			{
				if (message.contains(ProtocolCommandes.STAT.toString()))
				{
					Log.d("AnalyzerCalcul", "parserIn()");
					return formatStat();
				}
			}
		}
		return formatOut(id, resultat);
	}

	/**
	 * Format stat.
	 * 
	 * @return the string
	 */
	private String formatStat()
	{
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(ProtocolCommandes.STAT.toString());
		stringBuilder.append(ProtocolCommandes.SEP.toString());
		stringBuilder.append("Temps d'execution : ");
		stringBuilder.append(Server.getInstance().getTime());
		stringBuilder.append(ProtocolCommandes.SEP.toString());
		stringBuilder.append("Nombre de requetes : ");
		stringBuilder.append(Server.getInstance().getNbRequest());
		final String string = stringBuilder.toString();
		return string;
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
	private String formatOut(final String id, final float resultat)
	{
		return id + ProtocolCommandes.SEP + ProtocolCommandes.RES.toString()
				+ ProtocolCommandes.SEP + Float.toString(resultat);
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
				final String answer = parserIn(string);
				Log.d("AnalyzerCalcul", "update() answer " + answer);
				if (!answer.contains("null"))
				{
					clientInfo.getClientSender().sendMessage(answer);
				}
			}
		}
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier AnalyzerCalcul.java.
 * /*_________________________________________________________
 */
