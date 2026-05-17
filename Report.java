package ModelSystem;

import java.util.List;

public class Report {
    private List<Asset> assets;
    private double totalZakat;

    public Report(List<Asset> assets, double totalZakat) {
        this.assets = assets;
        this.totalZakat = totalZakat;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public double getTotalZakat() {
        return totalZakat;
    }
}