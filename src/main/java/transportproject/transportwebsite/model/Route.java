package transportproject.transportwebsite.model;

import transportproject.transportwebsite.model.transport.Transport;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Route")
public class Route {

    @Id
    @Column(name = "route_id")
    @GeneratedValue
    private Integer routeId;

    @ManyToOne
    @JoinColumn(name = "transport_id")
    private Transport transport;

    @ManyToMany(mappedBy = "routes")
    private List<Stop> stops;

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

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public Route(Transport transport) {
        this.transport = transport;
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
