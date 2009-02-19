/**
 * 
 */
package org.oneupfordev.doit.results;


/**
 * Simple Text Result.
 * @author Roger Leite
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
