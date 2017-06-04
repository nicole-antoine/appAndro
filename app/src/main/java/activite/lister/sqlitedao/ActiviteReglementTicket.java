package activite.lister.sqlitedao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;

import com.classe.metier.Intervention;
import com.classe.metier.MaterielIntervention;
import com.exemple.sqlitedao.R;
import com.exemple.sqlitedao.data.DAODB;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ActiviteReglementTicket extends Activity {
	
	public DAODB dba;
	private static final String UPDATE_BDD_SQLSERVER = "http://192.168.1.33/SIO2014/nicole/PPE4/PPE4/test_export_android_ticket.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		dba = new DAODB(this);
		
		setContentView(R.layout.un_ticket);
		super.onCreate(savedInstanceState);
		
		final Intent intent = this.getIntent();
		
		Intervention inter = null;
		MaterielIntervention matInter = null;
	
		dba.open();
		try {
			 inter = dba.getUneIntervention(intent.getIntExtra("idinterv", 0));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dba.open();
		try {
			matInter = dba.getUneInterventionMateriel(intent.getIntExtra("idmat", 0));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final EditText edittxt_temps = (EditText)findViewById(R.id.edittxt_duree);
		final EditText edittxt_conseil = (EditText)findViewById(R.id.edittxt_conseil);
		
		TextView TxtnumMateriel = (TextView)findViewById(R.id.txtview_nummateriel); // TextView Num du materiel
		TextView TxtDateDeb = (TextView)findViewById(R.id.txtview_intervdate); // TextView Date de la demande
		TextView TxtnumDemandeur = (TextView)findViewById(R.id.txtview_demandeur);
		TextView TxtConseil = (TextView)findViewById(R.id.txtview_conseil);
		
		final DatePicker dpicker = (DatePicker)findViewById(R.id.datePicker1);
		
		final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		
		String texteNumMateriel = TxtnumMateriel.getText() + " " + matInter.get_numMat() + ", un/une " + matInter.get_nomMateriel() + "."; // Chaine Num du materiel
		String texteDateDeb = TxtDateDeb.getText() + " " + inter.get_dateDemandeIntervention()+ "."; // Chaine Date de la demande
		String texteNumDemandeur = TxtnumDemandeur.getText() + " " + inter.get_idIntervenant() + ".";
		String texteConseil  = TxtConseil.getText() + ": ";
		
		TxtnumMateriel.setText(texteNumMateriel); // Mettre Num du materiel TextView
		TxtDateDeb.setText(texteDateDeb); // Mettre Date de la demande TextView 
		TxtnumDemandeur.setText(texteNumDemandeur);
		TxtConseil.setText(texteConseil);
		
		Button btnValiderTicket = (Button) findViewById(R.id.btnValiderTicket);
		
		//Utilisation d'un Listener [interface de gestion d'evenements]
		//pour recuperer l'interaction avec le bouton
		btnValiderTicket.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View arg0)
			{ 	
				// Affichage d'un message dans le LogCat du DDMS
				// Log.i("LocDVD", "Bouton Policier");
    		
				//Creation d'un AlertDialog
				final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActiviteReglementTicket.this);
				alertDialog.setTitle("Reglement Ticket");
				alertDialog.setMessage("Valider le ticket ?");
				//alertDialog.setIcon(R.drawable.policier);
    		
				alertDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() 
				{
					public void onClick(DialogInterface arg0, int arg1)
					{
						/*Toast toast = Toast.makeText(getApplicationContext(), "Ticket valide "
								+ dpicker.getYear() + "-" + dpicker.getMonth() + "-" + dpicker.getDayOfMonth() + " " 
								+ spinner.getSelectedItem() , Toast.LENGTH_SHORT);
						toast.show();*/
	        		
						int temps = edittxt_temps.getText().length();
						//si les deux champs sont remplis
						if(temps == 8 )
						{
							enregistrerBddSqlServer(edittxt_temps.getText().toString(), spinner.getSelectedItem().toString(), 
							intent.getIntExtra("idinterv", 0), intent.getIntExtra("idmat", 0), dpicker.getYear() + "-" + dpicker.getMonth() + "-" + dpicker.getDayOfMonth(), edittxt_conseil.getText().toString());
							//Intent intent = new Intent(ActiviteReglementTicket.this, MenuRedirection.class);
							//startActivity(intent);
						}
						else
							createDialog("Erreur", "La duree est au mauvais format/non renseignee");
						
					}
				});
				alertDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
				
					public void onClick(DialogInterface arg0, int arg1) {
						//Pas de traitement
					}
				});
				alertDialog.setNeutralButton("Je sais pas", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						//Pas de traitement
					}
				});
    		
				alertDialog.show();
    	
			}
    	
		});
	}
	
	private void createDialog(String title, String text)
	{
		//Creation d'une popup affichant un message
		AlertDialog ad = new AlertDialog.Builder(this).setPositiveButton("OK", null).setTitle(title).setMessage(text).create();
		ad.show();
	}
	
	private void enregistrerBddSqlServer(final String duree, final String typeInterv, final int idInterv, final int idMat, final String dateInterv, final String conseil)
	{
		//Creation d'un thread
		Thread t = new Thread()
		{
			public void run()
			{
				Looper.prepare();
				//On se connecte au serveur afin de communiquer avec le PHP
				DefaultHttpClient client = new DefaultHttpClient();
				HttpConnectionParams.setConnectionTimeout(client.getParams(), 15000);
				
				Toast toast = Toast.makeText(getApplicationContext(),
				duree + " " + typeInterv + " " + idInterv + " " + idMat + " " + dateInterv, Toast.LENGTH_SHORT);
				toast.show();
				
				try
				{
					//On etablit un lien avec le script PHP
					HttpPost post = new HttpPost(UPDATE_BDD_SQLSERVER);
					List<NameValuePair> nvps = new ArrayList<NameValuePair>();
					nvps.add(new BasicNameValuePair("temps", duree));
					nvps.add(new BasicNameValuePair("type", typeInterv));
					nvps.add(new BasicNameValuePair("numinterv", String.valueOf(idInterv)));
					nvps.add(new BasicNameValuePair("nummateriel", String.valueOf(idMat)));
					nvps.add(new BasicNameValuePair("date", dateInterv));
					nvps.add(new BasicNameValuePair("conseil", conseil));
					
					post.setHeader("Content-Type", "application/x-www-form-urlencoded");
					
					//On passe les parametres login et password qui vont etre recuperes par le script PHP en post
					post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
					
					//On recupere le resultat du script
					client.execute(post);				
				}
				catch(Exception e)
				{
					createDialog("ERREUR LOGIN", "Couldn't establish connection" + e.getMessage());
				}	
				Looper.loop();
			}
		};
		t.start();
	}
}
