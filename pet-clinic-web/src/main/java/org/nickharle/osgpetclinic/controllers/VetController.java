package org.nickharle.osgpetclinic.controllers;

import org.nickharle.osgpetclinic.model.Vet;
import org.nickharle.osgpetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }


    @RequestMapping({"/vets", "/vets/index", "/vets/index.html", "/vets.html"})
    public String listVets(Model model) {

        model.addAttribute("vets", vetService.findall());

        return "vets/index";
    }

    @GetMapping("/api/vets")
    // @ResponseBody is format of return, which by default is JSON
    public @ResponseBody Set<Vet> getVetsJson() {
        return vetService.findall();
    }

}
