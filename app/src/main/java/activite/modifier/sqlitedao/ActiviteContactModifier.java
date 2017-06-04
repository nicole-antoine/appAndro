package activite.modifier.sqlitedao;

import activite.lister.sqlitedao.ActiviteTousLesContacts;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.classe.metier.Contact;
import com.exemple.sqlitedao.R;
import com.exemple.sqlitedao.data.DAODB;

public class ActiviteContactModifier extends Activity {
	  EditText nomET, telephoneET, idET;
	  Button sauverBT;
	  Button listerBT;
	  DAODB dba;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_modifier); //layout contact.xml
        dba = new DAODB(this.getBaseContext()); //this = contexte
        dba.open();
        idET = (EditText)findViewById(R.id.id_modifier);
        nomET = (EditText)findViewById(R.id.nom_modifier);
        telephoneET = (EditText)findViewById(R.id.telephone_modifier);
        sauverBT = (Button)findViewById(R.id.submit_modifier);
        sauverBT.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    try {
                    	sauvegarderContactDansBD();
                    } 
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        listerBT = (Button)findViewById(R.id.lister_modifier);
        listerBT.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                try {
                	Intent intent = new Intent(ActiviteContactModifier.this,ActiviteTousLesContacts.class);
                	ActiviteContactModifier.this.startActivity(intent);
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
    }
    public void sauvegarderContactDansBD(){
    	Contact contact = new Contact( Integer.parseInt(idET.getText().toString()),nomET.getText().toString(), telephoneET.getText().toString());
    	dba.modifierContact(contact);
    	dba.close();
    	idET.setText("");
    	nomET.setText("");
    	telephoneET.setText("");
    	Intent i = new Intent(ActiviteContactModifier.this, ActiviteTousLesContacts.class);
    	this.startActivity(i);
    }
}