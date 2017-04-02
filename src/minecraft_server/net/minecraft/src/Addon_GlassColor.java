package net.minecraft.src;
public class Addon_GlassColor
{
	public Addon_GlassColor()
	{
		for (int Index = 0; Index < 16; Index++)
			FCRecipes.AddStokedCauldronRecipe(new ItemStack(Addon_Glass.glassStained, 1, Index), new ItemStack[] {new ItemStack(Block.glass), new ItemStack(Item.dyePowder, 1, Index+16)});
	}
}
