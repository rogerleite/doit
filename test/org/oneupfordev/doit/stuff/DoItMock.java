/**
 * 
 */
package org.oneupfordev.doit.stuff;

/**
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class DoItMock extends DoIt {

	@Override
	DoItSession getDoItSession(Context context, boolean loadExternalPacks) {
		Dictionary dictionary = new Dictionary();
		DoItSession session = new DoItSession(context, dictionary);
		return session;
	}

}