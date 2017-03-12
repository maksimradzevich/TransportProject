package transportproject.transportwebsite.model;

import transportproject.transportwebsite.model.transport.Transport;

import javax.persistence.*;

@Entity
@Table(name = "Route_Transport")
public class RouteTransport {

    @Id
    @Column(name = "id")
    @GeneratedValue()
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "transport_id")
    private Transport transport;
}
