package net.minecraft.src;

import net.minecraft.server.MinecraftServer;

public class BTHNetServerHandler extends NetServerHandler {

	public final MinecraftServer mcServer;

	public BTHNetServerHandler(MinecraftServer aServer, INetworkManager aNetManager, EntityPlayerMP aPlayer) {
		super(aServer, aNetManager, aPlayer);

		this.mcServer = aServer;
	}

	public void handleCustomPayload(Packet250CustomPayload aPacket) {
		super.handleCustomPayload(aPacket);

		try {
			Class.forName("JBAstrolabe").getMethod("serverCustomPacketReceived", MinecraftServer.class, EntityPlayerMP.class,
					Packet250CustomPayload.class).invoke(null, this.mcServer, this.playerEntity, aPacket);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Class.forName("AddonManager").getMethod("serverCustomPacketReceived", MinecraftServer.class, EntityPlayerMP.class,
					Packet250CustomPayload.class).invoke(null, this.mcServer, this.playerEntity, aPacket);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Class.forName("BTHBetterThanHorses").getMethod("serverCustomPacketReceived", MinecraftServer.class,
							EntityPlayerMP.class, Packet250CustomPayload.class)
					.invoke(null, this.mcServer, this.playerEntity, aPacket);
		} catch (ClassNotFoundException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}