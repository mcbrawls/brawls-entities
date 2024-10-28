package net.mcbrawls.entities

import eu.pb4.polymer.core.api.entity.PolymerEntityUtils
import net.mcbrawls.entities.entity.DisplayedModelEntity
import net.mcbrawls.entities.entity.PlayerAttachedTextDisplayEntity
import net.mcbrawls.entities.entity.TemporaryTextDisplayEntity
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

object BrawlsAPIEntities {
    val DISPLAYED_BLOCK = register(
        "displayed_block",
        EntityType.Builder.create(::DisplayedModelEntity, SpawnGroup.MISC)
            .dimensions(0.0f, 0.0f)
            .maxTrackingRange(2)
            .trackingTickInterval(10)
    )

    val TEMPORARY_TEXT_DISPLAY = register(
        "temporary_text_display",
        EntityType.Builder.create(::TemporaryTextDisplayEntity, SpawnGroup.MISC)
            .dimensions(0.0f, 0.0f)
            .disableSaving()
            .maxTrackingRange(2)
            .trackingTickInterval(1)
    )

    val PLAYER_ATTACHED_TEXT_DISPLAY = register(
        "player_attached_text_display",
        EntityType.Builder.create(::PlayerAttachedTextDisplayEntity, SpawnGroup.MISC)
            .dimensions(0.0f, 0.0f)
            .disableSaving()
            .disableSummon()
            .maxTrackingRange(32)
            .trackingTickInterval(10)
    )

    fun <E : Entity> register(id: String, builder: EntityType.Builder<E>): EntityType<E> {
        val key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of("brawls", id))
        val type = builder.build(key)
        PolymerEntityUtils.registerType(type)
        return Registry.register(Registries.ENTITY_TYPE, key, type)
    }
}
