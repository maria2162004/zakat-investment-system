package Service;

import Controller.ZakatController;
import ModelSystem.User;

public class ZakatCalculationFacade {
    private final ZakatController zakatController;
    private final User user;

    public ZakatCalculationFacade(User user) {
        this.user = user;
        this.zakatController = new ZakatController();
    }

    public double calculateZakat() {
        return zakatController.calculateZakat(user.getAssets());
    }
}