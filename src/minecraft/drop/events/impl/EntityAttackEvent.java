package drop.events.impl;

import drop.events.Event;
import net.minecraft.entity.Entity;

public class EntityAttackEvent extends Event {
	private Entity entity;
	
	public EntityAttackEvent(Entity entityIn) {
		entity = entityIn;
	}
	
	public Entity getEntity() {
		return entity;
	}
}
