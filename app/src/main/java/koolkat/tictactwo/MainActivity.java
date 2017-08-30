package koolkat.tictactwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 is X
    int activePlayer = 0;

    //true is x
    boolean turn = true;

    boolean isGameActive = true;

    //2 is unplayed
    int[] boardState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int moves = 0;
    private LinearLayout playAgainLayout;
    private TextView turnTextView;

    public void playAgain(View view) {

        moves = 0;

        isGameActive = true;

        turn = true;

        turnTextView.setText("Morty's Turn");

        playAgainLayout.setVisibility(View.GONE);
        activePlayer = 0;

        for (int i = 0; i < boardState.length; i++) {
            boardState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.boardgridlayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int playedTile = Integer.parseInt(counter.getTag().toString());

        if (boardState[playedTile] == 2 && isGameActive) {

            moves++;

            boardState[playedTile] = activePlayer;
            if (activePlayer == 0) {
                counter.setTranslationY(-1000f);
                counter.setImageResource(R.drawable.playx);
                counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);
                activePlayer = 1;
            } else {
                counter.setTranslationY(-1000f);
                counter.setImageResource(R.drawable.playo);
                counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);
                activePlayer = 0;
            }

            turn = !turn;

            if (turn)
                turnTextView.setText("Morty's Turn");

            else
                turnTextView.setText("Rick's Turn");

        }

        for (int[] winningPosition : winningPositions) {

            if (boardState[winningPosition[0]] == boardState[winningPosition[1]]
                    && boardState[winningPosition[1]] == boardState[winningPosition[2]]
                    && boardState[winningPosition[0]] != 2) {

                isGameActive = false;

                playAgainLayout.setVisibility(View.VISIBLE);

                TextView winnerTextView = (TextView) findViewById(R.id.winStateTextView);

                if (boardState[winningPosition[0]] == 0) {
                    winnerTextView.setText("Morty has won!");
                } else {
                    winnerTextView.setText("Rick has won!");
                }

            }

        }

        if (moves == 9) {

            isGameActive = false;

            playAgainLayout.setVisibility(View.VISIBLE);

            TextView winnerTextView = (TextView) findViewById(R.id.winStateTextView);

            winnerTextView.setText("It's a draw!");

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        turnTextView = (TextView) findViewById(R.id.turn_text_view);

    }
}
