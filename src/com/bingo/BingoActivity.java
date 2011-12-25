package com.bingo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BingoActivity extends Activity {
    /** Called when the activity is first created. */
	private Game game;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new Game(this);
        setContentView(game);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.new_game:
            newGame();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    private void newGame(){
        game = new Game(this);
        this.setContentView(game);
    }
}