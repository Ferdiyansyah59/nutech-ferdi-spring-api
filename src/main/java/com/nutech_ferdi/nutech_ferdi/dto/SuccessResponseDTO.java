package com.nutech_ferdi.nutech_ferdi.dto;

import lombok.Data;

@Data
public class SuccessResponseDTO<T> {
    private int status;
    private String message;
    private T data;

    public SuccessResponseDTO(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
