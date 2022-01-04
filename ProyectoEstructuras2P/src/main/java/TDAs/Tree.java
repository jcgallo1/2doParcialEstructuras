package TDAs;

import TDAs.Tree;
import TDAs.NodeTree;


public class Tree<V> {
    
    private NodeTree<V> root;

   
    public Tree(V rootContent){
        this.root = new NodeTree<>(rootContent);
    }

    
    public Tree(NodeTree<V> root){
        this.root = root;
    }

    //arbol vacío
    public Tree(){
        this((V) null);
    }

    
    public void setRoot(NodeTree<V> root) {
        this.root = root;
    }

    public NodeTree<V> getRoot() {
        return root;
    }

    public Tree<V> getChildrenByUtility(){
        return root.getChildren().peek();
    }

}
