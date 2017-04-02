package net.minecraft.src;
public class Addon_Copper
{
	Block blockCopper;
	Item ingotCopper;
	public Addon_Copper()
	{
		blockCopper=(new FCBlockOreStorage(3005)).setHardness(4.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("ginger_solid_copper");
	}
}
