package com.medicineservice.medicineservice.controller;

import com.medicineservice.medicineservice.entity.Medicine;
import com.medicineservice.medicineservice.models.Laboratory;
import com.medicineservice.medicineservice.service.MedicineService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicineControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MedicineService medicineService;

    @Test
    public void getAllMedicines() throws Exception {
        List<Medicine> medicines = new ArrayList<>();
        medicines.add(new Medicine(1,"Acetaminofen",new Date(), new Date(), 4, 4200F,2));

        medicines.add(new Medicine(2,"Pencil",new Date(), new Date(), 4, 3200F,3));

        Mockito.when(medicineService.getAllMedicines()).thenReturn(medicines);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/medicine"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    public void createMedicine() throws Exception {
        Laboratory existingLaboratory = new Laboratory();
        existingLaboratory.setId(2);
        existingLaboratory.setLaboratoryName("Lab B");

        Medicine createdMedicine = new Medicine();
        createdMedicine.setId(1);
        createdMedicine.setName("Acetaminofen");
        createdMedicine.setStock(4);
        createdMedicine.setLaboratoryId(2);
        createdMedicine.setUnitValue(4200);
        createdMedicine.setFabricationDate(new Date());
        createdMedicine.setDueDate(new Date());


        Mockito.when(medicineService.getLaboratoryById(2))
                .thenReturn(existingLaboratory);
        Mockito.when(medicineService.createMedicine(Mockito.any(Medicine.class)))
                .thenReturn(createdMedicine);

        String requestBody = "{\"name\": \"Acetaminofen\", \"stock\": 4, \"laboratoryId\": 2, \"unitValue\": 4200, \"fabricationDate\": \"2023-08-08\", \"dueDate\": \"2023-08-15\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/medicine")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Acetaminofen"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void getMedicineByLaboratory() throws Exception {
        Laboratory existingLaboratoryOne = new Laboratory();
        existingLaboratoryOne.setId(2);
        existingLaboratoryOne.setLaboratoryName("Lab B");

        Laboratory existingLaboratoryTwo = new Laboratory();
        existingLaboratoryTwo.setId(3);
        existingLaboratoryTwo.setLaboratoryName("Lab c");

        Medicine createdMedicineA = new Medicine();
        createdMedicineA.setId(1);
        createdMedicineA.setName("Acetaminofen");
        createdMedicineA.setStock(4);
        createdMedicineA.setLaboratoryId(2);
        createdMedicineA.setUnitValue(4200);
        createdMedicineA.setFabricationDate(new Date());
        createdMedicineA.setDueDate(new Date());

        Medicine createdMedicineB = new Medicine();
        createdMedicineB.setId(2);
        createdMedicineB.setName("Pencil");
        createdMedicineB.setStock(5);
        createdMedicineB.setLaboratoryId(3);
        createdMedicineB.setUnitValue(4200);
        createdMedicineB.setFabricationDate(new Date());
        createdMedicineB.setDueDate(new Date());

        List<Medicine> medicines = new ArrayList<>();
        medicines.add(createdMedicineA);
        medicines.add(createdMedicineB);

        Mockito.when(medicineService.getLaboratoryById(2)).thenReturn(existingLaboratoryOne);
        Mockito.when(medicineService.getLaboratoryById(3)).thenReturn(existingLaboratoryTwo);
        Mockito.when(medicineService.getMedicinesByLaboratory(2)).thenReturn(medicines);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/medicine/laboratory/{laboratoryId}",2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));

    }

    @Test
    public void getMedicineById() throws Exception {
        Laboratory existingLaboratoryOne = new Laboratory();
        existingLaboratoryOne.setId(2);
        existingLaboratoryOne.setLaboratoryName("Lab B");

        Medicine createdMedicine = new Medicine();
        createdMedicine.setId(1);
        createdMedicine.setName("Acetaminofen");
        createdMedicine.setStock(4);
        createdMedicine.setLaboratoryId(2);
        createdMedicine.setUnitValue(4200);
        createdMedicine.setFabricationDate(new Date());
        createdMedicine.setDueDate(new Date());


        Mockito.when(medicineService.getLaboratoryById(2)).thenReturn(existingLaboratoryOne);
        Mockito.when(medicineService.getById(1)).thenReturn(createdMedicine);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/medicine/{id}",1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Acetaminofen"));;

    }

    @Test
    public void deleteMedicineByLaboratory() throws Exception{
        Laboratory existingLaboratoryOne = new Laboratory();
        existingLaboratoryOne.setId(2);
        existingLaboratoryOne.setLaboratoryName("Lab B");

        Medicine createdMedicine = new Medicine();
        createdMedicine.setId(1);
        createdMedicine.setName("Acetaminofen");
        createdMedicine.setStock(4);
        createdMedicine.setLaboratoryId(2);
        createdMedicine.setUnitValue(4200);
        createdMedicine.setFabricationDate(new Date());
        createdMedicine.setDueDate(new Date());

        Mockito.when(medicineService.getLaboratoryById(2)).thenReturn(existingLaboratoryOne);
        Mockito.doNothing().when(medicineService).removeMedicine(createdMedicine);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/medicine/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    public void updateLaboratory() throws Exception {
        Medicine existingMedicine = new Medicine();
        existingMedicine.setId(1);
        existingMedicine.setName("Existing Medicine");
        existingMedicine.setLaboratoryId(2);

        Laboratory existingLaboratory = new Laboratory();
        existingLaboratory.setId(2);
        existingLaboratory.setLaboratoryName("Lab B");

        Medicine updatedMedicine = new Medicine();
        updatedMedicine.setId(1);
        updatedMedicine.setName("Updated Medicine");
        updatedMedicine.setLaboratoryId(2);

        Mockito.when(medicineService.getById(1))
                .thenReturn(existingMedicine);
        Mockito.when(medicineService.getLaboratoryById(2))
                .thenReturn(existingLaboratory);
        Mockito.when(medicineService.createMedicine(Mockito.any(Medicine.class)))
                .thenReturn(updatedMedicine);

        String requestBody = "{\"name\": \"Updated Medicine\", \"laboratoryId\": 2, \"stock\": 10, \"unitValue\": 5000}";

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/medicine/{id}", 1)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Medicine"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void updateStockMedicine() throws Exception {
        Medicine existingMedicine = new Medicine();
        existingMedicine.setId(1);
        existingMedicine.setName("Existing Medicine");
        existingMedicine.setStock(10);
        // Establecer otros campos según tu necesidad

        Mockito.when(medicineService.getById(1))
                .thenReturn(existingMedicine);
        Mockito.when(medicineService.createMedicine(Mockito.any(Medicine.class)))
                .thenReturn(existingMedicine);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/medicine/stock/{id}", 1)
                        .param("stock", "3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.stock").value(7));
    }



}
