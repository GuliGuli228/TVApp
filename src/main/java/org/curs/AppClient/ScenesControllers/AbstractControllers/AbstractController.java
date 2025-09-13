package org.curs.AppClient.ScenesControllers.AbstractControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.curs.AppClient.AppCache;
import org.curs.AppClient.ScenesControllers.SceneManager;
import org.curs.AppClient.ScenesControllers.Scenes;

import java.util.Objects;
import java.util.logging.Logger;

public class AbstractController {

    Logger logger = Logger.getLogger(this.getClass().getName());

    //(required = false)
    /*---Admin Buttons---*/
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
    protected Button AdminAccountButton;
    /*-------------------*/

    //(required = false)
    /*---Agent Buttons----*/
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
        }
        if(Objects.equals(AppCache.getRole(), "Agent")) {
            AgentContractButton.setOnAction(this::SwitchToAgentContract);
            AgentCustomersButton.setOnAction(this::SwitchToAgentCustomers);

            AgentPlaybackButton.setOnAction(this::SwitchToPlayback);
            AgentPromoButton.setOnAction(this::SwitchToPromo);
            AgentTelecastButton.setOnAction(this::SwitchToTelecast);
            AgentAccountButton.setOnAction(this::SwitchToAccount);
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

}
