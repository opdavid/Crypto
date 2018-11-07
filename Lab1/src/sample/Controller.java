package sample;

import com.google.common.collect.Lists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.domain.Matrix;
import sample.domain.MatrixElement;

import java.util.*;

public class Controller {

    private Map<Character,Integer> coding = new HashMap<>();
    private Integer dimension;

    private static final Integer n = 27;

    @FXML
    private TextField inputTextField;

    @FXML
    private Button helloButton;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private TextField dimensionTextField;

    @FXML
    private TextField matrixTextField;

    public Controller() {
    }

    private void initCoding(){
        coding.put(' ',0);
        int i = 1;
        char letter;
        for(letter='a'; letter<='z';letter++){
            coding.put(letter,i);
            i += 1;
        }
    }

    private Matrix initMatrix(Integer dimension,List<Integer> integers){
        Matrix matrix = new Matrix();
        this.dimension = dimension;
        int k = 0;
        for (int i=1;i<= dimension;i++){
            for (int j=1;j<=dimension;j++) {
                matrix.save(new MatrixElement<>(i, j, integers.get(k)));
                k++;
            }
        }
        return matrix;
    }

    private List<Integer> stringToCode(String input){
        List<Integer> codes = new ArrayList<>();
        int i=0;
        while (i<input.length()){
            codes.add(coding.get(input.charAt(i)));
            i++;
        }
        return codes;
    }

    private List<Integer> applyKey(List<Integer> codes, Matrix matrix){
        List<Integer> encoded = new ArrayList<>();
        List<List<Integer>> partition = Lists.partition(codes,dimension);

        for (List<Integer> list: partition){
            for (int i = 1; i<= dimension; i++){
                List<Integer> newList = list;
                int code = 0;
                for(int j = 1;j<=dimension;j++){
                    code = newList.get(j-1) * matrix.getValOnPos(j,i) + code;
                }
                encoded.add(code%n);
            }
        }
        return encoded;
    }

    private String codetoString (List<Integer> codes){
        StringBuilder s = new StringBuilder();
        for(Integer code:codes){
            s.append(Utils.getKeyFromValue(coding,code));
        }
        return s.toString();
    }

    public String encode(String input, Matrix matrix) throws Exception{
        try{
        if (Utils.validateInput(input) && Utils.validateMatrix(matrix)!= null){
            return codetoString(applyKey(stringToCode(input),matrix));
        }else {
            throw new Exception("error");
        }
        }catch (Exception e) {
            throw e;
        }
    }

    public String decode(String input, Matrix matrix) throws Exception{
        try {
            Matrix inversa = Utils.validateMatrix(matrix);

            System.out.println(inversa);
            if (Utils.validateInput(input) && inversa != null) {
                return codetoString(applyKey(stringToCode(input), inversa));
            } else {
                throw new Exception("error");
            }
        }catch (Exception e){
            throw e;
        }
    }

    public void encodeEvent(ActionEvent event){
        initCoding();
        List<Integer> l = new ArrayList<>();

        Matrix matrix = new Matrix();
        String input;

        try{
            String text = inputTextField.getText();
            Integer d = Integer.parseInt(dimensionTextField.getText());
            if(d != 2){
                throw new Exception("The dimension should be 2");
            }
            input = text;
            List<String> coordinates = Arrays.asList(matrixTextField.getText().split(","));
            System.out.println(coordinates);
            if (coordinates.size() != 4){
                throw new Exception("There is wrong number of matrix elements");
            }

            for (String s: coordinates){
                Integer i = Integer.parseInt(s);
                l.add(i);
            }

            matrix = initMatrix(d,l);

            System.out.println(input);
            System.out.println(matrix);
            String output = encode(input,matrix);
            System.out.println(output);
            outputTextArea.setText(output);

        }catch (Exception e){
            showError(e);
        }
    }

    private void showError(Exception e){
        System.out.println(e.getMessage());
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    public void decodeEvent(ActionEvent event){
        initCoding();
        List<Integer> l = new ArrayList<>();

        Matrix matrix = new Matrix();
        String input;

        try{
            String text = outputTextArea.getText();
            Integer d = Integer.parseInt(dimensionTextField.getText());
            if(d != 2){
                throw new Exception("The dimension should be 2");
            }
            input = text;
            List<String> coordinates = Arrays.asList(matrixTextField.getText().split(","));
            System.out.println(coordinates);
            if (coordinates.size() != 4){
                throw new Exception("There is wrong number of matrix elements");
            }

            for (String s: coordinates){
                Integer i = Integer.parseInt(s);
                l.add(i);
            }

            matrix = initMatrix(d,l);
            String output = decode(input,matrix);
            inputTextField.setText(input);
            System.out.println(input);
            System.out.println(output);
            outputTextArea.setText(output);
        }catch (Exception e){
            showError(e);
        }
    }
}
