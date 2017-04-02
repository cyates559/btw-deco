package net.minecraft.src;
public class Addon_Clay
{
	public static Block hardenedClay, stainedClay;
	public static final String[] 	tags=new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan", "lightGrey", "grey", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white" },
					names=new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Light Grey", "Grey", "Pink", "Lime", "Yellow", "Light Blue", "Magenta", "Orange", "White" };
	public Addon_Clay()
	{
		hardenedClay = (new Block(3044, Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("ginger_clay").setCreativeTab(CreativeTabs.tabBlock);
		stainedClay = (new BlockColored(3045, "ginger_clay", "Hardened Clay", Material.rock)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabBlock);
		Block.blockClay.SetCanBeCookedByKiln(true).SetItemIndexDroppedWhenCookedByKiln(hardenedClay.blockID);
		AddonManager.Register(hardenedClay, "Hardened Clay");
		

		for (int Index = 0; Index < 16; Index++)
		{

			FCRecipes.AddStokedCauldronRecipe(new ItemStack(stainedClay, 1, Index), new ItemStack[] { new ItemStack(hardenedClay), new ItemStack(Item.dyePowder, 1, Index) });
		}
	}
	public static class BlockColored extends Block
	{
		String NameTag;
		public Icon[] Icons = new Icon[16];
		public BlockColored(int ID, String Tag, String Name, Material MyMaterial)
		{
			super(ID, MyMaterial);
			NameTag = Tag;
			setUnlocalizedName(Tag);
			AddonManager.Register(this,tags,names," " + Name);
		}
		public int damageDropped(int Meta)
		{
			return Meta;
		}
	}
}
