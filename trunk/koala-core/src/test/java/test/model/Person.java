package test.model;

import javax.persistence.*;

@Entity
public class Person {
    @Id
    @SequenceGenerator(name = "person_seq", sequenceName = "TEST_PERSON_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
