package com.healthserviceapp.areas.medicine.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such medicine")
public class MedicineNotFoundException extends RuntimeException {
}
