import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

import org.w3c.dom.Node;

import components.binarytree.BinaryTree;
import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.queue.Queue;
import components.queue.Queue1L;
import components.sequence.Sequence;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stack.Stack;
import components.stack.Stack1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.statement.StatementKernel.Condition;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class TestCode {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TestCode() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static void foo(NaturalNumber n) {
        n.decrement();
        n = new NaturalNumber2();
    }

    /**
     * Inserts the given {@code T} in the {@code Queue<T>} sorted according to
     * the given {@code Comparator<T>} and maintains the {@code Queue<T>}
     * sorted.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to insert into
     * @param x
     *            the {@code T} to insert
     * @param order
     *            the {@code Comparator} defining the order for {@code T}
     * @updates q
     * @requires <pre>
     * IS_TOTAL_PREORDER([relation computed by order.compare method])  and
     * IS_SORTED(q, [relation computed by order.compare method])
     * </pre>
     * @ensures <pre>
     * perms(q, #q * <x>)  and
     * IS_SORTED(q, [relation computed by order.compare method])
     * </pre>
     */
    private static <T> void insertInOrder(Queue<T> q, T x,
            Comparator<T> order) {

        q.enqueue(x);
        Queue<T> tempQueue = q.newInstance();
        while (q.length() != 0 && order.compare(x, q.front()) > 0) {
            tempQueue.enqueue(q.dequeue());
        }
        tempQueue.enqueue(x);
        tempQueue.append(q);
        q.transferFrom(tempQueue);

    }

    /**
     * Sorts {@code this} according to the ordering provided by the
     * {@code compare} method from {@code order}.
     *
     * @param order
     *            ordering by which to sort
     * @updates this
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * perms(this, #this)  and
     * IS_SORTED(this, [relation computed by order.compare method])
     * </pre>
     */
    public void sort(Comparator<T> order) {
        Queue<T> tempQueue = this.newInstance();
        for (int i = 0; i < this.length(); i++) {
            T x = this.dequeue();
            tempQueue.insertInOrder(tempQueue, x, order);
        }
        this.transferFrom(tempQueue);

    }

    private static int sumDigits(NaturalNumber n) {
        int result = 0;
        int digit = n.divideBy10();
        if (!n.isZero()) {
            result += sumDigits(n);
        }
        result += digit;
        return result;
    }

    private static Queue<Integer> reverse(Queue<Integer> queue2) {
        Queue<Integer> result = new Queue1L<>();

        if (!(queue2.length() == 0)) {
            int num = queue2.dequeue();
            result = reverse(queue2);
            result.enqueue(num);

        }
        return result;
    }

    private static Stack<Integer> fillerZero(Stack<Integer> stack1) {
        Stack<Integer> test = new Stack1L<>();
        int startLength = test.length();

        if (startLength != 0) {
            test.push(0);
            test.flip();
            test = fillerZero(test);
        }

        stack1.transferFrom(test);
        return stack1;
    }

    public static void filler(Stack<Integer> s) {
        Stack<Integer> temp = new Stack1L<>();
        temp.push(0);
        s.flip();
        while (s.length() != 0) {
            int x = s.pop();
            temp.push(x);
            temp.push(0);
        }
        s.transferFrom(temp);
    }

    public static void removePairs(Map<String, String> map1) {
        Map<String, String> temp = new Map1L<>();
        while (map1.size() != 0) {
            Map.Pair<String, String> removed = map1.removeAny();
            if (!(removed.key().equals(removed.value()))) {
                temp.add(removed.key(), removed.value());
            }

        }
        map1.transferFrom(temp);
    }

    public static void removeHighLow(Map<String, NaturalNumber> m) {
        Map<String, NaturalNumber> temp = new Map1L<>();
        NaturalNumber low = new NaturalNumber2();
        NaturalNumber high = new NaturalNumber2();

        while (m.size() != 0) {
            Map.Pair<String, NaturalNumber> removed = m.removeAny();
            if (removed.value().compareTo(low) <= 0) {

            }
        }
    }

    public static void remove(Map<String, NaturalNumber> m) {
        Map<String, NaturalNumber> temp = new Map1L<>();
        Map.Pair<String, NaturalNumber> removed = m.removeAny();
        while (m.size() != 0) {
            Map.Pair<String, NaturalNumber> test = m.removeAny();
            int x = removed.value().compareTo(test.value());
            if (x < 0) {
                temp.add(removed.key(), removed.value());
                removed = test;
            } else if (x > 0) {
                temp.add(test.key(), test.value());
            }
        }
        m.transferFrom(temp);

        removed = m.removeAny();
        while (m.size() != 0) {
            Map.Pair<String, NaturalNumber> test = m.removeAny();
            int x = removed.value().compareTo(test.value());
            if (x > 0) {
                temp.add(removed.key(), removed.value());
                removed = test;
            } else if (x < 0) {
                temp.add(test.key(), test.value());
            }
        }
        m.transferFrom(temp);

    }

    /**
     * Shifts entries between {@code leftStack} and {@code rightStack}, keeping
     * reverse of the former concatenated with the latter fixed, and resulting
     * in length of the former equal to {@code newLeftLength}.
     *
     * @param <T>
     *            type of {@code Stack} entries
     * @param leftStack
     *            the left {@code Stack}
     * @param rightStack
     *            the right {@code Stack}
     * @param newLeftLength
     *            desired new length of {@code leftStack}
     * @updates leftStack, rightStack
     * @requires <pre>
     * 0 <= newLeftLength  and
     * newLeftLength <= |leftStack| + |rightStack|
     * </pre>
     * @ensures <pre>
     * rev(leftStack) * rightStack = rev(#leftStack) * #rightStack  and
     * |leftStack| = newLeftLength}
     * </pre>
     */
    private static <T> void setLengthOfLeftStack(Stack<T> leftStack,
            Stack<T> rightStack, int newLeftLength) {
        if (!(leftStack.length() == newLeftLength)) {
            if (leftStack.length() > newLeftLength) {
                leftStack.flip();

                while (leftStack.length() != newLeftLength) {
                    T removed = leftStack.pop();
                    rightStack.push(removed);
                }
                leftStack.flip();

            } else {
                rightStack.flip();

                while (leftStack.length() != newLeftLength) {
                    T removed = rightStack.pop();
                    leftStack.push(removed);
                }
                rightStack.flip();

            }

        }
    }

    /**
     * Finds {@code x} in {@code q} and, if such exists, moves it to the front
     * of {@code q}.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to be searched
     * @param x
     *            the entry to be searched for
     * @updates q
     * @ensures <pre>
     * perms(q, #q)  and
     * if <x> is substring of q
     *  then <x> is prefix of q
     * </pre>
     */
//    private static <T> void moveToFront(Queue<T> q, T x) {
//        Queue<T> leftQ = q.newInstance();
//        Queue<T> rightQ = q.newInstance();
//
//        while (q.length() != 0) {
//            T removed = q.dequeue();
//            if (removed.equals(x)) {
//                leftQ.enqueue(removed);
//            } else {
//                rightQ.enqueue(removed);
//            }
//        }
//        leftQ.append(rightQ);
//        q.transferFrom(leftQ);
//    }
    /**
     * Finds pair with first component {@code key} and, if such exists, moves it
     * to the front of {@code q}.
     *
     * @param <K>
     *            type of {@code Pair} key
     * @param <V>
     *            type of {@code Pair} value
     * @param q
     *            the {@code Queue} to be searched
     * @param key
     *            the key to be searched for
     * @updates q
     * @ensures <pre>
     * perms(q, #q)  and
     * if there exists value: V (<(key, value)> is substring of q)
     *  then there exists value: V (<(key, value)> is prefix of q)
     * </pre>
     */
    private static <K, V> void moveToFront(Queue<Pair<K, V>> q, K key) {
        Queue<Pair<K, V>> tempQueue = new Queue1L<Pair<K, V>>();
        for (int i = 0; i < q.length(); i++) {
            Map.Pair<K, V> removed = q.dequeue();
            if (removed.key().equals(key)) {
                q.enqueue(removed);
            } else {
                tempQueue.enqueue(removed);
            }
            tempQueue.flip();
            q.append(tempQueue);
        }
    }

    /**
     * Computes {@code a} mod {@code b} as % should have been defined to work.
     *
     * @param a
     *            the number being reduced
     * @param b
     *            the modulus
     * @return the result of a mod b, which satisfies 0 <= {@code mod} < b
     * @requires b > 0
     * @ensures <pre>
     * 0 <= mod  and  mod < b  and
     * there exists k: integer (a = k * b + mod)
     * </pre>
     */
    public static int mod(int a, int b) {
        int result = a % b;

        if (a < 0) {
            result += b;
        }
        return result;
    }

    /**
     * Simple class representing a 7-digit phone number in the form "XXX-XXXX"
     * for a phone in the immediate OSU area.
     */
    public class PhoneNumber {

        /**
         * The phone number representation.
         */
        private String rep;

        /**
         * Constructor. {@code pNum} must be in the form "XXX-XXXX" where each
         * "X" is a digit '0'-'9'.
         */
        public PhoneNumber(String pNum) {
            this.rep = pNum;
        }

        @Override
        public int hashCode() {

            // TODO - fill in body
            int digit = -1;
            for (int i = 0; i < this.rep.length(); i++) {
                if (!(this.rep.charAt(i) == '-')) {
                    digit = Character.digit(this.rep.charAt(i), 9);
                }
            }
            return digit;
        }

    }

    /**
     * Returns the size of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose size to return
     * @return the size of the given {@code BinaryTree}
     * @ensures size = |t|
     */
    public static <T> int size(BinaryTree<T> t) {
        int size = 0;

        for (T element : t) {
            size++;
        }
        return size;
    }

    /**
     * Returns the {@code String} prefix representation of the given
     * {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} to convert to a {@code String}
     * @return the prefix representation of {@code t}
     * @ensures treeToString = [the String prefix representation of t]
     */
    public static <T> String treeToString(BinaryTree<T> t) {
        BinaryTree<T> tLeft = t.newInstance();
        BinaryTree<T> tRight = t.newInstance();
        String result = "()";
        if (t.size() != 0) {
            T root = t.disassemble(tLeft, tRight);
            result = root.toString() + '(' + treeToString(tLeft)
                    + treeToString(tRight) + ')';
            t.assemble(root, tLeft, tRight);
        }
        return result;
    }

    /**
     * Returns a copy of the the given {@code BinaryTree}.
     *
     * @param t
     *            the {@code BinaryTree} to copy
     * @return a copy of the given {@code BinaryTree}
     * @ensures copy = t
     */
    public static BinaryTree<Integer> copy(BinaryTree<Integer> t) {
        BinaryTree<Integer> tCopy = t.newInstance();
        BinaryTree<Integer> tLeft = t.newInstance();
        BinaryTree<Integer> tRight = t.newInstance();
        if (t.size() != 0) {
            int root = t.disassemble(tLeft, tRight);
            tCopy.assemble(root, copy(tLeft), copy(tRight));
            t.assemble(root, tLeft, tRight);
        }
        return tCopy;
    }

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    public static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        BinaryTree<T> tLeft = t.newInstance();
        BinaryTree<T> tRight = t.newInstance();

        boolean result = false;
        if (t.height() != 0) {
            T root = t.disassemble(tLeft, tRight);
            if (root.compareTo(x) == 0) {
                result = true;
            } else if (root.compareTo(x) > 0) {
                result = isInTree(tLeft, x);
            } else {
                result = isInTree(tRight, x);

            }
            t.assemble(root, tLeft, tRight);
        }

        return result;
    }

    /**
     * Partitions {@code q} into two parts: entries no larger than
     * {@code partitioner} are put in {@code front}, and the rest are put in
     * {@code back}.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to be partitioned
     * @param partitioner
     *            the partitioning value
     * @param front
     *            upon return, the entries no larger than {@code partitioner}
     * @param back
     *            upon return, the entries larger than {@code partitioner}
     * @param order
     *            ordering by which to separate entries
     * @clears q
     * @replaces front, back
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * perms(#q, front * back)  and
     * for all x: T where (<x> is substring of front)
     *  ([relation computed by order.compare method](x, partitioner))  and
     * for all x: T where (<x> is substring of back)
     *  (not [relation computed by order.compare method](x, partitioner))
     * </pre>
     */
    private static <T> void partition(Queue<T> q, T partitioner, Queue<T> front,
            Queue<T> back, Comparator<T> order) {
        while (q.length() != 0) {
            T digit = q.dequeue();
            if (order.compare(digit, partitioner) > 1) {
                back.enqueue(digit);
            } else {
                front.enqueue(digit);
            }
        }
    }

    /**
     * Sorts {@code this} according to the ordering provided by the
     * {@code compare} method from {@code order}.
     *
     * @param order
     *            ordering by which to sort
     * @updates this
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * perms(this, #this)  and
     * IS_SORTED(this, [relation computed by order.compare method])
     * </pre>
     */
    public void sort(Comparator<T> order) {
        Queue<T> front = this.newInstance();
        Queue<T> back = this.newInstance();
        /*
         * Dequeue the partitioning entry from this
         */
        T digit = this.dequeue();

        /*
         * Partition this into two queues as discussed above (you will need to
         * declare and initialize two new queues)
         */
        partition(this, digit, front, back, order);

        /*
         * Recursively sort the two queues
         */
        front.sort(order);
        back.sort(order);

        /*
         * Reconstruct this by combining the two sorted queues and the
         * partitioning entry in the proper order
         */
        this.enqueue(digit);
        this.append(front);
        this.append(back);
    }

    /**
     * Checks if the given {@code BinaryTree<Integer>} satisfies the heap
     * ordering property according to the <= relation.
     *
     * @param t
     *            the binary tree
     * @return true if the given tree satisfies the heap ordering property;
     *         false otherwise
     * @ensures <pre>
     * satisfiesHeapOrdering = [t satisfies the heap ordering property]
     * </pre>
     */
    private static boolean satisfiesHeapOrdering(BinaryTree<Integer> t) {
        boolean result = false;
        BinaryTree<Integer> tLeft = t.newInstance();
        BinaryTree<Integer> tRight = t.newInstance();

        if (t.size() != 0) {
            Integer root = t.disassemble(tLeft, tRight);

            if (root <= tLeft.root() && root <= tRight.root()) {
                result = satisfiesHeapOrdering(tLeft)
                        && satisfiesHeapOrdering(tRight);
            }
            t.assemble(root, tLeft, tRight);

        }

        return result;
    }

    /**
     * Creator of initial representation for Stack2.
     */
    private void createNewRep() {

        // TODO - fill in body
        this.top = null;
        this.length == 0;
    }

    @Override
    public final void push(T x) {
        assert x != null : "Violation of: x is not null";

        // TODO - fill in body
        Node digit = new Node();
        digit.data = x;
        digit.next = this.top;
        this.top = digit;
        this.length++;

    }

    @Override
    public final T pop() {
        assert this.length() > 0 : "Violation of: this /= <>";

        T result = this.top.data;
        this.top = this.top.next;
        this.length--;

        return result;

    }

    @Override
    public final int length() {
        return this.length;
    }

    @Test
    public final void testConstructor() {
        Stack<String> s = this.constructorTest();
        Stack<String> sExpected = this.constructorRef();

        assertEquals(sExpected, s);
    }

    @Test
    public final void testPushEmpty() {
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = this.createFromArgsRef("Hi");

        s.push("Hi");

        assertEquals(sExpected, s);
    }

    @Test
    public final void testPushNonEmpty() {
        Stack<String> s = this.createFromArgsTest("Hello");
        Stack<String> sExpected = this.createFromArgsRef("Hi", "Hello");

        s.push("Hi");

        assertEquals(sExpected, s);
    }

    @Test
    public final void testPopEmpty() {
        Stack<String> s = this.createFromArgsTest("Hi");
        Stack<String> sExpected = this.createFromArgsRef();

        String result = s.pop();

        assertEquals(sExpected, s);
        assertEquals("Hi", result);
    }

    @Test
    public final void testPopNonEmpty() {
        Stack<String> s = this.createFromArgsTest("Hi", "Goodbye");
        Stack<String> sExpected = this.createFromArgsRef("Goodbye");

        String result = s.pop();

        assertEquals(sExpected, s);
        assertEquals("Hi", result);
    }

    @Test
    public final void testLength() {
        Stack<String> s = this.createFromArgsTest("Hi", "Goodbye", "bye bye");

        int length = s.length();

        assertEquals(3, length);
    }

    /**
     * Retreats the position in {@code this} by one.
     *
     * @updates this
     * @requires this.left /= <>
     * @ensures <pre>
     * this.left * this.right = #this.left * #this.right  and
     * |this.left| = |#this.left| - 1
     * </pre>
     */
    public void retreat() {
        Node lastNode = this.preFront;

        while (lastNode.next != this.lastLeftNode) {
            //goes through next node until it reaches the lastLeftNode
            lastnode = lastNode.next;
        }

        this.lastLeftNode = lastNode;

        this.rightLength++;
        this.leftLength--;
    }

    /**
     * Returns the size of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose size to return
     * @return the size of the given {@code Tree}
     * @ensures size = |t|
     */
    public static <T> int size(Tree<T> t) {
        Sequence<Tree<T>> seqTree = t.newSequenceOfTree();
        int result = 0;

        if (t.height() != 0) {
            T root = t.disassemble(seqTree);
            for (int i = 0; i < seqTree.length(); i++) {
                result = result + size(seqTree.entry(i));
            }
            result += 1;
            t.assemble(root, seqTree);
        }
        return result;
    }

    /**
     * Returns the size of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose size to return
     * @return the size of the given {@code Tree}
     * @ensures size = |t|
     */
    public static <T> int size(Tree<T> t) {
        Sequence<Tree<T>> seqTree = t.newSequenceOfTree();
        int result = 0;

        T root = t.disassemble(seqTree);
        for (int j = 0; j < t.height(); j++) {
            for (int i = 0; i < seqTree.length(); i++) {
                result = result + size(seqTree.entry(i));
            }
            result++;
        }
        t.assemble(root, seqTree);

        return result;
    }

    /**
     * Returns the height of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose height to return
     * @return the height of the given {@code Tree}
     * @ensures height = ht(t)
     */
    public static <T> int height(Tree<T> t) {
        Sequence<Tree<T>> seqTree = t.newSequenceOfTree();
        int height = 0;
        int maxHeight = 0;
        if (t.size() != 0) {
            T root = t.disassemble(seqTree);
            for (int i = 0; i < seqTree.length(); i++) {
                if (height > maxHeight) {
                    maxHeight = 1 + height + height(seqTree.entry(i));
                }
            }
            height = maxHeight;
            t.assemble(root, seqTree);
        }

        return height;

    }

    /**
     * Returns the largest integer in the given {@code Tree<Integer>}.
     *
     * @param t
     *            the {@code Tree<Integer>} whose largest integer to return
     * @return the largest integer in the given {@code Tree<Integer>}
     * @requires |t| > 0
     * @ensures <pre>
     * max is in labels(t)  and
     * for all i: integer where (i is in labels(t)) (i <= max)
     * </pre>
     */
    public static int max(Tree<Integer> t) {
        Sequence<Tree<T>> seqTree = t.newSequenceOfTree();
        int max = 0;
        int currMax = 0;
        if (t.size() != 0) {
            T root = t.disassemble(seqTree);
            for (int i = 0; i < seqTree.length(); i++) {
                if (max > currMax) {
                    max = max(seqTree.entry(i));
                }
                if (root > max) {
                    max = root;
                }
                currMax = max;
            }
            t.assemble(root, seqTree);
        }
        return max;
    }

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures <pre>
     * countOfPrimitiveCalls =
     *  [number of calls to primitive instructions in s]
     * </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {

                for (int i = 0; i < s.lengthOfBlock(); i++) {
                    Statement call = s.removeFromBlock(i);
                    count += countOfPrimitiveCalls(call);
                    s.addToBlock(i, call);
                }

                break;
            }
            case IF: {

                Statement call = new Statement1();

                Statement.Condition c = s.disassembleIf(call);
                count = countOfPrimitiveCalls(call);
                s.assembleIf(c, call);

                break;
            }
            case IF_ELSE: {

                Statement call = new Statement1();
                Statement call2 = new Statement1();

                Statement.Condition c = s.disassembleIfElse(call, call2);
                count = countOfPrimitiveCalls(call)
                        + countOfPrimitiveCalls(call2);

                s.assembleIfElse(c, call, call2);

                break;
            }
            case WHILE: {

                Statement call = s.newInstance();
                Statement.Condition c = s.disassembleWhile(call);

                count = countOfPrimitiveCalls(call);
                s.assembleWhile(c, call);

                break;
            }
            case CALL: {

                String call = s.disassembleCall();
                if (call.equals("move") || call.equals("turnright")
                        || call.equals("turnleft") || call.equals("infect")
                        || call.equals("skip")) {
                    count++;
                }
                s.assembleCall(call);
                break;
            }
            default: {
                // this will never happen...can you explain why?

                //yes because all of the possible cases are already
                //covered with the switch cases
                break;
            }
        }
        return count;
    }

    /**
     * Refactors the given {@code Statement} so that every IF_ELSE statement
     * with a negated condition (NEXT_IS_NOT_EMPTY, NEXT_IS_NOT_ENEMY,
     * NEXT_IS_NOT_FRIEND, NEXT_IS_NOT_WALL) is replaced by an equivalent
     * IF_ELSE with the opposite condition and the "then" and "else" BLOCKs
     * switched. Every other statement is left unmodified.
     *
     * @param s
     *            the {@code Statement}
     * @updates s
     * @ensures <pre>
     * s = [#s refactored so that IF_ELSE statements with "not"
     *   conditions are simplified so the "not" is removed]
     * </pre>
     */
    public static void simplifyIfElse(Statement s) {
        switch (s.kind()) {
            case BLOCK: {

                for (int i = 0; i < s.lengthOfBlock(); i++) {
                    Statement call = s.removeFromBlock(i);
                    simplifyIfElse(call);
                    s.addToBlock(i, call);
                }

                break;
            }
            case IF: {

                Statement call = new Statement1();

                Statement.Condition c = s.disassembleIf(call);
                simplifyIfElse(call);
                s.assembleIf(c, call);

                break;
            }
            case IF_ELSE: {

                Statement call1 = new Statement1();
                Statement call2 = new Statement1();
                Statement.Condition cond = s.disassembleIfElse(call1, call2);

                switch (cond.name()) {
                    case "NEXT_IS_NOT_EMPTY": {
                        cond = Condition.NEXT_IS_EMPTY;
                        simplifyIfElse(call1);
                        simplifyIfElse(call2);
                        s.assembleIfElse(cond, call2, call1);
                        break;
                    }

                    case "NEXT_IS_NOT_WALL": {
                        cond = Condition.NEXT_IS_WALL;
                        simplifyIfElse(call1);
                        simplifyIfElse(call2);
                        s.assembleIfElse(cond, call2, call1);
                        break;
                    }

                    case "NEXT_IS_NOT_FRIEND": {
                        cond = Condition.NEXT_IS_FRIEND;
                        simplifyIfElse(call1);
                        simplifyIfElse(call2);
                        s.assembleIfElse(cond, call2, call1);
                        break;
                    }

                    case "NEXT_IS_NOT_ENEMY": {
                        cond = Condition.NEXT_IS_ENEMY;
                        simplifyIfElse(call1);
                        simplifyIfElse(call2);
                        s.assembleIfElse(cond, call2, call1);
                        break;
                    }

                }

                break;

            }
            case WHILE: {

                Statement call = s.newInstance();
                Statement.Condition c = s.disassembleWhile(call);

                simplifyIfElse(call);
                s.assembleWhile(c, call);

                break;
            }
            case CALL: {
                // nothing to do here...can you explain why?
                //because a call cannot be simplified to an if else with switched cases
                break;
            }
            default: {
                // this will never happen...can you explain why?
                //because the switch handles all possible cases and will
                //never reach the default
                break;
            }
        }
    }

    public void renameInstruction(Program s, String oldName, String newName) {

        Map<String, Statement> ctxt = s.newContext();
        s.swapContext(ctxt);
        for (int i = 0; i < ctxt.size(); i++) {
            Map.Pair<String, Statement> pair = ctxt.removeAny();
            if (pair.key().equals(oldName)) {
                ctxt.add(newName, pair.value());
            } else {
                ctxt.add(oldName, pair.value());
            }
            this.renameInstruction(pair.value(), oldName, newName);
        }
        s.swapContext(ctxt);
        Statement body = s.newBody();
        s.swapBody(body);
        this.renameInstruction(body, oldName, newName);
        s.swapBody(body);

    }

    public void prettyPrint(SimpleWriter out, int offset) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";
        assert offset >= 0 : "Violation of: 0 <= offset";

        int indent = Program.INDENT_SIZE;
        switch (this.kind()) {
            case BLOCK: {

                int length = this.lengthOfBlock();
                for (int i = 0; i < length; i++) {
                    Statement sub = this.removeFromBlock(i);
                    sub.prettyPrint(out, offset);
                    this.addToBlock(i, sub);
                }
                break;
            }
            case IF: {

                Statement sub = this.newInstance();
                Condition cond = this.disassembleIf(sub);
                printSpaces(out, offset);

                out.println("IF " + toStringCondition(cond));
                sub.prettyPrint(out, offset + indent);

                //prints more spaces for the offset
                for (int i = 0; i < offset; i++) {
                    out.print(" ");
                }

                out.println("END IF");

                this.assembleIf(cond, sub);
                break;
            }
            case IF_ELSE: {

                Statement sub1 = this.newInstance();
                Statement sub2 = this.newInstance();
                Condition cond = this.disassembleIfElse(sub1, sub2);
                printSpaces(out, offset);

                out.println("IF " + toStringCondition(cond) + " THEN");
                sub1.prettyPrint(out, offset + indent);

                printSpaces(out, offset);

                out.println("ELSE");
                sub2.prettyPrint(out, offset + indent);

                printSpaces(out, offset);

                out.println("END IF");
                this.assembleIfElse(cond, sub1, sub2);

                break;
            }
            case WHILE: {

                Statement sub = this.newInstance();
                Condition cond = this.disassembleWhile(subTree);
                printSpaces(out, offset);

                out.println("WHILE " + toStringCondition(cond) + " DO");
                subTree.prettyPrint(out, offset + indent);

                printSpaces(out, offset);
                out.println("END WHILE");
                this.assembleWhile(cond, sub);

                break;
            }
            case CALL: {

                String call = this.disassembleCall();
                printSpaces(out, offset);
                out.println(call);
                this.assembleCall(call);

                break;
            }
            default: {
                // this will never happen...
                break;
            }
        }
    }

    /**
     * Refactors the given {@code Statement} by renaming every occurrence of
     * instruction {@code oldName} to {@code newName}. Every other statement is
     * left unmodified.
     *
     * @param s
     *            the {@code Statement}
     * @param oldName
     *            the name of the instruction to be renamed
     * @param newName
     *            the new name of the renamed instruction
     * @updates s
     * @requires [newName is a valid IDENTIFIER]
     * @ensures <pre>
     * s = [#s refactored so that every occurrence of instruction oldName
     *   is replaced by newName]
     * </pre>
     */
    public static void renameInstruction(Statement s, String oldName,
            String newName) {
        switch (this.kind()) {
            case BLOCK: {

                for (int i = 0; i < s.lengthOfBlock(); i++) {
                    Statement sub = s.removeFromBlock(i);
                    renameInstruction(sub, oldName, newName);
                    s.addToBlock(i, sub);

                break;
            }
            case IF: {

                Statement sub = s.newInstance();
                Condition cond = s.disassembleIf(sub);
                renameInstruction(sub, oldName, newName);
                s.assembleIf(cond, sub);

            }
            case IF_ELSE: {
                Statement sub1 = s.newInstance();
                Statement sub2 = s.newInstance();

                Condition cond1 = s.disassembleIfElse(sub1, sub2);
                renameInstruction(sub1, oldName, newName);
                renameInstruction(sub2, oldName, newName);

                s.assembleIfElse(cond, sub1, sub2);
                break;
            }
            case WHILE: {
                Statement sub = s.newInstance();
                Condition whileCondition = s.disassembleWhile(sub);
                renameInstruction(sub, oldName, newName);
                s.assembleWhile(whileCondition, sub);
                break;
            }
            case CALL: {
                String call = s.disassembleCall();
                if (call.equals(oldName)) {
                    s.assembleCall(newName);
                } else {
                    s.assembleCall(call);
                }

                break;
            }
            default: {
                // this will never happen...
                break;
            }
        }
    }

    /**
     * Tokenizes the entire input getting rid of all whitespace separators and
     * returning the non-separator tokens in a {@code Queue<String>}.
     *
     * @param in
     *            the input stream
     * @return the queue of tokens
     * @updates in.content
     * @requires in.is_open
     * @ensures <pre>
     * tokens =
     *   [the non-whitespace tokens in #in.content] * <END_OF_INPUT>  and
     * in.content = <>
     * </pre>
     */
    public static Queue<String> tokens(SimpleReader in) {

        //use nextWordOrSeparator

        Queue<String> result = new Queue1L<String>();
        while (in.atEOS()) {
            int position = 0;
            String text = in.nextLine();
            while (position < text.length()) {
                result.enqueue(nextWordOrSeparator(text, position));
                position += (nextWordOrSeparator(text, position)).length();

            }
        }
        result.enqueue(END_OF_INPUT);
        return result;

    }

    /**
     * Evaluates an expression and returns its value.
     *
     * @param source
     *            the {@code StringBuilder} that starts with an expr string
     * @return value of the expression
     * @updates source
     * @requires <pre>
     * [an expr string is a proper prefix of source, and the longest
     * such, s, concatenated with the character following s, is not a prefix
     * of any expr string]
     * </pre>
     * @ensures <pre>
     * valueOfExpr =
     *   [value of longest expr string at start of #source]  and
     * #source = [longest expr string at start of #source] * source
     * </pre>
     */
    public static int valueOfExpr(StringBuilder source) {
        int value = valueOfTerm(source);
        while (source.charAt(0) == ('+') || source.charAt(0) == ('-')) {
            char op = source.charAt(0);
            int nextTerm = valueOfTerm(source);
            source.deleteCharAt(0);
            if (op == ('+')) {
                value += nextTerm;
            } else {
                value -= nextTerm;

            }
        }
        return value;
    }

    /**
     * Evaluates a term and returns its value.
     *
     * @param source
     *            the {@code StringBuilder} that starts with a term string
     * @return value of the term
     * @updates source
     * @requires <pre>
     * [a term string is a proper prefix of source, and the longest
     * such, s, concatenated with the character following s, is not a prefix
     * of any term string]
     * </pre>
     * @ensures <pre>
     * valueOfTerm =
     *   [value of longest term string at start of #source]  and
     * #source = [longest term string at start of #source] * source
     * </pre>
     */
    private static int valueOfTerm(StringBuilder source) {
        int value = valueOfFactor(source);
        while (source.charAt(0) == ('*') || source.charAt(0) == ('/')) {
            char op = source.charAt(0);
            int nextFactor = valueOfFactor(source);
            source.deleteCharAt(0);
            if (op == ('*')) {
                value *= nextFactor;
            } else {
                value /= nextFactor;

            }
        }
        return value;

    }

    /**
     * Evaluates a factor and returns its value.
     *
     * @param source
     *            the {@code StringBuilder} that starts with a factor string
     * @return value of the factor
     * @updates source
     * @requires <pre>
     * [a factor string is a proper prefix of source, and the longest
     * such, s, concatenated with the character following s, is not a prefix
     * of any factor string]
     * </pre>
     * @ensures <pre>
     * valueOfFactor =
     *   [value of longest factor string at start of #source]  and
     * #source = [longest factor string at start of #source] * source
     * </pre>
     */
    private static int valueOfFactor(StringBuilder source) {
        int value = 0;
        if (source.charAt(0) == '(') {
            source.deleteCharAt(0);
            value = valueOfExpr(source);
            source.deleteCharAt(0);
        } else {
            value = valueOfDigitSeq(source);
        }

        return value;

    }

    /**
     * Evaluates a digit sequence and returns its value.
     *
     * @param source
     *            the {@code StringBuilder} that starts with a digit-seq string
     * @return value of the digit sequence
     * @updates source
     * @requires <pre>
     * [a digit-seq string is a proper prefix of source, which
     * contains a character that is not a digit]
     * </pre>
     * @ensures <pre>
     * valueOfDigitSeq =
     *   [value of longest digit-seq string at start of #source]  and
     * #source = [longest digit-seq string at start of #source] * source
     * </pre>
     */
    private static int valueOfDigitSeq(StringBuilder source) {
        int value = 0;
        String num = "";
        while (Character.isDigit(source.charAt(0))) {
            value = valueOfDigit(source);
            num += Integer.toString(value);
        }
        value = Integer.parseInt(num);
        return value;

    }

    /**
     * Evaluates a digit and returns its value.
     *
     * @param source
     *            the {@code StringBuilder} that starts with a digit
     * @return value of the digit
     * @updates source
     * @requires 1 < |source| and [the first character of source is a digit]
     * @ensures <pre>
     * valueOfDigit = [value of the digit at the start of #source]  and
     * #source = [digit string at start of #source] * source
     * </pre>
     */
    private static int valueOfDigit(StringBuilder source) {

        int value = Character.digit(source.charAt(0), 10);
        source.deleteCharAt(0);

        return value;
    }

    /**
     * Evaluates a Boolean expression and returns its value.
     *
     * @param tokens
     *            the {@code Queue<String>} that starts with a bool-expr string
     * @return value of the expression
     * @updates tokens
     * @requires [a bool-expr string is a prefix of tokens]
     * @ensures <pre>
     * valueOfBoolExpr =
     *   [value of longest bool-expr string at start of #tokens]  and
     * #tokens = [longest bool-expr string at start of #tokens] * tokens
     * </pre>
     */
    public static boolean valueOfBoolExpr(Queue<String> tokens) {
        boolean result = true;
        while (tokens.length() > 0) {
            switch (tokens.dequeue()) {
                case "T": {
                    result = true;
                    break;
                }
                case "F": {
                    result = false;
                    break;
                }
                case "NOT": {
                    result = !valueOfBoolExpr(tokens);
                    break;
                }
                case "(": {
                    result = valueOfBoolExpr(tokens);
                    break;
                }
                case ")": {
                    break;
                    //basically does nothing since it sees the closing parenthesis
                }
                case "AND": {
                    result = valueOfBoolExpr(tokens);
                    break;
                }
                case "OR": {
                    result = valueOfBoolExpr(tokens);
                    break;
                }
                default:
                    //doesnt reach this case ever besides EOI because of CF grammar
                    break;
            }
        }

        return result;
    }

    /**
     * BugsWorld possible cell states.
     */
    enum CellState {
        EMPTY, WALL, FRIEND, ENEMY;
    }

    /**
     * Returns whether the given integer is the byte code of a BugsWorld virtual
     * machine primitive instruction (MOVE, TURNLEFT, TURNRIGHT, INFECT, SKIP,
     * HALT).
     *
     * @param byteCode
     *            the integer to be checked
     * @return true if {@code byteCode} is the byte code of a primitive
     *         instruction or false otherwise
     * @ensures <pre>
     * isPrimitiveInstructionByteCode =
     *  [true iff byteCode is the byte code of a primitive instruction]
     * </pre>
     */
    private static boolean isPrimitiveInstructionByteCode(int byteCode) {...}

    /**
     * Returns the value of the condition in the given conditional jump
     * {@code condJump} given what the bug sees {@code wbs}. Note that if
     * {@code condJump} is the byte code for the conditional jump
     * JUMP_IF_NOT_condition, the value returned is the value of the "condition"
     * part of the jump instruction.
     *
     * @param wbs
     *            the {@code CellState} indicating what the bug sees
     * @param condJump
     *            the byte code of a conditional jump
     * @return the value of the conditional jump condition
     * @requires [condJump is the byte code of a conditional jump]
     * @ensures <pre>
     * conditionalJumpCondition =
     *  [the value of the condition of condJump given what the bug sees wbs]
     * </pre>
     */
    private static boolean conditionalJumpCondition(CellState wbs, int condJump) {...}

    /**
     * Returns the location of the next primitive instruction to execute in
     * compiled program {@code cp} given what the bug sees {@code wbs} and
     * starting from location {@code pc}.
     *
     * @param cp
     *            the compiled program
     * @param wbs
     *            the {@code CellState} indicating what the bug sees
     * @param pc
     *            the program counter
     * @return the location of the next primitive instruction to execute
     * @requires <pre>
     * [cp is a valid compiled BL program]  and
     * 0 <= pc < cp.length  and
     * [pc is the location of an instruction byte code in cp, that is, pc
     *  cannot be the location of an address]
     * </pre>
     * @ensures <pre>
     * [return the address of the next primitive instruction that
     *  should be executed in program cp given what the bug sees wbs and
     *  starting execution at address pc in program cp]
     * </pre>
     */
    public static int nextPrimitiveInstructionAddress(int[] cp, CellState wbs,
            int pc) {
        int result;
        int curr = cp[pc];
        for (int i = 0; i < cp.length; i++) {
            if (isPrimitiveInstructionByteCode(curr)) {
                result = cp[i];
            } else if (conditionalJumpCondition(wbs, curr)) {
                curr += 2;
                curr = nextPrimitiveInstructionAddress(cp, wbs, curr);
            }

        }

        return result;

    }

    }

    @Override
    public final void push(T x) {
        assert x != null : "Violation of: x is not null";
     Node p = new Node();
     p.data = x


     this.length()++;


    }

    /**
     * Generates the sequence of virtual machine instructions ("byte codes")
     * corresponding to {@code s} and appends it at the end of {@code cp}.
     *
     * @param s
     *            the {@code Statement} for which to generate code
     * @param context
     *            the {@code Context} in which to find user defined instructions
     * @param cp
     *            the {@code Sequence} containing the generated code
     * @updates cp
     * @ensures <pre>
     * if [all instructions called in s are either primitive or
     *     defined in context]  and
     *    [context does not include any calling cycles, i.e., recursion] then
     *  cp = #cp * [sequence of virtual machine "byte codes" corresponding to s]
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void generateCodeForStatement(Statement s,
            Map<String, Statement> context, Sequence<Integer> cp) {

        final int dummy = 0;

        switch (s.kind()) {
            case BLOCK: {

                // TODO - fill in case
                for (int i = 0; i < s.lengthOfBlock(); i++) {
                    Statement block = s.removeFromBlock(i);
                    generateCodeForStatement(block, context, cp);
                    s.addToBlock(i, block);
                }

                break;
            }
            case IF: {
                Statement b = s.newInstance();
                Condition c = s.disassembleIf(b);
                cp.add(cp.length(), conditionalJump(c).byteCode());
                int jump = cp.length();
                cp.add(cp.length(), dummy);
                generateCodeForStatement(b, context, cp);
                cp.replaceEntry(jump, cp.length());
                s.assembleIf(c, b);
                break;
            }
            case IF_ELSE: {

                // TODO - fill in case
                Statement b = s.newInstance();
                Statement c = s.newInstance();
                Condition cond = s.disassembleIfElse(b, c);
                cp.add(cp.length(), conditionalJump(cond).byteCode());
                int jumpOne = cp.length();
                cp.add(cp.length(), dummy);
                generateCodeForStatement(c, context, cp);
                cp.add(cp.length(), Instruction.valueOf("JUMP").byteCode());
                int jumpTwo = cp.length();
                cp.add(cp.length(), dummy);

                cp.replaceEntry(jumpTwo, cp.length());
                s.assembleIfElse(cond, b, c);
                break;

            }
            case WHILE: {

                // TODO - fill in case
                Statement b = s.newInstance();
                Condition cond = s.disassembleWhile(b);
                cp.add(cp.length(), conditionalJump(cond).byteCode());
                int jump = cp.length();
                cp.add(cp.length(), dummy);
                generateCodeForStatement(b, context, cp);
                cp.replaceEntry(jump, cp.length());
                s.assembleWhile(cond, b);
                break;
            }
            // remaining case CALL goes here
        }
    }

    /**
     * FIFO ordering for waiting line component with primary methods.
     *
     * @param <T>
     *            type of {@code WaitingLineKernel} entries (): ensures this =
     *            <> </pre>
     * @iterator ~this.seen * ~this.unseen = this
     */
    public interface WaitingLineKernel<T>
            extends Standard<WaitingLine<T>>, Iterable<T> {

        @Override
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof Queue<?>)) {
                return false;
            }
            Queue<?> q = (Queue<?>) obj;
            if (this.lengthLine() != q.length()) {
                return false;
            }
            Iterator<T> it1 = this.iterator();
            Iterator<?> it2 = q.iterator();
            while (it1.hasNext()) {
                T x1 = it1.next();
                Object x2 = it2.next();
                if (!x1.equals(x2)) {
                    return false;
                }
            }
            return true;
        }

        // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
        @Override
        public int hashCode() {
            final int samples = 2;
            final int a = 37;
            final int b = 17;
            int result = 0;
            /*
             * This code makes hashCode run in O(1) time. It works because of the
             * iterator order string specification, which guarantees that the (at
             * most) samples entries returned by the it.next() calls are the same
             * when the two Queues are equal.
             */
            int n = 0;
            Iterator<T> it = this.iterator();
            while (n < samples && it.hasNext()) {
                n++;
                T x = it.next();
                result = a * result + b * x.hashCode();
            }
            return result;
        }

        // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
        @Override
        public String toString() {
            StringBuilder result = new StringBuilder("<");
            Iterator<T> it = this.iterator();
            while (it.hasNext()) {
                result.append(it.next());
                if (it.hasNext()) {
                    result.append(",");
                }
            }
            result.append(">");
            return result.toString();
        }

        /**
         * Find the position of the {@code entry} in {@code this}
         *
         * @param entry
         *            the entry being looked for
         * @return the position of the {@code entry} in {@code this}
         * @requires <pre>
         * {@code  this /= <>}
         * </pre>
         * @ensures <pre>
         * {@code position = position of customer in this}
         * </pre>
         */
        int findPos(T entry) {
            int result = 0;

            for (int i = 0; i < this.lengthLine(); i++) {
                if (this.front().equals(entry)) {
                    result = i;
                }
                this.addPerson(this.removeFrontPerson());
            }
            return result;
        }

        /**
         * Adds {@code person} to the end of {@code this} if and only if
         * {@code this} does not contain {@code person}.
         *
         * @param person
         *            the entry to be added
         * @aliases reference {@code person}
         * @updates {@code this}
         * @requires <pre>
         * {@code this does not contain person}
         * @ensures
         * {@code this = #this * <person>}
         * </pre>
         */
        void addPerson(T person)

        /**
         * Removes {@code person} from the front of {@code this}.
         *
         * @return the entry removed
         * @updates {@code this}
         * @requires <pre>
         * {@code this /= <>}
         * </pre>
         * @ensures <pre>
         * {@code #this = <removeFrontPerson> * this}
         * </pre>
         */
        T removeFrontPerson();

        /**
         * Reports the front of {@code this}.
         *
         * @return the front entry of {@code this}
         * @aliases reference returned by {@code front}
         * @requires <pre>
         * {@code this /= <>}
         * </pre>
         * @ensures <pre>
         * {@code <front> is prefix of this}
         * </pre>
         */
        T frontPerson();

        /**
         * Reports length of {@code this}.
         *
         * @return the length of {@code this}
         * @ensures <pre>
         * {@code length = |this|}
         * </pre>
         */
        int lengthLine();

    }

    /**
     * {@code WaitingLineKernel} enhanced with secondary methods.
     *
     * @param <T>
     *            type of {@code WaitingLine} entries
     */
    public interface WaitingLine<T>
            extends WaitingLineKernel, QueueKernel, Queue<T> {

        /**
         * Find the position of the {@code entry} in {@code this}
         *
         * @param entry
         *            the entry being looked for
         * @return the position of the {@code entry} in {@code this}
         * @requires <pre>
         * {@code  this /= <>}
         * </pre>
         * @ensures <pre>
         * {@code position = position of customer in this}
         * </pre>
         */
        int findPos(T entry);

        /**
         * Raises the salary of all the employees in {@code map} whose name
         * starts with the given {@code initial} by the given
         * {@code raisePercent}.
         *
         * @param map
         *            the name to salary map
         * @param initial
         *            the initial of names of employees to be given a raise
         * @param raisePercent
         *            the raise to be given as a percentage of the current
         *            salary
         * @updates map
         * @requires [the salaries in map are positive] and raisePercent > 0
         * @ensures <pre>
         * DOMAIN(map) = DOMAIN(#map)  and
         * [the salaries of the employees in map whose names start with the given
         *  initial have been increased by raisePercent percent (and truncated to
         *  the nearest integer); all other employees have the same salary]
         * </pre>
         */
        private static void giveRaise(components.map.Map<String, Integer> map,
                char initial, int raisePercent) {
            double decimal = raisePercent * 0.01 + 1;

            for (int i = 0; i < map.size(); i++) {
                Map.Pair<String, Integer> pair = map.removeAny();
                if (pair.key().charAt(0) == initial) {
                    map.add(pair.key(), (pair.value() * decimal));
                } else {
                    map.add(pair.key(), pair.value());
                }
            }
        }

        /**
         * Raises the salary of all the employees in {@code map} whose name starts
         * with the given {@code initial} by the given {@code raisePercent}.
         *
         * @param map
         *            the name to salary map
         * @param initial
         *            the initial of names of employees to be given a raise
         * @param raisePercent
         *            the raise to be given as a percentage of the current salary
         * @updates map
         * @requires <pre>
         * [the salaries in map are positive]  and  raisePercent > 0  and
         * [the dynamic types of map and of all objects reachable from map
         *  (including any objects returned by operations (such as entrySet() and,
         *  from there, iterator()), and so on, recursively) support all
         *  optional operations]
         * </pre>
         * @ensures <pre>
         * DOMAIN(map) = DOMAIN(#map)  and
         * [the salaries of the employees in map whose names start with the given
         *  initial have been increased by raisePercent percent (and truncated to
         *  the nearest integer); all other employees have the same salary]
         * </pre>
         */
        private static void giveRaise(java.util.Map<String, Integer> map,
                char initial, int raisePercent) {
            double decimal = raisePercent * 0.01 + 1;

            //can use for-each loop
            for (Map.Entry<String,Integer> pair : map.entrySet()){
                if (pair.getKey().charAt(0) == initial){
                    pair.setValue(pair.getValue() * decimal);
                }
            }

//           Write a main program that copies a given text file into another file using SimpleReader to read the
//            input file and SimpleWriter to write the output file. The names of the input text file to be copied
//            and of the destination file where the copy is to be saved are provided as command-line arguments.
//            Assume that appropriate arguments will be provided and no error checking is necessary.
//            The command-line arguments are accessible by your main program through the String[] args array
//            parameter to the main method.

        public static void main(String[] args) {

            SimpleReader in = new SimpleReader1L(args[0]);
            SimpleWriter out = new SimpleWriter1L(args[1]);

            while (!in.atEOS()) {
                String line = in.nextLine();
                out.println(line);

            }

        }

        //close streams
        in.close();out.close();

    }

//        //Rewrite the file-copying main program using only the standard java.io classes discussed
//        in class. Assume that appropriate arguments will be provided and no error checking is necessary.
//        Do not handle the possible IOExceptions but simply declare that main may throw an IOException.

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        BufferedReader input;

        input = new BufferedReader(new FileReader(args[0]));

        PrintWriter output = null;
        output = new PrintWriter(new BufferedWriter(new FileWriter(args[1])));

        String s = input.readLine();
        while (s != null) {
            output.println(s);

            //starts reading next line
            s = input.readLine();
        }

        //close streams
        input.close();
        output.close();

    }

    public static voic main(String[] args) {
        Scanner in = new Scanner(System.in);

        BufferedReader input;
        try {
            input = new BufferedReader(new FileReader(args[0]));
        } catch (IOException e) {
            System.err.println("Error opening file.");
            in.close();
            return;
        }

        PrintWriter output = null;
        try {
            output = new PrintWriter(
                    new BufferedWriter(new FileWriter(args[1])));
        } catch (IOException e) {
            System.err.println("Error creating/accessing output file.");
        }
        String s = input.readLine();
        while (s != null) {
            output.println(s);

            //starts reading next line
            s = input.readLine();
        }

        //close streams
        input.close();
        output.close();
    }

    /**
     * Implementation of {@code EmailAccount}.
     *
     * @author Anurag Kejriwal.
     *
     */

