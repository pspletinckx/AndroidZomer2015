package com.example.fabrice.joetz2.Helpers.SaripaarRules;

import com.mobsandgeeks.saripaar.annotation.ValidateUsing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Fabrice on 4/08/2015.
 */
@ValidateUsing(RrnRule.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Rrn {
    public int messageResId()   default -1;                     // Mandatory attribute
    public String message()     default "Oops... too pricey";   // Mandatory attribute
    public int sequence()       default -1;                     // Mandatory attribute
}


