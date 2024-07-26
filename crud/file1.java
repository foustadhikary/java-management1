import java.util.Scanner;
import java.util.Random;
import java.util.AbstractMap;
import java.util.Vector;
import java.util.Map;

class Gym {
    String name;
    int age;
    int userId;

    // Constructor with parameters for name, age, and userId
    public Gym(String name, int age, int userId) {
        this.name = name;
        this.age = age;
        this.userId = userId;
    }

    public void welcomeMessage() {
        System.out.println("Welcome " + name + " to our community! Your userId is " + userId);
    }
}

class AddDetails extends Gym {
    protected static Vector<Map.Entry<String, Integer>> vector = new Vector<>();

    public AddDetails(String name, int age, int userId) {
        super(name, age, userId);
    }

    public void addDetails(String name, int userId) {
        Map.Entry<String, Integer> entry1 = new AbstractMap.SimpleEntry<>(name, userId);
        vector.add(entry1);
    }
}

class RemoveDetails extends AddDetails {
    public RemoveDetails(String name, int age, int userId) {
        super(name, age, userId);
    }

    public void removeDetails(String name, int userId) {
        vector.removeIf(entry -> entry.getKey().equals(name) && entry.getValue().equals(userId));
    }
}

class UpdateDetails extends AddDetails {
    public UpdateDetails(String name, int age, int userId) {
        super(name, age, userId);
    }

    public void update(String name, int userId, String newName) {
        for (int i = 0; i < vector.size(); i++) {
            Map.Entry<String, Integer> entry = vector.get(i);
            if (entry.getKey().equals(name) && entry.getValue().equals(userId)) {
                vector.set(i, new AbstractMap.SimpleEntry<>(newName, userId));
            }
        }
    }
}

class ShowDetails extends AddDetails {
    public ShowDetails(String name, int age, int userId) {
        super(name, age, userId);
    }

    public void show(String name, int userId) {
        for (Map.Entry<String, Integer> entry : vector) {
            if (entry.getKey().equals(name) && entry.getValue().equals(userId)) {
                System.out.println("Your name is " + entry.getKey() + " and your userId is: " + entry.getValue());
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter your age:");
        int age = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (age < 21) {
            System.out.println("Please don't register.");
        } else {
            System.out.println("Choose your event:");
            System.out.println("a -> register || b -> read || c -> update || d -> delete || e -> exit");

            while (true) {
                String event = sc.nextLine();

                if (event.equals("a")) {
                    System.out.println("Please enter your name:");
                    String name = sc.nextLine();
                    Random rand = new Random();
                    int userId = rand.nextInt(10000);

                    Gym member1 = new Gym(name, age, userId);
                    member1.welcomeMessage();

                    AddDetails details = new AddDetails(name, age, userId);
                    details.addDetails(name, userId);

                }
                if (event.equals("b")) {
                    System.out.println("Please enter your name:");
                    String name = sc.nextLine();
                    System.out.println("Please enter your user id:");
                    int userId = sc.nextInt();
                    sc.nextLine();

                    ShowDetails read = new ShowDetails(name, age, userId);
                    read.show(name, userId);

                }
                if (event.equals("c")) {
                    System.out.println("Please enter your name:");
                    String name = sc.nextLine();
                    System.out.println("Please enter your user id:");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Please enter your new name:");
                    String newName = sc.nextLine();

                    UpdateDetails update = new UpdateDetails(name, age, userId);
                    update.update(name, userId, newName);

                }
                if (event.equals("d")) {
                    System.out.println("Please enter your name:");
                    String name = sc.nextLine();
                    System.out.println("Please enter your user id:");
                    int userId = sc.nextInt();
                    sc.nextLine();

                    RemoveDetails remove = new RemoveDetails(name, age, userId);
                    remove.removeDetails(name, userId);

                }
                if (event.equals("e")) {
                    break;
                }
            }
        }
        System.out.println("thank you");
        sc.close();
    }
}
