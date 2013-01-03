package com.example.greenteamp3;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

public class Query {

	public static ArrayList<Item> Mp3PathInitiate(Context context) {
    	PathPUT(context, rootSDcardGET(context));
		ArrayList<Item> musicList = new ArrayList<Item>();
    	System.gc();
		
    	String[] proj = { 
    			MediaStore.Audio.Media.DATA,
    			MediaStore.Audio.Media.DISPLAY_NAME,
    			MediaStore.Audio.Media.TITLE, //?
    			MediaStore.Audio.Media._ID,//?
    			MediaStore.Audio.Media.ALBUM_ID};//?
    	
    	Cursor mediaCursor = context.getContentResolver().query(MediaStore.Audio
       			.Media.EXTERNAL_CONTENT_URI, proj, null, null, null);
    	String mp3Q;
    	String curFolder = "";
    	String myPath = null;
    	String[] folders;
    	String basePath = "";
    	int count = 0;
    	
    	if (mediaCursor.moveToFirst()) {
    		
    		do {
    		
    			mp3Q = (mediaCursor.getString(mediaCursor
    					.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
    			
    			if(mp3Q.toLowerCase().endsWith(".mp3")){
    				myPath = mediaCursor.getString(mediaCursor
        					.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
        			
    				folders = myPath.split("/");
    				
    				if(!curFolder.equals(folders[3])){
    					Item item = new Item();	
    					item.icon = R.drawable.musifolderlav70;
    					item.title =  mediaCursor.getString(mediaCursor
            					.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
    					item.filePath = "";
    					item.id = mediaCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
    					item.option1=folders[3];
    					item.show1 = folders[3];
    					item.show2 = Integer.toString(count)+" songs";
            			curFolder = folders[3];
            			basePath=basePath + folders[3] + "/";
            			musicList.add(item);
    				}
    			}
    			
    		} while(mediaCursor.moveToNext());
    	}
    	
    	
    	BasePathPUT(context, basePath);
		return AlphabetizeShow1(musicList);
	}
	
	private static ArrayList<Item> AlphabetizeShow1(ArrayList<Item> arraylist){
    	Item[] listMatters = new Item[arraylist.size()];
    	for (int i = 0; i < listMatters.length; i ++) {
    		listMatters[i] = arraylist.get(i);
    	}
    	
    	for (int i = 0; i < listMatters.length; i++) {
			for (int j = i; j < listMatters.length; j++) {
				if (listMatters[i].show1.compareTo(listMatters[j].show1) > 0) {
					Item swap = listMatters[i];
					listMatters[i] = listMatters[j];
					listMatters[j] = swap;
				}
			}
    	};
    	
    	ArrayList<Item> ret = new ArrayList<Item>();
    	for (int i = 0; i < listMatters.length; i ++) {
    		ret.add(listMatters[i]);
    	}
    	return ret;
    }
	
	public static void BasePathPUT(Context ctx, String mp3Path) {
		String aa = ctx.getString(R.string.app_name);
		SharedPreferences keyValues;
		keyValues = ctx.getSharedPreferences(aa, Context.MODE_PRIVATE);
		SharedPreferences.Editor keyValuesEditor = keyValues.edit();
		keyValuesEditor.putString("pathB", mp3Path);
		keyValuesEditor.commit();
	}
	
	public static String BasePathGET(Context ctx) {
		String aa = ctx.getString(R.string.app_name);
		SharedPreferences keyValues;
		keyValues = ctx.getSharedPreferences(aa, Context.MODE_PRIVATE);
		String mp3Path = null;
		mp3Path = keyValues.getString("pathB", "");
		return mp3Path;
	}
	
	public static void PathPUT(Context ctx, String mp3Path) {
		String aa = ctx.getString(R.string.app_name);
		SharedPreferences keyValues;
		keyValues = ctx.getSharedPreferences(aa, Context.MODE_PRIVATE);
		SharedPreferences.Editor keyValuesEditor = keyValues.edit();
		keyValuesEditor.putString("pathF", mp3Path);
		keyValuesEditor.commit();
	}
	
	public static String PathGET(Context ctx) {
		String aa = ctx.getString(R.string.app_name);
		SharedPreferences keyValues;
		keyValues = ctx.getSharedPreferences(aa, Context.MODE_PRIVATE);
		String mp3Path = null;
		mp3Path = keyValues.getString("pathF", "");
		return mp3Path;
	}
	
	public static void rootSDcardPUT(Context ctx) {
		String sdRoot = Environment.getExternalStorageDirectory() + "";
		String aa = ctx.getString(R.string.app_name);
		logo(sdRoot);
		if(sdRoot.equals("/storgae/sdcard0")) sdRoot="/storgae/extSdCard";
		SharedPreferences keyValues;
		keyValues = ctx.getSharedPreferences(aa, Context.MODE_PRIVATE);
		SharedPreferences.Editor keyValuesEditor = keyValues.edit();
		keyValuesEditor.putString("sdRoot", sdRoot);
		keyValuesEditor.commit();
	}
	
	public static String rootSDcardGET(Context ctx) {
		String aa = ctx.getString(R.string.app_name);
		SharedPreferences keyValues;
		keyValues = ctx.getSharedPreferences(aa, Context.MODE_PRIVATE);
		String mp3Path = null;
		mp3Path = keyValues.getString("sdRoot", "");
		return mp3Path;
	}
	
	
	public static void ShufflePUT(Context ctx, boolean toggle) {
		String aa = ctx.getString(R.string.app_name);
		SharedPreferences keyValues;
		keyValues = ctx.getSharedPreferences(aa, Context.MODE_PRIVATE);
		SharedPreferences.Editor keyValuesEditor = keyValues.edit();
		keyValuesEditor.putBoolean("shuffle", toggle);
		keyValuesEditor.commit();
	}
	
	public static boolean ShuffleGET(Context ctx) {
		String aa = ctx.getString(R.string.app_name);
		SharedPreferences keyValues;
		keyValues = ctx.getSharedPreferences(aa, Context.MODE_PRIVATE);
		boolean values = keyValues.getBoolean("shuffle", false);
		return values;
	}
	
	private static void logo(Object message){
    	Log.i("reid", String.valueOf(message));
    }
    
    private static void tms(Context context, Object message){
    	Toast.makeText(	context, String.valueOf(message), Toast.LENGTH_SHORT).show();
    }
    private static void tm(Context context, Object message){
    	Toast.makeText(	context, String.valueOf(message), Toast.LENGTH_LONG).show();
    }
	
	
}
