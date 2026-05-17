package ModelSystem;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Asset> assets;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.assets = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void addAsset(Asset asset) {
        this.assets.add(asset);
    }

    public void removeAsset(Asset asset) {
        this.assets.remove(asset);
    }
}