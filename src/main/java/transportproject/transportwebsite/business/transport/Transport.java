package transportproject.transportwebsite.business.transport;

import transportproject.transportwebsite.dto.TransportDTO;

public class Transport {
    private final TransportDTO transportDTO;
    public Transport(TransportDTO transportDTO) {
        this.transportDTO = transportDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transport)) return false;

        Transport transport = (Transport) o;

        if (getInnerState() != null ? !getInnerState().equals(transport.getInnerState()) : transport.getInnerState() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getInnerState() != null ? getInnerState().hashCode() : 0;
    }

    public TransportDTO getInnerState() {
        return transportDTO;
    }

    public int getDatabaseIndex() {
        return transportDTO.getId();
    }
}
