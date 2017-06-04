package com.classe.adapter;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.classe.metier.ContratLocation;
import com.exemple.sqlitedao.R;
import com.exemple.sqlitedao.data.C;
import com.exemple.sqlitedao.data.DAODB;

public class ContratLocationAdapter extends BaseAdapter{
	
	private LayoutInflater inflater;
	private ArrayList<ContratLocation> contratslocation;
	private DAODB dba;
	
	public ContratLocationAdapter(Context context, DAODB dba) { //2 paramètres : le contexte et l'accès à la base de données
		this.dba = dba;
		this.inflater = LayoutInflater.from(context);
		this.contratslocation = new ArrayList<ContratLocation>();
		getdata();
	}
	//@SuppressWarnings("deprecation")
	public void getdata(){ //permet de remplir une liste ArrayList avec des objets de type Contact à partir des données de la table "contacts"
		Cursor c = dba.getTousContratsLocation(); //on récupère tous les contacts dans un curseur (voir classe ADODB)
        this.startManagingCursor(c);
        if(c.moveToFirst()){ //on teste si le curseur contient un enregistrement
        	do{
        		
        		int id = c.getInt(c.getColumnIndex(C.KEY_ID_CONTRATLOCATION)); 
        		int idSociete = c.getInt(c.getColumnIndex(C.ID_SOCIETE)); 
        		int ticketGratuit = c.getInt(c.getColumnIndex(C.TICKETGRATUIT)); 
        		int nbTickets = c.getInt(c.getColumnIndex(C.NBTICKETS)); 
        		String	dateDeb = c.getString(c.getColumnIndex(C.DATEDEBUTCONTRAT)); 
        		float ptttc = c.getInt(c.getColumnIndex(C.PTTTC)); 
        		float pttva = c.getInt(c.getColumnIndex(C.PTTVA)); 
        		float ptht = c.getInt(c.getColumnIndex(C.PTHT)); 
        		float depotGarantie = c.getInt(c.getColumnIndex(C.DEPOTGARANTIE)); 
        		
        		ContratLocation objContratLocation = new ContratLocation(id, idSociete, ticketGratuit, nbTickets, dateDeb
        				, ptttc, pttva, ptht, depotGarantie); 
        		contratslocation.add(objContratLocation);
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
		return contratslocation.size();  //renvoie le nombre d’éléments contenus dans la collection de données.
	}

	@Override
	public ContratLocation getItem(int i) {
		// TODO Auto-generated method stub
		return contratslocation.get(i); //retourne l’élément contenu à l’index-position donné
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
				v = inflater.inflate(R.layout.ligne_contratloc, null); //la méthode inflate permet de charger le fichier XML
				holder = new ViewHolder();
				holder.idC = (TextView)v.findViewById(R.id.id_contrat);
				holder.idS = (TextView)v.findViewById(R.id.id_societe);
				holder.ticketGrat = (TextView)v.findViewById(R.id.ticket_gratuit);	
				holder.nbTickets = (TextView)v.findViewById(R.id.nb_tickets);
				holder.dateDeb = (TextView)v.findViewById(R.id.date_debut);
				holder.ptttc = (TextView)v.findViewById(R.id.ptttc);	
				holder.pttva = (TextView)v.findViewById(R.id.pttva);
				holder.ptht = (TextView)v.findViewById(R.id.ptht);
				holder.depot = (TextView)v.findViewById(R.id.depot_garantie);	
		} 
		else 
		{
			holder = (ViewHolder) v.getTag();
		}  
		
		holder.contratloc = getItem(arg0); 
		//récupère l'élément de la position arg0
		
		//on peuple les différens TextView
		holder.idC.setText("Contrat N° " + String.valueOf(holder.contratloc.getIdContratLocation())); //int est converti en chaîne
		holder.idS.setText(String.valueOf("Société N° " + holder.contratloc.getIdSociete()));
		holder.ticketGrat.setText(String.valueOf("Ticket gratuit : " + holder.contratloc.getTicketGratuit()));
		holder.nbTickets.setText(String.valueOf("Tickets restant : " + holder.contratloc.getNbTickets()));
		holder.dateDeb.setText(String.valueOf("Date de signature : " + holder.contratloc.getDateDeb()));
		holder.ptttc.setText(String.valueOf("PTTTC : " + holder.contratloc.getPTTTC() + "€"));
		holder.pttva.setText(String.valueOf("PTTVA : " + holder.contratloc.getPTTVA()+ "€"));
		holder.ptht.setText(String.valueOf("PTHT : " + holder.contratloc.getPTHT()+ "€"));
		holder.depot.setText(String.valueOf("Dépôt de garantie : " + holder.contratloc.getDepotGarantie()+ "€"));

		v.setTag(holder);

		// TODO Auto-generated method stub
		return v;
	}
	public class ViewHolder { // création d'une structure de données
		public ContratLocation contratloc;
		public TextView idC;
		public TextView idS;
		public TextView ticketGrat;
		public TextView nbTickets;
		public TextView dateDeb;
		public TextView ptttc;
		public TextView pttva;
		public TextView ptht;
		public TextView depot;
	}
	
}
