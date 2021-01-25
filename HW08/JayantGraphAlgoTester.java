import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Jayant Tandon
 * @version 1.3
 */

@RunWith(JayantGraphAlgoTester.class)
@Suite.SuiteClasses({JayantGraphAlgoTester.Size1.class, JayantGraphAlgoTester.Size2.class,
        JayantGraphAlgoTester.BigGraph.class, JayantGraphAlgoTester.TestExceptions.class,
        JayantGraphAlgoTester.TestParrallelEdges.class, JayantGraphAlgoTester.TestSelfLoop.class,
        JayantGraphAlgoTester.TestDisconnected.class, JayantGraphAlgoTester.TestMix.class})
public class JayantGraphAlgoTester extends Suite {
    private static final int TIMEOUT = 200;
    private static Graph<Character> directedGraph;
    private static Graph<Character> undirectedGraph;


    public JayantGraphAlgoTester(Class<?> klass, RunnerBuilder builder) throws InitializationError {
        super(klass, builder);
    }

    public static class TestExceptions {
        @Before
        public void setUp() {
            Set<Vertex<Character>> vertices = new HashSet<>();
            Set<Edge<Character>> edges = new HashSet<>();
            directedGraph = new Graph<>(vertices, edges);
            undirectedGraph = new Graph<>(vertices, edges);
        }

        @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
        public void testDFS() {
            GraphAlgorithms.dfs(null, directedGraph);
            GraphAlgorithms.dfs(new Vertex<>('A'), null);
            GraphAlgorithms.dfs(new Vertex<>('A'), directedGraph);
        }

        @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
        public void testDijkstra() {
            GraphAlgorithms.dijkstras(null, directedGraph);
            GraphAlgorithms.dijkstras(new Vertex<>(0), null);
            GraphAlgorithms.dijkstras(new Vertex<>('A'), directedGraph);
        }

        @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
        public void testPrim() {
            GraphAlgorithms.prims(null, directedGraph);
            GraphAlgorithms.prims(new Vertex<>(0), null);
            GraphAlgorithms.prims(new Vertex<>('A'), directedGraph);
        }
    }

    public static class Size1 {
        @Before
        public void setUp() {
            Set<Vertex<Character>> vertices = new HashSet<>();
            Set<Edge<Character>> edges = new HashSet<>();

            vertices.add(new Vertex<>('A'));

            directedGraph = new Graph<>(vertices, edges);
            undirectedGraph = new Graph<>(vertices, edges);
        }

        @Test(timeout = TIMEOUT)
        public void testDFS() {
            assertEquals(Arrays.asList(new Vertex<>('A')),
                    GraphAlgorithms.dfs(new Vertex<>('A'), undirectedGraph));
            assertEquals(Arrays.asList(new Vertex<>('A')),
                    GraphAlgorithms.dfs(new Vertex<>('A'), directedGraph));
        }

        @Test(timeout = TIMEOUT)
        public void testDijk() {
            HashMap<Vertex<Character>, Integer> mapChar = new HashMap<>();
            mapChar.put(new Vertex<>('A'), 0);
            assertEquals(mapChar, GraphAlgorithms.dijkstras(new Vertex<>('A'), undirectedGraph));
            assertEquals(mapChar, GraphAlgorithms.dijkstras(new Vertex<>('A'), directedGraph));
        }

        @Test(timeout = TIMEOUT)
        public void testPrim() {
            assertEquals(new HashSet<>(), GraphAlgorithms.prims(new Vertex<>('A'), undirectedGraph));
        }

    }

    public static class Size2 {
        @Before
        public void setUp() {
            Set<Vertex<Character>> vertices = new HashSet<>();
            Set<Edge<Character>> edges = new HashSet<>();
            Set<Edge<Character>> edges1 = new HashSet<>();

            vertices.add(new Vertex<>('A'));
            vertices.add(new Vertex<>('B'));
            edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 12));

            directedGraph = new Graph<>(vertices, edges);

