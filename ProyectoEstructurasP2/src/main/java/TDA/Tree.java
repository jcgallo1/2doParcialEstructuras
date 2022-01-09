package TDA;

import TDA.NodeTree;

public class Tree<T> {
  
    private NodeTree<T> root; //root

   
    //Tree with specified root content value
    public Tree(T rootContent){
        this.root = new NodeTree<>(rootContent);
    }

    
    //Tree with a node
    public Tree(NodeTree<T> root){
        this.root = root;
    }

    
    //Empty Tree
    public Tree(){
        this((T) null);
    }

   
    //Cambia el nodo del arbol
    public void setRoot(NodeTree<T> root) {
        this.root = root;
    }

    public NodeTree<T> getRoot() {
        return root;
    }

    public Tree<T> getChildrenByUtility(){
        return root.getChildren().peek();
    }


	

}
