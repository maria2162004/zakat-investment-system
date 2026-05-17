package View;

import Controller.*;
import ModelSystem.*;
import Service.ReportExportService;
import Service.ZakatCalculationFacade;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);
    private final AuthController authController = new AuthController();
    private final ReportController reportController = new ReportController();
    private final ZakatController zakatController = new ZakatController();
    private AssetController assetController;
    private ZakatCalculationFacade zakatCalculationFacade;

    private void close() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void continuePrompt() {
        System.out.print("Press enter to continue...");
        scanner.nextLine();
    }

    private void header() {
        System.out.println("+-+-+-+-+-+-+-+-+-+");
        System.out.println("+ MG - InvestWise +");
        System.out.println("+-+-+-+-+-+-+-+-+-+");
    }

    public void start() {
        close();
        header();
        System.out.println("Welcome to InvestWise!");
        boolean exitApp = false;

        while (!exitApp) {
            boolean loggedIn = false;

            while (!loggedIn) {
                try {
                    close();
                    header();
                    System.out.print("1. Sign Up\n2. Login\n3. Exit\nChoose an option: ");
                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            close();
                            header();
                            System.out.print("Enter username: ");
                            String username = scanner.nextLine();
                            System.out.print("Enter password: ");
                            String password = scanner.nextLine();

                            if (authController.signUp(username, password)) {
                                System.out.println("Sign up successful. Please log in.");
                            } else {
                                System.out.println("Username already exists.");
                            }
                            continuePrompt();
                            break;

                        case 2:
                            close();
                            header();
                            System.out.print("Enter username: ");
                            username = scanner.nextLine();
                            System.out.print("Enter password: ");
                            password = scanner.nextLine();

                            if (authController.login(username, password)) {
                                loggedIn = true;
                                assetController = new AssetController(authController.getLoggedInUser());
                                zakatCalculationFacade = new ZakatCalculationFacade(authController.getLoggedInUser());
                                System.out.println("Login successful.");
                            } else {
                                System.out.println("Invalid credentials.");
                            }
                            continuePrompt();
                            break;

                        case 3:
                            exitApp = true;
                            loggedIn = true;
                            break;

                        default:
                            System.out.println("Invalid option.");
                            continuePrompt();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    continuePrompt();
                }
            }

            while (loggedIn && !exitApp) {
                try {
                    close();
                    header();
                    System.out.println(
                            "1. Add Asset\n2. Edit Asset\n3. Remove Asset\n4. View Zakat Report\n5. Calculate Zakat\n6. Log Out\n7. Exit");
                    int action = Integer.parseInt(scanner.nextLine());

                    switch (action) {
                        case 1:
                            boolean assetAdded = false;
                            while (!assetAdded) {
                                try {
                                    close();
                                    header();
                                    displayAssets();
                                    System.out.print("Enter asset name: ");
                                    String name = scanner.nextLine();
                                    double value;
                                    while (true) {
                                        System.out.print("Enter asset value:");
                                        value = Double.parseDouble(scanner.nextLine());
                                        if (value > 0)
                                            break;
                                        System.out.println("Error: Value cannot be negative or 0 Try again.");
                                    }
                                    System.out.print("Enter asset type: ");
                                    String type = scanner.nextLine();

                                    List<Asset> assets = assetController.getAssets();
                                    boolean exists = false;
                                    for (Asset asset : assets) {
                                        if (asset.getName().equalsIgnoreCase(name)
                                                && asset.getType().equalsIgnoreCase(type)) {
                                            System.out.println("Asset already exists. Please edit it instead.");
                                            exists = true;
                                            break;
                                        }
                                    }

                                    if (!exists) {
                                        assetController.addAsset(name, value, type);
                                        System.out.println("Asset added successfully ;)");
                                        assetAdded = true;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid value. Please enter a numeric value for the asset.");
                                }

                                if (!assetAdded) {
                                    System.out.print("Do you want to try again? (y/n): ");
                                    String retry = scanner.nextLine().trim().toLowerCase();
                                    if (!retry.equals("y"))
                                        break;
                                }

                                continuePrompt();
                            }
                            break;

                        case 2:
                            close();
                            header();
                            List<Asset> assets = assetController.getAssets();
                            if (assets.isEmpty()) {
                                System.out.println("No assets available to edit.");
                                continuePrompt();
                                break;
                            }
                            displayAssets();
                            System.out.print("Enter asset name to edit: ");
                            String editName = scanner.nextLine();
                            double newValue;
                            while (true) {
                                System.out.print("Enter asset value:");
                                newValue = Double.parseDouble(scanner.nextLine());
                                if (newValue > 0)
                                    break;
                                System.out.println("Error: Value cannot be negative or 0 Try again.");
                            }
                            System.out.print("Enter new type (press Enter to keep the same): ");
                            String newType = scanner.nextLine();
                            assetController.editAsset(editName, newValue, newType);
                            continuePrompt();
                            break;

                        case 3:
                            close();
                            header();
                            assets = assetController.getAssets();
                            if (assets.isEmpty()) {
                                System.out.println("No assets available to remove.");
                                continuePrompt();
                                break;
                            }
                            displayAssets();
                            System.out.print("Enter asset name to remove: ");
                            String removeName = scanner.nextLine();
                            assetController.removeAsset(removeName);
                            continuePrompt();
                            break;

                        case 4:
                            close();
                            header();
                            assets = assetController.getAssets();
                            if (assets.isEmpty()) {
                                System.out.println("No assets available to generate report.");
                                continuePrompt();
                                break;
                            }
                            Report report = reportController.generateReport(assetController.getAssets());
                            System.out.println("Zakat Report:");
                            for (Asset asset : report.getAssets()) {
                                System.out.println(
                                        "- " + asset.getName() + ": " + asset.getValue() + " " + asset.getType());
                            }
                            System.out.println("Total Zakat Due: " + report.getTotalZakat());

                            System.out.print("Do you want to export this report? (y/n): ");
                            String exportChoice = scanner.nextLine().trim().toLowerCase();
                            if (exportChoice.equals("y")) {
                                String filename = "Report" + System.currentTimeMillis() + ".txt";
                                ReportExportService.getInstance().exportReport(report, filename);
                                System.out.println("Report exported successfully as " + filename);
                            }
                            continuePrompt();
                            break;

                        case 5:
                            close();
                            header();
                            System.out.println("Zakat Calculator");
                            try {
                                System.out.print("Enter asset value: ");
                                String input = scanner.nextLine().trim();
                                double assetValue = Double.parseDouble(input);
                                double zakat = zakatController.calculateZakatForValue(assetValue);
                                System.out.printf("Zakat for %.2f is: %.2f%n", assetValue, zakat);
                            } catch (NumberFormatException e) {
                                System.out.println(
                                        "Invalid input. Please enter a numeric value.");
                            }
                            continuePrompt();
                            break;

                        case 6:
                            loggedIn = false;
                            break;

                        case 7:
                            exitApp = true;
                            break;

                        default:
                            System.out.println("Invalid option.");
                            continuePrompt();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    continuePrompt();
                }
            }
        }

        close();
        header();
        System.out.println("Thank you for using InvestWise. Goodbye!");
    }

    private void displayAssets() {
        List<Asset> assets = assetController.getAssets();
        if (assets.isEmpty()) {
            System.out.println("No assets available.");
        } else {
            System.out.println("Current Assets:");
            for (int i = 0; i < assets.size(); i++) {
                Asset asset = assets.get(i);
                System.out.printf("%d. %s - %.2f (%s)%n", i + 1, asset.getName(), asset.getValue(), asset.getType());
            }
        }
    }
}
