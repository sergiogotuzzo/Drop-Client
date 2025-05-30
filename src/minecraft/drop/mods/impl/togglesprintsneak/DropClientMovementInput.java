package drop.mods.impl.togglesprintsneak;

import java.text.DecimalFormat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovementInput;
import drop.mods.ModInstances;

public class DropClientMovementInput extends MovementInput {
	private boolean sprint = false;
	private GameSettings gameSettings;
	private int sneakWasPressed = 0;
	private int sprintWasPressed = 0;
	private EntityPlayerSP player;
	private float originalFlySpeed = -1.0F;
	private float boostedFlySpeed = 0;
	private Minecraft mc;
	
	private static final DecimalFormat df = new DecimalFormat("#.0");
	
	private ToggleSprintSneak toggleSprintSneakMod = ModInstances.getToggleSprintSneakMod();
	
	public DropClientMovementInput(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
		this.mc = Minecraft.getMinecraft();
		this.sprint = toggleSprintSneakMod.isSprinting();
		this.sneak = toggleSprintSneakMod.isSneaking();
	}
	
	@Override
	public void updatePlayerMoveState() {
		player = mc.thePlayer;
		moveStrafe = 0.0F;
		moveForward = 0.0F;
		
		if (gameSettings.keyBindForward.isKeyDown()) {
			moveForward++;
		}
		
		if (gameSettings.keyBindBack.isKeyDown()) {
			moveForward--;
		}
		
		if (gameSettings.keyBindLeft.isKeyDown()) {
			moveStrafe++;
		}
		
		if (gameSettings.keyBindRight.isKeyDown()) {
			moveStrafe--;
		}
		
		jump = gameSettings.keyBindJump.isKeyDown();
		
		if (toggleSprintSneakMod.isEnabled() && toggleSprintSneakMod.isToggleSneakEnabled()) {
			if (gameSettings.keyBindSneak.isKeyDown()) {
				if (sneakWasPressed == 0) {
					if (sneak) {
						sneakWasPressed = -1;
					} else if (player.isRiding() || player.capabilities.isFlying) {
						sneakWasPressed = toggleSprintSneakMod.keyHoldTicks + 1;
					} else {
						sneakWasPressed = 1;
					}
					
					sneak = !sneak;
				} else if (sneakWasPressed > 0) {
					sneakWasPressed++;
				}
			} else {
				if ((toggleSprintSneakMod.keyHoldTicks > 0) && (sneakWasPressed > toggleSprintSneakMod.keyHoldTicks)) {
					sneak = false;
				}
				
				sneakWasPressed = 0;
			}
		} else {
			sneak = gameSettings.keyBindSneak.isKeyDown();
		}
		
		if (sneak) {
			moveStrafe *= 0.3F;
			moveForward *= 0.3F;
		}
		
		toggleSprintSneakMod.setSneaking(sneak);
		
		if (toggleSprintSneakMod.isEnabled() && toggleSprintSneakMod.isToggleSprintEnabled()) {
			if (gameSettings.keyBindSprint.isKeyDown() && !player.capabilities.isFlying) {
				if (sprintWasPressed == 0) {
					if (sprint) {
						sprintWasPressed = -1;
					} else if (player.capabilities.isFlying) {
						sprintWasPressed = toggleSprintSneakMod.keyHoldTicks + 1;
					} else {
						sprintWasPressed = 1;
					}
					
					sprint = !sprint;
				} else if (sprintWasPressed > 0) {
					sprintWasPressed++;
				}
			} else {
				if ((toggleSprintSneakMod.keyHoldTicks > 0) && (sprintWasPressed > toggleSprintSneakMod.keyHoldTicks)) {
					sprint = false;
				}
				
				sprintWasPressed = 0;
			}
		} else {
			sprint = false;
		}
		
		if (sprint && moveForward == 1.0F && player.onGround && !player.isUsingItem() && !player.isPotionActive(Potion.blindness)) {
			player.setSprinting(true);
		}
		
		toggleSprintSneakMod.setSprinting(sprint);
		
		if (toggleSprintSneakMod.isFlyBoostEnabled() && player.capabilities.isCreativeMode && player.capabilities.isFlying && (mc.getRenderViewEntity() == player) && mc.gameSettings.keyBindSprint.isKeyDown()) {
			if (originalFlySpeed < 0.0F || this.player.capabilities.getFlySpeed() != boostedFlySpeed) {
				originalFlySpeed = this.player.capabilities.getFlySpeed();
			}
			
			boostedFlySpeed = originalFlySpeed * toggleSprintSneakMod.getFlyBoostFactor();
			
			player.capabilities.setFlySpeed(boostedFlySpeed);
			
			if (sneak) {
				player.motionY -= 0.15D * (double) (toggleSprintSneakMod.getFlyBoostFactor() - 1.0F);
			}
			
			if (jump) {
				player.motionY += 0.15D * (double) (toggleSprintSneakMod.getFlyBoostFactor() - 1.0F);
			}
		} else {
			if (player.capabilities.getFlySpeed() == boostedFlySpeed) {
				this.player.capabilities.setFlySpeed(originalFlySpeed);
			}
			
			originalFlySpeed = -1.0F;
		}
	}
	
	public String getDisplayText() {
		String displayText = "";
		
		boolean isFlying = mc.thePlayer.capabilities.isFlying;
		boolean isRiding = mc.thePlayer.isRiding();
		boolean isHoldingSneak = gameSettings.keyBindSneak.isKeyDown();
		boolean isHoldingSprint = gameSettings.keyBindSprint.isKeyDown();
		
		String spacing = "  ";
		
		if (isFlying) {
			if (originalFlySpeed > 0.0F) {
				displayText += toggleSprintSneakMod.getBrackets().wrap("Flying (" + df.format(boostedFlySpeed / originalFlySpeed) + "x Boost)") + spacing;
			} else {
				displayText += toggleSprintSneakMod.getBrackets().wrap("Flying") + spacing;
			}
		}
		
		if (isRiding) {
			displayText += toggleSprintSneakMod.getBrackets().wrap("Riding") + spacing;
		}
		
		if (sneak) {
			if (isFlying) {
				displayText += toggleSprintSneakMod.getBrackets().wrap("Descending") + spacing;
			} else if (isRiding) {
				displayText += toggleSprintSneakMod.getBrackets().wrap("Dismounting") + spacing;
			} else if (isHoldingSneak) {
				displayText += toggleSprintSneakMod.getBrackets().wrap("Sneaking (Key Held)") + spacing;
			} else {
			}
		} else if (sprint && !isFlying && !isRiding) {
			if (isHoldingSprint) {
				displayText += toggleSprintSneakMod.getBrackets().wrap("Sprinting (Key Held)") + spacing;
			} else {
				displayText += toggleSprintSneakMod.getBrackets().wrap("Sprinting (Toggled)") + spacing;
			}
		}
		
		return displayText.trim();
	}
}
