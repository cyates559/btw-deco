package net.minecraft.src;
public class Addon_GlassColor
{
	public Addon_GlassColor()
	{
		if(!AddonManager.require("Glass")) return;
		if(!AddonManager.require("Flowers")) return;

		Addon_Stained_Glass_Item addon_Stained_Glass_Item = new Addon_Stained_Glass_Item();

		for (int Index = 0; Index < 16; Index++) {
			FCRecipes.AddStokedCauldronRecipe(new ItemStack(addon_Stained_Glass_Item, 1, Index), new ItemStack[] {new ItemStack(Block.glass), new ItemStack(Item.dyePowder, 1, Index+16)});
		}
	}
}
