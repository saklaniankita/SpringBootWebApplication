package com.in28minutes.springboot.springbootwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
//@SessionAttributes("name")
public class WelcomeController {
    //private AuthenticationService authenticationService;

    private Logger logger = LoggerFactory.getLogger(getClass());

//    public LoginController(AuthenticationService authenticationService) {
//        this.authenticationService = authenticationService;
//    }

    /* @RequestMapping("login")
    public String goToLoginPage(@RequestParam String name, ModelMap model){
        logger.debug("Request Param is -> {}", name);

//        logger.debug("logging at debug level");
//        logger.info("logging at info level");
//        logger.warn("logging at warn level");
//        logger.error("logging at error level");

        model.put("name", name);
        return "login";
    }*/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToWelcomePage(ModelMap model) {
        model.put("name",getLoggedInUsername());
        System.out.println("logged in user in method goToWelcomePage: "+getLoggedInUsername());
        return "welcome";
    }

    private String getLoggedInUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();

    }

//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public String goToWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
//        if (authenticationService.authenticate(name, password)) {
//            model.put("name", name);
//            return "welcome";
//        }
//        model.put("errMessage", "Invalid Credentials! Try Again!");
//        return "login";
//    }
}
