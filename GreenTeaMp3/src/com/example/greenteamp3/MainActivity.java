package com.example.greenteamp3;

import java.util.ArrayList;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	GridView gv;
	GridAdapter MyNewAdapter;
	MediaPlayer mp = new MediaPlayer();
	ArrayList<Item> PlayThisList = new ArrayList<Item>();
	ArrayList<Item> gridList = new ArrayList<Item>();
	
	Button btnPlayPause, btnPrev, btnNext, btnShuffle, btnAlbum;
	Button btnFontSize, btnColorFont, btnColorBack, btnPlayed;
	Button btnSongs, btnGenre, btnArtists, btnPlayList, btnFolders;
	

	View relSettings;
	Handler mHandler = new Handler();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View main = (RelativeLayout) findViewById(R.id.main);
		main.setBackgroundColor(Color.parseColor(ColorWheel.getColorById(0)));
        
        relSettings = (RelativeLayout) findViewById(R.id.Settings);
        relSettings.setVisibility(-1);
        
        gv = (GridView) findViewById(R.id.gridView1);
        btnPlayPause = (Button) findViewById(R.id.buttonPlayPause);
        btnNext = (Button) findViewById(R.id.buttonNext);
        btnPrev = (Button) findViewById(R.id.buttonPrev);
        btnShuffle = (Button) findViewById(R.id.buttonShuffle);
        btnAlbum = (Button) findViewById(R.id.buttonRedBall);
        
        btnFontSize = (Button) findViewById(R.id.buttonFontSize);
        btnColorFont = (Button) findViewById(R.id.buttonFontColor);
        btnColorBack = (Button) findViewById(R.id.buttonBackColor);
        btnPlayed = (Button) findViewById(R.id.buttonPlayed);
        
        btnPlayList = (Button) findViewById(R.id.buttonLists);
        btnSongs = (Button) findViewById(R.id.buttonSongs);
        btnGenre = (Button) findViewById(R.id.buttonGenre);
        btnArtists = (Button) findViewById(R.id.buttonArtists);
        btnFolders = (Button) findViewById(R.id.buttonFolders);
        
        if(Query.ShuffleGET(this))
    		btnShuffle.setBackgroundResource(R.drawable.shuffle_on);
        
        Query.rootSDcardPUT(getApplicationContext());
        FillGrid(Query.Mp3PathInitiate(this));
        
        
        
        
        
        
    }

    //methods
    
    private void FillGrid(ArrayList<Item> adp) {
    	gridList=adp;
    	MyNewAdapter = new GridAdapter(this, adp);
    	String aa = ColorWheel.FontColorString();
    	String[] tw = aa.split(",");
    	int colorINDEX = ColorWheel.FontColorStrIndexGET(this);
    	
    	MyNewAdapter.setFontColor(ColorWheel.getColorById(Integer.parseInt(tw[colorINDEX])));
   		MyNewAdapter.setFontSize(ColorWheel.FontSizeGET(getApplicationContext()));
		MyNewAdapter.setPadding(5);
		gv.setAdapter(MyNewAdapter);
    }
    
    private void CloseRelFolders(int timeInt){
    	relSettings.setVisibility(0);
		mHandler.postDelayed(new Runnable() {
			public void run() {
				relSettings.setVisibility(-1);
			}
		}, timeInt * 1000);
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent it;
		switch (item.getItemId()) {
		case R.id.menu_settings:
			CloseRelFolders(10);
			return true;

//		case R.id.menu_Tips:
//			it = new Intent(getBaseContext(), Tips.class);
//			startActivity(it);
//			return true;
			
		}
		return false;
	}
    
    
    public static boolean sdCardPresent () {
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }
    
    private void logo(Object message){
    	Log.i("reid", String.valueOf(message));
    }
    
    private void tms(Object message){
    	Toast.makeText(	this, String.valueOf(message), Toast.LENGTH_SHORT).show();
    }
    
    private void tm(Object message){
    	Toast.makeText(	this, String.valueOf(message), Toast.LENGTH_LONG).show();
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
