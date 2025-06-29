package drop.mods.impl;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import drop.ColorManager;
import drop.gui.GuiSettings;
import drop.mods.ModDraggable;
import drop.mods.ModOptions;
import drop.gui.hud.ScreenPosition;
import drop.mods.option.ParentOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.ColorOption;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.EnumChatFormatting;

public class Scoreboard extends ModDraggable {
	public Scoreboard() {
		super(true, 0.5, 0.5);
		
		this.options = new ModOptions(
				new BooleanOption(this, "hide", false, new GuiSettings(5, "Hide")),
				new BooleanOption(this, "hideNumbers", true, new ParentOption("hide", true), new GuiSettings(1, "Hide Numbers")),
				new ColorOption(this, "numbersColor", ColorManager.fromRGB(255, 85, 85, false), new ParentOption("hideNumbers", true), new GuiSettings(6, "Numbers Color", true, false)),
				new BooleanOption(this, "textShadow", false, new ParentOption("hide", true), new GuiSettings(2, "Text Shadow")),
				new ColorOption(this, "backgroundColor", ColorManager.fromRGB(0, 0, 0, 127, false), new ParentOption("hide", true), new GuiSettings(3, "Background Color", false, true)),
				new ColorOption(this, "topBackgroundColor", ColorManager.fromRGB(0, 0, 0, 127, false), new ParentOption("hide", true), new GuiSettings(4, "Top Background Color", false, true))
				);
		
		saveOptions();
	}
	
	private String dummyTitle = EnumChatFormatting.BOLD + "DROP CLIENT";
	private List<String> lines = Arrays.asList(" ", "A PvP client for", "Minecraft 1.8.9.", " ");

	@Override
	public int getWidth() {
		int width = font.getStringWidth(dummyTitle);
		
		int i = 0;
		
		for (String line : lines) {
			i++;
			
			width = Math.max(width, font.getStringWidth(line + (options.getBooleanOption("hideNumbers").isToggled() ? "" : "   " + i)));
		}
		
		return !options.getBooleanOption("hide").isToggled() ? width + (options.getBooleanOption("hideNumbers").isToggled() ? 2 : 0) : 0;
	}

	@Override
	public int getHeight() {
		return !options.getBooleanOption("hide").isToggled() ? 1 + 2 + lines.size() * (font.FONT_HEIGHT + 2) : 0;
	}
	
	@Override
	public void render(ScreenPosition pos) {
		if (!options.getBooleanOption("hide").isToggled() && mc.theWorld.getScoreboard() != null) {
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
		if (!options.getBooleanOption("hide").isToggled()) {
			getBounds().fill(options.getColorOption("backgroundColor").getColor());
			
			drawText(dummyTitle, pos.getAbsoluteX() + getWidth() / 2 - font.getStringWidth(dummyTitle) / 2, pos.getAbsoluteY() + 1, Color.WHITE.getRGB(), options.getBooleanOption("textShadow").isToggled(), false);
			
			int i = 0;
			
			for (String line : lines) {
				i++;
							
				drawText(line, pos.getAbsoluteX() + 2, pos.getAbsoluteY() + i * font.FONT_HEIGHT + 2, Color.WHITE.getRGB(), options.getBooleanOption("textShadow").isToggled(), false);

				if (!options.getBooleanOption("hideNumbers").isToggled()) {
					drawText(String.valueOf(i), pos.getAbsoluteX() + getWidth() - font.getStringWidth(String.valueOf(i)), pos.getAbsoluteY() + i * font.FONT_HEIGHT + 2, options.getColorOption("numbersColor").getColor(), options.getBooleanOption("textShadow").isToggled());
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
            String lineText = ScorePlayerTeam.formatPlayerName(scorePlayerTeam, score.getPlayerName()) + (options.getBooleanOption("hideNumbers").isToggled() ? "" : ": " + EnumChatFormatting.RED + score.getScorePoints());
            
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
            
            drawRect(l1 - 2, k, l, k + font.FONT_HEIGHT, options.getColorOption("backgroundColor").getColor().getRGB());
            
            ScorePlayerTeam scorePlayerTeam = scoreboard.getPlayersTeam(score.getPlayerName());
            String title = ScorePlayerTeam.formatPlayerName(scorePlayerTeam, score.getPlayerName());

            font.drawString(title, l1, k, 553648127, options.getBooleanOption("textShadow").isToggled());

            if (!options.getBooleanOption("hideNumbers").isToggled()) {
                String number = String.valueOf(score.getScorePoints());

                drawText(number, l - font.getStringWidth(number), k, options.getColorOption("numbersColor").getColor(), options.getBooleanOption("textShadow").isToggled());
            }

            if (j == collection.size()) {
                String content = scoreObjective.getDisplayName();
                
                drawRect(l1 - 2 , k - font.FONT_HEIGHT - 1, l, k - 1, options.getColorOption("topBackgroundColor").getColor().getRGB());
                drawRect(l1 - 2, k - 1, l, k, options.getColorOption("topBackgroundColor").getColor().getRGB());
                
                font.drawString(content, l1 + i / 2 - font.getStringWidth(content) / 2, k - font.FONT_HEIGHT, 553648127, options.getBooleanOption("textShadow").isToggled());
            }
        }
    }
}
