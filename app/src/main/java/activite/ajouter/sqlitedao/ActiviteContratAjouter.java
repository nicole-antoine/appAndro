package activite.ajouter.sqlitedao;

import activite.lister.sqlitedao.ActiviteTousLesContrats;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.exemple.sqlitedao.R;
import com.exemple.sqlitedao.data.DAODB;

public class ActiviteContratAjouter extends Activity {
	  EditText societeET, ticketGratuitET, nbTicketsET, dateDebET, ptttcET, pttvaET, pthtET, depotET ;
	  Button sauverBT;
	  Button listerBT;
	  DAODB dba;

  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.contrat_ajouter); //layout contrat.xml
     
      dba = new DAODB(this.getBaseContext()); //this = contexte
      dba.open();
    
      societeET = (EditText)findViewById(R.id.edittxt_societe);
      ticketGratuitET = (EditText)findViewById(R.id.edittxt_ticket_gratuit);
      nbTicketsET = (EditText)findViewById(R.id.edittxt_nbtickets);
      dateDebET = (EditText)findViewById(R.id.edittxt_date_deb);
      ptttcET = (EditText)findViewById(R.id.edittxt_ptttc);
      pttvaET = (EditText)findViewById(R.id.edittxt_pttva);
      pthtET = (EditText)findViewById(R.id.edittxt_ptht);
      depotET = (EditText)findViewById(R.id.edittxt_depot);
      
      
      sauverBT = (Button)findViewById(R.id.submitButtonContrat);
      sauverBT.setOnClickListener(new OnClickListener() {
              public void onClick(View v) {
                  try {
                  	sauvegarderContratDansBD();
                  } 
                  catch (Exception e) {
                	  createDialog("erreur", e.getMessage());
                  }
              }
          });
      listerBT = (Button)findViewById(R.id.listerButtonContrat);
      listerBT.setOnClickListener(new OnClickListener() {
    	  public void onClick(View v) {
              try {
              	Intent intent = new Intent(ActiviteContratAjouter.this,ActiviteTousLesContrats.class);
              	ActiviteContratAjouter.this.startActivity(intent);
              } 
              catch (Exception e) {
                  e.printStackTrace();
              }
          }
      });
      
  } 
  
  public void sauvegarderContratDansBD(){
  	dba.insererContratLocation(Integer.parseInt(societeET.getText().toString()), Integer.parseInt(ticketGratuitET.getText().toString()),
  			Integer.parseInt(nbTicketsET.getText().toString()), dateDebET.getText().toString(),
  			Float.parseFloat(ptttcET.getText().toString()), Float.parseFloat(pttvaET.getText().toString()),
  			Float.parseFloat(pthtET.getText().toString()), Float.parseFloat(depotET.getText().toString()));
  	dba.close();
  	
  	societeET.setText("");
  	ticketGratuitET.setText("");
  	nbTicketsET.setText("");
  	dateDebET.setText("");
  	ptttcET.setText("");
  	pttvaET.setText("");
  	pthtET.setText("");
  	depotET.setText("");
  	
  	Intent i = new Intent(ActiviteContratAjouter.this, ActiviteTousLesContrats.class);
  	this.startActivity(i);
  }
  
  private void createDialog(String title, String text)
	{
		//Creation d'une popup affichant un message
		AlertDialog ad = new AlertDialog.Builder(this).setPositiveButton("OK", null).setTitle(title).setMessage(text).create();
		ad.show();
	}

}

