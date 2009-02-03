/**
 * 
 */
package org.oneupfordev.doit.packs.descriptors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.oneupfordev.doit.exceptions.ExpressionIllegalArgumentException;
import org.oneupfordev.doit.packs.PackLoader;

/**
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class ExprPackDescriptor {

	protected String name;
	protected List<ExprCmdDescriptor> descriptors = new ArrayList<ExprCmdDescriptor>();
	protected List<Throwable> errors = new ArrayList<Throwable>();

	public ExprPackDescriptor(String name) {
		if (name == null || "".equals(name.trim())) {
			throw new ExpressionIllegalArgumentException("Name of Expression Pack Descriptor cannot be null or empty.");
		}
		this.name = name;
	}

	public void add(ExprCmdDescriptor descr) {
		this.descriptors.add(descr);
	}

	public void add(Throwable t) {
		this.errors.add(t);
	}

	/**
	 * Package is Ok if has descriptors and no errors.
	 * @return <code>true</code> if package is OK.
	 */
	public boolean isOk() {
		return (descriptors.size() > 0) && (errors.size() == 0);
	}

	public String getName() {
		return this.name;
	}

	/**
	 * @return <b>read only</b> list of Errors that occurred in the {@link PackLoader#load(org.oneupfordev.doit.ExpressionPack)}.
	 */
	public List<Throwable> getErrors() {
		return Collections.unmodifiableList(errors);
	}

	/**
	 * @return <b>read only</b> list of {@link ExprCmdDescriptor}s.
	 */
	public List<ExprCmdDescriptor> getDescriptors() {
		return Collections.unmodifiableList(descriptors);
	}

}
