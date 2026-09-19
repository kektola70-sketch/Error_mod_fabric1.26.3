package com.errormod.client;

import net.fabricmc.api.ClientModInitializer;

import com.errormod.ErrorMod;

/**
 * Клиентская точка входа мода Error.
 *
 * <p>Здесь размещается логика, относящаяся только к клиенту:
 * рендеринг, горячие клавиши, экраны и т. п.
 */
public class ErrorModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ErrorMod.LOGGER.info("[Error] Клиентская часть мода Error инициализирована.");
	}
}
