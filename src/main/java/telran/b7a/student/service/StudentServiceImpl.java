package telran.b7a.student.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.b7a.student.dao.StudentsMongoRepository;
import telran.b7a.student.dto.*;
import telran.b7a.student.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    //    StudentRepository studentRepository;
    StudentsMongoRepository studentRepository;
    ModelMapper modelMapper;

    @Autowired
    public StudentServiceImpl(StudentsMongoRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addStudent(StudentCredentialsDto studentCredentialsDto) {
        if (studentRepository.findById(studentCredentialsDto.getId()).isPresent()) {
            return false;
        }
        Student student = modelMapper.map(studentCredentialsDto, Student.class);
        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        if (student == null) return null;
//        return StudentDto.builder()
//                .id(student.getId())
//                .name(student.getName())
//                .scores(student.getScores())
//                .build();
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto deleteStudent(Integer id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.deleteById(id);
        return student == null ? null : modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentCredentialsDto updateStudent(Integer id, UpdateStudentDto updateStudentDto) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        student.setName(updateStudentDto.getName());
        student.setPassword(updateStudentDto.getPassword());
        return modelMapper.map(student, StudentCredentialsDto.class);
    }

    @Override
    public boolean addScore(Integer id, ScoreDto scoreDto) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        boolean res = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
        studentRepository.save(student);
        return res;
    }

    @Override
    public List<StudentDto> findStudentsByName(String name) {
        return studentRepository.findAll().stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .map(s -> modelMapper.map(s, StudentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> findStudentsByMinScore() {
        int min = studentRepository.findAll().stream()
                .flatMap(e -> e.getScores().values().stream())
                .mapToInt(Integer::intValue)
                .min().orElse(0);

       return studentRepository.findAll().stream()
                .filter(e -> e.getScores().containsValue(min))
                .map(s -> modelMapper.map(s, StudentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Integer quantity() {
//        int a = studentRepository.findAll().size();
//        System.out.println(a);
//        return a;
        return null;
    }

}
