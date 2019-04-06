package com.lucas.javase.annotation.create;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
public @interface NotNull {

}
