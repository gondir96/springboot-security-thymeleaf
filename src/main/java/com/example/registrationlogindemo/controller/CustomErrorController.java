package com.example.registrationlogindemo.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // Handle 403 Forbidden
            if (statusCode == HttpServletResponse.SC_FORBIDDEN) {
                model.addAttribute("error", "403");
                model.addAttribute("message", "Access Denied");
                return "error/403";
            }
            // Handle other error codes as needed
        }

        // Default error handling
        return "error/default";
    }
}
