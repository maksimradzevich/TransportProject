package transportproject.transportwebsite.dto;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UserDTO")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class UserDTO {

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
    private List<StopDTO> favoriteStopDTOS = new ArrayList<StopDTO>();
    @ManyToMany(cascade = CascadeType.ALL)

    @JoinTable(name = "user_transport",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "transport_id")})
    private List<TransportDTO> favoriteTransportDTO = new ArrayList<TransportDTO>();

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDTO() {
    }

    public List<TransportDTO> getFavoriteTransportDTO() {
        return favoriteTransportDTO;
    }

    public void setFavoriteTransportDTO(List<TransportDTO> favoriteTransportDTO) {
        this.favoriteTransportDTO = favoriteTransportDTO;
    }

    public List<StopDTO> getFavoriteStopDTOS() {
        return favoriteStopDTOS;
    }

    public void setFavoriteStopDTOS(List<StopDTO> favoriteStopDTOS) {
        this.favoriteStopDTOS = favoriteStopDTOS;
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
        if (!(o instanceof UserDTO)) return false;

        UserDTO userDTO = (UserDTO) o;

        if (!getId().equals(userDTO.getId())) return false;
        if (!getEmail().equals(userDTO.getEmail())) return false;
        if (!getPassword().equals(userDTO.getPassword())) return false;
        if (getRole() != null ? !getRole().equals(userDTO.getRole()) : userDTO.getRole() != null) return false;

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
