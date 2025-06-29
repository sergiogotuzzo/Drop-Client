package drop.event.impl;

import drop.event.Event;
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
