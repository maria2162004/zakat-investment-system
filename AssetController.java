package Controller;

import java.util.List;

import ModelSystem.Asset;
import ModelSystem.User;

public class AssetController {
    private User currentUser;

    public AssetController(User currentUser) {
        this.currentUser = currentUser;
    }

    public void addAsset(String name, double value, String type) {
        Asset newAsset = new Asset(name, value, type);
        currentUser.addAsset(newAsset);
    }

    public List<Asset> getAssets() {
        return currentUser.getAssets();
    }

    public void editAsset(String name, double newValue, String newType) {
        for (Asset asset : currentUser.getAssets()) {
            if (asset.getName().equalsIgnoreCase(name)) {
                asset.setValue(newValue);
                if (newType != null && !newType.isEmpty()) {
                    asset.setType(newType);
                }
                System.out.println("Asset updated successfully.");
                return;
            }
        }
        System.out.println("Asset not found.");
    }

    public void removeAsset(String name) {
        for (Asset asset : currentUser.getAssets()) {
            if (asset.getName().equalsIgnoreCase(name)) {
                currentUser.removeAsset(asset);
                System.out.println("Asset removed successfully.");
                return;
            }
        }
        System.out.println("Asset not found.");
    }
}