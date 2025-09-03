package application.PieceGUI;

import chess.pieces.ChessPiece;
import chess.pieces.CoOrdinates;
import chess.pieces.enhanced.*;
import chess.pieces.PieceFactory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;
/*
 * This class is responsible for rendering chess pieces in the GUI.
 * @author dosum
 */

public class PieceRenderer {
	private static final Map<String, Image> pieceImageMap = new HashMap<>();
	private static final int PIECE_SIZE = 60; // Size of each piece image
	
	// Map piece types to their image file names
	private static final Map<String, String> pieceTypeToImageName = new HashMap<>();

	static {
		initializeImageMappings();
		// Load images for all pieces
		loadPieceImage();
	}

	private static void loadPieceImage() {
		// TODO Auto-generated method stub
		String[] colors = { "White", "Black" };
		
		//Load images for all pieces
		for (String pieceName : PieceFactory.getAllPieceNames()) {
			for (String color : colors) {
				loadImage(color, pieceName);
			}
		}
		
	}

	private static void loadImage(String color, String pieceName) {
		// TODO Auto-generated method stub
		String imageName = pieceTypeToImageName.getOrDefault(pieceName, pieceName.toLowerCase());
		
		String key = color +  "_" + pieceName;
		String imagePath = "/images/" + imageName.replace("white", color.toLowerCase()).replace("black", color.toLowerCase());
		
		try {
			Image image = new Image(PieceRenderer.class.getResourceAsStream(imagePath));
			pieceImageMap.put(key, image);
		} catch (Exception e) {
			System.err.println("Error loading image: " + imagePath);
			// Handle missing image scenario
		}
	}

	private static void initializeImageMappings() {
		// TODO Auto-generated method stub
		PieceFactory.getAllPieceNames();
		for(String pieceName : PieceFactory.getAllPieceNames()) {
			if (pieceName.contains("Super")) {
	            	Pattern pattern = Pattern.compile("Super([A-Z])");
	            	Matcher matcher = pattern.matcher(pieceName);
	            if (matcher.find()) {
	                	pieceName = matcher.replaceAll("super_" + matcher.group(1).toLowerCase());
	            }
			}
			pieceTypeToImageName.put(pieceName, pieceName.toLowerCase() + "_white.png");
			pieceTypeToImageName.put(pieceName, pieceName.toLowerCase() + "_black.png");
		}
	}
	
	public static ImageView createPieceView(ChessPiece piece) {
		if (piece == null) {
			return null;
		}
		
		String color = piece.getTeamColour() ? "White" : "Black";
		String pieceType = piece.getName();
		// Handle special naming for enhanced pieces
		String key = color + "_" + piece.getName();
		
		Image image = pieceImageMap.get(key);
		ImageView imageView;
		
		if (image == null) {
			System.err.println("No image found for key: " + key);
			return null; // Handle missing image scenario
		}else {
			
			//Fallback: Create a piece using the factory to ensure we have the right type
			ChessPiece fallbackPiece = PieceFactory.create(pieceType, piece.getCordnts(), piece.getTeamColour());
			imageView = createTextPieceView(fallbackPiece);
		}
		// Set image and size
		imageView.setFitWidth(PIECE_SIZE);
		imageView.setFitHeight(PIECE_SIZE);
		imageView.setUserData(piece); // Store the piece in user data for easy access
		
		//applyPieceStyle(imageView, piece);
		return imageView;
	}
	
	private static ChessPiece createFallbackPiece(String pieceType, CoOrdinates pos, boolean isWhite) {
		// TODO Auto-generated method stub
		
		try {
			//Use the factory to create a temp piece for fall back rendering
			return PieceFactory.create(pieceType, new CoOrdinates(1,1), isWhite);
		}catch (Exception e) {
			//Ultimate fallback: Create a pawn if all else fails
			return new chess.pieces.Pawn(new CoOrdinates(1,1), isWhite);
		}
	}
	
	private static ImageView createTextPieceView(ChessPiece piece) {
		// Create a label to represent the piece
		Label label = new Label(piece.getName().substring(0, 1)); // Use first letter of piece name
		ImageView imageView = new ImageView();
		imageView.setUserData(piece); // Store the piece in user data for easy access
		// Could convert the label to an image if needed
		return imageView;
	}
	
	private void applyPieceStyle(ImageView imageView, ChessPiece piece) {
		// Apply styles based on piece type or team color if needed
		imageView.getStyleClass().add("chess-piece");
		imageView.getStyleClass().add(piece.getTeamColour() ? "white-piece" : "black-piece");
		
		if(piece.special()) {
			imageView.getStyleClass().add("special-piece");
			addEnhancedPieceEffect(imageView, piece);
		}
	}
	
	private static void addEnhancedPieceEffect(ImageView imageView, ChessPiece piece) {
		
		String pieceType = piece.getName();
		
		imageView.getStyleClass().add("enhanced-piece");
	}
	
	//Fallback to Unicode with enhanced pieces
	public static Label createTextPiece(ChessPiece piece) {
		if (piece == null) return null;
		
		String symbol = getUnicodeSymbol(piece);
		Label label = new Label(symbol);
		
		if(piece.special()) {
			
			label.getStyleClass().add("enhanced-text-piece");
			label.setTextFill(piece.getTeamColour() ? Color.GOLD : Color.DARKRED);
		}else {
			
			label.getStyleClass().add("chess-piece");
			label.getStyleClass().add("text-piece");
		}
		label.getStyleClass().add(piece.getTeamColour() ? "white-piece" : "black-piece");
		label.setUserData(piece); // Store the piece in user data for easy access
		return label;
	}
	
	private static String getUnicodeSymbol(ChessPiece piece) {
		boolean isWhite = piece.getTeamColour();
        String className = piece.getClass().getSimpleName();
        
        // Remove "Super" prefix for basic symbols
        if (className.startsWith("Super")) {
            className = className.substring(5);
        }
        
        switch (className) {
            case "King": return isWhite ? "♔" : "♚";
            case "Queen": return isWhite ? "♕" : "♛";
            case "Rook": return isWhite ? "♖" : "♜";
            case "Bishop": return isWhite ? "♗" : "♝";
            case "Knight": return isWhite ? "♘" : "♞";
            case "Pawn": return isWhite ? "♙" : "♟";
            case "Bomber": return isWhite ? "💣" : "💣";
            case "Spearman": return isWhite ? "⚔️" : "⚔️";
            default: return "?";
        }
	}
	
	
	
}
