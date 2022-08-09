package com.project.spring.new_main_project.util;

import javax.servlet.http.HttpServletRequest;

public class Util {

    public static String getSiteURL(HttpServletRequest request){
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
