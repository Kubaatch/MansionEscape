package logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author   Jakub Hřebíček
 *@version  1.1 2024/03/23
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Hra hra;

    /**
    *  Konstruktor třídy
    *  
    *  @param hra instance třídy hry, ve které se bude ve hře "chodit"
    */    
    public PrikazJdi(Hra hra) {
        this.hra = hra;
        this.plan = hra.getHerniPlan();
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaná sousední místnost
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        // pokud chybí druhé slovo (sousední prostor), tak ....
        if (parametry.length == 0) {
            return "Kam mám jít? Musíš zadat jméno východu";
        }
        if (parametry.length > 1) {
            return "Napsal jsi toho nějak moc...";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        else {
            plan.setAktualniProstor(sousedniProstor);
            if (plan.getAktualniProstor().equals(plan.getVyherniProstor())) {
                hra.setEpilog("Gratuluji, vyhrál jsi hru!");
                hra.setKonecHry(true);
                return "";
            }
            return sousedniProstor.dlouhyPopis() + '\n' + plan.getKapsy().dlouhyPopis();
        }
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
