package com.robertoalv.repaso.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;


import com.robertoalv.repaso.model.dtos.verifyUser;
import com.robertoalv.repaso.model.entities.User;

@Controller
@RequestMapping("/taller")
public class TallerController {
	
	private static Date getDateFromString(String dateStr) {
	    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	        return date.parse(dateStr);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	
	private static List<User> userEmployee = new ArrayList<>();
	static {
		userEmployee.add(new User("AB123456", "Andrea", "Martinez", getDateFromString("22/03/2023"), true,"admin", "12345"));
		userEmployee.add(new User("AC123456", "Carlos", "Arrucha", getDateFromString("25/03/2023"), false,"normal", "54321"));
		userEmployee.add(new User("AD123456", "Esteban", "Blanco", getDateFromString("05/03/2023"), true,"normal", "12345D"));
		userEmployee.add(new User("AF123456", "Karla", "Aguilar", getDateFromString("02/01/2023"), true,"normal", "asdfg"));
		userEmployee.add(new User("AH123456", "Cesar", "Ramirez", getDateFromString("06/01/2022"), true,"admin", "zxcvb"));
	}
	
	//Verify the role for the view
	@GetMapping("/view")
	public String getView(@RequestParam("role") String role, Model model) {
		
		//Know the weather
		String time = Calendar.getInstance().getTime().toString();
		model.addAttribute("time", time);
	  if (role.equals("admin")) {
	    return "view-admin";
	  } else {
	    return "view-user";
	  }
	}
	
	@GetMapping("/login")
	public String loginEmployee() {
		return "login";			
	}
	
	@GetMapping("/verify")
	public String getID(@ModelAttribute verifyUser userInfo ) {
	    for(User user : userEmployee) {
	        if(user.getId().equals(userInfo.getId())
	                && user.getActive() == true
	                && user.getPassword().equals(userInfo.getPassword())
	                && user.isRecentlyHired() == false){
	            System.out.println("Simon si existe");
	            
	            //Verify employee role
	            if(user.getRole().equals("admin")) {
	                return "redirect:/taller/view?role=admin";
	              } else {
	                return "redirect:/taller/view?role=user";
	              }
	        }
	    }//employee not found
	    return "notfound";
	}
	

}
