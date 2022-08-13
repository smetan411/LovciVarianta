package lovcivarianta;

import org.bukkit.entity.Player;

import java.util.List;

public class StavHry {
    private Player bezec;
    private List<Player> lovci;

    public enum Stav {
        ZACATEK_HRY, PROSEL_PORTALEM, KONEC_HRY
    }

    private Stav stav = Stav.KONEC_HRY;

    public Stav getStav() {
        return stav;
    }

    public void zmenStav(Stav stav) {
        this.stav = stav;
    }

    public void start() {
        stav = Stav.ZACATEK_HRY;
    }

    public void stop() {
        stav = Stav.KONEC_HRY;
    }

    public boolean jedeHra() {
        return stav != Stav.KONEC_HRY;
    }

    public void setBezec(Player bezec, List<Player> lovci) {
        this.bezec = bezec;
        this.lovci = lovci;
    }

    public Player getBezec() {
        return bezec;
    }

    public List<Player> getLovci() {
        return lovci;
    }

    public void zpravaLovcum(String zprava) {
        lovci.forEach(lovec -> lovec.sendMessage(zprava));
    }

    public void zpravaBezci(String zprava) {
        bezec.sendMessage(zprava);
    }

}
