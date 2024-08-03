package com.sunnylow.notion_clone.model;

public enum TagColor {
	AQUA("#00FFFF"),
	BLACK("#000000"),
	BLUE("#0000FF"),
	FUCHSIA("#FF00FF"),
	GRAY("#808080"),
	GREEN("#008000"),
	LIME("#00FF00"),
	MAROON("#800000"),
	NAVY("#000080"),
	OLIVE("#808000"),
	PURPLE("#800080"),
	RED("#FF0000"),
	SILVER("#C0C0C0"),
	TEAL("#008080"),
	WHITE("#FFFFFF"),
	YELLOW("#FFFF00");

	private final String hexCode;

	TagColor(String hexCode) {
		this.hexCode = hexCode;
	}

	public String getHexCode() {
		return hexCode;
	}
}
