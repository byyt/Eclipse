package com.example.greenteamp3.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.example.greenteamp3.ColorWheel;
import com.example.greenteamp3.GridAdapter;
import com.example.greenteamp3.Item;
import com.example.greenteamp3.Query;
import com.example.greenteamp3.R;

public class FoldersFragment extends Fragment implements OnClickListener {

	private static final String TAG = "FoldersFragment";
	
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
	
	public interface TravelFragmentCallback {

	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(TAG, "creating the folders fragment view");
		
		View v = inflater.inflate(R.layout.activity_main, container, false);
		
		View main = (RelativeLayout) v.findViewById(R.id.main);
		main.setBackgroundColor(Color.parseColor(ColorWheel.getColorById(0)));
        
        relSettings = (RelativeLayout) v.findViewById(R.id.Settings);
        relSettings.setVisibility(-1);
        
        gv = (GridView) v.findViewById(R.id.gridView1);
        btnPlayPause = (Button) v.findViewById(R.id.buttonPlayPause);
        btnNext = (Button) v.findViewById(R.id.buttonNext);
        btnPrev = (Button) v.findViewById(R.id.buttonPrev);
        btnShuffle = (Button) v.findViewById(R.id.buttonShuffle);
        btnAlbum = (Button) v.findViewById(R.id.buttonRedBall);
        
        btnFontSize = (Button) v.findViewById(R.id.buttonFontSize);
        btnColorFont = (Button) v.findViewById(R.id.buttonFontColor);
        btnColorBack = (Button) v.findViewById(R.id.buttonBackColor);
        btnPlayed = (Button) v.findViewById(R.id.buttonPlayed);
        
        btnPlayList = (Button) v.findViewById(R.id.buttonLists);
        btnSongs = (Button) v.findViewById(R.id.buttonSongs);
        btnGenre = (Button) v.findViewById(R.id.buttonGenre);
        btnArtists = (Button) v.findViewById(R.id.buttonArtists);
        btnFolders = (Button) v.findViewById(R.id.buttonFolders);
        
        if (Query.ShuffleGET(getActivity()))
    		btnShuffle.setBackgroundResource(R.drawable.shuffle_on);
        
        Query.rootSDcardPUT(getActivity().getApplicationContext());
        FillGrid(Query.Mp3PathInitiate(getActivity()));
		
		return v;
	}
	
	@Override
    public void onResume() {
        super.onResume();      
        
    }
	
	@Override
	public void onClick(View v) {		

	}
	
	private void FillGrid(ArrayList<Item> adp) {
    	gridList = adp;
    	MyNewAdapter = new GridAdapter(getActivity(), adp);
    	String aa = ColorWheel.FontColorString();
    	String[] tw = aa.split(",");
    	int colorINDEX = ColorWheel.FontColorStrIndexGET(getActivity());
    	
    	MyNewAdapter.setFontColor(ColorWheel.getColorById(Integer.parseInt(tw[colorINDEX])));
   		MyNewAdapter.setFontSize(ColorWheel.FontSizeGET(getActivity().getApplicationContext()));
		MyNewAdapter.setPadding(5);
		gv.setAdapter(MyNewAdapter);
    }
    
    private void CloseRelFolders(int timeInt) {
    	relSettings.setVisibility(View.VISIBLE);
		mHandler.postDelayed(new Runnable() {
			public void run() {
				relSettings.setVisibility(View.INVISIBLE);
			}
		}, timeInt * 1000);
    }
}
