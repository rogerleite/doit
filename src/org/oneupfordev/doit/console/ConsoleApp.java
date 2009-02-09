/**
 * 
 */
package org.oneupfordev.doit.console;

import java.util.Scanner;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.Dictionary;
import org.oneupfordev.doit.Result;
import org.oneupfordev.doit.internals.cmds.InternalExpressionPack;
import org.oneupfordev.doit.packs.descriptors.ExprPackDescriptor;
import org.oneupfordev.doit.parsers.ExpressionParser;
import org.oneupfordev.doit.stuff.Context;

/**
 * Console Application to run {@link CallableExpression}s.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class ConsoleApp {

	public static void main(String[] args) {
		System.out.println("=========================");
		System.out.println("Welcome ('quit' to quit!)");
		System.out.print("Getting ExpressionParser ...");
		ExpressionParser ep = getExpressionParser();
		System.out.print(" done!\n");
		System.out.println("=========================");

		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");

		System.out.print(getTitlePrompt());
		while (sc.hasNextLine()){
			String userCmd = sc.next();
			if ("quit".equalsIgnoreCase(userCmd)) {
				break;
			}
			System.out.println("Executing ... " + userCmd);

			try {
				CallableExpression ce = ep.parse(userCmd);
				Result result = ce.doIt();
				System.out.println(result.textValue());
			} catch (Throwable t) {
				System.out.println("ERROR: " + t.getMessage());
				//t.printStackTrace();
			}

			System.out.print(getTitlePrompt());
		}
		System.out.println("Bye!");
	}

	private static String getTitlePrompt() {
		String userAt = System.getenv("USERNAME") + "@DoIt$ ";
		return userAt;
	}

	private static ExpressionParser getExpressionParser() {
		Context ctx = new Context();

		Dictionary dic = new Dictionary();
		ExprPackDescriptor packDescriptor = dic.load(new InternalExpressionPack());
		dic.add(packDescriptor);

		return new ExpressionParser(ctx, dic);
	}

}
