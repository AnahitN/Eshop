package manager;

import db.DBConnectionProvider;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {

    private static Connection connection = DBConnectionProvider.getInstance().getConnection();
    private CategoryManager categoryManager = new CategoryManager();

    public void save(Product product) {
        String sql = "INSERT INTO product(name,description,price,quantity,category_id) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setInt(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCategory().getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getInt(1));
            }
            System.out.println("product added into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(Product product) {
        String sql = "UPDATE product SET name = ?,description = ?,price = ?,quantity = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,product.getName());
            ps.setString(2,product.getDescription());
            ps.setInt(3,product.getPrice());
            ps.setInt(4,product.getQuantity());
            ps.setInt(5,product.getId());
            ps.executeUpdate();
            System.out.println("product was updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
String sql = "Select * from product";
        try {PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery("Select * from product");
            while (resultSet.next()) {
                products.add(getProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private Product getProductFromResultSet(ResultSet resultSet) {
        Product product = new Product();
        try {
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setDescription(resultSet.getString("description"));
            product.setPrice(resultSet.getInt("price"));
            product.setQuantity(resultSet.getInt("quantity"));
            int category_id = resultSet.getInt("category_id");
            product.setCategory(categoryManager.getById(category_id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public Product getById(int id) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("Select * from product where id = " + id);
            if (resultSet.next()) {
                return getProductFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeById(int productId) {
        String sql = "DELETE FROM product WHERE id = " + productId;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sumOfProducts() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(id) FROM product;");
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                System.out.println("sum of products is: " + count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void maxPriceOfProduct() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT MAX(price) FROM product;");
            if (resultSet.next()) {
                int max = resultSet.getInt(1);
                System.out.println("Max price of product is: " + max);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void minPriceOfProduct() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT MIN(price) FROM product;");
            if (resultSet.next()) {
                int min = resultSet.getInt(1);
                System.out.println("Min. price of product is: " + min);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void avgPriceOfProducts() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT AVG(price) FROM product;");
            if (resultSet.next()) {
                int avg = resultSet.getInt(1);
                System.out.println("average price of products is: " + avg);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
