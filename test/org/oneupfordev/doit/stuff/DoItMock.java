/**
 * 
 */
package org.oneupfordev.doit.stuff;

/**
 * @author Roger Leite
 */
public class DoItMock extends DoIt {

	@Override
	DoItSession getDoItSession(Context context, boolean loadExternalPacks) {
		Dictionary dictionary = new Dictionary();
		DoItSession session = new DoItSessionMock(context, dictionary);
		return session;
	}

}