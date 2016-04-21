/**
 * 
 */
package com.circuitree.app;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.circuitree.app.dao.Dao;


/**
 * @author AB
 *
 */

@Controller
@RequestMapping("/circuitree")
public class BackendController {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public @ResponseBody JSONObject Login(@RequestParam(value = "userName", required = true) String userId,
			@RequestParam(value = "password", required = true) String userPwd) {
		JSONObject jsonObject = new JSONObject();
		if (userId != null && userPwd != null) {
			log.info("Entered /login Method");
			Dao userLogin = new Dao();
			return userLogin.login(userId, userPwd);
		}

		else {
			jsonObject.put("response", "Missing Parameter");
			return jsonObject;
		}

	}
	
	
	@RequestMapping(value = "/login/isLogin", method = RequestMethod.GET)
	public @ResponseBody JSONObject isLogin(@RequestParam(value = "sessionToken", required = true) String token) {
		log.info("Entered /isLogin method");
		Dao userLogin = new Dao();
	    return userLogin.isLogin(token);
		

	}
	

	@RequestMapping(value = "/checkComments", method = RequestMethod.GET)
	public @ResponseBody JSONObject checkComments(@RequestParam(value = "sessionToken", required = true) String token) {
		log.info("Entered /checkComments");
		Dao userLogin = new Dao();
	    return userLogin.checkComments(token);
		

	}
	
	@RequestMapping(value = "/addComments", method = RequestMethod.POST)
	public @ResponseBody JSONObject checkComments(@RequestParam(value = "sessionToken", required = true) String token,
			@RequestParam(value = "comment", required = true) String comments) {
		log.info("Entered /addComments");
		Dao userLogin = new Dao();
	    return userLogin.addComments(token,comments);
		

	}

	@RequestMapping(value = "/logout", method = RequestMethod.PUT)
	public @ResponseBody JSONObject logout(@RequestParam(value = "sessionToken", required = true) String sessionToken){
			log.info("Entered /logout method");
			Dao userLogin = new Dao();
		    return   userLogin.logout(sessionToken);
			
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody JSONObject register(@RequestParam(value = "firstName", required = true) String firstname,
			@RequestParam(value = "lastName", required = true) String lastName,@RequestParam(value = "email", required = true) String email,@RequestParam(value = "contactNo", required = true) String contactNo,@RequestParam
			(value = "address1", required = true) String address1,@RequestParam(value = "address2", required = false) String address2,@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "state", required = false) String state,@RequestParam(value = "country", required = true) String country,@RequestParam(value = "postalCode", required = false)String postalCode,
			@RequestParam(value = "password", required = true) String password)
			{
				log.info("Entered /register method");
			Dao userLogin = new Dao();
		    return   userLogin.register(firstname, lastName, email, contactNo, address1, address2, city, state, country, postalCode,password);
			
	}
}
