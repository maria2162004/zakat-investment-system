package Controller;

import java.util.List;

import ModelSystem.Asset;
import ModelSystem.ZakatCalculator;

public class ZakatController {
    private ZakatCalculator calculator = new ZakatCalculator();

    public double calculateZakat(List<Asset> assets) {
        return calculator.calculate(assets);
    }

    public double calculateZakatForValue(double assetValue) {
        // Assuming zakat is 2.5% of the asset value
        return assetValue * 0.025;
    }
}