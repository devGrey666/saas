package com.grey.saas.base;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Value
@AllArgsConstructor
public class BaseResponse<T> {

    int code;
    String message;
    T body;

    public BaseResponse(T body) {
        this.code = 200;
        this.message = "success";
        this.body = body;
    }

    public ResponseEntity<BaseResponse<T>> ok() {
        return new ResponseEntity<>(this, HttpStatus.OK);
    }

    public ResponseEntity<BaseResponse<T>> get(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }
}