package org.nickharle.osgpetclinic.bootstrap;

import org.nickharle.osgpetclinic.model.Owner;
import org.nickharle.osgpetclinic.model.Pet;
import org.nickharle.osgpetclinic.model.PetType;
import org.nickharle.osgpetclinic.model.Vet;
import org.nickharle.osgpetclinic.services.OwnerService;
import org.nickharle.osgpetclinic.services.PetTypeService;
import org.nickharle.osgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Micheal");
        owner1.setLastName("Cane");
        owner1.setAddress("1 Tipperary Lane");
        owner1.setCity("Dublin");
        owner1.setTelephone("123456");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthData(LocalDate.now());
        mikesPet.setName("Rex");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Doris");
        owner2.setLastName("Day");
        owner1.setAddress("1 Country Lane");
        owner1.setCity("Nashville");
        owner1.setTelephone("654321");

        Pet dorisPet = new Pet();
        dorisPet.setName("Tabby");
        dorisPet.setOwner(owner2);
        dorisPet.setBirthData(LocalDate.now());
        dorisPet.setPetType(savedCatPetType);
        owner2.getPets().add(dorisPet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners ... ");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Greta");
        vet2.setLastName("Garbo");
        vetService.save(vet2);

        System.out.println("Loaded Vets ... ");
    }
}
