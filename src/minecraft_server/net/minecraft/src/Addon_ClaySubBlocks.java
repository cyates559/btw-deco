package net.minecraft.src;
public class Addon_ClaySubBlocks
{
	Block claySidingAndCorner, clayMouldingAndDecorative, clayStairs;
	Block[] stainedClaySidingAndCorner, stainedClayMouldingAndDecorative,stainedClayStairs;
	public Addon_ClaySubBlocks()
	{
		if(!AddonManager.isAddonLoaded("Clay")) return;
		int start=AddonManager.id_clay_sub_start,end=start+51,id=start,i=0;
		final String main="Hardened Clay";
		claySidingAndCorner=new AddonManager.FCBlockSidingAndCornerAndDecorative_Wall(id++, Material.rock, "ginger_clay", 2.0F, 5.0F, Block.soundWoodFootstep, "claySiding", "Hardened Clay");
		clayMouldingAndDecorative = new FCBlockMouldingAndDecorative(id++, Material.rock, "ginger_clay", "ginger_clay_column", 3042, 2.0F, 5.0F, Block.soundWoodFootstep, "clayMolding");
		clayStairs = new FCBlockStairs(id++, Addon_Clay.hardenedClay, i).setUnlocalizedName("stairsHardenedClay");
		

		Item.itemsList[claySidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(claySidingAndCorner.blockID - 256);
		Item.itemsList[clayMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(clayMouldingAndDecorative.blockID - 256);
		AddonManager.Register(clayStairs, main+" Stairs");
		AddonManager.NameSubBlocks_Wall(claySidingAndCorner, clayMouldingAndDecorative, main);

		stainedClaySidingAndCorner=new Block[16];
		stainedClayMouldingAndDecorative=new Block[16];
		stainedClayStairs=new Block[16];
		while(i<16)
		{
			stainedClaySidingAndCorner[i] = new AddonManager.FCBlockSidingAndCornerAndDecorative_Wall(id++, Material.rock, "ginger_clay_"+i, 2.0F, 5.0F, Block.soundStoneFootstep, "stainedClaySiding_"+i, "Stained Clay");
			stainedClayMouldingAndDecorative[i] = new FCBlockMouldingAndDecorative(id++, Material.rock, "ginger_clay_"+i, "ginger_clay_column_"+i, 3042, 2.0F, 5.0F, Block.soundWoodFootstep, "stainedClayMoulding_"+i);
			stainedClayStairs[i] = new FCBlockStairs(id++, Addon_Clay.stainedClay, i).setUnlocalizedName("stairsStainedClay_"+i);

			Item.itemsList[stainedClaySidingAndCorner[i].blockID] = new FCItemBlockSidingAndCorner(stainedClaySidingAndCorner[i].blockID - 256);
			Item.itemsList[stainedClayMouldingAndDecorative[i].blockID] = new FCItemBlockMouldingAndDecorative(stainedClayMouldingAndDecorative[i].blockID - 256);
			AddonManager.Register(stainedClayStairs[i], Addon_Clay.names[i]+" "+main+" Stairs");
			AddonManager.NameSubBlocks_Wall(stainedClaySidingAndCorner[i], stainedClayMouldingAndDecorative[i], Addon_Clay.names[i]+" "+main);

			i++;//i is metadata from original 16 color set
		}


		//Recipes
		FCRecipes.AddAnvilRecipe(new ItemStack(claySidingAndCorner, 8, 0), new Object[] {"####", '#', new ItemStack(Addon_Clay.hardenedClay, 1, 0)});
		FCRecipes.AddAnvilRecipe(new ItemStack(clayMouldingAndDecorative, 8, 0), new Object[] {"####", '#', new ItemStack(claySidingAndCorner, 1, 0)});
		FCRecipes.AddAnvilRecipe(new ItemStack(claySidingAndCorner, 8, 1), new Object[] {"####", '#', new ItemStack(clayMouldingAndDecorative, 1, 0)});
		FCRecipes.AddVanillaRecipe(new ItemStack(clayMouldingAndDecorative, 1, 12), new Object[] {"M", "M", "M", 'M', new ItemStack(clayMouldingAndDecorative, 1, 0)});
		FCRecipes.AddVanillaRecipe(new ItemStack(clayMouldingAndDecorative, 6, 13), new Object[] {" S ", "###", "###", '#', new ItemStack(Addon_Clay.hardenedClay, 1, 0), 'S', new ItemStack(claySidingAndCorner, 8, 0)});
		FCRecipes.AddVanillaRecipe(new ItemStack(clayMouldingAndDecorative, 4, 15), new Object[] {"###", " X ", " X ", '#', new ItemStack(claySidingAndCorner, 1, 0), 'X', new ItemStack(clayMouldingAndDecorative, 1, 0)});
		FCRecipes.AddVanillaRecipe(new ItemStack(claySidingAndCorner, 4, 12), new Object[] {"###", " X ", '#', new ItemStack(claySidingAndCorner, 1, 0), 'X', new ItemStack(clayMouldingAndDecorative, 1, 0)});

		FCRecipes.AddVanillaRecipe(new ItemStack(claySidingAndCorner, 2, 14), new Object[] {"###", "###", '#', new ItemStack(Addon_Clay.hardenedClay, 1, 0)});
		FCRecipes.AddVanillaRecipe(new ItemStack(claySidingAndCorner, 2, 14), new Object[] {"###", '#', new ItemStack(clayMouldingAndDecorative, 1, 0)});

		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Addon_Clay.hardenedClay, 1, 0), new Object[] {new ItemStack(claySidingAndCorner, 1, 0), new ItemStack(claySidingAndCorner, 1, 0)});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(claySidingAndCorner, 1, 0), new Object[] {new ItemStack(clayMouldingAndDecorative, 1, 0), new ItemStack(clayMouldingAndDecorative, 1, 0)});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(clayMouldingAndDecorative, 1, 0), new Object[] {new ItemStack(claySidingAndCorner, 1, 1), new ItemStack(claySidingAndCorner, 1, 1)});
		
		//Stairs
		FCRecipes.AddVanillaRecipe(new ItemStack(clayStairs, 4, 0), new Object[]{"#  ","## ","###",'#',new ItemStack(Addon_Clay.hardenedClay, 1, 0)});
		FCRecipes.AddVanillaRecipe(new ItemStack(clayStairs, 1, 0), new Object[]{"# ","##",'#',new ItemStack(clayMouldingAndDecorative, 1, 0)});
		for(i=0;i<16;i++)
		{
			//Sub blocks
			FCRecipes.AddAnvilRecipe(new ItemStack(stainedClaySidingAndCorner[i], 8, 0), new Object[] {"####", '#', new ItemStack(Addon_Clay.stainedClay, 1, i)});
			FCRecipes.AddAnvilRecipe(new ItemStack(stainedClayMouldingAndDecorative[i], 8, 0), new Object[] {"####", '#', new ItemStack(stainedClaySidingAndCorner[i], 1, 0)});
			FCRecipes.AddAnvilRecipe(new ItemStack(stainedClaySidingAndCorner[i], 8, 1), new Object[] {"####", '#', new ItemStack(stainedClayMouldingAndDecorative[i], 1, 0)});
			
			FCRecipes.AddVanillaRecipe(new ItemStack(stainedClayMouldingAndDecorative[i], 1, 12), new Object[] {"M", "M", "M", 'M', new ItemStack(stainedClayMouldingAndDecorative[i], 1, 0)});
			FCRecipes.AddVanillaRecipe(new ItemStack(stainedClayMouldingAndDecorative[i], 6, 13), new Object[] {" S ", "###", "###", '#', new ItemStack(Addon_Clay.stainedClay, 1, i), 'S', new ItemStack(stainedClaySidingAndCorner[i], 8, 0)});
			FCRecipes.AddVanillaRecipe(new ItemStack(stainedClayMouldingAndDecorative[i], 4, 15), new Object[] {"###", " X ", " X ", '#', new ItemStack(stainedClaySidingAndCorner[i], 1, 0), 'X', new ItemStack(stainedClayMouldingAndDecorative[i], 1, 0)});
			FCRecipes.AddVanillaRecipe(new ItemStack(stainedClaySidingAndCorner[i], 4, 12), new Object[] {"###", " X ", '#', new ItemStack(stainedClaySidingAndCorner[i], 1, 0), 'X', new ItemStack(stainedClayMouldingAndDecorative[i], 1, 0)});

			//Fences
			FCRecipes.AddVanillaRecipe(new ItemStack(stainedClaySidingAndCorner[i], 2, 14), new Object[] {"###", "###", '#', new ItemStack(Addon_Clay.stainedClay, 1, i)});
			FCRecipes.AddVanillaRecipe(new ItemStack(stainedClaySidingAndCorner[i], 2, 14), new Object[] {"###", '#', new ItemStack(stainedClayMouldingAndDecorative[i], 1, 0)});

			FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Addon_Clay.stainedClay, 1, i), new Object[] {new ItemStack(stainedClaySidingAndCorner[i], 1, 0), new ItemStack(stainedClaySidingAndCorner[i], 1, 0)});
			FCRecipes.AddShapelessVanillaRecipe(new ItemStack(stainedClaySidingAndCorner[i], 1, 0), new Object[] {new ItemStack(stainedClayMouldingAndDecorative[i], 1, 0), new ItemStack(stainedClayMouldingAndDecorative[i], 1, 0)});
			FCRecipes.AddShapelessVanillaRecipe(new ItemStack(stainedClayMouldingAndDecorative[i], 1, 0), new Object[] {new ItemStack(stainedClaySidingAndCorner[i], 1, 1), new ItemStack(stainedClaySidingAndCorner[i], 1, 1)});
			
			//Stairs
			FCRecipes.AddVanillaRecipe(new ItemStack(stainedClayStairs[i], 4, 0), new Object[]{"#  ","## ","###",'#',new ItemStack(Addon_Clay.stainedClay, 1, i)});
			FCRecipes.AddVanillaRecipe(new ItemStack(stainedClayStairs[i], 1, 0), new Object[]{"# ","##",'#',new ItemStack(stainedClayMouldingAndDecorative[i], 1, 0)});
		}
	}
}
