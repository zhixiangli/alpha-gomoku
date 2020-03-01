package com.zhixiangli.gomoku.dashboard.javafx;

import com.google.common.base.Preconditions;
import com.zhixiangli.gomoku.console.common.PlayerProperties;
import com.zhixiangli.gomoku.core.chessboard.ChessType;
import com.zhixiangli.gomoku.core.service.ChessboardService;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.apache.commons.lang3.StringUtils;

import java.awt.Point;
import java.io.IOException;

/**
 * pane of each cell of chessboard.
 *
 * @author lizhixiang
 */
class DashboardCellPane extends Pane {

    /**
     * pane of this cell.
     */
    @FXML
    private Pane cellPane;

    /**
     * chess of this cell.
     */
    @FXML
    private Circle cellCircle;

    /**
     * chess position
     */
    private final Point position;

    /**
     * Gomoku Service.
     */
    private final ChessboardService chessboardService = ChessboardService.getInstance();

    DashboardCellPane(final Point position) throws IOException {
        this.position = position;

        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cell_pane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    /**
     * initialize.
     */
    @FXML
    public void initialize() {
        // location of this chess piece.
        cellCircle.layoutXProperty().bind(cellPane.widthProperty().divide(2));
        cellCircle.layoutYProperty().bind(cellPane.heightProperty().divide(2));

        // radius of this chess piece.
        cellCircle.radiusProperty().bind(Bindings.min(cellPane.widthProperty(), cellPane.heightProperty()).divide(2.5));

        // the chess type of this cell, and add listener when the chess type
        // is changed.
        chessboardService.addChessboardChangeListener(position,
                (observable, oldValue, newValue) -> updateCellPane(newValue, true));
        updateCellPane(ChessType.EMPTY, true);

        chessboardService.addLastMovePointChangeListener((observable, oldValue, newValue) -> {
            if (position.equals(oldValue)) {
                updateCellPane(chessboardService.getChessboard(oldValue), false);
            }
        });

    }

    private void updateCellPane(final ChessType chessType, final boolean isLatestPosition) {
        if (ChessType.EMPTY == chessType) {
            cellCircle.visibleProperty().set(false);
        } else {
            cellCircle.visibleProperty().set(true);
            if (isLatestPosition) {
                cellCircle.setFill((ChessType.BLACK == chessType) ? Color.BLACK : Color.CORNSILK);
            } else {
                cellCircle.setFill((ChessType.BLACK == chessType) ? Color.DIMGRAY : Color.WHITE);
            }
        }
    }

    /**
     * when this cell is clicked, this method will be called.
     */
    @FXML
    public void takeMove() {
        final ChessType chessType = chessboardService.getCurrentChessType();
        Preconditions.checkState((chessType == ChessType.BLACK) || (chessType == ChessType.WHITE));
        if (chessType == ChessType.BLACK) {
            Preconditions.checkState(StringUtils.isBlank(PlayerProperties.playerBlackCommand));
        } else {
            Preconditions.checkState(StringUtils.isBlank(PlayerProperties.playerWhiteCommand));
        }

        chessboardService.takeMove(position);
    }

}
