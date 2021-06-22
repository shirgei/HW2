import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public abstract class StorageItem {
    private String name;
    private Date date;
    private int size;
    private static int numOfFiles = 0;


    public StorageItem(String name, int size) {
        this.name = name;
        this.size = size;
        this.date.setTime(Main.rnd.nextLong());
    }

    public long dateToMillis(String strDate) {
        long milliseconds = 0;
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = dateFormat.parse(strDate);
            milliseconds = date.getTime();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return milliseconds;
    }

    private long abs(long number) {
        if(number < 0)
            return -number;
        return number;
    }



    public StorageItem(String name){
        this.name = name;
        long startDate = dateToMillis("2017/01/01 00:00:00");
        long endDate = dateToMillis("2021/12/31 23:59:59");
        long rndLong =
                (abs(Main.rnd.nextLong() % (endDate - startDate)) + startDate);
        this.date = new Timestamp(rndLong);
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

    private int countAllFiles(Folder folder) {
        int count = 0;
        for(StorageItem item :folder.getFolder()) {
            if(item instanceof File)
                count++;
            else
                count += countAllFiles((Folder) item);
        }
        return count;
    }

    public void printTree(SortingField field) {
        if (this instanceof File) {
            System.out.println(this.getName());
        }
        sortExternalFolder((Folder) this, field);
        int indent = 0;
        numOfFiles = 0;
        int allFiles = this.countAllFiles((Folder)this);
        StringBuilder printString = new StringBuilder();
        printDirectoryTree((Folder)this, indent, printString, field,
                allFiles);
        System.out.println(printString);

    }


    private void sortExternalFolder(Folder folder, SortingField field)
    {
        sortFolder(field, folder.getFolder());
    }


    private static String getIndentString(int indent) {
        StringBuilder printString = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            printString.append("|    ");
        }
        return printString.toString();
    }

    private static void printFile(File file, int indent,
                                  StringBuilder printString) {
        printString.append(getIndentString(indent));
        printString.append(file.getName());
    }


    public void printDirectoryTree(Folder folder, int indent,
                                   StringBuilder printString,
                                   SortingField field, int allFiles) {

        printString.append(getIndentString(indent));
        printString.append(folder.getName());
        printString.append("\n");
        for(int i = 0; i < folder.getFolder().size(); i++) {
            if(folder.getFolder().get(i) instanceof Folder) {
                sortFolder(field,
                        ((Folder) folder.getFolder().get(i)).getFolder());
                printDirectoryTree((Folder) folder.getFolder().get(i),
                        indent + 1, printString, field, allFiles);
            }
            else {
                printFile((File) folder.getFolder().get(i),
                        indent + 1, printString);
               numOfFiles++;
                if(numOfFiles < allFiles)
                    printString.append("\n");
            }

        }
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
            case "DATE":
                Comparator<StorageItem> compareDate = Comparator.comparing((StorageItem::getDate));
                Comparator<StorageItem> completeCompareDate = compareDate.thenComparing(compareName);
                folder.sort(completeCompareDate);
                break;
            default:
                break;
        }

 }



}