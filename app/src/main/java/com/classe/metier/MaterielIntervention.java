package com.classe.metier;

public class MaterielIntervention {
	
	// Variables privées
    private int _idMaterielIntervention;
    private String _nomMateriel;
    private String _etatIntervention;
    private String _conseil;
    private int _numMat;
    
    /**
	 * @param _idMaterielIntervention
	 * @param _etatIntervention
	 * @param _nomMateriel
	 */
	public MaterielIntervention(int _idMaterielIntervention, String _nomMateriel, String _etatIntervention, String _conseil, int _numMat) {
		this._idMaterielIntervention = _idMaterielIntervention;
		this._etatIntervention = _etatIntervention;
		this._nomMateriel = _nomMateriel;
		this.set_conseil(_conseil);
		this._numMat = _numMat;
	}
	/**
	 * @return the _nomMateriel
	 */
	public String get_nomMateriel() {
		return _nomMateriel;
	}
	/**
	 * @param _nomMateriel the _nomMateriel to set
	 */
	public void set_nomMateriel(String _nomMateriel) {
		this._nomMateriel = _nomMateriel;
	}
	/**
	 * @return the _etatIntervention
	 */
	public String get_etatIntervention() {
		return _etatIntervention;
	}
	/**
	 * @param _etatIntervention the _etatIntervention to set
	 */
	public void set_etatIntervention(String _etatIntervention) {
		this._etatIntervention = _etatIntervention;
	}
	/**
	 * @return the _idMaterielIntervention
	 */
	public int get_idMaterielIntervention() {
		return _idMaterielIntervention;
	}
	/**
	 * @param _idMaterielIntervention the _idMaterielIntervention to set
	 */
	public void set_idMaterielIntervention(int _idMaterielIntervention) {
		this._idMaterielIntervention = _idMaterielIntervention;
	}
	/**
	 * @return the _numMat
	 */
	public int get_numMat() {
		return _numMat;
	}
	/**
	 * @param _numMat the _numMat to set
	 */
	public void set_numMat(int _numMat) {
		this._numMat = _numMat;
	}
	public String get_conseil() {
		return _conseil;
	}
	public void set_conseil(String _conseil) {
		this._conseil = _conseil;
	}
    

}
