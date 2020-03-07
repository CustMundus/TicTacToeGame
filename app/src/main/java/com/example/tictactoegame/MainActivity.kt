package com.example.tictactoegame

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


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

    var playingPlayer: PLAYINGPLAYER? = PLAYINGPLAYER.FIRST_PLAYER
    var winnerOfGame: WINNER_OF_GAME? = null
    var human = true

    var player1Options:ArrayList<Int> = ArrayList()
    var player2Options:ArrayList<Int> = ArrayList()
    var allDisabledImages: ArrayList<ImageButton?> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            startService(Intent(this@MainActivity,MusicService::class.java))
        }catch (e: Exception){
            e.printStackTrace()
        }

        menuOnStart()

        playingPlayer = PLAYINGPLAYER.FIRST_PLAYER
    }

    fun imageButtonClicked(view: View){

        val selectedImageButton: ImageButton = view as ImageButton
        var randomNumber = (Math.random() * 6 +1).toInt()

        when (randomNumber) {

            1 -> tableLayout.setBackgroundColor(Color.parseColor("#004222"))
            2 -> tableLayout.setBackgroundColor(Color.parseColor("#024359"))
            3 -> tableLayout.setBackgroundColor(Color.parseColor("#e0b824"))
            4 -> tableLayout.setBackgroundColor(Color.parseColor("#d17128"))
            5 -> tableLayout.setBackgroundColor(Color.parseColor("#d04b3f"))
            6 -> tableLayout.setBackgroundColor(Color.WHITE)
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
    private fun action(optionNumber: Int, _selectedImageButton: ImageButton) {
        var selectedImageButton = _selectedImageButton


        if (playingPlayer == PLAYINGPLAYER.FIRST_PLAYER){
            selectedImageButton.setImageResource(R.drawable.cross)
            player1Options.add(optionNumber)
            selectedImageButton.isEnabled = false
            allDisabledImages.add(selectedImageButton)
            playingPlayer = PLAYINGPLAYER.SECOND_PLAYER
            specifyTheWinnerOfGameFirst()

            if(human == true){
                return
            }


        }
        if(playingPlayer == PLAYINGPLAYER.SECOND_PLAYER){
            when(human){
                true ->{
                    selectedImageButton.setImageResource(R.drawable.curcle)
                    player2Options.add(optionNumber)
                    selectedImageButton.isEnabled =false
                    allDisabledImages.add(selectedImageButton)
                    specifyTheWinnerOfGameSecond()
                    playingPlayer = PLAYINGPLAYER.FIRST_PLAYER

                }
                else ->{
                    var notSelectedImageNumbers = ArrayList<Int>()

                    for (imageNumbere in 1..9){
                        if(!(player1Options.contains(imageNumbere))){
                            if (!player2Options.contains(imageNumbere)){
                                notSelectedImageNumbers.add(imageNumbere)
                            }
                        }
                    }
                    try{
                        var randomNumber = ((Math.random() * notSelectedImageNumbers.size)).toInt()
                        var imageNumber = notSelectedImageNumbers[randomNumber]
                        when (imageNumber){
                            1 -> selectedImageButton = imageButton
                            2 -> selectedImageButton = imageButton2
                            3 -> selectedImageButton = imageButton3
                            4 -> selectedImageButton = imageButton4
                            5 -> selectedImageButton = imageButton5
                            6 -> selectedImageButton = imageButton6
                            7 -> selectedImageButton = imageButton7
                            8 -> selectedImageButton = imageButton8
                            9 -> selectedImageButton = imageButton9
                        }
                        selectedImageButton.setImageResource(R.drawable.curcle)
                        player2Options.add((imageNumber))
                        selectedImageButton.isEnabled = false
                        allDisabledImages.add((selectedImageButton))
                        specifyTheWinnerOfGameSecond()
                        playingPlayer = PLAYINGPLAYER.FIRST_PLAYER
                    } catch(e:Exception){
                        e.printStackTrace()
                    }
                }
            }

        }
//        if (playingPlayer == PLAYINGPLAYER.SECOND_PLAYER && human == true) {
//            selectedImageButton.setImageResource(R.drawable.curcle)
//            player2Options.add(optionNumber)
//            selectedImageButton.isEnabled =false
//            allDisabledImages.add(selectedImageButton)
//            playingPlayer = PLAYINGPLAYER.FIRST_PLAYER
//        }else if(playingPlayer == PLAYINGPLAYER.SECOND_PLAYER && human == false){
//            val notSelectedImageNumbers = ArrayList<Int>()
//
//            for (imageNumbere in 1..9){
//                if(!(player1Options.contains(imageNumbere))){
//                        if (!player2Options.contains(imageNumbere)){
//                            notSelectedImageNumbers.add(imageNumbere)
//                        }
//                    }
//                }
//                try{
//                    var randomNumber = ((Math.random() * notSelectedImageNumbers.size)).toInt()
//                    var imageNumber = notSelectedImageNumbers[randomNumber]
//                    when (imageNumber){
//                        1 -> selectedImageButton = imageButton
//                        2 -> selectedImageButton = imageButton2
//                        3 -> selectedImageButton = imageButton3
//                        4 -> selectedImageButton = imageButton4
//                        5 -> selectedImageButton = imageButton5
//                        6 -> selectedImageButton = imageButton6
//                        7 -> selectedImageButton = imageButton7
//                        8 -> selectedImageButton = imageButton8
//                        9 -> selectedImageButton = imageButton9
//                    }
//                    selectedImageButton.setImageResource(R.drawable.curcle)
//                    player2Options.add((imageNumber))
//                    selectedImageButton.isEnabled = false
//                    allDisabledImages.add((selectedImageButton))
//                    playingPlayer = PLAYINGPLAYER.FIRST_PLAYER
//                } catch(e:Exception){
//                    e.printStackTrace()
//                }
//            }






    }

    private fun specifyTheWinnerOfGameFirst(){
        if (player1Options.contains(1) && player1Options.contains(2) && player1Options.contains(3)){
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        } else if (player1Options.contains(4) && player1Options.contains(5) && player1Options.contains(6)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        } else if (player1Options.contains(7) && player1Options.contains(8) && player1Options.contains(9)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        } else if (player1Options.contains(1) && player1Options.contains(4) && player1Options.contains(7)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        } else if (player1Options.contains(2) && player1Options.contains(5) && player1Options.contains(8)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        } else if (player1Options.contains(3) && player1Options.contains(6) && player1Options.contains(9)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        } else if (player1Options.contains(1) && player1Options.contains(5) && player1Options.contains(9)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        }  else if (player1Options.contains(3) && player1Options.contains(5) && player1Options.contains(7)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_ONE
        }
        checkDrawState()

        if(winnerOfGame == WINNER_OF_GAME.PLAYER_ONE) {
            createAlert("Player One Wins","Congratulations to Player 1",AlertDialog.BUTTON_POSITIVE, "OK", false)

            return
        }
    }
    private fun specifyTheWinnerOfGameSecond(){
        if (player2Options.contains(1) && player2Options.contains(2) && player2Options.contains(3)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        } else if (player2Options.contains(4) && player2Options.contains(5) && player2Options.contains(6)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        }else if (player2Options.contains(7) && player2Options.contains(8) && player2Options.contains(9)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        } else if (player2Options.contains(1) && player2Options.contains(4) && player2Options.contains(7)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        }else if (player2Options.contains(2) && player2Options.contains(5) && player2Options.contains(8)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        }else if (player2Options.contains(3) && player2Options.contains(6) && player2Options.contains(9)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        } else if (player2Options.contains(1) && player2Options.contains(5) && player2Options.contains(9)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        }else if (player2Options.contains(3) && player2Options.contains(5) && player2Options.contains(7)) {
            winnerOfGame = WINNER_OF_GAME.PLAYER_TWO
        }

            if (winnerOfGame == WINNER_OF_GAME.PLAYER_TWO){
            createAlert("Player Two Wins","Congratulations to Player 2",AlertDialog.BUTTON_POSITIVE, "OK", false)

            return
        }

        checkDrawState()
    }

    private fun createAlert(title: String, message: String, whichButton: Int, buttonText: String, cancelable: Boolean){
        val menuex = AlertDialog.BUTTON_NEGATIVE
        val menuext = "Go to the menu"
        val alertDialog: AlertDialog = AlertDialog.Builder(this@MainActivity).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage((message))

        alertDialog.setButton(whichButton, buttonText) {
                dialog: DialogInterface?, which: Int ->

            resetGame()

        }
        alertDialog.setButton(menuex,menuext){
                dialog: DialogInterface?, which: Int ->
            menuOnStart()
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

        playingPlayer = PLAYINGPLAYER.FIRST_PLAYER

        return
    }

    private fun checkDrawState(){

        if (allDisabledImages.size == 9 && winnerOfGame != WINNER_OF_GAME.PLAYER_ONE && winnerOfGame != WINNER_OF_GAME.PLAYER_TWO) {

            createAlert("DRAW", "No one won the game!", AlertDialog.BUTTON_POSITIVE,"OK",false)

        }
    }
    private fun menuOnStart(){
        //title: String, message: String, whichButton: Int, buttonText: String, cancelable: Boolean
        val title = "Menu"
        val message = "Choose the game mode!"
        val humanButton = AlertDialog.BUTTON_POSITIVE
        val aiButton = AlertDialog.BUTTON_NEGATIVE
        val humanButtonText = "Two players"
        val aiButtonText = "Play with AI"
        val cancelable = false

        val alertDialog: AlertDialog = AlertDialog.Builder(this@MainActivity).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage((message))

        alertDialog.setButton(humanButton, humanButtonText) {
                dialog: DialogInterface?, which: Int ->

            human = true
            resetGame()
        }
        alertDialog.setButton(aiButton, aiButtonText) {
                dialog: DialogInterface?, which: Int ->

            human = false
            resetGame()
        }
        alertDialog.setCancelable(cancelable)

        alertDialog.show()
    }
}
