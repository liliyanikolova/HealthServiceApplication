package com.healthserviceapp.areas.users.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such title")
public class TitleNotFoundException extends RuntimeException {
}