            edges1.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 12));
            edges1.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 12));
            undirectedGraph = new Graph<>(vertices, edges1);
        }

        @Test(timeout = TIMEOUT)
        public void testDFS() {
            assertEquals(Arrays.asList(new Vertex<>('A'), new Vertex<>('B')),
                    GraphAlgorithms.dfs(new Vertex<>('A'), undirectedGraph));
            assertEquals(Arrays.asList(new Vertex<>('A'), new Vertex<>('B')),
                    GraphAlgorithms.dfs(new Vertex<>('A'), directedGraph));
        }

        @Test(timeout = TIMEOUT)
        public void testDijk() {
            HashMap<Vertex<Character>, Integer> mapChar = new HashMap<>();
            mapChar.put(new Vertex<>('A'), 0);
            mapChar.put(new Vertex<>('B'), 12);
            assertEquals(mapChar, GraphAlgorithms.dijkstras(new Vertex<>('A'), undirectedGraph));
            assertEquals(mapChar, GraphAlgorithms.dijkstras(new Vertex<>('A'), directedGraph));
        }


        @Test(timeout = TIMEOUT)
        public void testPrim() {
            assertEquals(new HashSet<>(Arrays.asList(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 12),
                    new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 12))),
                    GraphAlgorithms.prims(new Vertex<>('A'), undirectedGraph));
        }
    }

    public static class BigGraph {
        @Before
        public void setUp() {
            Set<Vertex<Character>> vertices = new HashSet<>();
            Set<Edge<Character>> edges = new HashSet<>();
            Set<Edge<Character>> edges1 = new HashSet<>();

            vertices.add(new Vertex<>('A'));
            vertices.add(new Vertex<>('B'));
            vertices.add(new Vertex<>('C'));
            vertices.add(new Vertex<>('D'));
            vertices.add(new Vertex<>('E'));
            vertices.add(new Vertex<>('F'));
            vertices.add(new Vertex<>('G'));
            vertices.add(new Vertex<>('H'));
            vertices.add(new Vertex<>('I'));
            vertices.add(new Vertex<>('J'));
            vertices.add(new Vertex<>('K'));
            vertices.add(new Vertex<>('L'));
            vertices.add(new Vertex<>('M'));
            vertices.add(new Vertex<>('N'));
            vertices.add(new Vertex<>('O'));
            vertices.add(new Vertex<>('P'));
            vertices.add(new Vertex<>('Q'));
            vertices.add(new Vertex<>('R'));

            /*
            https://graphonline.ru/tmp/saved/BS/BSmLenbxgDmMMIGk.png
             */
            edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('H'), 4));
            edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('O'), 1));
            edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 3));
            edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 1));
            edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('G'), 7));
            edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('R'), 2));
            edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('I'), 3));
            edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('I'), 7));
            edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('C'), 7));
            edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('K'), 3));
            edges.add(new Edge<>(new Vertex<>('G'), new Vertex<>('K'), 3));
            edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('E'), 8));
            edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('L'), 3));
            edges.add(new Edge<>(new Vertex<>('H'), new Vertex<>('O'), 1));
            edges.add(new Edge<>(new Vertex<>('I'), new Vertex<>('H'), 9));
            edges.add(new Edge<>(new Vertex<>('I'), new Vertex<>('L'), 7));
            edges.add(new Edge<>(new Vertex<>('J'), new Vertex<>('F'), 9));
            edges.add(new Edge<>(new Vertex<>('J'), new Vertex<>('G'), 3));
            edges.add(new Edge<>(new Vertex<>('J'), new Vertex<>('K'), 3));
            edges.add(new Edge<>(new Vertex<>('J'), new Vertex<>('M'), 5));
            edges.add(new Edge<>(new Vertex<>('K'), new Vertex<>('D'), 6));
            edges.add(new Edge<>(new Vertex<>('K'), new Vertex<>('J'), 2));
            edges.add(new Edge<>(new Vertex<>('K'), new Vertex<>('R'), 1));
            edges.add(new Edge<>(new Vertex<>('L'), new Vertex<>('I'), 3));
            edges.add(new Edge<>(new Vertex<>('L'), new Vertex<>('M'), 4));
            edges.add(new Edge<>(new Vertex<>('M'), new Vertex<>('I'), 3));
            edges.add(new Edge<>(new Vertex<>('M'), new Vertex<>('L'), 3));
            edges.add(new Edge<>(new Vertex<>('M'), new Vertex<>('N'), 2));
            edges.add(new Edge<>(new Vertex<>('M'), new Vertex<>('Q'), 9));
            edges.add(new Edge<>(new Vertex<>('N'), new Vertex<>('R'), 6));
            edges.add(new Edge<>(new Vertex<>('O'), new Vertex<>('H'), 4));
            edges.add(new Edge<>(new Vertex<>('O'), new Vertex<>('L'), 1));
            edges.add(new Edge<>(new Vertex<>('O'), new Vertex<>('P'), 8));
            edges.add(new Edge<>(new Vertex<>('P'), new Vertex<>('Q'), 6));
            edges.add(new Edge<>(new Vertex<>('P'), new Vertex<>('R'), 5));
            edges.add(new Edge<>(new Vertex<>('Q'), new Vertex<>('P'), 8));
            edges.add(new Edge<>(new Vertex<>('Q'), new Vertex<>('R'), 1));
            edges.add(new Edge<>(new Vertex<>('R'), new Vertex<>('K'), 8));
            edges.add(new Edge<>(new Vertex<>('R'), new Vertex<>('Q'), 5));

            directedGraph = new Graph<>(vertices, edges);

            /*
            https://graphonline.ru/tmp/saved/ia/iagzUNIzlVXocxyg.png
             */
            edges1.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 7));
            edges1.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 6));
            edges1.add(new Edge<>(new Vertex<>('A'), new Vertex<>('E'), 2));
            edges1.add(new Edge<>(new Vertex<>('A'), new Vertex<>('H'), 5));
            edges1.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 7));
            edges1.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 3));
            edges1.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 6));
            edges1.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 3));
            edges1.add(new Edge<>(new Vertex<>('C'), new Vertex<>('G'), 9));
            edges1.add(new Edge<>(new Vertex<>('D'), new Vertex<>('G'), 9));
            edges1.add(new Edge<>(new Vertex<>('D'), new Vertex<>('K'), 5));
            edges1.add(new Edge<>(new Vertex<>('D'), new Vertex<>('R'), 9));
            edges1.add(new Edge<>(new Vertex<>('E'), new Vertex<>('A'), 2));
            edges1.add(new Edge<>(new Vertex<>('E'), new Vertex<>('H'), 6));
            edges1.add(new Edge<>(new Vertex<>('F'), new Vertex<>('G'), 1));
            edges1.add(new Edge<>(new Vertex<>('F'), new Vertex<>('I'), 6));
            edges1.add(new Edge<>(new Vertex<>('F'), new Vertex<>('J'), 4));
            edges1.add(new Edge<>(new Vertex<>('G'), new Vertex<>('C'), 9));
            edges1.add(new Edge<>(new Vertex<>('G'), new Vertex<>('D'), 9));
            edges1.add(new Edge<>(new Vertex<>('G'), new Vertex<>('F'), 1));
            edges1.add(new Edge<>(new Vertex<>('G'), new Vertex<>('J'), 9));
            edges1.add(new Edge<>(new Vertex<>('G'), new Vertex<>('K'), 6));
            edges1.add(new Edge<>(new Vertex<>('H'), new Vertex<>('A'), 5));
            edges1.add(new Edge<>(new Vertex<>('H'), new Vertex<>('E'), 6));
            edges1.add(new Edge<>(new Vertex<>('H'), new Vertex<>('I'), 7));
            edges1.add(new Edge<>(new Vertex<>('H'), new Vertex<>('L'), 2));
            edges1.add(new Edge<>(new Vertex<>('H'), new Vertex<>('O'), 8));
            edges1.add(new Edge<>(new Vertex<>('I'), new Vertex<>('F'), 6));
            edges1.add(new Edge<>(new Vertex<>('I'), new Vertex<>('H'), 7));
            edges1.add(new Edge<>(new Vertex<>('I'), new Vertex<>('J'), 8));
            edges1.add(new Edge<>(new Vertex<>('I'), new Vertex<>('L'), 6));
            edges1.add(new Edge<>(new Vertex<>('J'), new Vertex<>('F'), 4));
            edges1.add(new Edge<>(new Vertex<>('J'), new Vertex<>('G'), 9));
            edges1.add(new Edge<>(new Vertex<>('J'), new Vertex<>('I'), 8));
            edges1.add(new Edge<>(new Vertex<>('J'), new Vertex<>('K'), 3));
            edges1.add(new Edge<>(new Vertex<>('J'), new Vertex<>('M'), 3));
            edges1.add(new Edge<>(new Vertex<>('J'), new Vertex<>('N'), 8));
            edges1.add(new Edge<>(new Vertex<>('K'), new Vertex<>('D'), 5));
            edges1.add(new Edge<>(new Vertex<>('K'), new Vertex<>('G'), 6));
            edges1.add(new Edge<>(new Vertex<>('K'), new Vertex<>('J'), 3));
            edges1.add(new Edge<>(new Vertex<>('K'), new Vertex<>('N'), 5));
            edges1.add(new Edge<>(new Vertex<>('K'), new Vertex<>('R'), 5));
            edges1.add(new Edge<>(new Vertex<>('L'), new Vertex<>('H'), 2));
            edges1.add(new Edge<>(new Vertex<>('L'), new Vertex<>('I'), 6));
            edges1.add(new Edge<>(new Vertex<>('L'), new Vertex<>('M'), 6));
            edges1.add(new Edge<>(new Vertex<>('L'), new Vertex<>('P'), 2));
            edges1.add(new Edge<>(new Vertex<>('M'), new Vertex<>('J'), 3));
            edges1.add(new Edge<>(new Vertex<>('M'), new Vertex<>('L'), 6));
            edges1.add(new Edge<>(new Vertex<>('M'), new Vertex<>('N'), 1));
            edges1.add(new Edge<>(new Vertex<>('M'), new Vertex<>('Q'), 1));
            edges1.add(new Edge<>(new Vertex<>('N'), new Vertex<>('J'), 8));
            edges1.add(new Edge<>(new Vertex<>('N'), new Vertex<>('K'), 5));
            edges1.add(new Edge<>(new Vertex<>('N'), new Vertex<>('M'), 1));
            edges1.add(new Edge<>(new Vertex<>('N'), new Vertex<>('Q'), 1));
            edges1.add(new Edge<>(new Vertex<>('N'), new Vertex<>('R'), 9));
            edges1.add(new Edge<>(new Vertex<>('O'), new Vertex<>('H'), 8));
            edges1.add(new Edge<>(new Vertex<>('P'), new Vertex<>('L'), 2));
            edges1.add(new Edge<>(new Vertex<>('P'), new Vertex<>('R'), 3));
            edges1.add(new Edge<>(new Vertex<>('Q'), new Vertex<>('M'), 1));
            edges1.add(new Edge<>(new Vertex<>('Q'), new Vertex<>('N'), 1));
            edges1.add(new Edge<>(new Vertex<>('Q'), new Vertex<>('R'), 3));
            edges1.add(new Edge<>(new Vertex<>('R'), new Vertex<>('D'), 9));
            edges1.add(new Edge<>(new Vertex<>('R'), new Vertex<>('K'), 5));
            edges1.add(new Edge<>(new Vertex<>('R'), new Vertex<>('N'), 9));
            edges1.add(new Edge<>(new Vertex<>('R'), new Vertex<>('P'), 3));
            edges1.add(new Edge<>(new Vertex<>('R'), new Vertex<>('Q'), 3));

            undirectedGraph = new Graph<>(vertices, edges1);
        }

        @Test(timeout = TIMEOUT)
        public void testDFS() {
            List<Vertex<Character>> dirExp = new LinkedList<>();

            dirExp.add(new Vertex<>('A'));
            dirExp.add(new Vertex<>('H'));
            dirExp.add(new Vertex<>('E'));
            dirExp.add(new Vertex<>('I'));
            dirExp.add(new Vertex<>('L'));
            dirExp.add(new Vertex<>('M'));
            dirExp.add(new Vertex<>('N'));
            dirExp.add(new Vertex<>('R'));
            dirExp.add(new Vertex<>('Q'));
            dirExp.add(new Vertex<>('P'));
            dirExp.add(new Vertex<>('K'));
            dirExp.add(new Vertex<>('J'));
            dirExp.add(new Vertex<>('F'));
            dirExp.add(new Vertex<>('G'));
            dirExp.add(new Vertex<>('C'));
            dirExp.add(new Vertex<>('B'));
            dirExp.add(new Vertex<>('D'));
            dirExp.add(new Vertex<>('O'));

            List<Vertex<Character>> undirExp = new LinkedList<>();

            undirExp.add(new Vertex<>('A'));
            undirExp.add(new Vertex<>('B'));
            undirExp.add(new Vertex<>('C'));
            undirExp.add(new Vertex<>('G'));
            undirExp.add(new Vertex<>('F'));
            undirExp.add(new Vertex<>('J'));
            undirExp.add(new Vertex<>('K'));
            undirExp.add(new Vertex<>('N'));
            undirExp.add(new Vertex<>('M'));
            undirExp.add(new Vertex<>('L'));
            undirExp.add(new Vertex<>('I'));
            undirExp.add(new Vertex<>('H'));
            undirExp.add(new Vertex<>('E'));
            undirExp.add(new Vertex<>('O'));
            undirExp.add(new Vertex<>('P'));
            undirExp.add(new Vertex<>('R'));
            undirExp.add(new Vertex<>('Q'));
            undirExp.add(new Vertex<>('D'));


            assertEquals(dirExp, GraphAlgorithms.dfs(new Vertex<>('A'), directedGraph));
            assertEquals(undirExp, GraphAlgorithms.dfs(new Vertex<>('A'), undirectedGraph));

            dirExp = new LinkedList<>();
            dirExp.add(new Vertex<>('D'));
            dirExp.add(new Vertex<>('R'));
            dirExp.add(new Vertex<>('Q'));
            dirExp.add(new Vertex<>('P'));
            dirExp.add(new Vertex<>('K'));
            dirExp.add(new Vertex<>('J'));
            dirExp.add(new Vertex<>('M'));
            dirExp.add(new Vertex<>('N'));
            dirExp.add(new Vertex<>('L'));
            dirExp.add(new Vertex<>('I'));
            dirExp.add(new Vertex<>('H'));
            dirExp.add(new Vertex<>('E'));
            dirExp.add(new Vertex<>('O'));
            dirExp.add(new Vertex<>('F'));
            dirExp.add(new Vertex<>('G'));
            dirExp.add(new Vertex<>('C'));
            dirExp.add(new Vertex<>('B'));

            assertEquals(dirExp, GraphAlgorithms.dfs(new Vertex<>('D'), directedGraph));

            undirExp = new LinkedList<>();

            undirExp.add(new Vertex<>('D'));
            undirExp.add(new Vertex<>('G'));
            undirExp.add(new Vertex<>('F'));
            undirExp.add(new Vertex<>('J'));
            undirExp.add(new Vertex<>('K'));
            undirExp.add(new Vertex<>('N'));
            undirExp.add(new Vertex<>('M'));
            undirExp.add(new Vertex<>('L'));
            undirExp.add(new Vertex<>('I'));
            undirExp.add(new Vertex<>('H'));
            undirExp.add(new Vertex<>('E'));
            undirExp.add(new Vertex<>('A'));
            undirExp.add(new Vertex<>('B'));
            undirExp.add(new Vertex<>('C'));
            undirExp.add(new Vertex<>('O'));
            undirExp.add(new Vertex<>('P'));
            undirExp.add(new Vertex<>('R'));
            undirExp.add(new Vertex<>('Q'));

            assertEquals(undirExp, GraphAlgorithms.dfs(new Vertex<>('D'), undirectedGraph));
        }

        @Test(timeout = TIMEOUT)
        public void testDijk() {
            HashMap<Vertex<Character>, Integer> mapDir = new HashMap<>();
            mapDir.put(new Vertex<>('A'), 0);
            mapDir.put(new Vertex<>('B'), 35);
            mapDir.put(new Vertex<>('C'), 34);
            mapDir.put(new Vertex<>('D'), 28);
            mapDir.put(new Vertex<>('E'), 12);
            mapDir.put(new Vertex<>('F'), 33);
            mapDir.put(new Vertex<>('G'), 27);
            mapDir.put(new Vertex<>('H'), 4);
            mapDir.put(new Vertex<>('I'), 5);
            mapDir.put(new Vertex<>('J'), 24);
            mapDir.put(new Vertex<>('K'), 22);
            mapDir.put(new Vertex<>('L'), 2);
            mapDir.put(new Vertex<>('M'), 6);
            mapDir.put(new Vertex<>('N'), 8);
            mapDir.put(new Vertex<>('O'), 1);
            mapDir.put(new Vertex<>('P'), 9);
            mapDir.put(new Vertex<>('Q'), 15);
            mapDir.put(new Vertex<>('R'), 14);

            HashMap<Vertex<Character>, Integer> mapUndir = new HashMap<>();
            mapUndir.put(new Vertex<>('A'), 0);
            mapUndir.put(new Vertex<>('B'), 7);
            mapUndir.put(new Vertex<>('C'), 6);
            mapUndir.put(new Vertex<>('D'), 21);
            mapUndir.put(new Vertex<>('E'), 2);
            mapUndir.put(new Vertex<>('F'), 16);
            mapUndir.put(new Vertex<>('G'), 15);
            mapUndir.put(new Vertex<>('H'), 5);
            mapUndir.put(new Vertex<>('I'), 12);
            mapUndir.put(new Vertex<>('J'), 16);
            mapUndir.put(new Vertex<>('K'), 17);
            mapUndir.put(new Vertex<>('L'), 7);
            mapUndir.put(new Vertex<>('M'), 13);
            mapUndir.put(new Vertex<>('N'), 14);
            mapUndir.put(new Vertex<>('O'), 13);
            mapUndir.put(new Vertex<>('P'), 9);
            mapUndir.put(new Vertex<>('Q'), 14);
            mapUndir.put(new Vertex<>('R'), 12);

            assertEquals(mapDir, GraphAlgorithms.dijkstras(new Vertex<>('A'), directedGraph));
            assertEquals(mapUndir, GraphAlgorithms.dijkstras(new Vertex<>('A'), undirectedGraph));

        }

        @Test(timeout = TIMEOUT)
        public void testPrim() {
            HashSet<Edge<Character>> exp = new HashSet<>();
            exp.add(new Edge<>(new Vertex<>('R'), new Vertex<>('Q'), 3));
            exp.add(new Edge<>(new Vertex<>('Q'), new Vertex<>('R'), 3));
            exp.add(new Edge<>(new Vertex<>('F'), new Vertex<>('G'), 1));
            exp.add(new Edge<>(new Vertex<>('G'), new Vertex<>('F'), 1));
            exp.add(new Edge<>(new Vertex<>('P'), new Vertex<>('R'), 3));
            exp.add(new Edge<>(new Vertex<>('R'), new Vertex<>('P'), 3));
            exp.add(new Edge<>(new Vertex<>('J'), new Vertex<>('K'), 3));
            exp.add(new Edge<>(new Vertex<>('K'), new Vertex<>('J'), 3));
            exp.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 3));
            exp.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 3));
            exp.add(new Edge<>(new Vertex<>('L'), new Vertex<>('I'), 6));
            exp.add(new Edge<>(new Vertex<>('I'), new Vertex<>('L'), 6));
            exp.add(new Edge<>(new Vertex<>('M'), new Vertex<>('J'), 3));
            exp.add(new Edge<>(new Vertex<>('J'), new Vertex<>('M'), 3));
            exp.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 6));
            exp.add(new Edge<>(new Vertex<>('C'), new Vertex<>('A'), 6));
            exp.add(new Edge<>(new Vertex<>('A'), new Vertex<>('E'), 2));
            exp.add(new Edge<>(new Vertex<>('E'), new Vertex<>('A'), 2));
            exp.add(new Edge<>(new Vertex<>('H'), new Vertex<>('L'), 2));
            exp.add(new Edge<>(new Vertex<>('L'), new Vertex<>('H'), 2));
            exp.add(new Edge<>(new Vertex<>('J'), new Vertex<>('F'), 4));
            exp.add(new Edge<>(new Vertex<>('F'), new Vertex<>('J'), 4));
            exp.add(new Edge<>(new Vertex<>('K'), new Vertex<>('D'), 5));
            exp.add(new Edge<>(new Vertex<>('D'), new Vertex<>('K'), 5));
            exp.add(new Edge<>(new Vertex<>('A'), new Vertex<>('H'), 5));
            exp.add(new Edge<>(new Vertex<>('H'), new Vertex<>('A'), 5));
            exp.add(new Edge<>(new Vertex<>('H'), new Vertex<>('O'), 8));
            exp.add(new Edge<>(new Vertex<>('O'), new Vertex<>('H'), 8));
            exp.add(new Edge<>(new Vertex<>('Q'), new Vertex<>('M'), 1));
            exp.add(new Edge<>(new Vertex<>('M'), new Vertex<>('Q'), 1));
            exp.add(new Edge<>(new Vertex<>('L'), new Vertex<>('P'), 2));
            exp.add(new Edge<>(new Vertex<>('P'), new Vertex<>('L'), 2));
            exp.add(new Edge<>(new Vertex<>('Q'), new Vertex<>('N'), 1));
            exp.add(new Edge<>(new Vertex<>('N'), new Vertex<>('Q'), 1));

            assertEquals(exp, GraphAlgorithms.prims(new Vertex<>('A'), undirectedGraph));
        }
    }

    public static class TestParrallelEdges {

        @Before
        public void setUp() {
            Set<Vertex<Character>> vertices = new HashSet<>();
            Set<Edge<Character>> edges = new HashSet<>();
            Set<Edge<Character>> edges1 = new HashSet<>();

            vertices.add(new Vertex<>('A'));
            vertices.add(new Vertex<>('B'));
            vertices.add(new Vertex<>('C'));
            vertices.add(new Vertex<>('D'));
            vertices.add(new Vertex<>('E'));
            vertices.add(new Vertex<>('F'));

            /*
            https://graphonline.ru/tmp/saved/lL/lLnSrImjzbJVAgSm.png
             */

            edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 4));
            edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 8));
            edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('C'), 3));
            edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('E'), 4));
            edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('F'), 1));
            edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 2));
            edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 4));
            edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('F'), 4));
            edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('F'), 6));
            edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('F'), 6));

            directedGraph = new Graph<>(vertices, edges);

            /*
            https://graphonline.ru/tmp/saved/uS/uSjHaJIPEWlnWNCI.png
             */
            edges1.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 5));
            edges1.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 11));
            edges1.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 11));
            edges1.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 5));
            edges1.add(new Edge<>(new Vertex<>('B'), new Vertex<>('D'), 11));
            edges1.add(new Edge<>(new Vertex<>('C'), new Vertex<>('F'), 7));
            edges1.add(new Edge<>(new Vertex<>('D'), new Vertex<>('B'), 11));
            edges1.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 7));
            edges1.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 3));
            edges1.add(new Edge<>(new Vertex<>('D'), new Vertex<>('F'), 1));
            edges1.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 7));
            edges1.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 3));
            edges1.add(new Edge<>(new Vertex<>('F'), new Vertex<>('D'), 1));
            edges1.add(new Edge<>(new Vertex<>('F'), new Vertex<>('C'), 7));


            undirectedGraph = new Graph<>(vertices, edges1);
        }

        @Test(timeout = TIMEOUT)
        public void testDFS() {
            List<Vertex<Character>> dirExp = new LinkedList<>();

            dirExp.add(new Vertex<>('A'));
            dirExp.add(new Vertex<>('C'));
            dirExp.add(new Vertex<>('D'));
            dirExp.add(new Vertex<>('F'));
            dirExp.add(new Vertex<>('B'));
            dirExp.add(new Vertex<>('E'));


            List<Vertex<Character>> undirExp = new LinkedList<>();

            undirExp.add(new Vertex<>('A'));
            undirExp.add(new Vertex<>('B'));
            undirExp.add(new Vertex<>('D'));
            undirExp.add(new Vertex<>('E'));
            undirExp.add(new Vertex<>('F'));
            undirExp.add(new Vertex<>('C'));


            assertEquals(dirExp, GraphAlgorithms.dfs(new Vertex<>('A'), directedGraph));
            assertEquals(undirExp, GraphAlgorithms.dfs(new Vertex<>('A'), undirectedGraph));
        }

        @Test(timeout = TIMEOUT)
        public void testDijk() {
            HashMap<Vertex<Character>, Integer> mapDir = new HashMap<>();
            mapDir.put(new Vertex<>('A'), 0);
            mapDir.put(new Vertex<>('B'), 4);
            mapDir.put(new Vertex<>('C'), 3);
            mapDir.put(new Vertex<>('D'), 5);
            mapDir.put(new Vertex<>('E'), 8);
            mapDir.put(new Vertex<>('F'), 5);


            HashMap<Vertex<Character>, Integer> mapUndir = new HashMap<>();
            mapUndir.put(new Vertex<>('A'), 0);
            mapUndir.put(new Vertex<>('B'), 5);
            mapUndir.put(new Vertex<>('C'), 24);
            mapUndir.put(new Vertex<>('D'), 16);
            mapUndir.put(new Vertex<>('E'), 19);
            mapUndir.put(new Vertex<>('F'), 17);


            assertEquals(mapDir, GraphAlgorithms.dijkstras(new Vertex<>('A'), directedGraph));
            assertEquals(mapUndir, GraphAlgorithms.dijkstras(new Vertex<>('A'), undirectedGraph));

        }

        @Test(timeout = TIMEOUT)
        public void testPrim() {
            HashSet<Edge<Character>> exp = new HashSet<>();
            exp.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 3));
            exp.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 3));
            exp.add(new Edge<>(new Vertex<>('F'), new Vertex<>('C'), 7));
            exp.add(new Edge<>(new Vertex<>('C'), new Vertex<>('F'), 7));
            exp.add(new Edge<>(new Vertex<>('D'), new Vertex<>('F'), 1));
            exp.add(new Edge<>(new Vertex<>('F'), new Vertex<>('D'), 1));
            exp.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 5));
            exp.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 5));
            exp.add(new Edge<>(new Vertex<>('B'), new Vertex<>('D'), 11));
            exp.add(new Edge<>(new Vertex<>('D'), new Vertex<>('B'), 11));

            assertEquals(exp, GraphAlgorithms.prims(new Vertex<>('A'), undirectedGraph));
        }

    }

    public static class TestSelfLoop {

        @Before
        public void setUp() {
            Set<Vertex<Character>> vertices = new HashSet<>();
            Set<Edge<Character>> edges = new HashSet<>();
            Set<Edge<Character>> edges1 = new HashSet<>();

            vertices.add(new Vertex<>('A'));
            vertices.add(new Vertex<>('B'));
            vertices.add(new Vertex<>('C'));
            vertices.add(new Vertex<>('D'));
            vertices.add(new Vertex<>('E'));
            vertices.add(new Vertex<>('F'));

            /*
            https://graphonline.ru/tmp/saved/vr/vrHlznkCQdHTIWCo.png
             */

            edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('A'), 5));
            edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 7));
            edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('D'), 1));
            edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 3));
            edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('D'), 7));
            edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('F'), 11));
            edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 5));
            edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 5));
            edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('E'), 3));


            directedGraph = new Graph<>(vertices, edges);

            /*
            https://graphonline.ru/tmp/saved/aB/aBTikJYVPmdjaZkW.png
             */
            edges1.add(new Edge<>(new Vertex<>('A'), new Vertex<>('A'), 5));
            edges1.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 7));
            edges1.add(new Edge<>(new Vertex<>('A'), new Vertex<>('D'), 3));
            edges1.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 7));
            edges1.add(new Edge<>(new Vertex<>('B'), new Vertex<>('D'), 7));
            edges1.add(new Edge<>(new Vertex<>('B'), new Vertex<>('E'), 3));
            edges1.add(new Edge<>(new Vertex<>('B'), new Vertex<>('F'), 11));
            edges1.add(new Edge<>(new Vertex<>('C'), new Vertex<>('C'), 3));
            edges1.add(new Edge<>(new Vertex<>('C'), new Vertex<>('E'), 3));
            edges1.add(new Edge<>(new Vertex<>('D'), new Vertex<>('A'), 3));
            edges1.add(new Edge<>(new Vertex<>('D'), new Vertex<>('B'), 7));
            edges1.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 5));
            edges1.add(new Edge<>(new Vertex<>('E'), new Vertex<>('B'), 3));
            edges1.add(new Edge<>(new Vertex<>('E'), new Vertex<>('C'), 3));
            edges1.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 5));
            edges1.add(new Edge<>(new Vertex<>('F'), new Vertex<>('B'), 11));
            edges1.add(new Edge<>(new Vertex<>('F'), new Vertex<>('F'), 7));


            undirectedGraph = new Graph<>(vertices, edges1);
        }

        @Test(timeout = TIMEOUT)
        public void testDFS() {
            List<Vertex<Character>> dirExp = new LinkedList<>();

            dirExp.add(new Vertex<>('A'));
            dirExp.add(new Vertex<>('B'));
            dirExp.add(new Vertex<>('D'));
            dirExp.add(new Vertex<>('E'));
            dirExp.add(new Vertex<>('C'));
            dirExp.add(new Vertex<>('F'));


            List<Vertex<Character>> undirExp = new LinkedList<>();

            undirExp.add(new Vertex<>('A'));
            undirExp.add(new Vertex<>('B'));
            undirExp.add(new Vertex<>('D'));
            undirExp.add(new Vertex<>('E'));
            undirExp.add(new Vertex<>('C'));
            undirExp.add(new Vertex<>('F'));


            assertEquals(dirExp, GraphAlgorithms.dfs(new Vertex<>('A'), directedGraph));
            assertEquals(undirExp, GraphAlgorithms.dfs(new Vertex<>('A'), undirectedGraph));
        }

        @Test(timeout = TIMEOUT)
        public void testDijk() {
            HashMap<Vertex<Character>, Integer> mapDir = new HashMap<>();
            mapDir.put(new Vertex<>('A'), 0);
            mapDir.put(new Vertex<>('B'), 7);
            mapDir.put(new Vertex<>('C'), 10);
            mapDir.put(new Vertex<>('D'), 1);
            mapDir.put(new Vertex<>('E'), 6);
            mapDir.put(new Vertex<>('F'), 18);


            HashMap<Vertex<Character>, Integer> mapUndir = new HashMap<>();
            mapUndir.put(new Vertex<>('A'), 0);
            mapUndir.put(new Vertex<>('B'), 7);
            mapUndir.put(new Vertex<>('C'), 11);
            mapUndir.put(new Vertex<>('D'), 3);
            mapUndir.put(new Vertex<>('E'), 8);
            mapUndir.put(new Vertex<>('F'), 18);


            assertEquals(mapDir, GraphAlgorithms.dijkstras(new Vertex<>('A'), directedGraph));
            assertEquals(mapUndir, GraphAlgorithms.dijkstras(new Vertex<>('A'), undirectedGraph));

        }

        @Test(timeout = TIMEOUT)
        public void testPrim() {
            HashSet<Edge<Character>> exp = new HashSet<>();
            exp.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 5));
            exp.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 5));
            exp.add(new Edge<>(new Vertex<>('E'), new Vertex<>('B'), 3));
            exp.add(new Edge<>(new Vertex<>('B'), new Vertex<>('E'), 3));
            exp.add(new Edge<>(new Vertex<>('E'), new Vertex<>('C'), 3));
            exp.add(new Edge<>(new Vertex<>('C'), new Vertex<>('E'), 3));
            exp.add(new Edge<>(new Vertex<>('A'), new Vertex<>('D'), 3));
            exp.add(new Edge<>(new Vertex<>('D'), new Vertex<>('A'), 3));
            exp.add(new Edge<>(new Vertex<>('B'), new Vertex<>('F'), 11));
            exp.add(new Edge<>(new Vertex<>('F'), new Vertex<>('B'), 11));


            assertEquals(exp, GraphAlgorithms.prims(new Vertex<>('A'), undirectedGraph));
        }

    }

    public static class TestDisconnected {

        @Before
        public void setUp() {
            Set<Vertex<Character>> vertices = new HashSet<>();
            Set<Edge<Character>> edges = new HashSet<>();
            Set<Edge<Character>> edges1 = new HashSet<>();

            vertices.add(new Vertex<>('A'));
            vertices.add(new Vertex<>('B'));
            vertices.add(new Vertex<>('C'));
            vertices.add(new Vertex<>('D'));
            vertices.add(new Vertex<>('E'));
            vertices.add(new Vertex<>('F'));

            /*
            https://graphonline.ru/tmp/saved/Oi/OiivKuMoQxjlsNDi.png
             */

            edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 7));
            edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 1));
            edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('D'), 5));
            edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('E'), 3));
            edges.add(new Edge<>(new Vertex<>('C'), new Vertex<>('E'), 7));
            edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 1));
            edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('C'), 5));

            directedGraph = new Graph<>(vertices, edges);

            /*
            https://graphonline.ru/tmp/saved/IP/IPDRLpGswSPYzHLT.png
             */
            edges1.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 5));
            edges1.add(new Edge<>(new Vertex<>('A'), new Vertex<>('D'), 3));
            edges1.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 5));
            edges1.add(new Edge<>(new Vertex<>('B'), new Vertex<>('C'), 8));
            edges1.add(new Edge<>(new Vertex<>('B'), new Vertex<>('D'), 3));
            edges1.add(new Edge<>(new Vertex<>('B'), new Vertex<>('E'), 11));
            edges1.add(new Edge<>(new Vertex<>('C'), new Vertex<>('B'), 8));
            edges1.add(new Edge<>(new Vertex<>('D'), new Vertex<>('A'), 3));
            edges1.add(new Edge<>(new Vertex<>('D'), new Vertex<>('B'), 3));
            edges1.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 4));
            edges1.add(new Edge<>(new Vertex<>('E'), new Vertex<>('B'), 11));
            edges1.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 4));

            undirectedGraph = new Graph<>(vertices, edges1);
        }

        @Test(timeout = TIMEOUT)
        public void testDFS() {
            List<Vertex<Character>> dirExp = new LinkedList<>();

            dirExp.add(new Vertex<>('A'));
            dirExp.add(new Vertex<>('B'));
            dirExp.add(new Vertex<>('D'));
            dirExp.add(new Vertex<>('E'));
            dirExp.add(new Vertex<>('C'));


            List<Vertex<Character>> undirExp = new LinkedList<>();

            undirExp.add(new Vertex<>('A'));
            undirExp.add(new Vertex<>('B'));
            undirExp.add(new Vertex<>('D'));
            undirExp.add(new Vertex<>('E'));
            undirExp.add(new Vertex<>('C'));


            assertEquals(dirExp, GraphAlgorithms.dfs(new Vertex<>('A'), directedGraph));
            assertEquals(undirExp, GraphAlgorithms.dfs(new Vertex<>('A'), undirectedGraph));
            assertEquals(Arrays.asList(new Vertex<>('F')),
                    GraphAlgorithms.dfs(new Vertex<>('F'), directedGraph));
            assertEquals(Arrays.asList(new Vertex<>('F')),
                    GraphAlgorithms.dfs(new Vertex<>('F'), undirectedGraph));
        }

        @Test(timeout = TIMEOUT)
        public void testDijk() {
            HashMap<Vertex<Character>, Integer> mapDir = new HashMap<>();
            mapDir.put(new Vertex<>('A'), 0);
            mapDir.put(new Vertex<>('B'), 7);
            mapDir.put(new Vertex<>('C'), 15);
            mapDir.put(new Vertex<>('D'), 12);
            mapDir.put(new Vertex<>('E'), 10);
            mapDir.put(new Vertex<>('F'), Integer.MAX_VALUE);


            HashMap<Vertex<Character>, Integer> mapUndir = new HashMap<>();
            mapUndir.put(new Vertex<>('A'), 0);
            mapUndir.put(new Vertex<>('B'), 5);
            mapUndir.put(new Vertex<>('C'), 13);
            mapUndir.put(new Vertex<>('D'), 3);
            mapUndir.put(new Vertex<>('E'), 7);
            mapUndir.put(new Vertex<>('F'), Integer.MAX_VALUE);


            assertEquals(mapDir, GraphAlgorithms.dijkstras(new Vertex<>('A'), directedGraph));

            mapDir.put(new Vertex<>('A'), Integer.MAX_VALUE);
            mapDir.put(new Vertex<>('B'), Integer.MAX_VALUE);
            mapDir.put(new Vertex<>('C'), Integer.MAX_VALUE);
            mapDir.put(new Vertex<>('D'), Integer.MAX_VALUE);
            mapDir.put(new Vertex<>('E'), Integer.MAX_VALUE);
            mapDir.put(new Vertex<>('F'), 0);
            assertEquals(mapDir, GraphAlgorithms.dijkstras(new Vertex<>('F'), directedGraph));

            assertEquals(mapUndir, GraphAlgorithms.dijkstras(new Vertex<>('A'), undirectedGraph));

            mapUndir.put(new Vertex<>('A'), Integer.MAX_VALUE);
            mapUndir.put(new Vertex<>('B'), Integer.MAX_VALUE);
            mapUndir.put(new Vertex<>('C'), Integer.MAX_VALUE);
            mapUndir.put(new Vertex<>('D'), Integer.MAX_VALUE);
            mapUndir.put(new Vertex<>('E'), Integer.MAX_VALUE);
            mapUndir.put(new Vertex<>('F'), 0);

            assertEquals(mapUndir, GraphAlgorithms.dijkstras(new Vertex<>('F'), undirectedGraph));

        }

        @Test(timeout = TIMEOUT)
        public void testPrim() {
            assertNull(GraphAlgorithms.prims(new Vertex<>('A'), undirectedGraph));
            assertNull(GraphAlgorithms.prims(new Vertex<>('F'), undirectedGraph));
        }

    }

    public static class TestMix {

        @Before
        public void setUp() {
            Set<Vertex<Character>> vertices = new HashSet<>();
            Set<Edge<Character>> edges = new HashSet<>();
            Set<Edge<Character>> edges1 = new HashSet<>();

            vertices.add(new Vertex<>('A'));
            vertices.add(new Vertex<>('B'));
            vertices.add(new Vertex<>('C'));
            vertices.add(new Vertex<>('D'));
            vertices.add(new Vertex<>('E'));
            vertices.add(new Vertex<>('F'));

            /*
            https://graphonline.ru/tmp/saved/Vp/VpbMKXXwqYddGbFL.png
             */

            edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 7));
            edges.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 3));
            edges.add(new Edge<>(new Vertex<>('B'), new Vertex<>('D'), 11));
            edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('A'), 5));
            edges.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 7));
            edges.add(new Edge<>(new Vertex<>('E'), new Vertex<>('E'), 3));
            edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('F'), 7));
            edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('F'), 11));
            edges.add(new Edge<>(new Vertex<>('F'), new Vertex<>('E'), 4));

            directedGraph = new Graph<>(vertices, edges);

            /*
            https://graphonline.ru/tmp/saved/CQ/CQMYlxKGYBItjqrT.png
             */
            edges1.add(new Edge<>(new Vertex<>('A'), new Vertex<>('A'), 11));
            edges1.add(new Edge<>(new Vertex<>('A'), new Vertex<>('B'), 8));
            edges1.add(new Edge<>(new Vertex<>('A'), new Vertex<>('E'), 9));
            edges1.add(new Edge<>(new Vertex<>('B'), new Vertex<>('A'), 8));
            edges1.add(new Edge<>(new Vertex<>('B'), new Vertex<>('E'), 7));
            edges1.add(new Edge<>(new Vertex<>('C'), new Vertex<>('D'), 3));
            edges1.add(new Edge<>(new Vertex<>('D'), new Vertex<>('C'), 3));
            edges1.add(new Edge<>(new Vertex<>('D'), new Vertex<>('D'), 6));
            edges1.add(new Edge<>(new Vertex<>('D'), new Vertex<>('E'), 9));
            edges1.add(new Edge<>(new Vertex<>('E'), new Vertex<>('A'), 9));
            edges1.add(new Edge<>(new Vertex<>('E'), new Vertex<>('B'), 7));
            edges1.add(new Edge<>(new Vertex<>('E'), new Vertex<>('D'), 9));
            edges1.add(new Edge<>(new Vertex<>('F'), new Vertex<>('F'), 5));

            undirectedGraph = new Graph<>(vertices, edges1);
        }

        @Test(timeout = TIMEOUT)
        public void testDFS() {
            List<Vertex<Character>> dirExp = new LinkedList<>();

            dirExp.add(new Vertex<>('A'));
            dirExp.add(new Vertex<>('B'));
            dirExp.add(new Vertex<>('D'));
            dirExp.add(new Vertex<>('C'));


            List<Vertex<Character>> undirExp = new LinkedList<>();

            undirExp.add(new Vertex<>('A'));
            undirExp.add(new Vertex<>('B'));
            undirExp.add(new Vertex<>('E'));
            undirExp.add(new Vertex<>('D'));
            undirExp.add(new Vertex<>('C'));


            assertEquals(dirExp, GraphAlgorithms.dfs(new Vertex<>('A'), directedGraph));
            assertEquals(undirExp, GraphAlgorithms.dfs(new Vertex<>('A'), undirectedGraph));
            assertEquals(Arrays.asList(new Vertex<>('F'), new Vertex<>('E')),
                    GraphAlgorithms.dfs(new Vertex<>('F'), directedGraph));
            assertEquals(Arrays.asList(new Vertex<>('E')),
                    GraphAlgorithms.dfs(new Vertex<>('E'), directedGraph));
            assertEquals(Arrays.asList(new Vertex<>('F')),
                    GraphAlgorithms.dfs(new Vertex<>('F'), undirectedGraph));
        }

        @Test(timeout = TIMEOUT)
        public void testDijk() {
            HashMap<Vertex<Character>, Integer> mapDir = new HashMap<>();
            mapDir.put(new Vertex<>('A'), 0);
            mapDir.put(new Vertex<>('B'), 3);
            mapDir.put(new Vertex<>('C'), 21);
            mapDir.put(new Vertex<>('D'), 14);
            mapDir.put(new Vertex<>('E'), Integer.MAX_VALUE);
            mapDir.put(new Vertex<>('F'), Integer.MAX_VALUE);


            HashMap<Vertex<Character>, Integer> mapUndir = new HashMap<>();
            mapUndir.put(new Vertex<>('A'), 0);
            mapUndir.put(new Vertex<>('B'), 8);
            mapUndir.put(new Vertex<>('C'), 21);
            mapUndir.put(new Vertex<>('D'), 18);
            mapUndir.put(new Vertex<>('E'), 9);
            mapUndir.put(new Vertex<>('F'), Integer.MAX_VALUE);


            assertEquals(mapDir, GraphAlgorithms.dijkstras(new Vertex<>('A'), directedGraph));

            mapDir.put(new Vertex<>('A'), Integer.MAX_VALUE);
            mapDir.put(new Vertex<>('B'), Integer.MAX_VALUE);
            mapDir.put(new Vertex<>('C'), Integer.MAX_VALUE);
            mapDir.put(new Vertex<>('D'), Integer.MAX_VALUE);
            mapDir.put(new Vertex<>('E'), 4);
            mapDir.put(new Vertex<>('F'), 0);

            assertEquals(mapDir, GraphAlgorithms.dijkstras(new Vertex<>('F'), directedGraph));

            assertEquals(mapUndir, GraphAlgorithms.dijkstras(new Vertex<>('A'), undirectedGraph));

            mapUndir.put(new Vertex<>('A'), Integer.MAX_VALUE);
            mapUndir.put(new Vertex<>('B'), Integer.MAX_VALUE);
            mapUndir.put(new Vertex<>('C'), Integer.MAX_VALUE);
            mapUndir.put(new Vertex<>('D'), Integer.MAX_VALUE);
            mapUndir.put(new Vertex<>('E'), Integer.MAX_VALUE);
            mapUndir.put(new Vertex<>('F'), 0);

            assertEquals(mapUndir, GraphAlgorithms.dijkstras(new Vertex<>('F'), undirectedGraph));

        }

        @Test(timeout = TIMEOUT)
        public void testPrim() {
            // Connected D & F to make this test possible, would result in null otherwise.

            Set<Edge<Character>> exp = new HashSet<>();

            exp.add(new Edge<>(new Vertex<>('B'),new Vertex<>('E'), 7));
            exp.add(new Edge<>(new Vertex<>('E'),new Vertex<>('B'), 7));
            exp.add(new Edge<>(new Vertex<>('D'),new Vertex<>('C'), 3));
            exp.add(new Edge<>(new Vertex<>('C'),new Vertex<>('D'), 3));
            exp.add(new Edge<>(new Vertex<>('D'),new Vertex<>('F'), 4));
            exp.add(new Edge<>(new Vertex<>('F'),new Vertex<>('D'), 4));
            exp.add(new Edge<>(new Vertex<>('E'),new Vertex<>('D'), 9));
            exp.add(new Edge<>(new Vertex<>('D'),new Vertex<>('E'), 9));
            exp.add(new Edge<>(new Vertex<>('A'),new Vertex<>('B'), 8));
            exp.add(new Edge<>(new Vertex<>('B'),new Vertex<>('A'), 8));

            Set<Edge<Character>> edges1 = undirectedGraph.getEdges();

            edges1.add(new Edge<>(new Vertex<>('D'), new Vertex<>('F'), 4));
            edges1.add(new Edge<>(new Vertex<>('F'), new Vertex<>('D'), 4));

            undirectedGraph = new Graph<>(undirectedGraph.getVertices(), edges1);

            assertEquals(exp, GraphAlgorithms.prims(new Vertex<>('A'), undirectedGraph));
            assertEquals(exp, GraphAlgorithms.prims(new Vertex<>('F'), undirectedGraph));
        }

    }
}
