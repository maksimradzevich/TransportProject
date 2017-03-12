package transportproject.transportwebsite.model.transport;

import transportproject.transportwebsite.model.RouteTransport;
import transportproject.transportwebsite.model.transport.TransportType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Transport")
public class Transport {

    @Id
    @Column(name = "transport_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransportType type;
    @Column(name = "route_number")
    private Integer routeNumber;
    @OneToMany(mappedBy = "transport", cascade = CascadeType.ALL)
    private List<RouteTransport> routesForTransport;

    public Transport() {
    }

    public Integer getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(Integer routeNumber) {
        this.routeNumber = routeNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TransportType getType() {
        return type;
    }

    public void setType(TransportType type) {
        this.type = type;
    }

    public List<RouteTransport> getRoutesForTransport() {
        return routesForTransport;
    }

    public void setRoutesForTransport(List<RouteTransport> routesForTransport) {
        this.routesForTransport = routesForTransport;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", type=" + type +
                ", routeNumber=" + routeNumber +
                '}';
    }
}
