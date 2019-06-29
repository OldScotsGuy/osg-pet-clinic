package org.nickharle.osgpetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nickharle.osgpetclinic.model.Owner;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    final Long ownerid = 1L;
    String lastname = "Smith";

    @BeforeEach
    void setUp() {
        // Initalise ownerMapService and populate with 1 owner
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(Owner.builder().id(ownerid).lastName(lastname).build());
    }

    @Test
    void findall() {
        Set<Owner> ownerSet = ownerMapService.findall();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerid);
        assertEquals(ownerid, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = ownerMapService.save(owner2);
        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoID() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerid));
        assertEquals(0, ownerMapService.findall().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerid);
        assertEquals(0, ownerMapService.findall().size());
    }

    @Test
    void findByLastName() {
        Owner smith = ownerMapService.findByLastName(lastname);
        assertNotNull(smith);
        assertEquals(ownerid, smith.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner smith = ownerMapService.findByLastName("notSmith");
        assertNull(smith);
    }
}