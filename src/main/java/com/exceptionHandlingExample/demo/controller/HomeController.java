package com.exceptionHandlingExample.demo.controller;
import com.exceptionHandlingExample.demo.customeexception.*;
import com.exceptionHandlingExample.demo.model.User;
import com.exceptionHandlingExample.demo.repo.UserRepository;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.persistence.Access;
@Controller
public class HomeController {
	@Autowired
	UserRepository ur;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	@GetMapping(value="/getA",produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,String>> getA() {
		Map<String,String> m=new HashMap<>();
		m.put("Method", "getA");
		m.put("timestamp", new Date().toString());
		m.put("status", HttpStatus.OK.toString());
		System.out.println(m);
		ResponseEntity<Map<String,String>> r=new ResponseEntity<>(m, HttpStatus.OK);
		return r;
		
	}
	@GetMapping("getB")
	@ResponseBody
	public String getB()
	{
		if(Math.random()<1) throw new UserNotFoundException("User Not Found");
		return "This is getB page";
	}
	@GetMapping(value="/save", produces="application/json")
	public ResponseEntity<User> saveUser(User u,@Autowired Connection c) throws SQLException{
		if(!c.isValid(5))
		ur.findById(1);
		
		return new ResponseEntity<User>(HttpStatus.OK).status(HttpStatus.OK).body(u);
	} 
	@ExceptionHandler(value=ConnectException.class)
	public ResponseEntity<String> handleConnectException(ConnectException c) {
		return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR).status(500).body("Connection to db is down");
	}
}
