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
        //scores for cli a, b and c
        double[] inputArray = new double[]{ 2, 1, 1};
        OptimizationData f = new LinearObjectiveFunction(inputArray,-5);
        OptimizationData constraintSet = getConstraints();
        OptimizationData goalType = GoalType.MAXIMIZE;

        PointValuePair solution = new org.apache.commons.math3.optim.linear.SimplexSolver().optimize(f,constraintSet,goalType);

        System.out.println("solution.getPoint()[0] = " + solution.getPoint()[0]);
        System.out.println("solution.getPoint()[1] = " + solution.getPoint()[1]);
        System.out.println("solution.getPoint()[2] = " + solution.getPoint()[2]);
        System.out.println("Val = " + solution.getValue());

    }

    private static LinearConstraintSet getConstraints(){
        Collection constraints = new ArrayList();
        //ALI imp goal is 20
        constraints.add(new LinearConstraint(new double[] { 1, 1, 1 }, Relationship.EQ, 20));
        //a cannot exceed 10
        constraints.add(new LinearConstraint(new double[] { 1, 0, 0 }, Relationship.LEQ, 10));
        //b cannot be smaller than 5
        constraints.add(new LinearConstraint(new double[] { 0, 1, 0 }, Relationship.GEQ, 5));
        //c cannot be smaller than 2
        constraints.add(new LinearConstraint(new double[] { 0, 0, 1 }, Relationship.GEQ, 2));
        //a+c cannot be smaller than 7
        constraints.add(new LinearConstraint(new double[] { 1, 0, 1 }, Relationship.GEQ, 7));
        //Margin constraint: WACMt for a, b and c are {4, 2, 3}, ALI's expected WACMt is 2.8 therefore total spend = 2.8*20=56
        constraints.add(new LinearConstraint(new double[] { 4, 2, 3 }, Relationship.LEQ, 56));


        LinearConstraintSet constraintSet = new LinearConstraintSet(constraints);
        return constraintSet;
    }
}
