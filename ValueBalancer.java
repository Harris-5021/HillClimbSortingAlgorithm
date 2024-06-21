import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class ValueBalancer {

    // The number of values to balance on the trucks
    int numValues = 20;

    // The values to balance on the trucks
    List<Float> values = CSVReader();

    // The maximum number of iterations to perform
    int maxIterations = 10;

    // The mutation rate
    float mutationRate = 0.01f;

    // The RandomMutationHillClimb object
    RandomMutationHillClimb rmhc;

    // Constructor
    public ValueBalancer() {
        // Create an instance of the RandomMutationHillClimb class
        rmhc = new RandomMutationHillClimb(numValues, values, maxIterations, mutationRate);
    }

    public static void main(String[] args) {
        // Create an instance of the ValueBalancer class
        ValueBalancer vb = new ValueBalancer();

        // The number of iterations to run the algorithm for
        int numIterations = 10;

        // Create an instance of the RandomMutationHillClimb class
        RandomMutationHillClimb rmhc = vb.rmhc;

        // Run the algorithm for a specified number of iterations
        for (int i = 0; i < numIterations; i++) {
            // Perform one iteration of the algorithm
            rmhc.iterate();

            // Print the binary string of the current solution
            System.out.println( "Iteration " + (i +1) + ": Binary string = " + Arrays.toString(rmhc.solution)+":Fitness = " + rmhc.getFitness(rmhc.solution));
        }

        // Get the best solution found
        int[] bestSolution = rmhc.bestSolution;

        // Get the fitness of the best solution
        float bestFitness = rmhc.bestFitness;

        // Print the best solution and its fitness
        System.out.println("Best solution: " + Arrays.toString(bestSolution));
        System.out.println("Best fitness: " + bestFitness);
    }

    // This method reads the data from a CSV file and returns it as a List of Floats
    public List<Float> CSVReader() {
        // The path to the CSV file
        String file = "C:/Users/harri/OneDrive/Documents/HillClimb/src/bricks.csv";

        // The list to hold the data
        List<Float> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // Read each line from the file
            String line;
            while ((line = br.readLine()) != null) {
                // Parse the line as a float and add it to the data list
                data.add(Float.valueOf(line));
            }
        } catch (Exception e) {
            // If there was an error reading the file, throw a runtime exception
            throw new RuntimeException(e);
        }

        return data;
    }
}


