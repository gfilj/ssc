package com.project.pdf.pdfbox;

/**
 * Created by goforit on 2017/9/23.
 */
public class TextPositionKey {

    private int positionY;
    private int height;

    public TextPositionKey(int positionY, int height) {
        this.positionY = positionY;
        this.height = height;
    }

    public int getEndY(){
        return positionY+height + 5;
    }

    public int getPositionY() {
        return positionY-5;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TextPositionKey){
            TextPositionKey key = (TextPositionKey) obj;
            if(getPositionY()>=key.getEndY()){
                return false;
            }
            if(getEndY()<=key.getPositionY()){
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "TextPositionKey{" +
                "positionY=" + positionY +
                ", height=" + height +
                '}';
    }
}
