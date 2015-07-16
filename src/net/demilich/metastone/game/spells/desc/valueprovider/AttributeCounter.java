package net.demilich.metastone.game.spells.desc.valueprovider;

import java.util.List;

import net.demilich.metastone.game.GameContext;
import net.demilich.metastone.game.GameTag;
import net.demilich.metastone.game.Player;
import net.demilich.metastone.game.entities.Entity;
import net.demilich.metastone.game.targeting.EntityReference;

public class AttributeCounter extends ValueProvider {


	public AttributeCounter(ValueProviderDesc desc) {
		super(desc);
	}

	@Override
	protected int provideValue(GameContext context, Player player, Entity target) {
		EntityReference source = desc.getSource();
		List<Entity> relevantEntities = context.resolveTarget(player, null, source);
		int count = 0;
		GameTag attribute = (GameTag) desc.get(ValueProviderArg.ATTRIBUTE);
		for (Entity entity : relevantEntities) {
			if (entity.hasTag(attribute)) {
				count++;
			}
		}
		int multiplier = desc.contains(ValueProviderArg.MULTIPLIER) ? desc.getInt(ValueProviderArg.MULTIPLIER) : 1;
		return count * multiplier;
	}

}
