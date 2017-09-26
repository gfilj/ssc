package com.project.webdriver.model;

/**
 * Created by goforit on 2017/9/10.
 */
public class PdfImgLocation implements Comparable<PdfImgLocation>{
    private float position;
    private float length;
    private float endPosition;

    public float getPosition() {
        return position;
    }

    public void setPosition(float position) {
        this.position = position;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public PdfImgLocation(float position, float length) {
        this.position = position;
        this.length = length;
    }

    public float getEndPosition() {
        setEndPosition(position+length);
        return endPosition;
    }

    public void setEndPosition(float endPosition) {
        this.endPosition = endPosition;
    }

    @Override
    public int compareTo(PdfImgLocation o) {
        if(position>o.getPosition()){
            return -1;
        }else if(position<o.getPosition()){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return "PdfImgLocation{" +
                "position=" + position +
                ", length=" + length +
                ", endPosition=" + endPosition +
                '}';
    }
}
