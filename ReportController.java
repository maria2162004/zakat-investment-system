package Controller;

import java.util.List;

import ModelSystem.Asset;
import ModelSystem.Report;
import ModelSystem.ZakatCalculator;

public class ReportController {
    private ZakatCalculator zakatCalculator = new ZakatCalculator();

    public Report generateReport(List<Asset> assets) {
        double totalZakat = zakatCalculator.calculate(assets);
        return new Report(assets, totalZakat);
    }
}