package ohtu.ohtuvarasto;

public class Varasto {

    // --- piilotettu tietorakenteen toteutus: ---
    private double tilavuus;  // paljonko varastoon mahtuu,  > 0
    private double saldo;     // paljonko varastossa on nyt, >= 0

    // --- konstruktorit: ---
    public Varasto(double tilavuus) {  // tilavuus on annettava
        if (tilavuus > 0.0) {
            this.tilavuus = tilavuus;
        } else {// virheellinen, nollataan
            this.tilavuus = 0.0;  // => k?yttökelvoton varasto
        }
        saldo = 0;     // oletus: varasto on tyhj?
    }

    public Varasto(double tilavuus, double alkuSaldo) { // kuormitetaan
        this.tilavuus = Math.max(tilavuus, 0); //if lause pois
        if (alkuSaldo < 0.0) {
            this.saldo = 0.0;
        } else if (alkuSaldo <= tilavuus) {// mahtuu
            this.saldo = alkuSaldo;
        } else {
            this.saldo = tilavuus;  // t?yteen ja ylim??r? hukkaan!
        }
    }

    // --- ottavat aksessorit eli getterit: ---
    public double getSaldo() {
        return saldo;
    }

    public double getTilavuus() {
        return tilavuus;
    }

    public double paljonkoMahtuu() {  // huom: ominaisuus voidaan myös laskea
        return tilavuus - saldo;        //  ei tarvita erillist? kentt?? vielaTilaa tms.
    }

    // --- asettavat aksessorit eli setterit: ---
    public void lisaaVarastoon(double maara) {
        if (maara < 0) { // virhetilanteessa voidaan tehd? 
            return;       // t?llainen pikapoistuminenkin!
        }
        if (maara <= paljonkoMahtuu()) { // omia aksessoreita voi kutsua
            saldo = saldo + maara;          // ihan suoraan sellaisinaan
        } else {
            saldo = tilavuus;  // t?yteen ja ylim??r? hukkaan!
        }
    }

    public double otaVarastosta(double maara) {
        if (maara < 0) {// virhetilanteessa voidaan tehd? 
            return 0.0;   // t?llainen pikapoistuminenkin!
        }
        if (maara > saldo) {          // annetaan mit? voidaan
            double kaikkiMitaVoidaan = saldo;
            saldo = 0.0;               // ja tyhj?ksi menee
            return kaikkiMitaVoidaan;  // poistutaan saman tien
        }
        // jos t?nne p??st??n, kaikki pyydetty voidaan antaa
        saldo = saldo - maara;  // v?hennet??n annettava saldosta
        return maara;
    }

    // --- Merkkijonoesitys Varasto-oliolle: ----
    public String toString() {
        return ("saldo = " + saldo + ", viel? tilaa " + paljonkoMahtuu());
    }
}