package lovcivarianta;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PruchodPortalem implements Listener {

    private final StavHry stavHry;

    public PruchodPortalem(StavHry stavHry) {
        this.stavHry = stavHry;
    }

    @EventHandler
    public void projdiPortalem(PlayerTeleportEvent event) {
        if (!stavHry.jedeHra()) return;
        if (event.getCause() != PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) return;

        if ( event.getPlayer().equals(stavHry.getBezec()) && stavHry.getStav() == StavHry.Stav.ZACATEK_HRY){
            stavHry.zmenStav(StavHry.Stav.PROSEL_PORTALEM);
            stavHry.zpravaBezci("Utekls do Netheru.", false);
            stavHry.zpravaLovcum("Bezec zdrhl do Netheru.", false);
        }
        if (stavHry.getStav() == StavHry.Stav.PROSEL_PORTALEM) {
            event.setCancelled(true);
            event.getPlayer().teleport(Bukkit.getWorld("world_the_end").getSpawnLocation());
        }
    }
}


