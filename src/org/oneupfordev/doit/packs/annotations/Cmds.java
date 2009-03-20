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
 * Used to group {@link Cmd} annotations.
 * @author Roger Leite
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Cmds {

	Cmd[] value();

}
