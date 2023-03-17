package com.example.gametest2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.GameFramework.GameMainActivity;
import com.example.GameFramework.LocalGame;
import com.example.GameFramework.gameConfiguration.GameConfig;
import com.example.GameFramework.infoMessage.GameState;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //game view, controller and model that all correspond to one another
    //instance for onClick to work
    GameView gv = new GameView();
    GameController gc = new GameController(gv);
    GameModel gm = gv.getGameModel();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.runatest);
        //game view, controller and model that all correspond to one another
        //GameView gv = new GameView();
        //GameController gc = new GameController(gv);
        //GameModel gm = gv.getGameModel();

        //make the button an on click listener

        //make a multi like editable text
        gm.et = (EditText) findViewById(R.id.multiLineText);
        Button runTest = findViewById(R.id.runTestButton);
        runTest.setOnClickListener(gc);
        onClick(runTest);

        //try and catch to make sure the file opened correctly
        try {
            //make the scan
            gm.scan = new Scanner(gm.dictionary);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //while loop to make the hashset that has all of the words
        while (gm.scan.hasNextLine()) {
            gm.spellCheckTable.add(gm.scan.nextLine());
        }


        com.example.gametest2.gameState gs = new com.example.gametest2.gameState(gv);
    }
    public void onClick(View view){
        //GameView gvforclick = new GameView();
        gm.et.getText().clear();
        gameState firstInstance = new gameState();
        gameState secondInstance = new gameState(firstInstance);
        firstInstance.isTilePlaced();
        firstInstance.playTile();
        firstInstance.spelling();
        firstInstance.getScore();
        firstInstance.getFirstPlayerTurn();
        firstInstance.getSecondPlayerTurn();
        firstInstance.getThirdPlayerTurn();
        firstInstance.getFourthPlayerTurn();
        firstInstance.getAiTurn();

        gm.et.setText("Player one has gone: " + firstInstance.getFirstPlayerTurn()
                        + ". " + "Player two has gone: " + firstInstance.getSecondPlayerTurn()
                + ". " + "Player three has gone: " + firstInstance.getThirdPlayerTurn()
                + ". " + "Player four has gone: " + firstInstance.getFourthPlayerTurn()
                + ". " + "AI has gone: " + firstInstance.getAiTurn() + " . The score is "
                + firstInstance.getScore() +". A tile is placed: "
                + firstInstance.isTilePlaced() + ". Word has been spell checked: "
                + firstInstance.spelling() +". The player has played a tile "
                + firstInstance.playTile() + ".");

        gameState thirdInstance = new gameState();
        gameState fourthInstance = new gameState(thirdInstance);

        secondInstance.toString();
        fourthInstance.toString();
    }

    //@Override
    //public GameConfig createDefaultConfig() {
      //  return null;
    //}

    //@Override
    //public LocalGame createLocalGame(gameState gameState) {
      //  return null;
    //}


}