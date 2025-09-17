package org.curs.AppClient.ScenesControllers.DialogControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.curs.AppClient.AppCache;
import org.curs.AppClient.ScenesControllers.AbstractControllers.AbstractDialogController;
import org.curs.AppClient.ScenesControllers.ElementConntrollers.AddPlaybackController;
import org.curs.AppClient.ScenesControllers.SceneManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AddContractController extends AbstractDialogController {
    @FXML
    private TextField CustomerIdField;
    @FXML
    private Button AddPlaybackButton;
    @FXML
    private VBox DataField;
    @FXML
    private Button ComputePrice;

    private final AtomicReference<List<AppCache.PromoResponse>> currentCustomerPromos = new AtomicReference<>(new ArrayList<>());;
    @FXML
    public void initialize() {
        List<AppCache.PromoResponse> promos = AppCache.getPromoResponses();
        AddPlaybackButton.setDisable(true);

        AddPlaybackButton.setOnAction(e->AddPlaybackButtonClick(DataField));
        CancelDialogButton.setOnAction(SceneManager::closeDialog);
        CustomerIdField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.isEmpty()){
                currentCustomerPromos.set(promos.stream().
                        filter(promoResponse -> promoResponse.customerId().equals(Integer.valueOf(newValue)))
                        .toList());
                AddPlaybackButton.setDisable(false);
            }
            else {
                currentCustomerPromos.set(null);
                AddPlaybackButton.setDisable(true);
            }
            DataField.getChildren().clear();
        });

        ComputePrice.setOnAction(event -> {
            contractPlaybacks = getChildrenData(DataField);
            PrePrice.setText(String.valueOf(contractPlaybacks.stream().mapToDouble(AddPlaybackController.Playback::price).sum()));
            AddDialogButton.setVisible(true);
        });

        AddDialogButton.setOnAction(event -> {
            if (!contractPlaybacks.isEmpty()){
                try {
                    JsonArray playbacks = new JsonArray();
                    JsonObject contractData = new JsonObject();
                    contractData.addProperty("customerId", CustomerIdField.getText());
                    contractData.addProperty("agentId", AppCache.getAgentId());


                    for(AddPlaybackController.Playback playback : contractPlaybacks){
                        JsonObject temp_playback = new JsonObject();
                        temp_playback.addProperty("promo_id", playback.promoId());
                        temp_playback.addProperty("time", playback.time());
                        temp_playback.addProperty("date", playback.date());
                        temp_playback.addProperty("telecastId", playback.telecastId());
                        playbacks.add(temp_playback);
                    }
                    contractData.add("playbacks", playbacks);
                    ApiUtil.bodyRequest(ApiPaths.POST_CONTRACT, ApiRequests.POST, contractData);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                SceneManager.closeDialog(event);
            }
        });



    }

    private void AddPlaybackButtonClick(VBox vBox) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLScenes/PlaybackElement.fxml"));
            loader.setController(new AddPlaybackController(List.copyOf(currentCustomerPromos.get()), vBox));
            VBox element = loader.load();
            vBox.getChildren().add(element);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
