package com.enjin.gamesplazabrasil;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;


import java.util.ArrayList;

public class Tags extends JavaPlugin implements Listener {



    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        registerConfig();


    }
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    Scoreboard board = manager.getNewScoreboard();

    Team tagdono = board.registerNewTeam("tagdono");
    Team tagsubdono = board.registerNewTeam("tagsubdono");
    Team tagadmin = board.registerNewTeam("tagadmin");
    Team taglider = board.registerNewTeam("taglider");
    Team tagmod = board.registerNewTeam("tagmod");
    Team tagajudante = board.registerNewTeam("tagajudante");
    Team tagvipvit = board.registerNewTeam("tagvipvit");
    Team tagvip = board.registerNewTeam("tagvip");
    Team tagyt = board.registerNewTeam("tagyt");
    Team tagpadrao = board.registerNewTeam("tagpadrao");
    Team tagtestador = board.registerNewTeam("tagtestador");
    Team tagstaff = board.registerNewTeam("tagstaff");

    @EventHandler (priority = EventPriority.LOWEST)
    void onPlayerJoin(PlayerJoinEvent e) {

        Player p = (Player) e.getPlayer();

        p.setScoreboard(board);

        this.getConfig().addDefault(e.getPlayer().getName(), "padrao");
        saveConfig();

        String tag = this.getConfig().getString(p.getName());

    }


    @EventHandler (priority = EventPriority.LOWEST)
    void onPlyerChat(AsyncPlayerChatEvent e) {

        e.setCancelled(true);
     //   e.setMessage(null);

    Player p = e.getPlayer();


    String mutado = Bukkit.getServer().getPluginManager().getPlugin("GamesPlazaPunishmentGUI").getConfig().getString(p.getName());
    Bukkit.getServer().getPluginManager().getPlugin("GamesPlazaPunishmentGUI").saveConfig();

    if(mutado == "mute"){
        e.setCancelled(true);
    }else if (e.getMessage().equalsIgnoreCase("entendido")) {
        e.setCancelled(true);
    }else if(Bukkit.getServer().getPluginManager().getPlugin("GamesPlazaPunishmentGUI").getConfig().getString(p.getName() + "Av") == "pend") {

    e.setCancelled(true);
    }else{
        p.setScoreboard(board);


        String tag = this.getConfig().getString(p.getName());
        saveConfig();

        if(tag == "dono"){
            p.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.MAGIC + "a" + ChatColor.DARK_RED + "Dono" + ChatColor.MAGIC + "a " + ChatColor.DARK_RED + " " + p.getName());

        }
        if (tag == "sd") {

            p.setDisplayName(ChatColor.AQUA + "" + ChatColor.MAGIC + "a" + ChatColor.AQUA + "Subdono" + ChatColor.MAGIC + "a" + ChatColor.AQUA + " " + p.getName());
        }
        if (tag == "adm") {

            p.setDisplayName(ChatColor.RED + "" + ChatColor.MAGIC + "a" + ChatColor.RED + "Admin" + ChatColor.MAGIC + "a" + ChatColor.RED + " " + p.getName());
        }
        if (tag == "lider") {

            p.setDisplayName(ChatColor.RED + "" + ChatColor.MAGIC + "a" + ChatColor.RED + "Lider" + ChatColor.MAGIC + "a" + ChatColor.RED + " " + p.getName());
        }
        if (tag == "mod") {

            p.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.MAGIC + "a" + ChatColor.DARK_GREEN + "Mod" + ChatColor.MAGIC + "a" + ChatColor.DARK_GREEN + " " + p.getName());
        }
        if (tag == "aj") {

            p.setDisplayName(ChatColor.GREEN + "" + ChatColor.MAGIC + "a" + ChatColor.GREEN + "Ajudante" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " " + p.getName());
        }
        if (tag == "vipvit") {

            p.setDisplayName(ChatColor.BLUE + "" + ChatColor.MAGIC + "a" + ChatColor.BLUE + "Vip" + ChatColor.MAGIC + "a" + ChatColor.BLUE + " " + p.getName());
        }
        if (tag == "vip") {

            p.setDisplayName(ChatColor.YELLOW + "" + ChatColor.MAGIC + "a" + ChatColor.YELLOW + "Vip" + ChatColor.MAGIC + "a" + ChatColor.YELLOW + " " + p.getName());
        }
        if (tag == "yt") {

            p.setDisplayName(ChatColor.BLACK + "" + ChatColor.MAGIC + "a" + ChatColor.BLACK + "You" + ChatColor.GOLD + "Tube" + ChatColor.MAGIC + "a" + ChatColor.RED + " " + p.getName());
        }
        if (tag == "staff") {

            p.setDisplayName(ChatColor.GOLD + "" + ChatColor.MAGIC + "a" + ChatColor.GOLD + "Staff" + ChatColor.MAGIC + "a" + ChatColor.GOLD + " " + p.getName());
        }
        if (tag == "test") {

            p.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.MAGIC + "a" + ChatColor.DARK_AQUA + "Testador" + ChatColor.MAGIC + "a" + ChatColor.DARK_AQUA + " " + p.getName());
        }
        if (tag == "padrao") {

            p.setDisplayName(ChatColor.GRAY + p.getName());
        }

        TextComponent message = new TextComponent(p.getDisplayName() + ChatColor.WHITE + ": " + e.getMessage());
        e.setCancelled(true);
        e.setMessage("");




        for (Player target : Bukkit.getOnlinePlayers()) {


            if (target.hasPermission("punish.chat.punish")) {

                TextComponent bpunir = new TextComponent(ChatColor.GOLD + " - [ " + ChatColor.RED + "Punir" + ChatColor.GOLD + " ]");
                ClickEvent punish = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/punir " + p.getName());
                HoverEvent h = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD + "Clique aqui para punir o jogador").create());
                bpunir.setHoverEvent(h);
                bpunir.setClickEvent(punish);

                target.spigot().sendMessage(message, bpunir);
                e.setCancelled(true);
                e.setMessage("");
            } else {
                target.spigot().sendMessage(message);
                e.setCancelled(true);
                e.setMessage("");
            }


        }
    }
    }

    public void registerConfig(){

      this.getConfig().options().copyDefaults(true);
      this.saveConfig();

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tag")) {

            Player p = (Player) sender;

            if (args.length == 0) {

                p.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "-----------------[Tags]-----------------");
                p.sendMessage(ChatColor.WHITE + "TAG         DATA DE ATIVACAO           VENCIMENTO");
                if (p.hasPermission("tag.tag.vip")) {
                    p.sendMessage(ChatColor.YELLOW + "VIP                 xx/xx/xxxx               xx/xx/xxxx");
                } else {
                    return true;
                }
                if (p.hasPermission("tag.tag.vipvit")) {
                    p.sendMessage(ChatColor.BLUE + "VIPVIT            xx/xx/xxxx               xx/xx/xxxx");
                } else {
                    return true;
                }
                if (p.hasPermission("tag.tag.testador")) {
                    p.sendMessage(ChatColor.DARK_AQUA + "TESTADOR             xx/xx/xxxx               xx/xx/xxxx");
                } else {
                    return true;
                }
                if (p.hasPermission("tag.tag.tagstaff")) {
                    p.sendMessage(ChatColor.GOLD + "STAFF              xx/xx/xxxx                xx/xx/xxxx");
                } else {
                    return true;
                }
                if (p.hasPermission("tag.tag.ajudante")) {
                    p.sendMessage(ChatColor.GREEN + "AJUDANTE       xx/xx/xxxx              xx/xx/xxxx");
                } else {
                    return true;
                }
                if (p.hasPermission("tag.tag.mod")) {
                    p.sendMessage(ChatColor.DARK_GREEN + "MOD                 xx/xx/xxxx              xx/xx/xxxx");
                } else {
                    return true;
                }
                if (p.hasPermission("tag.tag.lider")) {
                    p.sendMessage(ChatColor.RED + "LIDER              xx/xx/xxxx              xx/xx/xxxx");
                } else {
                    return true;
                }
                if (p.hasPermission("tag.tag.admin")) {
                    p.sendMessage(ChatColor.RED + "ADMIN              xx/xx/xxxx              xx/xx/xxxx");
                } else {
                    return true;
                }
                if (p.hasPermission("tag.tag.subdono")) {
                    p.sendMessage(ChatColor.AQUA + "SUBDONO          xx/xx/xxxx             xx/xx/xxxx");
                } else {
                    return true;
                }
                if (p.hasPermission("tag.tag.youtube")) {
                    p.sendMessage(ChatColor.BLACK + "YOU" + ChatColor.GOLD + "TUBE" + "         xx/xx/xxxx             xx/xx/xxxx");
                } else {
                    return true;
                }
                if (p.hasPermission("tag.tag.dono")) {
                    p.sendMessage(ChatColor.RED + "DONO                xx/xx/xxxx             xx/xx/xxxx");
                } else {
                    return true;
                }

                return true;
            }


            if (args.length > 1) {
                p.sendMessage(ChatColor.BLUE + "Tag>" + ChatColor.RED + "Muitos argumentos! Use /tag <tag> ");
                return true;
            }


            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("dono")) {
                    if (p.hasPermission("tag.tag.dono")) {
                        tagdono.addPlayer(p);
                        this.getConfig().set(p.getName(), "dono");
                        saveConfig();
                        p.setScoreboard(board);
                        p.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.MAGIC + "a" + ChatColor.DARK_RED + "Dono" + ChatColor.MAGIC + "a " + ChatColor.DARK_RED + " " + p.getName());
                        tagdono.setPrefix(ChatColor.DARK_RED + "" + ChatColor.MAGIC + "a" + ChatColor.DARK_RED + ChatColor.BOLD + "ADM ");
                        tagdono.setSuffix(ChatColor.DARK_RED + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + " Dono" + ChatColor.DARK_RED + ChatColor.MAGIC + "a");


                        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.DARK_RED + ChatColor.MAGIC + "a" + ChatColor.DARK_RED + "DONO" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");
                    }
                }

                if (args[0].equalsIgnoreCase("subdono")) {
                    if (p.hasPermission("tag.tag.subdono")) {
                        this.getConfig().set(p.getName(), "sd");
                        saveConfig();
                        p.setScoreboard(board);
                        tagsubdono.addPlayer(p);

                        tagsubdono.setPrefix(ChatColor.AQUA + "" + ChatColor.BOLD + "ADM ");
                        p.setDisplayName(ChatColor.AQUA + "" + ChatColor.MAGIC + "a" + ChatColor.AQUA + "Subdono" + ChatColor.MAGIC + "a" + ChatColor.AQUA + " " + p.getName());
                        tagsubdono.setSuffix(" Subdono");
                        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.AQUA + ChatColor.MAGIC + "a" + ChatColor.AQUA + "SUBDONO" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");
                    }
                }
                if (args[0].equalsIgnoreCase("sd")) {
                    if (p.hasPermission("tag.tag.subdono")) {
                        this.getConfig().set(p.getName(), "sd");
                        saveConfig();
                        p.setDisplayName(ChatColor.AQUA + "" + ChatColor.MAGIC + "a" + ChatColor.AQUA + "Subdono" + ChatColor.MAGIC + "a" + ChatColor.AQUA + " " + p.getName());
                        p.setScoreboard(board);
                        tagsubdono.addPlayer(p);

                        tagsubdono.setPrefix(ChatColor.AQUA + "" + ChatColor.BOLD + "ADM ");

                        tagsubdono.setSuffix(" Subdono");
                        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.AQUA + ChatColor.MAGIC + "a" + ChatColor.AQUA + "SUBDONO" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");
                    }
                }


                if (args[0].equalsIgnoreCase("admin")) {
                    if (p.hasPermission("tag.tag.admin")) {
                        this.getConfig().set(p.getName(), "adm");
                        saveConfig();
                        p.setScoreboard(board);
                        tagadmin.addPlayer(p);
                        p.setDisplayName(ChatColor.RED + "" + ChatColor.MAGIC + "a" + ChatColor.RED + "Admin" + ChatColor.MAGIC + "a" + ChatColor.RED + " " + p.getName());
                        tagadmin.setPrefix(ChatColor.RED + "" + ChatColor.BOLD + "ADM ");
                        tagadmin.setSuffix(ChatColor.RED + "" + ChatColor.ITALIC + " Admin");
                        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.RED + ChatColor.MAGIC + "a" + ChatColor.RED + "ADMIN" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");
                    }
                }
                if (args[0].equalsIgnoreCase("lider")) {
                    if (p.hasPermission("tag.tag.admin")) {
                        this.getConfig().set(p.getName(), "lider");
                        saveConfig();
                        p.setScoreboard(board);
                        taglider.addPlayer(p);
                        p.setDisplayName(ChatColor.RED + "" + ChatColor.MAGIC + "a" + ChatColor.RED + "Lider" + ChatColor.MAGIC + "a" + ChatColor.RED + " " + p.getName());
                        taglider.setPrefix(ChatColor.RED + "" + ChatColor.BOLD + "ADM ");
                        taglider.setSuffix(ChatColor.RED + "" + ChatColor.ITALIC + " Lider");
                        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.RED + ChatColor.MAGIC + "a" + ChatColor.RED + "LIDER" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");
                    }
                }
                if (args[0].equalsIgnoreCase("mod")) {
                    if (p.hasPermission("tag.tag.mod")) {
                        this.getConfig().set(p.getName(), "mod");
                        saveConfig();
                        tagmod.addPlayer(p);
                        p.setScoreboard(board);
                        p.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.MAGIC + "a" + ChatColor.DARK_GREEN + "Mod" + ChatColor.MAGIC + "a" + ChatColor.DARK_GREEN + " " + p.getName());
                        tagmod.setPrefix(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "MOD ");
                        tagmod.setSuffix(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + " Moderador");
                        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.DARK_GREEN + ChatColor.MAGIC + "a" + ChatColor.DARK_GREEN + "MODERADOR" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");
                    }
                }
                if (args[0].equalsIgnoreCase("ajudante")) {
                    if (p.hasPermission("tag.tag.ajudante")) {
                        this.getConfig().set(p.getName(), "aj");
                        saveConfig();
                        p.setScoreboard(board);
                        p.setDisplayName(ChatColor.GREEN + "" + ChatColor.MAGIC + "a" + ChatColor.GREEN + "Ajudante" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " " + p.getName());
                        tagajudante.addPlayer(p);
                        tagajudante.setPrefix(ChatColor.GREEN + "" + ChatColor.BOLD + "MOD ");
                        tagajudante.setSuffix(ChatColor.GREEN + "" + ChatColor.BOLD + " Ajudante");
                        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.GREEN + ChatColor.MAGIC + "a" + ChatColor.GREEN + "AJUDANTE" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");
                    }
                }
                if (args[0].equalsIgnoreCase("aj")) {
                    if (p.hasPermission("tag.tag.ajudante")) {
                        this.getConfig().set(p.getName(), "aj");
                        saveConfig();
                        p.setScoreboard(board);
                        p.setDisplayName(ChatColor.GREEN + "" + ChatColor.MAGIC + "a" + ChatColor.GREEN + "Ajudante" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " " + p.getName());
                        tagajudante.addPlayer(p);
                        tagajudante.setPrefix(ChatColor.GREEN + "" + ChatColor.BOLD + "MOD ");
                        tagajudante.setSuffix(ChatColor.GREEN + "" + ChatColor.BOLD + " Ajudante");
                        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.GREEN + ChatColor.MAGIC + "a" + ChatColor.GREEN + "AJUDANTE" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");
                    }
                }
                if (args[0].equalsIgnoreCase("vipvit")) {
                    if (p.hasPermission("tag.tag.vipvit")) {
                        this.getConfig().set(p.getName(), "vipvit");
                        saveConfig();
                        p.setScoreboard(board);
                        p.setDisplayName(ChatColor.BLUE + "" + ChatColor.MAGIC + "a" + ChatColor.BLUE + "Vip" + ChatColor.MAGIC + "a" + ChatColor.BLUE + " " + p.getName());
                        tagvipvit.addPlayer(p);
                        tagvipvit.setPrefix(ChatColor.BLUE + "" + ChatColor.BOLD + "VIP ");
                        tagvipvit.setSuffix(ChatColor.BLUE + "" + ChatColor.ITALIC + " Eterno");
                        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.BLUE + ChatColor.MAGIC + "a" + ChatColor.BLUE + "VipVit" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");
                    }
                }
                if (args[0].equalsIgnoreCase("vip")) {
                    if (p.hasPermission("tag.tag.vip")) {
                        this.getConfig().set(p.getName(), "vip");
                        saveConfig();
                        p.setScoreboard(board);
                        p.setDisplayName(ChatColor.YELLOW + "" + ChatColor.MAGIC + "a" + ChatColor.YELLOW + "Vip" + ChatColor.MAGIC + "a" + ChatColor.YELLOW + " " + p.getName());
                        tagvip.addPlayer(p);
                        tagvip.setPrefix(ChatColor.YELLOW + "" + ChatColor.BOLD + "VIP ");
                        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.YELLOW + ChatColor.MAGIC + "a" + ChatColor.YELLOW + "VIP" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");
                    }
                }
                if (args[0].equalsIgnoreCase("youtube")) {
                    if (p.hasPermission("tag.tag.youtube")) {
                        p.setScoreboard(board);
                        this.getConfig().set(p.getName(), "yt");
                        saveConfig();
                        p.setDisplayName(ChatColor.BLACK + "" + ChatColor.MAGIC + "a" + ChatColor.BLACK + "You" + ChatColor.GOLD + "Tube" + ChatColor.MAGIC + "a" + ChatColor.RED + " " + p.getName());
                        tagyt.addPlayer(p);
                        tagyt.setPrefix(ChatColor.BLACK + "" + ChatColor.MAGIC + "a" + ChatColor.BOLD + "" + ChatColor.BLACK + "You " + ChatColor.RED);
                        tagyt.setSuffix(ChatColor.GOLD + " Tube" + ChatColor.MAGIC + "a");
                        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.BLACK + "" + ChatColor.MAGIC + "a" + ChatColor.BOLD + "" + ChatColor.BLACK + "You" + ChatColor.GOLD + "Tube" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");
                    }
                }
                if (args[0].equalsIgnoreCase("yt")) {
                    if (p.hasPermission("tag.tag.youtube")) {
                        p.setScoreboard(board);
                        this.getConfig().set(p.getName(), "yt");
                        saveConfig();

                        p.setDisplayName(ChatColor.BLACK + "" + ChatColor.MAGIC + "a" + ChatColor.BLACK + "You" + ChatColor.GOLD + "Tube" + ChatColor.MAGIC + "a" + ChatColor.RED + " " + p.getName());
                        tagyt.addPlayer(p);
                        tagyt.setPrefix(ChatColor.BLACK + "" + ChatColor.MAGIC + "a" + ChatColor.BOLD + "" + ChatColor.BLACK + "You " + ChatColor.RED);
                        tagyt.setSuffix(ChatColor.GOLD + " Tube" + ChatColor.MAGIC + "a");
                        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.BLACK + "" + ChatColor.MAGIC + "a" + ChatColor.BOLD + "" + ChatColor.BLACK + "You" + ChatColor.GOLD + "Tube" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");
                    }
                }
                if (args[0].equalsIgnoreCase("off")) {
                    if (p.hasPermission("tag.tag.off")) {
                        tagpadrao.addPlayer(p);
                        this.getConfig().set(p.getName(), "padrao");
                        saveConfig();
                        p.setDisplayName(ChatColor.GRAY + p.getName());

                        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta sem tag agora!");
                    }
                }
                if (args[0].equalsIgnoreCase("staff")) {
                    if (p.hasPermission("tag.tag.staff")) {
                        this.getConfig().set(p.getName(), "staff");
                        saveConfig();
                        p.setScoreboard(board);
                        tagstaff.addPlayer(p);
                        p.setDisplayName(ChatColor.GOLD + "" + ChatColor.MAGIC + "a" + ChatColor.GOLD + "Staff" + ChatColor.MAGIC + "a" + ChatColor.GOLD + " " + p.getName());
                        tagstaff.setPrefix(ChatColor.GOLD + "Staff ");
                        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.GOLD + "" + ChatColor.MAGIC + "a" + ChatColor.GOLD + "Staff" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");
                    }
                }
                if (args[0].equalsIgnoreCase("testador")) {
                    if (p.hasPermission("tag.tag.testador")) {
                        p.setScoreboard(board);
                        this.getConfig().set(p.getName(), "test");
                        saveConfig();
                        tagtestador.addPlayer(p);
                        p.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.MAGIC + "a" + ChatColor.DARK_AQUA + "Testador" + ChatColor.MAGIC + "a" + ChatColor.DARK_AQUA + " " + p.getName());
                        tagtestador.setPrefix(ChatColor.DARK_AQUA + "Testador ");
                        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta com a tag " + ChatColor.DARK_AQUA + "" + ChatColor.MAGIC + "a" + ChatColor.DARK_AQUA + "Testador" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");
                    }
                }
            } else if (args.length == 1) {
                if (args[0] != "dono" || args[0] != "subdono" || args[0] != "admin" || args[0] != "lider" || args[0] != "ajudante" || args[0] != "vipvit" || args[0] != "vip" || args[0] != "youtube" || args[0] != "sd" || args[0] != "yt" || args[0] != "aj") {

                    p.sendMessage(ChatColor.DARK_RED + "Tag> " + ChatColor.RED + "Voce nao possui a tag \"" + args[0] + "\". Compre mais Tags ou tempo em http://gamesplazabrasil.enjin.com/loja");


                }
            }

        }












        if (command.getName().equalsIgnoreCase("settag")) {
            Player p = (Player) sender;
            if (args.length == 0) {

                p.sendMessage(ChatColor.DARK_RED + "Tag> " + ChatColor.RED + "Use /settag <player> <tag>");
                return true;
            }
            if (args.length > 2) {
                p.sendMessage(ChatColor.DARK_RED + "Tag> " + ChatColor.RED + "Use /settag <player> <tag>");
                return true;
            }
            if (args.length == 2) {

                Player target = Bukkit.getPlayer(args[0]);

                if (args[1].equalsIgnoreCase("dono")) {
                    if (p.hasPermission("tag.tag.dono")) {
                        tagdono.addPlayer(target);
                        this.getConfig().set(target.getName(), "dono");
                        saveConfig();
                        target.setScoreboard(board);
                        target.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.MAGIC + "a" + ChatColor.DARK_RED + "Dono" + ChatColor.MAGIC + "a " + ChatColor.DARK_RED + " " + target.getName());
                        tagdono.setPrefix(ChatColor.DARK_RED + "" + ChatColor.MAGIC + "a" + ChatColor.DARK_RED + ChatColor.BOLD + "ADM ");
                        tagdono.setSuffix(ChatColor.DARK_RED + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + " Dono" + ChatColor.DARK_RED + ChatColor.MAGIC + "a");


                        target.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "O player " + p.getName() + " mudou sua tag para" + ChatColor.DARK_RED + ChatColor.MAGIC + "a" + ChatColor.DARK_RED + "DONO" + ChatColor.MAGIC + "a");
                        p.sendMessage(ChatColor.GOLD + "Tag> " + ChatColor.AQUA + "Voce mudou a tag do player " + ChatColor.DARK_PURPLE + target.getName() + ChatColor.AQUA + " para " + ChatColor.DARK_RED + ChatColor.MAGIC + "a" + ChatColor.DARK_RED + "DONO" + ChatColor.MAGIC + "a");
                    }
                }

                if (args[1].equalsIgnoreCase("subdono")) {
                    if (p.hasPermission("tag.tag.subdono")) {
                        this.getConfig().set(target.getName(), "sd");
                        saveConfig();
                        target.setScoreboard(board);
                        tagsubdono.addPlayer(target);

                        tagsubdono.setPrefix(ChatColor.AQUA + "" + ChatColor.BOLD + "ADM ");
                        target.setDisplayName(ChatColor.AQUA + "" + ChatColor.MAGIC + "a" + ChatColor.AQUA + "Subdono" + ChatColor.MAGIC + "a" + ChatColor.AQUA + " " + target.getName());
                        tagsubdono.setSuffix(" Subdono");
                        target.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "O player " + p.getName() + " mudou sua tag para " + ChatColor.AQUA + ChatColor.MAGIC + "a" + ChatColor.AQUA + "SUBDONO" + ChatColor.MAGIC + "a");
                        p.sendMessage(ChatColor.GOLD + "Tag> " + ChatColor.AQUA + "Voce mudou a tag do player " + ChatColor.DARK_PURPLE + target.getName() + ChatColor.AQUA + " para " + ChatColor.AQUA + ChatColor.MAGIC + "a" + ChatColor.AQUA + "SUBDONO" + ChatColor.MAGIC + "a");
                    }
                }
                if (args[1].equalsIgnoreCase("sd")) {
                    if (p.hasPermission("tag.tag.subdono")) {
                        this.getConfig().set(target.getName(), "sd");
                        saveConfig();
                        target.setScoreboard(board);
                        tagsubdono.addPlayer(target);

                        tagsubdono.setPrefix(ChatColor.AQUA + "" + ChatColor.BOLD + "ADM ");
                        target.setDisplayName(ChatColor.AQUA + "" + ChatColor.MAGIC + "a" + ChatColor.AQUA + "Subdono" + ChatColor.MAGIC + "a" + ChatColor.AQUA + " " + target.getName());
                        tagsubdono.setSuffix(" Subdono");
                        target.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "O player " + p.getName() + " mudou sua tag para " + ChatColor.AQUA + ChatColor.MAGIC + "a" + ChatColor.AQUA + "SUBDONO" + ChatColor.MAGIC + "a");
                        p.sendMessage(ChatColor.GOLD + "Tag> " + ChatColor.AQUA + "Voce mudou a tag do player " + ChatColor.DARK_PURPLE + target.getName() + ChatColor.AQUA + " para " + ChatColor.AQUA + ChatColor.MAGIC + "a" + ChatColor.AQUA + "SUBDONO" + ChatColor.MAGIC + "a");
                    }
                }


                if (args[1].equalsIgnoreCase("admin")) {
                    if (p.hasPermission("tag.tag.admin")) {
                        this.getConfig().set(target.getName(), "adm");
                        saveConfig();
                        target.setScoreboard(board);
                        tagadmin.addPlayer(target);
                        target.setDisplayName(ChatColor.RED + "" + ChatColor.MAGIC + "a" + ChatColor.RED + "Admin" + ChatColor.MAGIC + "a" + ChatColor.RED + " " + target.getName());
                        tagadmin.setPrefix(ChatColor.RED + "" + ChatColor.BOLD + "ADM ");
                        tagadmin.setSuffix(ChatColor.RED + "" + ChatColor.ITALIC + " Admin");
                        target.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "O player " + p.getName() + "mudou sua tag para " + ChatColor.RED + ChatColor.MAGIC + "a" + ChatColor.RED + "ADMIN" + ChatColor.MAGIC + "a");
                        p.sendMessage(ChatColor.GOLD + "Tag> " + ChatColor.AQUA + "Voce mudou a tag do player " + ChatColor.DARK_PURPLE + target.getName() + ChatColor.AQUA + " para " + ChatColor.RED + ChatColor.MAGIC + "a" + ChatColor.RED + "ADMIN" + ChatColor.MAGIC + "a");
                    }
                }
                if (args[1].equalsIgnoreCase("lider")) {
                    if (p.hasPermission("tag.tag.admin")) {
                        this.getConfig().set(p.getName(), "lider");
                        saveConfig();
                        target.setScoreboard(board);
                        taglider.addPlayer(target);
                        target.setDisplayName(ChatColor.RED + "" + ChatColor.MAGIC + "a" + ChatColor.RED + "Lider" + ChatColor.MAGIC + "a" + ChatColor.RED + " " + target.getName());
                        taglider.setPrefix(ChatColor.RED + "" + ChatColor.BOLD + "ADM ");
                        taglider.setSuffix(ChatColor.RED + "" + ChatColor.ITALIC + " Lider");
                        target.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "O player " + p.getName() + " mudou sua tag para " + ChatColor.RED + ChatColor.MAGIC + "a" + ChatColor.RED + "LIDER" + ChatColor.MAGIC + "a");
                        p.sendMessage(ChatColor.GOLD + "Tag> " + ChatColor.AQUA + "Voce mudou a tag do player " + ChatColor.DARK_PURPLE + target.getName() + ChatColor.AQUA + " para " + ChatColor.RED + ChatColor.MAGIC + "a" + ChatColor.RED + "LIDER" + ChatColor.MAGIC + "a");

                    }
                }
                if (args[1].equalsIgnoreCase("mod")) {
                    if (p.hasPermission("tag.tag.mod")) {
                        this.getConfig().set(target.getName(), "mod");
                        saveConfig();
                        tagmod.addPlayer(target);
                        target.setScoreboard(board);
                        target.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.MAGIC + "a" + ChatColor.DARK_GREEN + "Mod" + ChatColor.MAGIC + "a" + ChatColor.DARK_GREEN + " " + target.getName());
                        tagmod.setPrefix(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "MOD ");
                        tagmod.setSuffix(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + " Moderador");
                        target.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "O player " + p.getName() + " mudou sua tag para" + ChatColor.DARK_GREEN + ChatColor.MAGIC + "a" + ChatColor.DARK_GREEN + "MODERADOR" + ChatColor.MAGIC + "a");
                        p.sendMessage(ChatColor.GOLD + "Tag> " + ChatColor.AQUA + "Voce mudou a tag do player " + ChatColor.DARK_PURPLE + target.getName() + ChatColor.AQUA + " para " + ChatColor.DARK_GREEN + ChatColor.MAGIC + "a" + ChatColor.DARK_GREEN + "MODERADOR" + ChatColor.MAGIC + "a");
                    }
                }

                if (args[0].equalsIgnoreCase("aj")) {
                    if (p.hasPermission("tag.tag.ajudante")) {
                        this.getConfig().set(target.getName(), "aj");
                        saveConfig();
                        target.setScoreboard(board);
                        target.setDisplayName(ChatColor.GREEN + "" + ChatColor.MAGIC + "a" + ChatColor.GREEN + "Ajudante" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " " + target.getName());
                        tagajudante.addPlayer(target);
                        tagajudante.setPrefix(ChatColor.GREEN + "" + ChatColor.BOLD + "MOD ");
                        tagajudante.setSuffix(ChatColor.GREEN + "" + ChatColor.BOLD + " Ajudante");
                        target.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "O player " + p.getName() + "mudou sua tag para" + ChatColor.GREEN + ChatColor.MAGIC + "a" + ChatColor.GREEN + "AJUDANTE" + ChatColor.MAGIC + "a");
                        p.sendMessage(ChatColor.GOLD + "Tag> " + ChatColor.AQUA + "Voce mudou a tag do player " + ChatColor.DARK_PURPLE + target.getName() + ChatColor.AQUA + " para " + ChatColor.GREEN + ChatColor.MAGIC + "a" + ChatColor.GREEN + "AJUDANTE" + ChatColor.MAGIC + "a");
                    }
                }
                if (args[1].equalsIgnoreCase("vipvit")) {
                    if (p.hasPermission("tag.tag.vipvit")) {
                        this.getConfig().set(target.getName(), "vipvit");
                        saveConfig();
                        target.setScoreboard(board);
                        target.setDisplayName(ChatColor.BLUE + "" + ChatColor.MAGIC + "a" + ChatColor.BLUE + "Vip" + ChatColor.MAGIC + "a" + ChatColor.BLUE + " " + target.getName());
                        tagvipvit.addPlayer(target);
                        tagvipvit.setPrefix(ChatColor.BLUE + "" + ChatColor.BOLD + "VIP ");
                        tagvipvit.setSuffix(ChatColor.BLUE + "" + ChatColor.ITALIC + " Eterno");
                        target.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "O player " + p.getName() + " mudou sua tag para" + ChatColor.BLUE + ChatColor.MAGIC + "a" + ChatColor.BLUE + "VipVit" + ChatColor.MAGIC + "a");
                        p.sendMessage(ChatColor.GOLD + "Tag> " + ChatColor.AQUA + "Voce mudou a tag do player " + ChatColor.DARK_PURPLE + target.getName() + ChatColor.AQUA + " para " + ChatColor.BLUE + ChatColor.MAGIC + "a" + ChatColor.BLUE + "VipVit" + ChatColor.MAGIC + "a");
                    }
                }
                if (args[1].equalsIgnoreCase("vip")) {
                    if (p.hasPermission("tag.tag.vip")) {
                        this.getConfig().set(target.getName(), "vip");
                        saveConfig();
                        target.setScoreboard(board);
                        target.setDisplayName(ChatColor.YELLOW + "" + ChatColor.MAGIC + "a" + ChatColor.YELLOW + "Vip" + ChatColor.MAGIC + "a" + ChatColor.YELLOW + " " + target.getName());
                        tagvip.addPlayer(target);
                        tagvip.setPrefix(ChatColor.YELLOW + "" + ChatColor.BOLD + "VIP ");
                        target.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "O player " + p.getName() + " mudou sua tag para" + ChatColor.YELLOW + ChatColor.MAGIC + "a" + ChatColor.YELLOW + "VIP" + ChatColor.MAGIC + "a");
                        p.sendMessage(ChatColor.GOLD + "Tag> " + ChatColor.AQUA + "Voce mudou a tag do player " + ChatColor.DARK_PURPLE + target.getName() + ChatColor.AQUA + " para " + ChatColor.YELLOW + ChatColor.MAGIC + "a" + ChatColor.YELLOW + "VIP" + ChatColor.MAGIC + "a");
                    }
                }


                if (args[1].equalsIgnoreCase("yt")) {
                    if (p.hasPermission("tag.tag.youtube")) {
                        target.setScoreboard(board);
                        this.getConfig().set(target.getName(), "yt");
                        saveConfig();

                        target.setDisplayName(ChatColor.BLACK + "" + ChatColor.MAGIC + "a" + ChatColor.BLACK + "You" + ChatColor.GOLD + "Tube" + ChatColor.MAGIC + "a" + ChatColor.RED + " " + target.getName());
                        tagyt.addPlayer(target);
                        tagyt.setPrefix(ChatColor.BLACK + "" + ChatColor.MAGIC + "a" + ChatColor.BOLD + "" + ChatColor.BLACK + "You " + ChatColor.RED);
                        tagyt.setSuffix(ChatColor.GOLD + " Tube" + ChatColor.MAGIC + "a");
                        target.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "O player " + p.getName() + " mudou sua tag para " + ChatColor.BLACK + "" + ChatColor.MAGIC + "a" + ChatColor.BOLD + "" + ChatColor.BLACK + "You" + ChatColor.GOLD + "Tube" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");
                    }
                }
                if (args[1].equalsIgnoreCase("off")) {
                    if (p.hasPermission("tag.tag.off")) {
                        tagpadrao.addPlayer(target);
                        this.getConfig().set(target.getName(), "padrao");
                        saveConfig();
                        target.setDisplayName(ChatColor.GRAY + target.getName());

                        target.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "O player " + p.getName() + " desabilitou sua tag");
                    }
                }


            } else if (args.length == 2) {
                if (args[1] != "dono" || args[1] != "subdono" || args[1] != "admin" || args[1] != "lider" || args[1] != "ajudante" || args[1] != "vipvit" || args[1] != "vip" || args[1] != "youtube" || args[1] != "sd" || args[1] != "yt" || args[1] != "aj") {

                    p.sendMessage(ChatColor.DARK_RED + "Tag> " + ChatColor.RED + "Voce nao possui a tag \"" + args[0] + "\". Compre mais Tags ou tempo em http://gamesplazabrasil.enjin.com/loja");


                }


            }

        }


        return false;
      }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll();
    }
}


