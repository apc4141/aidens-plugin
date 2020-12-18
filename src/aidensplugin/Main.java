package aidensplugin;

import aidensplugin.commands.*;
import aidensplugin.items.base.CustomMetaManager;
import aidensplugin.items.base.MaterialHelper;
import aidensplugin.items.base.MinersGemBlockWhiteList;
import aidensplugin.items.listener.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable()
    {
        //init everything important
        CustomMetaManager.init(this);
        MinersGemBlockWhiteList.init();
        MaterialHelper.init();
        CraftCommand.init();

        //Register event handlers
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new EnderBowListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerRightClickedListener(), this);
        getServer().getPluginManager().registerEvents(new EnchantedGemListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new ItemDurabilityListener(), this);

        //Heal player command
        this.getCommand("heal").setExecutor(new HealCommand(this));
        //Heal player command
        this.getCommand("feed").setExecutor(new FeedCommand(this));

        //Thunder stick command
        this.getCommand("give-thunderstick").setExecutor(new GiveThunderStckCommand(this));

        //Mechanical boots command
        this.getCommand("give-mechanicalboots").setExecutor(new GiveMechanicalBootsCommand(this));

        //Ender bow command
        this.getCommand("give-enderbow").setExecutor(new GiveEnderBowCommand(this));

        //Flynn stick command
        this.getCommand("give-flynnstick").setExecutor(new GiveFlynnStickCommand(this));

        //Enchanted gem command
        this.getCommand("give-enchantedgem").setExecutor(new GiveEnchantedGemCommand(this));

        //Miners gem command
        this.getCommand("give-minersgem").setExecutor(new GiveMinersGemCommand(this));

        //Infinite hot snowball command
        this.getCommand("give-infinitehotsnowball").setExecutor(new GiveIHotSnowballCommand(this));

        //Caving flower command
        this.getCommand("give-cavingflower").setExecutor(new GiveCavingFlowerCommand(this));

        //Escape flower command
        this.getCommand("give-fishhelm").setExecutor(new GiveFishHelmCommand(this));

        //General command
        this.getCommand("craft").setExecutor(new CraftCommand());
        this.getCommand("craft").setTabCompleter(new CraftCommandCompleter());
        this.getCommand("extract").setExecutor(new ExtractCommand());
    }
}