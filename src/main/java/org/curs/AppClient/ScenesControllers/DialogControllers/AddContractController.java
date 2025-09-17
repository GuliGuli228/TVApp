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
