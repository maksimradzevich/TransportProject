package transportproject.transportwebsite.model;

import javax.persistence.*;

@Entity
@Table(name = "Stop")
public class Stop {

    public Stop() {
    }

    @Id
    @Column(name = "stop_id")
    @GeneratedValue()
    private Integer id;

    @Column(name = "name")
    private String name;

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

    @Override
    public String toString() {
        return "Stop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Stop(String name) {
        this.name = name;
    }
}
