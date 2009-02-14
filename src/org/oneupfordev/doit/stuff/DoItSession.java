/**
 * 
 */
package org.oneupfordev.doit.stuff;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.ExpressionPack;
import org.oneupfordev.doit.internals.cmds.InternalExpressionPack;
import org.oneupfordev.doit.packs.PackLoader;
import org.oneupfordev.doit.packs.PackLoaderByAnnotation;
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

	private Context context = null;
	private Dictionary dictionary = null;
	private ExpressionParser expressionParser = null;

	protected DoItSession(final Context context, final Dictionary dictionary) {
		this.context = context;
		this.dictionary = dictionary;
		expressionParser = new ExpressionParser(this.context, this.dictionary);
	}

	void loadInternalPack() {
		load(new InternalExpressionPack());
	}

	void loadExternalPacks(final String packPath) {
		//get "packpath" from context
		//new PackFinder()
		//for each path in packpath
			//packs = packFinder.find(path)
			//load(packs)
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
