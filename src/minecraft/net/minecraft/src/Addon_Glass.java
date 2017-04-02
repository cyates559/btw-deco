package net.minecraft.src;

import java.util.Random;

public class Addon_Glass
{
	public static Block glassStained, glassPaneStained;
	public static Item glassChunk;
	public Addon_Glass()
	{
		glassChunk = new Item(30002).setUnlocalizedName("ginger_glassball").setCreativeTab(CreativeTabs.tabMaterials);
		glassStained = new BlockStainedGlass(3003);
		AddonManager.Name(glassChunk, "Piece of Glass");
		

		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(Block.glass, 1), new ItemStack[] {new ItemStack(glassChunk, 4)});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(glassChunk, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemPileSand)});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(glassChunk, 4), new Object[]{new ItemStack(Block.glass, 1)});

		FCRecipes.RemoveVanillaRecipe(new ItemStack(Block.thinGlass, 16), new Object[] {"###", "###", '#', Block.glass});
		FCRecipes.AddVanillaRecipe(new ItemStack(Block.thinGlass, 12), new Object[] {"###", "###", '#', Block.glass});

		FCCraftingManagerCrucibleStoked.getInstance().RemoveRecipe(new ItemStack(Block.glass, 3), new ItemStack[] {new ItemStack(Block.thinGlass, 8)});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(Block.glass, 1), new ItemStack[] {new ItemStack(Block.thinGlass, 2)});

		FCRecipes.RemoveVanillaRecipe(new ItemStack(Item.glassBottle, 3), new Object[] {"# #", " # ", '#', Block.glass});
		FCRecipes.AddVanillaRecipe(new ItemStack(Item.glassBottle, 3), new Object[] {" # ", "# #", "###", '#', glassChunk});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(glassChunk, 2), new ItemStack[] {new ItemStack(Item.glassBottle, 1)});
		
		for (int Index = 0; Index < 16; Index++)
		{
			FCRecipes.AddStokedCauldronRecipe(new ItemStack(glassStained, 1, Index), new ItemStack[] { new ItemStack(Block.glass), new ItemStack(Item.dyePowder, 1, Index) });
			FCRecipes.AddStokedCauldronRecipe(new ItemStack(Block.glass), new ItemStack[]{new ItemStack(FCBetterThanWolves.fcSoap), new ItemStack(glassStained, 2, Index) });

			//FCRecipes.AddVanillaRecipe(new ItemStack(stainedGlassPane, 16, Index), new Object[] { "GGG", "GGG", 'G', new ItemStack(stainedGlass, 1, Index) });
			//FCRecipes.AddStokedCrucibleRecipe(new ItemStack(stainedGlass, 3, Index), new ItemStack[] { new ItemStack(stainedGlassPane, 8, Index) });
		}
	}
	public static class BlockStainedGlass extends FCBlockGlass
	{
		public BlockStainedGlass(int ID)
		{
			super(ID,Material.glass,false);
			setHardness(0.3F);
			setStepSound(soundGlassFootstep);
			setUnlocalizedName("ginger_glass_");
			AddonManager.Register(this, new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan", "lightGrey", "grey", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white" }, new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Light Grey", "Grey", "Pink", "Lime", "Yellow", "Light Blue", "Magenta", "Orange", "White" }, " Stained Glass Block");
		}
		public int damageDropped(int Meta)
		{
			return Meta;
		}
		public int getRenderBlockPass()
		{
			return 1;
		}
//CLIENT ONLY METHODS
		public static Icon[] PaneSideIcons = new Icon[16], Icons = new Icon[16];
		public Icon getIcon(int Side, int Meta)
		{
			return Icons[Meta];
		}
		public void registerIcons(IconRegister Register)
		{
			for (int Index = 0; Index < 16; Index++)
			{
				Icons[Index] = Register.registerIcon("ginger_glass_" + Index);
				PaneSideIcons[Index] = Register.registerIcon("ginger_glass_pane_top_" + Index);
			}
		}
//
	}
}
