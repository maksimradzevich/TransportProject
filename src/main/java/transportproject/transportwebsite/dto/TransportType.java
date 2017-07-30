package transportproject.transportwebsite.dto;

public enum TransportType {
    BUS("автобус"),
    TROLLEYBUS("троллейбус");

    private String name;

    TransportType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

