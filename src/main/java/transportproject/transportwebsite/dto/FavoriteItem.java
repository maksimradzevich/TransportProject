package transportproject.transportwebsite.dto;

public class FavoriteItem {
    private String type;
    private Integer id;

    public boolean isTypeEquals(FavoriteItemTypes type) {
        return type == FavoriteItemTypes.valueOf(this.type.toUpperCase());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
