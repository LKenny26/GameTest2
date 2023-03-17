package com.example.gametest2;

import androidx.annotation.Nullable;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.GameFramework.GameMainActivity;
import com.example.GameFramework.LocalGame;
import com.example.GameFramework.gameConfiguration.GameConfig;
import com.example.GameFramework.infoMessage.GameState;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainActivity extends GameMainActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        GameView gv = new GameView();
        GameController gc = new GameController(gv);
        GameModel gm = gv.getGameModel();

        Button runTest = findViewById(R.id.runTestButton);
        runTest.setOnClickListener(gc);

        gm.et = findViewById(R.id.multiLineText);

        try {
            gm.scan = new Scanner(gm.dictionary);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (gm.scan.hasNextLine()) {
            gm.spellCheckTable.add(gm.scan.nextLine());
        }


        com.example.gametest2.GameState gs = new com.example.gametest2.GameState(gv);
    }

    @Override
    public GameConfig createDefaultConfig() {
        return null;
    }

    @Override
    public LocalGame createLocalGame(GameState gameState) {
        return null;
    }

}