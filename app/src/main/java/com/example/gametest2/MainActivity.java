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
import com.example.GameFramework.gameConfiguration.GamePlayerType;
import com.example.GameFramework.infoMessage.GameState;
import com.example.GameFramework.players.GamePlayer;
import com.example.gametest2.players.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends GameMainActivity {
    private static final int PORT_NUMBER = 2278;

    HashSet<String> table = new HashSet<String>();

    @Override
    public GameConfig createDefaultConfig() {
        //array of the players
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        //make the player types
        playerTypes.add(new GamePlayerType("Local Human Player") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new ScrabbleHumanPlayer(name);
            }
        });//human

        playerTypes.add(new GamePlayerType("Computer Player") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new ScrabbleComputerPlayer(name);
            }
        });//computer

        playerTypes.add(new GamePlayerType("Smart Computer Player") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new ScrabbleSmartComputerPlayer(name);
            }
        });

        //make the default config
        GameConfig defaultConfig = new GameConfig(playerTypes, 1, 2, "Scrabble", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); //add human
        defaultConfig.addPlayer("Computer", 1); //add computer
        defaultConfig.addPlayer("Smart Computer", 2); //add smart computer
        defaultConfig.setRemoteData("Remote Human Player", "", 0);

        return defaultConfig;
    }

    //@Override
    public LocalGame createLocalGame(GameState gs) {

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.words_alpha)));
        try {
            String line;
            //= reader.readLine();
            while ((line = reader.readLine()) != null) {
                //while loop checking for null, if not null add word
                //use readLine in while loop

                if (line.length() > 1 && line.length() < 16) {
                    table.add(line);
                    //reader.close();
                }
            }
            reader.close();
        }
        catch(Exception e){
        }

        if (gs == null) {
            return new ScrabbleLocalGame(table);
        }
        return new ScrabbleLocalGame((ScrabbleGameState) gs, table);
    }

    public HashSet<String> getHashSet(){
        return table;
    }
}