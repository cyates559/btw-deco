package net.minecraft.src;
public class Addon_WhiteBrick
{
	public static Block whiteStoneBrick, whiteBrickMouldingAndDecorative, whiteBrickSidingAndCorner, whiteBrickStairs;
	public Addon_WhiteBrick()
	{
		whiteStoneBrick = new BlockStoneBrick(3008);
		whiteBrickSidingAndCorner = new AddonManager.FCBlockSidingAndCornerAndDecorative_Wall(3009, Material.rock, "ginger_bricks_white_0_decorative", 2.0F, 5.0F, Block.soundWoodFootstep, "whiteBrickSiding", "White Brick");
		whiteBrickMouldingAndDecorative = new FCBlockMouldingAndDecorative(3010, Material.rock, "ginger_bricks_white_0_decorative", "ginger_bricks_white_0_column", 3042, 2.0F, 5.0F, Block.soundWoodFootstep, "whiteBrickMolding");
		whiteBrickStairs = new FCBlockStairs(3011, whiteStoneBrick, 0).setUnlocalizedName("stairsWhiteBrick");

		Item.itemsList[whiteBrickSidingAndCorner.blockID] = new FCItemBlockSidingAndCorner(whiteBrickSidingAndCorner.blockID - 256);
		Item.itemsList[whiteBrickMouldingAndDecorative.blockID] = new FCItemBlockMouldingAndDecorative(whiteBrickMouldingAndDecorative.blockID - 256);
		AddonManager.Register(whiteBrickStairs, "White Stone Brick Stairs");

		AddonManager.NameSubBlocks_Wall(whiteBrickSidingAndCorner, whiteBrickMouldingAndDecorative, "White Stone Brick");
	
		FCRecipes.AddVanillaRecipe(new ItemStack(whiteStoneBrick,4,0), new Object[]{"XX","XX",'X',new ItemStack(FCBetterThanWolves.fcAestheticOpaque,1,9)});
		FCRecipes.AddAnvilRecipe(new ItemStack(whiteStoneBrick, 12, 3), new Object[] {"####", "#  #", "#  #", "####", '#', whiteStoneBrick});
		FCRecipes.AddSubBlockRecipesOfType(whiteStoneBrick, 0, whiteBrickSidingAndCorner, whiteBrickMouldingAndDecorative, true);
		FCRecipes.AddVanillaRecipe(new ItemStack(whiteBrickStairs), new Object[] {"# ", "##", '#', new ItemStack(whiteBrickMouldingAndDecorative)});
	}
	public static class BlockStoneBrick extends Block
	{
		public BlockStoneBrick(int ID)
		{
			super(ID, Material.rock);
			setHardness(1.5F);
			setResistance(10.0F);
			setStepSound(Block.soundStoneFootstep);
			ItemPickaxe.SetAllPicksToBeEffectiveVsBlock(this);
			setUnlocalizedName("whiteStoneBrick");
			setCreativeTab(CreativeTabs.tabBlock);
			AddonManager.Register(this, new String[] { "regular", "mossy", "cracked", "chiseled" }, new String[] { "White Stone Bricks", "Mossy White Stone Bricks", "Cracked White Stone Bricks", "Chiseled White Stone" });
		}
		public int damageDropped(int Meta)
		{
			return Meta;
		}
//CLIENT ONLY
		public static Icon[] Icons = new Icon[4];
		public void registerIcons(IconRegister Register)
		{
			for (int Index = 0; Index < 4; Index++)
			{
				Icons[Index] = Register.registerIcon("ginger_bricks_white_" + Index);
			}
		}
		public Icon getIcon(int Side, int Meta)
		{
			return Icons[Meta];
		}
//
	}
	public static class FCBlockSidingAndCornerAndDecorative_Wall extends FCBlockSidingAndCornerAndDecorative
	{

