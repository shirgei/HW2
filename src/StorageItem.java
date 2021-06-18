import java.util.Date;

public class StorageItem {
    private String name;
    private Date date;
    private int size;

    public StorageItem(String name,  int size){
        this.name=name;
        this.date = date;
        this.size=size;
        this.date.setTime(long(Main.rnd));

    }
}
