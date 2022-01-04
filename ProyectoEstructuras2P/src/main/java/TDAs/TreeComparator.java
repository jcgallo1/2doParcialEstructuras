package TDAs;

import java.util.Comparator;



//Comparador para la PriorityQueue , comparar los hijos
@FunctionalInterface
public interface TreeComparator <T> extends Comparator<Tree<T>> {
    
    int compareContent(T o1, T o2);
    
    @Override
    default int compare(Tree<T> t1, Tree<T> t2){
        return compareContent(t1.getRoot().getContent(), t2.getRoot().getContent());
    }
    
    
}
   