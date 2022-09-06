/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import com.mycomany.entities.Commandee;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mycompany.services.ServiceCmd;

/**
 *
 * @author Amirov
 */
public class ServiceCmd {
    

     //singleton 
    public static ServiceCmd instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceCmd getInstance() {
        if(instance == null )
            instance = new ServiceCmd();
        return instance ;
    }
    
    public ServiceCmd() {
        req = new ConnectionRequest();
        
    }
    
    
    
     //ajout 
   
                     public void ajoutLiv(Commandee arb) throws IOException{
        
        String url =Statics.BASE_URL+"/ajouter/commandee?nomutilisateur="+arb.getNomutilisateur() +"&etat="+arb.getEtat()+"&datecreation="+arb.getDatecreation()+"&totale="+arb.getTotale();  
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//
        
    }
    
    
    
         //affichage
    
    public ArrayList<Commandee>affichageLiv() {
        ArrayList<Commandee> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/afficher/commandee";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Commandee re = new Commandee();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String nomutilisateur = obj.get("nom_utilisateur").toString();                    
                        String etat = obj.get("etat").toString();
                        String datecreation = obj.get("date_creation").toString();

                         float totale = Float.parseFloat(obj.get("totale").toString());
                                                

                     //   float etat = Float.parseFloat(obj.get("etat").toString());
                        
                        re.setId((int)id);
                        
                        re.setNomutilisateur(nomutilisateur);
                        re.setEtat(etat);
                        re.setDatecreation(datecreation);

                        re.setTotale((int)totale);
                        
                 
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
        
    
                     

/*public ArrayList<Commandee> parseCat(String jsonText) {
        try {
            commandee= new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> clubListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) clubListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données

                Commandee e = new Commandee ();

                try {
                    float id = Float.parseFloat(obj.get("id").toString());

                    e.setId((int) id);
                } catch (Exception e1) {
                    System.out.println("id");
                }

                try {
                    e.setNomutilisateur(obj.get("nomutilisateur").toString());
                } catch (Exception e2) {
                    System.out.println("nomutilisateur");
                }

               

                try {
                    e.setEtat(obj.get("etat").toString());
                } catch (Exception e4) {
                    System.out.println("etat");
                }
                try {
                    e.setDatecreation(obj.get("=datecreation").toString());
                } catch (Exception e5) {
                    System.out.println("datecreation");
                }
                try {
                    float totale = Float.parseFloat(obj.get("totale").toString());

                    e.setTotale((int) totale);
                } catch (Exception e6) {
                    System.out.println("totale");
                }
                try {
                    commandee.add(e);
                } catch (Exception e7) {
                    System.out.println("Commande");
                }
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         
        return commandee;
    }
        */
    
      //Delete 
    public boolean deleteLiv(int id ) {
        String url = Statics.BASE_URL +"/delete/commandee?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
     NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
     public boolean modifierClub(Commandee club) {
        String url =Statics.BASE_URL+"/modifier/commandee?id="+club.getId()+"&nom_utilisateur="+club.getNomutilisateur()+"&etat="+club.getEtat()+"&date_creation="+club.getDatecreation()+"&totale="+club.getTotale();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    
    
}
