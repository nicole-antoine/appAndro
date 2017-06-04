package com.classe.metier;

public class ContratLocation {
	   
    // Variables privées
    private int _idContratLocation;
    private int _idSociete;
    private int _ticketGratuit;
    private int _nbTickets;
    private String _dateDebContrat;
    private float _ptttc;
    private float _pttva;
    private float _ptht;
    private float _depotGarantie;
     
    // Constructeur par défaut
    public ContratLocation(){}
    // Constructeur ordinaire avec id
    public ContratLocation(int idC, int idS, int tickGrat, int nbTick, String dateDeb, float ptttc, float pttva, float ptht, float deportGar){
        this._idContratLocation = idC;
        this._idSociete = idS;
        this._ticketGratuit = tickGrat;
        this._nbTickets = nbTick;
        this._dateDebContrat = dateDeb;
        this._ptttc = ptttc;
        this._pttva = pttva;
        this._ptht = ptht;
        this._depotGarantie = deportGar;
    }
     
    // Constructeur pour l'ajout dans la base de données sans id
    public ContratLocation(int idS, int tickGrat, int nbTick, String dateDeb, float ptttc, float pttva, float ptht, float deportGar){
        this._idSociete = idS;
        this._ticketGratuit = tickGrat;
        this._nbTickets = nbTick;
        this._dateDebContrat = dateDeb;
        this._ptttc = ptttc;
        this._pttva = pttva;
        this._ptht = ptht;
        this._depotGarantie = deportGar;
    }
   // ACCESSEURS
   public int getIdContratLocation(){return this._idContratLocation;}
   public int getIdSociete(){return this._idSociete;}
   public int getTicketGratuit(){return this._ticketGratuit; }
   public int getNbTickets(){return this._nbTickets;}
   public String getDateDeb(){return this._dateDebContrat;}
   public float getPTTTC(){return this._ptttc;}
   public float getPTTVA(){return this._pttva;}
   public float getPTHT(){return this._ptht; }
   public float getDepotGarantie(){return this._depotGarantie; }
   
   // MUTATEURS
   public void setIdContratLocation(int idC){this._idContratLocation = idC;}
   public void setIdSociete(int idS){this._idSociete = idS;}
   public void setTicketGratuit(int tickGrat){this._ticketGratuit = tickGrat;}
   public void setNbTickets(int nbTick){this._nbTickets = nbTick;}
   public void setDateDeb(String dateDeb){this._dateDebContrat = dateDeb;}
   public void setPTTTC(int ptttc){this._ptttc = ptttc;}
   public void setPTTVA(int pttva){this._pttva = pttva;}
   public void setPTHT(int ptht){this._ptht = ptht;}
   public void setDepotGarantie(int deportGar){this._depotGarantie = deportGar;}
   
   public String toString(){
	   return "IDC: " + this.getIdContratLocation() + ", " + "IDS: " + this.getIdSociete() + ", " + " TickGrat: " + this.getTicketGratuit() + ", " 
			   + " NbTick: " + this.getNbTickets() + ", " + " DateDeb: " + this.getDateDeb() + ", " + " PTTTC: " + this.getPTTTC() + ", " 
			   + " PTTVA: " + this.getPTTVA() + ", " + " PTHT:" + this.getPTHT() + ", " + "DepotGar: " + this.getDepotGarantie();
   }
}

