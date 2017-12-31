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

            tagDono(p);

        }
        if (tag == "sd") {

            tagSubno(p);

        }
        if (tag == "adm") {

            tagAdmin(p);
        }
        if (tag == "lider") {

           tagLider(p);
        }
        if (tag == "mod") {

          tagMod(p);
        }
        if (tag == "aj") {

           tagAjudante(p);
        }
        if (tag == "vipvit") {

           tagVipVit(p);
        }
        if (tag == "vip") {

            tagVip(p);
        }
        if (tag == "yt") {

           tagYt(p);
        }
        if (tag == "staff") {

           tagStaff(p);
        }
        if (tag == "test") {

            tagTestador(p);
        }
        if (tag == "padrao") {

            tagOff(p);
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
                        tagDono(p);

                      }
                }

                if (args[0].equalsIgnoreCase("subdono")) {
                    if (p.hasPermission("tag.tag.subdono")) {
                        tagSubno(p);

                    }
                }
                if (args[0].equalsIgnoreCase("sd")) {
                    if (p.hasPermission("tag.tag.subdono")) {
                        tagSubno(p);

                    }
                }


                if (args[0].equalsIgnoreCase("admin")) {
                    if (p.hasPermission("tag.tag.admin")) {
                        tagAdmin(p);

                       }
                }
                if (args[0].equalsIgnoreCase("lider")) {
                    if (p.hasPermission("tag.tag.admin")) {

                    tagLider(p);
                    }
                }
                if (args[0].equalsIgnoreCase("mod")) {
                    if (p.hasPermission("tag.tag.mod")) {

                        tagMod(p);

                    }
                }
                if (args[0].equalsIgnoreCase("ajudante")) {
                    if (p.hasPermission("tag.tag.ajudante")) {

                        tagAjudante(p);

                    }
                }
                if (args[0].equalsIgnoreCase("aj")) {
                    if (p.hasPermission("tag.tag.ajudante")) {

                    tagAjudante(p);

                    }
                }
                if (args[0].equalsIgnoreCase("vipvit")) {
                    if (p.hasPermission("tag.tag.vipvit")) {

                    tagVipVit(p);

                    }
                }
                if (args[0].equalsIgnoreCase("vip")) {
                    if (p.hasPermission("tag.tag.vip")) {

                        tagVip(p);

                    }
                }
                if (args[0].equalsIgnoreCase("youtube")) {
                    if (p.hasPermission("tag.tag.youtube")) {

                    tagYt(p);

                    }
                }
                if (args[0].equalsIgnoreCase("yt")) {
                    if (p.hasPermission("tag.tag.youtube")) {
                      //tagYt(p);
                        p.sendMessage(ChatColor.DARK_RED + "Mudar Tag> " + ChatColor.RED + "A tag YouTuber esta temporariametne offline, devido a um erro na programacao! Em breve ela estara de volta :)");
                    }
                }
                if (args[0].equalsIgnoreCase("off")) {
                    if (p.hasPermission("tag.tag.off")) {

                        tagOff(p);

                    }
                }
                if (args[0].equalsIgnoreCase("staff")) {
                    if (p.hasPermission("tag.tag.staff")) {

                        tagStaff(p);

                    }
                }
                if (args[0].equalsIgnoreCase("testador")) {
                    if (p.hasPermission("tag.tag.testador")) {

                    tagTestador(p);

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

                        tagDono(target);
                        target.sendMessage(ChatColor.GOLD + "Mudar Tag> " + ChatColor.AQUA + "O player " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.AQUA + " mudou a sua tag");

                    }
                }

                if (args[1].equalsIgnoreCase("subdono")) {
                    if (p.hasPermission("tag.tag.subdono")) {

                    tagSubno(target);
                        target.sendMessage(ChatColor.GOLD + "Mudar Tag> " + ChatColor.AQUA + "O player " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.AQUA + " mudou a sua tag");

                    }
                }
                if (args[1].equalsIgnoreCase("sd")) {
                    if (p.hasPermission("tag.tag.subdono")) {
                        this.getConfig().set(target.getName(), "sd");

                    tagSubno(target);
                        target.sendMessage(ChatColor.GOLD + "Mudar Tag> " + ChatColor.AQUA + "O player " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.AQUA + " mudou a sua tag");
                    }
                }


                if (args[1].equalsIgnoreCase("admin")) {
                    if (p.hasPermission("tag.tag.admin")) {

                        tagAdmin(target);
                        target.sendMessage(ChatColor.GOLD + "Mudar Tag> " + ChatColor.AQUA + "O player " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.AQUA + " mudou a sua tag");
                    }
                }
                if (args[1].equalsIgnoreCase("lider")) {
                    if (p.hasPermission("tag.tag.admin")) {

                        tagLider(target);
                        target.sendMessage(ChatColor.GOLD + "Mudar Tag> " + ChatColor.AQUA + "O player " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.AQUA + " mudou a sua tag");
                    }
                }
                if (args[1].equalsIgnoreCase("mod")) {
                    if (p.hasPermission("tag.tag.mod")) {

                        tagMod(target);
                        target.sendMessage(ChatColor.GOLD + "Mudar Tag> " + ChatColor.AQUA + "O player " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.AQUA + " mudou a sua tag");
                    }
                }

                if (args[0].equalsIgnoreCase("aj")) {
                    if (p.hasPermission("tag.tag.ajudante")) {

                    tagAjudante(target);
                        target.sendMessage(ChatColor.GOLD + "Mudar Tag> " + ChatColor.AQUA + "O player " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.AQUA + " mudou a sua tag");
                    }
                }
                if (args[1].equalsIgnoreCase("vipvit")) {
                    if (p.hasPermission("tag.tag.vipvit")) {

                        tagVipVit(target);
                        target.sendMessage(ChatColor.GOLD + "Mudar Tag> " + ChatColor.AQUA + "O player " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.AQUA + " mudou a sua tag");
                    }
                }
                if (args[1].equalsIgnoreCase("vip")) {
                    if (p.hasPermission("tag.tag.vip")) {

                        tagVip(target);
                        target.sendMessage(ChatColor.GOLD + "Mudar Tag> " + ChatColor.AQUA + "O player " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.AQUA + " mudou a sua tag");
                    }
                }


                if (args[1].equalsIgnoreCase("yt")) {
                    if (p.hasPermission("tag.tag.youtube")) {
/*
                        tagYt(target);
                        target.sendMessage(ChatColor.GOLD + "Mudar Tag> " + ChatColor.AQUA + "O player " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.AQUA + " mudou a sua tag");
  */
                    p.sendMessage(ChatColor.DARK_RED + "Mudar Tag> " + ChatColor.RED + "A tag YouTuber esta temporariametne offline, devido a um erro na programacao! Em breve ela estara de volta :)");
                    }
                }
                if (args[1].equalsIgnoreCase("off")) {
                    if (p.hasPermission("tag.tag.off")) {

                    tagOff(target);
                        target.sendMessage(ChatColor.GOLD + "Mudar Tag> " + ChatColor.AQUA + "O player " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.AQUA + " mudou a sua tag");
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

      void tagDono (Player p)  {

          tagdono.addPlayer(p);
          this.getConfig().set(p.getName(), "dono");
          saveConfig();
          p.setScoreboard(board);
          p.setDisplayName( ChatColor.DARK_RED + "" + ChatColor.BOLD + "" + ChatColor.MAGIC + "a" + ChatColor.DARK_RED + "DONO" + ChatColor.MAGIC + "a " + ChatColor.DARK_RED + " " + p.getName());
          tagdono.setPrefix(ChatColor.DARK_RED + "" + ChatColor.BOLD + "ADM " + ChatColor.WHITE);
          tagdono.setSuffix(ChatColor.DARK_RED + "" + ChatColor.BOLD + "" + ChatColor.ITALIC + " Dono");


          p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.DARK_RED + ChatColor.MAGIC + "a" + ChatColor.DARK_RED + "DONO" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");



      }
    void tagSubno (Player p)  {

        this.getConfig().set(p.getName(), "sd");
        saveConfig();
        p.setScoreboard(board);
        tagsubdono.addPlayer(p);

        tagsubdono.setPrefix(ChatColor.AQUA + "" + ChatColor.BOLD + "ADM " + ChatColor.WHITE );
        p.setDisplayName(ChatColor.BOLD + "" + ChatColor.AQUA + "" + ChatColor.MAGIC + "a" + ChatColor.AQUA + "SUBDONO" + ChatColor.MAGIC + "a" + ChatColor.AQUA + " " + p.getName());
        tagsubdono.setSuffix(ChatColor.AQUA + ""  + ChatColor.BOLD + " Subdono");
        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.AQUA + ChatColor.MAGIC + "a" + ChatColor.AQUA + "SUBDONO" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");

    }



    void tagAdmin(Player p)  {

        this.getConfig().set(p.getName(), "adm");
        saveConfig();
        p.setScoreboard(board);
        tagadmin.addPlayer(p);
        p.setDisplayName(ChatColor.BOLD + "" + ChatColor.RED + "" + ChatColor.MAGIC + "a" + ChatColor.RED + "ADMIN" + ChatColor.MAGIC + "a" + ChatColor.RED + " " + p.getName());
        tagadmin.setPrefix(ChatColor.RED + "" + ChatColor.BOLD + "ADM " + ChatColor.WHITE);
        tagadmin.setSuffix(ChatColor.RED + "" + ChatColor.ITALIC + " Admin");
        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.RED + ChatColor.MAGIC + "a" + ChatColor.RED + "ADMIN" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");


    }

    void tagLider (Player p) {

        this.getConfig().set(p.getName(), "lider");
        saveConfig();
        p.setScoreboard(board);
        taglider.addPlayer(p);
        p.setDisplayName(ChatColor.BOLD + "" + ChatColor.RED + "" + ChatColor.MAGIC + "a" + ChatColor.RED + "LIDER" + ChatColor.MAGIC + "a" + ChatColor.RED + " " + p.getName());
        taglider.setPrefix(ChatColor.RED + "" + ChatColor.BOLD + "ADM " + ChatColor.WHITE);
        taglider.setSuffix(ChatColor.RED + "" + ChatColor.ITALIC + " Lider");
        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.RED + ChatColor.MAGIC + "a" + ChatColor.RED + "LIDER" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");

    }
    void tagMod (Player p) {

        this.getConfig().set(p.getName(), "mod");
        saveConfig();
        tagmod.addPlayer(p);
        p.setScoreboard(board);
        p.setDisplayName(ChatColor.BOLD + "" + ChatColor.DARK_GREEN + "" + ChatColor.MAGIC + "a" + ChatColor.DARK_GREEN + "MODERADOR" + ChatColor.MAGIC + "a" + ChatColor.DARK_GREEN + " " + p.getName());
        tagmod.setPrefix(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "MOD " + ChatColor.WHITE );
        tagmod.setSuffix(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + " Moderador");
        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.DARK_GREEN + ChatColor.MAGIC + "a" + ChatColor.DARK_GREEN + "MODERADOR" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");

    }
    void tagAjudante (Player p) {

        this.getConfig().set(p.getName(), "aj");
        saveConfig();
        p.setScoreboard(board);
        p.setDisplayName(ChatColor.BOLD + "" + ChatColor.GREEN + "" + ChatColor.MAGIC + "a" + ChatColor.GREEN + "AJUDANTE" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " " + p.getName());
        tagajudante.addPlayer(p);
        tagajudante.setPrefix(ChatColor.GREEN + "" + ChatColor.BOLD + "MOD " + ChatColor.WHITE );
        tagajudante.setSuffix(ChatColor.GREEN + "" + ChatColor.BOLD + " Ajudante");
        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.GREEN + ChatColor.MAGIC + "a" + ChatColor.GREEN + "AJUDANTE" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");


    }

    void tagVipVit (Player p) {

        this.getConfig().set(p.getName(), "vipvit");
        saveConfig();
        p.setScoreboard(board);
        p.setDisplayName(ChatColor.BOLD + "" + ChatColor.BLUE + "" + ChatColor.MAGIC + "a" + ChatColor.BLUE + "VIP" + ChatColor.MAGIC + "a" + ChatColor.BLUE + " " + p.getName());
        tagvipvit.addPlayer(p);
        tagvipvit.setPrefix(ChatColor.BLUE + "" + ChatColor.BOLD + "VIP " + ChatColor.WHITE);
        tagvipvit.setSuffix(ChatColor.BLUE + "" + ChatColor.ITALIC + " Eterno");
        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.BLUE + ChatColor.MAGIC + "a" + ChatColor.BLUE + "VipVit" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");


    }
    void tagVip (Player p) {

        this.getConfig().set(p.getName(), "vip");
        saveConfig();
        p.setScoreboard(board);
        p.setDisplayName(ChatColor.BOLD + "" + ChatColor.YELLOW + "" + ChatColor.MAGIC + "a" + ChatColor.YELLOW + "VIP" + ChatColor.MAGIC + "a" + ChatColor.YELLOW + " " + p.getName());
        tagvip.addPlayer(p);
        tagvip.setPrefix(ChatColor.YELLOW + "" + ChatColor.BOLD + "VIP ");
        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.YELLOW + ChatColor.MAGIC + "a" + ChatColor.YELLOW + "VIP" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");


    }
    void tagYt (Player p){

        p.setScoreboard(board);
        this.getConfig().set(p.getName(), "yt");
        saveConfig();
        p.setDisplayName(ChatColor.BOLD + "" + ChatColor.BLACK + "" + ChatColor.MAGIC + "a" + ChatColor.BLACK + "YOU" + ChatColor.WHITE + "" + ChatColor.DARK_RED + "TUBER" + ChatColor.MAGIC + "a" + ChatColor.WHITE + "" + ChatColor.GOLD + " " + p.getName());
        tagyt.addPlayer(p);
        tagyt.setPrefix(ChatColor.BLACK + "YOU" + ChatColor.RED + "TUBER " + ChatColor.GOLD + p.getName());
        //tagyt.setSuffix(ChatColor.DARK_RED + " TUBER");
        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.BLACK + "" + ChatColor.MAGIC + "a" + ChatColor.BOLD + "" + ChatColor.BLACK + "YOU" + ChatColor.DARK_RED + "TUBER" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");


    }

    void tagOff (Player p){

        tagpadrao.addPlayer(p);
        this.getConfig().set(p.getName(), "padrao");
        saveConfig();
        p.setDisplayName(ChatColor.GRAY + p.getName());

        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta sem tag agora!");

    }

    void tagStaff (Player p) {

        this.getConfig().set(p.getName(), "staff");
        saveConfig();
        p.setScoreboard(board);
        tagstaff.addPlayer(p);
        p.setDisplayName(ChatColor.BOLD + "" + ChatColor.GOLD + "" + ChatColor.MAGIC + "a" + ChatColor.GOLD + "STAFF" + ChatColor.MAGIC + "a" + ChatColor.GOLD + " " + p.getName());
        tagstaff.setPrefix(ChatColor.GOLD + "Staff ");
        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta usando a tag " + ChatColor.GOLD + "" + ChatColor.MAGIC + "a" + ChatColor.GOLD + "STAFF" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");


    }
    void tagTestador (Player p) {

        p.setScoreboard(board);
        this.getConfig().set(p.getName(), "test");
        saveConfig();
        tagtestador.addPlayer(p);
        p.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.MAGIC + "a" + ChatColor.DARK_AQUA + "TESTADOR" + ChatColor.MAGIC + "a" + ChatColor.DARK_AQUA + " " + p.getName());
        tagtestador.setPrefix(ChatColor.DARK_AQUA + "Testador ");
        p.sendMessage(ChatColor.BLUE + "Tag> " + ChatColor.GREEN + "Voce esta com a tag " + ChatColor.DARK_AQUA + "" + ChatColor.MAGIC + "a" + ChatColor.DARK_AQUA + "TESTADOR" + ChatColor.MAGIC + "a" + ChatColor.GREEN + " agora!");



    }


    @Override
    public void onDisable() {
        HandlerList.unregisterAll();
    }
}


