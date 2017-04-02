package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class Addon_Flowers
{
	public static Block flower, tulip;
	public static Item fertilizer;
	public Addon_Flowers()
	{
		Item.m_bSuppressConflictWarnings=true;
		Item.dyePowder = new FCItemDye_ColorPlus(95);
		Item.m_bSuppressConflictWarnings=false;
		
		FCBetterThanWolves.fcPlanter = new BlockPlanter(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcPlanter));
		flower = new BlockFlowers(3002, "flower",
			new String[]{ "yucca", "hyacinth", "birdsParadise", "azalea", "cornFlower", "lavender", "honeysuckle","allium", "orchidBlue", "poppy", "azureBluet", "daisy", "peony","lilac","rosebush"},
			new String[]{ "Yucca", "Hyacinth", "Birds of Paradise", "Azaleas", "Cornflower", "Lavender", "Honeysuckle", "Allium","Blue Orchid", "Poppy", "Azure Bluet", "Daisy", "Peony", "Lilac", "Rose Bush"});
		tulip = new BlockFlowers(3006, "tulip",
			new String[] { "red", "pink", "orange", "white"},
			new String[] { "Red", "Pink", "Orange", "White"}, " Tulip");
		
		fertilizer = new ItemFertilizer(30003);
		

//recipes//
		//Change flowerpot recipe
		FCRecipes.RemoveVanillaRecipe(new ItemStack(Item.flowerPot, 1), new Object[] {"# #", " # ", '#', Item.brick});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Item.flowerPot, 1), new ItemStack[]{new ItemStack(FCBetterThanWolves.fcItemPileDirt), new ItemStack(FCBetterThanWolves.fcUrn)});
		for (int I = 0; I < 16; ++I)
			FCRecipes.AddShapelessVanillaRecipe(new ItemStack(Block.cloth, 1, BlockCloth.getDyeFromBlock(I)), new Object[] {new ItemStack(Item.dyePowder, 1, I+16), new ItemStack(Item.itemsList[Block.cloth.blockID], 1, 0)});
		
		//Fertilizer
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(fertilizer, 2), new ItemStack[] { new ItemStack(Item.dyePowder, 1, 15), new ItemStack(FCBetterThanWolves.fcDung) });

		//New millstone recipes, flowers
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 10)},	new ItemStack[]{new ItemStack(flower, 1, 0)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 12)},	new ItemStack[]{new ItemStack(flower, 1, 1)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 14)},	new ItemStack[]{new ItemStack(flower, 1, 2)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 9) },	new ItemStack[]{new ItemStack(flower, 1, 3)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 20)},	new ItemStack[]{new ItemStack(flower, 1, 4)});//newdye
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 5) },	new ItemStack[]{new ItemStack(flower, 1, 5)});//newdye maybe
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 1, 31),new ItemStack(Item.dyePowder, 1, 11)},new ItemStack[]{new ItemStack(flower, 1, 6)});//newdye maybe
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 13)},	new ItemStack[]{new ItemStack(flower, 1, 7)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 12)},	new ItemStack[]{new ItemStack(flower, 1, 8)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 1) },	new ItemStack[]{new ItemStack(flower, 1, 9)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 1, 31),new ItemStack(Item.dyePowder, 1, 11)},new ItemStack[]{new ItemStack(flower, 1, 10)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 1, 31),new ItemStack(Item.dyePowder, 1, 11)},new ItemStack[]{new ItemStack(flower, 1, 11)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 13)},	new ItemStack[]{new ItemStack(flower, 1, 12)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 13)},	new ItemStack[]{new ItemStack(flower, 1, 13)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 3, 1)},	new ItemStack[]{new ItemStack(flower, 1, 14)});
		//tulips
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 1, 14),new ItemStack(Item.dyePowder, 1, 1)},	new ItemStack[]{new ItemStack(tulip, 1, 0)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 9)},	new ItemStack[]{new ItemStack(tulip, 1, 1)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 14)},	new ItemStack[]{new ItemStack(tulip, 1, 2)});
		FCRecipes.AddMillStoneRecipe(new ItemStack[]{new ItemStack(Item.dyePowder, 2, 31)},	new ItemStack[]{new ItemStack(tulip, 1, 3)});
		
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
		Icon[] Icons;
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
		public void registerIcons(IconRegister Register)
		{
			for (int Index = 0; Index < Icons.length; Index++)
			{
				Icons[Index] = Register.registerIcon("ginger_"+TextureTag+"_" + Index);
			}
		}
		public int damageDropped(int Meta)
		{
			return Meta;
		}
		public Icon getIcon(int Side, int Meta)
		{
			return Icons[Meta];
		}
	}
	public static class FCItemDye_ColorPlus extends FCItemDye
	{
		public Icon[] ExtraIcons = new Icon[16];
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
		public String getUnlocalizedName(ItemStack I)
		{
			return super.getUnlocalizedName() + "." + ColorPlus_dyeColorNames[I.getItemDamage()&31];
		}
		public Icon getIconFromDamage(int Meta)
		{
			return Meta>15 ? ExtraIcons[Meta-16] : super.getIconFromDamage(Meta);
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
						int R = itemRand.nextInt(27);
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
								CurrentWorld.setBlockAndMetadataWithNotify(X, Y+1, Z, flower.blockID, R);
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
}
