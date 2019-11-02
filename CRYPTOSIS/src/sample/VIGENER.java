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
import sun.java2d.pipe.OutlineTextRenderer;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class VIGENER implements Initializable {

    static StringBuilder output=new StringBuilder();
    @FXML
    private javafx.scene.control.TextArea INPUT;
    @FXML
    private javafx.scene.control.TextArea OUTPUT;
    @FXML
    private javafx.scene.control.TextField ENCRYPTKEY1;

    @FXML private javafx.scene.control.Button closeButton;
    @FXML private javafx.scene.control.Label backButton;
    @FXML private javafx.scene.control.Button chooseButton;
    @FXML private ComboBox<String> language;
    ObservableList<String> list = FXCollections.observableArrayList("ENGLISH","FRENSH");

    public static void start2() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(AFFINE.class.getResource("vigener.fxml"));
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
            System.out.println(
                    "Unable to open file '");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void ENCRYPT() {
        if(ENCRYPTKEY1.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("You don't insert your KEY WORD");
            alert.setContentText("Pres OK to continue");
            alert.showAndWait();
        }
        else{

        OUTPUT.setText("");
        String TXT_INP = INPUT.getText();
        //OUTPUT.setText(TXT_INP);
        String kk1 = ENCRYPTKEY1.getText();
        ENCRYPTKEY1.setText("");
        crypt(TXT_INP,kk1);}
    }

    public void DECRYPT() {
        if(ENCRYPTKEY1.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("You don't insert your KEY WORD");
            alert.setContentText("Pres OK to continue");
            alert.showAndWait();
        }
        else{

            OUTPUT.setText("");
            String TXT_INP = INPUT.getText();
            String kk1 = ENCRYPTKEY1.getText();
            ENCRYPTKEY1.setText("");
            decrypt(TXT_INP,kk1);}

        }
    public void BRUTEFORCE(){

            OUTPUT.setText("");
            String TXT_INP = INPUT.getText();
            OUTPUT.setText("SOON...");
            //sequence(TXT_INP);
        }

    public  void crypt(String text , String key){

        String[] CODE = new String[26];
        int[] CODE1 = new int[26];
        StringBuilder cipher = new StringBuilder();

        CODE = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        CODE1 = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};

        String[] tab_text = new String[text.length()];
        int[] tab1_text = new int[text.length()];
        String[] tab_key = new String[text.length()];
        int[] tab1_key = new int[key.length()];
        int[] tab_add = new int[text.length()];



        tab_text=text.split("");
        tab_key=key.split("");

        for(int i = 0 ; i<tab_text.length ; i++) {
            for (int j = 0; j < CODE.length; j++) {

                if (tab_text[i].equals(CODE[j])) {

                    tab1_text[i] = CODE1[j];
                }
            }
        }
        for(int i = 0 ; i<tab_key.length ; i++){
            for(int j =0 ; j < CODE.length; j++){

                if(tab_key[i].equals(CODE[j])){

                    tab1_key[i]=CODE1[j];
                }
            }
        }
        int M=0 ;
        int N=0 ;
        for(int i = 0 ; i<tab1_text.length ; i++){

            if(N>tab1_key.length-1){
                N=0;
            }
            if(M>=tab1_text.length){
                break;
            }


            tab_add[i]=tab1_text[M]+tab1_key[N];
            if(tab_add[i]>25){

                tab_add[i]=tab_add[i]-26;
            }
            M++;N++;


        }
        /*for(int i =0 ; i<tab_add.length  ; i++){
            System.out.print(tab_add[i]);
        }*/
        for(int i = 0 ; i<tab_add.length ; i++){
            for(int j =0 ; j < CODE.length; j++){

                if(tab_add[i]==CODE1[j]){

                    cipher.append(CODE[j]);
                }
            }


        }


        System.out.print(cipher);
        OUTPUT.setText(cipher.toString());

    }

    public void decrypt(String text , String key){



        String[] CODE = new String[26];
        int[] CODE1 = new int[26];
        StringBuilder cipher = new StringBuilder();

        CODE = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        CODE1 = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};

        String[] tab_text = new String[text.length()];
        int[] tab1_text = new int[text.length()];
        String[] tab_key = new String[text.length()];
        int[] tab1_key = new int[key.length()];
        int[] tab_add = new int[text.length()];



        tab_text=text.split("");
        tab_key=key.split("");

        for(int i = 0 ; i<tab_text.length ; i++){
            for(int j =0 ; j < CODE.length; j++){

                if(tab_text[i].equals(CODE[j])){

                    tab1_text[i]=CODE1[j];
                }
            }


        }
        for(int i = 0 ; i<tab_key.length ; i++){
            for(int j =0 ; j < CODE.length; j++){

                if(tab_key[i].equals(CODE[j])){

                    tab1_key[i]=CODE1[j];
                }
            }


        }
        int M=0 ;
        int N=0 ;
        for(int i = 0 ; i<tab1_text.length ; i++){

            if(N>tab1_key.length-1){
                N=0;
            }
            if(M>=tab1_text.length){
                break;
            }


            tab_add[i]=tab1_text[M]-tab1_key[N];
            if(tab_add[i]<0){

                tab_add[i]=tab_add[i]+26;
            }
            M++;N++;


        }
        /*for(int i =0 ; i<tab_add.length  ; i++){
            System.out.print(tab_add[i]);
        }*/
        for(int i = 0 ; i<tab_add.length ; i++){
            for(int j =0 ; j < CODE.length; j++){

                if(tab_add[i]==CODE1[j]){

                    cipher.append(CODE[j]);
                }
            }


        }


        System.out.print(cipher);
        OUTPUT.setText(cipher.toString());


    }


    public void sequence(String text) {

        String[] tab_cipher = new String[text.length()];
        tab_cipher = text.split("");
        StringBuilder seq = new StringBuilder();
        StringBuilder position = new StringBuilder();
        String[] seq1 = new String[text.length()];
        StringBuilder numbers = new StringBuilder();

        for (int i = 0; i + 2 < tab_cipher.length; i++) {
            for (int j = i + 2; j + 2 < tab_cipher.length; j++) {
               /* if(i+1>tab_cipher.length || i+2>tab_cipher.length || j+2>tab_cipher.length ||  j+1>tab_cipher.length  ){
                    break;
                }*/

                if (tab_cipher[i].equals(tab_cipher[j]) && tab_cipher[i + 1].equals(tab_cipher[j + 1]) && tab_cipher[i + 2].equals(tab_cipher[j + 2])) {

                    //System.out.println(tab_cipher[j]+tab_cipher[j+1]+tab_cipher[j+2]);
                    seq.append(tab_cipher[i] + tab_cipher[i + 1] + tab_cipher[i + 2] + " " + (j - i) + "\n");
                    numbers.append((j - i) + ",");
                    //seq.append(j-i+"\n");


                }
            }
        }
        System.out.print(seq);
        //multiple(numbers);
        String[] tab1 = new String[tab_cipher.length];
        tab1 = text.split("");
        StringBuilder ttt = new StringBuilder();
        String[] tab2 = new String[tab1.length / 4];
        tab2=text.split("",4);
        int i=0 ;
        while(i<tab1.length){

            for(int j=i ; j<i+4 ; j++){

                tab2[i]=tab1[j]+tab1[j+1]+tab1[j+2]+tab1[j+3];
            }
            i=+4;

        }

        for(int l = 0 ; l<tab2.length ; l++){

            System.out.println(tab2[l]);
            OUTPUT.setText(tab2[l]);
        }
        /*int j=0;
        while(j<tab1.length-1){

            for (int i = 0 ; i < j ; i++) {

                test = tab1[i] + tab1[i + 1] + tab1[i + 2] + tab1[i + 3];

            }
            j+=4;
            System.out.println(test);
        }*/
    }


    //System.out.print(position);

    public void  multiple (StringBuilder num){

        //System.out.println(num);
        String num1 = num.toString();
        //System.out.println(num1);
        String[] tab;
        tab=num1.split(",");
        /*for(int i =0 ; i<tab.length ; i++){

            System.out.println(tab[i]);
        }*/
        String[][] res = new String[tab.length][];
        for(int i=0 ; i<tab.length ; i++){
            System.out.println("----------------------------");

            for(int j=2; j<20 ; j++){

                if(Integer.valueOf(tab[i])%j==0){

                    //res[i][j] = j+" *";
                    System.out.println(j+" is div for "+tab[i]);
                    OUTPUT.setText(j+" is div for "+tab[i]);

                }
            }
        }

        /*for(int i=0 ; i<tab.length ; i++){



                System.out.println(res[i][j]+" ");

            }
            System.out.println();
        }*/

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
