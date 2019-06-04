package org.nickharle.osgpetclinic.services;

import org.nickharle.osgpetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findByID(Long id);

    Pet save(Pet pet);

    Set<Pet> finadAll();
}
