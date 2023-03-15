package com.example.gametest2;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

public class GameController implements View.OnClickListener{
    private GameView gv;
    private GameModel gm;

    public GameController(GameView gvo){
        gv = gvo;
        gm = gv.getGameModel();
    }

    @Override
    public void onClick(View view) {

    }
}
