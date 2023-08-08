package com.ventasservice.ventasservice.controller;

import com.ventasservice.ventasservice.entity.Sale;
import com.ventasservice.ventasservice.models.Medicine;
import com.ventasservice.ventasservice.service.SaleService;
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
public class SaleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SaleService saleService;


    @Test
    public void createSale() throws Exception {
        Medicine createdMedicine = new Medicine();
        createdMedicine.setId(1);
        createdMedicine.setName("Acetaminofen");
        createdMedicine.setStock(4);
        createdMedicine.setLaboratoryId(2);
        createdMedicine.setUnitValue(4200);
        createdMedicine.setFabricationDate(new Date());
        createdMedicine.setDueDate(new Date());

        Sale createdSale = new Sale();
        createdSale.setId(1);
        createdSale.setStockSale(4);
        createdSale.setCreationDate(new Date());
        createdSale.setUnitValue(1400);
        createdSale.setTotalValue(2800);
        createdSale.setMedicineId(1);

        Mockito.when(saleService.getMedicineById(2))
                .thenReturn(createdMedicine);
        Mockito.when(saleService.createSale(Mockito.any(Sale.class)))
                .thenReturn(createdSale);

        String requestBody = "{\"medicineId\": 2, \"stockSale\": 4, \"unitValue\": 1400}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/sale")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stockSale").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalValue").value(2800));
    }

    @Test
    public void getAllSales() throws Exception {
        List<Sale> sales = new ArrayList<>();

        Medicine createdMedicine = new Medicine();
        createdMedicine.setId(1);
        createdMedicine.setName("Acetaminofen");
        createdMedicine.setStock(4);
        createdMedicine.setLaboratoryId(2);
        createdMedicine.setUnitValue(4200);
        createdMedicine.setFabricationDate(new Date());
        createdMedicine.setDueDate(new Date());

        Sale createdSaleOne = new Sale();
        createdSaleOne.setId(1);
        createdSaleOne.setStockSale(4);
        createdSaleOne.setCreationDate(new Date());
        createdSaleOne.setUnitValue(1400);
        createdSaleOne.setTotalValue(2800);
        createdSaleOne.setMedicineId(1);

        Sale createdSaleTwo = new Sale();
        createdSaleTwo.setId(2);
        createdSaleTwo.setStockSale(8);
        createdSaleTwo.setCreationDate(new Date());
        createdSaleTwo.setUnitValue(2400);
        createdSaleTwo.setTotalValue(4800);
        createdSaleTwo.setMedicineId(1);

        sales.add(createdSaleTwo);
        sales.add(createdSaleOne);

        Mockito.when(saleService.getMedicineById(2))
                .thenReturn(createdMedicine);

        Mockito.when(saleService.getAllSales()).thenReturn(sales);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/sale"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));


    }

    @Test
    public void getSalesByDateRange() throws Exception {
        List<Sale> sales = new ArrayList<>();

        Medicine createdMedicine = new Medicine();
        createdMedicine.setId(1);
        createdMedicine.setName("Acetaminofen");
        createdMedicine.setStock(4);
        createdMedicine.setLaboratoryId(2);
        createdMedicine.setUnitValue(4200);
        createdMedicine.setFabricationDate(new Date());
        createdMedicine.setDueDate(new Date());

        Sale createdSaleOne = new Sale();
        createdSaleOne.setId(1);
        createdSaleOne.setStockSale(4);
        createdSaleOne.setCreationDate(new Date());
        createdSaleOne.setUnitValue(1400);
        createdSaleOne.setTotalValue(2800);
        createdSaleOne.setMedicineId(1);

        Sale createdSaleTwo = new Sale();
        createdSaleTwo.setId(2);
        createdSaleTwo.setStockSale(8);
        createdSaleTwo.setCreationDate(new Date());
        createdSaleTwo.setUnitValue(2400);
        createdSaleTwo.setTotalValue(4800);
        createdSaleTwo.setMedicineId(1);

        sales.add(createdSaleOne);
        sales.add(createdSaleTwo);

        String dateOne = "2023-08-01";
        String dateTwo = "2023-08-10";

        Mockito.when(saleService.getSalesByDateRange(dateOne, dateTwo))
                .thenReturn(sales);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/sale/dates/{dateOne}/{dateTwo}", dateOne, dateTwo))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2));


    }

    @Test
    public void getSaleById() throws Exception {

        Medicine createdMedicine = new Medicine();
        createdMedicine.setId(1);
        createdMedicine.setName("Acetaminofen");
        createdMedicine.setStock(4);
        createdMedicine.setLaboratoryId(2);
        createdMedicine.setUnitValue(4200);
        createdMedicine.setFabricationDate(new Date());
        createdMedicine.setDueDate(new Date());

        Sale createdSaleOne = new Sale();
        createdSaleOne.setId(1);
        createdSaleOne.setStockSale(4);
        createdSaleOne.setCreationDate(new Date());
        createdSaleOne.setUnitValue(1400);
        createdSaleOne.setTotalValue(2800);
        createdSaleOne.setMedicineId(1);

        Mockito.when(saleService.getMedicineById(2))
                .thenReturn(createdMedicine);

        Mockito.when(saleService.getSaleById(1)).thenReturn(createdSaleOne);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/sale/{id}",1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.medicineId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalValue").value(2800));


    }
}
