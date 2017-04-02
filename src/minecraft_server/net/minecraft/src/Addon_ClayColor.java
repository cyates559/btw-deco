package net.minecraft.src;
public class Addon_ClayColor
{
	public Addon_ClayColor()
	{
		if(AddonManager.require("Clay")) return;
		if(AddonManager.require("Flowers")) return;
		for (int Index = 0; Index < 16; Index++)
			FCRecipes.AddStokedCauldronRecipe(new ItemStack(Addon_Clay.stainedClay, 1, Index), new ItemStack[] { new ItemStack(Addon_Clay.hardenedClay), new ItemStack(Item.dyePowder, 1, Index+16) });
	}
}
