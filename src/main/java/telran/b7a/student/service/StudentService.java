package telran.b7a.student.service;

import telran.b7a.student.dto.ScoreDto;
import telran.b7a.student.dto.StudentCredentialsDto;
import telran.b7a.student.dto.StudentDto;
import telran.b7a.student.dto.UpdateStudentDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface StudentService {
    boolean addStudent(StudentCredentialsDto studentCredentialsDto);

    StudentDto findStudent(Integer id);

    StudentDto deleteStudent(Integer id);

    StudentCredentialsDto updateStudent(Integer id, UpdateStudentDto updateStudentDto);

    boolean addScore(Integer id, ScoreDto scoreDto);

    List<StudentDto> findStudentsByName(String name);

//    List<StudentDto> findStudentsByMinScore();

    long getStudentsNamesQuantity(List<String> names);

    List<StudentDto> getStudentsByExamScore(String exam, int score);
}
