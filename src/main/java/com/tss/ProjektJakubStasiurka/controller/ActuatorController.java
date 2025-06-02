package com.tss.ProjektJakubStasiurka.controller;

import com.tss.ProjektJakubStasiurka.helper.ActuatorHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ActuatorController {

    private final ActuatorHelper actuatorHelper;

    public ActuatorController(ActuatorHelper actuatorHelper) {
        this.actuatorHelper = actuatorHelper;
    }

    @GetMapping("/actuatorInfo")
    public String actuatorPanel(Model model) {
        model.addAttribute("endpoints", actuatorHelper.getEndpoints());
        model.addAttribute("actuatorInfo", actuatorHelper.getInfo());
        return "actuator-panel";
    }

}
