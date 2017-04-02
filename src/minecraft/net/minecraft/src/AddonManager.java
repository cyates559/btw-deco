package net.minecraft.src;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddonManager extends FCAddOn
{
	private static ArrayList<String> Names = new ArrayList<String>();
	private static ArrayList<Object> NameTargets = new ArrayList<Object>();
	public void Initialize()
	{
		CheatBlockIDs();
	}
	public void PostInitialize()
	{//}public void x(){
		
		loadAddon("HayBale");
		boolean flowers=loadAddon("Flowers"),
			glass=loadAddon("Glass"),
			clay=loadAddon("Clay");
		if(flowers)
		{
			if(glass)loadAddon("GlassColor");
			if(clay)loadAddon("ClayColor");
		}
		loadAddon("WhiteBrick");
		loadAddon("Fruit");
		loadAddon("Chairs");
		loadAddon("Lanterns");
		loadAddon("Diamondium");
		loadAddon("Walls");
		loadAddon("PurpleBlock");
	}
	private static boolean Create_HasCall=false;
	public void OnLanguageLoaded(StringTranslate Language)
	{
		int Index = 0;
		while(Index<Names.size())
		{
			if(NameTargets.get(Index) instanceof Item)
			{
				Language.GetTranslateTable().put(((Item)NameTargets.get(Index)).getUnlocalizedName() + ".name", Names.get(Index));
			}
			else if(NameTargets.get(Index) instanceof Block)
			{
				Language.GetTranslateTable().put(((Block)NameTargets.get(Index)).getUnlocalizedName() + ".name", Names.get(Index));
			}
			else if(NameTargets.get(Index) instanceof ItemStack)
			{
				Language.GetTranslateTable().put(Item.itemsList[((ItemStack)NameTargets.get(Index)).itemID].getUnlocalizedName(((ItemStack)NameTargets.get(Index)))+ ".name", Names.get(Index));
			}
			else if(NameTargets.get(Index) instanceof String)
			{
				Language.GetTranslateTable().put(NameTargets.get(Index).toString(), Names.get(Index));
			}
			else System.out.println("You can't name that kind of object!");
			Index++;
		}
	}
	public static void Name(Object Target, String Name)
	{
		Names.add(Name);
		NameTargets.add(Target);
	}
	public static void NameSubBlocks(Block SidingAndCorner, Block MouldingAndDecorative, String Name)
	{

		Name(SidingAndCorner, "Siding And Corner");
		Name(MouldingAndDecorative, "Moulding And Decorative");

		String Tag = SidingAndCorner.getUnlocalizedName();
		Name(Tag + ".siding" + ".name", Name + " Siding");
		Name(Tag + ".corner" + ".name", Name + " Corner");
		Name(Tag + ".bench" + ".name", Name + " Bench");
		Name(Tag + ".fence" + ".name", Name + " Fence");

		Tag = MouldingAndDecorative.getUnlocalizedName();
		Name(Tag + ".moulding" + ".name", Name + " Moulding");
		Name(Tag + ".column" + ".name", Name + " Column");
		Name(Tag + ".pedestal" + ".name", Name + " Pedestal");
		Name(Tag + ".table" + ".name", Name + " Table");
	}
	public static void NameSubBlocks_Wall(Block SidingAndCorner, Block MouldingAndDecorative, String Name)
	{

		Name(SidingAndCorner, "Siding And Corner");
		Name(MouldingAndDecorative, "Moulding And Decorative");

		String Tag = SidingAndCorner.getUnlocalizedName();
		Name(Tag + ".siding" + ".name", Name + " Siding");
		Name(Tag + ".corner" + ".name", Name + " Corner");
		Name(Tag + ".bench" + ".name", Name + " Bench");
		Name(Tag + ".fence" + ".name", Name + " Wall");

		Tag = MouldingAndDecorative.getUnlocalizedName();
		Name(Tag + ".moulding" + ".name", Name + " Moulding");
		Name(Tag + ".column" + ".name", Name + " Column");
		Name(Tag + ".pedestal" + ".name", Name + " Pedestal");
		Name(Tag + ".table" + ".name", Name + " Table");
	}
	public static int ReplaceBlockID(Block Victim)
	{
		Block.blocksList[Victim.blockID] = null;
		return Victim.blockID;
	}
	public static void MakeStorage(Item SubItem, Block Container)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(Container), new Object[]{"XXX","XXX","XXX",'X',SubItem});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(SubItem, 9), new ItemStack[]{new ItemStack(Container)});
	}
	public static void MakeStorage(Item SubItem, Item Container)
	{
		FCRecipes.AddVanillaRecipe(new ItemStack(Container), new Object[]{"XXX","XXX","XXX",'X',SubItem});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(SubItem, 9), new ItemStack[]{new ItemStack(Container)});
	}
	public static void MakeStorage(ItemStack SubItem, ItemStack Container)
	{
		FCRecipes.AddVanillaRecipe(Container, new Object[]{"XXX","XXX","XXX",'X',SubItem});
		FCRecipes.AddShapelessVanillaRecipe(new ItemStack(SubItem.itemID, 9,SubItem.getItemDamage()), new ItemStack[]{Container});
	}
	public static void Register(Block Target)
	{
		Item.itemsList[Target.blockID] = new ItemBlock(Target.blockID - 256);
	}
	public static void Register(Block Target, String Name)
	{
		Register(Target);
		Name(Target, Name);
	}
	public static void Register(Block Target, String[] Names, String PreTitle, String[] Titles, String PostTitle)
	{
		Item.itemsList[Target.blockID] = new ItemMultiBlock(Target, Names, PreTitle, Titles, PostTitle);
	}
	public static void Register(Block Target, String[] Names, String[] Titles)
	{
		Item.itemsList[Target.blockID] = new ItemMultiBlock(Target, Names, "", Titles, "");
	}
	public static void Register(Block Target, String[] Names, String[] Titles, String PostTitle)
	{
		Item.itemsList[Target.blockID] = new ItemMultiBlock(Target, Names, "", Titles, PostTitle);
	}
	public static void Register(Block Target, String[] Names, String PreTitle, String[] Titles)
	{
		Item.itemsList[Target.blockID] = new ItemMultiBlock(Target, Names, PreTitle, Titles, "");
	}
	private static void CheatBlockIDs()
	{
		//Better Than Wolves Potential Fluid Sources.
		boolean[] NEW_m_bBlocksPotentialFluidSources = new boolean[Block.blocksList.length];
		for(int Index = 0;Index<FCBetterThanWolves.m_bBlocksPotentialFluidSources.length;Index++)
			NEW_m_bBlocksPotentialFluidSources[Index]=FCBetterThanWolves.m_bBlocksPotentialFluidSources[Index];
		FCBetterThanWolves.m_bBlocksPotentialFluidSources = NEW_m_bBlocksPotentialFluidSources;
		for (int var1 = 256; var1 < FCBetterThanWolves.m_bBlocksPotentialFluidSources.length; ++var1)
		{
			Block var2 = Block.blocksList[var1];
			if (var2 != null && var2 instanceof FCIBlockFluidSource) FCBetterThanWolves.m_bBlocksPotentialFluidSources[var1] = true;
			else FCBetterThanWolves.m_bBlocksPotentialFluidSources[var1] = false;
		}


		//Player Block Statistics
		StatBase[] NEW_mineBlockStatArray = new StatBase[Block.blocksList.length];
		for(int Index = 0;Index<256;Index++)
		NEW_mineBlockStatArray[Index]=StatList.mineBlockStatArray[Index];
		for (int Index = 256; Index < Block.blocksList.length; ++Index)
		if (Block.blocksList[Index] != null && Block.blocksList[Index].getEnableStats())
		{
			String Fapstation = StatCollector.translateToLocalFormatted("stat.mineBlock", new Object[] {Block.blocksList[Index].getLocalizedName()});
			NEW_mineBlockStatArray[Index] = (new StatCrafting(16777216 + Index, Fapstation, Index)).registerStat();
			StatList.objectMineStats.add((StatCrafting)NEW_mineBlockStatArray[Index]);
		}
		StatList.mineBlockStatArray=NEW_mineBlockStatArray;
		//Blocks can catch fire and and spread fire
		int[] NEW_chanceToEncourageFire = new int[4096];
		int[] NEW_abilityToCatchFire  = new int[4096];
		for(int Index = 0;Index <BlockFire.chanceToEncourageFire.length;Index++)
		{
			NEW_chanceToEncourageFire[Index] = BlockFire.chanceToEncourageFire[Index];
			NEW_abilityToCatchFire[Index]  = BlockFire.abilityToCatchFire[Index];
		}
		BlockFire.chanceToEncourageFire = NEW_chanceToEncourageFire;
		BlockFire.abilityToCatchFire  = NEW_abilityToCatchFire;
		//EnderMan's CarriableBlocks
		try
		{
			Field carriableBlocksField = EntityEnderman.class.getDeclaredFields()[0];
			carriableBlocksField.setAccessible(true);
			boolean[] NEW_carriableBlocks = new boolean[4096],OLD_carriableBlocks = (boolean[]) carriableBlocksField.get(EntityEnderman.class);
			for(int Index = 0; Index<256;Index++)
				NEW_carriableBlocks[Index]= OLD_carriableBlocks[Index];
			carriableBlocksField.set(EntityEnderman.class, NEW_carriableBlocks);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		//FCTileEntityBeacon BlocksList
		Field[] BeaconFields = FCTileEntityBeacon.class.getDeclaredFields();
		Field Beacon__m_iEffectsByBlockID = BeaconFields[4];
		System.out.println(Beacon__m_iEffectsByBlockID.getName());
		Beacon__m_iEffectsByBlockID.setAccessible(true);
		ArrayList[] NEW_EffectsByBlockID = new ArrayList[4096];
        for (int var0 = 0; var0 < 4096; ++var0)
        {
            NEW_EffectsByBlockID[var0] = new ArrayList();
        }
		try
		{
			Beacon__m_iEffectsByBlockID.set(FCTileEntityBeacon.class, NEW_EffectsByBlockID);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		FCTileEntityBeacon.InitializeEffectsByBlockID();


		Item.map = (ItemMap)(new FCItemMap_IDFix(102)).setUnlocalizedName("map");
	}
	public static class FCItemMap_IDFix extends ItemMap
	{
		protected FCItemMap_IDFix(int par1) {
			super(par1);
		}

		public void updateMapData(World var1, Entity var2, MapData var3)
		{
			if (var1.provider.dimensionId == var3.dimension && var2 instanceof EntityPlayer && var3.IsEntityLocationVisibleOnMap(var2))
			{
				_updateMapData(var1, var2, var3);
			}
		}
		public void _updateMapData(World par1World, Entity par2Entity, MapData par3MapData)
		{
			if (par1World.provider.dimensionId == par3MapData.dimension && par2Entity instanceof EntityPlayer)
			{
				short var4 = 128;
				short var5 = 128;
				int var6 = 1 << par3MapData.scale;
				int var7 = par3MapData.xCenter;
				int var8 = par3MapData.zCenter;
				int var9 = MathHelper.floor_double(par2Entity.posX - (double)var7) / var6 + var4 / 2;
				int var10 = MathHelper.floor_double(par2Entity.posZ - (double)var8) / var6 + var5 / 2;
				int var11 = 128 / var6;

				if (par1World.provider.hasNoSky) var11 /= 2;

				MapInfo var12 = par3MapData.func_82568_a((EntityPlayer)par2Entity);
				++var12.field_82569_d;

				for (int var13 = var9 - var11 + 1; var13 < var9 + var11; ++var13)
				{
					if ((var13 & 15) == (var12.field_82569_d & 15))
					{
						int var14 = 255;
						int var15 = 0;
						double var16 = 0.0D;

						for (int var18 = var10 - var11 - 1; var18 < var10 + var11; ++var18)
						{
							if (var13 >= 0 && var18 >= -1 && var13 < var4 && var18 < var5)
							{
								int var19 = var13 - var9;
								int var20 = var18 - var10;
								boolean var21 = var19 * var19 + var20 * var20 > (var11 - 2) * (var11 - 2);
								int var22 = (var7 / var6 + var13 - var4 / 2) * var6;
								int var23 = (var8 / var6 + var18 - var5 / 2) * var6;
								int[] var24 = new int[4096];
								Chunk var25 = par1World.getChunkFromBlockCoords(var22, var23);

								if (!var25.isEmpty())
								{
									int var26 = var22 & 15;
									int var27 = var23 & 15;
									int var28 = 0;
									double var29 = 0.0D;
									int var31;
									int var32;
									int var33;
									int var36;

									if (par1World.provider.hasNoSky)
									{
										var31 = var22 + var23 * 231871;
										var31 = var31 * var31 * 31287121 + var31 * 11;

										if ((var31 >> 20 & 1) == 0)
										{
											var24[Block.dirt.blockID] += 10;
										}
										else
										{
											var24[Block.stone.blockID] += 10;
										}
										var29 = 100.0D;
									}
									else
									{
										for (var31 = 0; var31 < var6; ++var31)
										{
											for (var32 = 0; var32 < var6; ++var32)
											{
												var33 = var25.getHeightValue(var31 + var26, var32 + var27) + 1;
												int var34 = 0;

												if (var33 > 1)
												{
													boolean var35;

													do
													{
														var35 = true;
														var34 = var25.getBlockID(var31 + var26, var33 - 1, var32 + var27);

														if (var34 == 0)
														{
															var35 = false;
														}
														else if (var33 > 0 && var34 > 0 && Block.blocksList[var34].blockMaterial.materialMapColor == MapColor.airColor)
														{
															var35 = false;
														}

														if (!var35)
														{
															--var33;

															if (var33 <= 0) break;

															var34 = var25.getBlockID(var31 + var26, var33 - 1, var32 + var27);
														}
													}while (var33 > 0 && !var35);

													if (var33 > 0 && var34 != 0 && Block.blocksList[var34].blockMaterial.isLiquid())
													{
														var36 = var33 - 1;
														boolean var37 = false;
														int var43;

														do
														{
															var43 = var25.getBlockID(var31 + var26, var36--, var32 + var27);
															++var28;
														}while (var36 > 0 && var43 != 0 && Block.blocksList[var43].blockMaterial.isLiquid());
													}
												}

												var29 += (double)var33 / (double)(var6 * var6);
												++var24[var34];
											}
										}
									}

									var28 /= var6 * var6;
									var31 = 0;
									var32 = 0;

									for (var33 = 0; var33 < 4096; ++var33)
									{
										if (var24[var33] > var31)
										{
											var32 = var33;
											var31 = var24[var33];
										}
									}

									double var40 = (var29 - var16) * 4.0D / (double)(var6 + 4) + ((double)(var13 + var18 & 1) - 0.5D) * 0.4D;
									byte var39 = 1;

									if (var40 > 0.6D) var39 = 2;

									if (var40 < -0.6D) var39 = 0;

									var36 = 0;

									if (var32 > 0)
									{
										MapColor var42 = Block.blocksList[var32].blockMaterial.materialMapColor;

										if (var42 == MapColor.waterColor)
										{
											var40 = (double)var28 * 0.1D + (double)(var13 + var18 & 1) * 0.2D;
											var39 = 1;

											if (var40 < 0.5D) var39 = 2;

											if (var40 > 0.9D) var39 = 0;
										}

										var36 = var42.colorIndex;
									}

									var16 = var29;

									if (var18 >= 0 && var19 * var19 + var20 * var20 < var11 * var11 && (!var21 || (var13 + var18 & 1) != 0))
									{
										byte var41 = par3MapData.colors[var13 + var18 * var4];
										byte var38 = (byte)(var36 * 4 + var39);

										if (var41 != var38)
										{
											if (var14 > var18) var14 = var18;

											if (var15 < var18) var15 = var18;

											par3MapData.colors[var13 + var18 * var4] = var38;
										}
									}
								}
							}
						}

						if (var14 <= var15)
							par3MapData.setColumnDirty(var13, var14, var15);
					}
				}
			}
		}

	}
	public static class ItemMultiBlock extends ItemBlockWithMetadata
	{
		String[] blockNames;
		ItemMultiBlock(Block Owner, String[] Names, String PreTitle, String[] Titles, String PostTitle)
		{
			super(Owner.blockID - 256,Owner);
			setMaxDamage(0);
			setHasSubtypes(true);
			setUnlocalizedName(Owner.getUnlocalizedName());
			blockNames = Names;
			for (int Index = 0; Index < Titles.length; ++Index)
			Name(new ItemStack(this, 1, Index), PreTitle + Titles[Index] + PostTitle);
		}
		public String getUnlocalizedName(ItemStack Reference)
		{
			return super.getUnlocalizedName() + "." + this.blockNames[Reference.getItemDamage()];
		}
		public void getSubItems(int var1, CreativeTabs var2, List var3)
		{
			for (int var4 = 0; var4 < this.blockNames.length; ++var4)
				var3.add(new ItemStack(this, 1, var4));
		}
	}
	private static Map<String, Object> GetConfigInfo(String AddonName)
	{
		File ConfigFile = new File(Minecraft.getMinecraftDir(),AddonName+"Config.txt");
		Map<String,Object>Return=new HashMap<String,Object>();
		try
		{
			BufferedReader Reader=new BufferedReader(new FileReader(ConfigFile));
			String Line="";
			while((Line=Reader.readLine())!=null)
			{
				String[]SplitLine=Line.split("=");
				for (int Index=0;Index<SplitLine.length;++Index)
				SplitLine[Index]=SplitLine[Index].trim();
				Return.put(SplitLine[0],(SplitLine[1]=="1"||SplitLine[1]=="0")?SplitLine[1]=="1":SplitLine[1]);
			}
			Reader.close();
		}
		catch(Exception X)
		{
			X.printStackTrace();
		}
		return Return;
	}
	private static void WriteToConfigFile(String AddonName, Map<String, Object> Contents)
	{
		File ConfigFile = new File(Minecraft.getMinecraftDir(),AddonName+"Config.txt");
		PrintWriter Writer;
		try
		{
			Writer = new PrintWriter(ConfigFile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return;
		}
		for(String CurrentKey:Contents.keySet())
		{
			Object Value=Contents.get(CurrentKey);
			Writer.println(CurrentKey+"="+((Value instanceof Boolean)?((Boolean)Value==true?"1":"0"):Value.toString()));
		}
		Writer.close();
	}
	public static Map<String, Object> GetConfigInfo(String AddonName, Map<String, Object> Default)
	{
		File ConfigFile = new File(Minecraft.getMinecraftDir(),AddonName+"Config.txt");
		if(!ConfigFile.exists())
		{
			WriteToConfigFile(AddonName, Default);
			return Default;
		}
		Map<String,Object>CurrentConfigInfo=GetConfigInfo(AddonName),NewConfigInfo=Default;
		boolean NeedsRewrite = false;
		for(String CurrentKey:Default.keySet())
		{
			if(CurrentConfigInfo.containsKey(CurrentKey))
			NewConfigInfo.put(CurrentKey, CurrentConfigInfo.get(CurrentKey));
			else
			NeedsRewrite=true;
		}
		if(NeedsRewrite)
		WriteToConfigFile(AddonName, CurrentConfigInfo);
		return NewConfigInfo;
	}
	public static int ToInt(boolean Victim)
	{
		return Victim?1:0;
	}
	public boolean loadAddon(String name)
	{
		Class c;
		try
		{
			Class.forName(this.getClass().getPackage().getName()+".Addon_"+name).newInstance();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
