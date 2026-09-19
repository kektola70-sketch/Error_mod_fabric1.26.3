package com.errormod.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

/**
 * «Жезл ошибки».
 *
 * <p>При использовании воспроизводит глитч-эффект: частицы, звук,
 * кратковременные эффекты и сообщение в чате в стиле системной ошибки.
 */
public class ErrorWandItem extends Item {
	public ErrorWandItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player user, InteractionHand hand) {
		// Всю игровую логику выполняем только на сервере.
		if (level instanceof ServerLevel serverLevel) {
			// Частицы вокруг игрока.
			serverLevel.sendParticles(
					ParticleTypes.PORTAL,
					user.getX(), user.getY() + 1.0D, user.getZ(),
					60, 0.6D, 0.8D, 0.6D, 0.15D
			);
			serverLevel.sendParticles(
					ParticleTypes.ELECTRIC_SPARK,
					user.getX(), user.getY() + 1.0D, user.getZ(),
					30, 0.5D, 0.7D, 0.5D, 0.1D
			);

			// Звук «сбоя».
			level.playSound(null, user.blockPosition(), SoundEvents.BEACON_DEACTIVATE, SoundSource.PLAYERS, 0.8F, 1.6F);

			// Кратковременные «глитч»-эффекты.
			user.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0));
			user.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 60, 0));

			// Сообщение в стиле системной ошибки.
			user.sendSystemMessage(
					Component.literal("[ERROR] ").withStyle(ChatFormatting.DARK_RED)
							.append(Component.translatable("message.error.wand_used").withStyle(ChatFormatting.RED))
			);
		}

		// Кулдаун задаётся через Item.Properties#useCooldown при регистрации предмета.
		return InteractionResult.SUCCESS;
	}
}
