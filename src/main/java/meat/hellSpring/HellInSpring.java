package meat.hellSpring;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface HellInSpring {
    Class hellNewImpl();
}
