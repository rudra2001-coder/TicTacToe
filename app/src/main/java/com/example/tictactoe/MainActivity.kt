package com.example.tictactoe

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var isPlayerX = true // Player X starts first
    private val buttons = arrayOfNulls<Button>(9)
    private val board = arrayOfNulls<String>(9) // Track moves

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize buttons
        buttons[0] = findViewById(R.id.btn1)
        buttons[1] = findViewById(R.id.btn2)
        buttons[2] = findViewById(R.id.btn3)
        buttons[3] = findViewById(R.id.btn4)
        buttons[4] = findViewById(R.id.btn5)
        buttons[5] = findViewById(R.id.btn6)
        buttons[6] = findViewById(R.id.btn7)
        buttons[7] = findViewById(R.id.btn8)
        buttons[8] = findViewById(R.id.btn9)



        // ... Initialize all 9 buttons similarly

        // Set click listeners
        for (i in 0 until 9) {
            buttons[i]?.setOnClickListener { onCellClicked(i) }
        }

        // Reset game
        findViewById<Button>(R.id.resetButton).setOnClickListener { resetGame() }





    }
    private fun onCellClicked(position: Int) {
        if (board[position] == null) { // Check if cell is empty
            // Update board and UI
            board[position] = if (isPlayerX) "X" else "O"
            buttons[position]?.text = board[position]

            // Check for win/draw
            if (checkWin()) {
                showWinnerDialog(if (isPlayerX) "X" else "O")
            } else if (isBoardFull()) {
                showWinnerDialog("Draw")
            } else {
                // Switch player
                isPlayerX = !isPlayerX
            }
        }
    }
    private fun checkWin(): Boolean {
        // Define winning combinations
        val winCombinations = arrayOf(
            intArrayOf(0, 1, 2), // Rows
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6), // Columns
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8), // Diagonals
            intArrayOf(2, 4, 6)
        )

        // Check all combinations
        for (combination in winCombinations) {
            if (board[combination[0]] != null &&
                board[combination[0]] == board[combination[1]] &&
                board[combination[1]] == board[combination[2]]
            ) {
                return true
            }
        }
        return false
    }

    private fun isBoardFull(): Boolean {
        return !board.contains(null)
    }
    private fun resetGame() {
        // Clear board and UI
        for (i in 0 until 9) {
            board[i] = null
            buttons[i]?.text = ""
        }
        isPlayerX = true
    }

    private fun showWinnerDialog(winner: String) {
        AlertDialog.Builder(this)
            .setTitle(
                if (winner == "Draw") "Game Over!"
                else "Player $winner Wins!"
            )
            .setMessage("Play again?")
            .setPositiveButton("Yes") { _, _ -> resetGame() }
            .setNegativeButton("No") { _, _ -> finish() }
            .show()
    }






    }
