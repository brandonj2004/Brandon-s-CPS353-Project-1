package apiproject;
import java.util.List;

/**
 * Wraps integer collection structures so the API can safely change 
 * from a List/Array to an actual streaming architecture later without breaking contracts.
 */
public interface DataStreamWrapper {
    List<Integer> getDataAsList();
}