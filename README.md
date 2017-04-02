# Better Than Wolves Deco Addon

## Installing the Server
1. Install Better Than Wolves
2. Download the ZIP file of this repository (or clone it)
3. Extract the ZIP file and go to the minecraft_server directory inside of it
4. Copy the contents of this directory into your minecraft_server.jar file
5. Copy the addonconfig.txt file to the same directory that your minecraft_server.jar folder

## Installing the Client
1. Install Better Than Wolves.
2. Download the ZIP file of this repository (or clone it)
3. Extract the ZIP file and go to the minecraft directory inside of it
4. Copy the contents of this directory into your minecraft jar file
5. Copy the addonconfig.txt file to your minecraft directory (unless you've changed it, this should be '~/.minecraft' or 'C:\Users\YourUserName\Application Data\.minecraft')

## Using the addonconfig.txt File
* The addonconfig.txt file tells the Mod which Add-ons to load and must be placed in the Working directory of your game or server
* The addons are listed in the file separated by spaces
* The names of the addons correspond to the class files that you've placed inside your jars.  For example, "HayBale" corresponds to "Addon_HayBale.class"
* Some addons require other addons to be loaded.  If you attempt to load an addon before it's prequisites have been met, the addon will fail to load and print an error in the log
* __Note__
  * Disabling addons by deleting their files is now depricated.
  * Files with '$' in their names are subclasses, you cannot disable these, they are part of another addon.
* __Addons that have prereqs:__
  * The GlassColor addon requires the Glass and the Flowers addons.
  * The ClayColor addon requires the Clay and the Flowers addons.
  * The ClaySubBlocks addon requires the Clay addon.
  

