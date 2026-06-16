package de.juliusxyz.schrottcraft;

import java.util.Objects;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SchrottCraftPlugin extends JavaPlugin implements Listener, CommandExecutor {
    private final SchrottBank bank = new SchrottBank();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(getCommand("schrott")).setExecutor(this);
        getLogger().info("SchrottCraft ist an. Wahrscheinlich.");
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        int points = pointsFor(event.getBlock().getType());
        bank.add(player.getUniqueId(), points);

        if (bank.pointsFor(player.getUniqueId()) % 9 == 0) {
            player.sendMessage(ChatColor.GRAY + "Schrottpunkte: " + bank.pointsFor(player.getUniqueId()));
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!bank.trySpend(player.getUniqueId(), 1)) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Du hast zu wenig Schrottpunkte. Bau halt erst was ab.");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Nur Spieler koennen diesen Schrott benutzen.");
            return true;
        }

        if (args.length == 0 || args[0].equalsIgnoreCase("score")) {
            player.sendMessage(ChatColor.YELLOW + "Du hast " + bank.pointsFor(player.getUniqueId()) + " Schrottpunkte.");
            return true;
        }

        if (args[0].equalsIgnoreCase("help")) {
            player.sendMessage(ChatColor.GRAY + "/schrott score - zeigt deinen Punktestand");
            player.sendMessage(ChatColor.GRAY + "Bloecke abbauen gibt Punkte, platzieren kostet einen Punkt.");
            return true;
        }

        player.sendMessage(ChatColor.DARK_RED + "Keine Ahnung, was das heissen soll.");
        return true;
    }

    private int pointsFor(Material material) {
        if (material == Material.DIAMOND_ORE || material == Material.DEEPSLATE_DIAMOND_ORE) {
            return 8;
        }
        if (material.name().endsWith("_ORE")) {
            return 4;
        }
        if (material.isSolid()) {
            return 1;
        }
        return 0;
    }
}
