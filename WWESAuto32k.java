package me.THEREALWWEFAN231.auto32k;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
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
	
	public static Minecraft mc = Minecraft.getMinecraft();//holy crap did this cause problems..o_o, you guys know absolutely nothing changes when using Minecraft.getMinecraft(), and this..., and for rusher hack i was talking about stuff like this, https://imgur.com/MQKoLqX , i have nothing against someone using Minecraft.getMinecraft(), every instance they need the minecraft instance, like i said its exactly the same

	public static boolean is32kEnabled;
	public static int cps = 13;
	public static boolean isKillauraOptionEnabled = true;
	public static KeyBinding auto32kKeybind;
	public static KeyBinding auto32kCpsIncrementKeybind;
	public static KeyBinding auto32kCpsdecrementKeybind;
	public static KeyBinding auto32kToggleKillauraKeybind;

	@EventHandler
	public void init(FMLInitializationEvent event) {
		auto32kKeybind = new KeyBinding("Auto 32k bind", Keyboard.KEY_APOSTROPHE, "WWE Auto 32k");
		auto32kCpsIncrementKeybind = new KeyBinding("Auto 32k cps increment", Keyboard.KEY_EQUALS, "WWE Auto 32k");
		auto32kCpsdecrementKeybind = new KeyBinding("Auto 32k cps decrement", Keyboard.KEY_MINUS, "WWE Auto 32k");
		auto32kToggleKillauraKeybind = new KeyBinding("Auto 32k kill aura toggle", Keyboard.KEY_BACKSLASH, "WWE Auto 32k");
		ClientRegistry.registerKeyBinding(auto32kKeybind);
		ClientRegistry.registerKeyBinding(auto32kCpsIncrementKeybind);
		ClientRegistry.registerKeyBinding(auto32kCpsdecrementKeybind);
		ClientRegistry.registerKeyBinding(auto32kToggleKillauraKeybind);
	}

	@EventHandler
	public void post(FMLPostInitializationEvent event) {
		loadInformation();
		MinecraftForge.EVENT_BUS.register(new Auto32kModule());
	}
	
	public static void saveInformation() {
		try {
			File file = new File(mc.gameDir.getAbsolutePath(), "WWE's Auto 32k.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.write("cps:" + cps + "\r\n");
			bufferedWriter.write("killaura:" + isKillauraOptionEnabled + "\r\n");
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadInformation() {
		try {
			File file = new File(mc.gameDir.getAbsolutePath(), "WWE's Auto 32k.txt");
			if (!file.exists()) {
				saveInformation();
				return;
			}
			FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
			DataInputStream dataInputStream = new DataInputStream(fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if(!line.contains(":")) {
					continue;
				}
				String key = line.split(":")[0];
				String value = line.split(":")[1];
				if(key.equalsIgnoreCase("cps")) {
					cps = Integer.parseInt(value);
				}else if(key.equalsIgnoreCase("killaura")) {
					isKillauraOptionEnabled = Boolean.parseBoolean(value);
				}

			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			saveInformation();
		}
	}

}