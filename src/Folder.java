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
        String tempath = "";
        int counter = 0;
        boolean flage = false;
        while (counter != path.length()){
            if (path.charAt(counter) == '/' || path.length() == counter){
                for (int j= 0 ; j< folder.size(); j++) {
                    if (tempath.equals(folder.get(j).getName())) {
                        flage = true;

                        if (folder.get(j) instanceof Folder && flage == true) {
                            Folder folder1 = (Folder) folder.get(j);
                            return folder1.findFile(path.substring(counter+1));
                        }
                        if (folder.get(j) instanceof File && tempath.equals(folder.get(j).getName())) {
                            return (File) folder.get(j);
                        } else {
                            return null;
                        }
                    }
                    }

                }
            else{
                tempath+= path.charAt(counter);
            }
            counter++;
        }
        for (int k= 0; k< folder.size(); k++){
            if (tempath.equals(folder.get(k).getName())){
                return (File) folder.get(k);
            }
        }

        return null;
            }


        }


