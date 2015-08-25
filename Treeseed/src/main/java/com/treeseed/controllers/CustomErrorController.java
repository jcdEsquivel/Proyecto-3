package com.treeseed.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;


@RestController
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

  
    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    public void login(Locale locale, Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{				
		
    	response.sendRedirect("/treeseed.org/#/index");	
	}

    @Override
    public String getErrorPath() {
        return PATH;
    }

  

}