/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.scenes;

import application.navigation.NavManager;
import application.scenes.utility.SceneAnnotation;
/**
 *
 * @author dosum
 */
// GameScene.java
import chess.battlefield.ChessBoard;
import chess.pieces.ChessPiece;
import chess.pieces.CoOrdinates;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class represents the main game scene where the chess board and pieces are displayed.
 * @author dosum
 */

@SceneAnnotation(SceneType.GAME)
public class GameScene extends BaseScene {
    private SceneManager sceneManager;
    private ChessBoard chessBoard = ChessBoard.getInstance();
    private GridPane chessGrid;
    
    public GameScene(SceneManager sceneManager, NavManager navManager) {
        super(sceneManager, navManager);
		this.sceneManager = sceneManager;
    }
    
    private void initializeChessBoard() {
        chessGrid.getChildren().clear();
        
        // Create 8x8 chess board
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                StackPane square = createSquare(row, col);
                chessGrid.add(square, col, 7 - row); // Flip for chess coordinates
            }
        }
        
        // TODO: Add pieces from your ChessBoard instance
        updateBoardPieces();
    }
    
    private StackPane createSquare(int row, int col) {
        // Create square background
        Rectangle square = new Rectangle(60, 60);
        square.setFill((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);
        
        StackPane stackPane = new StackPane(square);
        stackPane.setOnMouseClicked(e -> handleSquareClick(row, col));
        
        return stackPane;
    }
    
    private void handleSquareClick(int row, int col) {
        // Convert to chess coordinates (A-H, 1-8)
        char file = (char) ('A' + col);
        int rank = 8 - row;
        System.out.println("Clicked: " + file + rank);
        
        // TODO: Implement piece selection and movement
    }
    
    private void updateBoardPieces() {
        // TODO: Implement piece rendering from your ChessBoard
        // This would iterate through board.getBoard() and place pieces
    }
    
    @Override
    protected void applyStyles() {
        scene.getStylesheets().add(getClass().getResource("/resources/css/scenes/game.css").toExternalForm());
    }
    
    public Scene getScene() {
        return scene;
    }
    
    public void updateGameState() {
        updateBoardPieces();
    }

	@Override
	protected void createContent() {
		// Top panel with game info
        Label turnLabel = new Label("White's Turn");
        turnLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        Button menuButton = new Button("Main Menu");
        menuButton.setOnAction(e -> sceneManager.showScene(SceneType.MAIN_MENU));
        
        HBox topPanel = new HBox(20);
        topPanel.setAlignment(Pos.CENTER_LEFT);
        topPanel.setPadding(new Insets(10));
        topPanel.getChildren().addAll(turnLabel, menuButton);
        
        // Chess board
        chessGrid = new GridPane();
        chessGrid.setAlignment(Pos.CENTER);
        initializeChessBoard();
        
        // Main layout
        //BorderPane root = new BorderPane();
        root.setTop(topPanel);
        root.setCenter(chessGrid);
        root.setPadding(new Insets(10));
        
        //scene = new Scene(root, 800, 600);
        //applyStyles();
		
	}
}
