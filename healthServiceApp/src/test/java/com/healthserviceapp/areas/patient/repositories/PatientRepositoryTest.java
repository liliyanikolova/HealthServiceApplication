package com.healthserviceapp.areas.patient.repositories;

import com.healthserviceapp.areas.patient.entities.Patient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class PatientRepositoryTest {

    public static final String EGN = "8804158713";

    public static final String ANOTHER_EGN = "8904158711";

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PatientRepository patientRepository;

    @Before
    public void setUp() throws Exception{
        //Arrange
        Patient patient = new Patient();
        patient.setEgn(EGN);
        this.testEntityManager.persist(patient);
    }

    @Test
    public void findByEgn_ShouldReturnPatient() throws Exception {
        //Act
        Patient patient = this.patientRepository.findByEgn(EGN);

        //Assert
        assertEquals(EGN, patient.getEgn());
    }

    @Test
    public void findByEgn_ShouldNotReturnPatient() throws Exception {
        //Act
        Patient patient = this.patientRepository.findByEgn(ANOTHER_EGN);

        //Assert
        assertNull(patient);
    }
}