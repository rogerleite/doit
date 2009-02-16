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
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class Context {

	//TODO: make this default constructor as protected

	private Map<String, Object> attributes = new HashMap<String, Object>();

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
