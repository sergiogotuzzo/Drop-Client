package drop.gui;

import net.minecraft.client.gui.Gui;

public class GuiDC {
	public static void drawRect(int left, int top, int right, int bottom, int color) {
		Gui.drawRect(left, top, right, bottom, color);
	}
	
	public static void drawHorizontalLine(int startX, int endX, int y, int color) {
        if (endX < startX) {
            int i = startX;
            
            startX = endX;
            endX = i;
        }

        drawRect(startX, y, endX + 1, y + 1, color);
    }

	public static void drawVerticalLine(int x, int startY, int endY, int color) {
        if (endY < startY) {
            int i = startY;
            
            startY = endY;
            endY = i;
        }

        drawRect(x, startY + 1, x + 1, endY, color);
    }
	
	public static void drawHollowRect(int x, int y, int width, int height, int color) {
		drawHorizontalLine(x, x + width, y, color);
		drawHorizontalLine(x, x + width, y + height, color);
		drawVerticalLine(x, y + height, y, color);
		drawVerticalLine(x + width, y + height, y, color);
	}
}
