package net.minecraft.src;

import net.minecraft.server.MinecraftServer;

public abstract class CustomBTWAddonNetServerHandler {
	public CustomBTWAddonNetServerHandler(MinecraftServer aServer, INetworkManager aNetManager, EntityPlayerMP aPlayer) {}

	public void handleCustomPayload(Packet250CustomPayload aPacket) {}	
}