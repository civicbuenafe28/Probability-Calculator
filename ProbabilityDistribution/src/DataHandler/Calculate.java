package DataHandler;

import java.util.Stack;

public class Calculate {

    public static double mean(Stack<Data> stack) {
        double mean = 0;

        for (Data data : stack) {
            mean += data.outcome * data.probability;
        }

        return mean;
    }


    public static double variance(Stack<Data> stack) {
        double mean = 0;
        double sumXSqrdPX = 0;

        for (Data data : stack) {
            mean += data.outcome * data.probability;
            sumXSqrdPX += Math.pow(data.outcome, 2) * data.probability;
        }

        return sumXSqrdPX - Math.pow(mean, 2);
    }


    public static double stdDv8tion(Stack<Data> stack) {
        double mean = 0;
        double sumXSqrdPX = 0;

        for (Data data : stack) {
            mean += data.outcome * data.probability;
            sumXSqrdPX += Math.pow(data.outcome, 2) * data.probability;
        }

        double variance = sumXSqrdPX - Math.pow(mean, 2);

        return Math.sqrt(variance);
    }
}
