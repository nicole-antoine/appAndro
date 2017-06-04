package activite.supprimer.sqlitedao;

import activite.lister.sqlitedao.ActiviteTousLesContrats;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.exemple.sqlitedao.R;
import com.exemple.sqlitedao.data.DAODB;

public class ActiviteContratSupprimer extends Activity {
	  EditText idET;
	  Button sauverBT;
	  Button listerBT;
	  DAODB dba;

  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.contrat_supprimer); //layout contact_supprimer.xml
      dba = new DAODB(this.getBaseContext()); //this = contexte
      dba.open();
      
      idET = (EditText)findViewById(R.id.id_supprimer_contrat);
     
      sauverBT = (Button)findViewById(R.id.submit_supprimer_contrat);
      sauverBT.setOnClickListener(new OnClickListener() {
              public void onClick(View v) {
                  try {
                  	supprimerContactDansBD();
                  } 
                  catch (Exception e) {
                      e.printStackTrace();
                  }
              }
          });
      listerBT = (Button)findViewById(R.id.submit_lister_supprimer_contrats);
      listerBT.setOnClickListener(new OnClickListener() {
          public void onClick(View v) {
              try {
              	Intent intent = new Intent(ActiviteContratSupprimer.this,ActiviteTousLesContrats.class);
              	ActiviteContratSupprimer.this.startActivity(intent);
              } 
              catch (Exception e) {
                  e.printStackTrace();
              }
          }
      });
      
  }
  public void supprimerContactDansBD(){
  	dba.supprimerUnContratLocation(Integer.parseInt(idET.getText().toString()));
  	dba.close();
  	
  	idET.setText("");
  
  	Intent i = new Intent(ActiviteContratSupprimer.this, ActiviteTousLesContrats.class);
  	this.startActivity(i);
  }
}


