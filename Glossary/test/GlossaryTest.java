import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

public class GlossaryTest {

    @Test
    public void testGetTermsAndDefinitions1() {
        SimpleReader input = new SimpleReader1L("test1.txt");
        Map<String, String> definMap = new Map1L<>();

        Queue<String> listTerms = new Queue1L<>();
        listTerms.enqueue("test");
        listTerms.enqueue("car");
        assertEquals(listTerms,
                Glossary.getTermsAndDefinitions(definMap, input));
        input.close();
    }

    @Test
    public void testGetTermsAndDefinitions2() {
        SimpleReader input = new SimpleReader1L("test2.txt");
        Map<String, String> definMap = new Map1L<>();

        Queue<String> listTerms = new Queue1L<>();
        listTerms.enqueue("test");
        listTerms.enqueue("car");

        assertEquals(listTerms,
                Glossary.getTermsAndDefinitions(definMap, input));
        input.close();
    }

    @Test
    public void testGetTermsAndDefinitions3() {
        SimpleReader input = new SimpleReader1L("test3.txt");
        Map<String, String> definMap = new Map1L<>();

        Queue<String> listTerms = new Queue1L<>();
        listTerms.enqueue("hi");
        listTerms.enqueue("bye");
        listTerms.enqueue("yo whats up");
        listTerms.enqueue("ohio");

        assertEquals(listTerms,
                Glossary.getTermsAndDefinitions(definMap, input));
        input.close();
    }

    @Test
    public void testDefinitionLinks1() {
        SimpleReader input = new SimpleReader1L("test3.txt");
        Map<String, String> definMap = new Map1L<>();

        Queue<String> listTerms = new Queue1L<>();
        listTerms.enqueue("hi");
        listTerms.enqueue("bye");
        listTerms.enqueue("yo whats up");
        listTerms.enqueue("ohio");

        assertEquals(listTerms, Glossary.definitionLinks(definMap, listTerms));
        input.close();
    }

}
