package lovci;

import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class ZabitiEndermana implements Listener {

    private final StavHry stavHry;

    public ZabitiEndermana(StavHry stavHry) {
        this.stavHry = stavHry;
    }

    @EventHandler
    public void zabitiEndermana(EntityDeathEvent event){
        var zemrelaEntita = event.getEntity();
        var utocnik = zemrelaEntita.getKiller();
        if(zemrelaEntita.getType().equals(EntityType.ENDERMAN) &&
                (utocnik.equals(stavHry.getBezec()) &&
                (stavHry.getStav().equals(StavHry.Stav.PROSEL_PORTALEM)))){
            stavHry.stop();
            stavHry.zpravaBezci("Zabil jsi Endermana, vyhrals. Konec hry.");
            stavHry.zpravaLovcum("Hrac zabil Endermana, prohrali jste. Konec hry.");
        }
    }
}
