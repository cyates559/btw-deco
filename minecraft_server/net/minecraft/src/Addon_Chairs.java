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
			ItemAxe.SetAllAxesToBeEffectiveVsBlock(this);
			setStepSound(soundWoodFootstep);
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
	}
}
