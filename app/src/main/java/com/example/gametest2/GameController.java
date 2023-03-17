package com.example.gametest2;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

public class GameController implements View.OnClickListener{
    private GameView gv;
    private GameModel gm;
    private gameState gs;

    public GameController(GameView gvo){
        gv = gvo;
        gm = gv.getGameModel();
        //gs = gv.getGameState();
    }

    @Override
    public void onClick(View view) {
        gm.et.setText("");

        gm.et.setText(gs.toString());
    }
}
