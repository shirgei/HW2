import java.util.ArrayList;

public class Folder extends StorageItem {
    private ArrayList<StorageItem> folder;
    private File file;

    public Folder(String name) {
        super(name);
        this.folder = new ArrayList<>();
        //check what to do with folder and file
    }


    @Override
    public int getSize() {
        int totalSize = 0;
        for (int i = 0; i < folder.size(); i++) {
            totalSize += folder.get(i).getSize();
        }
        return totalSize;
    }

    public boolean addItem(StorageItem item){
        boolean add = true;
        for (int i = 0; i < folder.size(); i++){
            if(item.getName()==this.folder.get(i).getName()){
                return false;
            }
            if(folder.get(i) instanceof Folder){
                (Folder)this.folder.get(i).addItem(item);
            }
        }
    }
}

