package evaluation;


import build_json.Feature;
import build_json.JsonExtractor;
import engine.Association;
import engine.AssociationEngine;
import engine.hashFunction.Hash;
import engine.hashFunction.HashFunction;
import engine.hashFunction.SecretKey;
import engine.hashFunction.sha256.Sha256HashFunction;
import engine.hashFunction.sha256.Sha256SecretKey;
import evaluation.utils.Constants;
import templateUpdater.StaticTemplateUpdater;
import verifier.BasicVerifier;
import verifier.Verifier;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;


/**
 * Created by Julien Hatin on 11/12/15.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException, FileNotFoundException {

        JsonExtractor extractor = JsonExtractor.getInstance();
        HashMap<Integer, List<Feature>> features = extractor.extractFeatures(Constants.datacollectionFile, Constants.dataCollectionSubjects); //On récupère les données des clients pour la base de donnée

        HashFunction hashFunction = new Sha256HashFunction(); // On défini notre fonction de hash
        SecretKey secretKey = new Sha256SecretKey("Un secret à ne pas partager"); // Le secret et la fonction de hash sont communs à tous les utilisateurs dans cette phase d'expérimentation

        double SLOT_TIME = 3 * Constants.ONE_MINUTE; //On echantillone toute les 3 minutes

        List<Integer> subjects = Constants.dataCollectionSubjects; //La liste des clients dans le fichier de données

        for (int subject : subjects) { //Pour chaque client
            Iterator<Feature> featureIterator = features.get(subject).iterator();//On récupère les données de l'utilisateur actuel qu'on place dans un itérateur
         
            AssociationEngine associationEngine = new AssociationEngine(3); // On associe les événements par 3

            //On crée un nouveau vérifieur /!\ chaque utilisateur doit avoir son propre vérifier
            Verifier verifier = new BasicVerifier(new StaticTemplateUpdater(), 0.08, -0.1);

            Feature feature = featureIterator.next();

            double currentTime = 0d; //Le temps de départ est fixé à zéro

            //Phase d'enrollement
            while (feature.getTimestamp() < Constants.ONE_WEEK) {
                if ((feature.getTimestamp() < currentTime + SLOT_TIME)) {
                    associationEngine.add(feature.getDiscreteValue());
                } else {
                    currentTime = currentTime + SLOT_TIME;
                    if (associationEngine.size() > 3) { // Si on a plus de 3 events
  
                        List<Association> associationList = associationEngine.getEventAssociation(); //On récupère les associations
                        associationEngine.clear(); //On oublie pas de vider la liste des valuers

                        List<Hash> hashList = hashFunction.performHash(associationList, secretKey); // On récupère la liste des hash
                        	
                        verifier.enroll(hashList, currentTime); // On envoie au serveur les hash pour enrollement
                    }
                    currentTime += SLOT_TIME; // On avance de 3 minutes
                }

                feature = featureIterator.next();
            }

            //Phase de vérification
            List<Double> scoreList = new ArrayList<>();
            List<Double> timestampList = new ArrayList<>();
            do {
                if ((feature.getTimestamp() < currentTime + SLOT_TIME)) {
                    associationEngine.add(feature.getDiscreteValue());
                } else {
                    currentTime = currentTime + SLOT_TIME;
                    if (associationEngine.size() > 3) { // Si on a plus de 3 events
                        List<Association> associationList = associationEngine.getEventAssociation(); //On récupère les associations
                        associationEngine.clear(); //On oublie pas de vider la liste des valuers
                        List<Hash> hashList = hashFunction.performHash(associationList, secretKey); // On récupère la liste des hash
                        verifier.verify(hashList, currentTime); // On envoi au serveur les hash pour verification
                    }
                    //On ajoute le score dans le tableau de score
                    scoreList.add(verifier.getTrustScore().getScore());
                    timestampList.add(currentTime);
                    currentTime += SLOT_TIME; // On avance de 3 minutes
                }
                feature = featureIterator.next();
            } while (featureIterator.hasNext());

            //On affiche les scores des utilisateurs      
           // System.setOut(new PrintStream(new FileOutputStream("out.log")));// On reidirige la sortie standard vers un fichier 
            
            System.out.println("utilisateur : " + subject);
            System.out.println(timestampList.toString()); 
            System.out.println("\nScore de l'utilisateur\n"+scoreList.toString());
        }
    }


}