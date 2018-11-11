package csc312;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
 *  See conment in Week6Assignment on how to complete it
 */

@Inherited
@Retention(RUNTIME)
public @interface AuthorshipInformation
{
    String author();
    
    
    String course();
}

