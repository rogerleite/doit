/**
 * 
 */
package org.oneupfordev.doit.stuff;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.oneupfordev.doit.ExpressionPack;
import org.oneupfordev.doit.exceptions.ExpressionIllegalArgumentException;
import org.oneupfordev.doit.internals.cmds.InternalExpressionPack;
import org.oneupfordev.doit.packs.PackLoader;
import org.oneupfordev.doit.packs.PackLoaderByAnnotation;
import org.oneupfordev.doit.packs.descriptors.ExprPackDescriptor;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;

/**
 * Responsible for managing Descriptors.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class Dictionary {

	//TODO: make this default constructor as protected

	private Set<ExprPackDescriptor> setPackDescriptors = new HashSet<ExprPackDescriptor>();

	public ExprPackDescriptor load(ExpressionPack expressionPack) {
		PackLoader packLoader = new PackLoaderByAnnotation();
		return packLoader.load(expressionPack);
	}

	public void add(ExprPackDescriptor packDescriptor) {
		if (packDescriptor == null) {
			throw new RuntimeException("packDescriptor cannot be null.");
		}
		if (setPackDescriptors.contains(packDescriptor)) {
			throw new RuntimeException("packDescriptor " + packDescriptor.getName() + " already exists.");
		}
		setPackDescriptors.add(packDescriptor);
	}

	public void loadInternalPack() {
		ExprPackDescriptor packDescriptor = load(new InternalExpressionPack());
		add(packDescriptor);
	}

	public RootCmdDescriptor find(final String expression) {
		if (expression == null || "".equals(expression.trim())) {
			throw new ExpressionIllegalArgumentException("expression cannot be null or empty.");
		}
		String trimmedExpr = expression.trim();
		String rootCmdName = trimmedExpr.split(" ")[0];
		for (ExprPackDescriptor packDescriptor : setPackDescriptors) {
			for (RootCmdDescriptor cmdDescr : packDescriptor.getDescriptors()) {
				if (cmdDescr.getName().equalsIgnoreCase(rootCmdName)) {
					return cmdDescr;
				}
			}
		}
		return null;
	}

	/**
	 * @return <b>Read Only</b> {@link Set} of loaded {@link ExprPackDescriptor}s.
	 */
	public Set<ExprPackDescriptor> getPackDescriptors() {
		return Collections.unmodifiableSet(setPackDescriptors);
	}

}
