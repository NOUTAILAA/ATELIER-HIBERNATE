package ma.projet.classes;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private Date dateDebut;
    private Date dateFin;

    @ManyToOne
    private Employe chefDeProjet;

    @OneToMany(mappedBy = "projet")
    private List<Tache> taches;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Employe getChefDeProjet() {
		return chefDeProjet;
	}

	public void setChefDeProjet(Employe chefDeProjet) {
		this.chefDeProjet = chefDeProjet;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

	public Projet(int id, String nom, Date dateDebut, Date dateFin, Employe chefDeProjet, List<Tache> taches) {
		super();
		this.id = id;
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.chefDeProjet = chefDeProjet;
		this.taches = taches;
	}

	public Projet(String nom, Date dateDebut, Date dateFin, Employe chefDeProjet) {
		super();
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.chefDeProjet = chefDeProjet;
	}
public Projet() {}
    // Getters, setters et constructeurs
}
