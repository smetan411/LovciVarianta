package lovci_teleport_end;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class SmrtBezce implements Listener {

    private final StavHry stavHry;

    public SmrtBezce(StavHry stavHry) {
        this.stavHry = stavHry;
    }

    @EventHandler
    public void bezecZemrel(PlayerDeathEvent smrtBezce) {
        if (!stavHry.jedeHra()) return;

        Player player = smrtBezce.getEntity();
        if (player.equals(stavHry.getBezec())) {
            stavHry.zpravaBezci("Zabili te, konec hry.", true);
            stavHry.zpravaLovcum("Vyhrals, bezec mrtev.", true);
            stavHry.stop();
        }
    }

}
