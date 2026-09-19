package com.errormod;

import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * Регистрация блоков мода Error.
 */
public final class ErrorBlocks {
	/**
	 * Блок «Error» — светящийся глитч-блок, который можно добывать киркой.
	 */
	public static final Block ERROR_BLOCK = register(
			"error_block",
			Block::new,
			BlockBehaviour.Properties.of()
					.strength(3.0F, 9.0F)
					.lightLevel(state -> 10)
					.requiresCorrectToolForDrops()
					.sound(SoundType.AMETHYST)
	);

	private ErrorBlocks() {
	}

	/**
	 * Регистрирует блок вместе с его предметом (BlockItem).
	 *
	 * @param name         путь идентификатора, например {@code "error_block"}
	 * @param blockFactory конструктор блока
	 * @param properties   свойства блока
	 * @return созданный и зарегистрированный блок
	 */
	private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
		Identifier id = ErrorMod.id(name);
		ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);

		// Создаём блок и регистрируем его.
		Block block = blockFactory.apply(properties.setId(blockKey));
		Registry.register(BuiltInRegistries.BLOCK, blockKey, block);

		// Создаём предмет для блока и регистрируем его.
		BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey));
		Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);

		return block;
	}

	/**
	 * Пустой метод, вызов которого запускает статическую инициализацию класса,
	 * то есть регистрацию всех блоков.
	 */
	public static void initialize() {
		ErrorMod.LOGGER.info("[Error] Блоки зарегистрированы.");
	}
}
