package com.fauzanirfanto.product_manager.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public ResponseEntity<Object> handleResponse(String message,
                                                 HttpStatus status,
                                                 Object data,
                                                 Object errorCode,
                                                 HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("message",message);
        map.put("status",status.value());
        map.put("data",data==null?"":data);
        map.put("timestamp", LocalDateTime.now());
        map.put("success",!status.isError());
        if(errorCode!=null) {// ini tandanya error !!
            map.put("error-code",errorCode);
            map.put("path",request.getPathInfo());
        }
        return new ResponseEntity<>(map,status);
    }

    public ResponseEntity<Object> handleResponse(String message,
                                                 HttpStatus status,
                                                 Object data,
                                                 Object errorCode,
                                                 WebRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("message",message);
        map.put("status",status.value());
        map.put("data",data==null?"":data);
        map.put("timestamp", LocalDateTime.now());
        map.put("success",!status.isError());
        if(errorCode!=null) {// ini tandanya error !!
            map.put("error-code",errorCode);
            map.put("path",request.getContextPath());
        }
        return new ResponseEntity<>(map,status);
    }

}
