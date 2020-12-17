package Controller;

public class EmployeeController {
	package edu.met.controller;

	import java.util.Collection;
	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Component;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.servlet.ModelAndView;

	import edu.met.dao.EmployeeDAO;
	import edu.met.model.Employee;

	@Component
	@RequestMapping("/employee")
	public class EmployeeController {
		
		@Autowired
		EmployeeDAO employeeDAO;
		
		@GetMapping
		public ModelAndView initialize(){
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("employee",new Employee());
			modelAndView.setViewName("employee");
			return modelAndView;
		}
		
		@PostMapping
		public ModelAndView saveEmployee(@ModelAttribute Employee employee){
			ModelAndView modelAndView = new ModelAndView();
			//modelAndView.addObject("employee",new Employee());
			employeeDAO.saveEmployee(employee);
			Collection<Employee> employees = employeeDAO.getAllEmployees();
			modelAndView.addObject("employees",employees);
			modelAndView.setViewName("employee");
			
			return modelAndView;
		}
	}


}
