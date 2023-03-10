//package com.example.demojpa.exception;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.http.HttpStatus.BAD_REQUEST;
//import static org.springframework.http.HttpStatus.OK;
//
//
//@ControllerAdvice
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class CustomExceptionHandler {
//
////    @ResponseStatus(BAD_REQUEST)
////    @ResponseBody
////    @ExceptionHandler(MethodArgumentNotValidException.class)
////    public Error methodArgumentNotValidException(MethodArgumentNotValidException ex) {
////        BindingResult result = ex.getBindingResult();
////        List<FieldError> fieldErrors = result.getFieldErrors();
////        return processFieldErrors(fieldErrors);
////    }
////
//
//    @ResponseStatus(OK)
//    @ResponseBody
//    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
//    public MyCustomError methodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        BindingResult result = ex.getBindingResult();
//        List<FieldError> fieldErrors = result.getFieldErrors();
//        return processFieldErrors(fieldErrors);
//    }
//
//    @ResponseStatus(OK)
//    @ResponseBody
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public MyCustomError notReadableExceptionHandle(HttpMessageNotReadableException ex) {
//        return new MyCustomError(500, ex.getMessage());
//    }
//
//    @ResponseStatus(OK)
//    @ResponseBody
//    @ExceptionHandler(value = {Exception.class})
//    public MyCustomError methodArgumentNotValidException(Exception ex) {
//        return new MyCustomError(500, ex.getMessage());
//    }
//
//    private MyCustomError processFieldErrors(List<FieldError> fieldErrors) {
//        MyCustomError error = new MyCustomError(BAD_REQUEST.value(), "validation error");
//        for (FieldError fieldError : fieldErrors) {
//            error.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
//        }
//        return error;
//    }
//
//    static class MyCustomError {
//        private final int status;
//        private final String message;
//        private List<DetailError> fieldErrors = new ArrayList<>();
//
//        MyCustomError(int status, String message) {
//            this.status = status;
//            this.message = message;
//        }
//
//        public int getStatus() {
//            return status;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void addFieldError(String fieldName, String message) {
//            DetailError error = new DetailError(fieldName, message);
//            fieldErrors.add(error);
//        }
//
//        public List<DetailError> getFieldErrors() {
//            return fieldErrors;
//        }
//    }
//
//    @Data
//    @AllArgsConstructor
//    public static class DetailError {
//        private String field;
//        private String message;
//    }
//}
