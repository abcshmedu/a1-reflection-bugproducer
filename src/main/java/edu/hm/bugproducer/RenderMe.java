package edu.hm.bugproducer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public @Retention(RetentionPolicy.RUNTIME)  @interface RenderMe {

    String with() default "";

}
