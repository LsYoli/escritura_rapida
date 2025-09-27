/**
 * Controller class for the instructions window of the game.
 * Displays the rules, controls, and guidelines for the player.
 *
 * @author Luis Santiago Arenas Hincapié
 * @version 1.0
 */
package com.example.funcion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

/**
 * Controller for the instructions window (instrucciones.fxml).
 * Handles navigation back to the main window.
 */
public class ControladorInstrucciones {

    @FXML
    private Button botonRegresar;

    /**
     * Initialize the controller after loading FXML
     */
    @FXML
    private void initialize() {
        // Additional initialization if needed
    }

    /**
     * Handles the back button (←) click event.
     * Returns to the main window.
     * @param evento the action event
     */
    @FXML
    private void manejarBotonRegresar(ActionEvent evento) {
        try {
            AplicacionEscrituraRapida.cambiarEscena("/com/example/funcion/principal.fxml");
        } catch (Exception e) {
            System.err.println("Error returning to main window: " + e.getMessage());
            e.printStackTrace();
        }
    }
}