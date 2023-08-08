package com.laboratoryservice.laboratoryservice.repository;

import com.laboratoryservice.laboratoryservice.entity.Laboratory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DataJpaTest
public class LaboratoryRepositoryTest {

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Test
    public void createLaboratoryTest(){
          //Registering laboratory to test the save method
          laboratoryRepository.save(new Laboratory(1, "A"));

          //Finding the laboratory
          Laboratory laboratory = laboratoryRepository.findById(1).orElse(null);

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
        laboratoryRepository.save(new Laboratory(1, "YUT"));

        //Finding the laboratory
        Laboratory laboratory = laboratoryRepository.findById(1).orElse(null);

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
        laboratoryRepository.save(new Laboratory(1, "YUT"));
        laboratoryRepository.save(new Laboratory(2, "OPT"));
        laboratoryRepository.save(new Laboratory(3, "ALT"));

        //Using the method findAll() to find all laboratories
        List<Laboratory> laboratories = laboratoryRepository.findAll();

        //Confirming that laboratories is not empty
        Assertions.assertTrue(!laboratories.isEmpty());

        //Confirming the size of the list
        Assertions.assertEquals(3, laboratories.size());

        //Confirming the laboratories names
        Assertions.assertEquals("YUT", laboratories.get(0).getLaboratoryName());
        Assertions.assertEquals("OPT", laboratories.get(1).getLaboratoryName());
        Assertions.assertEquals("ALT", laboratories.get(2).getLaboratoryName());
    }

    @Test
    public void deleteLaboratory(){
        //Registering laboratory to test the delete method
        laboratoryRepository.save(new Laboratory(1, "YUT"));

        //Finding the laboratory
        Laboratory laboratory = laboratoryRepository.findById(6).orElse(null);

        //Verifying the found laboratory is not null
        Assertions.assertNotNull(laboratory);

        //Executing delete method
        laboratoryRepository.deleteById(1);

        //Getting deleted laboratory
        Laboratory deletedLaboratory = laboratoryRepository.findById(6).orElse(null);

        //Verifying laboratory doesn't exist
        Assertions.assertNull(deletedLaboratory);
    }

    @Test
    public void updateLaboratory(){
        //Registering laboratory to test save method
        laboratoryRepository.save(new Laboratory(1, "YUT"));

        //Registering a laboratory with the same id, but with different name
        laboratoryRepository.save(new Laboratory(1, "ALT"));

        //Finding the laboratory
        Laboratory laboratory = laboratoryRepository.findById(1).orElse(null);

        //Verifying the found laboratory is not null
        Assertions.assertNotNull(laboratory);

        //Verifying the found laboratory has the new name
        Assertions.assertEquals("ALT",laboratory.getLaboratoryName());
    }


}
