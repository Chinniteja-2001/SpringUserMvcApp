package org.jsp.usermvcdemo.controller;

import org.jsp.usermvcdemo.dao.UserDao;
import org.jsp.usermvcdemo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/open-register")
	public ModelAndView openRegister(ModelAndView view) {
		view.setViewName("register");
		view.addObject("u", new User());
		return view;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
//	@PostMapping(value="/register")
	//post method will hide all the key-value pairs data present in the url.
	public ModelAndView register(@ModelAttribute User u,ModelAndView view) {
		u=userDao.saveUser(u);
		view.setViewName("print");
		view.addObject("message", "user saved with id: "+u.getId());
		return view;
	}
	
	@RequestMapping("/open-update")
	public ModelAndView openUpdate(ModelAndView view) {
		view.setViewName("update");
		view.addObject("u", new User());
		return view;
	}
	
	@PostMapping("/update")
	public ModelAndView update(@ModelAttribute User u,ModelAndView view) {
		u=userDao.updateUser(u);
		if(u!=null) {
			view.setViewName("print");
			view.addObject("message", "user updated");
			return view;
		}
		view.setViewName("print");
		view.addObject("mesage", "cannot update user as Id is invalid");
		return view;
	}
	
	@RequestMapping("/open-view")
	public String openView(String view) {
		return view;
	}
	
	@GetMapping(value="/find-by-id")
	public ModelAndView findById(@RequestParam int id,ModelAndView view) {
		User u=userDao.findById(id);
		if(u!=null) {
			view.setViewName("display");
			view.addObject("u",u);
			return view;
		}
		view.setViewName("print");
		view.addObject("message","invalid User Id");
		return view;
	}
	
	@RequestMapping("/verify-by-id")
	public ModelAndView verifyById(@RequestParam(name="id") int id,@RequestParam(name="password") String password,ModelAndView view) {
		User u=userDao.verifyById(id, password);
		if(u!=null) {
			view.setViewName("display");
			view.addObject("u",u);
			return view;
		}
		view.setViewName("print");
		view.addObject("message","invalid id or password");
		return view;
	}
}
