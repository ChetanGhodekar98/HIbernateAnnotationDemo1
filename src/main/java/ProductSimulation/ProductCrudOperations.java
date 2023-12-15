package ProductSimulation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class ProductCrudOperations {
    private static Scanner scanner=new Scanner(System.in);
    static SessionFactory sessionFactory;

    static {
        Configuration cfg=new Configuration().configure().addAnnotatedClass(Product.class);
        sessionFactory=cfg.buildSessionFactory();
    }
    private static void operation(){
        boolean status=true;
        while (status) {
            System.out.println("1. ADD PRODUCT");
            System.out.println("2. UPDATE PRODUCT");
            System.out.println("3. DELETE PRODUCT");
            System.out.println("4. DISPLAY PRODUCT");
            System.out.println("5. EXIT");
            System.out.println("ENTER YOUR CHOICE");
            int choice= scanner.nextInt();
            switch (choice){
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    displayProduct();
                    break;
                case 5:
                    System.out.println("THANK U FOR VISITING !");
                    status=false;
                    sessionFactory.close();
                    break;
                default :
                    System.out.println("INVALID CHOICE");
                    break;
            }
        }
    }
    private static void addProduct(){
        System.out.println("ENTER PRODUCT ID");
        int id= scanner.nextInt();
        System.out.println("ENTER PRODUCT NAME");
        String name= scanner.next();
        System.out.println("ENTER PRODUCT PRICE");
        double price= scanner.nextDouble();
        System.out.println("ENTER PRODUCT CATEGORY");
        String cat= scanner.next();
        System.out.println("ENTER PRODUCT QTY");
        int qty= scanner.nextInt();

        Product p=new Product();
        p.setProductId(id);
        p.setProductName(name);
        p.setProductPrice(price);
        p.setProductCategory(cat);
        p.setProductQuantity(qty);

        Session session=sessionFactory.openSession();
        Transaction tx= session.beginTransaction();
        session.save(p);
        tx.commit();
        session.close();
        System.out.println("PRODUCT ADDED :)");
    }
    private static void updateProduct(){
        System.out.println("ENTER PRODUCT ID");
        int id= scanner.nextInt();
        System.out.println("ENTER PRODUCT PRICE");
        double price= scanner.nextDouble();

        Session session=sessionFactory.openSession();
        Product p1=session.get(Product.class,id);

        p1.setProductPrice(price);

        Transaction tx= session.beginTransaction();
        session.update(p1);
        tx.commit();
        session.close();
        System.out.println("PRODUCT DATA UPDATED SUCCESSFULLY");
    }
    private static void deleteProduct(){
        System.out.println("ENTER PRODUCT ID");
        int id= scanner.nextInt();

        Session session=sessionFactory.openSession();
        Product p1=session.get(Product.class,id);

        Transaction tx= session.beginTransaction();
        session.delete(p1);
        tx.commit();
        session.close();
        System.out.println("PRODUCT DATA DELETE SUCCESSFULLY");
    }

    private static void displayProduct(){
        System.out.println("ENTER PRODUCT ID");
        int id= scanner.nextInt();

        Session session=sessionFactory.openSession();
        Product p1=session.get(Product.class,id);

        System.out.println("PRODUCT ID->"+p1.getProductId());
        System.out.println("PRODUCT NAME ->"+p1.getProductName());
        System.out.println("PRODUCT PRICE ->"+p1.getProductPrice());
        System.out.println("PRODUCT CATEGORY ->"+p1.getProductCategory());
        System.out.println("PRODUCT QUANTITY ->"+p1.getProductQuantity());

        session.close();
    }
    public static void main(String[] args) {
        operation();
    }
}
