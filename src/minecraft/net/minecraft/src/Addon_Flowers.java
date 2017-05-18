package net.minecraft.src;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Addon_Flowers
{
	public static Block flower, tulip;
	public static Item fertilizer;
	public static String[]
		flowers = { "yucca", "hyacinth", "birdsParadise", "azalea", "cornFlower", "lavender", "honeysuckle","allium", "orchidBlue", "poppy", "azureBluet", "daisy", "peony","lilac","rosebush", "roseBlue"},
		tulips = { "red","pink", "orange", "white", "blue"},
		flowerNames = { "Yucca", "Hyacinth", "Birds of Paradise", "Azaleas", "Cornflower", "Lavender", "Honeysuckle", "Allium","Blue Orchid", "Poppy", "Azure Bluet", "Daisy", "Peony", "Lilac", "Rose Bush", "Blue Rose"},
		tulipNames = { "Red", "Pink", "Orange", "White", "Blue"};
	public Addon_Flowers()
	{
		Item.m_bSuppressConflictWarnings=true;
		Item.dyePowder = new FCItemDye_ColorPlus(95);
		Item.m_bSuppressConflictWarnings=false;
		
		List recipes = CraftingManager.getInstance().getRecipeList();
		ArrayList<RecipeFireworks> fireworks = new ArrayList<RecipeFireworks>();
		for(Object o: recipes)
			if(o instanceof RecipeFireworks)
				fireworks.add((RecipeFireworks)o);
		for(RecipeFireworks rf: fireworks)
			recipes.remove(rf);
		recipes.add(new RecipeFireworks_Color());


		FCBetterThanWolves.fcPlanter = new BlockPlanter(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcPlanter));
		flower = new BlockFlowers(3002, "flower",flowers,flowerNames);
		tulip = new BlockFlowers(3006, "tulip", tulips, tulipNames, " Tulip");
		
		fertilizer = new ItemFertilizer(30003);
		

//recipes//
		//Change flowerpot recipe
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Item.flowerPot, 1), new Object[] {"# #", " # ", '#', Item.brick});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.flowerPot, 1), new ItemStack[]{new ItemStack(FCBetterThanWolves.fcItemPileDirt), new ItemStack(FCBetterThanWolves.fcUrn)});
		for (int I = 0; I < 16; ++I)
			FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Block.cloth, 1, BlockCloth.getDyeFromBlock(I)), new Object[] {new ItemStack(Item.dyePowder, 1, I+16), new ItemStack(Item.itemsList[Block.cloth.blockID], 1, 0)});
		
		//Fertilizer
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(fertilizer, 2), new ItemStack[] { new ItemStack(Item.dyePowder, 1, 15), new ItemStack(FCBetterThanWolves.fcDung) });

		//Flower Recipes

		// Yucca
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 10)},	new ItemStack[]{new ItemStack(flower, 1, 0)});
		// Hyacinth
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 12)},	new ItemStack[]{new ItemStack(flower, 1, 1)});
		// Birds of Paridise
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 14)},	new ItemStack[]{new ItemStack(flower, 1, 2)});
		// Azalea
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 9) },	new ItemStack[]{new ItemStack(flower, 1, 3)});
		// Cornflower
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 20)},	new ItemStack[]{new ItemStack(flower, 1, 4)});
		// Lavender
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 5) },	new ItemStack[]{new ItemStack(flower, 1, 5)});
		// Honeysuckle
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 1, 31),new ItemStack(Item.dyePowder, 1, 11)},new ItemStack[]{new ItemStack(flower, 1, 6)});
		// Allium
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 13)},	new ItemStack[]{new ItemStack(flower, 1, 7)});
		// Blue Orchid
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 12)},	new ItemStack[]{new ItemStack(flower, 1, 8)});
		// Poppy
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 1) },	new ItemStack[]{new ItemStack(flower, 1, 9)});
		// Azure Bluet
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 1, 31),new ItemStack(Item.dyePowder, 1, 11)},new ItemStack[]{new ItemStack(flower, 1, 10)});
		// Daisy
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 1, 31),new ItemStack(Item.dyePowder, 1, 11)},new ItemStack[]{new ItemStack(flower, 1, 11)});
		// Peony
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 13)},	new ItemStack[]{new ItemStack(flower, 1, 12)});
		// Lilac
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 13)},	new ItemStack[]{new ItemStack(flower, 1, 13)});
		// Rose Bush
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 3, 1)},	new ItemStack[]{new ItemStack(flower, 1, 14)});
		// Blue Rose
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 20)},	new ItemStack[]{new ItemStack(flower, 1, 15)});
		// Tulips
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 1, 14),new ItemStack(Item.dyePowder, 1, 1)},	new ItemStack[]{new ItemStack(tulip, 1, 0)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 9)},	new ItemStack[]{new ItemStack(tulip, 1, 1)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 14)},	new ItemStack[]{new ItemStack(tulip, 1, 2)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 31)},	new ItemStack[]{new ItemStack(tulip, 1, 3)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 20)},	new ItemStack[]{new ItemStack(tulip, 1, 4)});
		
		//Cooking with dyes
		for (int Index = 0; Index < 16; Index++)
		{
			FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Block.cloth,1,BlockCloth.getDyeFromBlock(Index)),
				new Object[]{new ItemStack(Item.dyePowder,1,Index+16),new ItemStack(Item.itemsList[Block.cloth.blockID], 1,0)});
			//FCRecipes.AddVanillaRecipe(new ItemStack(stainedGlassPane, 16, Index), new Object[] { "GGG", "GGG", 'G', new ItemStack(stainedGlass, 1, Index) });
			//FCRecipes.AddStokedCrucibleRecipe(new ItemStack(stainedGlass, 3, Index), new ItemStack[] { new ItemStack(stainedGlassPane, 8, Index) });
		}
		//Mixing dyes
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 10), new Object[] {new ItemStack(Item.dyePowder, 1, 2), new ItemStack(Item.dyePowder, 1, 31)});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 8), new Object[] {new ItemStack(Item.dyePowder, 1, 0), new ItemStack(Item.dyePowder, 1, 31)});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 7), new Object[] {new ItemStack(Item.dyePowder, 1, 8), new ItemStack(Item.dyePowder, 1, 31)});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 9), new Object[] {new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 31)});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 6), new Object[] {new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 2)});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 5), new Object[] {new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 1)});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 3, 13), new Object[] {new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 9)});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 12), new Object[] {new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 15)});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 12), new Object[] {new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 31)});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 2, 12), new Object[] {new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.dyePowder, 1, 15)});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 3, 7), new Object[] {new ItemStack(Item.dyePowder, 1, 0), new ItemStack(Item.dyePowder, 1, 31), new ItemStack(Item.dyePowder, 1, 15)});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 3, 7), new Object[] {new ItemStack(Item.dyePowder, 1, 0), new ItemStack(Item.dyePowder, 1, 31), new ItemStack(Item.dyePowder, 1, 31)});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 4, 13), new Object[] {new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 15)});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 4, 13), new Object[] {new ItemStack(Item.dyePowder, 1, 20), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 31)});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.dyePowder, 4, 13), new Object[] {new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 31)});
	}
	public static class BlockFlowers extends BlockFlower
	{

//CLIENT ONLY
		Icon[] Icons;
//
		String TextureTag;
		public BlockFlowers(int ID, String Texture, String[] Names, String[] Titles){this(ID,Texture, Names, "",Titles,"");}
		public BlockFlowers(int ID, String Texture, String[] Names, String[] Titles, String PostTitle){this(ID,Texture, Names, "",Titles,PostTitle);}
		public BlockFlowers(int ID, String Texture, String[] Names, String PreTitle, String[] Titles){this(ID,Texture, Names, PreTitle ,Titles,"");}
		public BlockFlowers(int ID, String Texture, String[] Names, String PreTitle, String[] Titles, String PostTitle)
		{
			super(ID);
			TextureTag = Texture;
			Icons = new Icon[Names.length];
			setUnlocalizedName("deco"+Texture);
			setStepSound(soundGrassFootstep);
			setCreativeTab(CreativeTabs.tabDecorations);
			AddonManager.Register(this, Names, PreTitle, Titles, PostTitle);
		}
		public boolean canBlockStay(World par1World, int par2, int par3, int par4)
		{
			return Block.plantRed.canBlockStay(par1World, par2, par3, par4);
		}
		public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
		{
			return Block.plantRed.canPlaceBlockAt(par1World, par2, par3, par4);
		}
		public int damageDropped(int Meta)
		{
			return Meta;
		}
//CLIENT ONLY METHODS
		public Icon getIcon(int Side, int Meta)
		{
			return Icons[Meta];
		}
		public void registerIcons(IconRegister Register)
		{
			for (int Index = 0; Index < Icons.length; Index++)
			{
				Icons[Index] = Register.registerIcon("ginger_"+TextureTag+"_" + Index);
			}
		}
//
	}
	public static class FCItemDye_ColorPlus extends FCItemDye
	{
//CLIENT ONLY METHODS
		public Icon[] ExtraIcons = new Icon[16];
//
		public static final String[] ColorPlus_dyeColorNames = new String[] {"black","red","green","brown","blue","purple","cyan","silver","gray","pink","lime","yellow","lightBlue","magenta","orange", "white","black2","red2","green2","brown2","blue2","purple2","cyan2","silver2","gray2","pink2","lime2","yellow2","lightBlue2","magenta2","orange2","white2"};
		public FCItemDye_ColorPlus(int ID)
		{
			super(ID);
			setUnlocalizedName("dyePowder");
			SetBellowsBlowDistance(2);
			AddonManager.Name(new ItemStack(this, 1, 20), "Blue Dye");
			AddonManager.Name(new ItemStack(this, 1, 31), "White Dye");
		}
		public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
		{
			return var2 != null && !var2.canPlayerEdit(var4, var5, var6, var7, var1) ?
				false :(var1.getItemDamage() == 15 ?
					this.ApplyBoneMeal(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10) : (var1.getItemDamage() == 3 ?
					this.ApplyCocoaBeans(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10) : false
				));
		}
		private boolean CanBonemealBeAppliedToBlock(World var1, int var2, int var3, int var4)
		{
			int var5 = var1.getBlockId(var2, var3, var4);
			int var6 = var1.getBlockMetadata(var2, var3, var4);
			return var5 == Block.tilledField.blockID || var5 == FCBetterThanWolves.fcPlanter.blockID && var6 == 1 || var5 == Block.grass.blockID;
		}
		private boolean ApplyCocoaBeans(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
		{
			int var11 = var3.getBlockId(var4, var5, var6);
			int var12 = var3.getBlockMetadata(var4, var5, var6);
			if (var11 == Block.wood.blockID && BlockLog.limitToValidMetadata(var12) == 3)
			{
				if (var7 != 0 && var7 != 1)
				{
					FCUtilsBlockPos var13 = new FCUtilsBlockPos(var4, var5, var6);
					var13.AddFacingAsOffset(var7);
					if (var3.isAirBlock(var13.i, var13.j, var13.k))
					{
						int var14 = Block.cocoaPlant.blockID;
						int var15 = Block.blocksList[var14].onBlockPlaced(var3, var13.i, var13.j, var13.k, var7, var8, var9, var10, 0);
						var3.setBlockAndMetadataWithNotify(var13.i, var13.j, var13.k, var14, var15);
						if (var2 == null || !var2.capabilities.isCreativeMode)
							--var1.stackSize;
					}
					return true;
				}
				else return false;
			}
			else return false;
		}
		private boolean ApplyBoneMeal(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
		{
			if (!this.CanBonemealBeAppliedToBlock(var3, var4, var5, var6))
				--var5;
			if (this.CanBonemealBeAppliedToBlock(var3, var4, var5, var6))
			{
				int var11 = var3.getBlockId(var4, var5, var6);
				if (var11 == Block.tilledField.blockID)
				{
					int var12 = var3.getBlockMetadata(var4, var5, var6);
					var3.setBlockAndMetadataWithNotify(var4, var5, var6, FCBetterThanWolves.fcBlockFarmlandFertilized.blockID, var12);
				}
				else if (var11 == FCBetterThanWolves.fcPlanter.blockID)
				{
					((FCBlockPlanter) ((FCBlockPlanter) FCBetterThanWolves.fcPlanter)).SetPlanterType(var3, var4, var5, var6, 2);
				}
				else if (var11 == Block.grass.blockID)
				{
					if (var3.provider.dimensionId == 1)
						return false;
					if (!var3.isRemote) this.GrowTallGrassAndFlowers(var3, var4, var5, var6);
				}
				--var1.stackSize;
				return true;
			}
			else return false;
		}
		private void GrowTallGrassAndFlowers(World CurrentWorld, int X, int Y, int Z)
		{
			if (Block.tallGrass.canBlockStay(CurrentWorld, X, Y + 1, Z) && CurrentWorld.isAirBlock(X, Y + 1, Z))
			CurrentWorld.setBlockAndMetadataWithNotify(X, Y + 1, Z, Block.tallGrass.blockID, 1);
		}
		public String getUnlocalizedName(ItemStack I)
		{
			return super.getUnlocalizedName() + "." + ColorPlus_dyeColorNames[I.getItemDamage()&31];
		}
		public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
		{
			for (int var4 = 0; var4 < 16; ++var4)
			{
				par3List.add(new ItemStack(par1, 1, var4));
			}
			par3List.add(new ItemStack(par1, 1, 20));
			par3List.add(new ItemStack(par1, 1, 31));
		}
//CLIENT ONLY METHODS
		public Icon getIconFromDamage(int Meta)
		{
			return Meta>15 ? ExtraIcons[Meta-16] : super.getIconFromDamage(Meta);
		}
		public void registerIcons(IconRegister var1)
		{
			super.registerIcons(var1);
			int I=0;
			while(I<16)
			{
				ExtraIcons[I] = var1.registerIcon("ginger_dyePowder_"+I);
				I++;
			}
		}
//
	}
	
	public static class ItemFertilizer extends Item
	{
		ItemFertilizer(int ID)
		{
			super(ID);
			setUnlocalizedName("ginger_fertilizer");
			setCreativeTab(CreativeTabs.tabMaterials);
			SetBellowsBlowDistance(FCBetterThanWolves.fcCoalDust.GetBellowsBlowDistance(0));
			AddonManager.Name(this, "Fertilizer");
		}
		public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
		{
			return var2 != null && !var2.canPlayerEdit(var4, var5, var6, var7, var1) ? false : this.ApplyBoneMeal(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
		}
		private boolean CanBonemealBeAppliedToBlock(World var1, int var2, int var3, int var4)
		{
			int var5 = var1.getBlockId(var2, var3, var4);
			int var6 = var1.getBlockMetadata(var2, var3, var4);
			return var5 == Block.tilledField.blockID || var5 == FCBetterThanWolves.fcPlanter.blockID && var6 == 1 || var5 == Block.grass.blockID;
		}
		private boolean ApplyBoneMeal(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
		{
			if (!this.CanBonemealBeAppliedToBlock(var3, var4, var5, var6))
				--var5;
			if (this.CanBonemealBeAppliedToBlock(var3, var4, var5, var6))
			{
				int var11 = var3.getBlockId(var4, var5, var6);
				if (var11 == Block.tilledField.blockID)
				{
					int var12 = var3.getBlockMetadata(var4, var5, var6);
					var3.setBlockAndMetadataWithNotify(var4, var5, var6, FCBetterThanWolves.fcBlockFarmlandFertilized.blockID, var12);
				}
				else if (var11 == FCBetterThanWolves.fcPlanter.blockID)
				{
					((FCBlockPlanter) ((FCBlockPlanter) FCBetterThanWolves.fcPlanter)).SetPlanterType(var3, var4, var5, var6, 2);
				}
				else if (var11 == Block.grass.blockID)
				{
					if (var3.provider.dimensionId == 1)
						return false;
					if (!var3.isRemote)
						this.GrowTallGrassAndFlowers(var3, var4, var5, var6);
				}
				--var1.stackSize;
				return true;
			}
			else return false;
		}
		public void GrowTallGrassAndFlowers(World CurrentWorld, int X, int Y, int Z)
		{
			int Index = 0;
			while (Index < 128)
			{
				int NewX = X;
				int NewY = Y + 1;
				int NewZ = Z;
				boolean Test = true;
				int SubIndex = 0;
				while (true)
				{
					if (SubIndex < Index / 16)
					{
						NewX += itemRand.nextInt(3) - 1;
						NewY += (itemRand.nextInt(3) - 1) * itemRand.nextInt(3) / 2;
						NewZ += itemRand.nextInt(3) - 1;
						if (CurrentWorld.getBlockId(NewX, NewY - 1, NewZ) == Block.grass.blockID && !CurrentWorld.isBlockNormalCube(NewX, NewY, NewZ))
						{
							++SubIndex;
							continue;
						}
							Test = false;
					}
					if (Test && CurrentWorld.getBlockId(NewX, NewY, NewZ) == 0)
					{
						if (itemRand.nextInt(4) == 0 && Block.tallGrass.canBlockStay(CurrentWorld, NewX, NewY, NewZ))
							CurrentWorld.setBlockAndMetadataWithNotify(NewX, NewY, NewZ, Block.tallGrass.blockID, 1);
						else if (itemRand.nextInt(3) == 0 && Block.plantYellow.canBlockStay(CurrentWorld, NewX, NewY, NewZ))
						{
							int R = itemRand.nextInt(21);
							switch (R)
							{
								case 0:
								case 1:
								case 2:
								case 3:
								case 4:
								case 5:
								case 6:
								case 7:
								case 8:
								case 9:
								case 10:
								case 11:
								case 12:
								case 13:
								case 14:
									CurrentWorld.setBlockAndMetadataWithNotify(NewX, NewY, NewZ, flower.blockID, R);
									break;
								case 15:
									CurrentWorld.setBlockWithNotify(NewX, NewY, NewZ, Block.plantYellow.blockID);
									break;
								case 16:
									CurrentWorld.setBlockWithNotify(NewX, NewY, NewZ, Block.plantRed.blockID);
									break;
								case 17:
								case 18:
								case 19:
								case 20:
									CurrentWorld.setBlockAndMetadataWithNotify(NewX, NewY, NewZ, tulip.blockID, R-17);
									break;
							}
						}
						//else if (decoSapling.canBlockStay(CurrentWorld, NewX, NewY, NewZ))
						//CurrentWorld.setBlockWithNotify(NewX, NewY, NewZ, decoSapling.blockID);
					}
					++Index;
					break;
				}
			}
		}
	}
	public static class BlockPlanter extends FCBlockPlanter
	{
		public BlockPlanter(int ID)
		{
			super(ID);
			this.setHardness(0.6F);
			this.setStepSound(soundGlassFootstep);
			this.setUnlocalizedName("fcBlockPlanter");
			this.setTickRandomly(true);
			ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this);
			setCreativeTab(CreativeTabs.tabDecorations);
		}

		public void updateTick(World CurrentWorld, int X, int Y, int Z, Random itemRand)
		{
			int var6 = this.GetPlanterType(CurrentWorld, X, Y, Z);

			if (var6 == 9 || var6 == 11 || var6 == 13 || var6 == 15)
			{
				int GrowthState = this.GetGrassGrowthState(CurrentWorld, X, Y, Z);
				int var8 = 0;
				int var9;
				if (CurrentWorld.isAirBlock(X, Y + 1, Z) && CurrentWorld.getBlockLightValue(X, Y + 1, Z) >= 8)
				{
					var8 = GrowthState + 1;
					if (var8 > 3)
					{
						var8 = 0;
						int R = itemRand.nextInt(28);
						switch (R)
						{
							case 0:
							case 1:
							case 2:
							case 3:
							case 4:
							case 5:
							case 6:
							case 7:
							case 8:
							case 9:
							case 10:
							case 11:
							case 12:
							case 13:
								CurrentWorld.setBlockAndMetadataWithNotify(X, Y+1, Z, flower.blockID, R);
								break;
							case 14:
								R = itemRand.nextInt(5);
								switch(R)
								{//RARE FLOWERS
									case 0:
										CurrentWorld.setBlockAndMetadataWithNotify(X, Y + 1, Z, flower.blockID, 15);
										break;
									case 1:
										CurrentWorld.setBlockAndMetadataWithNotify(X, Y + 1, Z, tulip.blockID, 4);
										break;
									case 2:
										CurrentWorld.setBlockAndMetadataWithNotify(X, Y + 1, Z, Block.tallGrass.blockID, 0);
										break;
									case 3:
										CurrentWorld.setBlockAndMetadataWithNotify(X, Y + 1, Z, Block.tallGrass.blockID, 2);
										break;
								}
								break;
							case 15:
								CurrentWorld.setBlockWithNotify(X, Y+1, Z, Block.plantYellow.blockID);
								break;
							case 16:
								CurrentWorld.setBlockWithNotify(X, Y+1, Z, Block.plantRed.blockID);
								break;
							case 17:
							case 18:
							case 19:
							case 20:
								CurrentWorld.setBlockAndMetadataWithNotify(X, Y+1, Z, tulip.blockID, R-17);
								break;
							default:
								CurrentWorld.setBlockAndMetadataWithNotify(X, Y + 1, Z, Block.tallGrass.blockID, 1);
						}
					}
				}
				if (CurrentWorld.getBlockLightValue(X, Y + 1, Z) >= 9)
				{
					for (var9 = 0; var9 < 4; ++var9)
					{
						int CurrentWorld0 = X + itemRand.nextInt(3) - 1;
						int CurrentWorld1 = Y + itemRand.nextInt(5) - 3;
						int CurrentWorld2 = Z + itemRand.nextInt(3) - 1;
						int CurrentWorld3 = CurrentWorld.getBlockId(CurrentWorld0, CurrentWorld1 + 1, CurrentWorld2);

						if (CurrentWorld.getBlockId(CurrentWorld0, CurrentWorld1, CurrentWorld2) == Block.dirt.blockID && CurrentWorld.getBlockLightValue(CurrentWorld0, CurrentWorld1 + 1, CurrentWorld2) >= 4 && Block.lightOpacity[CurrentWorld3] <= 2)CurrentWorld.setBlockWithNotify(CurrentWorld0, CurrentWorld1, CurrentWorld2, Block.grass.blockID);
					}
				}
				if (var8 != GrowthState)
					this.SetGrassGrowthState(CurrentWorld, X, Y, Z, var8);
			}
		}
	}
	public class RecipeFireworks_Color implements IRecipe
	{
		private ItemStack field_92102_a;

		/**
		* Used to check if a recipe matches current crafting inventory
		*/
		public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World)
		{
			this.field_92102_a = null;
			int var3 = 0;
			int var4 = 0;
			int var5 = 0;
			int var6 = 0;
			int var7 = 0;
			int var8 = 0;

			for (int var9 = 0; var9 < par1InventoryCrafting.getSizeInventory(); ++var9)
			{
				ItemStack var10 = par1InventoryCrafting.getStackInSlot(var9);

				if (var10 != null)
				{
					if (var10.itemID == Item.gunpowder.itemID)
					{
						++var4;
					}
					else if (var10.itemID == Item.fireworkCharge.itemID)
					{
						++var6;
					}
					else if (var10.itemID == Item.dyePowder.itemID)
					{
						++var5;
					}
					else if (var10.itemID == Item.paper.itemID)
					{
						++var3;
					}
					else if (var10.itemID == Item.lightStoneDust.itemID)
					{
						++var7;
					}
					else if (var10.itemID == Item.diamond.itemID)
					{
						++var7;
					}
					else if (var10.itemID == Item.fireballCharge.itemID)
					{
						++var8;
					}
					else if (var10.itemID == Item.feather.itemID)
					{
						++var8;
					}
					else if (var10.itemID == Item.goldNugget.itemID)
					{
						++var8;
					}
					else
					{
						if (var10.itemID != Item.skull.itemID)
						{
							return false;
						}
						++var8;
					}
				}
			}

			var7 += var5 + var8;

			if (var4 <= 3 && var3 <= 1)
			{
			int var12;
			ItemStack var13;
			NBTTagCompound var15;
			NBTTagCompound var16;

			if (var4 >= 1 && var3 == 1 && var7 == 0)
			{
				this.field_92102_a = new ItemStack(Item.firework);

				if (var6 > 0)
				{
					var15 = new NBTTagCompound();
					var16 = new NBTTagCompound("Fireworks");
					NBTTagList var19 = new NBTTagList("Explosions");

					for (var12 = 0; var12 < par1InventoryCrafting.getSizeInventory(); ++var12)
					{
						var13 = par1InventoryCrafting.getStackInSlot(var12);

						if (var13 != null && var13.itemID == Item.fireworkCharge.itemID && var13.hasTagCompound() && var13.getTagCompound().hasKey("Explosion"))
						{
							var19.appendTag(var13.getTagCompound().getCompoundTag("Explosion"));
						}
					}

					var16.setTag("Explosions", var19);
					var16.setByte("Flight", (byte)var4);
					var15.setTag("Fireworks", var16);
					this.field_92102_a.setTagCompound(var15);
				}

				return true;
			}
			else
			{
			int var21;

			if (var4 == 1 && var3 == 0 && var6 == 0 && var5 > 0 && var8 <= 1)
			{
				this.field_92102_a = new ItemStack(Item.fireworkCharge);
				var15 = new NBTTagCompound();
				var16 = new NBTTagCompound("Explosion");
				byte var17 = 0;
				ArrayList var20 = new ArrayList();

				for (var21 = 0; var21 < par1InventoryCrafting.getSizeInventory(); ++var21)
				{
					ItemStack var14 = par1InventoryCrafting.getStackInSlot(var21);

					if (var14 != null)
					{
						if (var14.itemID == Item.dyePowder.itemID)
						{
							var20.add(Integer.valueOf(ItemDye.dyeColors[var14.getItemDamage()%16]));
						}
						else if (var14.itemID == Item.lightStoneDust.itemID)
						{
							var16.setBoolean("Flicker", true);
						}
						else if (var14.itemID == Item.diamond.itemID)
						{
							var16.setBoolean("Trail", true);
						}
						else if (var14.itemID == Item.fireballCharge.itemID)
						{
							var17 = 1;
						}
						else if (var14.itemID == Item.feather.itemID)
						{
							var17 = 4;
						}
						else if (var14.itemID == Item.goldNugget.itemID)
						{
							var17 = 2;
						}
						else if (var14.itemID == Item.skull.itemID)
						{
							var17 = 3;
						}
					}
				}

				int[] var24 = new int[var20.size()];

				for (int var23 = 0; var23 < var24.length; ++var23)
				{
					var24[var23] = ((Integer)var20.get(var23)).intValue();
				}

				var16.setIntArray("Colors", var24);
				var16.setByte("Type", var17);
				var15.setTag("Explosion", var16);
				this.field_92102_a.setTagCompound(var15);
				return true;
			}
			else if (var4 == 0 && var3 == 0 && var6 == 1 && var5 > 0 && var5 == var7)
			{
				ArrayList var11 = new ArrayList();

				for (var12 = 0; var12 < par1InventoryCrafting.getSizeInventory(); ++var12)
				{
					var13 = par1InventoryCrafting.getStackInSlot(var12);

					if (var13 != null)
					{
						if (var13.itemID == Item.dyePowder.itemID)
						{
							var11.add(Integer.valueOf(ItemDye.dyeColors[var13.getItemDamage()%16]));
						}
						else if (var13.itemID == Item.fireworkCharge.itemID)
						{
							this.field_92102_a = var13.copy();
							this.field_92102_a.stackSize = 1;
						}
					}
				}

				int[] var18 = new int[var11.size()];

				for (var21 = 0; var21 < var18.length; ++var21)
				{
					var18[var21] = ((Integer)var11.get(var21)).intValue();
				}

				if (this.field_92102_a != null && this.field_92102_a.hasTagCompound())
				{
					NBTTagCompound var22 = this.field_92102_a.getTagCompound().getCompoundTag("Explosion");

					if (var22 == null)
					{
						return false;
					}
					else
					{
						var22.setIntArray("FadeColors", var18);
						return true;
					}
					}
					else
					{
						return false;
					}
					}
					else
					{
						return false;
					}
				}
			}
			else
			{
				return false;
			}
		}

		/**
		* Returns an Item that is the result of this recipe
		*/
		public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
		{
			return this.field_92102_a.copy();
		}

		/**
		* Returns the size of the recipe area
		*/
		public int getRecipeSize()
		{
			return 10;
		}

		public ItemStack getRecipeOutput()
		{
			return this.field_92102_a;
		}

		public boolean matches(IRecipe var1)
		{
			return false;
		}
	}
}
