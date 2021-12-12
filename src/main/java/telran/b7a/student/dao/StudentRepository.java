package telran.b7a.student.dao;

import telran.b7a.student.model.Student;

import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student>findById(int id);
    Student removeStudentById(int id);
}
