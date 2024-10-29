package ma.projet;

import java.text.SimpleDateFormat;
import java.util.List;

import ma.projet.classes.Employe;
import ma.projet.classes.EmployeTache;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.service.EmployeService;
import ma.projet.service.EmployeTacheService;
import ma.projet.service.ProjetService;
import ma.projet.service.TacheService;

public class Main {
    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Initialisation des services
            EmployeService employeService = new EmployeService();
            ProjetService projetService = new ProjetService();
            TacheService tacheService = new TacheService();
            EmployeTacheService employeTacheService = new EmployeTacheService();

            // Création d'employés
            Employe employe1 = new Employe("Dupont", "Jean", "0612345678");
            Employe employe2 = new Employe("Martin", "Louis", "0612345679");
            employeService.create(employe1);
            employeService.create(employe2);

            // Création de projets
            Projet projet1 = new Projet("Projet Alpha", sdf.parse("2024-01-01"), sdf.parse("2024-12-31"), employe1);
            Projet projet2 = new Projet("Projet Beta", sdf.parse("2024-06-01"), sdf.parse("2024-09-30"), employe2);
            projetService.create(projet1);
            projetService.create(projet2);

            // Création de tâches
            Tache tache1 = new Tache("Analyse", sdf.parse("2024-01-10"), sdf.parse("2024-01-20"), 1000.0, projet1);
            Tache tache2 = new Tache("Développement", sdf.parse("2024-02-01"), sdf.parse("2024-06-01"), 5000.0, projet1);
            Tache tache3 = new Tache("Test", sdf.parse("2024-07-01"), sdf.parse("2024-08-01"), 2000.0, projet2);
            tacheService.create(tache1);
            tacheService.create(tache2);
            tacheService.create(tache3);

            // Associer des tâches à des employés
            EmployeTache employeTache1 = new EmployeTache(employe1, tache1, sdf.parse("2024-01-10"), sdf.parse("2024-01-18"));
            EmployeTache employeTache2 = new EmployeTache(employe2, tache2, sdf.parse("2024-02-01"), sdf.parse("2024-05-15"));
            employeTacheService.create(employeTache1);
            employeTacheService.create(employeTache2);

            // Afficher la liste des tâches réalisées par un employé
            System.out.println("Tâches réalisées par " + employe1.getNom() + " :");
            List<Tache> tachesParEmploye = employeService.getTachesParEmploye(employe1.getId());
            for (Tache t : tachesParEmploye) {
                System.out.println("- " + t.getNom());
            }

            // Afficher la liste des projets gérés par un employé
            System.out.println("\nProjets gérés par " + employe1.getNom() + " :");
            List<Projet> projetsParEmploye = employeService.getProjetsParEmploye(employe1.getId());
            for (Projet p : projetsParEmploye) {
                System.out.println("- " + p.getNom());
            }

            // Afficher la liste des tâches planifiées pour un projet
            System.out.println("\nTâches planifiées pour le " + projet1.getNom() + " :");
            List<Tache> tachesParProjet = projetService.getTachesParProjet(projet1.getId());
            for (Tache t : tachesParProjet) {
                System.out.println("- " + t.getNom() + " (Prix : " + t.getPrix() + ")");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
