package com.example.gametest2.actions;

import com.example.GameFramework.actionMessage.GameAction;
import com.example.GameFramework.players.GamePlayer;

public class CompMoveAction extends GameAction {
    private int row;
    private int col;
    //public CompMoveAction(GamePlayer player){super(player);}
    public CompMoveAction(GamePlayer player, int x, int y){
      super(player);
      this.row = x;
      this.col = y;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }
}
