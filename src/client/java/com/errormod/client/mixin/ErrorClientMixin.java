package com.errormod.client.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.errormod.ErrorMod;

/**
 * Пример клиентского миксина: срабатывает при запуске клиента.
 */
@Mixin(Minecraft.class)
public class ErrorClientMixin {
	@Inject(at = @At("HEAD"), method = "run")
	private void error$onRun(CallbackInfo info) {
		// Код внедряется в начало Minecraft.run()V
		ErrorMod.LOGGER.info("[Error] Клиент Minecraft запускается с модом Error.");
	}
}
