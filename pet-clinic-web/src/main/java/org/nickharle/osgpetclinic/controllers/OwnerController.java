package org.nickharle.osgpetclinic.controllers;

import org.nickharle.osgpetclinic.model.Owner;
import org.nickharle.osgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

    // Display individual owner
    @GetMapping("/{ownerid}")
    public ModelAndView showOwner(@PathVariable("ownerid") Long ownerid) {
        ModelAndView nav = new ModelAndView("/owners/ownerDetails");
        nav.addObject(ownerService.findById(ownerid));
        return nav;
    }

    // Supply HTML find owners form with an empty object
    @RequestMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "/owners/findOwners";
    }

    // Process find owners form data
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

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        Owner owner = new Owner();
        model.addAttribute("owner", owner);
        return "/owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "/owners/createOrUpdateOwnerForm";
        } else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        Owner owner = ownerService.findById(ownerId);
        model.addAttribute("owner", owner);
        return "/owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId) throws Exception {
        if (result.hasErrors()) {
            return "/owners/createOrUpdateOwnerForm";
        } else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
}
