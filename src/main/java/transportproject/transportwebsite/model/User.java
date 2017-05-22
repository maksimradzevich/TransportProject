package transportproject.transportwebsite.model;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import transportproject.transportwebsite.model.transport.Stop;
import transportproject.transportwebsite.model.transport.Transport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    private String role;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_stop",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "stop_id")})
    private List<Stop> favoriteStops = new ArrayList<Stop>();
    @ManyToMany(cascade = CascadeType.ALL)

    @JoinTable(name = "user_transport",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "transport_id")})
    private List<Transport> favoriteTransport = new ArrayList<Transport>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public List<Transport> getFavoriteTransport() {
        return favoriteTransport;
    }

    public void setFavoriteTransport(List<Transport> favoriteTransport) {
        this.favoriteTransport = favoriteTransport;
    }

    public List<Stop> getFavoriteStops() {
        return favoriteStops;
    }

    public void setFavoriteStops(List<Stop> favoriteStops) {
        this.favoriteStops = favoriteStops;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getId().equals(user.getId())) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        if (getRole() != null ? !getRole().equals(user.getRole()) : user.getRole() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        return result;
    }
}
