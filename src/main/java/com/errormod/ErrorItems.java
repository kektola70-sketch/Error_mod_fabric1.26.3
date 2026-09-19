package com.errormod;

import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;

import com.errormod.item.ErrorWandItem;

/**
 * Регистрация предметов и креативной вкладки мода Error.
 */
public final class ErrorItems {
	/** «Осколок ошибки» — базовый ресурс мода. */
	public static final Item ERROR_FRAGMENT = register(
			"error_fragment",
			Item::new,
			new Item.Properties().rarity(Rarity.UNCOMMON)
	);

	/** «Жезл ошибки» — при использовании вызывает глитч-эффект. */
	public static final Item ERROR_WAND = register(
			"error_wand",
			ErrorWandItem::new,
			new Item.Properties()
					.stacksTo(1)
					.rarity(Rarity.EPIC)
					.useCooldown(3.0F)
	);

	/** Ключ креативной вкладки мода. */
	public static final ResourceKey<CreativeModeTab> ERROR_TAB_KEY =
			ResourceKey.create(Registries.CREATIVE_MODE_TAB, ErrorMod.id("error_tab"));

	private ErrorItems() {
	}

	/**
	 * Регистрирует предмет в реестре предметов.
	 *
	 * @param name        путь идентификатора, например {@code "error_fragment"}
	 * @param itemFactory конструктор предмета
	 * @param properties  свойства предмета
	 * @return созданный и зарегистрированный предмет
	 */
	private static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, ErrorMod.id(name));

		Item item = itemFactory.apply(properties.setId(itemKey));
		Registry.register(BuiltInRegistries.ITEM, itemKey, item);

		return item;
	}

	/**
	 * Статическая инициализация предметов, регистрация креативной вкладки
	 * и добавление предметов мода в ванильные вкладки.
	 */
	public static void initialize() {
		// Собственная вкладка креатива со всем содержимым мода.
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ERROR_TAB_KEY, FabricCreativeModeTab.builder()
				.title(Component.translatable("itemGroup.error.error_tab"))
				.icon(() -> new ItemStack(ERROR_FRAGMENT))
				.displayItems((context, entries) -> {
					entries.accept(ERROR_FRAGMENT);
					entries.accept(ERROR_WAND);
					entries.accept(ErrorBlocks.ERROR_BLOCK);
				})
				.build());

		// Дублируем предметы в ванильные вкладки, чтобы их было проще найти.
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
				.register(entries -> entries.accept(ERROR_FRAGMENT));

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
				.register(entries -> entries.accept(ERROR_WAND));

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS)
				.register(entries -> entries.accept(ErrorBlocks.ERROR_BLOCK));

		ErrorMod.LOGGER.info("[Error] Предметы и креативная вкладка зарегистрированы.");
	}
}
