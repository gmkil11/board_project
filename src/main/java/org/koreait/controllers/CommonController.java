package org.koreait.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.koreait.commons.exceptions.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice("org.koreait.controllers")
public class CommonController {


    @ExceptionHandler(Exception.class)
    public String errorHandler(Exception e, Model model, HttpServletRequest request, HttpServletResponse response) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (e instanceof CommonException){
            CommonException commonException = (CommonException)e;
            status = commonException.getStatus();
        }

        response.setStatus(status.value());

        Map<String, String> attrs =  new HashMap<>();
        attrs.put("status", String.valueOf(status.value()));
        attrs.put("path", request.getRequestURI());
        attrs.put("metod", request.getMethod());
        attrs.put("message", e.getMessage());
        attrs.put("timeStamp", LocalDateTime.now().toString());

        model.addAllAttributes(attrs);
        e.printStackTrace();

        log.error(e.getMessage());

        return "error/common";
    }
}