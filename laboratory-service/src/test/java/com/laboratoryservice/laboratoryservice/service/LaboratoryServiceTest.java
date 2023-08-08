package com.laboratoryservice.laboratoryservice.service;


import com.laboratoryservice.laboratoryservice.entity.Laboratory;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class LaboratoryServiceTest {
    @Autowired
    private LaboratoryService laboratoryService;

    @Test
    public void createLaboratoryTest(){
        //Registering laboratory to test the createLaboratory method from laboratoryService
        laboratoryService.createLaboratory(new Laboratory(1, "A"));

        //Finding the laboratory
        Laboratory laboratory = laboratoryService.getLaboratoryById(1);

        //Verifying the found laboratory is not null
        Assertions.assertNotNull(laboratory);

        //Verifying laboratory's id
        Assertions.assertEquals(1, laboratory.getId());

        //Verifying laboratory's name
        Assertions.assertEquals("A",laboratory.getLaboratoryName());
    }

    @Test
    public void getLaboratoryById(){
        //Registering laboratory to test the find method
        laboratoryService.createLaboratory(new Laboratory(1, "YUT"));

        //Finding the laboratory
        Laboratory laboratory = laboratoryService.getLaboratoryById(1);

        //Verifying the found laboratory is not null
        Assertions.assertNotNull(laboratory);

        //Verifying laboratory's id
        Assertions.assertEquals(1, laboratory.getId());

        //Verifying laboratory's name
        Assertions.assertEquals("YUT",laboratory.getLaboratoryName());
    }

    @Test
    public void getAllLaboratories(){
        //Registering laboratories in order to do the test
        laboratoryService.createLaboratory(new Laboratory(1, "YUT"));
        laboratoryService.createLaboratory(new Laboratory(2, "OPT"));
        laboratoryService.createLaboratory(new Laboratory(3, "ALT"));

        //Using the method findAll() to find all laboratories
        List<Laboratory> laboratories = laboratoryService.getAllLaboratories();

        //Confirming that laboratories is not empty
        Assertions.assertTrue(!laboratories.isEmpty());

        //Confirming the size of the list
        Assertions.assertEquals(3, laboratories.size());

        //Confirming the id's in each position
        Assertions.assertEquals(1, laboratories.get(0).getId());
        Assertions.assertEquals(2, laboratories.get(1).getId());
        Assertions.assertEquals(3, laboratories.get(2).getId());

        //Confirming the laboratories names
        Assertions.assertEquals("YUT", laboratories.get(0).getLaboratoryName());
        Assertions.assertEquals("OPT", laboratories.get(1).getLaboratoryName());
        Assertions.assertEquals("ALT", laboratories.get(2).getLaboratoryName());
    }

    @Test
    public void deleteLaboratory(){
        //Registering laboratory to test the delete method
        laboratoryService.createLaboratory(new Laboratory(1, "YUT"));

        //Finding the laboratory
        Laboratory laboratory = laboratoryService.getLaboratoryById(1);

        //Verifying the found laboratory is not null
        Assertions.assertNotNull(laboratory);

        //Executing delete method
        laboratoryService.removeLaboratory(1);

        //Getting deleted laboratory
        Laboratory deletedLaboratory = laboratoryService.getLaboratoryById(1);

        //Verifying laboratory doesn't exist
        Assertions.assertNull(deletedLaboratory);
    }


}
