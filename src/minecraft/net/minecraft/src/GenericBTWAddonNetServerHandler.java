package net.minecraft.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import net.minecraft.server.MinecraftServer;

public class GenericBTWAddonNetServerHandler extends NetServerHandler {
	private ArrayList<CustomBTWAddonNetServerHandler> customHandlers = new ArrayList<CustomBTWAddonNetServerHandler>();
	public final MinecraftServer mcServer;
	public EntityPlayerMP playerEntity;

	public GenericBTWAddonNetServerHandler(MinecraftServer aServer, INetworkManager aNetManager, EntityPlayerMP aPlayer) {
		super(aServer, aNetManager, aPlayer);
		
		this.mcServer = aServer;
		this.playerEntity = aPlayer;

		String prefix = getClassPackagePrefix();
		ArrayList<String> classNames = getCustomNetworkListenersClassNames();

		for (String className : classNames) {
			try {
				Constructor<?> c = Class.forName(prefix + className).getConstructor(MinecraftServer.class, INetworkManager.class,
						EntityPlayerMP.class);
	
				CustomBTWAddonNetServerHandler nsh = (CustomBTWAddonNetServerHandler) c.newInstance(aServer, aNetManager,	aPlayer);
	
				customHandlers.add(nsh);
			} catch (Throwable e) {}
		}
	}

	public void handleCustomPayload(Packet250CustomPayload aPacket) {
		for (CustomBTWAddonNetServerHandler netHandler : customHandlers) {
			netHandler.handleCustomPayload(aPacket);
		}
	}
	
	private static ArrayList<String> getCustomNetworkListenersClassNames() {
		ArrayList<String> result = new ArrayList<String>();
		BufferedReader buffer = null;

		try {
			buffer = new BufferedReader(
					new InputStreamReader(NetworkListenThread.class.getResourceAsStream("/SharedCompatibilityAddonConfig.txt")));
		} catch (Throwable e) {
			// Currently known addons
			result.add("JBNetServerHandler");
			result.add("AddonNetServerHandler");
			return result;
		}

		try {
			String line = "";

			while ((line = buffer.readLine()) != null) {
				String[] value = line.split("=");

				for (int i = 0; i < value.length; ++i) {
					value[i] = value[i].trim();
				}

				if (!value[0].equals("class_name")) {
					continue;
				}

				result.add(value[1]);
			}

			buffer.close();
		} catch (Throwable e) {
			System.out.println("Failed to load SharedCompatibilityAddonConfig.txt");
			e.printStackTrace();
		}

		return result;
	}
	
	private String getClassPackagePrefix() {
		try {
			if (Class.forName("FCBetterThanWolves") != null) {
				return "";
			}
		} catch (Throwable e) {
		}
		try {
			if (Class.forName("net.minecraft.src.FCBetterThanWolves") != null) {
				return "net.minecraft.src.";
			}
		} catch (Throwable e) {
		}
		return "";
	}
}