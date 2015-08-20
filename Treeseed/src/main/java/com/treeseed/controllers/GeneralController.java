package com.treeseed.controllers;

import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


// TODO: Auto-generated Javadoc
/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/")
public class GeneralController {
	
	/**
	 * Simply selects the home view to render by returning its name.
	 *
	 * @param locale the locale
	 * @param model the model
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login(Locale locale, Model model,HttpServletRequest request,HttpServletResponse response) {				
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("treeseed");
		return mav;
	}
	
	
	/**
	 * User from facebook.
	 *
	 * @param locale the locale
	 * @param model the model
	 * @param request the request
	 * @param response the response
	 * @return the string
	 */
	@RequestMapping(value = "/sharedDonation", method = RequestMethod.GET)
	public String userFromFacebook(Locale locale, Model model,HttpServletRequest request,HttpServletResponse response) {				

		response.setContentType("text/plain");
		Enumeration<String> parameterNames = request.getParameterNames();
		ModelAndView mav = new ModelAndView();
		
        while (parameterNames.hasMoreElements()) {

            String paramName = parameterNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);

            for (int i = 0; i < paramValues.length; i++) {

                String paramValue = paramValues[i];
                
                mav.setViewName("treeseed");

        		return "redirect:/#/sharedDonation/" + paramValue;	
            }
        }
        mav.setViewName("treeseed");
		return "";
}
	
	
	@RequestMapping(value = "/goTo", method = RequestMethod.GET)
	public String donorProfile(Locale locale, Model model,HttpServletRequest request,HttpServletResponse response) {				

		response.setContentType("text/plain");
		Enumeration<String> parameterNames = request.getParameterNames();
		ModelAndView mav = new ModelAndView();
		 String paramName = null;
		 String[] paramValues = null;
		 String id = null;
		 String fatherId = null;
		 String type = null;
		
        while (parameterNames.hasMoreElements()) {

        	 paramName = parameterNames.nextElement();
             paramValues = request.getParameterValues(paramName);
             type = paramValues[0];
             	
            paramName = parameterNames.nextElement();
            paramValues = request.getParameterValues(paramName);
            id = paramValues[0];
            
            paramName = parameterNames.nextElement();
            paramValues = request.getParameterValues(paramName);
            fatherId = paramValues[0];
           
            
            mav.setViewName("treeseed");

    		return "redirect:/#/shared?type="+type+"&fatherId="+fatherId+"&id=" + id;	
            
        }
        mav.setViewName("treeseed");
		return "";
}
	
	
	
	/**
	 * Home.
	 *
	 * @param locale the locale
	 * @param model the model
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model,HttpServletRequest request,HttpServletResponse response) {				
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
}
