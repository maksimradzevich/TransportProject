package transportproject.transportwebsite.dto;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "RouteDTO")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class RouteDTO {

    @Id
    @Column(name = "route_id")
    @GeneratedValue
    private Integer routeId;

    @ManyToOne
    @JoinColumn(name = "transport_id")
    private TransportDTO transportDTO;

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
//    private List<StopDTO> stops;

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public TransportDTO getTransportDTO() {
        return transportDTO;
    }

    public void setTransportDTO(TransportDTO transportDTO) {
        this.transportDTO = transportDTO;
    }

//    public List<StopDTO> get() {
//        return stops;
//    }
//
//    public void setStops(List<StopDTO> stops) {
//        this.stops = stops;
//    }

    public RouteDTO(TransportDTO transportDTO) {
        this.transportDTO = transportDTO;
    }

    public List<RouteStop> getRouteStops() {
        return routeStops;
    }

    public void setRouteStops(List<RouteStop> routeStops) {
        this.routeStops = routeStops;
    }

    public RouteDTO() {
    }

    @Override
    public String toString() {
        return "RouteDTO{" +
                "routeId=" + routeId +
                ", transportDTO=" + transportDTO +
                '}';
    }
}
