package com.example.gametest2.players;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import com.example.GameFramework.GameMainActivity;
import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.GameFramework.players.GameHumanPlayer;
import com.example.GameFramework.utilities.Logger;
import com.example.gametest2.R;
import com.example.gametest2.ScrabbleGameState;
import com.example.gametest2.Square;
import com.example.gametest2.actions.*;
import com.example.gametest2.views.Board;

public class ScrabbleHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    private static final String TAG = "ScrabbleHumanPlayer1";
    private Button playword = null;
    private Button shuffle = null;
    private Button removeTiles = null;
    private Button skip = null;
    private Button spellcheck = null;

    private Board bd;
    //Tile t;

    public ScrabbleHumanPlayer(String name) {super(name); }

    @Override
    public void onClick(View button) {
        //t = new Tile();
        int x = button.getId();
        ScrabbleGameState sgs = new ScrabbleGameState();
        //PlayWordAction pwa = new PlayWordAction(this);
        ShuffleAction sha = new ShuffleAction(this);
        SkipAction ska = new SkipAction(this);
        SpellCheckAction sca = new SpellCheckAction(this);

        RemoveTilesAction rta = new RemoveTilesAction(this);

        if(button.getId() == R.id.playword){
            PlayWordAction pwa = new PlayWordAction(this, sgs.getPlayerID());
            game.sendAction(pwa);
            bd.invalidate();
        }
        else if(button.getId() == R.id.shuffle){
            //super.game.sendAction(sha);
            //call shuffle method in tile
        }
       else if(button.getId() == R.id.skip){
            game.sendAction(ska);
            bd.invalidate();
        }
        else if(button.getId() == R.id.spellcheck){
          //  super.game.sendAction(sca);
        }
        else if(button.getId() == R.id.removeTiles){
            //super.game.sendAction(rta);
        }

    }

    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.human_player_view);
    }

    @Override
    public void receiveInfo(GameInfo info) {
        if(info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo){
            //bd.flash(Color.RED, 50);
            Logger.log(TAG, "wrong move");
        }
        else if(!(info instanceof ScrabbleGameState)){
            return;
        }
        else {
            bd.setState((ScrabbleGameState) info);
            bd.invalidate();
            Logger.log(TAG, "receiving");
        }
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        myActivity = activity;
        activity.setContentView(R.layout.human_player_view);

        this.playword = (Button)activity.findViewById(R.id.playword);
        System.out.println("play word" + playword.getId() + " " + R.id.playword);
        this.shuffle = (Button)activity.findViewById(R.id.shuffle);
        System.out.println("play word" + shuffle.getId() + " " + R.id.shuffle);
        this.removeTiles = (Button)activity.findViewById(R.id.removeTiles);
        System.out.println("play word" + removeTiles.getId() + " " + R.id.removeTiles);
        this.skip = (Button)activity.findViewById(R.id.skip);
        System.out.println("play word" + skip.getId() + " " + R.id.skip);
        this.spellcheck = (Button)activity.findViewById(R.id.spellcheck);
        System.out.println("play word" + spellcheck.getId() + " " + R.id.spellcheck);

        playword.setOnClickListener(this);
        shuffle.setOnClickListener(this);
        removeTiles.setOnClickListener(this);
        skip.setOnClickListener(this);
        spellcheck.setOnClickListener(this);

        bd = (Board)myActivity.findViewById(R.id.Board);
        Logger.log("setting listeners", "onClick");
    }

    public String getName(){
        return name;
    }
}
