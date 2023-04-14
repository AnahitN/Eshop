
import manager.CategoryManager;
import manager.ProductManager;
import model.Category;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class ProductCategoryMain {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductManager productManager = new ProductManager();
    private static CategoryManager categoryManager = new CategoryManager();

    public static void main(String[] args) {
        boolean isRun = true;

        while (isRun) {
            System.out.println("please input 0 for exit");
            System.out.println("please input 1 for add category");
            System.out.println("please input 2 for edit category by id");
            System.out.println("please input 3 for delete category by id");
            System.out.println("please input 4 for add product");
            System.out.println("please input 5 for edit product by id");
            System.out.println("please input 6 for delete product by id");
            System.out.println("please input 7 for print sum of products");
            System.out.println("please input 8 for print max price of product");
            System.out.println("please input 9 for print min price of product");
            System.out.println("please input 10 for print avg price of products");
            String command = scanner.nextLine();
            switch (command) {
                case "0":
                    isRun = false;
                    break;
                case "1":
                    addCategory();
                    break;
                case "2":
                    editCategoryById();
                    break;
                case "3":
                    deleteCategoryById();
                    break;
                case "4":
                    addProduct();
                    break;
                case "5":
                    editProductById();
                    break;
                case "6":
                    deleteProductById();
                    break;
                case "7":
                    sum();
                    break;
                case "8":
                    maxPrice();
                    break;
                case "9":
                    minPrice();
                    break;
                case "10":
                    avgPrice();
                    break;
                default:
                    System.out.println("wrong command");
            }

        }
    }

    private static void avgPrice() {
        List<Product> all = productManager.getAll();
        for (Product product : all) {
            System.out.println(product);
        }
        productManager.avgPriceOfProducts();
    }

    private static void minPrice() {
        List<Product> all = productManager.getAll();
        for (Product product : all) {
            System.out.println(product);
        }
        productManager.minPriceOfProduct();
    }

    private static void maxPrice() {
        List<Product> all = productManager.getAll();
        for (Product product : all) {
            System.out.println(product);
        }
        productManager.maxPriceOfProduct();
    }

    private static void sum() {
        List<Product> all = productManager.getAll();
        for (Product product : all) {
            System.out.println(product);
        }
        productManager.sumOfProducts();
    }


    private static void deleteProductById() {
        List<Product> all = productManager.getAll();
        for (Product product : all) {
            System.out.println(product);
        }
        System.out.println("please choose product id");
        int productId = Integer.parseInt(scanner.nextLine());
        productManager.removeById(productId);
        System.out.println("product deleted");
    }

    private static void editProductById() {
        List<Product> all = productManager.getAll();
        for (Product product : all) {
            System.out.println(product);
        }
        System.out.println("please choose product id");
        int id = Integer.parseInt(scanner.nextLine());
        if (productManager.getById(id) != null) {
            System.out.println("please input product name,description,price,quantity");
            String productStr = scanner.nextLine();
            String[] productData = productStr.split(",");
            Product product = new Product();
            product.setId(id);
            product.setName(productData[0]);
            product.setDescription(productData[1]);
            product.setPrice(Integer.parseInt(productData[2]));
            product.setQuantity(Integer.parseInt(productData[3]));
            productManager.update(product);
        }else {
            System.out.println("product does not exist");
        }
    }

    private static void addProduct() {
        List<Category> all = categoryManager.getAll();
        for (Category category : all) {
            System.out.println(category);
        }
        System.out.println("please choose category id");
        int id = Integer.parseInt(scanner.nextLine());
        Category category = categoryManager.getById(id);
        if (category != null) {
            System.out.println("please input product name,description,price,quantity");
            String productStr = scanner.nextLine();
            String[] productData = productStr.split(",");
            Product product = new Product();
            product.setName(productData[0]);
            product.setDescription(productData[1]);
            product.setPrice(Integer.parseInt(productData[2]));
            product.setQuantity(Integer.parseInt(productData[3]));
            product.setCategory(category);
            productManager.save(product);

        }
    }

    private static void deleteCategoryById() {
        List<Category> all = categoryManager.getAll();
        for (Category category : all) {
            System.out.println(category);
        }
        System.out.println("please choose category id");
        int id = Integer.parseInt(scanner.nextLine());
        if (categoryManager.getById(id) != null) {
            categoryManager.removeById(id);
            System.out.println("category deleted");
        } else {
            System.out.println("category does not exist");
        }
    }

    private static void editCategoryById() {
        List<Category> all = categoryManager.getAll();
        for (Category category : all) {
            System.out.println(category);
        }
        System.out.println("please choose category id");
        int id = Integer.parseInt(scanner.nextLine());
        if (categoryManager.getById(id) != null) {
            System.out.println("please input category name");
            String categoryName = scanner.nextLine();
            Category category = new Category();
            category = categoryManager.getById(id);
            category.setName(categoryName);
            categoryManager.update(category);
            System.out.println("category was edited");
        } else {
            System.out.println("category does not exist");
        }
    }

    private static void addCategory() {
        System.out.println("please input category name");
        String categoryName = scanner.nextLine();
        Category category = new Category();
        category.setName(categoryName);
        categoryManager.save(category);
    }

}
