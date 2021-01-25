/**
 * Your implementation of a BST.
 *
 * @author - Rutvik Marakana
 * @version 1.0
 * @userid - rmarakana3
 * @GTID - 903401647
 *
 * Collaborators: None
 *
 * Resources: None
 */

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The elements should be added to the BST in the order in 
     * which they appear in the Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for-loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The Collection can't be null");
        }
        for (T temp : data) {
            if (temp == null) {
                throw new IllegalArgumentException("Any data in the Collection cannot be null");
            }
            add(temp);
        }
    }

    /**
     * Adds the data to the tree.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data to be added in a tree can't be null");
        }
        root = rAdd(root, data);
    }

    /**
     * Helper method of add(T data)
     *
     * The data is added to leaf if it is not present in the tree.
     *
     * If the data is present, then nothing is changed in the tree.
     *
     * Time complexity is O(log n) for best and average cases and O(n) for worst case.
     * @param curr The current node
     * @param data the data to be added in the tree
     * @return The new tree after adding the given data if not present in the tree, otherwise the old tree is returned.
     */
    private BSTNode<T> rAdd(BSTNode<T> curr, T data) {
        if (curr == null) {
            size++;
            return new BSTNode<>(data);
        }

        if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(rAdd(curr.getLeft(), data));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(rAdd(curr.getRight(), data));
        }
        return curr;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data. You MUST use recursion to find and remove the
     * predecessor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data to be removed from the tree can't be null");
        }
        BSTNode<T> dummy = new BSTNode<>(null);
        root = rRemove(root, data, dummy);
        if (dummy.getData() == null) {
            throw new NoSuchElementException("Data to be removed is not in the tree");
        } else {
            return dummy.getData();
        }
    }

    /**
     * Helper method of remove(T data)
     *
     * Method searches the data to be removed. If the data
     * is found, then it is removed and the tree is rearranged such that no rules of BST are violated. If
     * the data is not found, nothing is done in the tree.
     *
     * Times complexity is O(log n) for best and average cases and O(n) for worst case.
     *
     * @param curr The current node in the tree
     * @param data The data to be removed
     * @param dummy A helper node which stores the removed data
     * @return The new tree after removing the given data from the tree if present.
     */
    private BSTNode<T> rRemove(BSTNode<T> curr, T data, BSTNode<T> dummy) {
        if (curr == null) {
            return null;
        }
        if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(rRemove(curr.getLeft(), data, dummy));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(rRemove(curr.getRight(), data, dummy));
        } else {
            dummy.setData(curr.getData());
            size--;
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } else if (curr.getLeft() != null && curr.getRight() == null) {
                return curr.getLeft();
            } else if (curr.getRight() != null && curr.getLeft() == null) {
                return curr.getRight();
            } else {
                BSTNode<T> dummy2 = new BSTNode<>(null);
                curr.setLeft(removePredecessor(curr.getLeft(), dummy2));
                curr.setData(dummy2.getData());
            }
        }
        return curr;

    }

    /**
     * Helper method for rRemove(BSTNode curr, T data, BSTNode dummy)
     *
     * Ths method is called only when the node to be removed from the tree has 2 children.
     *
     * This method iterates to the left of the current node once and then recursively
     * iterates to the right of the current node till the predecessor is reached.
     *
     * @param curr The current node
     * @param dummy Dummy node that stores the data found in the predecessor
     * @return The left node of the current node for base case and the current node for all other cases.
     */
    private BSTNode<T> removePredecessor(BSTNode<T> curr, BSTNode<T> dummy) {
        if (curr.getRight() == null) {
            dummy.setData(curr.getData());
            return curr.getLeft();
        } else {
            curr.setRight(removePredecessor(curr.getRight(), dummy));
            return curr;
        }
    }
    /**
     * Returns the data from the tree matching the given parameter.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data to be found cannot be null");
        }
        BSTNode<T> temp = helperGet(root, data);
        if (temp == null) {
            throw new NoSuchElementException("Data to be found is not in the tree");
        } else {
            return temp.getData();
        }
    }

    /**
     * Helper method of get(T data)
     *
     * Method searches the tree to find the node whose data matches the data in the parameter.
     *
     * Returns the node whose data matches the data in the parameter.
     *
     * If no node's data matches the data in the parameter then null is returned
     *
     * Time complexity is O(log n) for best and average cases and O(n) for worst cases
     *
     * @param curr The current node
     * @param data The data to be found in the tree
     * @return The node that contains the data to be found if the data is present in the tree otherwise null.
     */
    private BSTNode<T> helperGet(BSTNode<T> curr, T data) {
        if (curr == null) {
            return null;
        }
        if (data.compareTo(curr.getData()) < 0) {
            return helperGet(curr.getLeft(), data);
        } else if (data.compareTo(curr.getData()) > 0) {
            return helperGet(curr.getRight(), data);
        }
        return curr;
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data to be searched for in the tree is null");
        }
        return helperContains(root, data);

    }

    /**
     * Helper method for contains(T data)
     *
     * Returns whether the data is in the tree or not.
     *
     * If the data is not found, the method returns false. If the data is found, method returns true.
     *
     * Time complexity is O(log n) for best and average cases and O(n) for worst case.
     *
     * @param curr The current node
     * @param data The data to be searched in the tree
     * @return True if the data is found in the tree, otherwise false
     */
    private boolean helperContains(BSTNode<T> curr, T data) {
        if (curr == null) {
            return false;
        }
        if (data.compareTo(curr.getData()) < 0) {
            return helperContains(curr.getLeft(), data);
        } else if (data.compareTo(curr.getData()) > 0) {
            return helperContains(curr.getRight(), data);
        }
        return true;

    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> temp = new ArrayList<>();
        preorderHelper(root, temp);
        return temp;
    }

    /**
     * Helper method of preOrder()
     *
     * Method follows the CLR(Current Left Right) mechanism to store the data in List. This means that firstly data
     * in the current node is added to the List.Then, the current node's left is traversed. Lastly the current
     * node's right is traversed.
     *
     * The time complexity for pre-order traversal is O(n)
     *
     * @param curr The current node
     * @param temp The List where node's data is stored
     */
    private void preorderHelper(BSTNode<T> curr, List<T> temp) {
        if (curr != null) {
            temp.add(curr.getData());
            preorderHelper(curr.getLeft(), temp);
            preorderHelper(curr.getRight(), temp);
        }
    }
    /**
     * Generate an in-order traversal of the tree.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> temp = new ArrayList<>();
        inorderHelper(root, temp);
        return temp;
    }

    /**
     * Helper method of inorder()
     *
     * Method follows the LCR(Left Current Right) mechanism to store the data in List. This means that firstly
     * the current node's left is traversed. Then, data in the current node is added to the List. Lastly the
     * current node's right is traversed.
     *
     * The time complexity for in-order traversal is O(n)
     *
     * @param curr The current node
     * @param temp The List where node's data is stored
     */
    private void inorderHelper(BSTNode<T> curr, List<T> temp) {
        if (curr != null) {
            inorderHelper(curr.getLeft(), temp);
            temp.add(curr.getData());
            inorderHelper(curr.getRight(), temp);
        }
    }
    /**
     * Generate a post-order traversal of the tree.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> temp = new ArrayList<>();
        postorderHelper(root, temp);
        return temp;
    }

    /**
     * Helper method of postorder()
     *
     * Method follows the LRC(Left Right Current) mechanism to store the data in List. This means that firstly
     * the current node's left is traversed. Then, the current node's right is traversed. Lastly, data in the
     * current node is added to the List.
     *
     * The time complexity for post-order traversal is O(n)
     *
     * @param curr The current node
     * @param temp The List where node's data is stored
     */
    private void postorderHelper(BSTNode<T> curr, List<T> temp) {
        if (curr != null) {
            postorderHelper(curr.getLeft(), temp);
            postorderHelper(curr.getRight(), temp);
            temp.add(curr.getData());
        }
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use. You may import java.util.Queue as well as an implmenting
     * class.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        Queue<BSTNode<T>> temp = new LinkedList<>();
        List<T> temp2 = new ArrayList<>();

        temp.add(root);

        while (!temp.isEmpty()) {
            BSTNode<T> curr = temp.remove();
            if (curr != null) {
                temp2.add(curr.getData());
                temp.add(curr.getLeft());
                temp.add(curr.getRight());
            }
        }
        return temp2;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (root == null) {
            return -1;
        }
        return heightHelper(root);
    }

    /**
     * Helper method to height()
     *
     * The method finds the height of any node in the tree. The height found is of the node passed as parameter during
     * the first call to the method. In this case, the first call is made in method height() and the parameter
     * passed to the method call is the root node.
     *
     * The time complexity is O(n)
     *
     * @param curr The current node
     * @return The height of the node passed in the first call to the method( in this case it's the root node).
     */
    private int heightHelper(BSTNode<T> curr) {

        if (curr == null) {
            return -1;
        }
        return Math.max(heightHelper(curr.getLeft()), heightHelper(curr.getRight())) + 1;
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Finds the path between two elements in the tree, specifically the path
     * from data1 to data2, inclusive of both.
     *
     * To do this, you must first find the deepest common ancestor of both data
     * and add it to the list. Then traverse to data1 while adding its ancestors
     * to the front of the list. Finally, traverse to data2 while adding its
     * ancestors to the back of the list. Please note that there is no
     * relationship between the data parameters in that they may not belong
     * to the same branch. You will most likely have to split off and
     * traverse the tree for each piece of data.
     * *
     * You may only use 1 list instance to complete this method. Think about
     * what type of list to use since you will have to add to the front and
     * back of the list.
     *
     * This method only needs to traverse to the deepest common ancestor once.
     * From that node, go to each data in one traversal each. Failure to do
     * so will result in a penalty.
     *
     * If both data1 and data2 are equal and in the tree, the list should be
     * of size 1 and contain the element from the tree equal to data1 and data2.
     *
     * Hint: How can we use the order property of the BST to locate the deepest
     * common ancestor?
     *
     * Ex:
     * Given the following BST composed of Integers
     *              50
     *          /        \
     *        25         75
     *      /    \
     *     12    37
     *    /  \    \
     *   11   15   40
     *  /
     * 10
     * findPathBetween(10, 40) should return the list [10, 11, 12, 25, 37, 40]
     * findPathBetween(50, 37) should return the list [50, 25, 37]
     * findPathBetween(75, 75) should return the list [75]
     *
     * Must be O(log n) for a balanced tree and O(n) for worst case.
     *
     * @param data1 the data to start the path from
     * @param data2 the data to end the path on
     * @return the unique path between the two elements
     * @throws java.lang.IllegalArgumentException if either data1 or data2 is
     *                                            null
     * @throws java.util.NoSuchElementException   if data1 or data2 is not in
     *                                            the tree
     */
    public List<T> findPathBetween(T data1, T data2) {
        if (data1 == null) {
            throw new IllegalArgumentException("The starting data of the path cannot be null");
        }
        if (data2 == null) {
            throw new IllegalArgumentException("The ending data of the path cannot be null");
        }
        LinkedList<T> temp = new LinkedList<>();
        BSTNode<T> ancestor = findAncestor(root, data1, data2);
        if (ancestor == null) {
            throw new NoSuchElementException("Data1 or data2 not found. Cannot find the path.");
        }
        if (data1.compareTo(ancestor.getData()) == 0 && data2.compareTo(ancestor.getData()) == 0) {
            temp.add(ancestor.getData());
            return temp;
        } else if (data1.compareTo(ancestor.getData()) != 0 && data2.compareTo(ancestor.getData()) == 0) {
            traverseData1(data1, ancestor, temp);
        } else if (data1.compareTo(ancestor.getData()) == 0 && data2.compareTo(ancestor.getData()) != 0) {
            temp.add(ancestor.getData());
            traverseData2(data2, ancestor, temp);
        } else {
            traverseData1(data1, ancestor, temp);
            traverseData2(data2, ancestor, temp);
        }
        return temp;
    }

    /**
     * Helper method to findPathBetween(T data1, T data2)
     *
     * Method finds the deepest common ancestor.
     *
     * @param curr The current node
     * @param data1 The data from where the path starts
     * @param data2 The data at which the path ends
     * @return The node that's the deepest common ancestor of parameters data1 and data2
     */
    private BSTNode<T> findAncestor(BSTNode<T> curr, T data1, T data2) {
        if (curr == null) {
            return null;
        }

        if (data1.compareTo(curr.getData()) > 0  && data2.compareTo(curr.getData()) > 0) {
            return findAncestor(curr.getRight(), data1, data2);
        } else if (data1.compareTo(curr.getData()) < 0 && data2.compareTo(curr.getData()) < 0) {
            return findAncestor(curr.getLeft(), data1, data2);
        }
        return curr;
    }

    /**
     * Helper method to findPathBetween()
     *
     * Method adds the data of nodes from the deepest common ancestor to the passed data in a LinkedList if the data
     * is found. The data from the deepest common ancestor to data1 is added to the front of the list.
     *
     * @param data1 The data from where the path starts in findPathBetween() method
     * @param curr The current node
     * @param temp The linked list in which the data is added
     */
    private void traverseData1(T data1, BSTNode<T> curr, LinkedList<T> temp) {

        if (curr == null) {
            throw new NoSuchElementException("Data1 is not found in the list");
        } else {
            if (data1.compareTo(curr.getData()) < 0) {
                temp.addFirst(curr.getData());
                traverseData1(data1, curr.getLeft(), temp);
            } else if (data1.compareTo(curr.getData()) > 0) {
                temp.addFirst(curr.getData());
                traverseData1(data1, curr.getRight(), temp);
            } else if (data1.compareTo(curr.getData()) == 0) {
                temp.addFirst(curr.getData());
            }
        }
    }

    /**
     * Helper method to findPathBetween()
     *
     * Method adds the data of nodes from the deepest common ancestor to the passed data in the LinkedList if the data
     * is found. The data from the deepest common ancestor to data2 is added to the end of the list.
     *
     * @param data2 The data at which the path ends in findPathBetween() method.
     * @param curr The current node
     * @param temp The linked list
     */
    private void traverseData2(T data2, BSTNode<T> curr, LinkedList<T> temp) {

        if (curr == null) {
            throw new NoSuchElementException("Data 2 is not found in the list");
        } else {
            if (data2.compareTo(curr.getData()) < 0) {
                if (temp.getLast().compareTo(curr.getData()) == 0) {
                    traverseData2(data2, curr.getLeft(), temp);
                } else {
                    temp.addLast(curr.getData());
                    traverseData2(data2, curr.getLeft(), temp);
                }
            } else if (data2.compareTo(curr.getData()) > 0) {
                if (temp.getLast().compareTo(curr.getData()) == 0) {
                    traverseData2(data2, curr.getRight(), temp);
                } else {
                    temp.addLast(curr.getData());
                    traverseData2(data2, curr.getRight(), temp);
                }
            } else if (data2.compareTo(curr.getData()) == 0) {
                if (temp.getLast().compareTo(curr.getData()) != 0) {
                    temp.addLast(curr.getData());
                }
            }
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }


}
