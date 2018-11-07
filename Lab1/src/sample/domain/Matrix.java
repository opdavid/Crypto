package sample.domain;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

    private List<MatrixElement<Integer>> matrix = new ArrayList<>();

    public Matrix(List<MatrixElement<Integer>> matrix) {
        this.matrix = matrix;
    }

    public Matrix() {
    }

    public void save(MatrixElement matrixElement){
        this.matrix.add(matrixElement);
    }

    public Integer getValOnPos(Integer pos1,Integer pos2){
        for (MatrixElement<Integer> element:matrix){
            if (element.getPosX().equals(pos1) && element.getPosY().equals(pos2))
                return element.getValue();
        }
        return -1;
    }

    public void setValOnPos(Integer pos1,Integer pos2,Integer value){
        this.save(new MatrixElement(pos1,pos2,value));
    }

    public Matrix transpusa(){
        Matrix t = new Matrix();
        t.setValOnPos(1,1,this.getValOnPos(1,1));
        t.setValOnPos(1,2,this.getValOnPos(2,1));
        t.setValOnPos(2,1,this.getValOnPos(1,2));
        t.setValOnPos(2,2,this.getValOnPos(2,2));
        return t;
    }

    public List<MatrixElement<Integer>> getMatrix() {
        return matrix;
    }

    public void setMatrix(List<MatrixElement<Integer>> matrix) {
        this.matrix = matrix;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "matrix=" + matrix +
                '}';
    }
}
