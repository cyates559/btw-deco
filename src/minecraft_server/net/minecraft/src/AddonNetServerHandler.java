package net.minecraft.src;

import net.minecraft.server.MinecraftServer;

public class AddonNetServerHandler extends CustomBTWAddonNetServerHandler{

	public final MinecraftServer mcServer;
	public EntityPlayerMP playerEntity;

	public AddonNetServerHandler(MinecraftServer aServer, INetworkManager aNetManager, EntityPlayerMP aPlayer) {
		super(aServer, aNetManager, aPlayer);
		
		this.mcServer = aServer;
		this.playerEntity = aPlayer;
	}

	public void handleCustomPayload(Packet250CustomPayload aPacket) {
		AddonManager.serverCustomPacketReceived(this.mcServer, this.playerEntity, aPacket);
	}
}