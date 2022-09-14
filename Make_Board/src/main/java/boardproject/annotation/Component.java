package boardproject.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // Annotation 유지정책 RUNTIME
public @interface Component {
	String value() default "";
}
