import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
 *
 * @author - Rutvik Marakana
 * @version 1.0
 * @userid - rmarakana3
 * @GTID - 903401647
 *
 * Collaborators: NONE
 *
 * Resources: NONE
 */
public class AVL<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The collection can't be null");
        }

        for (T temp : data) {
            if (temp == null) {
                throw new IllegalArgumentException("Any data in the collection can't be null");
            }
            add(temp);
        }

    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data to be added in a AVL tree can't be null");
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
     * @param curr The current node
     * @param data The data to be added in the AVL tree
     * @return The new tree after adding the given data if not present in the tree, otherwise the old tree is returned.
     */
    private AVLNode<T> rAdd(AVLNode<T> curr, T data) {
        if (curr == null) {
            size++;
            AVLNode<T> temp = new AVLNode<>(data);
            return temp;
        }

        if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(rAdd(curr.getLeft(), data));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(rAdd(curr.getRight(), data));
        } else {
            return curr;
        }
        return balance(curr);
    }

    /**
     * Method for the right rotation of the AVL tree
     *
     * @param curr The current node
     * @return The new tree after right rotation
     */
    private AVLNode<T> rightRotation(AVLNode<T> curr) {
        AVLNode<T> temp = curr.getLeft();
        curr.setLeft(temp.getRight());
        temp.setRight(curr);

        update(curr);
        update(temp);

        return temp;
    }

    /**
     * Method for left rotation of the AVL tree
     *
     * @param curr The current node
     * @return The new tree after left rotation
     */
    private AVLNode<T> leftRotation(AVLNode<T> curr) {
        AVLNode<T> temp = curr.getRight();
        curr.setRight(temp.getLeft());
        temp.setLeft(curr);

        update(curr);
        update(temp);

        return temp;
    }

    /**
     * Method that rotates the tree based on the balance factor of the current node
     *
     * @param curr The current node
     * @return The new tree after all the balancing is done
     */
    private AVLNode<T> balance(AVLNode<T> curr) {
        update(curr);
        if (curr.getBalanceFactor() < -1) {
            if (curr.getRight().getBalanceFactor() > 0) {
                curr.setRight(rightRotation(curr.getRight()));
            }
            return leftRotation(curr);
        } else if (curr.getBalanceFactor() > 1) {
            if (curr.getLeft().getBalanceFactor() < 0) {
                curr.setLeft(leftRotation(curr.getLeft()));
            }
            return rightRotation(curr);
        }
        return curr;
    }

    /**
     * Method that updates the height and the balance factor of current node
     *
     * @param curr The current node
     */
    private void update(AVLNode<T> curr) {
        curr.setBalanceFactor(heightHelper(curr.getLeft()) - heightHelper(curr.getRight()));
        curr.setHeight(1 + Math.max(heightHelper(curr.getLeft()), heightHelper(curr.getRight())));
    }

    /**
     * return the node's height
     *
     * @param curr The current node
     * @return The height of the node
     */
    private int heightHelper(AVLNode<T> curr) {

        if (curr == null) {
            return -1;
        }
        return curr.getHeight();
    }


    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data, NOT predecessor. As a reminder, rotations can occur
     * after removing the successor node. Do NOT use the provided public
     * predecessor method to remove a 2-child node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data to be removed from the AVL tree can't be null");
        }
        AVLNode<T> dummy = new AVLNode<>(null);
        root = rRemove(root, data, dummy);
        if (dummy.getData() == null) {
            throw new NoSuchElementException("Data to be removed is not in the AVL tree");
        } else {
            return dummy.getData();
        }
    }

    /**
     * Helper method of remove(T data)
     *
     * Method searches the data to be removed. If the data
     * is found, then it is removed and the tree is rearranged such that no rules of AVL are violated. If
     * the data is not found, nothing is done in the tree.
     *
     * @param curr The current node in the tree
     * @param data The data to be removed
     * @param dummy A helper node which stores the removed data
     * @return The new tree after removing the given data from the tree if present.
     */
    private AVLNode<T> rRemove(AVLNode<T> curr, T data, AVLNode<T> dummy) {

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
            if (curr.getLeft() == null && curr.getRight() != null) {
                return curr.getRight();
            } else if (curr.getRight() == null && curr.getLeft() != null) {
                return curr.getLeft();
            } else if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } else {
                AVLNode<T> dummy2 = new AVLNode<>(null);
                curr.setRight(removeSuccessor(curr.getRight(), dummy2));
                curr.setData(dummy2.getData());
            }
        }

        return balance(curr);
    }

    /**
     * Helper method for rRemove(BSTNode curr, T data, BSTNode dummy)
     *
     * Ths method is called only when the node to be removed from the tree has 2 children.
     *
     * This method iterates to the right of the current node once and then recursively
     * iterates to the left of the current node till the predecessor is reached.
     *
     * @param curr The current node
     * @param dummy Dummy node that stores the data found in the predecessor
     * @return The right node of the current node for base case and the return value from balance() method
     * for all other cases
     */
    private AVLNode<T> removeSuccessor(AVLNode<T> curr, AVLNode<T> dummy) {
        if (curr.getLeft() == null) {
            dummy.setData(curr.getData());
            return curr.getRight();
        } else {
            curr.setLeft(removeSuccessor(curr.getLeft(), dummy));
            return balance(curr);
        }
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data to be found cannot be null");
        }
        AVLNode<T> temp = helperGet(root, data);
        if (temp == null) {
            throw new NoSuchElementException("Data to be found is not in the AVL tree");
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
     * @param curr The current node
     * @param data The data to be found in the tree
     * @return The node that contains the data to be found if the data is present in the tree otherwise null.
     */
    private AVLNode<T> helperGet(AVLNode<T> curr, T data) {
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
     * @param data the data to search for in the tree
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data to be searched for in the AVL tree is null");
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
     * @param curr The current node
     * @param data The data to be searched in the tree
     * @return True if the data is found in the tree, otherwise false
     */
    private boolean helperContains(AVLNode<T> curr, T data) {
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
     * Returns the height of the root of the tree. Do NOT compute the height
     * recursively. This method should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (root == null) {
            return -1;
        }
        return root.getHeight();
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * In your BST homework, you worked with the concept of the predecessor, the
     * largest data that is smaller than the current data. However, you only
     * saw it in the context of the 2-child remove case.
     *
     * This method should retrieve (but not remove) the predecessor of the data
     * passed in. There are 3 cases to consider:
     * 1: The left subtree is non-empty. In this case, the predecessor is the
     * rightmost node of the left subtree.
     * 2: The left subtree is empty. In this case, the predecessor is the deepest
     * ancestor of the node containing data whose right child is also
     * an ancestor of data.
     * 3: If the data passed in is the minimum data in the tree, return null.
     *
     * This should NOT be used in the remove method.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *     76
     *   /    \
     * 34      90
     *  \    /
     *  40  81
     * predecessor(76) should return 40
     * predecessor(81) should return 76
     *
     * @param data the data to find the predecessor of
     * @return the predecessor of data. If there is no smaller data than the
     * one given, return null.
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T predecessor(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data whose predecessor is to be found in the AVL Tree can't be null");
        }
        AVLNode<T> dummy = new AVLNode<>(null);
        dummy = findPredecessor(root, dummy, data);

        if (dummy.getData() == null) {
            return null;
        }
        return dummy.getData();
    }

    /**
     * Helper method for predecessor(T data)
     *
     * The method is used to find the predecessor of a given node if it exists
     *
     * @param curr The current node
     * @param dummy The dummy node
     * @param data Data whose predecessor is to be found
     * @return The node containing predecessor
     * @throws java.util.NoSuchElementException If the data is null
     */
    private AVLNode<T> findPredecessor(AVLNode<T> curr, AVLNode<T> dummy, T data) {

        if (curr == null) {
            throw new NoSuchElementException("Data is not found in the tree");
        }

        if (curr.getData().compareTo(data) > 0) {
            return findPredecessor(curr.getLeft(), dummy, data);
        } else if (curr.getData().compareTo(data) < 0) {
            dummy = curr;
            return findPredecessor(curr.getRight(), dummy, data);
        } else {
            if (curr.getLeft() != null) {
                dummy = findPredecessorHelper(curr.getLeft());
            }
            return dummy;
        }
    }

    /**
     * Helper method for findPredecessor()
     *
     * Method iterates to the right of the current node till null reached.
     *
     * @param curr The current node
     * @return The node containing predecessor of a give node
     */
    private AVLNode<T> findPredecessorHelper(AVLNode<T> curr) {
        if (curr.getRight() == null) {
            return curr;
        } else {
            return findPredecessorHelper(curr.getRight());
        }
    }
    /**
     * Finds and retrieves the k-smallest elements from the AVL in sorted order,
     * least to greatest.
     *
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *              50
     *            /    \
     *         25      75
     *        /  \     / \
     *      13   37  70  80
     *    /  \    \      \
     *   12  15    40    85
     *  /
     * 10
     * kSmallest(0) should return the list []
     * kSmallest(5) should return the list [10, 12, 13, 15, 25].
     * kSmallest(3) should return the list [10, 12, 13].
     *
     * @param k the number of smallest elements to return
     * @return sorted list consisting of the k smallest elements
     * @throws java.lang.IllegalArgumentException if k < 0 or k > n, the number
     *                                            of data in the AVL
     */
    public List<T> kSmallest(int k) {
        if (k < 0 || k > size) {
            throw new IllegalArgumentException("k can't be less than 0 or greater than size");
        }
        List<T> temp = new ArrayList<>(k);
        if (k == 0) {
            return temp;
        }
        return kSmallestHelper(root, k, temp);
    }

    /**
     * Helper method for kSmallest()
     *
     * Method finds first k smallest data in the tree and stores it in a list in ascending order.
     * @param curr The current node
     * @param k Number of data to be stored in list
     * @param temp The list where first k smallest data is stored in ascending order
     * @return The list with first k smallest data in ascending order
     */
    private List<T> kSmallestHelper(AVLNode<T> curr, int k, List<T> temp) {
        if (curr == null) {
            return null;
        }

        if (temp.size() == k) {
            return temp;
        }
        kSmallestHelper(curr.getLeft(), k, temp);
        if (temp.size() == k) {
            return temp;
        }
        temp.add(curr.getData());
        if (temp.size() == k) {
            return temp;
        }
        kSmallestHelper(curr.getRight(), k, temp);
        return temp;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
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
