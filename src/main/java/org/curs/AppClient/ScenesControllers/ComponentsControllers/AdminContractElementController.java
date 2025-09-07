package org.curs.AppClient.ScenesControllers.ComponentsControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminContractElementController {

    @FXML
    private Label contractId;
    @FXML
    private Label agentId;
    @FXML
    private Label customerId;
    @FXML
    private Label price;

    public void setData(String contractId,String agentId,String customerId,String price){
        this.contractId.setText(contractId);
        this.agentId.setText(agentId);
        this.customerId.setText(customerId);
        this.price.setText(price);
    }
}
