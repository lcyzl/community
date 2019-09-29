package com.cyz.community.community.advice;

import com.cyz.community.community.exception.CustomizeException;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
=======
>>>>>>> 80b6fecbfaa5c93861658b8f0b106db94a5bc824
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

<<<<<<< HEAD
import javax.servlet.http.HttpServletRequest;

=======
>>>>>>> 80b6fecbfaa5c93861658b8f0b106db94a5bc824
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model){
        if (e instanceof CustomizeException){
            model.addAttribute("message",e.getMessage());

        }else {
            model.addAttribute("message","服务有错哦");

        }
        return new ModelAndView("error");

    }
}
