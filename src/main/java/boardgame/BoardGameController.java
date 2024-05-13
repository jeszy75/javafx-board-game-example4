package boardgame;

import boardgame.model.BoardGameModel;
import boardgame.model.Square;
import boardgame.util.EnumImageStorage;
import boardgame.util.ImageStorage;
import javafx.beans.binding.ObjectBinding;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class BoardGameController {

    @FXML
    private GridPane board;

    private final BoardGameModel model = new BoardGameModel();

    private final ImageStorage<Square> imageStorage = new EnumImageStorage<>(Square.class);

    @FXML
    private void initialize() {
        for (var i = 0; i < board.getRowCount(); i++) {
            for (var j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare(i, j);
                board.add(square, j, i);
            }
        }
    }

    private StackPane createSquare(int row, int col) {
        var square = new StackPane();
        square.getStyleClass().add("square");
        var imageView = new ImageView();
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.imageProperty().bind(
                new ObjectBinding<Image>() {
                    {
                        super.bind(model.squareProperty(row, col));
                    }
                    @Override
                    protected Image computeValue() {
                        return imageStorage.get(model.squareProperty(row, col).get());
                    }
                }
        );
        square.getChildren().add(imageView);
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        System.out.printf("Click on square (%d,%d)%n", row, col);
        model.makeMove(row, col);
    }

}
