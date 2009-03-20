/**
 * 
 */
package org.oneupfordev.doit.packs.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Describe "inner" commands <u>below</u> {@link RootCmd} or another {@link Cmd}.</p>
 * @author Roger Leite
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Cmd {

	String name();
	String[] innerCmds() default {};

}
