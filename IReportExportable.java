package Interfaces;

import ModelSystem.Report;

public interface IReportExportable {
    void exportReport(Report report, String filename);
}
