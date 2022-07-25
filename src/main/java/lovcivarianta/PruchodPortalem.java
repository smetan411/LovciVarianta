package lovcivarianta;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
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
        var typUdalosti = event.getCause();
        if (typUdalosti == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
            stavHry.zmenStav(StavHry.Stav.PROSEL_PORTALEM);
            stavHry.zpravaBezci("Utekls jim do Netheru, ale jeste neni konec hry.");
            stavHry.zpravaLovcum("Zdrhl vam do Netheru, ale jeste neni konec hry.");
        }
    }
}


