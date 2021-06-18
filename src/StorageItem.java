import java.util.Date;

public abstract class StorageItem {
    private String name;
    private Date date;
    private int size;

    public StorageItem(String name, int size) {
        this.name = name;
        this.size = size;
        this.date.setTime(Main.rnd.nextLong()); //check how to print
    }

    public StorageItem(String name){
        this.name = name;
        this.size = 0;
        this.date.setTime(Main.rnd.nextLong());
    }

    public Date getDate() {
        return this.date;
    }

    public String getName() {
        return this.name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public abstract int getSize();

    public void printTree(SortingField field){

    }
}