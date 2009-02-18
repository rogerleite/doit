/**
 * 
 */
package org.oneupfordev.doit.stuff;

import java.net.URL;


/**
 * It is the object responsible for creating new {@link DoItSession}s.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class DoIt {

	/** Default Constructor. */
	public DoIt() {}

	public DoItSession createSession() {
		return createSession(false);
	}

	public DoItSession createSession(boolean loadExternalPacks) {
		Context context = createContext();
		return getDoItSession(context, loadExternalPacks);
	}

	DoItSession getDoItSession(final Context context, boolean loadExternalPacks) {
		Dictionary dictionary = new Dictionary();
		DoItSession session = new DoItSession(context, dictionary);
		session.loadInternalPack();
		if (loadExternalPacks) {
			session.loadExternalPacks(context.getAttribute(Context.PACKPATH_KEY).toString());
		}
		return session;
	}

	Context createContext() {
		Context context = new Context();
		//URL packsUrl = DoIt.class.getResource("../../../../packs/");
		//context.setAttribute(Context.PACKPATH_KEY, packsUrl.getPath());
		context.setAttribute(Context.PACKPATH_KEY, "packs");
		return context;
	}

}
