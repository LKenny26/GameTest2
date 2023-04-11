package com.example.gametest2;

import android.view.View;
import android.widget.Button;

import com.example.GameFramework.GameMainActivity;
import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.players.GameHumanPlayer;

public class ScrabbleHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    private Button playword = null;
    private Button shuffle = null;
    private Button newtiles = null;
    private Button skip = null;
    private Button spellcheck = null;

    public ScrabbleHumanPlayer(String name) {super(name); }

    @Override
    public void onClick(View button) {

        PlayWordAction pwa = new PlayWordAction(this);
        ShuffleAction sha = new ShuffleAction(this);
        SkipAction ska = new SkipAction(this);
        SpellCheckAction sca = new SpellCheckAction(this);
        GetNewTilesAction gnta = new GetNewTilesAction(this);

        if(button.getId() == R.id.playword){
            super.game.sendAction(pwa);
        }
        else if(button.getId() == R.id.shuffle){
            super.game.sendAction(sha);
        }
        else if(button.getId() == R.id.skip){
            super.game.sendAction(ska);
        }
        else if(button.getId() == R.id.spellcheck){
            super.game.sendAction(sca);
        }
        else if(button.getId() == R.id.newtiles){
            super.game.sendAction(gnta);
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
        this.shuffle = (Button)activity.findViewById(R.id.shuffle);
        this.newtiles = (Button)activity.findViewById(R.id.newtiles);
        this.skip = (Button)activity.findViewById(R.id.skip);
        this.spellcheck = (Button)activity.findViewById(R.id.spellcheck);

        playword.setOnClickListener(this);
        shuffle.setOnClickListener(this);
        newtiles.setOnClickListener(this);
        skip.setOnClickListener(this);
        spellcheck.setOnClickListener(this);
    }

    public String getName(){
        return name;
    }
}
