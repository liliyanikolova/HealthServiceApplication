package com.healthserviceapp.areas.protocol.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such diagnosis")
public class DiagnosisNotFoundException extends RuntimeException {
}
