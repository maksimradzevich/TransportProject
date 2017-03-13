package transportproject.transportwebsite.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Stop")
public class Stop {

    @Id
    @Column(name = "stop_id")
    @GeneratedValue()
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "Route_Stop",
                joinColumns = {
                    @JoinColumn(name = "stop_id")
                },
                inverseJoinColumns = {
                    @JoinColumn(name = "route_id")
                })
    private List<Route> routes;

    public Stop() {
    }

    public Stop(String name) {
        this.name = name;
    }

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
}
