package transportproject.transportwebsite.dto;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "StopDTO")
@org.hibernate.annotations.Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class StopDTO {

    @Id
    @Column(name = "stop_id")
    @GeneratedValue()
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "stop")
    private List<RouteStop> routeStops;

    public List<RouteStop> getRouteStops() {
        return routeStops;
    }

    public void setRouteStops(List<RouteStop> routeStops) {
        this.routeStops = routeStops;
    }

    public StopDTO() {

    }

    public StopDTO(String name) {
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
        return "StopDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StopDTO)) return false;

        StopDTO stopDTO = (StopDTO) o;

        if (id != null ? !id.equals(stopDTO.id) : stopDTO.id != null) return false;
        if (name != null ? !name.equals(stopDTO.name) : stopDTO.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
