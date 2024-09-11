package com.ayed.gestionstock.handlers;

import com.ayed.gestionstock.exception.errorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class errorDTO {

    private Integer httpCode;

    private errorCodes code;

    private String message;

    private List<String> errors = new ArrayList<>();
}
