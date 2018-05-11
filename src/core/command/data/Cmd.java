package core.command.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used for command branches
 * 
 * @author PhantomUnicorns
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cmd {
	
	String[] args();
	String description() default "";
	String permission() default "";
	boolean overLength() default false;
	boolean sendDefaultMessages() default true;
	CmdType type() default CmdType.ALL;
	
}