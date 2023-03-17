package com.example.gametest2;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

public class GameController implements View.OnClickListener{
    //inital variables
    private GameView gv;
    private GameModel gm;

    //constructor
    public GameController(GameView gvo){
        gv = gvo;
        gm = gv.getGameModel();
    }

    //onclick method
    @Override
    public void onClick(View view) {
        gm.et.setText("");

        gm.et.setText(gs.toString());
    }
}
