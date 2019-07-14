package org.svnadmin.controller.basic;

import org.apache.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.svnadmin.common.web.BaseController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ManErrorController implements ErrorController {

    protected Logger logger = Logger.getLogger(ManErrorController.class);

    private static final String ERROR_PATH="/error";

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        if(statusCode == 401){
            //return "/error/401";
        }else if(statusCode == 404){
            return "/common/404";
        }else if(statusCode == 403){
            //return "/error/403";
        }else{
           // return "/error/500";
        }
        return "/common/exception";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
