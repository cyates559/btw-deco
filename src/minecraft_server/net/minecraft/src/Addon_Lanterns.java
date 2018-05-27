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
		FCBetterThanWolves.fcAestheticNonOpaque = new FCBlockAestheticNonOpaque_LightningRodFix(AddonManager.ReplaceBlockID(FCBetterThanWolves.fcAestheticNonOpaque));
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

		FCRecipes.AddVanillaRecipe(new ItemStack(paperWall, 4), new Object[] { "ppp", "pwp", "ppp", 'p', Item.paper, 'w', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767) });
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(Item.ingotIron, 1), new ItemStack[] { new ItemStack(fenceSteel, 1) });
		FCRecipes.AddAnvilRecipe(new ItemStack(fenceSteel, 10), new Object[] { " X X", "XXXX", " X X", " X X", 'X', new ItemStack(Item.ingotIron) });
		
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(bottleHempOil,1), new Object[]{Item.glassBottle, FCBetterThanWolves.fcHempSeeds});
		FCRecipes.AddVanillaRecipe(new ItemStack(lanternPaper,1),new Object[]{"pwp","wcw","pwp",'c', new ItemStack(FCBetterThanWolves.fcItemCandle,1,32767), 'p', Item.paper, 'w', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767)});
		FCRecipes.AddAnvilRecipe(new ItemStack(lanternGold,2), new Object[]{" ss "," gg ","cggc","cggc",'s',Block.stone,'g',Item.goldNugget,'c', new ItemStack(FCBetterThanWolves.fcItemCandle,1,32767)});
		FCRecipes.AddVanillaRecipe(new ItemStack(lanternSteel,1),new Object[]{" s ","shs"," s ",'s',fenceSteel,'h',bottleHempOil});
		FCRecipes.AddStokedCrucibleRecipe(new ItemStack(Item.ingotIron,4), new ItemStack[]{new ItemStack(lanternSteel,1)});
	}
	public static class BlockLanternGold extends Block
	{
		public BlockLanternGold(int ID)
		{
			super(ID, Material.iron);
			setUnlocalizedName("ginger_lantern_gold");
			setStepSound(soundStoneFootstep);
			setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
			setCreativeTab(CreativeTabs.tabDecorations);
			setHardness(0.3F);
			setLightValue(1F);
			AddonManager.Register(this, "Chandelier");
			this.SetAxesEffectiveOn(true);
		}
		public boolean isOpaqueCube()
		{
			return false;
		}
		public boolean renderAsNormalBlock()
		{
			return false;
		}
		public int getRenderType()
		{
			return 1;
		}
		public void randomDisplayTick(World CurrentWorld, int X_, int Y_, int Z_, Random par5Random)
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
	}
	public static class BlockLantern extends Block implements FCIBlock
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
			FCBlockAestheticNonOpaque_LightningRodFix.AddHoldableBlock(this);
		}
		public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
		{
			setBlockBoundsBasedOnState(var1, var2, var3,var4);
			return SetFacingInMetadata(var9, var5);
		}
		public boolean renderAsNormalBlock()
		{
			return false;
		}
		public void setBlockBoundsBasedOnState(IBlockAccess Access, int X, int Y, int Z)
		{
			switch (GetFacing(Access, X, Y, Z))
			{
				default:
				case 0: setBlockBounds(.3125F,.5F,.3125F,.6875F,1F,.6875F); break;
				case 1: setBlockBounds(.3125F,0F,.3125F,.6875F,.5F,.6875F); break;
				case 2: setBlockBounds(.3125F,0F,.625F,.6875F,.5F,1F);   break;
				case 3: setBlockBounds(.3125F,0F,0F,.6875F,.5F,.375F);   break;
				case 4: setBlockBounds(.625F,0F,.3125F,1F,.5F,.6875F);   break;
				case 5: setBlockBounds(0F,0F,.3125F,.375F,.5F,.6875F);   break;
			}
		}
		public AxisAlignedBB getCollisionBoundingBoxFromPool(World CurrentWorld, int X, int Y, int Z)
		{
			switch (GetFacing(CurrentWorld, X, Y, Z))
			{
				default:
				case 0: return AxisAlignedBB.getAABBPool().getAABB(X+.3125D,	Y+.5D,	Z+.3125D,	X+.6875D,	Y+1D,	Z+.6875D);
				case 1: return AxisAlignedBB.getAABBPool().getAABB(X+.3125D,	Y,	Z+.3125D,	X+.6875D,	Y+.5D,	Z+.6875D);
				case 2: return AxisAlignedBB.getAABBPool().getAABB(X+.3125D,	Y,	Z+.625D,	X+.6875D,	Y+.5D,	Z+1D);
				case 3: return AxisAlignedBB.getAABBPool().getAABB(X+.3125D,	Y,	Z,		X+.6875D,	Y+.5D,	Z+.375D);
				case 4: return AxisAlignedBB.getAABBPool().getAABB(X+.625D,	Y,	Z+.3125D,	X+1D,		Y+.5D,	Z+.6875D);
				case 5: return AxisAlignedBB.getAABBPool().getAABB(X,		Y,	Z+.3125D,	X+.375D,	Y+.5D,	Z+.6875D);
			}
		}
		public void setBlockBoundsForItemRender()
		{
			setBlockBounds(.3125F, 0F, .3125F, .6875F, .5F, .6875F);
		}
		public boolean isOpaqueCube()
		{
			return false;
		}
		public int GetFacing(IBlockAccess var1, int var2, int var3, int var4)
		{
			return var1.getBlockMetadata(var2, var3, var4);
		}
		public void SetFacing(World var1, int var2, int var3, int var4, int var5)
		{
			var1.setBlockMetadataWithNotify(var2, var3, var4, var5);
		}
		public int GetFacingFromMetadata(int var1)
		{
			return var1;
		}
		public int SetFacingInMetadata(int var1, int var2)
		{
			return var2;
		}
		public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
		{
			return false;
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
			
		}
		public int RotateMetadataAroundJAxis(int var1, boolean var2)
		{
			return 0;
		}
		public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
		{
			return false;
		}
	}
	public static class FCBlockAestheticNonOpaque_LightningRodFix extends FCBlockAestheticNonOpaque
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
		
	}
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
