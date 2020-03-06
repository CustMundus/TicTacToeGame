package com.example.tictactoegame

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TableLayout
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    enum class  PLAYINGPLAYER {
        FIRST_PLAYER,
        SECOND_PLAYER
    }
    enum class WINNER_OF_GAME{
        PLAYER_ONE,
        PLAYER_TWO,
        NO_ONE
    }

    var playingPlayer: PLAYINGPLAYER? = null
    var winnerOfGame: WINNER_OF_GAME? = null

    var player1Options:ArrayList<Int> = ArrayList()
    var player2Options:ArrayList<Int> = ArrayList()
    var allDisabledImages: ArrayList<ImageButton?> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playingPlayer = PLAYINGPLAYER.FIRST_PLAYER
    }

    fun imageButtonClicked(view: View){

        val selectedImageButton: ImageButton = view as ImageButton
        var randomNumber = (Math.random() * 9 +1).toInt()

        when (randomNumber) {

            1 -> tableLayout.setBackgroundColor(Color.YELLOW)
            2 -> tableLayout.setBackgroundColor(Color.DKGRAY)
            3 -> tableLayout.setBackgroundColor(Color.GREEN)
            4 -> tableLayout.setBackgroundColor(Color.LTGRAY)
            5 -> tableLayout.setBackgroundColor(Color.CYAN)
            6 -> tableLayout.setBackgroundColor(Color.MAGENTA)
            7 -> tableLayout.setBackgroundColor(Color.RED)
            8 -> tableLayout.setBackgroundColor(Color.BLUE)
            9 -> tableLayout.setBackgroundColor(Color.WHITE)
        }

        var optionNumber = 0
        when (selectedImageButton.id){

            R.id.imageButton -> optionNumber = 1
            R.id.imageButton2 -> optionNumber = 2
            R.id.imageButton3 -> optionNumber = 3
            R.id.imageButton4 -> optionNumber = 4
            R.id.imageButton5 -> optionNumber = 5
            R.id.imageButton6 -> optionNumber = 6
            R.id.imageButton7 -> optionNumber = 7
            R.id.imageButton8 -> optionNumber = 8
            R.id.imageButton9 -> optionNumber = 9
        }
        action(optionNumber, selectedImageButton)
    }
    private fun action(optionNumber: Int, selectedImageButton: ImageButton) {

        if (playingPlayer == PLAYINGPLAYER.FIRST_PLAYER){
            selectedImageButton.setImageResource(R.drawable.cross)
            player1Options.add(optionNumber)
            selectedImageButton.isEnabled = false
            allDisabledImages.add(selectedImageButton)
            playingPlayer = PLAYINGPLAYER.SECOND_PLAYER
        } else if (playingPlayer == PLAYINGPLAYER.SECOND_PLAYER) {

            selectedImageButton.setImageResource(R.drawable.curcle)
            player2Options.add(optionNumber)
            selectedImageButton.isEnabled =false
            allDisabledImages.add(selectedImageButton)
            playingPlayer = PLAYINGPLAYER.FIRST_PLAYER
        }

        specifyTheWinnerOfGame()



    }

    private fun specifyTheWinnerOfGame(){
        if (player1Options.contains(1) && player1Options.contains(2) && player1Options.contains(3)){
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        } else if (player2Options.contains(1) && player2Options.contains(2) && player2Options.contains(3)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        } else if (player1Options.contains(4) && player1Options.contains(5) && player1Options.contains(6)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        } else if (player2Options.contains(4) && player2Options.contains(5) && player2Options.contains(6)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        } else if (player1Options.contains(7) && player1Options.contains(8) && player1Options.contains(9)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        } else if (player2Options.contains(7) && player2Options.contains(8) && player2Options.contains(9)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        } else if (player1Options.contains(1) && player1Options.contains(4) && player1Options.contains(7)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        } else if (player2Options.contains(1) && player2Options.contains(4) && player2Options.contains(7)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        } else if (player1Options.contains(2) && player1Options.contains(5) && player1Options.contains(8)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        } else if (player2Options.contains(2) && player2Options.contains(5) && player2Options.contains(8)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        } else if (player1Options.contains(3) && player1Options.contains(6) && player1Options.contains(9)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        } else if (player2Options.contains(3) && player2Options.contains(6) && player2Options.contains(9)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        } else if (player1Options.contains(1) && player1Options.contains(5) && player1Options.contains(9)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        } else if (player2Options.contains(1) && player2Options.contains(5) && player2Options.contains(9)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        } else if (player1Options.contains(3) && player1Options.contains(5) && player1Options.contains(7)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        } else if (player2Options.contains(3) && player2Options.contains(5) && player2Options.contains(7)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        }

        if(winnerOfGame == WINNER_OF_GAME.PLAYER_ONE) {
            createAlert("Player One Wins","Congratulations to Player 1",AlertDialog.BUTTON_POSITIVE, "OK", false)

            return
        } else if (winnerOfGame == WINNER_OF_GAME.PLAYER_TWO){
            createAlert("Player Two Wins","Congratulations to Player 2",AlertDialog.BUTTON_POSITIVE, "OK", false)

            return
        }

        checkDrawState()
    }

    private fun createAlert(title: String, messege: String, whichButton: Int, buttonText: String, cancelable: Boolean){
        val alertDialog: AlertDialog = AlertDialog.Builder(this@MainActivity).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage((messege))

        alertDialog.setButton(whichButton, buttonText) {
                dialog: DialogInterface?, which: Int ->

            resetGame()
        }
        alertDialog.setCancelable(cancelable)

        alertDialog.show()
    }
    private fun  resetGame(){

        player1Options.clear()
        player2Options.clear()
        allDisabledImages.clear()
        winnerOfGame = WINNER_OF_GAME.NO_ONE

        imageButton.setImageResource(R.drawable.hold)
        imageButton2.setImageResource(R.drawable.hold)
        imageButton3.setImageResource(R.drawable.hold)
        imageButton4.setImageResource(R.drawable.hold)
        imageButton5.setImageResource(R.drawable.hold)
        imageButton6.setImageResource(R.drawable.hold)
        imageButton7.setImageResource(R.drawable.hold)
        imageButton8.setImageResource(R.drawable.hold)
        imageButton9.setImageResource(R.drawable.hold)

        imageButton.isEnabled = true
        imageButton2.isEnabled = true
        imageButton3.isEnabled = true
        imageButton4.isEnabled = true
        imageButton5.isEnabled = true
        imageButton6.isEnabled = true
        imageButton7.isEnabled = true
        imageButton8.isEnabled = true
        imageButton9.isEnabled = true
    }

    private fun checkDrawState(){

        if (allDisabledImages.size == 9 && winnerOfGame != WINNER_OF_GAME.PLAYER_ONE && winnerOfGame != WINNER_OF_GAME.PLAYER_TWO) {

            createAlert("DRAW", "No one won the game!", AlertDialog.BUTTON_POSITIVE,"OK",false)

        }
    }
}
