import java.util.ArrayList;

public class Folder extends StorageItem {
    private File file;
    private ArrayList<StorageItem> folder;


    public Folder(String name) {
        super(name);
        this.folder = new ArrayList<>();
    }


    public ArrayList<StorageItem> getFolder() {
        return folder;
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (int i = 0; i < folder.size(); i++) {
            totalSize += folder.get(i).getSize();
        }
        return totalSize;
    }

    public boolean addItem(StorageItem item) {
        boolean add = true;
        for (int i = 0; i < folder.size(); i++) {
            if (item.getName() == this.folder.get(i).getName()) {
                return false;
            }
        }
        this.folder.add(item);
        return true;
    }

    public File findFile(String path) {  //probably doesnt work
        String tempPath = "";
        int counter = 0;
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == '/') {
                for (int j = 0; j < folder.size(); j++) {
                    if (tempPath.equals(folder.get(j).getName())) {
                        if (folder.get(j) instanceof Folder) {
                            Folder folder1 = (Folder) folder.get(j);
                            folder1.findFile(path.substring(counter));
                        }
                    }
                }
            } else {
                tempPath += path.charAt(i);
                counter++;
            }
        }
        for (int i = 0; i < folder.size(); i++) {
            if (path.equals(folder.get(i).getName())) {
                return (File) folder.get(i);
            }
        }
        return null;
    }
}
