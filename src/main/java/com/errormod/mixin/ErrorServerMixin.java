package com.errormod.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.errormod.ErrorMod;

/**
 * Пример серверного миксина: выводит сообщение при загрузке мира.
 */
@Mixin(MinecraftServer.class)
public class ErrorServerMixin {
	@Inject(at = @At("HEAD"), method = "loadLevel")
	private void error$onLoadLevel(CallbackInfo info) {
		// Код внедряется в начало MinecraftServer.loadLevel()V
		ErrorMod.LOGGER.info("[Error] Загрузка мира — мод Error активен.");
	}
}
