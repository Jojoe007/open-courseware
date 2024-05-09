package com.takdanai.courseware.controllers.exception;

import com.takdanai.courseware.exceptions.CourseNotFoundException;
import com.takdanai.courseware.exceptions.TopicNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class CourseExceptionController {

    @ExceptionHandler({CourseNotFoundException.class, TopicNotFoundException.class})
    public String notFound(Exception exception) {
        log.error(exception.getMessage());

        return "errors/404";
    }

}
