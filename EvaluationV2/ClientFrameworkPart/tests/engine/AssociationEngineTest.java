package engine;

import engine.discreteValues.StringDiscreteValue;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by Julien Hatin on 12/12/15.
 */
public class AssociationEngineTest extends TestCase {

    private AssociationEngine associationEngine;

    public void setUp() throws Exception {
        super.setUp();
        associationEngine=new AssociationEngine(3);

    }

    public void tearDown() throws Exception {
        associationEngine.clear();
    }

    public void testGetEventAssociation() throws Exception {
        associationEngine.add(new StringDiscreteValue("wifiA"));
        associationEngine.add(new StringDiscreteValue("accelerometer1"));
        associationEngine.add(new StringDiscreteValue("applicationA"));
        associationEngine.add(new StringDiscreteValue("accelerometer1"));
        associationEngine.add(new StringDiscreteValue("nodeA"));

        assertEquals(associationEngine.size(), 5);
        List<Association> associationList = associationEngine.getEventAssociation();

        assertEquals(associationList.size(), 3);
        assertEquals(associationEngine.size(), 5);

        associationEngine.setNumberOfDiscreteValues(4);
        associationList = associationEngine.getEventAssociation();

        assertEquals(associationList.size(), 1);

    }

    public void testGetNumberOfDiscreteValues() throws Exception {
        assertEquals(associationEngine.getNumberOfDiscreteValues(), 3);
    }

    public void testSetNumberOfDiscreteValues() throws Exception {
        associationEngine.setNumberOfDiscreteValues(5);
        assertEquals(associationEngine.getNumberOfDiscreteValues(), 5);
        associationEngine.setNumberOfDiscreteValues(3);
        assertEquals(associationEngine.getNumberOfDiscreteValues(), 3);
    }

    public void testEquals() throws Exception {

    }
}