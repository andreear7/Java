import java.io.IOException;
public class Lab5PA {
    public static void main(String[] args) {
        Lab5PA app = new Lab5PA();
        app.testCreateSave();
        app.testLoadView();
    }
    private void testCreateSave() {
        Catalog catalog =
                new Catalog("Java Resources", "C:\\Users\\Dumitrascu\\Desktop\\r.txt");
        Document doc = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        doc.addTag("type", "Slides");

        catalog.add(doc);
        try {
            CatalogUtil.save(catalog);
        } catch (IOException e) {
            System.out.println("IO exception!");
            System.err.println(e);
        }
    }

    private void testLoadView() {
        try {
            Catalog catalog = CatalogUtil.load("C:\\Users\\Dumitrascu\\Desktop\\r.txt");
            //Document doc=new Document("doc","Document","https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
            //Document doc1=new Document("doc1","Document1","C:\\Users\\Dumitrascu\\Desktop\\r.txt");
            Document doc = catalog.findById("java1");
            CatalogUtil.view(doc);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Document doc1 = new Document("doc1", "Document1", "C:\\Users\\Dumitrascu\\Desktop\\r.txt");
        //CatalogUtil.view(doc);
        CatalogUtil.view(doc1);
    }

}
