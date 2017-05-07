package com.healthserviceapp.areas.patient.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such patient")
public class PatientNotFoundException extends RuntimeException {
}
