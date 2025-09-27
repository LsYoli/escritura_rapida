/**
 * Controller class that manages the main game logic.
 * Handles levels, time per level, answer validation, and error tracking.
 *
 * @author Luis Santiago Arenas Hincapié
 * @version 1.0
 */
package com.example.funcion;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/**
 * Controller for the main game window (juego.fxml).
 * Handles all game logic including timing, word validation, and level progression.
 */
public class ControladorJuego {

    @FXML
    private Label etiquetaPalabra;

    @FXML
    private Label etiquetaTemporizador;

    @FXML
    private Label etiquetaNivel;

    @FXML
    private TextArea areaTexto;

    @FXML
    private Button botonVerificar;

    @FXML
    private Label etiquetaErrores;

    @FXML
    private Label etiquetaProgreso;

    @FXML
    private Button botonReintentar;

    @FXML
    private Button botonMenu;

    // Game state variables
    private int nivelActual;
    private int tiempoRestante;
    private int tiempoBase;
    private String palabraActual;
    private Timeline cronometroJuego;
    private boolean juegoActivo;
    private int totalErrores;

    /**
     * Initialize the controller after loading FXML
     */
    @FXML
    private void initialize() {
        inicializarJuego();
        configurarManejadorTeclado();
    }

    /**
     * Sets up the game for a new session
     */
    private void inicializarJuego() {
        nivelActual = 1;
        tiempoBase = 20; // Starting time in seconds
        totalErrores = 0;
        juegoActivo = true;

        iniciarNuevoNivel();
    }

    /**
     * Sets up keyboard event handler for the input text area
     */
    private void configurarManejadorTeclado() {
        if (areaTexto != null) {
            areaTexto.setOnKeyPressed(this::manejarTeclaPresionada);
            areaTexto.requestFocus();
        }
    }

    /**
     * Handles key press events in the input text area
     * @param evento the key event
     */
    private void manejarTeclaPresionada(KeyEvent evento) {
        if (evento.getCode() == KeyCode.ENTER && juegoActivo) {
            evento.consume(); // Prevent new line
            manejarBotonVerificar(null);
        }
    }

    /**
     * Starts a new level with a fresh word and timer
     */
    private void iniciarNuevoNivel() {
        if (!juegoActivo) return;

        // Generate new word
        palabraActual = GeneradorPalabras.generarPalabra(nivelActual);
        etiquetaPalabra.setText(palabraActual);

        // Calculate time for this level (decreases every 5 levels)
        int reduccionTiempo = (nivelActual - 1) / 5 * 2;
        tiempoRestante = Math.max(2, tiempoBase - reduccionTiempo);

        // Update UI
        actualizarMostrarTemporizador();
        actualizarMostrarNivel();
        actualizarMostrarProgreso();

        // Clear input and errors
        if (areaTexto != null) {
            areaTexto.clear();
            areaTexto.requestFocus();
        }
        etiquetaErrores.setText("");

        // Start timer
        iniciarCronometro();
    }

    /**
     * Updates the timer display
     */
    private void actualizarMostrarTemporizador() {
        if (etiquetaTemporizador != null) {
            etiquetaTemporizador.setText("Tiempo: " + tiempoRestante + "s");
        }
    }

    /**
     * Updates the level display
     */
    private void actualizarMostrarNivel() {
        if (etiquetaNivel != null) {
            etiquetaNivel.setText("Nivel: " + nivelActual);
        }
    }

    /**
     * Updates the progress display
     */
    private void actualizarMostrarProgreso() {
        String progreso = String.format("Nivel actual: %d\nTiempo por nivel: %d segundos\nErrores totales: %d",
                nivelActual, tiempoRestante, totalErrores);
        etiquetaProgreso.setText(progreso);
    }

    /**
     * Starts the countdown timer for the current level
     */
    private void iniciarCronometro() {
        if (cronometroJuego != null) {
            cronometroJuego.stop();
        }

        cronometroJuego = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            tiempoRestante--;
            actualizarMostrarTemporizador();

            if (tiempoRestante <= 0) {
                tiempoAgotado();
            }
        }));

        cronometroJuego.setCycleCount(tiempoRestante);
        cronometroJuego.play();
    }

    /**
     * Handles when time runs out
     */
    private void tiempoAgotado() {
        juegoActivo = false;
        if (cronometroJuego != null) {
            cronometroJuego.stop();
        }

        String entradaUsuario = areaTexto != null ? areaTexto.getText().trim() : "";
        if (!entradaUsuario.equals(palabraActual)) {
            totalErrores++;
            mostrarError("¡Tiempo agotado! La palabra correcta era: " + palabraActual);
        }

        terminarJuego("¡Tiempo agotado!");
    }

    /**
     * Handles the verify button click event
     * @param evento the action event
     */
    @FXML
    private void manejarBotonVerificar(ActionEvent evento) {
        if (!juegoActivo) return;

        String entradaUsuario = areaTexto != null ? areaTexto.getText().trim() : "";

        if (entradaUsuario.equals(palabraActual)) {
            // Correct answer
            mostrarExito("¡Correcto! Avanzando al siguiente nivel...");
            nivelActual++;

            // Small delay before next level
            Timeline retraso = new Timeline(new KeyFrame(Duration.seconds(1), e -> iniciarNuevoNivel()));
            retraso.play();

        } else {
            // Wrong answer
            totalErrores++;
            mostrarError("Incorrecto. La palabra era: " + palabraActual + "\nTú escribiste: " + entradaUsuario);
            terminarJuego("¡Palabra incorrecta!");
        }
    }

    /**
     * Shows a success message
     * @param mensaje the success message
     */
    private void mostrarExito(String mensaje) {
        etiquetaErrores.setText("✓ " + mensaje);
        etiquetaErrores.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
    }

    /**
     * Shows an error message
     * @param mensaje the error message
     */
    private void mostrarError(String mensaje) {
        etiquetaErrores.setText("✗ " + mensaje);
        etiquetaErrores.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
    }

    /**
     * Ends the current game session
     * @param razon the reason for ending
     */
    private void terminarJuego(String razon) {
        juegoActivo = false;
        if (cronometroJuego != null) {
            cronometroJuego.stop();
        }

        String mensajeFinal = String.format("%s\n\nResumen final:\nNiveles completados: %d\nErrores totales: %d",
                razon, nivelActual - 1, totalErrores);
        etiquetaProgreso.setText(mensajeFinal);

        // Disable input
        if (areaTexto != null) {
            areaTexto.setDisable(true);
        }
        botonVerificar.setDisable(true);
    }

    /**
     * Handles the retry button click event
     * @param evento the action event
     */
    @FXML
    private void manejarBotonReintentar(ActionEvent evento) {
        // Re-enable components
        if (areaTexto != null) {
            areaTexto.setDisable(false);
        }
        botonVerificar.setDisable(false);

        // Reset game
        inicializarJuego();
    }

    /**
     * Handles the menu button click event
     * @param evento the action event
     */
    @FXML
    private void manejarBotonMenu(ActionEvent evento) {
        // Stop timer if running
        if (cronometroJuego != null) {
            cronometroJuego.stop();
        }

        try {
            AplicacionEscrituraRapida.cambiarEscena("/com/example/funcion/principal.fxml");
        } catch (Exception e) {
            System.err.println("Error returning to main menu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}