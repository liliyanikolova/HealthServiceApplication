package com.healthserviceapp.areas.patient.controllers;

import com.healthserviceapp.areas.patient.models.bindingModels.EditPatientBindingModel;
import com.healthserviceapp.areas.patient.services.PatientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(PatientController.class)
@ActiveProfiles("test")
public class PatientControllerTest {

    public static final Long ID = 1L;
    public static final String EGN = "9011092571";
    public static final String TOWN = "Sofia";
    public static final String PAGE_TITLE = "HealthService - Редактиране на пациент";

    public static final String RZOK_NUMBER = "022";
    public static final String HEALTH_SERVICE = "123";
    public static final String FIRST_NAME = "Ana";
    public static final String SURNAME = "Iliva";
    public static final String LAST_NAME = "Atova";
    public static final String ADDRESS = "Mladost 1";
    public static final String GP = "Maria Simeonova";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PatientService patientService;

    @Before
    public void setUp() throws Exception {
        //Arrange
        EditPatientBindingModel editPatientBindingModel = new EditPatientBindingModel();
        editPatientBindingModel.setId(ID);
        editPatientBindingModel.setEgn(EGN);
        editPatientBindingModel.setTown(TOWN);

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