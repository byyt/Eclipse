package com.example.greenteamp3;


import java.util.ArrayList;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class ColorWheel {   //color wheel

	public static String FontSizeString(){
		String aa = "25,30,40";
		return aa;
	}
	
	public static void FontSizePUT(Context ctx, int size) {
		String aa = ctx.getString(R.string.app_name);
		
		SharedPreferences keyValues;
		keyValues = ctx.getSharedPreferences(aa, Context.MODE_PRIVATE);
		SharedPreferences.Editor keyValuesEditor = keyValues.edit();
		
		keyValuesEditor.putInt("fontSize", size);
		keyValuesEditor.commit();
	}
	
	public static int FontSizeGET(Context ctx) {
		String aa = ctx.getString(R.string.app_name);
		SharedPreferences keyValues;
		keyValues = ctx.getSharedPreferences(aa, Context.MODE_PRIVATE);
		int values = keyValues.getInt("fontSize", 25);
		return values;
	}
	
	public static void BackColorPUT(Context ctx,int color) {
		String aa = ctx.getString(R.string.app_name);
		
		SharedPreferences keyValues;
		keyValues = ctx.getSharedPreferences(aa, Context.MODE_PRIVATE);
		SharedPreferences.Editor keyValuesEditor = keyValues.edit();
		
		keyValuesEditor.putInt("backColor", color);
		keyValuesEditor.commit();
	}
	
	public static int BackColorGET(Context ctx) {
		String aa = ctx.getString(R.string.app_name);
		SharedPreferences keyValues;
		keyValues = ctx.getSharedPreferences(aa, Context.MODE_PRIVATE);
		int values = keyValues.getInt("backColor", 0);
		return values;
	}
	
	public static void FontColorStrIndexPUT(Context ctx, int color) {
		String aa = ctx.getString(R.string.app_name);
		SharedPreferences keyValues;
		keyValues = ctx.getSharedPreferences(aa, Context.MODE_PRIVATE);
		SharedPreferences.Editor keyValuesEditor = keyValues.edit();
		keyValuesEditor.putInt("fontColor", color);
		keyValuesEditor.commit();
	}
	
	public static int FontColorStrIndexGET(Context ctx) {
		String aa = ctx.getString(R.string.app_name);
		SharedPreferences keyValues;
		keyValues = ctx.getSharedPreferences(aa, Context.MODE_PRIVATE);
		int values = keyValues.getInt("fontColor", 6);
		return values;
	}
	
	
	public static class Color {
		public String colorName;
		public String colorValue;

		Color(String colorName, String colorValue) {
			this.colorName = colorName;
			this.colorValue = colorValue;
		}
	}
	
	
	public static ArrayList<Color> getColors() {
		final ArrayList<Color> colors = new ArrayList<Color>();

		colors.add(new Color("blue", "#1500FF"));//0
		colors.add(new Color("blueL", "#006FFF"));
		colors.add(new Color("blueLL", "#8FBBF4"));
		colors.add(new Color("cyan", "#00D0FF"));
		colors.add(new Color("cyan", "#8FEFF4"));//4
		colors.add(new Color("cyan", "#96F5F8"));
		colors.add(new Color("green", "#1B7806"));//6
		colors.add(new Color("green", "#00FF00"));
		colors.add(new Color("green", "#0DCD36"));
		colors.add(new Color("orange", "#FF3300"));//9
		
		colors.add(new Color("orange", "#FF8C00"));
		colors.add(new Color("orange", "#FF6200"));
		colors.add(new Color("peach", "#F99C62"));
		colors.add(new Color("gold", "#E5B61D"));//13
		colors.add(new Color("brown", "#7F5409"));
		colors.add(new Color("brown", "#BA7A32"));
		colors.add(new Color("gray", "#5C5959"));
		colors.add(new Color("gray", "#A19B99"));
		colors.add(new Color("black", "#0C0B0B"));	//18	
		colors.add(new Color("white", "#FFFFFF"));  //19
		
		colors.add(new Color("red", "#FB0404"));//20
		colors.add(new Color("red", "#FB044F"));
		colors.add(new Color("red", "#F75151"));
		colors.add(new Color("pink", "#F70A71"));
		colors.add(new Color("pink", "#F70AC8"));
		colors.add(new Color("pink", "#F672DC"));//25
		colors.add(new Color("purple", "#BB00FF"));//26
		colors.add(new Color("purple", "#D372F6"));
		colors.add(new Color("purple", "#D69BFB"));		
		colors.add(new Color("purple", "#750DEC"));
		
		colors.add(new Color("olive", "#9DD633"));
		colors.add(new Color("olive", "#CBF381"));
		colors.add(new Color("blue green", "#06785A"));
		colors.add(new Color("blue green", "#46E4BA"));
		colors.add(new Color("yellow", "#FBFF00"));//34
		colors.add(new Color("yellow", "#EAFF00"));
		colors.add(new Color("yellow", "#F4F664"));
		return colors;

	}

	
	public static String FontColorString(){
		String aa = "34,26,25,20,19,18,13,9,6,4,0";
		return aa;
	}
	
	
	public static String getColorById(final int aId) {

		final int length = getColors().size();

		if (aId < 0 || aId >= length) {
			throw new IllegalArgumentException("Id must be between 0 and "
					+ (length - 1));
		}

//		logo(getColors().get(aId).colorValue);
		return getColors().get(aId).colorValue;
	}

	private static void logo(Object message){
    	Log.i("reid", String.valueOf(message));
    }
	
	public static String getColorByName(final String aName) {
		
		final ArrayList<Color> colors = getColors();
		for (final Color color : colors) {
			if (color.colorName.equalsIgnoreCase(aName)) {
				return color.colorValue;
			}
		}

		throw new IllegalArgumentException(
				"Color is not exists in colors array.");
	}

}