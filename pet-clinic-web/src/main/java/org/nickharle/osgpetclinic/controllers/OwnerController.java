package org.nickharle.osgpetclinic.controllers;

import org.nickharle.osgpetclinic.model.Owner;
import org.nickharle.osgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    // Prevents a web form from setting the id value
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    // Supply HTML form with an empty object
    @RequestMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "/owners/findOwners";
    }

    // Process form data
    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        // Allow parameterless GET request for /owners to return all results
        if (owner.getLastName() == null) {
            owner.setLastName("%");  // empty string signifies broadest possible search
        } else {
            owner.setLastName("%" + owner.getLastName() + "%");
        }

        // find owners by last name
        List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastName());

        if (results.isEmpty()) {
            // No owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "/owners/findowners";
        } else if (results.size() == 1) {
            // 1 owner found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.addAttribute("selections", results);
            return "/owners/ownersList";
        }

    }

    // Display individual owner
    @GetMapping("/{ownerid}")
    public ModelAndView showOwner(@PathVariable("ownerid") Long ownerid) {
        ModelAndView nav = new ModelAndView("/owners/ownerDetails");
        nav.addObject(ownerService.findById(ownerid));
        return nav;
    }

}
