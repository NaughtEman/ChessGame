module ChessGameGUI {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.desktop;
	requires org.reflections;
	
	opens application to javafx.graphics, javafx.fxml;
}
