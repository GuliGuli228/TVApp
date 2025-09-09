package org.curs.AppClient.ScenesControllers.AbstractControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.curs.AppClient.ScenesControllers.AdminControllers.AdminAgentsController;
import org.curs.AppClient.ScenesControllers.SceneManager;
import org.curs.AppClient.ScenesControllers.Scenes;

import java.util.logging.Logger;

public abstract class AdminAbstractController {
    private static final Logger logger = Logger.getLogger(AdminAgentsController.class.getName());

    @FXML
    protected Button AdminAddButton;
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
    protected void initialize() {
        logger.info("Initializing from AbstractController");
        AdminContractButton.setOnAction(this::SwitchToContract);
        AdminPlaybackButton.setOnAction(this::SwitchToPlayback);
        AdminCustomersButton.setOnAction(this::SwitchToCustomers);
        AdminPromosButton.setOnAction(this::SwitchToPromo);
        AdminAgentsButton.setOnAction(this::SwitchToAgents);
        AdminTelecastButton.setOnAction(this::SwitchToTelecast);
    }

    protected   void SwitchToContract(ActionEvent event)  {
        logger.info("switching to contract");
        SceneManager.switchScene(Scenes.ADMIN_CONTRACTS);
    }
    protected void SwitchToPlayback(ActionEvent event)  {
        logger.info("Switching to Playback");
        SceneManager.switchScene(Scenes.ADMIN_PLAYBACK);
    }
    protected void SwitchToCustomers(ActionEvent event)  {
        logger.info("Switching to Customers");
        SceneManager.switchScene(Scenes.ADMIN_CUSTOMERS);
    }
    protected void SwitchToPromo(ActionEvent event)  {
        logger.info("Switching to Promo");
        SceneManager.switchScene(Scenes.PROMO);
    }
    protected void SwitchToAgents(ActionEvent event)  {
        logger.info("Switching to Agents");
        SceneManager.switchScene(Scenes.AGENTS);
    }

    protected void SwitchToTelecast(ActionEvent event)  {
        logger.info("Switching to Telecast");
        SceneManager.switchScene(Scenes.TELECAST);
    }
}
