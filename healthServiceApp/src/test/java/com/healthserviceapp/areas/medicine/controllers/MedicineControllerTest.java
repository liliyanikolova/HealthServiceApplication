package com.healthserviceapp.areas.medicine.controllers;

import com.healthserviceapp.areas.medicine.customValidation.IsCodeUniqueValidator;
import com.healthserviceapp.areas.medicine.entities.Medicine;
import com.healthserviceapp.areas.medicine.models.bindingModels.AddMedicineBindingModel;
import com.healthserviceapp.areas.medicine.services.MedicineService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.ConstraintValidatorContext;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringRunner.class)
@WebMvcTest(MedicineController.class)
@ActiveProfiles("test")
public class MedicineControllerTest {

    public static final Long ID = 1L;
    public static final String CODE = "N22";
    public static final String NAME = "ELAX";
    public static final String MEASUREMENT = "ml";
    public static final String DOZES = "50";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MedicineService medicineService;

    @MockBean
    private IsCodeUniqueValidator isCodeUniqueValidator;

    @MockBean
    private ConstraintValidatorContext constraintValidatorContext;

    @Before
    public void setUp() throws Exception {
        when(this.isCodeUniqueValidator.isValid(CODE ,this.constraintValidatorContext)).thenReturn(true);
    }

    @Test
    public void getAllMedicines() throws Exception {
    }

    @Test
    public void getAddMedicinePage_shouldPass() throws Exception {
        this.mvc.perform(get("/medicines/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("medicines/add"));
    }

    @Test
    public void addMedicine_shouldPass() throws Exception {
        this.mvc
                .perform(post("/medicines/add")
                .param("code", CODE)
                .param("name", NAME)
                .param("measurement", MEASUREMENT)
                .param("doses", DOZES))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("medicines/add"));

        ArgumentCaptor<AddMedicineBindingModel> captor = ArgumentCaptor.forClass(AddMedicineBindingModel.class);
        verify(medicineService).addNewMedicine(captor.capture());
        AddMedicineBindingModel bu = captor.getValue();
        assertEquals(CODE, bu.getCode());
    }

    @Test
    public void deleteMedicine() throws Exception {

    }

//    @Test
//    public void getEditMedicinePage() throws Exception {
//        this.mvc
//                .perform(get("/medicines/edit/1"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("medicines/edit"))
//                .andExpect(model().attribute("editMedicineBindingModel", hasProperty("id", is(ID))))
//                .andExpect(model().attribute("editMedicineBindingModel", hasProperty("code", is(CODE))))
//                .andExpect(model().attribute("editMedicineBindingModel", hasProperty("name", is(NAME))))
//                .andExpect(model().attribute("editMedicineBindingModel", hasProperty("measurement", is(MEASUREMENT))))
//                .andExpect(model().attribute("editMedicineBindingModel", hasProperty("dozes", is(DOZES))));
//
//        verify(this.medicineService, times(1)).findMedicineById(ID);
//        verifyNoMoreInteractions(this.medicineService);
//    }

}