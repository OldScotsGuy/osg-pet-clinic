package org.nickharle.osgpetclinic.repositories;

import org.nickharle.osgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
