package enums;

/**
 * Created by Sinerak on 25/06/2017.
 */
public enum FileType {

    PNG(".png"),
    BMP(".bmp");

    private final String type;

    private FileType(String type){
        this.type = type;
    }

    public String getSuffix() {
        return type;
    }
}
