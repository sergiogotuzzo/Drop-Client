package drop.events.impl;

import drop.events.Event;
import net.minecraft.entity.Entity;

public class EntityDamageEvent extends Event {
	private Entity entity;
	
	public EntityDamageEvent(Entity entityIn) {
		entity = entityIn;
	}
	
	public Entity getEntity() {
		return entity;
	}
}
