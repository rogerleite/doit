/*
* This file is part of DoIt.
* 
* DoIt is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.

* DoIt is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Lesser General Public License for more details.

* You should have received a copy of the GNU Lesser General Public License
* along with DoIt.  If not, see <http://www.gnu.org/licenses/>.
* 
* Copyright 2009 Roger Leite
 */

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
import org.oneupfordev.doit.packs.annotations.Cmd;
import org.oneupfordev.doit.packs.annotations.Cmds;
import org.oneupfordev.doit.packs.annotations.RootCmd;
import org.oneupfordev.doit.packs.descriptors.CmdDescriptor;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;

/**
 * @author Roger Leite
 */
public class PackLoaderByAnnotation extends PackLoader {

	@SuppressWarnings("unchecked")
	@Override
	RootCmdDescriptor validateAndLoad(Class<? extends CallableExpression> classExpression) {

		ClassController<CallableExpression> clController = (ClassController<CallableExpression>) Mirror.on(classExpression);

		RootCmd exprDescr = clController.reflect().annotation(RootCmd.class).atClass();
		if (exprDescr == null) {
			String msg = String.format("Annotation %s not found at class: %s", RootCmd.class.getName(), classExpression.getName());
			throw new ExpressionNotValidException(msg);
		}

		RootCmdDescriptor expr = new RootCmdDescriptor(classExpression);
		expr.discoverArgumentType(clController);
		
		for (String rootCmd : exprDescr.cmds()) {
			//rootCmd = rootCmd.toLowerCase();
			CmdDescriptor cmdDescr = expr.addInnerCmd(clController, rootCmd);
			addInnerCmdsOf(cmdDescr, clController);
		}
		
		return expr;
	}

	private void addInnerCmdsOf(CmdDescriptor cmd, ClassController<CallableExpression> clController) {
		Cmd innerCmd = findInnerCmdByName(cmd.getName(), clController);

		if (innerCmd != null && innerCmd.innerCmds().length > 0) {
			for (String innerCommand : innerCmd.innerCmds()) {
				CmdDescriptor cmdDescr = cmd.addInnerCmd(clController, innerCommand);
				addInnerCmdsOf(cmdDescr, clController);
			}
		}
	}

	private Cmd findInnerCmdByName(String nameSearch, ClassController<CallableExpression> clController) {
		//TODO need to refactor here, because inclusion of Cmds annotation
		List<Annotation> allAnnotations = clController.reflectAll().annotations().atClass();
		for(Annotation a : allAnnotations) {
			if (a instanceof Cmd) {
				Cmd inner = (Cmd) a;
				if (inner.name().toLowerCase().equals(nameSearch)) {
					return inner;
				}
			} else if (a instanceof Cmds) {
				Cmds cmds = (Cmds) a;
				for (Cmd inner : cmds.value()) {
					if (inner.name().toLowerCase().equals(nameSearch)) {
						return inner;
					}
				}
			}
		}
		return null;
	}

}
