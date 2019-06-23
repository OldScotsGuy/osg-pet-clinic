package org.nickharle.osgpetclinic.bootstrap;

import org.nickharle.osgpetclinic.model.*;
import org.nickharle.osgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findall().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        // Define and persist pet types
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        // Define and persist Vet Specialities
        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentisty = specialtyService.save(dentistry);

        // Define and Persist owners with pets
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
        owner2.setAddress("1 Country Lane");
        owner2.setCity("Nashville");
        owner2.setTelephone("654321");
        Pet dorisPet = new Pet();
        dorisPet.setName("Tabby");
        dorisPet.setOwner(owner2);
        dorisPet.setBirthData(LocalDate.now());
        dorisPet.setPetType(savedCatPetType);
        owner2.getPets().add(dorisPet);
        ownerService.save(owner2);

        System.out.println("Loaded Owners ... ");

        // Define and persist visits
        Visit catVisit = new Visit();
        catVisit.setPet(dorisPet);
        catVisit.setData(LocalDate.now());
        catVisit.setDescription("Ill Cat");
        visitService.save(catVisit);

        // Define and persist vets
        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Greta");
        vet2.setLastName("Garbo");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("Loaded Vets ... ");
    }
}
