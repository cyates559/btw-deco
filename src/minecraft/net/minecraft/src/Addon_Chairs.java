package net.minecraft.src;
public class Addon_Chairs
{
	public static Block birchWoodChair, spruceWoodChair, jungleWoodChair, oakWoodChair;
	public Addon_Chairs()
	{
		oakWoodChair = new BlockChairWood(3036, "oak", "Oak");
		birchWoodChair = new BlockChairWood(3037, "birch", "Birch");
		spruceWoodChair = new BlockChairWood(3038, "spruce", "Spruce");
		jungleWoodChair = new BlockChairWood(3039, "jungle", "Jungle");
		FCRecipes.AddVanillaRecipe(new ItemStack(oakWoodChair, 4), new Object[] {"#  ", "###","X X", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 0)});
		FCRecipes.AddVanillaRecipe(new ItemStack(birchWoodChair, 4), new Object[] {"#  ", "###","X X", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 2), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 2)});
		FCRecipes.AddVanillaRecipe(new ItemStack(spruceWoodChair, 4), new Object[] {"#  ", "###","X X", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 1), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 1)});
		FCRecipes.AddVanillaRecipe(new ItemStack(jungleWoodChair, 4), new Object[] {"#  ", "###","X X", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 3), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 3)});
	}
	public static class BlockChairWood extends BlockChair
	{
		public BlockChairWood(int ID, String WoodType, String WoodName)
		{
			super(ID, Material.wood, WoodType + "wood", WoodName + " Wood");
			this.SetAxesEffectiveOn( true );
			setStepSound(soundWoodFootstep);
		}
	}
	public static class BlockChairStone extends BlockChair
	{
		public BlockChairStone(int ID, String tag, String name)
		{
			super(ID, Material.rock, tag, name);
			this.SetAxesEffectiveOn(true);
			setStepSound(soundStoneFootstep);
		}
	}
	public static class BlockChair extends Block implements FCIBlock
	{
		public BlockChair(int ID, Material MyMaterial, String Tag, String Name)
		{
			super(ID, MyMaterial);
			setUnlocalizedName("ginger_chair_" + Tag);
			setCreativeTab(CreativeTabs.tabDecorations);
			AddonManager.Register(this, Name + " Chair");
		}
		public int GetFacing(IBlockAccess var1, int var2, int var3, int var4)
		{
			return var1.getBlockMetadata(var2, var3, var4);
		}
		public void SetFacing(World var1, int var2, int var3, int var4, int var5)
		{
			var1.setBlockMetadataWithNotify(var2, var3, var4, var5);
		}
		public int GetFacingFromMetadata(int Meta)
		{
			return Meta;
		}
		public int SetFacingInMetadata(int var1, int var2)
		{
			return var2;
		}
		public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
		{
			return true;
		}
		public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
		{
			return false;
		}
		public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
		{
			return false;
		}
		public void RotateAroundJAxis(World var1, int var2, int var3, int var4, boolean var5)
		{
			FCUtilsMisc.StandardRotateAroundJ(this, var1, var2, var3, var4, var5);
		}
		public int RotateMetadataAroundJAxis(int var1, boolean var2)
		{
			return FCUtilsMisc.StandardRotateMetadataAroundJ(this, var1, var2);
		}
		public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
		{
			this.RotateAroundJAxis(var1, var2, var3, var4, var5);
			return true;
		}
		public boolean isOpaqueCube()
		{
			return false;
		}
		public boolean renderAsNormalBlock()
		{
			return false;
		}
		public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
		{
			if (var5 < 2)
			{
				var5 = 2;
			} else
			{
				var5 = FCUtilsMisc.GetOppositeFacing(var5);
			}
			return SetFacingInMetadata(var9, var5);
		}
		public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
		{
			int var7 = FCUtilsMisc.ConvertPlacingEntityOrientationToBlockFacing(var5);
			this.SetFacing(var1, var2, var3, var4, var7);
			
		}
		public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
		{
			int var5 = this.GetFacing(var1, var2, var3, var4);
			return var5 != 2 && var5 != 3 ? AxisAlignedBB.getAABBPool().getAABB((double) ((float) var2), (double) ((float) var3), (double) ((float) var4 + 0.5F - 0.25F), (double) ((float) var2 + 1.0F), (double) ((float) var3 + 1.0F), (double) ((float) var4 + 0.5F + 0.25F)) : AxisAlignedBB.getAABBPool().getAABB((double) ((float) var2 + 0.5F - 0.25F), (double) ((float) var3), (double) ((float) var4), (double) ((float) var2 + 0.5F + 0.25F), (double) ((float) var3 + 1.0F), (double) ((float) var4 + 1.0F));
		}
		public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
		{
			setBlockBounds(.0625F, 0.0F, .0625F, .9375F, 1.25F, .9375F);
		}
		public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
		{
			return true;
		}

		//CLIENT ONLY
		public void SetRenderBoundsRotatedAboutJToFacing(RenderBlocks var1, float var2, float var3, float var4, float var5, float var6, float var7, int var8)
		{
			float var9;
			float var10;
			float var11;
			float var12;
			if (var8 == 4)
			{
				var9 = 1.0F - var5;
				var10 = 1.0F - var7;
				var11 = 1.0F - var2;
				var12 = 1.0F - var4;
			} else if (var8 == 3)
			{
				var9 = var4;
				var10 = var2;
				var11 = var7;
				var12 = var5;
			} else if (var8 == 2)
			{
				var9 = 1.0F - var7;
				var10 = 1.0F - var5;
				var11 = 1.0F - var4;
				var12 = 1.0F - var2;
			} else
			{
				var9 = var2;
				var10 = var4;
				var11 = var5;
				var12 = var7;
			}
			var1.setRenderBounds((double) var9, (double) var3, (double) var10, (double) var11, (double) var6, (double) var12);
		}


		public boolean RenderBlock(RenderBlocks Render, int X, int Y, int Z)
		{
			int Facing = GetFacing(Render.blockAccess, X, Y, Z);
			SetRenderBoundsRotatedAboutJToFacing(Render, .0625F, 0F, .0625F, .1875F, 0.5F, .1875F, Facing);
			Render.renderStandardBlock(this, X, Y, Z);
			SetRenderBoundsRotatedAboutJToFacing(Render, .8125F, 0F, .0625F, .9375F, 0.5F, .1875F, Facing);
			Render.renderStandardBlock(this, X, Y, Z);
			SetRenderBoundsRotatedAboutJToFacing(Render, .0625F, 0F, .8125F, .1875F, 0.5F, .9375F, Facing);
			Render.renderStandardBlock(this, X, Y, Z);
			SetRenderBoundsRotatedAboutJToFacing(Render, .8125F, 0F, .8125F, .9375F, 0.5F, .9375F, Facing);
			Render.renderStandardBlock(this, X, Y, Z);
			SetRenderBoundsRotatedAboutJToFacing(Render, .0625F, .5F, .0625F, .9375F, .625F, .9375F, Facing);
			Render.renderStandardBlock(this, X, Y, Z);
			SetRenderBoundsRotatedAboutJToFacing(Render, .0625F, .625F, .0625F, .1875F, 1.25F, .9375F, Facing);
			Render.renderStandardBlock(this, X, Y, Z);
			return true;
		}
		public void RenderBlockAsItem(RenderBlocks Render, int var2, float var3)
		{
			Render.setRenderBounds(.0625D, 0D, .0625D, .1875D, 0.5D, .1875D);
			FCClientUtilsRender.RenderInvBlockWithTexture(Render, this, -0.5F, -0.5F, -0.5F, blockIcon);
			Render.setRenderBounds(.8125D, 0D, .0625D, .9375D, 0.5D, .1875D);
			FCClientUtilsRender.RenderInvBlockWithTexture(Render, this, -0.5F, -0.5F, -0.5F, blockIcon);
			Render.setRenderBounds(.0625D, 0D, .8125D, .1875D, 0.5D, .9375D);
			FCClientUtilsRender.RenderInvBlockWithTexture(Render, this, -0.5F, -0.5F, -0.5F, blockIcon);
			Render.setRenderBounds(.8125D, 0D, .8125D, .9375D, 0.5D, .9375D);
			FCClientUtilsRender.RenderInvBlockWithTexture(Render, this, -0.5F, -0.5F, -0.5F, blockIcon);
			Render.setRenderBounds(.0625D, .5D, .0625D, .9375D, .625D, .9375D);
			FCClientUtilsRender.RenderInvBlockWithTexture(Render, this, -0.5F, -0.5F, -0.5F, blockIcon);
			Render.setRenderBounds(.0625D, .625D, .0625D, .1875D, 1.25D, .9375D);
			FCClientUtilsRender.RenderInvBlockWithTexture(Render, this, -0.5F, -0.5F, -0.5F, blockIcon);
		}
	}
}
