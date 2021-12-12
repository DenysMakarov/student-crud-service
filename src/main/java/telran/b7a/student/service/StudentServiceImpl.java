package telran.b7a.student.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.b7a.student.dao.StudentRepository;
import telran.b7a.student.dao.StudentsMongoRepository;
import telran.b7a.student.dto.*;
import telran.b7a.student.model.Student;

import java.util.List;
import java.util.Map;
import java.util.Set;
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
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.deleteById(id);
//                Student student = studentRepository.deleteById(id);
//        Student student = studentRepository.removeStudentById(id);
//        if (student == null) return null;
//        return StudentDto.builder()
//                .id(student.getId())
//                .name(student.getName())
//                .scores(student.getScores())
//                .build();
        return student == null ? null : modelMapper.map(student, StudentDto.class);
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
        Map<Integer, Set<Integer>> mapToScore = studentRepository.findAll().stream()
                .flatMap(e -> e.getScores().values().stream())
                .collect(Collectors.groupingBy(s -> s.intValue(), Collectors.toSet()));

        System.out.println(mapToScore);

//        studentRepository.findAll().stream()
//                .flatMap(e -> e.getScores().values().stream())
//                .forEach(System.out::println);
//        Map<Integer, StudentDto> scoreList = studentRepository.findAll()
//                .stream()
//                .flatMap(s -> s.getScores().entrySet().stream())
//                .collect(Collectors.groupingBy(e -> e.getValue(), Collectors.summarizingInt(e)))

//        Integer maxValue = studentRepository.findAll().stream()
//                .flatMap(e -> e.getScores().entrySet().stream().forEach(e -> System.out.println(e.getValue())));
        return null;
    }

//    @Override
//    public List<StudentDto> findStudentsByMinScore() {
//        return studentRepository.findAll().stream()
//                .filter(s -> s.getScores().entrySet()
//                        .stream().mapToInt(s -> s.getValue()).sum();
//    }

//    Map<String, Double> modelNameList = returnedRecords.entrySet().stream()
//            .flatMap(e -> e.getValue().stream())
//            .collect(Collectors.groupingBy(t -> cars.get(t.getRegNumber()).getModelName(), Collectors.summingDouble(RentRecord::getCoast)));
//
//    Double max = modelNameList.values().stream()
//            .max(Double::compare).orElse(null);
//
//        return modelNameList.entrySet().stream()
//                .filter(e -> e.getValue().equals(max))
//            .map(Map.Entry::getKey)
//                .collect(Collectors.toList());

//    driverRecords.entrySet().stream().forEach(System.out::println);
//    List<Car> c = driverRecords.entrySet().stream()
//            .flatMap(e -> e.getValue().stream())
//            .filter(e -> e.getLicenceId() == licence)
//            .map(o -> cars.get(o.getRegNumber()))
//            .distinct()
//            .collect(Collectors.toList());
//        if (c.size() == 0) return null;
//        return c;
}
