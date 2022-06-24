package com.example.appnewssite.aop;
// aop-> aspect orenter programmer
// anotatsiyalar yozishi mummki bo'lgan anotatsiyalar
import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD) // buni sen anotatsiyani qachin qayerda ishlatasan
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPermission {


    String permission();


}
