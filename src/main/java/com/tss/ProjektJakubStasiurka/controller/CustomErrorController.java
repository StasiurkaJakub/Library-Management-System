package com.tss.ProjektJakubStasiurka.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        Object uri = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        Object error = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authInfo", auth);
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        model.addAttribute("path", uri);
        model.addAttribute("error", error);
        return "error";
    }
}
