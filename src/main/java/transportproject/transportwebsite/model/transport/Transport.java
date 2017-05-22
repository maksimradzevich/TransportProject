package transportproject.transportwebsite.model.transport;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Transport")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
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
    private List<Route> routes;


    public Transport(TransportType type, Integer routeNumber) {
        this.type = type;
        this.routeNumber = routeNumber;
    }

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

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transport)) return false;

        Transport transport = (Transport) o;

        if (id != null ? !id.equals(transport.id) : transport.id != null) return false;
        if (type != transport.type) return false;
        if (routeNumber != null ? !routeNumber.equals(transport.routeNumber) : transport.routeNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (routeNumber != null ? routeNumber.hashCode() : 0);
        return result;
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
