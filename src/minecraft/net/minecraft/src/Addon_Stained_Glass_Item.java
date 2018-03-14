package net.minecraft.src;

import java.util.List;

public class Addon_Stained_Glass_Item extends Item {
	public static final String[] stainedGlassNames = new String[] {"Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Light Grey", "Grey", "Pink", "Lime", "Yellow", "Light Blue", "Magenta", "Orange", "White"};
	public static final String[] iconNames = new String[] {"ginger_stained_glass_item_0", "ginger_stained_glass_item_1", "ginger_stained_glass_item_2", "ginger_stained_glass_item_3", "ginger_stained_glass_item_4", "ginger_stained_glass_item_5", "ginger_stained_glass_item_6", "ginger_stained_glass_item_7", "ginger_stained_glass_item_8", "ginger_stained_glass_item_9", "ginger_stained_glass_item_10", "ginger_stained_glass_item_11", "ginger_stained_glass_item_12", "ginger_stained_glass_item_13", "ginger_stained_glass_item_14", "ginger_stained_glass_item_15"};
	private Icon[] icons;
	
	public Addon_Stained_Glass_Item() {
		super(30008);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setUnlocalizedName("ginger_stained_glass_item");
	}
	
	public Icon getIconFromDamage(int damage) {
		int var2 = MathHelper.clamp_int(damage, 0, 15);
		return this.icons[var2];
	}
	
	public void getSubItems(int ID, CreativeTabs par2CreativeTabs, List list) {
		for (int i=0; i<=15; ++i) {
			list.add(new ItemStack(ID, 1, i));
		}
	}
	
	public String getItemDisplayName(ItemStack par1ItemStack) {
		int damage = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 5);
		return stainedGlassNames[damage]+" Stained Glass";
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		int damage = par1ItemStack.getItemDamage();

		if (par7 == 0)
    {
        --par5;
    }

    if (par7 == 1)
    {
        ++par5;
    }

    if (par7 == 2)
    {
        --par6;
    }

    if (par7 == 3)
    {
        ++par6;
    }

    if (par7 == 4)
    {
        --par4;
    }

    if (par7 == 5)
    {
        ++par4;
    }

    if (!par3World.isAirBlock(par4, par5, par6))
    {
        return false;
    }
    
    if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
    {
        return false;
    }
    else
    {
    	--par1ItemStack.stackSize;
    	par3World.setBlock(par4, par5, par6, 3003, damage, 2);

    	return true;
    }
	}
	
	@Override
	public void registerIcons(IconRegister ir) {
		this.icons = new Icon[iconNames.length];

		for (int i=0; i<iconNames.length; ++i) {
			this.icons[i] = ir.registerIcon(iconNames[i]);
		}
	}
}