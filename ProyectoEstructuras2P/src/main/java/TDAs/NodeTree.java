package TDAs;

import java.util.PriorityQueue;

public class NodeTree<V> {
    private V content;
    private PriorityQueue<Tree<V>> children;

    public NodeTree(V value){
        this.content = value;
    }
    public NodeTree(){}

    public PriorityQueue<Tree<V>> getChildren() {
        return children;
    }

    public V getContent() {
        return content;
    }

    public void setChildren(PriorityQueue<Tree<V>> children) {
        this.children = children;
    }

    public void setContent(V content) {
        this.content = content;
    }

}
