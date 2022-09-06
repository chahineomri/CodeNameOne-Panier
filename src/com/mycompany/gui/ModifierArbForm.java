
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Commandee;
import com.mycompany.services.ServiceCmd;


/**
 *
 * @author Lenovo
 */
public class ModifierArbForm extends BaseForm {
    
    Form current;
    public ModifierArbForm(Resources res , Commandee r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Commande");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField nom_utilisateur = new TextField(r.getNomutilisateur(), "nom_utilisateur" , 20 , TextField.ANY);
        TextField etat = new TextField(r.getEtat(), "etat" , 20 , TextField.ANY);
                TextField date_creation = new TextField(r.getDatecreation(), "date_creation" , 20 , TextField.ANY);

        TextField totale = new TextField(String.valueOf(r.getTotale()), "totale" , 20 , TextField.ANY);

 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
    
        
        
        nom_utilisateur.setUIID("NewsTopLine");
        etat.setUIID("NewsTopLine");
        date_creation.setUIID("NewsTopLine");
         totale.setUIID("NewsTopLine");

        
        nom_utilisateur.setSingleLineTextArea(true);
        etat.setSingleLineTextArea(true);
        date_creation.setSingleLineTextArea(true);
        totale.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNomutilisateur(nom_utilisateur.getText());
           r.setEtat(etat.getText());
                      r.setDatecreation(date_creation.getText());

           r.setTotale(Integer.parseInt( (totale.getText() )) );
           
       
       
       //appel fonction modfier reclamation men service
       
       if(ServiceCmd.getInstance().modifierClub(r)) { // if true
           new ListArbForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListArbForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(nom_utilisateur),
                createLineSeparator(),
                new FloatingHint(etat),
                createLineSeparator(),
                new FloatingHint(date_creation),
                createLineSeparator(),
                                new FloatingHint(totale),

                
                createLineSeparator(),//ligne de sÃ©paration
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}
