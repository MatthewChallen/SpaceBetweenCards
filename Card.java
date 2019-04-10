package com.playdis.cards;

public abstract class Card {
	
	private String name;
	private String description;
	public boolean cardDrawn = false;
	
	public Card (String name, String description) {
		this.name = name;
		this.description = description;
	}
    
	// Methods common to all
	public String getName() {
		return this.name;
	}
	public String getDescription() {
		return this.description;
	}
	public String toString() {
		return this.name + ", " + description;
	}
	
	// Methods to be overridden
	public abstract boolean play(PlayField theField);
	public abstract String getCardFileName();
	public abstract void onPlay();
	public abstract void onDraw();
	public abstract void onDiscard();
	public abstract void update();
}
