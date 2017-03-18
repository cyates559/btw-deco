package net.minecraft.src;
public class Addon_HayBale
{
	public static Block hayBale;
	public Addon_HayBale()
	{
		hayBale = new BlockBale(3025);
		AddonManager.MakeStorage(Item.wheat, hayBale);
	}
	public static class BlockBale extends Block
	{
		static Icon TopIcon;
		public BlockBale(int ID)
		{
			super(ID, Material.cloth);
			setUnlocalizedName("blockHay");
			setStepSound(soundGrassFootstep);
			setCreativeTab(CreativeTabs.tabBlock);
			ItemAxe.SetAllAxesToBeEffectiveVsBlock(this);
			AddonManager.Register(this);
			AddonManager.Name(this, "Hay Bale");
		}
		public Icon getIcon(int Side, int Meta)
		{
			return Side < 2 ? TopIcon : blockIcon;
		}
		public void registerIcons(IconRegister Icons)
		{
			blockIcon = Icons.registerIcon("ginger_hay_side");
			TopIcon = Icons.registerIcon("ginger_hay_top");
		}
	}
}
