package telran.b7a.student.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import telran.b7a.student.model.Student;

import java.util.List;
import java.util.stream.Stream;

public interface StudentsMongoRepository extends MongoRepository<Student, Integer> {
    Stream<Student> findBy();
    Stream<Student> findByNameIgnoreCase(String name);
    long countByNameInIgnoreCase(List<String> names);

//    @Query(value = "{'scores.History': {$gte: 90}}") // по дефолту find
@Query(value = "{'scores.?0': {$gte: ?1}}") // 0 и 1 -> аргументы (exam, score)
Stream<Student> findByExamAndScoreGreaterEqualsThan(String exam, int score);
}
