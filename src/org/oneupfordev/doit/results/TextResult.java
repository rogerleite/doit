/**
 * 
 */
package org.oneupfordev.doit.results;

import org.oneupfordev.doit.Result;

/**
 * Simple Text Result.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class TextResult implements Result {

	private String value = null;

	public TextResult(final String value) {
		this.value = value;
	}

	public String textValue() {
		return value;
	}

}
