package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class AFFINE implements Initializable {

    public static void start1() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(AFFINE.class.getResource("sample.fxml"));
        Image icon = new Image(AFFINE.class.getResourceAsStream("Images/1.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("CRYPTOSIS");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }

    private void closelastButtonAction(){ //hadi pour button cancel

        Stage stage = (Stage) closeButton.getScene().getWindow();

        stage.close();

    }

    static int counter = 0;
    static StringBuilder output=new StringBuilder();
    @FXML
    private javafx.scene.control.TextArea INPUT;
    @FXML
    private javafx.scene.control.TextArea OUTPUT;
    @FXML
    private javafx.scene.control.TextField ENCRYPTKEY1;
    @FXML
    private javafx.scene.control.TextField ENCRYPTKEY2;

    @FXML private javafx.scene.control.Button closeButton;
    @FXML private javafx.scene.control.Label backButton;
    @FXML private javafx.scene.control.Button chooseButton;
    @FXML private ComboBox<String> language;
    @FXML private javafx.scene.control.ProgressIndicator waitbutton;
    ObservableList<String> list = FXCollections.observableArrayList("ENGLISH","FRENSH");

    public void closeButtonAction(){

        Stage stage = (Stage) closeButton.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warnning");
        alert.setHeaderText("Look you want to close the program !");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            stage.close();
        } else {
            alert.close();

        }

    }
    public void back() throws IOException {

        //Main.launch();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog with Custom Actions");
        alert.setHeaderText("It Looks you want to get back , you want to save your Output ?");
        alert.setContentText("Choose your option.");

        ButtonType buttonTypeOne = new ButtonType("Save");
        ButtonType buttonTypeTwo = new ButtonType("Without Save");
        //ButtonType buttonTypeThree = new ButtonType("Three");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            alert.close();
        } else if (result.get() == buttonTypeTwo) {
            closelastButtonAction();
            Controller.start();
        }
        // ... user chose "Two"

        else {
            closelastButtonAction();
            //System.out.println("test");
        }
    }
    public void Choose() {

        FileChooser fileChooser = new FileChooser();
        Stage stage1 = (Stage) chooseButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage1);
        String lines="";
        String line;
        try {
            FileReader fileReader = new FileReader(selectedFile);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                lines += line;

            }
            INPUT.setText(lines);

            //split(lines);
        } catch (FileNotFoundException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Unable to open file '");
            alert.setContentText("Choose another file (.txt)");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void ENCRYPT() {
        if(ENCRYPTKEY1.getText().equals("") || ENCRYPTKEY2.getText().equals("")  ){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("You miss insert your KEY");
            alert.setContentText("Pres OK to continue and try again");
            alert.showAndWait();
            ENCRYPTKEY1.setText("");
            ENCRYPTKEY2.setText("");
        }
        else{

            OUTPUT.setText("");
            String TXT_INP = INPUT.getText();
            //OUTPUT.setText(TXT_INP);
            String kk1 = ENCRYPTKEY1.getText();
            String kk2 = ENCRYPTKEY2.getText();
            int k1 = Integer.parseInt(kk1);
            int k2 = Integer.parseInt(kk2);
            ENCRYPTKEY1.setText("");
            ENCRYPTKEY2.setText("");

            crypt_affine(k1,k2,TXT_INP);}
    }

    public void DECRYPT() {

        if(ENCRYPTKEY1.getText().equals("") || ENCRYPTKEY2.getText().equals("") ){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("You miss insert your KEY");
            alert.setContentText("Pres OK to continue and try again");
            alert.showAndWait();
        }
        else{

            OUTPUT.setText("");
            String TXT_INP = INPUT.getText();
            String kk1 = ENCRYPTKEY1.getText();
            String kk2 = ENCRYPTKEY2.getText();
            int k1 = Integer.parseInt(kk1);
            int k2 = Integer.parseInt(kk2);
            ENCRYPTKEY1.setText("");
            ENCRYPTKEY2.setText("");
            decry_affine(k1,k2,TXT_INP);}

    }
    public void BF() {
        waitbutton.setVisible(true);
        //BRUTEFORCE();
        //waitbutton.setVisible(false);
    }
    public void BRUTEFORCE(){

        if(language.getValue()==null){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("You don't choose your language");
            alert.setContentText("Pres OK to continue and try again");
            alert.showAndWait();
        }

        else{

            OUTPUT.setText("");
            String TXT_INP = INPUT.getText();
            ENCRYPTKEY1.setText("");
            ENCRYPTKEY2.setText("");
            int indice = 0;
            if (language.getValue().equals("ENGLISH")) {
                indice = 2;

            }
            if (language.getValue().equals("FRENSH")) {
                indice = 1;

            }

            Brute_Force_affine(TXT_INP, indice);
            waitbutton.setVisible(false);
        }


        }


    public  void crypt_affine(int k1, int k2, String text) {
        StringBuilder outputc = new StringBuilder();

        String[] CODE = new String[26];
        int[] CODE1 = new int[26];

        CODE = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        CODE1 = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};

        String[] tab = text.split("");
        String[] tab1 = new String[text.length()];
        String[] tab2 = new String[text.length()];

        //int[] tab1 = new int[tab.length];
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (tab[i].equals(CODE[j])) {
                    tab1[i] = String.valueOf(CODE1[j]);
                }
            }
        }

        int fct = 0;

        System.out.print("\n");
        for (int i = 0; i < tab.length; i++) {

            fct = (Integer.valueOf(tab1[i]) * k1) + k2;
            fct = fct % 26;
            tab2[i] = CODE[fct];

            fct = 0;

        }
        System.out.print("\n");
        for (int j = 0; j < text.length(); j++) {

            //System.out.print(tab2[j]);
            //OUTPUT.setText(tab2[j]);
            outputc.append(tab2[j]);
        }

        //System.out.println(outputc);
        String outp = outputc.toString();
        System.out.println(outp);
        OUTPUT.setText(outp);


    }

    public  void decry_affine(int k1 , int k2 , String text) {

        String[] CODE = new String[26];
        int[] CODE1 = new int[26];
        StringBuilder outputc = new StringBuilder();

        CODE = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        CODE1 = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};


        String[] tab = text.split("");
        String[] tab1 = new String[text.length()];
        String[] tab2 = new String[text.length()];

        //int[] tab1 = new int[tab.length];
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (tab[i].equals(CODE[j])) {

                    tab1[i] = String.valueOf(CODE1[j]);
                }
            }
        }

        int fct = 0;
        //int counter = 0;
        System.out.print("\n");
        int k11 = 0;
        int product = 0;
        for (int i = 0; i < 26; i++) {

            product = k1 * i;
            if (product % 26 == 1) {
                k11 = i;
                break;
            }
        }
        for (int i = 0; i < tab.length; i++) {


            fct = k11 * (Integer.valueOf(tab1[i]) - k2);
            fct = fct % 26;

            if (fct < 0) {

                fct = fct + 26;

            }

            tab2[i] = CODE[fct];
            outputc.append(CODE[fct]);
        }
        System.out.print("\n");
        for (int j = 0; j < tab.length; j++) {

            //System.out.print(tab2[j]);
            //outputc.append(tab2[j]);
        }
        //System.out.println("\n");
        String outp = outputc.toString();
        System.out.println(outp);
        OUTPUT.setText(outp);
    }
    public  void Brute_Force_affine(String text , int language) {
        OUTPUT.setText("WAIT...");
        String[] CODE = new String[26];
        int[] CODE1 = new int[26];
        StringBuilder outpB = new StringBuilder();
        CODE = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        CODE1 = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};


        String[] tab = text.split("");
        String[] tab1 = new String[text.length()];
        String[] tab2 = new String[text.length()];

        //int[] tab1 = new int[tab.length];
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (tab[i].equals(CODE[j])) {

                    tab1[i] = String.valueOf(CODE1[j]);
                }
            }
        }
        System.out.print("\n");
        for (int j = 0; j < tab.length; j++) {

            System.out.print(tab1[j]);
        }

        int fct = 0;
        System.out.print("\n");
        int k11 = 0;
        //int k2=0;
        int product = 0;
        StringBuilder plaintext = new StringBuilder("");
        for(int k1=0 ; k1<26; k1++) {
            for (int k2 = 0; k2 < 26; k2++) {
                for (int i = 0; i < 26; i++) {

                    product = k1 * i;
                    if (product % 26 == 1) {
                        k11 = i;
                        // System.out.print("A="+k1+",");
                        //System.out.print("B="+k2+" ");
                        for (int j = 0; j < text.length(); j++) {

                            fct = k11 * (Integer.valueOf(tab1[j]) - k2);

                            fct = fct % 26;
                            // System.out.println(fct);
                            if (fct < 0) {

                                fct = fct + 26;
                            }


                            tab2[j] = CODE[fct];
                            //OUTPUT.setText(tab2[j]);
                            //plaintext.append(tab2[j]);
                            //System.out.print(CODE[fct]);

                        }
                        /*for (int p = 0; p < tab2.length; p++) {

                            System.out.print(tab2[p]);




                        }*/
                        System.out.println("\n");
                        for (int wrd = 3; wrd < 7; wrd++) {
                            if(wrd>tab2.length){
                                break;
                            }
                            dict_attack(tab2,k1,k2,wrd,language);
                        }



                    }


                }


            }

        }
        System.out.println(counter + " solutions found : ");
        System.out.println(output);
        // String tt=Integer.toString(counter);
        //OUTPUT.setText(tt);
        // OUTPUT.setText(output.toString());

        //show
    }
    //rllqqpxelrzetlmzjqzp
    public  int[] dict_attack( String[] cipher,int A , int B,int wrd , int language) {

        // The name of the file to open.
        String fileName="";
        if(language==1){
            fileName = "C:\\Users\\yama-\\Desktop\\AFFINE_CIPHER\\src\\sample\\Frensh.txt";
        }
        else {
            fileName = "C:\\Users\\yama-\\Desktop\\AFFINE_CIPHER\\src\\sample\\English.txt";
        }

        StringBuilder text1 = new StringBuilder();
        StringBuilder plaintext = new StringBuilder();
        String out="";
        String out1="";
        int[] coef_dic = new int[2];

        for (int i = 0; i < wrd; i++) {
            //if(wrd>text1.length()){break;}
            text1.append(cipher[i]);
        }
        for(int j =0 ; j<cipher.length ; j++){
            plaintext.append(cipher[j]);

        }
        //System.out.println(text1);
        String text2 = text1.toString();
        // This will reference one line at a time
        String line = null;

        try {

            FileReader fileReader =
                    new FileReader(fileName);


            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                if (text2.equals(line)) {
                    out = "The word found is  :" + text1 +" | in  KEY 1= "+A+" and KEY2 = "+B +" |";


                    System.out.println(out);
                    System.out.println("in A = " + A + " and B = " + B);
                    coef_dic[0] = A;
                    coef_dic[1] = B;
                    counter += 1;
                    output.append(out+"\n");
                    output.append(plaintext);
                    output.append("\n");
                    String outp = output.toString();
                    //System.out.println(outp);
                    OUTPUT.setText(outp);

                }

            }
            // System.out.println(counter);



            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");


        }

        return coef_dic;


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        language.setItems(list);
        waitbutton.setVisible(false);
    }
}