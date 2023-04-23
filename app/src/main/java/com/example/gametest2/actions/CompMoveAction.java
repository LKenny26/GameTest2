package com.example.gametest2.actions;

import com.example.GameFramework.actionMessage.GameAction;
import com.example.GameFramework.players.GamePlayer;
import com.example.gametest2.views.Board;

public class CompMoveAction extends GameAction {
    private int row;
    private int col;

    Board bd;
    //public CompMoveAction(GamePlayer player){super(player);}
    public CompMoveAction(GamePlayer player, int x, int y){
      super(player);
      this.row = Math.max(0, Math.min(10, x));
      this.col = Math.max(0, Math.min(10, y));
      bd.invalidate();
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }
}
