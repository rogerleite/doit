/**
 * 
 */
package org.oneupfordev.doit;

import org.oneupfordev.doit.stuff.Context;
import org.oneupfordev.doit.stuff.Dictionary;
import org.oneupfordev.doit.stuff.DoItSession;

/**
 * It is the object responsible for creating new {@link DoItSession}s.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class DoIt {

	private class NewDoItSession extends DoItSession {
		public NewDoItSession(Context context, Dictionary dictionary) {
			super(context, dictionary);
		}
	}

	/** Default Constructor. */
	public DoIt() {}

	public DoItSession createSession(boolean loadExternalPacks) {
		
		//create Context
			//add packpath to context
		//create dictionary
		
		//create session (with context and dictionary)
		//session.load();
		//return session
		
		//DoItSession session = new NewDoItSession(null, null);
		return null;
	}

}
