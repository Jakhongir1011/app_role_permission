package com.example.appnewssite.aop;

import com.example.appnewssite.entity.User;
import com.example.appnewssite.exception.ForbiddenException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect // bu yozgan anotatsiyaga natija deyapmiz ham bean, aspect, component deyapmiz
public class   CheckPermissionExecute {

    // bu nima qiladi biz yozgan CheckPermissionExecute qaysi anotatsiya bilan qachon ishlashini aytadi
  @Before(value = "@annotation(checkPermission)")
    public void checkUserPermissionMyMethod(CheckPermission checkPermission) throws ForbiddenException { // shu CheckPermission chaqirilsa ishlaysan

      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


      // stream aynan collection set list larni aylanish imkonini beradidan lamda expertion hisoblanadi

     //      }

    boolean exist = false;
    for (GrantedAuthority authority : user.getAuthorities()) {
      if (authority.getAuthority().equals(checkPermission.permission())){
         exist = true;
         break;
      }
    }
    if (!exist){
        String permission = checkPermission.permission();
        System.out.printf(permission);
        throw new ForbiddenException(checkPermission.permission(), "ruxsat yoq");
    }



    }


}
