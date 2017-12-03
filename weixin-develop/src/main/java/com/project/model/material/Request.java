package com.project.model.material;

/**
 * Created by goforit on 2017/11/27.
 */
public class Request {

    private String type;
    private int offset;
    private int count;

    public enum TypeEnum{
        IMAGE("image"), VIDEO("video"),VOICE("voice"),NEWS("news");
        private String type;

        TypeEnum(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

    }

    public Request(TypeEnum type, int offset, int count) {
        this.type = type.getType();
        this.offset = offset;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
