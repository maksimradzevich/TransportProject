package transportproject.transportwebsite.dto;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Route_Stop")
@org.hibernate.annotations.Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class RouteStop {

    @Id
    @Column(name = "route_stop_id")
    @GeneratedValue
    private Integer routeStopId;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private RouteDTO routeDTO;

    @ManyToOne
    @JoinColumn(name = "stop_id")
    private StopDTO stopDTO;

    @Column(name = "timetable", length = 4000)
    private String timetable;

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public Integer getRouteStopId() {
        return routeStopId;
    }

    public void setRouteStopId(Integer routeStopId) {
        this.routeStopId = routeStopId;
    }

    public RouteDTO getRouteDTO() {

        return routeDTO;
    }

    public void setRouteDTO(RouteDTO routeDTO) {
        this.routeDTO = routeDTO;
    }

    public StopDTO getStopDTO() {
        return stopDTO;
    }

    public void setStopDTO(StopDTO stopDTO) {
        this.stopDTO = stopDTO;
    }

    @Override
    public String toString() {
        return "RouteStop{" +
                "routeDTO=" + routeDTO +
                ", stopDTO=" + stopDTO +
                ", timetable='" + timetable + '\'' +
                "}\n";
    }
}
