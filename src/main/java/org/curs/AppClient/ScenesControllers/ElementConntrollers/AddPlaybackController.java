package org.curs.AppClient.ScenesControllers.ElementConntrollers;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.curs.AppClient.AppCache;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class AddPlaybackController {

    @FXML
    private ComboBox<Integer> PromoIDCombobox;
    @FXML
    private ComboBox<Integer> Hours;
    @FXML
    private ComboBox<Integer> Minutes;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private ComboBox<String> Telecast;
    @FXML
    private TextField Price;
    @FXML
    private Button Delete;
    @FXML
    private VBox root;

    private final VBox parent;
    private List<AppCache.PromoResponse> customerPromos = AppCache.getPromoResponses();
    private final List<AppCache.TelecastResponse> telecasts = AppCache.getTelecastResponses();
    private final Integer[] hours = Arrays.stream(IntStream.rangeClosed(0,24).toArray()).boxed().toArray(Integer[]::new);
    private final Integer[] minutes = Arrays.stream(IntStream.rangeClosed(0,60).toArray()).boxed().toArray(Integer[]::new);
    private final Integer[] PromoIds;
    private final String[] telecastsNames = telecasts.stream().map(AppCache.TelecastResponse::telecastName).toArray(String[]::new);

    public AddPlaybackController(List<AppCache.PromoResponse> customerPromos, VBox parent) {
       PromoIds = customerPromos.stream().map(AppCache.PromoResponse::promoId).toArray(Integer[]::new);
       this.customerPromos = customerPromos;
       this.parent = parent;
    }

    @FXML
    public void initialize() {
        Hours.getItems().addAll(hours);
        Minutes.getItems().addAll(minutes);
        PromoIDCombobox.getItems().addAll(PromoIds);
        Telecast.getItems().addAll(telecastsNames);
        Delete.setOnAction(this::DeletePlayback);

        StringBinding binding = Bindings.createStringBinding(
                () -> {
                    if (PromoIDCombobox.getValue() != null &&
                            Hours.getValue() != null &&
                            Minutes.getValue() != null &&
                            DatePicker.getValue() != null &&
                            Telecast.getValue() != null){
                        int promoId = PromoIDCombobox.getValue();
                        String telecastName = Telecast.getValue();

                        return String.valueOf(telecasts.stream().
                                filter(telecast -> telecast.telecastName().equals(telecastName)).
                                mapToDouble(telecast -> {
                                    double minutes1 = customerPromos.stream().
                                            filter(promo -> promo.promoId().equals(promoId)).
                                            map(AppCache.PromoResponse::duration).
                                            mapToDouble(duration ->
                                                    Duration.parse(duration).toHours() * 60
                                                    + Duration.parse(duration).toMinutes()
                                                    + (double) Duration.parse(duration).toSecondsPart() /60)
                                            .sum();
                                    return  telecast.minuteCost() * minutes1;
                                }).sum());
                    }
                    else return "";
                },
                PromoIDCombobox.valueProperty(),
                Hours.valueProperty(),
                Minutes.valueProperty(),
                DatePicker.valueProperty(),
                Telecast.valueProperty()

        );
        Price.textProperty().bind(binding);
    }

    private void DeletePlayback(ActionEvent actionEvent) {
        parent.getChildren().remove(root);
        parent.requestFocus();
    }
    public Playback getData() {
        Integer promoId = PromoIDCombobox.getValue();
        String time = String.format("%02d:%02d:00", Hours.getValue(), Minutes.getValue());
        String date = DatePicker.getValue().toString();
        Integer telecastId = telecasts.stream()
                .filter(telecastResponse -> telecastResponse.telecastName().equals(Telecast.getValue()))
                .map(AppCache.TelecastResponse::id)
                .findFirst()
                .orElse(null);
        Double price = Double.parseDouble(Price.getText());
        System.out.println(price);

        return new Playback(promoId, time, date, telecastId, price);
    }
}
