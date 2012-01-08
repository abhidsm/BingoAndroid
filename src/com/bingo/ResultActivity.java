package com.bingo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        
        showResult();
        
        final Button button = (Button) findViewById(R.id.play);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
        		Intent intent = new Intent(ResultActivity.this, BingoActivity.class);
        		startActivity(intent);
            }
        });
    }
    
    private void showResult(){
    	TextView resultView = (TextView)findViewById(R.id.resultMessage); 
    	if(BingoApplication.result == BingoApplication.WIN){
    		resultView.setText("Congrats! You win. Can you win again?");
    	}else if(BingoApplication.result == BingoApplication.FAIL){
    		resultView.setText("Sorry! You loose. Try Again!");
    	}if(BingoApplication.result == BingoApplication.TIE){
    		resultView.setText("Oops! Its a draw. Try Again!");
    	}
    }


}
