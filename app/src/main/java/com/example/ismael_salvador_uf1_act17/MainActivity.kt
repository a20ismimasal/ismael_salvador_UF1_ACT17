package com.example.ismael_salvador_uf1_act17

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Matriu de botons per representar el taulell
    private lateinit var buttons: Array<Button>
    // Jugador actual
    private var currentPlayer = "X"
    // Estat del taulell
    private var board = Array(3) { arrayOfNulls<String>(3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialitza els botons del taulell
        buttons = arrayOf(
            findViewById(R.id.button0),
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8)
        )

        // Assigna el listener de clic a cada botó
        for (i in buttons.indices) {
            buttons[i].setOnClickListener { onButtonClick(i) }
        }
    }

    private fun onButtonClick(index: Int) {
        val row = index / 3
        val col = index % 3

        // Comprova si la casella està buida
        if (board[row][col] == null) {
            // Actualitza el taulell i el botó amb el jugador actual
            board[row][col] = currentPlayer
            buttons[index].text = currentPlayer

            // Comprova si hi ha un guanyador
            if (checkForWin()) {
                Toast.makeText(this, "Player $currentPlayer wins!", Toast.LENGTH_LONG).show()
                resetBoard()
            } else if (board.flatten().none { it == null }) {
                // Comprova si el taulell està ple (empat)
                Toast.makeText(this, "It's a draw!", Toast.LENGTH_LONG).show()
                resetBoard()
            } else {
                // Canvia el torn al següent jugador
                currentPlayer = if (currentPlayer == "X") "O" else "X"
            }
        }
    }

    private fun checkForWin(): Boolean {
        // Comprova les files i les columnes
        for (i in 0..2) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true
            }
        }

        // Comprova les diagonals
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true
        }

        return false
    }

    private fun resetBoard() {
        // Reinicia el taulell i els botons
        board = Array(3) { arrayOfNulls<String>(3) }
        for (button in buttons) {
            button.text = ""
        }
        // Reinicia el jugador actual
        currentPlayer = "X"
    }
}
