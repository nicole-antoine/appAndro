package com.classe.metier;

public class Contact {
    
    // Variables privées
    private int _id;
    private String _nom;
    private String _telephone;
     
    // Constructeur par défaut
    public Contact(){}
    
    // Constructeur ordinaire avec id
    public Contact(int id, String nom, String telephone){
        this._id = id;
        this._nom = nom;
        this._telephone = telephone;
    }
     
    // Constructeur pour l'ajout dans la base de données sans id
    public Contact(String nom, String telephone){
        this._nom = nom;
        this._telephone = telephone;
    }
    // Accesseur ID
    public int getID(){
        return this._id;
    }
     
    // Mutateur id
    public void setID(int id){
        this._id = id;
    }
     
    // Accesseur nom
    public String getNom(){
        return this._nom;
    }
     
    // Mutateur nom
    public void setNom(String nom){
        this._nom = nom;
    }
     
    // Accesseur telephone
    public String getTelephone(){
        return this._telephone;
    }
     
    // Mutateur telephone
    public void setTelephone(String telephone){
        this._telephone = telephone;
    }
}

