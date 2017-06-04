package com.classe.adapter;
import com.classe.metier.Contact;
import com.exemple.sqlitedao.R; // R n'estpas dans le même package que ContactAdapter.java
import com.exemple.sqlitedao.data.C;
import com.exemple.sqlitedao.data.DAODB;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class ContactAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	private ArrayList<Contact> contacts;
	private DAODB dba;
	public ContactAdapter(Context context, DAODB dba) { //2 paramètres : le contexte et l'accès à la base de données
		this.dba = dba;
		this.inflater = LayoutInflater.from(context);
		this.contacts = new ArrayList<Contact>();
		getdata();
	}
	//@SuppressWarnings("deprecation")
	public void getdata(){ //permet de remplir une liste ArrayList avec des objets de type Contact à partir des données de la table "contacts"
		Cursor c = dba.getTousContacts(); //on récupère tous les contacts dans un curseur (voir classe ADODB)
        this.startManagingCursor(c);
        if(c.moveToFirst()){ //on teste si le curseur contient un enregistrement
        	do{
        		
        		int id = c.getInt(c.getColumnIndex(C.KEY_ID)); //extraction du curseur de la valeur de id
        		String nom = c.getString(c.getColumnIndex(C.NOM)); //extraction du curseur de la valeur de nom
        		String telephone = c.getString(c.getColumnIndex(C.TELEPHONE)); //extraction du curseur de la valeur de telephone
        		Contact objContact = new Contact(id,nom,telephone); // création de l'objet objContact
        		contacts.add(objContact);
        	}
        	while(c.moveToNext()); //on poursuit tant que le curseur n'a pas été complétement parcouru
        	
        }
		
	}
	private void startManagingCursor(Cursor c) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return contacts.size();  //renvoie le nombre d’éléments contenus dans la collection de données.
	}

	@Override
	public Contact getItem(int i) {
		// TODO Auto-generated method stub
		return contacts.get(i); //retourne l’élément contenu à l’index-position donné
	}

	@Override
	public long getItemId(int i) {
		// TODO Auto-generated method stub
		return i; //renvoie l'index-position
	}

	@Override
	//permet d'identifier les vues qui seront alimentées par les données
	public View getView(int arg0, View arg1, ViewGroup arg2) { 
		final ViewHolder holder;
		View v = arg1;
		if ((v == null) || (v.getTag() == null)) {
				v = inflater.inflate(R.layout.ligne, null); //la méthode inflate permet de charger le fichier XML
				holder = new ViewHolder();
				holder.id = (TextView)v.findViewById(R.id.id_contact);
				holder.nom = (TextView)v.findViewById(R.id.nom);
				holder.telephone = (TextView)v.findViewById(R.id.telephone	);		
		} 
		else 
		{
			holder = (ViewHolder) v.getTag();
		}  

		holder.contact = getItem(arg0); //récupère l'élément de la position arg0
		//on peuple les différens TextView
		holder.id.setText("Contact N° " + String.valueOf(holder.contact.getID())); //int est converti en chaîne
		holder.nom.setText("Nom : " + holder.contact.getNom());
		holder.telephone.setText("Téléphone : " + holder.contact.getTelephone());

		v.setTag(holder);

		// TODO Auto-generated method stub
		return v;
	}
	public class ViewHolder { // création d'une structure de données
		public Contact contact;
		public TextView id;
		public TextView nom;
		public TextView telephone;
	}
	
}
