package ma.projet.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mariage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dateDebut;
    private Date dateFin;
    private int nbrEnfant;

    @ManyToOne
    private Homme homme;

    @ManyToOne
    private Femme femme;

    // Constructeurs
    public Mariage() {}

    public Mariage(Date dateDebut, Date dateFin, int nbrEnfant, Homme homme, Femme femme) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrEnfant = nbrEnfant;
        this.homme = homme;
        this.femme = femme;
    }

	public Mariage(int id, Date dateDebut, Date dateFin, int nbrEnfant, Homme homme, Femme femme) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbrEnfant = nbrEnfant;
		this.homme = homme;
		this.femme = femme;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getNbrEnfant() {
		return nbrEnfant;
	}

	public void setNbrEnfant(int nbrEnfant) {
		this.nbrEnfant = nbrEnfant;
	}

	public Homme getHomme() {
		return homme;
	}

	public void setHomme(Homme homme) {
		this.homme = homme;
	}

	public Femme getFemme() {
		return femme;
	}

	public void setFemme(Femme femme) {
		this.femme = femme;
	}

    // Getters et Setters ici
}
