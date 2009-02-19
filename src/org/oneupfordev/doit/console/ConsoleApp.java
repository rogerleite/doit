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
 * @author Roger Leite
 */
public class ConsoleApp {

	private static final String QUIT = "quit!";
	private String userAt = null;

	public static void main(String[] args) {
		ConsoleApp consoleApp = new ConsoleApp();
		consoleApp.startConsole();
	}

	private ConsoleApp() {
	}

	public void startConsole() {
		sysOutln("=========================");
		sysOutln("Welcome to DoIt Console!");
		sysOut("\nGetting ExpressionParser ...");
		DoItSession session = createDoItSession();
		sysOut(" done!\n");
		sysOutln("=========================");
		sysOutln(getHelpMessage());

		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");

		sysOut(getTitlePrompt());
		while (sc.hasNextLine()){
			String userCmd = sc.next();
			if (QUIT.equalsIgnoreCase(userCmd.trim())) {
				break;
			}
			sysOutln("Executing ... " + userCmd);

			try {
				CallableExpression ce = session.parse(userCmd);
				Result result = ce.doIt();
				sysOutln(result.textValue());
			} catch (Throwable t) {
				sysOutln("ERROR: " + t.getMessage());
				//t.printStackTrace();
				sysOutln("\n" + getHelpMessage());
			}

			sysOut(getTitlePrompt());
		}
		sysOutln("Bye!");
	}

	private void sysOut(final String out) {
		System.out.print(out);
	}

	private void sysOutln(final String out) {
		System.out.println(out);
	}

	private DoItSession createDoItSession() {
		try {
			return new DoIt().createSession(true);
		} catch(Throwable t) {
			t.printStackTrace();
			try {
				return new DoIt().createSession();
			} catch (Throwable t2) {
				t2.printStackTrace();
				throw new RuntimeException(t2.getMessage(), t2);
			}
		}
	}

	private String getTitlePrompt() {
		if (userAt == null) {
			userAt = System.getenv("USERNAME") + "@DoIt$ ";
		}
		return userAt;
	}

	private String getHelpMessage() {
		return "Type '" + QUIT + "' to exit from here!";
	}

}
