package net.minecraft.src;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.server.MinecraftServer;

public class AddonManager extends FCAddOn
{
	public static final boolean DEBUG_ADDON_LOAD = false;

	public static final int
		id_paperWall=3000,
		id_fenceSteel=3001,
		id_lanternPaper=3027,
		id_lanternGold=3028,
		id_lanternSteel=3029,

		id_hardenedClay=3044,
		id_stainedClay=3045,

		id_blockCopper=3005,

		id_flower=3002,
		id_tulip=3006,

		id_blockDiamondium=3007,

		id_glassStained=3003,
		id_glassPaneStained=3004,

		id_hayBale=3025,

		id_whiteStoneBrick=3008,
		id_whiteBrickSidingAndCorner=3009,
		id_whiteBrickMouldingAndDecorative=3010,
		id_whiteBrickStairs=3011,

		id_oakWoodChair=3036,
		id_spruceWoodChair=3037,
		id_birchWoodChair=3038,
		id_jungleWoodChair=3039,

		id_clay_sub_start=3049,
		id_flag_start=4000;

	private static ArrayList<String> Names = new ArrayList<String>();
	private static ArrayList<Object> NameTargets = new ArrayList<Object>();
	private static ArrayList<String> loadedAddons = new ArrayList<String>();

	public void Initialize()
	{
		System.out.println("[INFO] AddonManager: Initialize");
	}
	public void PostInitialize()
	{
		System.out.println("[INFO] AddonManager: PostInitialize");
		try
		{
			File file = new File(net.minecraft.client.Minecraft.getMinecraftDir(), "addonconfig.txt");
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			fis.close();
			String str = new String(data, "UTF-8");
			String[] addons = str.split(" ");
			if(!file.exists())
			{
				System.out.println("[WARN] Could not find addonconfig.txt, loading of deco addons was cancelled.");
				return;
			}
			for(String addon:addons) loadAddon(addon.trim());
		}catch(Exception e){}
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
		FCRecipes.AddRecipe(new ItemStack(Container), new Object[]{"XXX","XXX","XXX",'X',SubItem});
		FCRecipes.AddShapelessRecipe(new ItemStack(SubItem, 9), new ItemStack[]{new ItemStack(Container)});
	}
	public static void MakeStorage(Item SubItem, Item Container)
	{
		FCRecipes.AddRecipe(new ItemStack(Container), new Object[]{"XXX","XXX","XXX",'X',SubItem});
		FCRecipes.AddShapelessRecipe(new ItemStack(SubItem, 9), new ItemStack[]{new ItemStack(Container)});
	}
	public static void MakeStorage(ItemStack SubItem, ItemStack Container)
	{
		FCRecipes.AddRecipe(Container, new Object[]{"XXX","XXX","XXX",'X',SubItem});
		FCRecipes.AddShapelessRecipe(new ItemStack(SubItem.itemID, 9,SubItem.getItemDamage()), new ItemStack[]{Container});
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
		File ConfigFile = new File(new File("."),AddonName+"Config.txt");
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
		File ConfigFile = new File(new File("."),AddonName+"Config.txt");
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
		File ConfigFile = new File(new File("."),AddonName+"Config.txt");
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
	public static boolean require(String name)
	{
		//The original way I wrote this method was stupid and did not make sense.
		return isAddonLoaded(name);
	}
	public static boolean isAddonLoaded(String name)
	{
		return loadedAddons.contains(name);
	}
	public static boolean loadAddon(String name)
	{
		try
		{
			Class.forName("Addon_"+name).newInstance();
			System.out.println("[INFO] Loaded addon: " + name);
			loadedAddons.add(name);
			return true;
		}
		catch (ClassNotFoundException ex1)
		{
			try
			{
				Class.forName(AddonManager.class.getPackage().getName()+".Addon_"+name).newInstance();
				System.out.println("[INFO] Loaded addon: " + name);
				loadedAddons.add(name);
				return true;
			}
			catch (ClassNotFoundException ex2)
			{
				System.out.println("[INFO] Addon not found: " + name);
			}
			catch (Exception ex3)
			{
				System.out.println("[WARN] Problem loading addon: " + name);
				if(DEBUG_ADDON_LOAD) ex3.printStackTrace();
			}
			return false;
		}
		catch (Exception ex4)
		{
			System.out.println("[WARN] Problem loading addon: " + name);
			if(DEBUG_ADDON_LOAD) ex4.printStackTrace();
			return false;
		}
	}
	public static class FCBlockSidingAndCornerAndDecorative_Wall extends FCBlockSidingAndCornerAndDecorative
	{
		static ArrayList<FCBlockSidingAndCornerAndDecorative_Wall> wallBlocks = new ArrayList<FCBlockSidingAndCornerAndDecorative_Wall>();
		public FCBlockSidingAndCornerAndDecorative_Wall(int var1, Material var2, String var3, float var4, float var5, StepSound var6, String var7, String OriginalName)
		{
			super(var1, var2, var3, var4, var5, var6, var7);
			setCreativeTab(CreativeTabs.tabDecorations);
			AddonManager.Name(getUnlocalizedName() + ".fence" + ".name", OriginalName + " Wall");
			wallBlocks.add(this);
		}
		public boolean DoesFenceConnectTo(IBlockAccess var1, int var2, int var3, int var4)
		{
			int var5 = var1.getBlockId(var2, var3, var4);
			if(Block.blocksList[var5] instanceof FCBlockSidingAndCornerAndDecorative_Wall && 14 == var1.getBlockMetadata(var2, var3, var4))
			{
				return true;
			}

			if ((var5 != this.blockID || var1.getBlockMetadata(var2, var3, var4) != 14) && var5 != Block.fenceGate.blockID)
			{
				Block var6 = Block.blocksList[var5];
				return var6 != null && var6.blockMaterial.isOpaque() && var6.renderAsNormalBlock() && var6.blockMaterial != Material.pumpkin;
			}
			else
			{
				return true;
			}
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


		public void SetBlockBoundsForFence(IBlockAccess var1, int var2, int var3, int var4)
		{
			float var5 = 0.375F;
			float var6 = 0.625F;
			float var7 = 0.375F;
			float var8 = 0.625F;

			if (this.DoesFenceConnectTo(var1, var2, var3, var4 - 1))
			{
				var7 = 0.0F;
			}

			if (this.DoesFenceConnectTo(var1, var2, var3, var4 + 1))
			{
				var8 = 1.0F;
			}

			if (this.DoesFenceConnectTo(var1, var2 - 1, var3, var4))
			{
				var5 = 0.0F;
			}

			if (this.DoesFenceConnectTo(var1, var2 + 1, var3, var4))
			{
				var6 = 1.0F;
			}

			this.setBlockBounds(var5, 0.0F, var7, var6, 1.0F, var8);
		}

		public void AddCollisionBoxesToListForFence(World par1IBlockAccess, int par2, int par3, int par4, AxisAlignedBB aabb, List l, Entity e)
		{
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

			switch(var6)
			{
			case 12:
				return RenderBench(var1, var5, var2, var3, var4, this);
			case 14:
				return RenderFence(var1, var5, var2, var3, var4, this);
			default:
				return super.RenderBlock(var1, var2, var3, var4);
			}

			//return var6 == 12 ? RenderBench(var1, var5, var2, var3, var4, this) : (var6 == 14 ? RenderFence(var1, var5, var2, var3, var4, this) : super.RenderBlock(var1, var2, var3, var4));
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
			boolean top = !var11 && me.DoesFenceConnectTo(blockAccess, X, Y + 1, Z);
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
			else if(((var5 && var6)||(var7 && var8)) && top)
			{
				if(var5 && var6)
				{
					Render.setRenderBounds(0.0D, 0.0D, 0.3125D, 1.0D, 1.0D, 0.6875D);
					Render.renderStandardBlock(me, X, Y, Z);
				}

				if (var7 && var8)
				{
					Render.setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, 1.0D, 1.0D);
					Render.renderStandardBlock(me, X, Y, Z);
				}
			}
			else
			{
				Render.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
				Render.renderStandardBlock(me, X, Y, Z);
				double height = (top?1.0D:0.8125D);

				if (var5)
				{
					Render.setRenderBounds(0.0D, 0.0D, 0.3125D, 0.25D, height, 0.6875D);
					Render.renderStandardBlock(me, X, Y, Z);
				}

				if (var6)
				{
					Render.setRenderBounds(0.75D, 0.0D, 0.3125D, 1.0D, height, 0.6875D);
					Render.renderStandardBlock(me, X, Y, Z);
				}

				if (var7)
				{
					Render.setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, height, 0.25D);
					Render.renderStandardBlock(me, X, Y, Z);
				}

				if (var8)
				{
					Render.setRenderBounds(0.3125D, 0.0D, 0.75D, 0.6875D, height, 1.0D);
					Render.renderStandardBlock(me, X, Y, Z);
				}
			}
			me.setBlockBoundsBasedOnState(Render.blockAccess, X, Y, Z);
			return true;
		}
	}
	
	public static void serverCustomPacketReceived(MinecraftServer ms, EntityPlayerMP epmp,
			Packet250CustomPayload packet) {
		try {
			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));

			if (packet.channel.equals("DECO|OLDGLASS")) {
				int size = dis.readInt();
				int damage = dis.readInt();
								
				ItemStack stack = new ItemStack(30008+256,size,damage);
				epmp.inventory.setInventorySlotContents(epmp.inventory.currentItem, stack);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
