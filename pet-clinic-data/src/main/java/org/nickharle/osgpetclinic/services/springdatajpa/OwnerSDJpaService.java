package org.nickharle.osgpetclinic.services.springdatajpa;

import org.nickharle.osgpetclinic.model.Owner;
import org.nickharle.osgpetclinic.repositories.OwnerRepository;
import org.nickharle.osgpetclinic.repositories.PetRepository;
import org.nickharle.osgpetclinic.repositories.PetTypeRepository;
import org.nickharle.osgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
                             PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public Set<Owner> findall() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add );
        return owners;
    }

    @Override
    public Owner findById(Long id) {
        //Optional<Owner> optionalOwner = ownerRepository.findById(aLong);
        //return optionalOwner.orElse(null);
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void delete(Owner owner) {
        ownerRepository.delete(owner);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }
}
