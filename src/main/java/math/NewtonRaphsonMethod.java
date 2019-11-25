package math;

/**
 * https://en.wikipedia.org/wiki/Newton%27s_method
 */
public class NewtonRaphsonMethod {
    
    private static double ACCURACY = 0.000001;
    
    public static double calcRoot(double number, int root) {
        if(root == 0 || (number < 0 && root % 2 == 0)) {
            return Double.NaN;
        }
        
        double temp = 1.0;
        double rootResult = temp - ((Math.pow(temp, root) - number) / (root * Math.pow(temp, root - 1)));
        
        while(Math.abs(rootResult - temp) > ACCURACY) {
            temp = rootResult;
            rootResult = temp - ((Math.pow(temp, root) - number) / (root * Math.pow(temp, root - 1)));
        }
        
        return rootResult;
    }
    
    public static double squareRoot(double number) {
        double temp = 1.0;
        double root = temp - ((temp * temp - number) / (2 * temp));
        
        while(Math.abs(root - temp) > ACCURACY) {
            temp = root;
            root = temp - ((temp * temp - number) / (2 * temp));
            
        }
        
        return root;
    }
    
    public static double cubeRoot(double number) {
        double root = 0.0;
        if(number < 0) {
            
            root = Double.NaN;
        }
        else {
            double temp = 1.0;
            root = temp - ((temp * temp * temp - number) / (3 * temp * temp));
            while(Math.abs(root - temp) > ACCURACY) {
                temp = root;
                root = temp - ((temp * temp * temp - number) / (3 * temp * temp));
            }
        }
        return root;
    }
    
    public static void main(String[] args) {
        System.out.println("squareRoot(9) = " + squareRoot(9));
        
        System.out.println("cubeRoot(27) = " + cubeRoot(27));
        
        System.out.println("calcRoot(9, 2) = " + calcRoot(9, 2));
        
        System.out.println("calcRoot(64, 3) = " + calcRoot(64, 3));
    
        System.out.println("calcRoot(-27, 3) = " + calcRoot(-27, 3));
    
        System.out.println("calcRoot(-35, 4) = " + calcRoot(-35, 4));
    }
}
