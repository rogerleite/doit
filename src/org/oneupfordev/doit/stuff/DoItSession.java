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
package org.oneupfordev.doit.stuff;

import java.util.List;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.ExpressionPack;
import org.oneupfordev.doit.internals.cmds.InternalExpressionPack;
import org.oneupfordev.doit.packs.PackLoader;
import org.oneupfordev.doit.packs.PackLoaderByAnnotation;
import org.oneupfordev.doit.packs.descriptors.ExprPackDescriptor;
import org.oneupfordev.doit.packs.search.FolderPack;
import org.oneupfordev.doit.packs.search.PackFinder;
import org.oneupfordev.doit.parsers.ExpressionParser;

/**
 * <p>This object allows:<br>
 * <ul>
 * <li>full access  to {@link Context}.</li>
 * <li><b>read only</b> access to {@link Dictionary}.</li>
 * <li>Parse Expressions!</li>
 * <li>load new {@link ExpressionPack}s of your application.</li>
 * </ul>
 * </p>
 * @author Roger Leite
 */
public class DoItSession {

	Context context = null;
	Dictionary dictionary = null;
	ExpressionParser expressionParser = null;

	/**
	 * Default access.<br>
	 * Generally {@link DoIt} creates this object.
	 * @param context with "pack path" attribute.
	 * @param dictionary empty one.
	 */
	DoItSession(final Context context, final Dictionary dictionary) {
		this.context = context;
		this.dictionary = dictionary;
		expressionParser = new ExpressionParser(this);
	}

	void loadInternalPack() {
		load(new InternalExpressionPack());
	}

	void loadExternalPacks(final String packPath) {
		PackFinder packFinder = new PackFinder();
		List<FolderPack> packs = packFinder.lookForPacks(packPath);
		List<ExpressionPack> exprPacks = packFinder.instantiatePacks(packs);
		for (ExpressionPack expPack : exprPacks) {
			load(expPack);
		}
	}

	public void load(ExpressionPack expressionPack) {
		//TODO document all possible exceptions can occurs.
		PackLoader packLoader = new PackLoaderByAnnotation();
		ExprPackDescriptor exprPackDescriptor = packLoader.load(expressionPack);
		dictionary.add(exprPackDescriptor);
	}

	public CallableExpression parse(final String expression) {
		return expressionParser.parse(expression);
	}

	public Context getContext() {
		return this.context;
	}

	public Dictionary getDictionary() {
		return this.dictionary;  //TODO in future, find a way to return a read-only dictionary.
	}

}
