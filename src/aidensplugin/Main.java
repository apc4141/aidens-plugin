package aidensplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable()
    {
        //init everything important
        CustomMetaManager.init();

        //Register event handlers
        getServer().getPluginManager().registerEvents(new TripleJumper(), this);
        getServer().getPluginManager().registerEvents(new EnderBowEvents(this), this);
        getServer().getPluginManager().registerEvents(new PlayerRightClickedEvent(), this);
        getServer().getPluginManager().registerEvents(new EnchantedGemEvent(), this);

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

        //Flynn stick command
        this.getCommand("give-enchantedgem").setExecutor(new GiveEnchantedGemCommand(this));
    }

    @Override
    public void onLoad()
    {
        //NBTInjector.inject();
    }
}