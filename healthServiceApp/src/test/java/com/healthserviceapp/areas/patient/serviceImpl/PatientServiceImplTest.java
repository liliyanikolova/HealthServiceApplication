package com.healthserviceapp.areas.patient.serviceImpl;

import com.healthserviceapp.areas.patient.entities.Patient;
import com.healthserviceapp.areas.patient.exceptions.PatientNotFoundException;
import com.healthserviceapp.areas.patient.models.bindingModels.EditPatientBindingModel;
import com.healthserviceapp.areas.patient.repositories.PatientRepository;
import com.healthserviceapp.areas.patient.services.PatientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PatientServiceImplTest {

    public static final Long VALID_ID = 1L;

    public static final Long INVALID_ID = -1L;

    public static final String EGN = "7710230980";

    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Before
    public void setUp() throws Exception {
        Patient patient = new Patient();
        patient.setId(VALID_ID);
        patient.setEgn(EGN);
        when(this.patientRepository.findOne(VALID_ID)).thenReturn(patient);
    }

    @Test
    public void findPatientById_GivenValidPatient_ShouldReturnValidModel() throws Exception {
        //Act
        EditPatientBindingModel editPatientBindingModel = this.patientService.findPatientById(VALID_ID);

        //Assert Id
        assertEquals(VALID_ID, editPatientBindingModel.getId());
        //Assert Egn
        assertEquals(EGN, editPatientBindingModel.getEgn());
        //Assert first name
        assertNull(editPatientBindingModel.getFirstName());
    }

    @Test
    public void findPatientById_GivenValidPatient_ShouldCallRepositoryFindById() throws Exception {
        this.patientService.findPatientById(VALID_ID);

        //Assert Database Call
        verify(this.patientRepository, times(1)).findOne(VALID_ID);
    }

    @Test(expected = PatientNotFoundException.class)
    public void findByIdGivenInvalidPatientId_ShouldThrowException() throws Exception {
        //Act
        this.patientService.findPatientById(INVALID_ID);
    }

}