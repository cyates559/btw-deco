package net.minecraft.src;
public class Addon_Diamondium
{
	public static Block blockDiamondium;
	public Addon_Diamondium()
	{
		blockDiamondium = new Block(3007, Material.iron).setHardness(10.0F).setResistance(2000.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("blockDiamond").setCreativeTab(CreativeTabs.tabBlock);
		Block.blockDiamond.setUnlocalizedName("ginger_solid_diamond");

		//FCTileEntityBeacon.AddBeaconEffect(blockDiamondium.blockID, 22222);

		AddonManager.Register(blockDiamondium, "Block of Diamondium");

		AddonManager.Name(Block.blockDiamond, "Block of Diamond");
		AddonManager.Name(FCBetterThanWolves.fcItemIngotDiamond, "Diamondium Ingot");
		AddonManager.Name(Item.pickaxeDiamond, "Diamondium Pickaxe");
		AddonManager.Name(Item.axeDiamond, "Diamondium Axe");
		AddonManager.Name(Item.shovelDiamond, "Diamondium Spade");
		AddonManager.Name(Item.hoeDiamond, "Diamondium Hoe");
		AddonManager.Name(Item.swordDiamond, "Diamondium Sword");

		AddonManager.MakeStorage(FCBetterThanWolves.fcItemIngotDiamond, blockDiamondium);
	}
	
}
