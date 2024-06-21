import java.util.List;
import java.util.Random;

public class RandomMutationHillClimb {

    // The number of values to balance on the trucks
    int numValues;

    // The values to balance on the trucks
    List<Float> values;

    // The array that represents the solution
    int[] solution;

    // The random number generator
    Random rand;

    // The maximum number of iterations to perform
    int maxIterations;

    // The best solution found so far
    int[] bestSolution;

    // The best fitness found so far
    float bestFitness;

    // The mutation rate
    float mutationRate;

    // Constructor
    public RandomMutationHillClimb(int numValues, List<Float> values, int maxIterations, float mutationRate) {
        this.numValues = numValues;
        this.values = values;
        this.maxIterations = maxIterations;
        this.mutationRate = mutationRate;

        // Initialize the random number generator
        rand = new Random();

        // Generate a random initial solution
        solution = new int[numValues];
        for (int i = 0; i < solution.length; i++) {
            solution[i] = rand.nextInt(2);
        }

        // Set the initial best solution to the initial solution
        bestSolution = solution.clone();
        bestFitness = getFitness(solution);
    }

    // This method evaluates the fitness of a solution
    public float getFitness(int[] solution) {
        // Calculate the sum of the values on each truck
        float sum1 = 0;
        float sum2 = 0;
        for (int i = 0; i < numValues; i++) {
            if (solution[i] == 0) {
                sum1 += values.get(i);
            } else {
                sum2 += values.get(i);
            }
        }

        // Return the absolute difference between the sums
        return Math.abs(sum1 - sum2);
    }

    // This method performs one iteration of the algorithm
    public void iterate() {
        // Generate a mutated version of the current solution
        int[] mutatedSolution = mutate(solution);

        // Evaluate the fitness of the mutated solution
        float fitness = getFitness(mutatedSolution);

        // If the mutated solution is better than the current solution,
        // replace the current solution with the mutated solution
        if (fitness < getFitness(solution)) {
            solution = mutatedSolution;
        }

        // If the mutated solution is better than the best solution found so far,
        // replace the best solution with the mutated solution
        if (fitness < bestFitness) {
            bestSolution = mutatedSolution;
            bestFitness = fitness;
        }
    }

    // This method mutates a solution by making random changes to the binary string
    public int[] mutate(int[] solution) {
        int[] mutatedSolution = solution.clone();

        // Loop through each bit in the binary string
        for (int i = 0; i < mutatedSolution.length; i++) {
            // With probability equal to the mutation rate, flip this bit
            if (rand.nextFloat() <= mutationRate) {
                mutatedSolution[i] = (mutatedSolution[i] == 0) ? 1 : 0;
            }
        }

        return mutatedSolution;
    }
}
