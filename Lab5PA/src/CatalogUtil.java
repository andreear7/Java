import java.awt.*;
import java.io.*;
import java.net.URI;

public class CatalogUtil {
    public static void save(Catalog catalog)
            throws IOException {
        try (var oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    public static Catalog load(String path) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(path);
        ObjectInputStream input = new ObjectInputStream(file);
        Catalog catalog = (Catalog) input.readObject();
        file.close();
        return catalog;
    }

    public static void view(Document doc) {
        try {
            Desktop desktop = Desktop.getDesktop();
            if (doc.getLocation().contains("http")) {
                URI uri = URI.create(doc.getLocation());
                desktop.browse(uri);
            } else {
                File file = new File(doc.getLocation());
                desktop.open(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

