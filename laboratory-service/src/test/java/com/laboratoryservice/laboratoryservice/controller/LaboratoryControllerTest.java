package com.laboratoryservice.laboratoryservice.controller;

import com.laboratoryservice.laboratoryservice.entity.Laboratory;
import com.laboratoryservice.laboratoryservice.service.LaboratoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class LaboratoryControllerTest {
      @Autowired
      private MockMvc mockMvc;
      @MockBean
      private LaboratoryService laboratoryService;

      @Test
      public void getAllLaboratories() throws Exception {
            List<Laboratory> laboratories = new ArrayList<>();
            laboratories.add(new Laboratory(1, "Lab A"));
            laboratories.add(new Laboratory(2, "Lab B"));

            Mockito.when(laboratoryService.getAllLaboratories()).thenReturn(laboratories);

            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/v1/laboratory"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
      }

      @Test
      public void createLaboratory() throws Exception {
            Laboratory createdLaboratory = new Laboratory();
            createdLaboratory.setId(1);
            createdLaboratory.setLaboratoryName("Lab A");

            Mockito.when(laboratoryService.createLaboratory(Mockito.any(Laboratory.class)))
                    .thenReturn(createdLaboratory);

            String requestBody = "{\"laboratoryName\": \"Lab A\"}";

            mockMvc.perform(MockMvcRequestBuilders
                            .post("/api/v1/laboratory")
                            .content(requestBody)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.laboratoryName").value("Lab A"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

      }

      @Test
      public void getLaboratoryById() throws Exception {
            Laboratory laboratory = new Laboratory();
            laboratory.setId(1);
            laboratory.setLaboratoryName("Lab A");

            Mockito.when(laboratoryService.getLaboratoryById(1)).thenReturn(laboratory);

            mockMvc.perform(MockMvcRequestBuilders
                            .get("/api/v1/laboratory/{id}", 1))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.laboratoryName").value("Lab A"));
      }

      @Test
      public void deleteLaboratoryById() throws Exception{
            Laboratory laboratory = new Laboratory();
            laboratory.setId(1);
            laboratory.setLaboratoryName("Lab A");

            Mockito.doNothing().when(laboratoryService).removeLaboratory(1);

            mockMvc.perform(MockMvcRequestBuilders
                            .delete("/api/v1/laboratory/{id}", 1))
                    .andExpect(MockMvcResultMatchers.status().isNoContent());

      }

      @Test
      public void updateLaboratory() throws Exception {
            Laboratory existingLaboratory = new Laboratory();
            existingLaboratory.setId(1);
            existingLaboratory.setLaboratoryName("Lab A");

            Laboratory updatedLaboratory = new Laboratory();
            updatedLaboratory.setId(1);
            updatedLaboratory.setLaboratoryName("Updated Lab A");

            Mockito.when(laboratoryService.getLaboratoryById(1))
                    .thenReturn(existingLaboratory);
            Mockito.when(laboratoryService.createLaboratory(Mockito.any(Laboratory.class)))
                    .thenReturn(updatedLaboratory);

            String requestBody = "{\"laboratoryName\": \"Updated Lab A\"}";

            mockMvc.perform(MockMvcRequestBuilders
                            .put("/api/v1/laboratory/{id}", 1)
                            .content(requestBody)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.laboratoryName").value("Updated Lab A"));
      }
      


}
