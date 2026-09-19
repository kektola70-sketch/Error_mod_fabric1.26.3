package com.errormod;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Главная точка входа мода Error.
 *
 * <p>Мод добавляет «глитч»-контент: блок Error, осколок ошибки,
 * жезл ошибки и собственную вкладку в креативном инвентаре.
 */
public class ErrorMod implements ModInitializer {
	/** Идентификатор мода. Используется как namespace для всех ресурсов. */
	public static final String MOD_ID = "error";

	/**
	 * Логгер пишет текст в консоль и в файл лога.
	 * Хорошая практика — использовать modid в качестве имени логгера,
	 * чтобы было понятно, какой мод оставил сообщение.
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Этот код выполняется, как только Minecraft готов к загрузке модов.
		// Часть вещей (например, ресурсы) на этом этапе ещё не инициализирована.

		LOGGER.info("[Error] Инициализация мода Error для Minecraft 26.3 (1.26.3)...");

		ErrorBlocks.initialize();
		ErrorItems.initialize();

		LOGGER.info("[Error] Мод Error успешно загружен. ERROR_CODE=0x1F4");
	}

	/**
	 * Создаёт {@link Identifier} в пространстве имён мода.
	 *
	 * @param path путь ресурса, например {@code "error_block"}
	 * @return идентификатор вида {@code error:error_block}
	 */
	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
