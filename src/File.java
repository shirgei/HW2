public class File extends StorageItem {
    private String ext;
    private String content;

    public File(String name, String ext, int size) {
        super(name, size);
        this.ext = ext;
    }

    public File(String name, String ext) {
        super(name);
        this.ext = ext;
    }

    public String getName() {
        return super.getName() + "." + this.ext;
    }

    public int getSize() {
        int counter = 0;
        if (this.content == null) {
            return counter;
        } else {
            for (int i = 0; i < content.length(); i++) {
                counter++;
            }
        }
        return counter;
    }

    public void addContent(String contentToAdd) {
        this.content = this.content + contentToAdd;
    }

    public void printContent() {
        System.out.println(getName() + " Size: " + getSize() + " Created: " + getDate());
        System.out.println(this.content);
    }
}
