import java.util.ArrayList;

public class Folder extends StorageItem {
    private ArrayList<Folder> folder;
    private File file;

    public Folder(String name) {
        super(name);
        this.folder = new ArrayList<Folder>();
        //check what to do with folder and file
    }

    public int getSize() {
        int totalSize = 0;
        for (int i = 0; i < this.folder.size(); i++) {
            if (this.folder.get(i) instanceof File) {
                totalSize += this.folder[i].getSize();
            }
        }

    }
}
