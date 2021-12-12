package telran.b7a.student.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.b7a.student.dao.StudentRepository;
import telran.b7a.student.dto.*;
import telran.b7a.student.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public boolean addStudent(StudentCredentialsDto studentCredentialsDto) {
        if (studentRepository.findById(studentCredentialsDto.getId()).isPresent()) {
            return false;
        }
//        Student student = new Student(studentCredentialsDto.getId(),
//                studentCredentialsDto.getName(), studentCredentialsDto.getPassword());
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
        Student student = studentRepository.removeStudentById(id);
//        if (student == null) return null;
//        return StudentDto.builder()
//                .id(student.getId())
//                .name(student.getName())
//                .scores(student.getScores())
//                .build();
        return  student == null ? null : modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentCredentialsDto updateStudent(Integer id, UpdateStudentDto updateStudentDto) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
//        if (student == null) return null;
        student.setName(updateStudentDto.getName());
        student.setPassword(updateStudentDto.getPassword());
//        return StudentCredentialsDto.builder()
//                .id(student.getId())
//                .name(updateStudentDto.getName())
//                .password(updateStudentDto.getPassword())
//                .build();
        return modelMapper.map(student, StudentCredentialsDto.class);
    }

    @Override
    public boolean addScore(Integer id, ScoreDto scoreDto) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
//        if (student == null){
//            throw new StudentNotFoundException(id);
//        }
        student.addScore(scoreDto.getExamName(), scoreDto.getScore());
        return true;
    }
}
