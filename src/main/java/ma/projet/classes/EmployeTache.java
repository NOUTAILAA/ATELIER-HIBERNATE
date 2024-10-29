package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;

@Entity
public class EmployeTache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dateDebutReelle;
    private Date dateFinReelle;

    @ManyToOne
    private Employe employe;

    @ManyToOne
    private Tache tache;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateDebutReelle() {
		return dateDebutReelle;
	}

	public void setDateDebutReelle(Date dateDebutReelle) {
		this.dateDebutReelle = dateDebutReelle;
	}

	public Date getDateFinReelle() {
		return dateFinReelle;
	}

	public void setDateFinReelle(Date dateFinReelle) {
		this.dateFinReelle = dateFinReelle;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

	public EmployeTache(int id, Date dateDebutReelle, Date dateFinReelle, Employe employe, Tache tache) {
		super();
		this.id = id;
		this.dateDebutReelle = dateDebutReelle;
		this.dateFinReelle = dateFinReelle;
		this.employe = employe;
		this.tache = tache;
	}

	public EmployeTache(Employe employe, Tache tache ,Date dateDebutReelle, Date dateFinReelle) {
		super();
		this.employe = employe;
		this.tache = tache;
		this.dateDebutReelle = dateDebutReelle;
		this.dateFinReelle = dateFinReelle;
		
	}
	public EmployeTache() {}

    // Getters, setters et constructeurs
}
