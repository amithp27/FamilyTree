package Family;

import Family.Family;
import Family.Gender;

import static Family.Relationships.*;

public class CallMain {

    public static void main(String[] args) {
        final Family shan = new Family(CallMain::fillFamilyTree);
        
        final Family.Member mem1 = shan.getMember("Krpi");
        final Family.Member mem2 = shan.getMember("Dritha");
        final Family.Member mem3 = shan.getMember("Aras");

        System.out.println("1. Sister-In-Laws of " +mem1+ " : " + SISTER_IN_LAW.to(mem1));
        System.out.println("2. Cousins of " +mem2+ " : " + COUSIN.to(mem2));
        System.out.println("3. Parental Uncles of " +mem2+ " : " + PATERNAL_UNCLE.to(mem2));
        System.out.println("4. Grand-daughters of " +mem3+ " : " + GRANDDAUGHTER.to(mem3));
    }

    public static void fillFamilyTree(Family fam) {
        //Constructed as per Chart in PDF [https://www.geektrust.in/api/pdf/open/PS1]

        //Members
        Family.Member kingShan = fam.addMember("King Shan", Gender.MALE);
        Family.Member queenAnga = fam.addMember("Queen Anga", Gender.FEMALE);
        Family.Member ish = fam.addMember("Ish", Gender.MALE);
        Family.Member chit = fam.addMember("Chit", Gender.MALE);
        Family.Member ambi = fam.addMember("Amba", Gender.FEMALE);
        Family.Member vich = fam.addMember("Vich", Gender.MALE);
        Family.Member lika = fam.addMember("Lika", Gender.FEMALE);
        Family.Member aras = fam.addMember("Aras", Gender.MALE);
        Family.Member chitra = fam.addMember("Chitra", Gender.FEMALE);
        Family.Member satya = fam.addMember("Satya", Gender.FEMALE);
        Family.Member vyan = fam.addMember("Vyan", Gender.MALE);
        Family.Member dritha = fam.addMember("Dritha", Gender.FEMALE);
        Family.Member jaya = fam.addMember("Jaya", Gender.MALE);
        Family.Member vritha = fam.addMember("Vritha", Gender.MALE);
        Family.Member tritha = fam.addMember("Tritha", Gender.FEMALE);
        Family.Member vila = fam.addMember("Vila", Gender.FEMALE);
        Family.Member chika = fam.addMember("Chika", Gender.FEMALE);
        Family.Member arit = fam.addMember("Arit", Gender.MALE);
        Family.Member ahit = fam.addMember("Ahit", Gender.MALE);
        Family.Member jnki = fam.addMember("Jnki", Gender.FEMALE);
        Family.Member satvy = fam.addMember("Satvy", Gender.FEMALE);
        Family.Member asva = fam.addMember("Asva", Gender.MALE);
        Family.Member krpi = fam.addMember("Krpi", Gender.FEMALE);
        Family.Member vyas = fam.addMember("Vyas", Gender.MALE);
        Family.Member atya = fam.addMember("Atya", Gender.FEMALE);
        Family.Member yodhan = fam.addMember("Yodhan", Gender.MALE);
        Family.Member laki = fam.addMember("Laki", Gender.MALE);
        Family.Member lavnya = fam.addMember("lavnya", Gender.FEMALE);
        Family.Member vasa = fam.addMember("Vasa", Gender.MALE);
        Family.Member kriya = fam.addMember("kriya", Gender.MALE);
        Family.Member krithi = fam.addMember("Krithi", Gender.FEMALE);

        //Relationships
        //KingShan and QueenAnga Children
        kingShan.isSpouseOf(queenAnga);
        kingShan.isParentOf(ish);
        queenAnga.isParentOf(ish);
        kingShan.isParentOf(chit);
        queenAnga.isParentOf(chit);
        kingShan.isParentOf(vich);
        queenAnga.isParentOf(vich);
        kingShan.isParentOf(satya);
        queenAnga.isParentOf(satya);
        kingShan.isParentOf(aras);
        queenAnga.isParentOf(aras);

        //Chit and Ambi Children
        chit.isSpouseOf(ambi);
        chit.isParentOf(dritha);
        ambi.isParentOf(dritha);
        chit.isParentOf(vritha);
        ambi.isParentOf(vritha);
        chit.isParentOf(tritha);
        ambi.isParentOf(tritha);

        //Vich and Lika children
        vich.isSpouseOf(lika);
        vich.isParentOf(vila);
        lika.isParentOf(vila);
        vich.isParentOf(chika);
        lika.isParentOf(chika);

        //Aras and Chitra children
        aras.isSpouseOf(chitra);
        aras.isParentOf(jnki);
        chitra.isParentOf(jnki);
        aras.isParentOf(ahit);
        chitra.isParentOf(ahit);

        //Satya and Vyan Children
        satya.isSpouseOf(vyan);
        satya.isParentOf(asva);
        vyan.isParentOf(asva);
        satya.isParentOf(vyas);
        vyan.isParentOf(vyas);
        satya.isParentOf(atya);
        vyan.isParentOf(atya);

        //Jaya and Dritha Children
        jaya.isSpouseOf(dritha);
        jaya.isParentOf(yodhan);
        dritha.isParentOf(yodhan);

        //Arit and Jnki Children
        arit.isSpouseOf(jnki);
        arit.isParentOf(laki);
        jnki.isParentOf(laki);
        arit.isParentOf(lavnya);
        jnki.isParentOf(lavnya);

        //Vyas ans Krpi Children
        krpi.isSpouseOf(vyas);
        vyas.isSpouseOf(asva);
        krpi.isParentOf(kriya);
        vyas.isParentOf(kriya);
        krpi.isParentOf(krithi);
        vyas.isParentOf(krithi);

        //Satvy and Asva Children
        satvy.isSpouseOf(asva);
        satvy.isParentOf(vasa);
        asva.isParentOf(vasa);

    }
}