package org.nickharle.osgpetclinic.services;

import org.nickharle.osgpetclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findByID(Long id);

    Vet save(Vet vet);

    Set<Vet> finadAll();
}
