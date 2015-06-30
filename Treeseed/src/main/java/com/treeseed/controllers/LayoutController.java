package com.treeseed.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/layoutservice")
public class LayoutController {			
	

	@RequestMapping(value = "/loginlayout")
	public String getLoginLayout()
	{
		return "layouts/login";
	}
	
	

}
