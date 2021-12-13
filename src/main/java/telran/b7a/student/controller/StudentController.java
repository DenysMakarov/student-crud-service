package telran.b7a.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.b7a.student.dto.ScoreDto;
import telran.b7a.student.dto.StudentCredentialsDto;
import telran.b7a.student.dto.StudentDto;
import telran.b7a.student.dto.UpdateStudentDto;
import telran.b7a.student.service.StudentService;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public boolean studentRegister(@RequestBody StudentCredentialsDto studentCredentialsDto){
        return studentService.addStudent(studentCredentialsDto);
    }

    @GetMapping("/student/{id}")
    public StudentDto findStudentById(@PathVariable("id") Integer studentId){
//        public StudentDto findStudentById(@PathVariable Integer id){
            return studentService.findStudent(studentId);
    }

    @DeleteMapping("/student/{id}")
        public StudentDto removeStudentById(@PathVariable Integer id){
        return studentService.deleteStudent(id);
    }

    @PutMapping("/student/{id}")
    public StudentCredentialsDto editStudent(@PathVariable Integer id, @RequestBody UpdateStudentDto updateStudentDto){
            return studentService.updateStudent(id, updateStudentDto);
    };

    @PutMapping("/score/student/{id}")
    public boolean addScore(@PathVariable Integer id, @RequestBody ScoreDto scoreDto){
        return studentService.addScore(id, scoreDto);
    };

    @GetMapping("/students/name/{name}")
    public List<StudentDto> findStudentsByName(@PathVariable String name){
        return studentService.findStudentsByName(name);
    }

    @GetMapping("students/exam/{exam}/minscore/score")
    public List<StudentDto> findStudentsByMinScore(@PathVariable String exam){
        return studentService.findStudentsByMinScore();
    }

    @GetMapping("students/quantity/students")
    public Integer quantity(){
        return studentService.quantity();
    }

}
