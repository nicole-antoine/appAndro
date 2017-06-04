package com.exemple.sqlitedao.data;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.classe.metier.Contact;
import com.classe.metier.ContratLocation;
import com.classe.metier.Intervention;
import com.classe.metier.MaterielIntervention;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DAODB {
	private SQLiteDatabase db;
	private final Context context;
	private final GestionDBHelper dbHelper;
	
	public DAODB(Context c) {
		this.context = c;
		this.dbHelper = new GestionDBHelper(this.context, C.DATABASE_NAME,
				null, C.DATABASE_VERSION);
	}

	public void close() {
		db.close();
	}

	public void open() throws SQLiteException {
		try {
			db = dbHelper.getWritableDatabase();
		} catch (SQLiteException ex) {
			Log.v("DataBase.open", ex.getMessage());
			db = dbHelper.getReadableDatabase();
		}
	}

	// Methodes CRUD

	// ******************************************************* CONTACTS ******************
	
	// Ajout d'un nouveau contact
	public long insererContact(String nom, String telephone) {
		try {

			ContentValues values = new ContentValues();
			values.put(C.NOM, nom);
			values.put(C.TELEPHONE, telephone);

			return db.insert(C.TABLE_CONTACTS, null, values);

		} catch (SQLiteException ex) {
			Log.v("DataBase.insererContact", ex.getMessage());
			return -1;
		}
	}

	// Lire tous les contacts
	public Cursor getTousContacts() {
		Cursor c = db.query(C.TABLE_CONTACTS, null, null, null, null, null,
				null);
		return c;
	}

	// Lecture d'un contact donn� par son id
	public Contact getUnContact(int id) {

		Contact contact = null;
		Cursor c = db.query(C.TABLE_CONTACTS, new String[] { C.KEY_ID, C.NOM,
				C.TELEPHONE }, C.KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (c != null) {
			// Comme la clause WHERE s'applique sur l'identifiant, il y aura au
			// plus un enregistrement dans le curseur
			c.moveToFirst();

			contact = new Contact(Integer.parseInt(c.getString(0)),
					c.getString(1), c.getString(2));
		}
		// On renvoie le contact
		c.close();
		db.close();
		return contact; // Attention contact peut-�tre null
	}

	// Lecture de plusieurs contacts commen�ant par premi�re lettre
	public List<Contact> getContactsParDebutNom(String lettre) {
		List<Contact> contactList = new ArrayList<Contact>();
		String selectQuery = "SELECT  * FROM " + C.TABLE_CONTACTS + "WHERE "
				+ C.NOM + "LIKE '" + lettre + "%'";

		Cursor cursor = db.rawQuery(selectQuery, null);

		// On parcourt le jeu d'enregistrement et on les les ajoute dans la
		// liste
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setNom(cursor.getString(1));
				contact.setTelephone(cursor.getString(2));
				// Ajout du contact � la liste
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		// On renvoie la liste des contacts
		return contactList;
	}

	// Modification d'un contact
	public int modifierContact(Contact contact) {
		int count = 0;
		ContentValues values = new ContentValues();
		values.put(C.NOM, contact.getNom());
		values.put(C.TELEPHONE, contact.getTelephone());

		// Modification de l'enregistrement
		count = db.update(C.TABLE_CONTACTS, values, C.KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
		db.close();
		return count;
	}

	// Suppression d'un contact donn�
	public int supprimerUnContact(Contact contact) {
		int count = 0;
		count = db.delete(C.TABLE_CONTACTS, C.KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
		db.close();
		return count;
	}

	// Suppression d'un contact donn� par son id
	public int supprimerUnContact(int id) {
		int count = 0;
		count = db.delete(C.TABLE_CONTACTS, C.KEY_ID + " = ?",
				new String[] { String.valueOf(id) });
		db.close();
		return count;
	}

	// Suppression de tous les contacts
	public void supprimerLesContacts() {
		db.delete(C.TABLE_CONTACTS, null, null);
		db.close();
	}

	// Lecture du nombre de contacts
	public int getContactsNombre() {
		String countQuery = "SELECT  * FROM " + C.TABLE_CONTACTS;
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		db.close();
		// On renvoie le nombre de contacts
		return cursor.getCount();
	}

	// ******************************************************* CONTRATSLOCATION ******************

	// Ajout d'un nouveau contratlocation
		public long insererContratLocation(int idSociete, int ticketGratuit, int nbTickets, String dateDeb, float ptttc, float pttva, float ptht, float depot) {
			try {

				ContentValues values = new ContentValues();
				values.put(C.ID_SOCIETE, idSociete);
				values.put(C.TICKETGRATUIT, ticketGratuit);
				values.put(C.NBTICKETS, nbTickets);
				values.put(C.DATEDEBUTCONTRAT, dateDeb);
				values.put(C.PTTTC, ptttc);
				values.put(C.PTTVA, pttva);
				values.put(C.PTHT, ptht);
				values.put(C.DEPOTGARANTIE, depot);

				return db.insert(C.TABLE_CONTRATLOCATION, null, values);

			} catch (SQLiteException ex) {
				//Log.v("DataBase.insererContact", ex.getMessage());
				return -1;
			}
		}
		
		// Ajout d'un nouveau contratlocation avec ID
				public long insererContratLocation(int idContratLocation, int idSociete, int ticketGratuit, int nbTickets, String dateDeb, float f, float g, float h, float i) {
					try {

						ContentValues values = new ContentValues();
						values.put(C.KEY_ID_CONTRATLOCATION, idContratLocation);
						values.put(C.ID_SOCIETE, idSociete);
						values.put(C.TICKETGRATUIT, ticketGratuit);
						values.put(C.NBTICKETS, nbTickets);
						values.put(C.DATEDEBUTCONTRAT, dateDeb);
						values.put(C.PTTTC, f);
						values.put(C.PTTVA, g);
						values.put(C.PTHT, h);
						values.put(C.DEPOTGARANTIE, i);

						return db.insert(C.TABLE_CONTRATLOCATION, null, values);

					} catch (SQLiteException ex) {
						//Log.v("DataBase.insererContact", ex.getMessage());
						return -1;
					}
				}

		// Lire tous les contratlocation
		public Cursor getTousContratsLocation() {
			Cursor c = db.query(C.TABLE_CONTRATLOCATION, null, null, null, null, null,
					null);
			return c;
		}

		// Lecture d'un contratlocation donn� par son id
		public ContratLocation getUnContratLocation(int idC) throws ParseException {

			ContratLocation contratloc = null;
			Cursor c = db.query(C.TABLE_CONTRATLOCATION, new String[] { 
					C.KEY_ID_CONTRATLOCATION, C.ID_SOCIETE, C.TICKETGRATUIT, 
					C.NBTICKETS, C.DATEDEBUTCONTRAT, C.PTTTC, C.PTTVA, C.PTHT, C.DEPOTGARANTIE }, C.KEY_ID_CONTRATLOCATION + "=?",
					new String[] { String.valueOf(idC) }, null, null, null, null);
			if (c != null) {
				// Comme la clause WHERE s'applique sur l'identifiant, il y aura au
				// plus un enregistrement dans le curseur
				c.moveToFirst();
				
				contratloc = new ContratLocation((c.getInt(0)), c.getInt(1), c.getInt(2), c.getInt(3)
						, c.getString(4), c.getInt(5), c.getInt(6), c.getInt(7), c.getInt(8));
			}
			// On renvoie le contact
			c.close();
			db.close();
			return contratloc; // Attention contratloc peut-�tre null
		}

		// Modification d'un contratlocation
		public int modifierContratLocation(ContratLocation contrat) {
			int count = 0;
			ContentValues values = new ContentValues();
			values.put(C.ID_SOCIETE, contrat.getIdSociete());
			values.put(C.TICKETGRATUIT, contrat.getTicketGratuit());
			values.put(C.NBTICKETS, contrat.getNbTickets());
			values.put(C.DATEDEBUTCONTRAT, contrat.getDateDeb());
			values.put(C.PTTTC, contrat.getPTTTC());
			values.put(C.PTTVA, contrat.getPTTVA());
			values.put(C.PTHT, contrat.getPTHT());
			values.put(C.DEPOTGARANTIE, contrat.getDepotGarantie());

			// Modification de l'enregistrement
			count = db.update(C.TABLE_CONTRATLOCATION, values, C.KEY_ID_CONTRATLOCATION + " = ?",
					new String[] { String.valueOf(contrat.getIdContratLocation()) });
			db.close();
			return count;
		}

		// Suppression d'un contact donn�
		public int supprimerUnContratLocation(ContratLocation contrat) {
			int count = 0;
			count = db.delete(C.TABLE_CONTRATLOCATION, C.KEY_ID_CONTRATLOCATION + " = ?",
					new String[] { String.valueOf(contrat.getIdContratLocation()) });
			db.close();
			return count;
		}

		// Suppression d'un contact donn� par son id
		public int supprimerUnContratLocation(int id) {
			int count = 0;
			count = db.delete(C.TABLE_CONTRATLOCATION, C.KEY_ID_CONTRATLOCATION + " = ?",
					new String[] { String.valueOf(id) });
			db.close();
			return count;
		}

		// Suppression de tous les contacts
		public void supprimerLesContratsLocation() {
			db.delete(C.TABLE_CONTRATLOCATION, null, null);
			db.close();
		}

		// Lecture du nombre de contacts
		public int getContratsLocationNombre() {
			String countQuery = "SELECT  * FROM " + C.TABLE_CONTRATLOCATION;
			Cursor cursor = db.rawQuery(countQuery, null);
			cursor.close();
			db.close();
			// On renvoie le nombre de contacts
			return cursor.getCount();
		}
		
		// ******************************************************* INTERVENTION ******************

		// Lecture de plusieurs interventions li�es � un technicien

		public Cursor getTousIntervention() 
		{
			Cursor c = db.query(C.TABLE_INTERVENTION, null, null, null, null, null, null);
			return c;
		}
		
		// Ajout d'une nouvelle intervention avec ID
		public long insererIntervention(int idIntervention, String dateDemande, String commentaire, int idDemandeur) {
			try {

				ContentValues values = new ContentValues();
				values.put(C.KEY_ID_INTERVENTION, idIntervention);
				values.put(C.DATE_INTERVENTION, dateDemande);
				values.put(C.COMMENTAIRE, commentaire);
				values.put(C.ID_DEMANDEUR, idDemandeur);

				return db.insert(C.TABLE_INTERVENTION, null, values);

			} catch (SQLiteException ex) {
				//Log.v("DataBase.insererContact", ex.getMessage());
				return -1;
			}
		}
		
		// Lecture d'une intervention donn� par son id
				public Intervention getUneIntervention(int idInterv) throws ParseException {

					Intervention intervention = null;
					Cursor c = db.query(C.TABLE_INTERVENTION, new String[] { 
							C.KEY_ID_INTERVENTION, C.DATE_INTERVENTION, C.COMMENTAIRE, 
							C.ID_DEMANDEUR }, C.KEY_ID_INTERVENTION + "=?",
							new String[] { String.valueOf(idInterv) }, null, null, null, null);
					if (c != null) {
						// Comme la clause WHERE s'applique sur l'identifiant, il y aura au
						// plus un enregistrement dans le curseur
						c.moveToFirst();
						
						intervention = new Intervention((c.getInt(0)), c.getString(1), c.getString(2), c.getInt(3));
					}
					// On renvoie l'intervention
					c.close();
					db.close();
					return intervention; // Attention intervention peut-�tre null
				}
		
		// ******************************************************* MATERIELINTERVENTION ******************

				// Lecture de plusieurs interventions li�es � un technicien

				public Cursor getTousMaterielIntervention() 
				{
					Cursor c = db.query(C.TABLE_MATERIELINTERVENTION, null, null, null, null, null, null);
					return c;
				}
				
				// Ajout d'une nouvelle intervention avec ID
				public long insererMaterielIntervention(int idIntervention, String nomCom, String etat, String conseil, int numMat) {
				try {

						ContentValues values = new ContentValues();
						values.put(C.ID_INTERVENTION, idIntervention);
						values.put(C.NOM_COM, nomCom);
						values.put(C.ETAT, etat);
						values.put(C.CONSEIL, conseil);
						values.put(C.NUM_MATERIEL, numMat);

						return db.insert(C.TABLE_MATERIELINTERVENTION, null, values);

					} catch (SQLiteException ex) {
						//Log.v("DataBase.insererContact", ex.getMessage());
						return -1;
					}
				}
				
				// Lecture d'une intervention sur materiel donn� par son id
				public MaterielIntervention getUneInterventionMateriel(int idMatInterv) throws ParseException {

					MaterielIntervention matIntervention = null;
					Cursor c = db.query(C.TABLE_MATERIELINTERVENTION, new String[] { 
							C.ID_INTERVENTION, C.NOM_COM, C.ETAT, C.CONSEIL,
							C.NUM_MATERIEL }, C.NUM_MATERIEL + "=?",
							new String[] { String.valueOf(idMatInterv) }, null, null, null, null);
					if (c != null) {
						// Comme la clause WHERE s'applique sur l'identifiant, il y aura au
						// plus un enregistrement dans le curseur
						c.moveToFirst();
						
						matIntervention = new MaterielIntervention((c.getInt(0)), c.getString(1), c.getString(2), c.getString(3), c.getInt(4));
					}
					// On renvoie l'intervention
					c.close();
					db.close();
					return matIntervention; // Attention intervention peut-�tre null
				}
}