		public FCBlockSidingAndCornerAndDecorative_Wall(int var1, Material var2, String var3, float var4, float var5, StepSound var6, String var7, String OriginalName)
		{
			super(var1, var2, var3, var4, var5, var6, var7);
			setCreativeTab(CreativeTabs.tabDecorations);
			AddonManager.Name(getUnlocalizedName() + ".fence" + ".name", OriginalName + " Wall");
		}
		public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
		{

			if(par1IBlockAccess.getBlockMetadata(par2,par3,par4) != 14)
			{
				super.setBlockBoundsBasedOnState(par1IBlockAccess, par2, par3, par4);
				return;
			}

			boolean var5 = this.DoesFenceConnectTo(par1IBlockAccess, par2, par3, par4 - 1);
			boolean var6 = this.DoesFenceConnectTo(par1IBlockAccess, par2, par3, par4 + 1);
			boolean var7 = this.DoesFenceConnectTo(par1IBlockAccess, par2 - 1, par3, par4);
			boolean var8 = this.DoesFenceConnectTo(par1IBlockAccess, par2 + 1, par3, par4);
			float var9 = 0.25F;
			float var10 = 0.75F;
			float var11 = 0.25F;
			float var12 = 0.75F;
			float var13 = 1.0F;

			if (var5)
			{
				var11 = 0.0F;
			}

			if (var6)
			{
				var12 = 1.0F;
			}

			if (var7)
			{
				var9 = 0.0F;
			}

			if (var8)
			{
				var10 = 1.0F;
			}

			if (var5 && var6 && !var7 && !var8)
			{
				var13 = 0.8125F;
				var9 = 0.3125F;
				var10 = 0.6875F;
			}
			else if (!var5 && !var6 && var7 && var8)
			{
				var13 = 0.8125F;
				var11 = 0.3125F;
				var12 = 0.6875F;
			}

			this.setBlockBounds(var9, 0.0F, var11, var10, var13, var12);
		}
//CLIENT ONLY
		public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
		{
			IBlockAccess var5 = var1.blockAccess;
			int var6 = var5.getBlockMetadata(var2, var3, var4);
			return var6 == 12 ? RenderBench(var1, var5, var2, var3, var4, this) : (var6 == 14 ? RenderFence(var1, var5, var2, var3, var4, this) : super.RenderBlock(var1, var2, var3, var4));
		}
		public static boolean RenderFence(RenderBlocks Render, IBlockAccess blockAccess, int X, int Y, int Z, Block block)
		{
			FCBlockSidingAndCornerAndDecorative me = (FCBlockSidingAndCornerAndDecorative)block;

			boolean var5 = me.DoesFenceConnectTo(blockAccess, X - 1, Y, Z);
			boolean var6 = me.DoesFenceConnectTo(blockAccess, X + 1, Y, Z);
			boolean var7 = me.DoesFenceConnectTo(blockAccess, X, Y, Z - 1);
			boolean var8 = me.DoesFenceConnectTo(blockAccess, X, Y, Z + 1);
			boolean var9 = var7 && var8 && !var5 && !var6;
			boolean var10 = !var7 && !var8 && var5 && var6;
			boolean var11 = blockAccess.isAirBlock(X, Y + 1, Z);

			if ((var9 || var10) && var11)
			{
				if (var9)
				{
					Render.setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, 0.8125D, 1.0D);
					Render.renderStandardBlock(me, X, Y, Z);
				}
				else
				{
					Render.setRenderBounds(0.0D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
					Render.renderStandardBlock(me, X, Y, Z);
				}
			}
			else
			{
				Render.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
				Render.renderStandardBlock(me, X, Y, Z);

				if (var5)
				{
					Render.setRenderBounds(0.0D, 0.0D, 0.3125D, 0.25D, 0.8125D, 0.6875D);
					Render.renderStandardBlock(me, X, Y, Z);
				}

				if (var6)
				{
					Render.setRenderBounds(0.75D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
					Render.renderStandardBlock(me, X, Y, Z);
				}

				if (var7)
				{
					Render.setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, 0.8125D, 0.25D);
					Render.renderStandardBlock(me, X, Y, Z);
				}

				if (var8)
				{
					Render.setRenderBounds(0.3125D, 0.0D, 0.75D, 0.6875D, 0.8125D, 1.0D);
					Render.renderStandardBlock(me, X, Y, Z);
				}
			}

			me.setBlockBoundsBasedOnState(Render.blockAccess, X, Y, Z);
			return true;
		}
//
	}
}