//  The integers used in email addresses follow the standard OSU naming strategy. That is, the smallest positive integer that makes an
// address unique is used. For example, if the first two email accounts instantiated are for Alice Scarlet and Bob Gray, both would have a
// "1" in their email address since the last names are sufficient to distinguish them. Only if a second account with a last name of Scarlet
// (or Gray) is instantiated would a "2" be used for the new account's email address.
// It is possible to have several people with the same last name (e.g., Alice Scarlet and Jane Scarlet) and even several people with the same full
// name (e.g., three Bob Gray accounts, each getting its separate email address gray.1, gray.2, and gray.3).
// The names provided to the constructor can use arbitrary case (e.g., Scarlet or SCARLET or ScArLeT). Whatever case was used by the client
// should be used in the strings returned by name and toString. However, the email address should only include lower case letters.
// Numbers that have been used previously for some email address cannot be reused for the same last name.

    public final class EmailAccount1 implements EmailAccount {

        /*
         * Private members
         * --------------------------------------------------------
         */

        // TODO - declare static and instance data members

        private static Map<String, Integer> emailMap = new HashMap<>();
        private static String firstName;
        private static String lastName;
        private static String emailAddress;

        /*
         * Constructor
         * ------------------------------------------------------------
         */

        /**
         * Constructor.
         *
         * @param firstName
         *            the first name
         * @param lastName
         *            the last name
         */
        public EmailAccount1(String firstName, String lastName) {

            // TODO - fill in body
            this.firstName = firstName;
            this.lastName = lastName;

            //if else needed to check if last name already exists.
            if (emailMap.containsKey(lastName.toLowerCase())) {
                int lastNameVal = emailMap.getValue(lastName.toLowerCase());
                lastNameVal++;
                this.address = lastName.toLowerCase() + "." + lastNameVal
                        + "@osu.edu";
                emailMap.replace(lastName.toLowerCase(), lastNameVal);
            } else {
                emailMap.add(lastName.toLowerCase(), 1);
                this.address = lastName.toLowerCase() + ".1@osu.edu";
            }

        }

        /*
         * Methods
         * ----------------------------------------------------------------
         */

        @Override
        public String name() {

            // TODO - fill in body
            String name = this.firstName + " " + this.lastName;
            return name;

        }

        @Override
        public String emailAddress() {

            // TODO - fill in body
            return this.emailAddress;

        }

        @Override
        public String toString() {

            // TODO - fill in body
            String result = "name is: " + this.firstName + " " + this.lastName
                    + " email is: " + this.address;
            return result;

        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        NaturalNumber num = new NaturalNumber2(10);
        foo(num);
        out.println(num);

        NaturalNumber num2 = new NaturalNumber2(9984);
        out.println(sumDigits(num2));

        Queue<Integer> queue1 = new Queue1L<>();
        queue1.enqueue(6);
        queue1.enqueue(7);
        queue1.enqueue(3);
        queue1.enqueue(9);

        out.println(reverse(queue1));

        Stack<Integer> test3 = new Stack1L<>();
        test3.push(8);
        out.println(fillerZero(test3));

        Stack<Integer> test2 = new Stack1L<>();
        test2.push(8);
        test2.push(6);
        test2.push(5);
        filler(test2);
        out.println(test2);

        Map<String, String> test4 = new Map1L<>();
        test4.add("Hello", "Hello");
        test4.add("Monkey", "Money");
        out.println(test4);
        removePairs(test4);
        out.println("\n" + test4);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
