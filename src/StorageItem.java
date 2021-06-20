import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public abstract class StorageItem implements Comparator<StorageItem> {
    private String name;
    private Date date;
    private int size;
    private ArrayList<StorageItem> folder;

    public StorageItem(String name, int size) {
        this.name = name;
        this.size = size;
        this.date.setTime(Main.rnd.nextLong()); //check how to print
    }

    public StorageItem(String name){
        this.name = name;
        this.size = 0;
        this.date.setTime(Main.rnd.nextLong());
        this.folder = new ArrayList<>();

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
        for(int i=0; i<

    }

 private void sortFolder(SortingField field, ArrayList<StorageItem> folder){
        Comparator<StorageItem> compareName = Comparator.comparing((StorageItem::getName));
        switch(field.toString()){
            case "NAME":
                folder.sort(compareName);
                break;
            case "SIZE":
                Comparator<StorageItem> compareSize = Comparator.comparing((StorageItem::getSize));
                Comparator<StorageItem> completeCompareSize = compareSize.thenComparing(compareName);
                folder.sort(completeCompareSize);
                break;
            case "Date":
                Comparator<StorageItem> compareDate = Comparator.comparing((StorageItem::getDate));
                Comparator<StorageItem> completeCompareDate = compareDate.thenComparing(compareName);
                folder.sort(completeCompareDate);
                break;
            default:
                break;
        }

 }



}