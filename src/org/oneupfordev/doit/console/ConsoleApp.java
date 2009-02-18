/**
 * 
 */
package org.oneupfordev.doit.console;

import java.util.Scanner;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.results.Result;
import org.oneupfordev.doit.stuff.DoIt;
import org.oneupfordev.doit.stuff.DoItSession;

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
		DoItSession session = null;
		try {
			session = new DoIt().createSession(true);
		} catch(Throwable t) {
			t.printStackTrace();
			throw new RuntimeException(t.getMessage(), t);
		}
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
				CallableExpression ce = session.parse(userCmd);
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

	private static String getHelpMessage() {
		return "Type '" + QUIT + "' to exit from here!";
	}

}
