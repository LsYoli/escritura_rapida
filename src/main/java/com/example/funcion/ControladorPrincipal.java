/**
 * Controller class for the main window of the application.
 * Manages navigation between different views (game, instructions, etc.)
 * and centralizes the interaction logic of the graphical interface.
 *
 * @author Luis Santiago Arenas Hincapié
 * @version 1.0
 */
package com.example.funcion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

/**
 * Controller for the main window (principal.fxml).
 * Handles navigation to game and instructions windows.
 */
public class ControladorPrincipal {

    @FXML
    private Button botonJugar;

    @FXML
    private Button botonAyuda;

    /**
     * Initialize the controller after loading FXML
     */
    @FXML
    private void initialize() {
        // Additional initialization if needed
    }

    /**
     * Handles the play button click event.
     * Switches to the game window.
     * @param evento the action event
     */
    @FXML
    private void manejarBotonJugar(ActionEvent evento) {
        try {
            AplicacionEscrituraRapida.cambiarEscena("/com/example/funcion/juego.fxml");
        } catch (Exception e) {
            System.err.println("Error loading game window: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handles the help button (?) click event.
     * Switches to the instructions window.
     * @param evento the action event
     */
    @FXML
    private void manejarBotonAyuda(ActionEvent evento) {
        try {
            AplicacionEscrituraRapida.cambiarEscena("/com/example/funcion/instrucciones.fxml");
        } catch (Exception e) {
            System.err.println("Error loading instructions window: " + e.getMessage());
            e.printStackTrace();
        }
    }
}