package me.THEREALWWEFAN231.auto32k;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = WWESAuto32k.MODID, name = WWESAuto32k.NAME, version = WWESAuto32k.VERSION)
public class WWESAuto32k {
	public static final String MODID = "wweauto32k";
	public static final String NAME = "WWE's Auto 32k";
	public static final String VERSION = "0.1";

	public static boolean is32kEnabled;
	public static int cps = 13;
	public static KeyBinding auto32kKeybind;
	public static KeyBinding auto32kCpsIncrementKeybind;
	public static KeyBinding auto32kCpsdecrementKeybind;

	@EventHandler
	public void init(FMLInitializationEvent event) {
		auto32kKeybind = new KeyBinding("Auto 32k bind", Keyboard.KEY_APOSTROPHE, "WWE Auto 32k");
		auto32kCpsIncrementKeybind = new KeyBinding("Auto 32k cps increment", Keyboard.KEY_EQUALS, "WWE Auto 32k");
		auto32kCpsdecrementKeybind = new KeyBinding("Auto 32k cps decrement", Keyboard.KEY_MINUS, "WWE Auto 32k");
		ClientRegistry.registerKeyBinding(auto32kKeybind);
		ClientRegistry.registerKeyBinding(auto32kCpsIncrementKeybind);
		ClientRegistry.registerKeyBinding(auto32kCpsdecrementKeybind);
	}

	@EventHandler
	public void post(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new Auto32kModule());
	}

}
