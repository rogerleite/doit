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
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
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
		expressionParser = new ExpressionParser(this.context, this.dictionary);
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
		dictionary.add(packLoader.load(expressionPack));
	}

	public CallableExpression parse(final String expression) {
		return expressionParser.parse(expression);
	}

}
