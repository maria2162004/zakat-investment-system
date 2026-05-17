package Interfaces;
import ModelSystem.Asset;
import java.util.List;

public interface IAssetService {
    void addAsset(String name, double value, String type);
    List<Asset> getAssets();
}

