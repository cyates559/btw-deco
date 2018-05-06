package net.minecraft.src;

import net.minecraft.server.MinecraftServer;

public class BTHNetServerHandler extends NetServerHandler {

	public final MinecraftServer mcServer;

	public BTHNetServerHandler(MinecraftServer aServer, INetworkManager aNetManager, EntityPlayerMP aPlayer) {
		super(aServer, aNetManager, aPlayer);
		
		this.mcServer = aServer;
		
		try {
			//if (Class.forName("net.minecraft.src.JBJorgesMiscellaneous") != null) {
			if (Class.forName("JBJorgesMiscellaneous") != null) {
				
				JBJorgesMiscellaneous.sendJBMiscTest(aPlayer);
				
				if (aServer.getCommandManager() != null && aServer.getCommandManager() instanceof ServerCommandManager) {
					//Object object = Class.forName("net.minecraft.src.JBCommandServerHardcoreDay").newInstance();
					Object object = Class.forName("JBCommandServerHardcoreDay").newInstance();
					((ServerCommandManager)aServer.getCommandManager()).registerCommand((CommandBase)object);
				}			
			}
		} catch (ClassNotFoundException e) {} catch (Exception e) {}
	}
	
	public void handleCustomPayload(Packet250CustomPayload aPacket) {
		super.handleCustomPayload(aPacket);

		String[] mods = new String[] { "JBJorgesMiscellaneous", "AddonManager" };
		for (String mod : mods) {
			try {
				//Class.forName("net.minecraft.src."+mod).getMethod("serverCustomPacketReceived", MinecraftServer.class, EntityPlayerMP.class, Packet250CustomPayload.class).invoke(null, this.mcServer, this.playerEntity, aPacket);
				Class.forName(mod).getMethod("serverCustomPacketReceived", MinecraftServer.class, EntityPlayerMP.class, Packet250CustomPayload.class).invoke(null, this.mcServer, this.playerEntity, aPacket);
			} catch (ClassNotFoundException e) {} catch (Exception e) {}
		}
	}
}