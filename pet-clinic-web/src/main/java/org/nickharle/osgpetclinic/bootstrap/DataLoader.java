package org.nickharle.osgpetclinic.bootstrap;

import org.nickharle.osgpetclinic.model.Owner;
import org.nickharle.osgpetclinic.model.Vet;
import org.nickharle.osgpetclinic.services.OwnerService;
import org.nickharle.osgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setFirstName("Micheal");
        owner1.setLastName("Cane");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Doris");
        owner2.setLastName("Day");

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
