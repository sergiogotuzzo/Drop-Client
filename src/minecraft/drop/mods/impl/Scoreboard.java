package drop.mods.impl;

import java.awt.Color;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiScoreboard;
import drop.mods.ModDraggable;
import drop.mods.hud.ScreenPosition;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.EnumChatFormatting;

public class Scoreboard extends ModDraggable {
	private boolean hideNumbers = false;
	private boolean textShadow = false;
	private ColorManager backgroundColor = ColorManager.fromRGB(0, 0, 0, 127, false);
	
	public Scoreboard() {
		super(true, 0.5, 0.5);
		
		setHideNumbers(getBooleanFromFile("hideNumbers", textShadow));
		setTextShadow(getBooleanFromFile("textShadow", textShadow));
		setBackgroundColor(getIntFromFile("backgroundColor", backgroundColor.getRGB()));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiScoreboard(previousGuiScreen);
	}
	
	private int listHeight;
	private int listWidth;

	@Override
	public int getWidth() {
		return listWidth;
	}

	@Override
	public int getHeight() {
		return listHeight;
	}
	
	@Override
	public void render(ScreenPosition pos) {
		if (mc.theWorld.getScoreboard() != null) {
			net.minecraft.scoreboard.Scoreboard scoreboard = this.mc.theWorld.getScoreboard();
			ScoreObjective scoreObjective = null;
			ScorePlayerTeam scorePlayerTeam = scoreboard.getPlayersTeam(this.mc.thePlayer.getName());
	        
	        if (scorePlayerTeam != null) {
	            int colorIndex = scorePlayerTeam.getChatFormat().getColorIndex();

	            if (colorIndex >= 0) {
	                scoreObjective = scoreboard.getObjectiveInDisplaySlot(3 + colorIndex);
	            }
	        }
	        
	        if (scoreObjective == null) {
	        	scoreObjective = scoreboard.getObjectiveInDisplaySlot(1);
	        }
	        
	        if (scoreObjective != null) {
		        ScaledResolution scaledResolution = new ScaledResolution(this.mc);

	            renderScoreboard(scoreObjective, scaledResolution);
	        }
		}
	}

	public void renderScoreboard(ScoreObjective scoreObjective, ScaledResolution scaledResolution) {
        net.minecraft.scoreboard.Scoreboard scoreboard = scoreObjective.getScoreboard();
        Collection<Score> collection = scoreboard.getSortedScores(scoreObjective);
        List<Score> list = Lists.newArrayList(Iterables.filter(collection, new Predicate<Score>() {
            public boolean apply(Score p_apply_1_) {
                return p_apply_1_.getPlayerName() != null && !p_apply_1_.getPlayerName().startsWith("#");
            }
        }));

        if (list.size() > 15) {
            collection = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
        } else {
            collection = list;
        }

        int i = font.getStringWidth(scoreObjective.getDisplayName());

        for (Score score : collection) {
            ScorePlayerTeam scorePlayerTeam = scoreboard.getPlayersTeam(score.getPlayerName());
            String lineText = ScorePlayerTeam.formatPlayerName(scorePlayerTeam, score.getPlayerName()) + (hideNumbers ? "" : ": " + EnumChatFormatting.RED + score.getScorePoints());
            
            i = Math.max(i, font.getStringWidth(lineText));
        }

        int i1 = collection.size() * font.FONT_HEIGHT;
        int j1 = pos.getAbsoluteY() + i1 + 10;
        int k1 = 3;
        int l1 = pos.getAbsoluteX() - i + i - k1 + 5;
        int j = 0;

        for (Score score : collection) {
        	++j;
        	
            int k = j1 - j * font.FONT_HEIGHT;
            int l = pos.getAbsoluteX() - k1 + 2 + i;
            
            drawRect(l1 - 2, k, l, k + font.FONT_HEIGHT, backgroundColor.getRGB());
            
            ScorePlayerTeam scorePlayerTeam = scoreboard.getPlayersTeam(score.getPlayerName());
            String title = ScorePlayerTeam.formatPlayerName(scorePlayerTeam, score.getPlayerName());

            font.drawString(title, l1, k, 553648127, textShadow);

            if (!hideNumbers) {
                String number = EnumChatFormatting.RED + "" + score.getScorePoints();

                font.drawString(number, l - font.getStringWidth(number), k, 553648127, textShadow);
            }
            
            listHeight = i1 + 10;
            listWidth = (l) - (l1 - 2);

            if (j == collection.size()) {
                String content = scoreObjective.getDisplayName();
                
                drawRect(l1 - 2 , k - font.FONT_HEIGHT - 1, l, k - 1, backgroundColor.getRGB());
                drawRect(l1 - 2, k - 1, l, k, backgroundColor.getRGB());
                
                font.drawString(content, l1 + i / 2 - font.getStringWidth(content) / 2, k - font.FONT_HEIGHT, 553648127, textShadow);
            }
        }
    }
	
	public void setHideNumbers(boolean toggled) {
		hideNumbers = toggled;
		
		setToFile("hideNumbers", toggled);
	}
	
	public boolean isHideNumbersToggled() {
		return hideNumbers;
	}
	
	public void setTextShadow(boolean toggled) {
		textShadow = toggled;
		
		setToFile("textShadow", toggled);
	}
	
	public boolean isTextShadowToggled() {
		return textShadow;
	}
	
	public void setBackgroundColor(int rgb) {
		backgroundColor = backgroundColor.fromRGB(rgb, false);
		
		setToFile("backgroundColor", rgb);
	}
	
	public ColorManager getBackgroundColor() {
		return backgroundColor;
	}
}
