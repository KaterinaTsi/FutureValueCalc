/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.futurevaluegui;

/**
 *
 * @author katerina
 */


import javax.swing.*;
import java.awt.*;

/**
* Η κλαση FutureValue υπολογίζει τη μελλοντική αξία μιας περιοδικής επένδυσης,
* με βάση το ποσό της μηνιαίας αποταμίευσης, το ετήσιο επιτόκιο και το χρονικό διάστημα επένδυσης.
* Ο ανατοκισμός γινεται μηνιαια.
* Περιέχει γραφικό περιβάλλον χρήστη.
 */

public class FutureValueGUI {
    
    /**
     * Η μέθοδος main εκκινεί την εφαρμογή.
     * Χρησιμοποιεί τη SwingUtilities.invokeLater για να δημιουργήσει 
     * το GUI με ασφάλεια από το main thread.
    */

    public static void main(String[] args) {
        
       SwingUtilities.invokeLater(() -> new FutureValue().setVisible(true));
        
    }
}

/**
 * Η κλάση FutureValue δημιουργει το γραφικό περιβαλλον χρηστη για τον υπολογισμό της μελλοντικης
 * αξίας σειρας πληρωμών.
 * Κληρονομει από JFrame και περιλαμβάνει πεδία εισόδου, ετικέτες και κουμπί υπολογισμού.
 */
class FutureValue extends JFrame {
    
    //Δηλώσεις των components του GUI
    private JTextField textPoso, textEpitokio, textEtos, textResult;
    private JLabel labelPoso, labelEpitokio, labelEtos, labelResult, keno;
    private JButton btnCalc;
    
    /**
     * Κατασκευαστής της κλάσης FutureValueGUI.
     * Αρχικοποιει και διατάσσει τα γραφικά στοιχεία και προσθετει τον listener για τον υπολογισμό της μελλοντικής αξίας.
     */
    public FutureValue() {
        
        
        //Δημιουργια πεδιων εισόδου
        textPoso = new JTextField();
        textEpitokio = new JTextField();
        textEtos = new JTextField();
        
        textResult = new JTextField();
        textResult.setEditable(false);  // Το αποτελεσμα δεν ειναι επεξεργασιμο από τον χρηηστη
        
        // Δημιουργία ετικετών
        labelPoso = new JLabel(" Ποσό μηνιαίας επένδυσης:");
        labelEpitokio =  new JLabel(" Ετήσιο επιτόκιο:");
        labelEtos =  new JLabel(" Αριθμός ετών:");
        labelResult =  new JLabel(" Μελλοντική αξία:");
        
        // Κουμπι υπολογισμοθ με action listener
        btnCalc = new JButton(" Υπολογισμός"); 
        btnCalc.addActionListener(e -> CalculateFvalue());
        
        // Κενη ετικέτα για καλύτερη διάταξη στο παραθυρο
        keno = new JLabel();
        
        //Ρυθμισεις παραθύρου
        setSize(400,400);
        setTitle("Future Value Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        setLayout(new GridLayout(5,2));
        
        // Προσθηκη στοιχειων στο παράθυροο
        add(labelPoso);
        add(textPoso);
        
       
        add(labelEpitokio);
        add(textEpitokio);
        
       
        add(labelEtos);
        add(textEtos);
        
        
        add(labelResult);
        add(textResult); 
        
        add(keno);
        
        add(btnCalc);
        
        
                
    }
    
    /** 
     * Υπολογίζει τη μελλοντική αξία της επένδυσης βάσει των τιμών που εισάγει ο χρήστης.
     * Χρησιμοποιεί μηνιαίο επιτόκιο και επανάληψη για κάθε μήνα.
     * Το αποτέλεσμα εμφανίζεται στο textResult.
     * Αν τα δεδομενα δεν είναι έγκυρα, εμφανίζεται μηνυμα λάθους.
     */
    private void CalculateFvalue() {
        
        try{
            // αναγνωση εισόδων και μετατροπή σε αριθμούς
            double poso = Double.parseDouble(textPoso.getText());
            double epitokio = Double.parseDouble(textEpitokio.getText());
            double years = Double.parseDouble(textEtos.getText());
            
            //υπολογισμός μηνών και μηνιαίου επιτοκίου
            double months = years * 12;
            double monthlyRate = epitokio / 12 / 100;
            double fvalue = 0;
           
            
            //επαναληψη για καθε μήνα και υπολογισμός της μελλοντικής αξας
            for(int i = 0; i < months; i++) {
                fvalue = ( fvalue + poso) * (1 + monthlyRate);
            }
                    
            textResult.setText(String.format("%.2f €", fvalue));
            
            
        } catch (NumberFormatException w) {
            
            textResult.setText("Μη εγκυρα δεδομενα");
        }
    }
    
}
