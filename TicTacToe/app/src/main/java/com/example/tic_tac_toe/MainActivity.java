package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0; // set active player to 0, means player 1
    boolean gameIsActive = true; // yes, the game is active
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {1,4,7},
            {2,5,8}, {0,4,8}, {2,4,6},{0,3,6}};
    // tic tac toe game, which cell is marked

    // what happen when the user click the image on the board
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        // number of pictures click on the board, 9 pictures
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        // if the user click the image within the board and
        // the game status is still active
        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;

            // if the user is active, player turn 1 or 2
            counter.setTranslationY(-1000f);  // run animation

            if (activePlayer == 0) {  // if player 1's turn
                counter.setImageResource(R.drawable.circle);
                activePlayer = 1;
                // set active player to player 2 after player 1 finish
            } else {  // if player 2's turn
                counter.setImageResource(R.drawable.no);
                activePlayer = 0; // set active player 1 to player 2 finisih
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            // run animation again

            //for loop to find out winners by checking the array elements of winning position
            // check if the user has clicked on the winning position or not
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    // Someone has won!, because they have click in the winning cells or positions
                    gameIsActive = false;  // game no longer active, cannot play

                    String winner = "X";  // set winner to X, player 2

                    if (gameState[winningPosition[0]] == 0) {  // if player 1 win the game
                        winner = "O";  // if the winner is player 1
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");  // display the winner

                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);  // display all components

                } else {
                    boolean gameIsOver = true;
                    for (int counterState : gameState) {
                        if (counterState == 2) gameIsOver = false;
                    }

                    if (gameIsOver) {
                        // if game is over already with no winner or losers
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a draw");
                        // display message
                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);  // set it to visible to display all components
                    }
                }
            }
        }
    }
    public void playAgain(View view) {
        gameIsActive = true;
        // change the status of the game to active again
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        // set player 1 as active, player 1 start the game first

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for (int i = 0; i< gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}