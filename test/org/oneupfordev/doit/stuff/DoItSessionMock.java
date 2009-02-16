/**
 * 
 */
package org.oneupfordev.doit.stuff;

/**
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class DoItSessionMock extends DoItSession {

	DoItSessionMock(Context context, Dictionary dictionary) {
		super(context, dictionary);
	}

	@Override
	void loadInternalPack() {
		// do nothing
	}

	@Override
	void loadExternalPacks(String packPath) {
		// do nothing
	}

	public Context getContext() {
		return this.context;
	}

	public Dictionary getDictionary() {
		return this.dictionary;
	}

}
