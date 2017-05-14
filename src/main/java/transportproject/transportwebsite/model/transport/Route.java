package transportproject.transportwebsite.model.transport;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Route")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Route {

    @Id
    @Column(name = "route_id")
    @GeneratedValue
    private Integer routeId;

    @ManyToOne
    @JoinColumn(name = "transport_id")
    private Transport transport;

    @OneToMany(mappedBy = "route")
    private List<RouteStop> routeStops;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "name")
    private String name;

//    @ManyToMany(mappedBy = "routes")
//    private List<Stop> stops;

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

//    public List<Stop> getStops() {
//        return stops;
//    }
//
//    public void setStops(List<Stop> stops) {
//        this.stops = stops;
//    }

    public Route(Transport transport) {
        this.transport = transport;
    }

    public List<RouteStop> getRouteStops() {
        return routeStops;
    }

    public void setRouteStops(List<RouteStop> routeStops) {
        this.routeStops = routeStops;
    }

    public Route() {
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", transport=" + transport +
                '}';
    }
}
