package meat.repeat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
//Самописная аннотация
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatScreamRandom {
    int min();
    int max();

}
