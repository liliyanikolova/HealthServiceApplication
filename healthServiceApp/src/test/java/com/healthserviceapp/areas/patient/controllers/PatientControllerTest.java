package com.healthserviceapp.areas.patient.controllers;

import com.healthserviceapp.areas.patient.models.bindingModels.EditPatientBindingModel;
import com.healthserviceapp.areas.patient.services.PatientService;
import com.healthserviceapp.interceptors.EditPageTitleInterceptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@WebMvcTest(PatientController.class)
@ActiveProfiles("test")
public class PatientControllerTest {

    public static final Long ID = 1L;

    public static final String EGN = "9011092571";

    public static final String TOWN = "Sofia";

    public static final String PAGE_TITLE = "HealthService - Редактиране на пациент";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PatientService patientService;

//    @MockBean
//    private EditPageTitleInterceptor editPageTitleInterceptor;

    @Before
    public void setUp() throws Exception {
        //Arrange
        EditPatientBindingModel editPatientBindingModel = new EditPatientBindingModel();
        editPatientBindingModel.setId(ID);
        editPatientBindingModel.setEgn(EGN);
        editPatientBindingModel.setTown(TOWN);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", PAGE_TITLE);
        when(this.patientService.findPatientById(ID)).thenReturn(editPatientBindingModel);
    }

    @Test
    public void getAddPatientPage_shouldPass() throws Exception {
        this.mvc.perform(get("/patients/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("patients/add"));
    }

    @Test
    public void getEditPatientPage_ValidPatient_ShouldReturnEditPatientPage() throws Exception {
        //Act
        this.mvc
                .perform(get("/patients/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("patients/edit"))
                .andExpect(model().attribute("editPatientBindingModel", hasProperty("id", is(ID))))
                .andExpect(model().attribute("editPatientBindingModel", hasProperty("egn", is(EGN))))
                .andExpect(model().attribute("editPatientBindingModel", hasProperty("town", is(TOWN))));

        verify(this.patientService, times(1)).findPatientById(ID);
        verifyNoMoreInteractions(this.patientService);
    }

}