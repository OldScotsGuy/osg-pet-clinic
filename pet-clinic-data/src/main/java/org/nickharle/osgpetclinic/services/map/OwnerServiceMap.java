package org.nickharle.osgpetclinic.services.map;

import org.nickharle.osgpetclinic.model.Owner;
import org.nickharle.osgpetclinic.model.Pet;
import org.nickharle.osgpetclinic.services.OwnerService;
import org.nickharle.osgpetclinic.services.PetService;
import org.nickharle.osgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findall() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner) {

        // Owners, Pets and PetTypes all have their own ids - need to keep all in sync
        if (owner != null) {
            if (owner.getPets() != null) {
                owner.getPets().forEach(pet -> {
                    // Handle petType id
                    if (pet.getPetType() != null) {
                        if (pet.getPetType().getId() == null) {
                            // Pet type not yet persisted (id is null) so persist this pet type
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    }

                    // Handle Pet id - make sure the pet object has the persisted pet id
                    if (pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            } else {
                throw new RuntimeException("Pet Type is required");
            }


            return super.save(owner);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
