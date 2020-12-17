package Controller;

public class HomeController {
	package edu.met.controller;

	import org.springframework.stereotype.Component;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.servlet.ModelAndView;

	@Component
	@RequestMapping("/home")
	public class HomeController {
		
		@RequestMapping(method=RequestMethod.GET)
		public ModelAndView welcome() {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("welcome","Welcome To Spring MVC From Controller");
			modelAndView.setViewName("welcome");
			return modelAndView;
		}
		
		@RequestMapping("/employee")
		public ModelAndView getEmployee(@RequestParam(name="empid")int id,String name) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("employee","Payroll Application From Controller " + id + " " + name);
			modelAndView.setViewName("employee");
			return modelAndView;
		}
		
	}


}
