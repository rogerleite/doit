/**
 * 
 */
package org.oneupfordev.doit.parsers;

import java.lang.reflect.Field;
import java.util.List;

import net.vidageek.mirror.ClassController;
import net.vidageek.mirror.Mirror;
import net.vidageek.mirror.ObjectController;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;
import org.oneupfordev.doit.parsers.expr.Expression;
import org.oneupfordev.doit.parsers.expr.Word;
import org.oneupfordev.doit.stuff.Context;
import org.oneupfordev.doit.stuff.Dictionary;

/**
 * @author Roger Leite
 */
public class ExpressionParser {
	private Context context = null;
	private Dictionary dictionary = null;

	public ExpressionParser(final Context context, final Dictionary dictionary) {
		this.context = context;
		this.dictionary = dictionary;
	}

	public CallableExpression parse(String expression) {
		RootCmdDescriptor rootCmdDescr = dictionary.find(expression);
		if (rootCmdDescr == null) {
			throw new RuntimeException("Command not found!");
		}

		Expression expr = new Expression(expression, rootCmdDescr);

		CallableExpression callable = findCommand(expr, rootCmdDescr);
		callable = injectStuff(callable, expr);

		int wordIndex = 1;
		//TODO: find a better way than this
		if (expr.getWords().size() >= 2 && expr.getWord(1).isParameterType()) {
			wordIndex = 2;
		}
		if (expr.getWords().size() > wordIndex) {
			callable = keepWalking(callable, expr, wordIndex);
		}

		return callable;
	}

	private CallableExpression findCommand(final Expression expr, final RootCmdDescriptor rootCmdDescr) {

		//TODO: validate first word of expr with rootCmdDescr getName
		List<Word> words = expr.getWords();

		ClassController<?> callableController = Mirror.on(rootCmdDescr.getClassExpression());
		CallableExpression callable = null;

		//TODO: validate if command can ACCEPT parameters
		if (words.size() >= 2 && words.get(1).isParameterType() ) {
			callable = (CallableExpression) callableController.invoke().constructor().withArgs(words.get(1).toString());
		} else {
			callable = (CallableExpression) callableController.invoke().constructor().withoutArgs();
		}
		
		return callable;
	}

	private CallableExpression injectStuff(final CallableExpression callable, final Expression statement) {

		callable.setContext(context);

		ClassController<?> callableController = Mirror.on(callable.getClass());
		/*Field rightSideField = callableController.reflect().field("rightSide");
		if (rightSideField != null) {
			Mirror.on(callable).set().field(rightSideField).withValue(statement.getRightSide());
		}*/
		Field dictionaryField = callableController.reflect().field("dictionary");
		if (dictionaryField != null) {
			Mirror.on(callable).set().field(dictionaryField).withValue(dictionary);
		}

		return callable;
	}

	private CallableExpression keepWalking(CallableExpression callable,
											final Expression expr,
											int wordIndex) {
		int step = 1;
		
		Word actualWord = expr.getWord(wordIndex);
		Word parameterWord = null;
		int nextWordIndex = wordIndex + 1;
		if (nextWordIndex < expr.getWords().size() && expr.getWord(nextWordIndex).isParameterType()) {
			parameterWord = expr.getWord(wordIndex + 1);
			step++;
		}

		ObjectController callableController = Mirror.on(callable);
		Object resultFromInvoke = null;
		if (parameterWord != null) {
			resultFromInvoke = callableController.invoke()
				.method(actualWord.getAsMethod(callable, true)).withArgs(parameterWord.toString());
		} else {
			resultFromInvoke = callableController.invoke()
				.method(actualWord.getAsMethod(callable, false)).withoutArgs();
		}

		if (resultFromInvoke instanceof CallableExpression) {
			//TODO: change this, to make the parameter final
			callable = (CallableExpression) resultFromInvoke;
		}

		if (wordIndex + step >= expr.getWords().size()) {
			return callable;
		} else {
			return keepWalking(callable, expr, wordIndex + step);
		}
	}

}
