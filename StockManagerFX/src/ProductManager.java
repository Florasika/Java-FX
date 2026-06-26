import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class ProductManager {

    private ObservableList<Product> products =
            FXCollections.observableArrayList();

    private final String FILE_NAME = "stock.csv";

    public ObservableList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
        saveProducts();
    }

    public void deleteProduct(Product product) {
        products.remove(product);
        saveProducts();
    }

    public void saveProducts() {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(FILE_NAME))) {

            for(Product p : products){

                writer.write(
                        p.getId() + "," +
                        p.getName() + "," +
                        p.getQuantity() + "," +
                        p.getPrice()
                );

                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadProducts() {

        File file = new File(FILE_NAME);

        if(!file.exists()) return;

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(FILE_NAME))) {

            String line;

            while((line = reader.readLine()) != null){

                String[] data = line.split(",");

                products.add(
                        new Product(
                                Integer.parseInt(data[0]),
                                data[1],
                                Integer.parseInt(data[2]),
                                Double.parseDouble(data[3])
                        )
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}