package com.gondir.book.controller;

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
        int statusCode = Integer.parseInt(status.toString());

        // Handle 403 Forbidden
        if (statusCode == HttpServletResponse.SC_FORBIDDEN) {
            model.addAttribute("error", statusCode);
            model.addAttribute("message", "Oops! You don't have permission to access this resource.");
            return "error/default";
        }

        // Handle 500 Internal Server Error
        if (statusCode == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
            model.addAttribute("error", statusCode);
            model.addAttribute("message", "Oops! Our server is practicing its disappearing act. Don't worry; it's just a phase. We'll bring it back from the magic show soon!");
            return "error/default";
        }
        // Handle other error codes as needed

        // Default error handling
        model.addAttribute("error", statusCode);
        model.addAttribute("message", "Well, this is awkward. It seems our code went on a coffee break without telling anyone. We've sent it a strongly worded email and expect it back shortly. Thanks for your patience!");
        return "error/default";
    }
}
