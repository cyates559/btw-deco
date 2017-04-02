package net.minecraft.src;
public class Addon_Fruit
{
	public static Item grape, orange, banana;
	public Addon_Fruit()
	{
		
		grape = new ItemFood(30004, 1, 0.0F, false).setUnlocalizedName("ginger_crop_grape");
		orange = new ItemFood(30005, 1, 0.0F, false).setUnlocalizedName("ginger_crop_orange");
		banana = new ItemFood(30006, 1, 0.0F, false).setUnlocalizedName("ginger_crop_banana");
		
		AddonManager.Name(grape, "Memberberries");
		AddonManager.Name(orange, "Orange");
		AddonManager.Name(banana, "Banana");
	}
}
