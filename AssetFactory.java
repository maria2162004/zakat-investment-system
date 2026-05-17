package Factory;

import ModelSystem.Asset;

public class AssetFactory {
    public static Asset createAsset(String name, double value, String type) {
        return new Asset(name, value, type);
    }
}