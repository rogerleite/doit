/**
 * 
 */
package org.oneupfordev.doit.packs.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.oneupfordev.doit.CallableExpression;

/**
 * <p>Describes the available commands of an {@link CallableExpression},
 * and for each command you can describe it's "inner" commands too.</p>
 * TODO: Put an example of code with these annotations.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RootCmd {

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface Cmd {
		String name();
		String[] innerCmds() default {};
	}

	String[] cmds() default {};
}
