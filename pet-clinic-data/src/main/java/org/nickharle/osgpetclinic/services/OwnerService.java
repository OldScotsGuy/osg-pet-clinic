package org.nickharle.osgpetclinic.services;

import org.nickharle.osgpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService {

    Owner findByLastName(String lastName);

    Owner findByID(Long id);

    Owner save(Owner owner);

    Set<Owner> finadAll();
}
