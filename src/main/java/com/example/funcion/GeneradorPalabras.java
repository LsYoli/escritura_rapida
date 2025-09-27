/**
 * Class responsible for generating random words for the game.
 * Defines the word set and provides a new word for each level.
 *
 * @author Luis Santiago Arenas Hincapié
 * @version 1.0
 */
package com.example.funcion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utility class for generating random words and phrases for the typing game.
 * Contains a collection of Spanish words and phrases with varying difficulty levels.
 */
public class GeneradorPalabras {

    private static final List<String> PALABRAS_FACILES = new ArrayList<>();
    private static final List<String> PALABRAS_MEDIAS = new ArrayList<>();
    private static final List<String> PALABRAS_DIFICILES = new ArrayList<>();
    private static final List<String> FRASES = new ArrayList<>();

    private static final Random aleatorio = new Random();

    static {
        // Initialize easy words (3-5 letters)
        PALABRAS_FACILES.add("casa");
        PALABRAS_FACILES.add("perro");
        PALABRAS_FACILES.add("gato");
        PALABRAS_FACILES.add("sol");
        PALABRAS_FACILES.add("luna");
        PALABRAS_FACILES.add("agua");
        PALABRAS_FACILES.add("fuego");
        PALABRAS_FACILES.add("aire");
        PALABRAS_FACILES.add("tierra");
        PALABRAS_FACILES.add("libro");
        PALABRAS_FACILES.add("mesa");
        PALABRAS_FACILES.add("silla");
        PALABRAS_FACILES.add("puerta");
        PALABRAS_FACILES.add("ventana");
        PALABRAS_FACILES.add("árbol");

        // Initialize medium words (6-8 letters)
        PALABRAS_MEDIAS.add("computadora");
        PALABRAS_MEDIAS.add("programar");
        PALABRAS_MEDIAS.add("tecnología");
        PALABRAS_MEDIAS.add("universidad");
        PALABRAS_MEDIAS.add("javascript");
        PALABRAS_MEDIAS.add("desarrollo");
        PALABRAS_MEDIAS.add("aplicación");
        PALABRAS_MEDIAS.add("escritura");
        PALABRAS_MEDIAS.add("velocidad");
        PALABRAS_MEDIAS.add("precisión");
        PALABRAS_MEDIAS.add("educación");
        PALABRAS_MEDIAS.add("estudiante");
        PALABRAS_MEDIAS.add("profesor");
        PALABRAS_MEDIAS.add("ejercicio");
        PALABRAS_MEDIAS.add("práctica");

        // Initialize hard words (9+ letters)
        PALABRAS_DIFICILES.add("extraordinario");
        PALABRAS_DIFICILES.add("incomprensible");
        PALABRAS_DIFICILES.add("supercalifragilistico");
        PALABRAS_DIFICILES.add("anticonstitucional");
        PALABRAS_DIFICILES.add("electroencefalograma");
        PALABRAS_DIFICILES.add("otorrinolaringólogo");
        PALABRAS_DIFICILES.add("paralelepípedo");
        PALABRAS_DIFICILES.add("esternocleidomastoideo");
        PALABRAS_DIFICILES.add("electrocardiograma");
        PALABRAS_DIFICILES.add("desoxirribonucleico");

        // Initialize phrases
        FRASES.add("Hola mundo");
        FRASES.add("JavaFX es genial");
        FRASES.add("La programación es arte");
        FRASES.add("¡Escribir rápido es divertido!");
        FRASES.add("El tiempo vuela cuando programas");
        FRASES.add("Código limpio, mente clara");
        FRASES.add("Debugging es como ser detective");
        FRASES.add("Un bug al día mantiene al programador despierto");
        FRASES.add("No hay errores, solo características no documentadas");
        FRASES.add("Primero hazlo funcionar, luego hazlo bonito");
        FRASES.add("La práctica hace al maestro");
        FRASES.add("Cada día se aprende algo nuevo");
        FRASES.add("La paciencia es una virtud");
        FRASES.add("Nunca pares de aprender");
        FRASES.add("El conocimiento es poder");
    }

    /**
     * Generates a random word or phrase based on the current level.
     * Difficulty increases as the level progresses.
     * @param nivel the current game level
     * @return a random word or phrase appropriate for the level
     */
    public static String generarPalabra(int nivel) {
        if (nivel <= 3) {
            // Easy words for first 3 levels
            return PALABRAS_FACILES.get(aleatorio.nextInt(PALABRAS_FACILES.size()));
        } else if (nivel <= 8) {
            // Mix of easy and medium words
            if (aleatorio.nextBoolean()) {
                return PALABRAS_FACILES.get(aleatorio.nextInt(PALABRAS_FACILES.size()));
            } else {
                return PALABRAS_MEDIAS.get(aleatorio.nextInt(PALABRAS_MEDIAS.size()));
            }
        } else if (nivel <= 15) {
            // Medium and hard words, with some phrases
            int opcion = aleatorio.nextInt(3);
            switch (opcion) {
                case 0:
                    return PALABRAS_MEDIAS.get(aleatorio.nextInt(PALABRAS_MEDIAS.size()));
                case 1:
                    return PALABRAS_DIFICILES.get(aleatorio.nextInt(PALABRAS_DIFICILES.size()));
                default:
                    return FRASES.get(aleatorio.nextInt(FRASES.size()));
            }
        } else {
            // High level: harder words and phrases
            if (aleatorio.nextBoolean()) {
                return PALABRAS_DIFICILES.get(aleatorio.nextInt(PALABRAS_DIFICILES.size()));
            } else {
                return FRASES.get(aleatorio.nextInt(FRASES.size()));
            }
        }
    }

    /**
     * Gets the total number of available words and phrases
     * @return total count of words and phrases
     */
    public static int obtenerTotalPalabras() {
        return PALABRAS_FACILES.size() + PALABRAS_MEDIAS.size() + PALABRAS_DIFICILES.size() + FRASES.size();
    }
}