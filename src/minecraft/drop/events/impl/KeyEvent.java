package drop.events.impl;

import drop.events.EventCancelable;

public class KeyEvent extends EventCancelable {
	private final int key;
	
	public KeyEvent(int key) {
		this.key = key;
	}
	
	public int getKey() {
		return key;
	}
}
