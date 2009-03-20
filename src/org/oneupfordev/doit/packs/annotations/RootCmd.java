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
 * <p>Describes the <u>first</u> available commands of an {@link CallableExpression}.</p>
 * @author Roger Leite
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RootCmd {

	String[] cmds() default {};

}
