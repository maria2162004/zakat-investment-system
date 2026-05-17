package Service;

import ModelSystem.Report;
import java.io.FileWriter;
import java.io.IOException;

public class ReportExportService {
    private static ReportExportService instance;

    private ReportExportService() {}

    public static ReportExportService getInstance() {
        if (instance == null) {
            instance = new ReportExportService();
        }
        return instance;
    }

    public void exportReport(Report report, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Zakat Report:\n");
            report.getAssets().forEach(asset -> {
                try {
                    writer.write(asset.getName() + ": " + asset.getValue() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.write("Total Zakat: " + report.getTotalZakat() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
