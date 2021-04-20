package com.EREMEI.TryingJavaStaff;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Repeatable(MyAnnos.class)
@Target(ElementType.METHOD)
@interface MyAnno {
    String value() default "N/A";
}

@Target(ElementType.METHOD)
@interface MyAnnos {
    MyAnno[] value();
}

public class AnnotationsTest implements Runnable {

    @Override
    public void run() {
        System.out.println("Testing annotations...");

        displayObj(null);
    }

    @Deprecated
    private void displayObj(Object o) {
        System.out.println(o);
    }
}