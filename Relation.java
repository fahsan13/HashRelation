import java.util.TreeSet;

/**
 * Interface for the Relation ADT. This interface acts as a contract
 * and stipulates the methods to be written by any class implementing
 * it.
 *
 * A Relation stores binary pairs, X and Y, such that the combination of
 * X and Y must be unique. Values of X or Y alone do not have to be
 * distinct.
 *
 * @author Faisal Ahsan
 *
 */

public interface Relation<X,Y> {

	/**
	 * Tests whether the Relation contains a given pair.
	 *
	 * @return True if Relation is empty. False otherwise.
	 */
	public boolean containsPair(X xValue, Y yValue);


	/**
	 * Given parameter X, will return a Set containing all
	 * Y values which correspond to an existing (X, Y) binary
	 * pairing, such that the relation contains (X, Y).
	 *
	 * @return TreeSet containing Y values for a given X
	 */

	public TreeSet<Y> getYValuesGivenX(X xValue);

	/**
	 * Given parameter Y, will return a Set containing all
	 * X values which correspond to an existing (X, Y) binary
	 * pairing, such that the relation contains (X, Y).
	 *
	 * @return TreeSet containing X values for a given Y.
	 */

	public TreeSet<X> getXValuesGivenY(Y yValue);

	/**
	 * Method to make the relation empty.
	 */
	public void makeEmpty();

	/**
	 * Add a binary pair to the relation.
	 *
	 * @param X
	 * @param Y
	 */
	public void addPair(X xValue, Y yValue);

	/**
	 * Remove a binary pair from the relation.
	 *
	 * @param X
	 * @param Y
	 */
	public void removePair(X xValue, Y yValue);

	/**
	 * Remove all binary pairs (X, Y) from the relation
	 * given a parameter X.
	 *
	 * @param X
	 */
	public void removeAllPairsGivenX(X xValue);

	/**
	 * Remove all binary pairs (X, Y) from the relation
	 * given a parameter Y.
	 *
	 * @param Y
	 */
	public void removeAllPairsGivenY(Y yValue);

	/**
	 * Renders the contents of the relation as a String
	 * which is returned by the method.
	 *
	 * @return String
	 */
	public String toString();
}
