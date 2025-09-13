package org.curs.AppClient.ScenesControllers.CommonControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.curs.AppClient.AppCache;
import org.curs.AppClient.JavaFXApp;
import org.curs.AppClient.ScenesControllers.SceneManager;

public class AccountSceneController {
    @FXML
    private Button ExitButton;
    @FXML
    private Button LogoutButton;
    @FXML
    private Button BackButton;
    @FXML
    private Label AccountName;
    @FXML
    private HBox root;

    @FXML
    private Label AccountPaneLogin;
    @FXML
    private Label AccountRole;
    @FXML
    private Label AddInfo1;
    @FXML
    private Label AddInfo2;
    @FXML
    private Label AddInfo3;


    @FXML
    public void initialize() {
        Platform.runLater(() -> root.requestFocus());

        AccountName.setText(AppCache.getUserName()); // Убираем фокус с AccountName
        BackButton.setOnAction(this::SwitchToLastScene);
        LogoutButton.setOnAction(this::Logout);
        ExitButton.setOnAction(this::Exit);

        AccountPaneLogin.setText("Логин: " + AppCache.getUserLogin());
        AccountRole.setText("Права: " + AppCache.getRole());

        if (AppCache.getRole().equals("Agent")) {
            int amountCustomers = AppCache.getCustomersResponses().size();
            int amountContracts = AppCache.getAgentContractResponses().size();
            double totalCapital = AppCache.getAgentContractResponses().stream().mapToDouble(AppCache.AgentContractResponse::price).sum();

            AddInfo1.setText("Кол-во договоров: " + amountContracts);
            AddInfo2.setText("Кол-во заказчиков: " + amountCustomers);
            AddInfo3.setText("Заработанный капитал: " +  totalCapital);

        }
        if (AppCache.getRole().equals("Admin")) {
            int amountAgents = AppCache.getAdminAgentResponses().size();
            int amountCustomers = AppCache.getCustomersResponses().size();
            double totalCapital = AppCache.getAdminContracts().stream().mapToDouble(AppCache.AdminContract::price).sum();

            AddInfo1.setText("Кол-во активных агентов: " + amountAgents);
            AddInfo2.setText("Кол-во заказчиков: " + amountCustomers);
            AddInfo3.setText("Прибыль: " + totalCapital);
        }

    }

    private void SwitchToLastScene(ActionEvent event) {
        SceneManager.switchScene(AppCache.getPreviousScene());
    }
    private void Logout(ActionEvent event) {
        AppCache.clearCache();
        SceneManager.showScene("WelcomeScene", JavaFXApp.getPrimaryStage(), new WelcomeSceneController());
    }
    private void Exit(ActionEvent event) {
        System.exit(0);
    }
}
