package transportproject.transportwebsite.model;

import javax.persistence.*;

@Entity
@Table(name = "Route_Stop")
public class RouteStop {

    @Id
    @Column(name = "route_stop_id")
    @GeneratedValue
    private Integer routeStopId;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "stop_id")
    private Stop stop;

    @Column(name = "timetable")
    private String timetable;

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    @Override
    public String toString() {
        return "RouteStop{" +
                "route=" + route +
                ", stop=" + stop +
                ", timetable='" + timetable + '\'' +
                '}';
    }
}
