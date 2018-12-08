package net.minecraft.src;
public class Addon_Clay
{
	public static Block hardenedClay, stainedClay;
	public static final String[] tags = new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan", "lightGrey", "grey", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white" };
	public static final String[] names = new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Light Grey", "Grey", "Pink", "Lime", "Yellow", "Light Blue", "Magenta", "Orange", "White" };
	public Addon_Clay()
	{
		hardenedClay = (new Block(3044, Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("ginger_clay").setCreativeTab(CreativeTabs.tabBlock);
		stainedClay = (new BlockColored(3045, "ginger_clay", "Hardened Clay", Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabBlock);
		FCBetterThanWolves.fcBlockUnfiredClay.SetCanBeCookedByKiln(true).SetItemIndexDroppedWhenCookedByKiln(hardenedClay.blockID);
		AddonManager.Register(hardenedClay, "Hardened Clay");

		for (int i = 0; i < 16; i++)
		{
			FCRecipes.AddStokedCauldronRecipe(new ItemStack(stainedClay, 1, i), new ItemStack[] { new ItemStack(hardenedClay), new ItemStack(Item.dyePowder, 1, i) });
		}
	}
	public static class BlockColored extends Block
	{
		String nameTag;
		public Icon[] icons = new Icon[16];
		public BlockColored(int id, String tag, String name, Material material)
		{
			super(id, material);
			this.nameTag = tag;
			this.setUnlocalizedName(tag);
			AddonManager.Register(this,tags, names," " + name);
		}
		@Override public int damageDropped(int metadata)
		{
			return metadata;
		}
		//CLIENT ONLY
		@Override public Icon getIcon(int side, int metadata)
		{
			return this.icons[metadata];
		}
		@Override public void registerIcons(IconRegister iconRegister)
		{
			for (int i = 0; i < 16; i++)
				this.icons[i] = iconRegister.registerIcon(nameTag+"_" + i);
		}
	}
}
