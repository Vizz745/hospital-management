import java.util.*;

class Dish {
    private String name;
    private double price;

    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Order {
    private List<Dish> dishes;

    public Order() {
        dishes = new ArrayList<>();
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public double calculateTotal() {
        double total = 0;
        for (Dish dish : dishes) {
            total += dish.getPrice();
        }
        return total;
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}

class Customer {
    private String name;
    private Order currentOrder;

    public Customer(String name) {
        this.name = name;
        this.currentOrder = new Order();
    }

    public String getName() {
        return name;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void addDishToOrder(Dish dish) {
        currentOrder.addDish(dish);
    }

    public double calculateTotalBill() {
        return currentOrder.calculateTotal();
    }
}

public class RestaurantManagementSystem {
    private static List<Dish> menu = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Initialize the menu
    public static void initializeMenu() {
        menu.add(new Dish("Pizza", 10.50));
        menu.add(new Dish("Burger", 5.75));
        menu.add(new Dish("Pasta", 8.25));
        menu.add(new Dish("Salad", 4.00));
        menu.add(new Dish("Soda", 2.50));
    }

    // Display menu items
    public static void displayMenu() {
        System.out.println("----- Menu -----");
        for (int i = 0; i < menu.size(); i++) {
            Dish dish = menu.get(i);
            System.out.println((i + 1) + ". " + dish.getName() + " - $" + dish.getPrice());
        }
        System.out.println("----------------");
    }

    // Create new customer
    public static Customer createCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        Customer customer = new Customer(name);
        customers.add(customer);
        return customer;
    }

    // Add dish to customer's order
    public static void addDishToOrder(Customer customer) {
        displayMenu();
        System.out.print("Enter dish number to add to order: ");
        int dishNumber = scanner.nextInt();
        scanner.nextLine();  // Consume newline character

        if (dishNumber < 1 || dishNumber > menu.size()) {
            System.out.println("Invalid dish number.");
        } else {
            Dish selectedDish = menu.get(dishNumber - 1);
            customer.addDishToOrder(selectedDish);
            System.out.println(selectedDish.getName() + " added to the order.");
        }
    }

    // Display all customers' orders and bills
    public static void displayBills() {
        for (Customer customer : customers) {
            System.out.println("\n----- Order for " + customer.getName() + " -----");
            double total = customer.calculateTotalBill();
            for (Dish dish : customer.getCurrentOrder().getDishes()) {
                System.out.println(dish.getName() + " - $" + dish.getPrice());
            }
            System.out.println("Total Bill: $" + total);
        }
    }

    // Display menu options
    public static void displayMenuOptions() {
        System.out.println("\n----- Restaurant Management System -----");
        System.out.println("1. Create Customer");
        System.out.println("2. Add Dish to Order");
        System.out.println("3. Display Bills");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {
        initializeMenu();
        boolean exit = false;

        while (!exit) {
            displayMenuOptions();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    createCustomer();
                    break;
                case 2:
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    Customer customer = null;
                    for (Customer c : customers) {
                        if (c.getName().equalsIgnoreCase(customerName)) {
                            customer = c;
                            break;
                        }
                    }
                    if (customer == null) {
                        System.out.println("Customer not found!");
                    } else {
                        addDishToOrder(customer);
                    }
                    break;
                case 3:
                    displayBills();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
