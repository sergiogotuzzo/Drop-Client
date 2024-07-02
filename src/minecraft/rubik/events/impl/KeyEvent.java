package rubik.events.impl;

import rubik.events.EventCancelable;

public class KeyEvent extends EventCancelable {
	private final int key;
	
	public KeyEvent(int key) {
		this.key = key;
	}
	
	public int getKey() {
		return key;
	}
}
