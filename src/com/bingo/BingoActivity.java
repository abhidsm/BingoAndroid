package com.bingo;

import android.app.Activity;
import android.os.Bundle;

public class BingoActivity extends Activity {
    /** Called when the activity is first created. */
	private Game game;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new Game(this);
        setContentView(game);
    }
}