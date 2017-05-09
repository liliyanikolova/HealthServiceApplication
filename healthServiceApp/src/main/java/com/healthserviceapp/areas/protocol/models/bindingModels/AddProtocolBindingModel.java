package com.healthserviceapp.areas.protocol.models.bindingModels;

import com.healthserviceapp.areas.protocol.customValidations.IsProtocolNumberUnique;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class AddProtocolBindingModel {

    @IsProtocolNumberUnique
    @NotEmpty(message = "Въведете номер на протокола")
    private String number;

    @NotEmpty(message = "Въведете дата на издаване")
    private Date dateOfIssue;

    @NotEmpty(message = "Въведете срок на протокола")
    private Integer term;

    @NotEmpty(message = "Изберете пациент")
    private String patient;

    @NotEmpty(message = "Изберете диагноза")
    private String diagnosis;

    @NotNull(message = "Въведете предписание")
    private PrescriptionBindingModel firstPrescription;

    private PrescriptionBindingModel secondPrescription;

    private PrescriptionBindingModel thirdPrescription;

    public AddProtocolBindingModel() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public PrescriptionBindingModel getFirstPrescription() {
        return firstPrescription;
    }

    public void setFirstPrescription(PrescriptionBindingModel firstPrescription) {
        this.firstPrescription = firstPrescription;
    }

    public PrescriptionBindingModel getSecondPrescription() {
        return secondPrescription;
    }

    public void setSecondPrescription(PrescriptionBindingModel secondPrescription) {
        this.secondPrescription = secondPrescription;
    }

    public PrescriptionBindingModel getThirdPrescription() {
        return thirdPrescription;
    }

    public void setThirdPrescription(PrescriptionBindingModel thirdPrescription) {
        this.thirdPrescription = thirdPrescription;
    }
}
