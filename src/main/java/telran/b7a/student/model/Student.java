package telran.b7a.student.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(of = "id")
@Getter
@NoArgsConstructor
@ToString
//@Document(collation = "std") // название коллекции || иначе по названию сущности
public class Student {
    @Id // установка первичного ключа
    int id;
    @Setter
    String name;
    @Setter
    String password;
    Map<String, Integer> scores = new HashMap<>();

    public Student(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
//        scores = new HashMap<>();
    }

    public boolean addScore(String exam, int score) {
       return scores.put(exam, score) == null;
    }
}
