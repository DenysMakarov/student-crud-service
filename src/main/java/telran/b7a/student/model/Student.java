package telran.b7a.student.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(of = "id")
@Getter
@NoArgsConstructor
public class Student {
    int id;
    @Setter
    String name;
    @Setter
    String password;
    Map<String, Integer> scores;

    public Student(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        scores = new HashMap<>();
    }

    public boolean addScore(String exam, int score) {
       return scores.put(exam, score) == null;
    }
}