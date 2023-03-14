package com.example.gametest2;

import androidx.annotation.Nullable;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;

import com.example.GameFramework.GameMainActivity;
import com.example.GameFramework.LocalGame;
import com.example.GameFramework.gameConfiguration.GameConfig;
import com.example.GameFramework.infoMessage.GameState;

public class MainActivity extends GameMainActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        Button runTest = findViewById(R.id.configButton);
        runTest.setOnClickListener();
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