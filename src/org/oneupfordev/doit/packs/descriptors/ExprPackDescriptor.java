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
	protected List<RootCmdDescriptor> descriptors = new ArrayList<RootCmdDescriptor>();
	protected List<Throwable> errors = new ArrayList<Throwable>();

	public ExprPackDescriptor(String name) {
		if (name == null || "".equals(name.trim())) {
			throw new ExpressionIllegalArgumentException("Name of Expression Pack Descriptor cannot be null or empty.");
		}
		this.name = name;
	}

	public void add(RootCmdDescriptor descr) {
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
	 * @return <b>read only</b> list of {@link RootCmdDescriptor}s.
	 */
	public List<RootCmdDescriptor> getDescriptors() {
		return Collections.unmodifiableList(descriptors);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("'" + this.name + "'");
		if (descriptors != null) {
			builder.append(" descriptors: [ ");
			for (RootCmdDescriptor rootCmd : descriptors) {
				builder.append(rootCmd);
			}
			builder.append(" ]");
		}

		if (errors != null) {
			builder.append(" errors: [ ");
			for (Throwable t : errors) {
				builder.append(t.getMessage());
			}
			builder.append(" ]");
		}

		return builder.toString() + " ";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descriptors == null) ? 0 : descriptors.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ExprPackDescriptor))
			return false;
		ExprPackDescriptor other = (ExprPackDescriptor) obj;
		if (descriptors == null) {
			if (other.descriptors != null)
				return false;
		} else if (!descriptors.equals(other.descriptors))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
