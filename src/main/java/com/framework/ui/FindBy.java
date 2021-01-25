package com.framework.ui;

import com.framework.Platform;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Repeatable(FindByList.class)
public @interface FindBy {
    String locator();

    String itemLocator() default "";

    Platform platform() default Platform.ANY;

    boolean excludeFromSearch() default false;
}
