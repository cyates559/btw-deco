package net.minecraft.src;
public class Addon_Walls
{
	
	public Addon_Walls()
	{
		FCBetterThanWolves.fcBlockStoneBrickSidingAndCorner = new AddonManager.FCBlockSidingAndCornerAndDecorative_Wall(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockStoneBrickSidingAndCorner),  Material.rock, "fcBlockDecorativeStoneBrick", 1.5F, 10.0F, Block.soundStoneFootstep, "fcStoneBrickSiding", "Stone Brick");
		FCBetterThanWolves.fcBlockBrickSidingAndCorner = new AddonManager.FCBlockSidingAndCornerAndDecorative_Wall(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcBlockBrickSidingAndCorner), Material.rock, "fcBlockDecorativeBrick", 2.0F, 10.0F, Block.soundStoneFootstep, "fcBrickSiding","Brick");

		// FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.cobblestoneWall, 6, 0), new Object[] {"###", "###", '#', Block.cobblestone});
		// FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.cobblestoneWall, 6, 1), new Object[] {"###", "###", '#', Block.cobblestoneMossy});

		// FCRecipes.AddVanillaRecipe(new ItemStack(Block.cobblestoneWall, 2, 0), new Object[] {"###", "###", '#', Block.cobblestone});
		// FCRecipes.AddVanillaRecipe(new ItemStack(Block.cobblestoneWall, 2, 1), new Object[] {"###", "###", '#', Block.cobblestoneMossy});
	}
}
