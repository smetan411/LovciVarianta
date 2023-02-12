package lovci_teleport_end;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class ZabitiDraka implements Listener {

    private final StavHry stavHry;

    public ZabitiDraka(StavHry stavHry) {
        this.stavHry = stavHry;
    }

    @EventHandler
    public void zabitiDraka(EntityDeathEvent event) {
        var zemrelaEntita = event.getEntity();
        var utocnik = zemrelaEntita.getKiller();

        if (EntityType.ENDER_DRAGON.equals(zemrelaEntita.getType()) &&
                stavHry.getBezec().equals(utocnik) &&
                stavHry.getStav() == StavHry.Stav.PROSEL_PORTALEM) {
            stavHry.stop();
            stavHry.zpravaBezci("Zabil jsi Draka, vyhrals. Konec hry.", true);
            stavHry.zpravaLovcum("Hrac zabil Draka, prohrali jste. Konec hry.", true);
        }
    }
}
