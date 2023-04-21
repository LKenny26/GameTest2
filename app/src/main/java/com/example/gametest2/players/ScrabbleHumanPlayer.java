package com.example.gametest2.players;

import android.view.View;
import android.widget.Button;

import com.example.GameFramework.GameMainActivity;
import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.players.GameHumanPlayer;
import com.example.gametest2.R;
import com.example.gametest2.actions.*;

public class ScrabbleHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    private Button playword = null;
    private Button shuffle = null;
    private Button removeTiles = null;
    private Button skip = null;
    private Button spellcheck = null;
    //Tile t;

    public ScrabbleHumanPlayer(String name) {super(name); }

    @Override
    public void onClick(View button) {
        //t = new Tile();
        int x = button.getId();
        PlayWordAction pwa = new PlayWordAction(this);
        ShuffleAction sha = new ShuffleAction(this);
        SkipAction ska = new SkipAction(this);
        SpellCheckAction sca = new SpellCheckAction(this);

        RemoveTilesAction rta = new RemoveTilesAction(this);

        if(button.getId() == R.id.playword){
            super.game.sendAction(pwa);
        }
        else if(button.getId() == R.id.shuffle){
            //super.game.sendAction(sha);
            //call shuffle method in tile
        }
       else if(button.getId() == R.id.skip){
            super.game.sendAction(ska);
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
    }

    public String getName(){
        return name;
    }
}
