/**
 * 
 */
package org.oneupfordev.doit.stuff;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.oneupfordev.doit.CallableExpression;

/**
 * Shared Object between {@link CallableExpression}s.
 * @author Roger Leite
 */
public class Context {

	static final String PACKPATH_KEY = "doit.packpath";

	private Map<String, Object> attributes = new HashMap<String, Object>();

	/**
	 * Default access.<br>
	 * Generally {@link DoIt} creates this object.
	 */
	Context() {
	}

	public Object getAttribute(String key) {
		return attributes.get(key);
	}

	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}

	public void removeAttribute(String key) {
		attributes.remove(key);
	}

	/**
	 * @return a <b>read-only</b> map of Attributes.
	 */
	public Map<String, Object> getAttributes() {
		return Collections.unmodifiableMap(attributes);
	}

}
