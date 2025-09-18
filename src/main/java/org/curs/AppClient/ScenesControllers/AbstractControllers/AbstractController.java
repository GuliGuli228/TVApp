package org.curs.AppClient.ScenesControllers.AbstractControllers;

import jakarta.annotation.Nullable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.curs.AppClient.AppCache;
import org.curs.AppClient.ScenesControllers.SceneManager;
import org.curs.AppClient.Enums.Scenes;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public abstract class AbstractController {

    Logger logger = Logger.getLogger(this.getClass().getName());
    static ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
    static int delay = 10;
    public static void startTimer(){
        service.schedule(() -> {
            AppCache.loadCache();
            SceneManager.switchScene(AppCache.getPreviousScene());
            },
            delay, TimeUnit.MINUTES);
    }


    /*---Admin Buttons---*/
    /*---Not Required, may be null---*/
    @FXML
    protected Button AdminFetchButton;
    @FXML
    protected Button AdminAddButton;
    @FXML
    protected Button AdminUpdateButton;
    @FXML
    protected Button AdminDeleteButton;
    @FXML
    protected Button AdminContractButton;
    @FXML
    protected Button AdminPlaybackButton;
    @FXML
    protected Button AdminCustomersButton;
    @FXML
    protected Button AdminPromosButton;
    @FXML
    protected Button AdminAgentsButton;
    @FXML
    protected Button AdminTelecastButton;
    @FXML
    protected Button AdminAccountButton;
    /*-------------------*/

    /*---Agent Buttons----*/
    /*---Not Required, may be null---*/
    @FXML
    protected  Button AgentAddButton;
    @FXML
    protected Button AgentFetchButton;
    @FXML
    protected Button AgentUpdateButton;
    @FXML
    protected Button AgentDeleteButton;
    @FXML
    protected Button AgentContractButton;
    @FXML
    protected Button AgentPlaybackButton;
    @FXML
    protected Button AgentCustomersButton;
    @FXML
    protected Button AgentPromoButton;
    @FXML
    protected Button AgentTelecastButton;
    @FXML
    protected Button AgentAccountButton;
    /*-------------------*/

    @FXML
    public void initialize() {
        if(Objects.equals(AppCache.getRole(), "Admin")) {
            AdminContractButton.setOnAction(this::SwitchToAdminContract);
            AdminCustomersButton.setOnAction(this::SwitchToAdminCustomers);
            AdminAgentsButton.setOnAction(this::SwitchToAdminAgents);

            AdminPlaybackButton.setOnAction(this::SwitchToPlayback);
            AdminTelecastButton.setOnAction(this::SwitchToTelecast);
            AdminPromosButton.setOnAction(this::SwitchToPromo);
            AdminAccountButton.setOnAction(this::SwitchToAccount);
            AdminFetchButton.setOnAction(this::FetchData);
        }
        if(Objects.equals(AppCache.getRole(), "Agent")) {
            AgentContractButton.setOnAction(this::SwitchToAgentContract);
            AgentCustomersButton.setOnAction(this::SwitchToAgentCustomers);

            AgentPlaybackButton.setOnAction(this::SwitchToPlayback);
            AgentPromoButton.setOnAction(this::SwitchToPromo);
            AgentTelecastButton.setOnAction(this::SwitchToTelecast);
            AgentAccountButton.setOnAction(this::SwitchToAccount);
            AgentFetchButton.setOnAction(this::FetchData);
        }
    }
    /*--- Admin Scenes--*/
    protected   void SwitchToAdminContract(ActionEvent event)  {
        logger.info("switching to Admin Contract");
        SceneManager.switchScene(Scenes.ADMIN_CONTRACTS);
    }
    protected void SwitchToAdminCustomers(ActionEvent event)  {
        logger.info("Switching to Admin Customers");
        SceneManager.switchScene(Scenes.ADMIN_CUSTOMERS);
    }
    protected void SwitchToAdminAgents(ActionEvent event)  {
        logger.info("Switching to Admin Agents");
        SceneManager.switchScene(Scenes.AGENTS);
    }
    /*------------------*/


    /*--- Agent Scenes--*/
    protected   void SwitchToAgentContract(ActionEvent event)  {
        logger.info("switching to Agent ontract");
        SceneManager.switchScene(Scenes.AGENT_CONTRACTS);
    }
    protected void SwitchToAgentCustomers(ActionEvent event)  {
        logger.info("Switching to Agent Customers");
        SceneManager.switchScene(Scenes.AGENT_CUSTOMERS);
    }
    /*------------------*/


    /*---Common Scenes---*/
    protected void SwitchToPromo(ActionEvent event)  {
        logger.info("Switching to Promo");
        SceneManager.switchScene(Scenes.PROMO);
    }
    protected void SwitchToTelecast(ActionEvent event)  {
        logger.info("Switching to Telecast");
        SceneManager.switchScene(Scenes.TELECAST);
    }
    protected void SwitchToPlayback(ActionEvent event)  {
        logger.info("Switching to Agent Playback");
        SceneManager.switchScene(Scenes.PLAYBACK);
    }
    protected void SwitchToAccount(ActionEvent event)  {
        logger.info("Switching to Account");
        SceneManager.switchScene(Scenes.ACCOUNT);
    }
    /*------------------*/

    /*---Common Buttons---*/
    protected void FetchData(ActionEvent event)  {
        logger.info("Fetching Data");
        AppCache.loadCache();
        SceneManager.switchScene(AppCache.getPreviousScene());
    }

}
