package com.example.gametest2;

import android.view.View;

import com.example.GameFramework.GameMainActivity;
import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.players.GameHumanPlayer;

public class ScrabbleHumanPlayer extends GameHumanPlayer implements View.OnClickListener {


    public ScrabbleHumanPlayer(String name){
        super(name);
    }
    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {

    }

    @Override
    public void onClick(View view) {

    }
}
