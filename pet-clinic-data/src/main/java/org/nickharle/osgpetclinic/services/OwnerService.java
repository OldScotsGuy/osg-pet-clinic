package org.nickharle.osgpetclinic.services;

import org.nickharle.osgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
