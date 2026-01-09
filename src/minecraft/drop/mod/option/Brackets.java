package drop.mod.option;

public enum Brackets {
    NONE(0, "", ""),
    ROUND(1, "(", ")"),
    SQUARE(2, "[", "]"),
    CURLY(3, "{", "}"),
	ANGULAR(4, "<", ">");

    private final int id;
    private final String open;
    private final String close;

    private Brackets(int id, String open, String close) {
        this.id = id;
        this.open = open;
        this.close = close;
    }

    public int getId() {
        return id;
    }

    public String getOpen() {
        return open;
    }

    public String getClose() {
        return close;
    }

    public String wrap(String text) {
        return open + text + close;
    }
    
    public String getName() {
    	return id == Brackets.NONE.getId() ? "None" : open + close;
    }
    
    public static Brackets fromId(int id) {
        for (Brackets b : values()) {
            if (b.id == id) {
            	return b;
            }
        }
        
        return NONE;
    }
}