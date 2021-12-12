package telran.b7a.student.dto;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "student not found")
//@NoArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(int id) {
        super("Student with id " + id + " NOT FOUND");
    }

    private static final long serialVersionUID = 1234;

}
