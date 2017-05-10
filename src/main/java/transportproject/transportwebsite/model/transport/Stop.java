package transportproject.transportwebsite.model.transport;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Stop")
@org.hibernate.annotations.Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class Stop {

    @Id
    @Column(name = "stop_id")
    @GeneratedValue()
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "stop")
    private List<RouteStop> routeStops;

//    @ManyToMany
//    @JoinTable(name = "RouteStop",
//                joinColumns = {
//                    @JoinColumn(name = "stop_id")
//                },
//                inverseJoinColumns = {
//                    @JoinColumn(name = "route_id")
//                })
//    private List<Route> routes;

    public List<RouteStop> getRouteStops() {
        return routeStops;
    }

    public void setRouteStops(List<RouteStop> routeStops) {
        this.routeStops = routeStops;
    }

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
