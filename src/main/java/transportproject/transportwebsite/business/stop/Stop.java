package transportproject.transportwebsite.business.stop;

import transportproject.transportwebsite.dto.StopDTO;

public class Stop {

    private final StopDTO stopDTO;

    public Stop(StopDTO stopDTO) {
        this.stopDTO = stopDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stop)) return false;

        Stop stop = (Stop) o;

        if (stopDTO != null ? !stopDTO.equals(stop.getInnerState()) : stop.getInnerState() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return stopDTO != null ? stopDTO.hashCode() : 0;
    }

    public StopDTO getInnerState() {
        return stopDTO;
    }

    public int getDatabaseIndex() {
        return stopDTO.getId();
    }
}
