package com.example.reactivebooking.model.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ValidationErrorResponse extends ErrorResponse {
    private Map<String, List<String>> errors;
}
