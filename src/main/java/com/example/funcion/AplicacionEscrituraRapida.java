/**
 * Main class that launches the fast typing application.
 * Configures the JavaFX environment and displays the main game window.
 *
 * @author Luis Santiago Arenas Hincapie
 * @version 1.0
 */
package com.example.funcion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class for the Fast Typing Game.
 * This class launches the JavaFX application and loads the main window.
 */
public class AplicacionEscrituraRapida extends Application {

    /**
     * Primary stage reference for scene management
     */
    private static Stage escenarioPrincipal;

    @Override
    public void start(Stage escenario) throws Exception {
        escenarioPrincipal = escenario;

        // Load the main window FXML file
        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/com/example/funcion/principal.fxml"));
        Parent raiz = cargador.load();

        // Configure the main scene
        Scene escena = new Scene(raiz, 600, 400);

        // Set up the primary stage
        escenarioPrincipal.setTitle("Escritura Rápida - Fast Typing Game");
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.setResizable(false);
        escenarioPrincipal.centerOnScreen();
        escenarioPrincipal.show();
    }

    /**
     * Returns the primary stage reference for scene switching
     * @return the primary stage
     */
    public static Stage obtenerEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    /**
     * Utility method to switch between scenes
     * @param rutaFxml the path to the FXML file
     * @throws Exception if the FXML file cannot be loaded
     */
    public static void cambiarEscena(String rutaFxml) throws Exception {
        FXMLLoader cargador = new FXMLLoader(AplicacionEscrituraRapida.class.getResource(rutaFxml));
        Parent raiz = cargador.load();
        Scene escena = new Scene(raiz, 600, 400);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
