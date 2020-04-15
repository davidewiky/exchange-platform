package ccf.org.platform.model.util;

import java.lang.annotation.*;

/**
 * This annotation is used for excluding fields in the toString method of entities classes.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcludeFromToString {
}
