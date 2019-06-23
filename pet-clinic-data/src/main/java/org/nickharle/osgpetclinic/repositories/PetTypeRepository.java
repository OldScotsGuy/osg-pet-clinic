package org.nickharle.osgpetclinic.repositories;

import org.nickharle.osgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
