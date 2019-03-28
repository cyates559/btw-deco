package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

public class Addon_Lanterns
{
	public static BlockPane paperWall, fenceSteel;
	public static Block lanternPaper, lanternSteel, lanternGold;
	public static Item bottleHempOil;
	public Addon_Lanterns()
	{
		//FCBetterThanWolves.fcAestheticNonOpaque = new FCBlockAestheticNonOpaque_LightningRodFix(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcAestheticNonOpaque));
		paperWall = new BlockPaperWall(3000);
		fenceSteel = new BlockFenceSteel(3001);

		lanternPaper = new BlockLantern(3027,Material.wood,.3F,"paper","Firefly Lantern",true).setStepSound(Block.soundWoodFootstep);
		lanternPaper.SetAxesEffectiveOn(true);
		lanternGold = new BlockLanternGold(3028);
		lanternSteel = new BlockLantern(3029,Material.iron,.5F,"steel","Wrought Iron Lantern").setStepSound(Block.soundStoneFootstep);
		lanternSteel.SetPicksEffectiveOn(true);

		bottleHempOil = new Item(30007).setUnlocalizedName("ginger_bottle_hempoil").setCreativeTab(CreativeTabs.tabMaterials);

		AddonManager.Register(paperWall, "Paper Wall");
		AddonManager.Register(fenceSteel, "Wrought Iron Bars");
		AddonManager.Name(bottleHempOil, "Hemp Oil");

		FCRecipes.AddRecipe(new ItemStack(paperWall, 4), new Object[] { "ppp", "pwp", "ppp", 'p', Item.paper, 'w', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767) });
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(Item.ingotIron, 1), new ItemStack[] { new ItemStack(fenceSteel, 1) });
		FCRecipes.AddAnvilRecipe(new ItemStack(fenceSteel, 10), new Object[] { " X X", "XXXX", " X X", " X X", 'X', new ItemStack(Item.ingotIron) });

		FCRecipes.AddShapelessRecipe(new ItemStack(bottleHempOil,1), new Object[]{Item.glassBottle, FCBetterThanWolves.fcItemHempSeeds});
		FCRecipes.AddRecipe(new ItemStack(lanternPaper,1),new Object[]{"pwp","wcw","pwp",'c', new ItemStack(FCBetterThanWolves.fcItemCandle,1,32767), 'p', Item.paper, 'w', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767)});
		FCRecipes.AddAnvilRecipe(new ItemStack(lanternGold,2), new Object[]{" ss "," gg ","cggc","cggc",'s',Block.stone,'g',Item.goldNugget,'c', new ItemStack(FCBetterThanWolves.fcItemCandle,1,32767)});
		FCRecipes.AddRecipe(new ItemStack(lanternSteel,1),new Object[]{" s ","shs"," s ",'s',fenceSteel,'h',bottleHempOil});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(Item.ingotIron,4), new ItemStack[]{new ItemStack(lanternSteel,1)});
	}
	/*public static class BlockSteelFence extends Block implements FCIBlock
	{
		public BlockSteelFence(int id, Material material)
		{
			super(id, material);
		}
		public int GetFacing(IBlockAccess var1, int var2, int var3, int var4)
		{
		}

		public void SetFacing(World var1, int var2, int var3, int var4, int var5)
		{
		}

		public int GetFacing(int var1)
		{
		}

		public int SetFacing(int var1, int var2)
		{
		}

		public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
		{
		}

		public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
		{
		}

		public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
		{
		}

		public void RotateAroundJAxis(World var1, int var2, int var3, int var4, boolean var5)
		{
		}

		public int RotateMetadataAroundJAxis(int var1, boolean var2)
		{
		}

		public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
		{
		}
	}*/
	public static class BlockLanternGold extends Block
	{
		public BlockLanternGold(int ID)
		{
			super(ID, Material.iron);
			setUnlocalizedName("ginger_lantern_gold");
			setStepSound(soundStoneFootstep);
			//setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
			setCreativeTab(CreativeTabs.tabDecorations);
			setHardness(0.3F);
			setLightValue(1F);
			AddonManager.Register(this, "Chandelier");
			this.SetPicksEffectiveOn(true);
			this.InitBlockBounds(.3125D,	0.0D,	.3125D,		.6875D,		.5D,	.6875D);
		}
		@Override public boolean isOpaqueCube()
		{
			return false;
		}
		@Override public boolean renderAsNormalBlock()
		{
			return false;
		}
		@Override public int getRenderType()
		{
			return 1;
		}
//CLIENT ONLY
		@Override public void randomDisplayTick(World CurrentWorld, int X_, int Y_, int Z_, Random par5Random)
		{
			float H = .55F, L = .15F, X = (float)X_, Y = (float)Y_, Z = (float)Z_;

			CurrentWorld.spawnParticle("smoke", X+L, Y+H, Z+L, 0.0D, 0.0D, 0.0D);
			CurrentWorld.spawnParticle("flame", X+L, Y+H, Z+L, 0.0D, 0.0D, 0.0D);

			CurrentWorld.spawnParticle("smoke", X+1F-L, Y+H, Z+L, 0.0D, 0.0D, 0.0D);
			CurrentWorld.spawnParticle("flame", X+1F-L, Y+H, Z+L, 0.0D, 0.0D, 0.0D);

			CurrentWorld.spawnParticle("smoke", X+L, Y+H, Z+1F-L, 0.0D, 0.0D, 0.0D);
			CurrentWorld.spawnParticle("flame", X+L, Y+H, Z+1F-L, 0.0D, 0.0D, 0.0D);

			CurrentWorld.spawnParticle("smoke", X+1F-L, Y+H, Z+1F-L, 0.0D, 0.0D, 0.0D);
			CurrentWorld.spawnParticle("flame", X+1F-L, Y+H, Z+1F-L, 0.0D, 0.0D, 0.0D);
		}
		@Override public boolean RenderBlock(RenderBlocks Render, int X, int Y, int Z)
		{
			Render.setRenderBounds(.375D, .875D, .375D, .625D, 1D, .625D);
			Render.renderStandardBlock(Block.stone, X, Y, Z);
			Render.renderCrossedSquares(this, X, Y, Z);
			return true;
		}
//
	}
	public static class BlockLantern extends Block
	{
		String tag;
		boolean animate;
		public BlockLantern(int ID, Material material,float hardness, String tag, String name){this(ID,material,hardness,tag,name,false);}
		public BlockLantern(int ID, Material material,float hardness, String tag, String name,boolean animate)
		{
			super(ID, material);
			this.tag=tag;
			this.animate=animate;
			setUnlocalizedName("ginger_lantern_"+tag);
			setCreativeTab(CreativeTabs.tabDecorations);
			setHardness(hardness);
			setLightValue(1F);
			//setBlockBounds(.3125F, 0F, .3125F, .6875F, .5F, .6875F);
			AddonManager.Register(this, name);
			//FCBlockAestheticNonOpaque_LightningRodFix.AddHoldableBlock(this);
			this.InitBlockBounds(.3125D,	0.0D,	.3125D,		.6875D,		.5D,	.6875D);
		}
		@Override public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
		{
			GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4);
			return SetFacing(var9, var5);
		}
		@Override public boolean isOpaqueCube()
		{
			return false;
		}
		@Override public boolean renderAsNormalBlock()
		{
			return false;
		}

		public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess Access, int x, int y, int z)
		{
			double[] bounds = new double[6];
			double[] bounds0 = {.3125D,	.5D,	.3125D,		.6875D,		1D,		.6875D};
			double[] bounds1 = {.3125D,	0.0D,	.3125D,		.6875D,		.5D,	.6875D};
			double[] bounds2 = {.3125D,	0.0D,	.625D,		.6875D,		.5D,	1D};
			double[] bounds3 = {.3125D,	0.0D,	0.0D,		.6875D,		.5D,	.375D};
			double[] bounds4 = {.625D,	0.0D,	.3125D,		1D,			.5D,	.6875D};
			double[] bounds5 = {0.0D,		0.0D,	.3125D,		.375D,		.5D,	.6875D};

			switch (GetFacing(Access, x, y, z))
			{
				default:
				case 0: bounds = bounds0;
					break;
				case 1: bounds = bounds1;
					break;
				case 2: bounds = bounds2;
					break;
				case 3: bounds = bounds3;
					break;
				case 4: bounds = bounds4;
					break;
				case 5: bounds = bounds5;
			}

			return AxisAlignedBB.getAABBPool().getAABB(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
		}

		public AxisAlignedBB GetBlockBoundsFromPoolForItemRender()
		{
			return AxisAlignedBB.getAABBPool().getAABB(.3125D,	0.0D,	.3125D,		.6875D,		.5D,	.6875D);
		}
		@Override public int GetFacing(IBlockAccess var1, int var2, int var3, int var4)
		{
			return var1.getBlockMetadata(var2, var3, var4);
		}
		@Override public void SetFacing(World var1, int var2, int var3, int var4, int var5)
		{
			var1.setBlockMetadataWithNotify(var2, var3, var4, var5);
		}
		@Override public int GetFacing(int var1)
		{
			return var1;
		}
		@Override public int SetFacing(int var1, int var2)
		{
			return var2;
		}
		@Override public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
		{
			return false;
		}
		@Override public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
		{
			return false;
		}
		@Override public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
		{
			return false;
		}
		@Override public boolean RotateAroundJAxis(World var1, int var2, int var3, int var4, boolean var5)
		{
			return false;
		}
		@Override public int RotateMetadataAroundJAxis(int var1, boolean var2)
		{
			return 0;
		}
		@Override public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
		{
			return false;
		}
//CLIENT ONLY
		Icon TopIcon;
		@Override public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
		{
			//return var5 == 0 && this.minY > 0.0D ? true : (var5 == 1 && this.maxY < 1.0D ? true : (var5 == 2 && this.minZ > 0.0D ? true : (var5 == 3 && this.maxZ < 1.0D ? true : (var5 == 4 && this.minX > 0.0D ? true : (var5 == 5 && this.maxX < 1.0D ? true : !var1.isBlockOpaqueCube(var2, var3, var4))))));
			return true;
		}
		@Override public void registerIcons(IconRegister Register)
		{
			blockIcon = Register.registerIcon("ginger_lantern_"+tag+(animate?"_anim":""));
			TopIcon = Register.registerIcon("ginger_lantern_"+tag+"_top");
		}
		@Override public Icon getIcon(int side, int meta)
		{
			return side < 2 ? TopIcon : blockIcon;
		}
	}
