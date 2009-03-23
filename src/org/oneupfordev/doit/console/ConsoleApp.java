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
		sysOutln("\n" + getLGPLMessage() + "\n");
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

	private String getLGPLMessage() {
		return "DoIt  Copyright (C) 2009  Roger Leite\n" +
					"This program comes with ABSOLUTELY NO WARRANTY;\n" +
					"This is free software, and you are welcome to redistribute it " +
					"under certain conditions;";
	}
}
