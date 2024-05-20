package t.education.metricsproducer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExamException extends RuntimeException {
    public ExamException() {
        super("Your set of exams is not suitable for admission to our institute.");
    }
}

