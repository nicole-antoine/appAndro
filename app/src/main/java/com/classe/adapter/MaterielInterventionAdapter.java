package com.classe.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.classe.metier.MaterielIntervention;
import com.exemple.sqlitedao.R;
import com.exemple.sqlitedao.data.C;
import com.exemple.sqlitedao.data.DAODB;

public class MaterielInterventionAdapter extends BaseAdapter{
	
	private LayoutInflater inflater;
	private ArrayList<MaterielIntervention> materiel_interventions;
	private DAODB dba;
	
	public MaterielInterventionAdapter(Context context, DAODB dba) { //2 paramètres : le contexte et l'accès à la base de données
		this.dba = dba;
		this.inflater = LayoutInflater.from(context);
		this.materiel_interventions = new ArrayList<MaterielIntervention>();
		getdata();
	}
	//@SuppressWarnings("deprecation")
	public void getdata(){ //permet de remplir une liste ArrayList avec des objets de type intervention
		Cursor c = dba.getTousMaterielIntervention(); //on récupère tous les contacts dans un curseur (voir classe ADODB)
        this.startManagingCursor(c);
        if(c.moveToFirst()){ //on teste si le curseur contient un enregistrement
        	do{
        		
        		int idInterv = c.getInt(c.getColumnIndex(C.ID_INTERVENTION)); //extraction du curseur de la valeur de id
        		String nomMat = c.getString(c.getColumnIndex(C.NOM_COM)); //extraction du curseur de la valeur de nom
        		String etat = c.getString(c.getColumnIndex(C.ETAT)); //extraction du curseur de la valeur de telephone
        		String conseil = c.getString(c.getColumnIndex(C.CONSEIL));
        		int numMat = c.getInt(c.getColumnIndex(C.NUM_MATERIEL));
        		
        		MaterielIntervention matInterv = new MaterielIntervention(idInterv,nomMat,etat,conseil,numMat); // création de l'objet objContact
        		materiel_interventions.add(matInterv);
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
		return materiel_interventions.size();  //renvoie le nombre d’éléments contenus dans la collection de données.
	}

	@Override
	public MaterielIntervention getItem(int i) {
		// TODO Auto-generated method stub
		return materiel_interventions.get(i); //retourne l’élément contenu à l’index-position donné
	}

	@Override
	public long getItemId(int i) {
		// TODO Auto-generated method stub
		return materiel_interventions.get(i).get_numMat(); //renvoie l'index-position
	}
	
	public int[] getListId(int i) {
		// TODO Auto-generated method stub
		int[] val = new int[2];
		val[0] = materiel_interventions.get(i).get_numMat();
		val[1] = materiel_interventions.get(i).get_idMaterielIntervention();
		return val; //renvoie l'index-position
	}

	@Override
	//permet d'identifier les vues qui seront alimentées par les données
	public View getView(int arg0, View arg1, ViewGroup arg2) { 
		final ViewHolder holder;
		View v = arg1;
		if ((v == null) || (v.getTag() == null)) {
				v = inflater.inflate(R.layout.ligne_materielintervention, null); //la méthode inflate permet de charger le fichier XML
				holder = new ViewHolder();
				holder.idMatInterv = (TextView)v.findViewById(R.id.id_materielintervention);
				holder.nomMat = (TextView)v.findViewById(R.id.nom_com);
				holder.etatInterv = (TextView)v.findViewById(R.id.etat);	
				holder.conseil = (TextView)v.findViewById(R.id.conseil);	
				holder.numMat = (TextView)v.findViewById(R.id.num_mat);
		} 
		else 
		{
			holder = (ViewHolder) v.getTag();
		}  

		holder.mat_interv = getItem(arg0); 
		//récupère l'élément de la position arg0
		
		//on peuple les différens TextView
		holder.idMatInterv.setText("Intervention N° " + holder.mat_interv.get_idMaterielIntervention()); 
		holder.nomMat.setText("Libelle Materiel : " + holder.mat_interv.get_nomMateriel());
		holder.etatInterv.setText("Etat : " + holder.mat_interv.get_etatIntervention()); 
		holder.conseil.setText("Conseil : " + holder.mat_interv.get_conseil()); 
		holder.numMat.setText("Materiel N° " + holder.mat_interv.get_numMat()); 
	

		v.setTag(holder);

		// TODO Auto-generated method stub
		return v;
	}
	
	public class ViewHolder { 
		// création d'une structure de données
		public MaterielIntervention mat_interv;
		public TextView idMatInterv;
		public TextView nomMat;
		public TextView etatInterv;
		public TextView conseil;
		public TextView numMat;
	}
	
}