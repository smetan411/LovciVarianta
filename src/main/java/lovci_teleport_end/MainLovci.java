package lovci_teleport_end;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public final class MainLovci extends JavaPlugin implements Listener {

    private StavHry stavHry = new StavHry();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new SmrtBezce(stavHry), this);
        getServer().getPluginManager().registerEvents(new PruchodPortalem(stavHry), this);
        getServer().getPluginManager().registerEvents(new ZabitiDraka(stavHry), this);
        getServer().getPluginManager().registerEvents(new OziveniLovce(stavHry), this);
        getServer().getPluginManager().registerEvents(new VyhozeniKompasu(stavHry), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        switch (command.getName()) {
            case "stop" -> {
                stavHry.stop();
                stavHry.zpravaBezci("Hra byla ukoncena.", true);
                stavHry.zpravaLovcum("Hra byla ukoncena.", true);
                stavHry.getLovci().forEach(lovec -> lovec.getInventory().clear());
                stavHry.getBezec().getInventory().clear();
            }
            case "start" -> {
                if (stavHry.jedeHra()) {
                    sender.sendMessage("Hra jede, nemuzes nastartovat novou, dokud hra neskonci.");
                    return true;
                }
                if (args.length != 1) {
                    sender.sendMessage("Spatne, musis zadat: start jmenoBezce.");
                    return false;
                }
                String jmenoBezce = args[0];
                Player bezec = sender.getServer().getPlayer(jmenoBezce);
                if (bezec == null) {
                    sender.sendMessage("Bezec jmenem " + jmenoBezce + " neexistuje.");
                    return true;
                }
                List<Player> lovci = new ArrayList<>(sender.getServer().getOnlinePlayers());
                lovci.remove(bezec);
                stavHry.setBezec(bezec, lovci);
                stavHry.zpravaBezci("Jsi bezec, prchej!", true);
                stavHry.zpravaLovcum("Jsi lovec, chyt bezce!", true);

                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    getServer().getOnlinePlayers().forEach(player2 -> {
                        player2.teleport(player.getLocation());
                        stavHry.getLovci().forEach(lovec -> lovec.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 720, 255, true, false, false)));
                        stavHry.getLovci().forEach(lovec -> lovec.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 720, 255, true, false, false)));
                        stavHry.getLovci().forEach(lovec -> lovec.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 720, 155, true, false, false)));
                        stavHry.getLovci().forEach(lovec -> lovec.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 720, 155, true, false, false)));
                        player2.getInventory().clear();
                        player2.getInventory().addItem(new ItemStack(Material.BREAD, 16));
                    });
                }
                new Kompas(stavHry).dejLovcumKompas();
                stavHry.start();
            }
        }
        return true;
    }
}
