package com.rarwin.certification_nlw.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DefaultError {

    private int code;

    private String message;
}
