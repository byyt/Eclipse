package com.example.greenteamp3;

public class Item {
	public int icon;
	public String show1;
	public String show2;
	public String option1;
	public String option2;
	
	public String title;
	public long albumId;
	public long id;
	
	public String filePath;
	public String trackName;
	public int duration;

	public String album;
	public String genre;
	public String artist;
	
	// default constructor
	public Item() {

	}
	
	public Item(Integer icon, String show1, String show2) {
		this.icon = icon;
		this.show1 = show1;
		this.show2 = show2;
	}

	@Override
	public String toString() {
		return show1;
	}
}