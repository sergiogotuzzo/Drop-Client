package drop.mods.option;

public class ParentOption {
	private String key;
	private boolean inverse;
	
	public ParentOption(String key, boolean inverse) {
		this.key = key;
		this.inverse = inverse;
	}
	
	public ParentOption(String key) {
		this(key, false);
	}
	
	public String getKey() {
		return key;
	}
	
	public boolean isInverted() {
		return inverse;
	}
}
