package lovcivarianta;

import org.bukkit.entity.Player;

import java.util.List;

public class StavHry {
    private Player bezec;
    private List<Player> lovci;
    private boolean hraJede = false;

    public enum Stav {
        PROSEL_PORTALEM, ZACATEK_HRY
    }

    private Stav stav = Stav.ZACATEK_HRY;

    public Stav getStav() {
        return stav;
    }

    public void zmenStav(Stav stav) {
        this.stav = stav;
    }

    public void start() {
        hraJede = true;
    }

    public void stop() {
        hraJede = false;
    }

    public boolean jedeHra() {
        return hraJede;
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
