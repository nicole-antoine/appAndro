package com.classe.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.classe.metier.Intervention;
import com.exemple.sqlitedao.R;
import com.exemple.sqlitedao.data.C;
import com.exemple.sqlitedao.data.DAODB;

public class InterventionAdapter extends BaseAdapter{
	
	private LayoutInflater inflater;
	private ArrayList<Intervention> interventions;
	private DAODB dba;
	
	public InterventionAdapter(Context context, DAODB dba) { //2 param�tres : le contexte et l'acc�s � la base de donn�es
		this.dba = dba;
		this.inflater = LayoutInflater.from(context);
		this.interventions = new ArrayList<Intervention>();
		getdata();
	}
	//@SuppressWarnings("deprecation")
	public void getdata(){ //permet de remplir une liste ArrayList avec des objets de type intervention
		Cursor c = dba.getTousIntervention(); //on r�cup�re tous les contacts dans un curseur (voir classe ADODB)
        this.startManagingCursor(c);
        if(c.moveToFirst()){ //on teste si le curseur contient un enregistrement
        	do{
        		
        		int idInterv = c.getInt(c.getColumnIndex(C.KEY_ID_INTERVENTION)); //extraction du curseur de la valeur de id
        		String dateCrea = c.getString(c.getColumnIndex(C.DATE_INTERVENTION)); //extraction du curseur de la valeur de nom
        		String commentaire = c.getString(c.getColumnIndex(C.COMMENTAIRE)); //extraction du curseur de la valeur de telephone
        		int idIntervenant = c.getInt(c.getColumnIndex(C.ID_DEMANDEUR)); //extraction du curseur de la valeur de telephone
        		
        		Intervention interv = new Intervention(idInterv,dateCrea,commentaire,idIntervenant); // cr�ation de l'objet objContact
        		interventions.add(interv);
        	}
        	while(c.moveToNext()); //on poursuit tant que le curseur n'a pas �t� compl�tement parcouru
        	
        }
        //interventions.add(new Intervention(1, "22/07/2004","Aucun", 5));
		
	}
	private void startManagingCursor(Cursor c) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return interventions.size();  //renvoie le nombre d��l�ments contenus dans la collection de donn�es.
	}

	@Override
	public Intervention getItem(int i) {
		// TODO Auto-generated method stub
		return interventions.get(i); //retourne l��l�ment contenu � l�index-position donn�
	}

	@Override
	public long getItemId(int i) {
		// TODO Auto-generated method stub
		return interventions.get(i).get_idIntervention(); //renvoie l'index-position
	}

	@Override
	//permet d'identifier les vues qui seront aliment�es par les donn�es
	public View getView(int arg0, View arg1, ViewGroup arg2) { 
		final ViewHolder holder;
		View v = arg1;
		if ((v == null) || (v.getTag() == null)) {
				v = inflater.inflate(R.layout.lignes_intervention, null); //la m�thode inflate permet de charger le fichier XML
				holder = new ViewHolder();
				holder.idInterv = (TextView)v.findViewById(R.id.id_intervention);
				holder.dateInterv = (TextView)v.findViewById(R.id.date_interv);
				holder.commentaire = (TextView)v.findViewById(R.id.commentaire);	
				holder.idIntervenant = (TextView)v.findViewById(R.id.id_intervenant);	
		} 
		else 
		{
			holder = (ViewHolder) v.getTag();
		}  

		holder.interv = getItem(arg0); 
		//r�cup�re l'�l�ment de la position arg0
		
		//on peuple les diff�rens TextView
		holder.idInterv.setText("Intervention N� " + holder.interv.get_idIntervention()); 
		holder.dateInterv.setText("Intervention demand�e le " + holder.interv.get_dateDemandeIntervention());
		holder.commentaire.setText("Commentaire : " + holder.interv.get_commentaire()); 
		holder.idIntervenant.setText("Demandeur N� " + holder.interv.get_idIntervenant()); 
	

		v.setTag(holder);

		// TODO Auto-generated method stub
		return v;
	}
	
	public class ViewHolder { 
		// cr�ation d'une structure de donn�es
		public Intervention interv;
		public TextView idInterv;
		public TextView dateInterv;
		public TextView commentaire;
		public TextView idIntervenant;
	}
	
}