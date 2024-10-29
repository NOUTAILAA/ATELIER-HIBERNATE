package ma.projet;

import ma.projet.beans.Homme;
import ma.projet.beans.Femme;
import ma.projet.beans.Mariage;
import ma.projet.service.HommeService;
import ma.projet.service.FemmeService;
import ma.projet.service.MariageService;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Initialisation des services
            HommeService hommeService = new HommeService();
            FemmeService femmeService = new FemmeService();
            MariageService mariageService = new MariageService();

            // Création de 5 hommes et 10 femmes
            Homme homme1 = new Homme("Dupont", "Jean", "0612345678", "123 Rue de Paris", sdf.parse("1980-01-01"));
            Homme homme2 = new Homme("Martin", "Louis", "0612345679", "456 Rue de Lyon", sdf.parse("1983-02-01"));
            Homme homme3 = new Homme("Durand", "Pierre", "0612345680", "789 Rue de Marseille", sdf.parse("1975-05-10"));
            Homme homme4 = new Homme("Petit", "Alain", "0612345681", "321 Rue de Lille", sdf.parse("1978-07-15"));
            Homme homme5 = new Homme("Leroy", "Marc", "0612345682", "654 Rue de Bordeaux", sdf.parse("1985-03-20"));
            hommeService.create(homme1);
            hommeService.create(homme2);
            hommeService.create(homme3);
            hommeService.create(homme4);
            hommeService.create(homme5);

            Femme femme1 = new Femme("Martin", "Sophie", "0612345683", "456 Rue de Lyon", sdf.parse("1985-01-01"));
            Femme femme2 = new Femme("Durand", "Marie", "0612345684", "789 Rue de Marseille", sdf.parse("1990-01-01"));
            Femme femme3 = new Femme("Lefevre", "Isabelle", "0612345685", "987 Rue de Nantes", sdf.parse("1982-04-22"));
            Femme femme4 = new Femme("Moreau", "Christine", "0612345686", "321 Rue de Lille", sdf.parse("1978-09-14"));
            Femme femme5 = new Femme("Simon", "Claire", "0612345687", "111 Rue de Nice", sdf.parse("1986-08-30"));
            Femme femme6 = new Femme("Michel", "Emilie", "0612345688", "222 Rue de Cannes", sdf.parse("1987-02-15"));
            Femme femme7 = new Femme("Thomas", "Lucie", "0612345689", "333 Rue de Toulon", sdf.parse("1989-12-05"));
            Femme femme8 = new Femme("Robert", "Anne", "0612345690", "444 Rue de Paris", sdf.parse("1983-06-11"));
            Femme femme9 = new Femme("Petit", "Julie", "0612345691", "555 Rue de Dijon", sdf.parse("1991-10-19"));
            Femme femme10 = new Femme("Durant", "Alice", "0612345692", "666 Rue de Lyon", sdf.parse("1981-03-25"));
            femmeService.create(femme1);
            femmeService.create(femme2);
            femmeService.create(femme3);
            femmeService.create(femme4);
            femmeService.create(femme5);
            femmeService.create(femme6);
            femmeService.create(femme7);
            femmeService.create(femme8);
            femmeService.create(femme9);
            femmeService.create(femme10);

            // Création et enregistrement de mariages (exemple pour quelques hommes et femmes)
            Mariage mariage1 = new Mariage(sdf.parse("2005-01-01"), null, 2, homme1, femme1);
            Mariage mariage2 = new Mariage(sdf.parse("2012-01-01"), sdf.parse("2017-01-01"), 1, homme1, femme2);
            mariageService.create(mariage1);
            mariageService.create(mariage2);

            // Afficher la liste des femmes
            List<Femme> femmes = femmeService.getAll();
            System.out.println("Liste des femmes:");
            for (Femme femme : femmes) {
                System.out.println("- " + femme.getNom() + " " + femme.getPrenom());
            }

            // Afficher la femme la plus âgée
            Femme femmePlusAgee = femmes.stream().min(Comparator.comparing(Femme::getDateNaissance)).orElse(null);
            if (femmePlusAgee != null) {
                System.out.println("Femme la plus âgée : " + femmePlusAgee.getNom() + " " + femmePlusAgee.getPrenom());
            }

            // Afficher les épouses de l'homme1 entre deux dates
            Date dateDebut = sdf.parse("2004-01-01");
            Date dateFin = sdf.parse("2018-01-01");
            List<Femme> epouses = hommeService.getEpousesParDate(homme1.getId(), dateDebut, dateFin);
            System.out.println("\nÉpouses de " + homme1.getPrenom() + " " + homme1.getNom() + " entre " + sdf.format(dateDebut) + " et " + sdf.format(dateFin) + " :");
            for (Femme epouse : epouses) {
                System.out.println("- " + epouse.getPrenom() + " " + epouse.getNom());
            }

            // Afficher le nombre d'enfants de femme1 entre deux dates
            int nombreEnfants = femmeService.getNombreEnfantsEntreDeuxDates(femme1.getId(), dateDebut, dateFin);
            System.out.println("\nNombre d'enfants de " + femme1.getNom() + " entre " + sdf.format(dateDebut) + " et " + sdf.format(dateFin) + " : " + nombreEnfants);

            // Afficher les femmes mariées deux fois ou plus
            List<Femme> femmesMarieesDeuxFois = femmeService.getFemmesMarieesDeuxFoisOuPlus();
            System.out.println("\nFemmes mariées deux fois ou plus :");
            for (Femme femme : femmesMarieesDeuxFois) {
                System.out.println("- " + femme.getNom() + " " + femme.getPrenom());
            }

            // Afficher les hommes mariés à quatre femmes entre deux dates
            int nombreHommesMarieesQuatreFois = hommeService.getNombreHommesMarieesQuatreFois(dateDebut, dateFin);
            System.out.println("\nNombre d'hommes mariés à quatre femmes entre " + sdf.format(dateDebut) + " et " + sdf.format(dateFin) + " : " + nombreHommesMarieesQuatreFois);

            // Afficher les mariages d'un homme donné avec les détails
            hommeService.afficherDetailsMariages(homme1.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