//
	/*public static class FCBlockAestheticNonOpaque_LightningRodFix extends FCBlockAestheticNonOpaque
	{
		static ArrayList BlocksThatCanBeHeld = new ArrayList();
		public FCBlockAestheticNonOpaque_LightningRodFix(int ID)
		{
			super(ID);
			setCreativeTab(CreativeTabs.tabDecorations);
			this.SetAxesEffectiveOn(true);
			this.SetPicksEffectiveOn(true);
		}
		public static void AddHoldableBlock(Block B)
		{
			AddHoldableBlockID(B.blockID);
		}
		public static void AddHoldableBlockID(int ID)
		{
			BlocksThatCanBeHeld.add(ID);
		}
//CLIENT ONLY
		public Icon LightningRodIcon;
		@Override public void registerIcons(IconRegister Register)
		{
			super.registerIcons(Register);
		}
		@Override public boolean RenderLightningRod(RenderBlocks var1, IBlockAccess BlockAccess, int X, int Y, int Z, Block var6)
		{
			var1.setRenderBounds(0.46875D, 0.0D, 0.46875D, 0.53125D, 1.0D, 0.53125D);
			FCClientUtilsRender.RenderStandardBlockWithTexture(var1, var6, X, Y, Z, LightningRodIcon);

			if (BlockAccess.getBlockId(X, Y - 1, Z) != FCBetterThanWolves.fcAestheticNonOpaque.blockID || BlockAccess.getBlockMetadata(X, Y - 1, Z) != 12)
			{
				var1.setRenderBounds(0.375D, 0.0D, 0.375D, 0.625D, 0.125D, 0.625D);
				FCClientUtilsRender.RenderStandardBlockWithTexture(var1, var6, X, Y, Z, LightningRodIcon);
			}

			int var8 = BlockAccess.getBlockId(X, Y + 1, Z);
			int var9 = BlockAccess.getBlockMetadata(X, Y + 1, Z);

			/* if(
				BlockAccess.getBlockId(X+1, Y, Z) == fenceSteel.blockID ||
			BlockAccess.getBlockId(X-1, Y, Z) == fenceSteel.blockID ||
			BlockAccess.getBlockId(X, Y, Z+1) == fenceSteel.blockID ||
			BlockAccess.getBlockId(X, Y, Z-1) == fenceSteel.blockID)
			{
			var1.renderBlockPane(fenceSteel, X, Y, Z);
			}*/
			/*
			if (var8 != FCBetterThanWolves.fcAestheticNonOpaque.blockID || var9 != 12)
			{
				if (var8 == FCBetterThanWolves.fcBlockCandle.blockID || (BlocksThatCanBeHeld.contains(var8) &&  (var8==lanternPaper.blockID?var9 == 1:true)))
				{
					var1.setRenderBounds(0.375D, 0.99609375D, 0.375D, 0.625D, 1.05859375D, 0.625D);
				}
				else
				{
					var1.setRenderBounds(0.40625D, 0.625D, 0.40625D, 0.59375D, 0.8125D, 0.59375D);
				}

				FCClientUtilsRender.RenderStandardBlockWithTexture(var1, var6, X, Y, Z, LightningRodIcon);
			}

			return true;
		}
//

	}*/
	public static class BlockFenceSteel extends FCBlockPane
	{
		public BlockFenceSteel(int ID)
		{
			super(ID, "ginger_panel_wroughtbars_face", "ginger_panel_wroughtbars_side", Material.iron, true);
			setHardness(5.0F);
			setResistance(10.0F);
			setStepSound(Block.soundMetalFootstep);
			setUnlocalizedName("fenceSteel");
			setCreativeTab(CreativeTabs.tabDecorations);
		}
	}
	public static class BlockPaperWall extends FCBlockPane
	{
		public BlockPaperWall(int ID)
		{
			super(ID, "ginger_panel_paperwall_face", "ginger_panel_paperwall_side", Material.iron, true);
			setHardness(0.3F);
			setResistance(1.0F);
			setStepSound(Block.soundWoodFootstep);
			setUnlocalizedName("paperWall");
			setCreativeTab(CreativeTabs.tabDecorations);
		}
	}
}
