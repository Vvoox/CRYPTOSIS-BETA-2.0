package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CEASAR implements Initializable {

    String inpt = "";

    private void closelastButtonAction() {

        Stage stage = (Stage) closeButton.getScene().getWindow();

        stage.close();

    }

    @FXML
    private javafx.scene.control.TextArea INPUT;
    @FXML
    private javafx.scene.control.TextArea OUTPUT;
    @FXML
    private javafx.scene.control.TextField ENCRYPTKEY1;

    @FXML
    private javafx.scene.control.Button closeButton;
    @FXML
    private javafx.scene.control.Label backButton;
    @FXML
    private javafx.scene.control.Button chooseButton;

    public static void start3() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(AFFINE.class.getResource("ceasar.fxml"));
        Image icon = new Image(AFFINE.class.getResourceAsStream("Images/1.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("CRYPTOSIS");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
    }

    public void closeButtonAction() {

        Stage stage = (Stage) closeButton.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warnning");
        alert.setHeaderText("Look you want to close the program !");
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            stage.close();
        } else {
            alert.close();

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        String lines = "";
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


        if (!ENCRYPTKEY1.getText().equals("") && !INPUT.getText().equals("")) {
            inpt = INPUT.getText();
            int key = Integer.parseInt(ENCRYPTKEY1.getText());
            ENC(inpt, key);

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("You miss insert your KEY");
            alert.setContentText("Pres OK to continue and try again");
            alert.showAndWait();
        }


    }

    public void DECRYPT() {
        if (!ENCRYPTKEY1.getText().equals("") && !INPUT.getText().equals("")) {
            inpt = INPUT.getText();
            int key = Integer.parseInt(ENCRYPTKEY1.getText());
            DEC(inpt, key);
            System.out.println(inpt + " " + key);

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("You miss insert your KEY");
            alert.setContentText("Pres OK to continue and try again");
            alert.showAndWait();
        }

    }

    public void BRUTEFORCE() {

        if (!INPUT.getText().equals("")) {
            inpt = INPUT.getText();
            BF(inpt);


        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Insert your input first");
            alert.setContentText("Pres OK to continue and try again");
            alert.showAndWait();
        }

    }



    String[] CODE = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public void ENC(String text, int k1) {
        StringBuilder outp = new StringBuilder();

        String[] tab = text.split("");
        int[] tab1 = new int[tab.length];
        for (int i = 0; i < tab.length; i++) {

            for (int j = 0; j < CODE.length; j++) {


                if (tab[i].equals(CODE[j])) {

                    if ((j + k1) < 25) {

                        tab1[i] = j + k1;
                    }
                    if ((j + k1 > 25)) {
                        tab1[i] = j + (k1 - 25);
                    }

                }
            }
        }

        for (int i = 0; i < tab.length; i++) {

            tab[i] = CODE[tab1[i]];
            outp.append(tab[i]);
        }
        OUTPUT.setText(outp.toString());


    }

    public void DEC(String text, int k1) {

        StringBuilder outp = new StringBuilder();

        String[] tab = text.split("");
        int[] tab1 = new int[tab.length];
        for (int i = 0; i < tab.length; i++) {

            for (int j = 0; j < CODE.length; j++) {


                if (tab[i].equals(CODE[j])) {

                    if ((j - k1) >= 0) {

                        tab1[i] = j - k1;
                    }
                    if ((j - k1) < 0) {
                        tab1[i] = 25 - (k1 - j-1);
                    }

                }
            }
        }

        for (int i = 0; i < tab.length; i++) {

            tab[i] = CODE[tab1[i]];
            outp.append(tab[i]);
        }
        OUTPUT.setText(outp.toString());
    }
    public void BF(String text) {

        StringBuilder outp = new StringBuilder();
        String[] tab = text.split("");
        int[] tab1 = new int[tab.length];
        int key[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
        int count = 0;
        for (int c = 0; c < key.length; c++) {

        for (int i = 0; i < tab.length; i++) {

            for (int j = 0; j < CODE.length; j++) {

                if (tab[i].equals(CODE[j])) {


                    if ((j - key[c]) > 0) {

                        tab1[i] = j - key[c];

                    }
                    if ((j - key[c]) <= 0) {
                        tab1[i] = 24 - (key[c] - j);

                    }


                }

            }
        }
        outp.append('"');
            for (int i = 0; i < tab.length; i++) {

                tab[i] = CODE[tab1[i]];
                outp.append(tab[i]);
            }
            outp.append('"');
            outp.append(" with the key : " +c);
            outp.append("\n");
            OUTPUT.setText(outp.toString());
            //OUTPUT.setText("\n");

    }

    }
}

