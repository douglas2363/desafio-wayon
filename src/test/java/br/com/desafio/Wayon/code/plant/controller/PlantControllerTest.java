//package br.com.desafio.Wayon.code.plant.controller;
//
//import br.com.desafio.Wayon.code.plant.management.Plant;
//import br.com.desafio.Wayon.code.plant.management.PlantController;
//import br.com.desafio.Wayon.code.plant.management.PlantService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;
//
//import static jdk.jfr.internal.jfc.model.Constraint.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(PlantController.class)
//public class PlantControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private PlantService plantService;
//
//    private Plant validPlant;
//    private Plant invalidPlant;
//
//    @BeforeEach
//    public void setUp() {
//        validPlant = new Plant("123", "A beautiful flower");
//        invalidPlant = new Plant("123ABC", "Invalid plant with alphanumeric code");
//
//        // Configuração adicional do mock se necessário
//    }
//
//    @Test
//    public void testCreatePlant_DuplicateCode() throws Exception {
//        when(plantService.createPlant(any(Plant.class)))
//                .thenThrow(new PlantAlreadyExistsException("Planta já existe"));
//
//        mockMvc.perform(post("/plants")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"code\": \"123\", \"description\": \"A beautiful flower\"}"))
//                .andExpect(status().isConflict())
//                .andExpect(content().string("Planta já existe"));
//    }
//
//    @Test
//    public void testCreatePlant_EmptyCode() throws Exception {
//        mockMvc.perform(post("/plants")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"code\": \"\", \"description\": \"A beautiful flower\"}"))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Código é obrigatório"));
//    }
//
//    @Test
//    public void testCreatePlant_CodeWithLetters() throws Exception {
//        mockMvc.perform(post("/plants")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"code\": \"123ABC\", \"description\": \"A beautiful flower\"}"))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Código inválido"));
//    }
//
//    @Test
//    public void testCreatePlant_DescriptionTooLong() throws Exception {
//        mockMvc.perform(post("/plants")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"code\": \"123\", \"description\": \"A very beautiful flower\"}"))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("Descrição não pode ser maior que 10 caracteres"));
//    }
//
//    @Test
//    @WithMockUser(roles = "USER")
//    public void testDeletePlant_AsUser_Failure() throws Exception {
//        mockMvc.perform(delete("/plants/{code}", "123"))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    public void testDeletePlant_AsAdmin_Success() throws Exception {
//        doNothing().when(plantService).deletePlant("123");
//
//        mockMvc.perform(delete("/plants/{code}", "123"))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    public void testSearchPlant_CodeNotFound() throws Exception {
//        String searchTerm = "999";
//        when(plantService.search(searchTerm)).thenReturn(List.of());
//
//        mockMvc.perform(get("/plants/search")
//                        .param("term", searchTerm))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(0));
//    }
//
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    public void testUpdatePlant_Valid() throws Exception {
//        String newDescription = "Updated beautiful flower";
//        Plant updatedPlant = new Plant("123", newDescription);
//        when(plantService.updatePlant(eq("123"), any(Plant.class))).thenReturn(updatedPlant);
//
//        mockMvc.perform(put("/plants/{code}", "123")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"description\": \"Updated beautiful flower\"}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.description").value(newDescription));
//    }
//}