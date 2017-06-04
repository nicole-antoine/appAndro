package com.exemple.sqlitedao.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GestionDBHelper extends SQLiteOpenHelper {

	// Chaine creation Table Contact
	private static final String CREATE_TABLE = "create table " + C.TABLE_CONTACTS 
			+ " (" + 	C.KEY_ID + " integer primary key autoincrement, " + C.NOM + " text not null, " + C.TELEPHONE + " text not null);" ;
	
	// Chaine creation Table MaterielIntervention
		private static final String CREATE_TABLE_MATERIELINTERVENTION = "create table " + C.TABLE_MATERIELINTERVENTION 
				+ " (" 
				+ C.ID_INTERVENTION + " integer, " 
				+ C.NOM_COM + " text not null, " 
				+ C.ETAT + " text not null, " 
				+ C.CONSEIL + " text not null, " 
				+ C.NUM_MATERIEL + " integer not null, "
				+ "PRIMARY KEY (" + C.ID_INTERVENTION + ", " + C.NUM_MATERIEL + "));" ;
		 
	
	// Chaine creation Table Intervention
	private static final String CREATE_TABLE_INTERVENTION = "create table " + C.TABLE_INTERVENTION 
			+ " (" 
			+ C.KEY_ID_INTERVENTION + " integer primary key autoincrement, " 
			+ C.DATE_INTERVENTION + " text not null, " 
			+ C.COMMENTAIRE + " text not null, " 
			+ C.ID_DEMANDEUR + " int not null"
			+ ");" ;
	
	// Chaine creation Table ContratLocation
	private static final String CREATE_TABLE_CONTRATLOCATION = "create table " 
	+ C.TABLE_CONTRATLOCATION + " (" + C.KEY_ID_CONTRATLOCATION + " integer primary key autoincrement, " 
    + C.ID_SOCIETE + " integer not null, " + C.TICKETGRATUIT + " integer not null," + C.NBTICKETS + " integer not null, " 
	+ C.DATEDEBUTCONTRAT + " text not null, " + C.PTTTC + " real not null, " + C.PTTVA + " real not null, " + C.PTHT 
	+ " real not null, " + C.DEPOTGARANTIE + " real not null);" ;                                       
			
	public GestionDBHelper(	Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	//Creation de la table contacts
	@Override
	public void onCreate(final SQLiteDatabase db) {
		Log.i("GestionDBHelpe.onCreate","Creation de toutes les tables");
		try {
			db.execSQL(CREATE_TABLE);
			db.execSQL("INSERT INTO " + C.TABLE_CONTACTS +  "(" + C.NOM + ","+ C.TELEPHONE + ") VALUES('Antoine', '0466295118');");
			db.execSQL("INSERT INTO " + C.TABLE_CONTACTS +  "(" + C.NOM + ","+ C.TELEPHONE + ") VALUES('Louis', '0662818959');");
			db.execSQL("INSERT INTO " + C.TABLE_CONTACTS +  "(" + C.NOM + ","+ C.TELEPHONE + ") VALUES('Corentin', '0853267895');");
			
			db.execSQL(CREATE_TABLE_INTERVENTION);
			/*db.execSQL("INSERT INTO " 
					+ C.TABLE_INTERVENTION +  "(" + C.DATE_INTERVENTION + "," + C.COMMENTAIRE + "," + C.ID_DEMANDEUR + ")"
					+ " VALUES('2014-05-05', 'Aucune', 501);");
			db.execSQL("INSERT INTO " 
					+ C.TABLE_INTERVENTION +  "(" + C.DATE_INTERVENTION + "," + C.COMMENTAIRE + "," + C.ID_DEMANDEUR + ")"
					+ " VALUES('2014-06-06', 'Aucun', 502);");*/
			
			db.execSQL(CREATE_TABLE_CONTRATLOCATION);
			/*db.execSQL("INSERT INTO " + C.TABLE_CONTRATLOCATION +  "(" + C.ID_SOCIETE + "," + C.TICKETGRATUIT + "," + C.NBTICKETS + "," 
			+ C.DATEDEBUTCONTRAT + "," + C.PTTTC + "," + C.PTTVA + "," + C.PTHT + "," + C.DEPOTGARANTIE + ") "
			+ "VALUES(1, 200, 200, '2014-05-05', 200, 300, 501, 501);");*/
			
			db.execSQL(CREATE_TABLE_MATERIELINTERVENTION);
			/*db.execSQL("INSERT INTO " 
					+ C.TABLE_MATERIELINTERVENTION +  "(" + C.ID_INTERVENTION + "," + C.NOM_COM + "," + C.ETAT + "," + C.CONSEIL + "," + C.NUM_MATERIEL + ")"
					+ " VALUES(2014, 'Srousi Razer Naga', 'Aucun', 'Faire Attention', 502);");*/
		}
			catch(SQLiteException ex){
				Log.i("GestionDBHelpe.onCreate",ex.getMessage());
			}
		}  
	
	// Mise a niveau de la base de donnees si nouvelle version
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Log.w("GestionDBHelpe.onUpgrade", "Mise � niveau de la version "+ oldVersion +" vers "+ newVersion +", avec suppression des anciennes donn�es");
		db.execSQL("DROP TABLE IF EXISTS "+ C.TABLE_CONTACTS);
		db.execSQL("DROP TABLE IF EXISTS "+ C.TABLE_INTERVENTION);
		db.execSQL("DROP TABLE IF EXISTS "+ C.TABLE_CONTRATLOCATION);
		db.execSQL("DROP TABLE IF EXISTS "+ C.TABLE_MATERIELINTERVENTION);
		onCreate(db);
	}
}
