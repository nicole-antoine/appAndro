package activite.modifier.sqlitedao;

import activite.lister.sqlitedao.ActiviteTousLesContrats;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.classe.metier.ContratLocation;
import com.exemple.sqlitedao.R;
import com.exemple.sqlitedao.data.DAODB;

public class ActiviteContratModifier extends Activity {
	  EditText societeET, ticketGratuitET, nbTicketsET, dateDebET, ptttcET, pttvaET, pthtET, depotET, idET ;
	  Button sauverBT;
	  Button listerBT;
	  DAODB dba;
	
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.contrat_modifier); //layout contrat.xml
      dba = new DAODB(this.getBaseContext()); //this = contexte
      dba.open();
    
      idET = (EditText)findViewById(R.id.edittxt_idcontrat);
      societeET = (EditText)findViewById(R.id.edittxt_societe);
      ticketGratuitET = (EditText)findViewById(R.id.edittxt_ticket_gratuit);
      nbTicketsET = (EditText)findViewById(R.id.edittxt_nbtickets);
      dateDebET = (EditText)findViewById(R.id.edittxt_date_deb);
      ptttcET = (EditText)findViewById(R.id.edittxt_ptttc);
      pttvaET = (EditText)findViewById(R.id.edittxt_pttva);
      pthtET = (EditText)findViewById(R.id.edittxt_ptht);
      depotET = (EditText)findViewById(R.id.edittxt_depot);
      
      sauverBT = (Button)findViewById(R.id.btn_modifier_contrat);
      sauverBT.setOnClickListener(new OnClickListener() {
              public void onClick(View v) {
                  try {
                  	sauvegarderContratDansBD();
                  } 
                  catch (Exception e) {
                      e.printStackTrace();
                  }
              }
          });
      listerBT = (Button)findViewById(R.id.btn_lister_modifier_Contrat);
      listerBT.setOnClickListener(new OnClickListener() {
          public void onClick(View v) {
              try {
              	Intent intent = new Intent(ActiviteContratModifier.this,ActiviteTousLesContrats.class);
              	ActiviteContratModifier.this.startActivity(intent);
              } 
              catch (Exception e) {
                  e.printStackTrace();
              }
          }
      });
      
  }
  public void sauvegarderContratDansBD(){ 
  
	  ContratLocation contrat = new ContratLocation(Integer.parseInt(idET.getText().toString()), Integer.parseInt(societeET.getText().toString()),
  			Integer.parseInt(ticketGratuitET.getText().toString()), Integer.parseInt(nbTicketsET.getText().toString()),
  			(dateDebET.getText().toString()), Float.parseFloat(ptttcET.getText().toString()), Float.parseFloat(pttvaET.getText().toString()),
  			Float.parseFloat(pthtET.getText().toString()),  Float.parseFloat(depotET.getText().toString()));
  	
  	dba.modifierContratLocation(contrat);
  	dba.close();
  
  	idET.setText("");
	societeET.setText("");
  	ticketGratuitET.setText("");
  	nbTicketsET.setText("");
  	dateDebET.setText("");
  	ptttcET.setText("");
  	pttvaET.setText("");
  	pthtET.setText("");
  	depotET.setText("");
  	
  	Intent i = new Intent(ActiviteContratModifier.this, ActiviteTousLesContrats.class);
  	this.startActivity(i);
  }
}