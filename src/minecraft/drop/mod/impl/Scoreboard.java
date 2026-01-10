package drop.mod.impl;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import drop.gui.GuiDC;
import drop.gui.GuiSettings;
import drop.gui.hud.ScreenPosition;
import drop.mod.ModColor;
import drop.mod.ModDraggable;
import drop.mod.option.ParentOption;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.ColorOption;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.EnumChatFormatting;

public class Scoreboard extends ModDraggable {
	public Scoreboard() {
		super(false, 0.5, 0.5);
		
		saveOptions(
				new BooleanOption(this, "hide", false, new GuiSettings(5, "Hide")),
				new BooleanOption(this, "hideNumbers", true, new ParentOption("hide", true), new GuiSettings(1, "Hide Numbers")),
				new ColorOption(this, "numbersColor", ModColor.fromRGB(255, 85, 85, false), new ParentOption("hideNumbers", true), new GuiSettings(6, "Numbers Color", true, false)),
				new BooleanOption(this, "textShadow", false, new ParentOption("hide", true), new GuiSettings(2, "Text Shadow")),
				new ColorOption(this, "backgroundColor", ModColor.fromRGB(0, 0, 0, 127, false), new ParentOption("hide", true), new GuiSettings(3, "Background Color", false, true)),
				new ColorOption(this, "topBackgroundColor", ModColor.fromRGB(0, 0, 0, 127, false), new ParentOption("hide", true), new GuiSettings(4, "Top Background Color", false, true))
				);
	}
	
	private String dummyTitle = EnumChatFormatting.BOLD + "DROP CLIENT";
	private List<String> lines = Arrays.asList(" ", "A PvP client for", "Minecraft 1.8.9.", " ");

	@Override
	public int getWidth() {
		int width = font.getStringWidth(dummyTitle);
		
		int i = 0;
		
		for (String line : lines) {
			i++;
			
			width = Math.max(width, font.getStringWidth(line + (getBooleanOption("hideNumbers").isToggled() ? "" : "   " + i)));
		}
		
		return !getBooleanOption("hide").isToggled() ? width + (getBooleanOption("hideNumbers").isToggled() ? 2 : 0) : 0;
	}

	@Override
	public int getHeight() {
		return !getBooleanOption("hide").isToggled() ? 1 + 2 + lines.size() * (font.FONT_HEIGHT + 2) : 0;
	}
	
	@Override
	public void render(ScreenPosition pos) {
		if (!getBooleanOption("hide").isToggled() && mc.theWorld.getScoreboard() != null) {
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

	            renderScoreboard(scoreObjective, scaledResolution, pos);
	        }
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if (!getBooleanOption("hide").isToggled()) {
			getBounds().fill(getColorOption("backgroundColor").getColor().getRGB());
			
			drawText(dummyTitle, pos.getAbsoluteX() + getWidth() / 2 - font.getStringWidth(dummyTitle) / 2, pos.getAbsoluteY() + 1, Color.WHITE.getRGB(), getBooleanOption("textShadow").isToggled(), false);
			
			int i = 0;
			
			for (String line : lines) {
				i++;
							
				drawText(line, pos.getAbsoluteX() + 2, pos.getAbsoluteY() + i * font.FONT_HEIGHT + 2, Color.WHITE.getRGB(), getBooleanOption("textShadow").isToggled(), false);

				if (!getBooleanOption("hideNumbers").isToggled()) {
					drawText(String.valueOf(i), pos.getAbsoluteX() + getWidth() - font.getStringWidth(String.valueOf(i)), pos.getAbsoluteY() + i * font.FONT_HEIGHT + 2, getColorOption("numbersColor").getColor(), getBooleanOption("textShadow").isToggled());
				}
			}
		}
	}

	public void renderScoreboard(ScoreObjective scoreObjective, ScaledResolution scaledResolution, ScreenPosition pos) {
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
            String lineText = ScorePlayerTeam.formatPlayerName(scorePlayerTeam, score.getPlayerName()) + (getBooleanOption("hideNumbers").isToggled() ? "" : ": " + EnumChatFormatting.RED + score.getScorePoints());
            
            i = Math.max(i, font.getStringWidth(lineText));
        }

        int i1 = collection.size() * font.FONT_HEIGHT;
        int j1 = pos.getAbsoluteY() + i1 + 10;
        int k1 = 3;
        int l1;
        
        if (pos.getRelativeX() < 1.0/3.0) {
            l1 = pos.getAbsoluteX() + 2;
        } else if (pos.getRelativeX() > 2.0/3.0) {
            l1 = pos.getAbsoluteX() + getWidth() - i;
        } else {
            l1 = pos.getAbsoluteX() + getWidth() / 2 - i / 2;
        }
        
        int j = 0;

        for (Score score : collection) {
        	++j;
        	
            int k = j1 - j * font.FONT_HEIGHT;
            int l = l1 + i;
            
            GuiDC.drawRect(l1 - 2, k, l, k + font.FONT_HEIGHT, getColorOption("backgroundColor").getColor().getRGB());
            
            ScorePlayerTeam scorePlayerTeam = scoreboard.getPlayersTeam(score.getPlayerName());
            String title = ScorePlayerTeam.formatPlayerName(scorePlayerTeam, score.getPlayerName());

            font.drawString(title, l1, k, 553648127, getBooleanOption("textShadow").isToggled());

            if (!getBooleanOption("hideNumbers").isToggled()) {
                String number = String.valueOf(score.getScorePoints());

                drawText(number, l - font.getStringWidth(number), k, getColorOption("numbersColor").getColor(), getBooleanOption("textShadow").isToggled());
            }

            if (j == collection.size()) {
                String content = scoreObjective.getDisplayName();
                
                GuiDC.drawRect(l1 - 2 , k - font.FONT_HEIGHT - 1, l, k - 1, getColorOption("topBackgroundColor").getColor().getRGB());
                GuiDC.drawRect(l1 - 2, k - 1, l, k, getColorOption("topBackgroundColor").getColor().getRGB());
                
                font.drawString(content, l1 + i / 2 - font.getStringWidth(content) / 2, k - font.FONT_HEIGHT, 553648127, getBooleanOption("textShadow").isToggled());
            }
        }
    }
}
