package com.exemple.sqlitedao.data;

public class C {
	public static final String DATABASE_NAME="contactsBD";
	public static final int DATABASE_VERSION=9;
	
	// Table et champs de contacts
	public static final String TABLE_CONTACTS="contacts";
	public static final String KEY_ID="_id";
	public static final String NOM="nom";
	public static final String TELEPHONE="telephone";

	// Table et champs de contralocation
	public static final String TABLE_CONTRATLOCATION="contratlocation";
	public static final String KEY_ID_CONTRATLOCATION="_idContratLocation";
	public static final String ID_SOCIETE="_idSociete";
	public static final String TICKETGRATUIT="ticketgratuit";
	public static final String NBTICKETS="nbtickets";
	public static final String DATEDEBUTCONTRAT="datedebutcontrat";
	public static final String PTTTC="ptttc";
	public static final String PTTVA="pttva";
	public static final String PTHT="ptht";
	public static final String DEPOTGARANTIE="depotgarantie";
	
	// Champs d'une table concernant les interventions 
	public static final String TABLE_INTERVENTION="intervention";
	public static final String KEY_ID_INTERVENTION="_idIntervention";
	public static final String DATE_INTERVENTION="datecreationintervention";
	public static final String COMMENTAIRE="commentaire";
	public static final String ID_DEMANDEUR="_idDemandeur";
	
	public static final String TABLE_MATERIELINTERVENTION="materiel_intervention";
	public static final String ID_INTERVENTION="_idMaterielIntervention";
	public static final String NOM_COM="nommateriel";
	public static final String ETAT="etat";
	public static final String CONSEIL="conseil";
	public static final String NUM_MATERIEL="nummateriel";
	
}

