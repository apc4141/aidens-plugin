package aidensplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CraftCommandCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> items = CraftCommand.getCraftItems();
        List<String> completions = new ArrayList<>();

        if(args.length == 1) {
            StringUtil.copyPartialMatches(args[0], items, completions);
        }

        Collections.sort(completions);
        return completions;
    }
}
