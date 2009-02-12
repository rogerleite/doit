/**
 * 
 */
package org.oneupfordev.doit.console;

import java.util.Scanner;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.Result;
import org.oneupfordev.doit.dictionary.Dictionary;
import org.oneupfordev.doit.parsers.ExpressionParser;
import org.oneupfordev.doit.stuff.Context;

/**
 * Console Application to run {@link CallableExpression}s.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class ConsoleApp {

	private static final String QUIT = "quit!";

	public static void main(String[] args) {
		System.out.println("=========================");
		System.out.println("Welcome to DoIt Console!");
		System.out.print("\nGetting ExpressionParser ...");
		ExpressionParser ep = getExpressionParser();
		System.out.print(" done!\n");
		System.out.println("=========================");
		System.out.println(getHelpMessage());

		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");

		System.out.print(getTitlePrompt());
		while (sc.hasNextLine()){
			String userCmd = sc.next();
			if (QUIT.equalsIgnoreCase(userCmd.trim())) {
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
				System.out.println("\n" + getHelpMessage());
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
		dic.loadInternalPack();

		return new ExpressionParser(ctx, dic);
	}

	private static String getHelpMessage() {
		return "Type '" + QUIT + "' to exit from here!";
	}

}
