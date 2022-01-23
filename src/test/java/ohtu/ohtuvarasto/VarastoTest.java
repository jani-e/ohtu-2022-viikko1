package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pit�isi olla sama kun lis�tty m��r�
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pit�isi viel� olla tilavuus-lis�tt�v� m��r� eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLis��Tilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pit�isi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    //omat testit
    @Test
    public void toimiikoToString() {
        assertEquals("saldo = 0.0, viel� tilaa " + varasto.paljonkoMahtuu(), varasto.toString());
    }
    
    @Test
    public void onkoVarastonTilavuusKelvollinen() {
        Varasto varasto = new Varasto (-2);
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiLisaaVarastoon() {
        varasto.lisaaVarastoon(2);
        varasto.lisaaVarastoon(-3);
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void meneekoSaldoYliTilavuuden() {
        varasto.lisaaVarastoon(11);
        //alkusaldo 0, alkutilavuus 10
        //11 -> 10
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoVarastosta() {
        varasto.otaVarastosta(-5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanEnemmanKuinSaldoaOn() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(6);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    //Varaston tilavuus ja alkusaldo konstruktori
    @Test
    public void onkoKelvollinenVarastoAnnetullaTilavuudella() {
        Varasto varasto = new Varasto(5, 2);
        assertEquals(5, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void onkoKelvollinenVarastoAnnetullaSaldolla() {
        Varasto varasto = new Varasto(5, 2);
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuus() {
        Varasto varasto = new Varasto(-5, 0);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenSaldo() {
        Varasto varasto = new Varasto(0, -5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

}