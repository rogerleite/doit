/**
 * 
 */
package org.oneupfordev.doit.packs;

import java.lang.annotation.Annotation;
import java.util.List;

import net.vidageek.mirror.ClassController;
import net.vidageek.mirror.Mirror;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.exceptions.ExpressionNotValidException;
import org.oneupfordev.doit.packs.annotations.ExprDescription;
import org.oneupfordev.doit.packs.annotations.ExprDescription.InnerCmdDescriptor;
import org.oneupfordev.doit.packs.descriptors.CmdDescriptor;
import org.oneupfordev.doit.packs.descriptors.ExprCmdDescriptor;

/**
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class PackLoaderByAnnotation extends PackLoader {

	/* (non-Javadoc)
	 * @see org.oneupfordev.doit.packs.PackLoader#load(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	ExprCmdDescriptor validateAndLoad(Class<? extends CallableExpression> classExpression) {
		
		ClassController<CallableExpression> clController = 
			(ClassController<CallableExpression>) Mirror.on(classExpression);

		ExprDescription exprDescr = clController.reflect().annotation(ExprDescription.class).atClass();
		if (exprDescr == null) {
			throw new ExpressionNotValidException("Annotation " + ExprDescription.class.getName()
					+ " not found at class: " + classExpression.getName());
		}

		ExprCmdDescriptor expr = new ExprCmdDescriptor(classExpression);
		expr.discoverArgumentType(clController);
		
		for (String rootCmd : exprDescr.cmds()) {
			//rootCmd = rootCmd.toLowerCase();
			CmdDescriptor cmdDescr = expr.addInnerCmd(clController, rootCmd);
			addInnerCmdsOf(cmdDescr, clController);
		}
		
		return expr;
	}

	private void addInnerCmdsOf(CmdDescriptor cmd, ClassController<CallableExpression> clController) {
		InnerCmdDescriptor innerCmd = findInnerCmdByName(cmd.getName(), clController);

		if (innerCmd != null && innerCmd.innerCmds().length > 0) {
			for (String innerCommand : innerCmd.innerCmds()) {
				CmdDescriptor cmdDescr = cmd.addInnerCmd(clController, innerCommand);
				addInnerCmdsOf(cmdDescr, clController);
			}
		}
	}

	private InnerCmdDescriptor findInnerCmdByName(String nameSearch, ClassController<CallableExpression> clController) {
		List<Annotation> allAnnotations = clController.reflectAll().annotations().atClass();
		for(Annotation a : allAnnotations) {
			if (a instanceof InnerCmdDescriptor) {
				InnerCmdDescriptor inner = (InnerCmdDescriptor) a;
				if (inner.name().toLowerCase().equals(nameSearch)) {
					return inner;
				}
			}
		}
		return null;
	}

}
