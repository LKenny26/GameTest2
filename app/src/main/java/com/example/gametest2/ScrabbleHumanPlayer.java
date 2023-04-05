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
        return myActivity.findViewById(R.id.human_player_view);
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        //setting the human's player view
        myActivity = activity;
        activity.setContentView(R.layout.human_player_view);
    }

    @Override
    public void onClick(View view) {

    }
}
