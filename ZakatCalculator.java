package ModelSystem;

import java.util.List;

public class ZakatCalculator {
    private static final double ZAKAT_PERCENTAGE = 0.025;

    public double calculate(List<Asset> assets) {
        double total = 0;
        for (Asset asset : assets) {
            total += asset.getValue();
        }
        return total * ZAKAT_PERCENTAGE;
    }
}