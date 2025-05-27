import java.lang.reflect.Method;

public class ReflectionExample {

    public void sayHello(String name) {
        System.out.println("Hello, " + name + "!");
    }

    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        try {
            // Load a class dynamically [cite: 99]
            Class<?> myClass = Class.forName("ReflectionExample");

            // Create an instance of the class
            Object instance = myClass.getDeclaredConstructor().newInstance();

            // Get a specific method by name and parameter types [cite: 99]
            Method sayHelloMethod = myClass.getDeclaredMethod("sayHello", String.class);

            // Invoke the method dynamically [cite: 99]
            sayHelloMethod.invoke(instance, "World");

            Method addMethod = myClass.getDeclaredMethod("add", int.class, int.class);
            Object result = addMethod.invoke(instance, 10, 5);
            System.out.println("Add result: " + result);

            // Print all method names and parameters [cite: 100]
            System.out.println("\nAll methods in ReflectionExample:");
            Method[] methods = myClass.getDeclaredMethods(); // cite: 100
            for (Method method : methods) {
                System.out.print("Method Name: " + method.getName() + ", Parameters: ("); // cite: 100
                Class<?>[] parameterTypes = method.getParameterTypes(); // cite: 100
                for (int i = 0; i < parameterTypes.length; i++) {
                    System.out.print(parameterTypes[i].getSimpleName());
                    if (i < parameterTypes.length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println(")");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}