import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Chris on 12/5/2014.
 */
public class SimplexSolver {
    public static void main(String[] args){
        double[] inputArray = new double[]{ 2, 1};
        OptimizationData f = new LinearObjectiveFunction(inputArray,-5);
        OptimizationData constraintSet = getConstraints();
        OptimizationData goalType = GoalType.MINIMIZE;

        PointValuePair solution = new org.apache.commons.math3.optim.linear.SimplexSolver().optimize(f,constraintSet,goalType);

        System.out.println("solution.getPoint()[0] = " + solution.getPoint()[0]);
        System.out.println("solution.getPoint()[1] = " + solution.getPoint()[1]);
        System.out.println("Val = " + solution.getValue());

    }

    private static LinearConstraintSet getConstraints(){
        Collection constraints = new ArrayList();

        constraints.add(new LinearConstraint(new double[] { 1, 2 }, Relationship.LEQ, 10));
        constraints.add(new LinearConstraint(new double[] { 0, 1 }, Relationship.GEQ, 0));
        constraints.add(new LinearConstraint(new double[] { 1, 1 }, Relationship.EQ, 5));



        LinearConstraintSet constraintSet = new LinearConstraintSet(constraints);
        return constraintSet;
    }
}
