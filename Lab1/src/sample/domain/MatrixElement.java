package sample.domain;

public class MatrixElement<K> {
    private Integer posX;
    private Integer posY;
    private K value;

    public MatrixElement(Integer posX, Integer posY, K value) {
        this.posX = posX;
        this.posY = posY;
        this.value = value;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public K getValue() {
        return value;
    }

    public void setValue(K value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MatrixElement{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", value=" + value +
                '}';
    }
}
