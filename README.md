# Error — мод для Minecraft 26.3 (1.26.3), Fabric

Мод в «глитч»-стилистике: добавляет блок ошибки, осколок ошибки и жезл ошибки,
а также собственную вкладку в креативном инвентаре.

## Версии

| Компонент       | Версия        |
|-----------------|---------------|
| Minecraft       | `26.3`        |
| Fabric Loader   | `0.19.5`      |
| Fabric API      | `0.161.0+26.3`|
| Fabric Loom     | `1.17-SNAPSHOT` |
| Gradle          | `9.5.1`       |
| Java            | `25`          |

> **О версии «1.26.3».** Начиная с 26.1 Mojang перешла на новую схему именования:
> версия называется просто **26.3** (последняя версия «старого» формата — 1.21.11).
> Поэтому в `gradle.properties` указано `minecraft_version=26.3` — это и есть та самая
> версия, которую в лаунчере и на сайтах модов обозначают как 1.26.3.

## Содержимое мода

| Объект | ID | Описание |
|--------|----|----------|
| Блок ошибки | `error:error_block` | Светящийся (уровень света 10) глитч-блок, прочность 3.0, добывается киркой |
| Осколок ошибки | `error:error_fragment` | Базовый ресурс, редкость `UNCOMMON` |
| Жезл ошибки | `error:error_wand` | Редкость `EPIC`, кулдаун 3 с; при использовании даёт частицы, звук, эффекты свечения и тошноты + сообщение в чат |
| Вкладка креатива | `error:error_tab` | Содержит весь контент мода |

### Рецепты

- 9 осколков → 1 блок ошибки (и обратная разборка: блок → 9 осколков)
- 2 осколка + палка (вертикально) → жезл ошибки

## Сборка

Требуется **JDK 25**.

```bash
./gradlew build
```

Готовый файл появится в `build/libs/error_26.3.jar`.

Скопируйте его в папку `mods` вашего клиента/сервера. Также потребуется
[Fabric API](https://modrinth.com/mod/fabric-api) для версии 26.3.

## Запуск в среде разработки

```bash
./gradlew runClient   # запуск клиента
./gradlew runServer   # запуск сервера
```

## Структура проекта

```
src/main/java/com/errormod/          — общий код (блоки, предметы, точка входа)
src/main/java/com/errormod/item/     — кастомные классы предметов
src/main/java/com/errormod/mixin/    — серверные миксины
src/client/java/com/errormod/client/ — клиентский код и миксины
src/main/resources/assets/error/     — текстуры, модели, локализация
src/main/resources/data/             — рецепты, таблицы лута, теги
```

## Примечания по API 26.3

В 26.x используются официальные маппинги Mojang (Yarn больше не применяется).
Ключевые отличия, учтённые в коде:

- `Identifier.fromNamespaceAndPath(...)` вместо `new ResourceLocation(...)`
- обязательный `.setId(ResourceKey)` в `Item.Properties` / `BlockBehaviour.Properties`
- регистрация через `Registry.register(BuiltInRegistries.ITEM, key, item)`
- креативные вкладки через `FabricCreativeModeTab.builder()` и `CreativeModeTabEvents`
- модели предметов требуют «client item» в `assets/error/items/*.json`

## Лицензия

MIT — см. файл [LICENSE](LICENSE).
