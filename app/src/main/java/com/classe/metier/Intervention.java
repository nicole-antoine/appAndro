package com.classe.metier;

public class Intervention {

	// Variables privées
    private int _idIntervention;
    private String _dateDemandeIntervention;
    private String _commentaire;
    private int _idIntervenant;
	
	/**
	 * @param _idIntervention
	 * @param _dateDemandeIntervention
	 * @param _commentaire
	 * @param _idIntervenant
	 */
	public Intervention(int _idIntervention, String _dateDemandeIntervention,
			String _commentaire, int _idIntervenant) {
		this._idIntervention = _idIntervention;
		this._dateDemandeIntervention = _dateDemandeIntervention;
		this._commentaire = _commentaire;
		this._idIntervenant = _idIntervenant;
	}
	public Intervention(){}
	/**
	 * @return the _idIntervention
	 */
	public int get_idIntervention() {
		return _idIntervention;
	}
	/**
	 * @param _idIntervention the _idIntervention to set
	 */
	public void set_idIntervention(int _idIntervention) {
		this._idIntervention = _idIntervention;
	}
	/**
	 * @return the _dateDemandeIntervention
	 */
	public String get_dateDemandeIntervention() {
		return _dateDemandeIntervention;
	}
	/**
	 * @param _dateDemandeIntervention the _dateDemandeIntervention to set
	 */
	public void set_dateDemandeIntervention(String _dateDemandeIntervention) {
		this._dateDemandeIntervention = _dateDemandeIntervention;
	}
	/**
	 * @return the _commentaire
	 */
	public String get_commentaire() {
		return _commentaire;
	}
	/**
	 * @param _commentaire the _commentaire to set
	 */
	public void set_commentaire(String _commentaire) {
		this._commentaire = _commentaire;
	}
	/**
	 * @return the _idIntervenant
	 */
	public int get_idIntervenant() {
		return _idIntervenant;
	}

	/**
	 * @param _idIntervenant the _idIntervenant to set
	 */
	public void set_idIntervenant(int _idIntervenant) {
		this._idIntervenant = _idIntervenant;
	}
}
