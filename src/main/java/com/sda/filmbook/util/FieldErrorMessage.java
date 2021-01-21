package com.sda.filmbook.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FieldErrorMessage {

    private String field;
    private String message;


}
