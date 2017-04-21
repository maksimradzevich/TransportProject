package transportproject.transportwebsite.model.transport;

public enum TransportType {
    BUS("автобуса"),
    TROLLEYBUS("троллейбуса");

    private String name;

    TransportType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

