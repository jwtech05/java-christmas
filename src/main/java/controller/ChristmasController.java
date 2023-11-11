package controller;

import view.InputView;
import view.OutputView;

public class ChristmasController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    public void run(){
        pickVisitDate();
        pickMenu();

    }

    private void pickMenu() {
        outputView.printHello();
        inputView.readDate();
    }

    private void pickVisitDate() {
    }
}
