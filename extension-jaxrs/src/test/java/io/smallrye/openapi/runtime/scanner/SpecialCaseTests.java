package io.smallrye.openapi.runtime.scanner;

import java.io.IOException;

import org.eclipse.microprofile.openapi.models.media.Schema;
import org.jboss.jandex.Type;
import org.json.JSONException;
import org.junit.Test;

import test.io.smallrye.openapi.runtime.scanner.entities.SpecialCaseTestContainer;

/**
 * @author Marc Savy {@literal <marc@rhymewithgravy.com>}
 */
public class SpecialCaseTests extends JaxRsDataObjectScannerTestBase {

    @Test
    public void testCollection_SimpleTerminalType() throws IOException, JSONException {
        String name = SpecialCaseTestContainer.class.getName();
        Type pType = getFieldFromKlazz(name, "listOfString").type();
        OpenApiDataObjectScanner scanner = new OpenApiDataObjectScanner(context, pType);

        Schema result = scanner.process();

        printToConsole(name, result);
        assertJsonEquals(name, "special.simple.expected.json", result);
    }

    @Test
    public void testCollection_DataObjectList() throws IOException, JSONException {
        String name = SpecialCaseTestContainer.class.getName();
        Type pType = getFieldFromKlazz(name, "ccList").type();
        OpenApiDataObjectScanner scanner = new OpenApiDataObjectScanner(context, pType);

        Schema result = scanner.process();

        printToConsole(name, result);
        assertJsonEquals(name, "special.dataObjectList.expected.json", result);
    }

    @Test
    public void testCollection_WildcardWithSuperBound() throws IOException, JSONException {
        String name = SpecialCaseTestContainer.class.getName();
        Type pType = getFieldFromKlazz(name, "listSuperFlight").type();
        OpenApiDataObjectScanner scanner = new OpenApiDataObjectScanner(context, pType);

        Schema result = scanner.process();

        printToConsole(name, result);
        assertJsonEquals(name, "special.wildcardWithSuperBound.expected.json", result);
    }

    @Test
    public void testCollection_WildcardWithExtendBound() throws IOException, JSONException {
        String name = SpecialCaseTestContainer.class.getName();
        Type pType = getFieldFromKlazz(name, "listExtendsFoo").type();
        OpenApiDataObjectScanner scanner = new OpenApiDataObjectScanner(context, pType);

        Schema result = scanner.process();

        printToConsole(name, result);
        assertJsonEquals(name, "special.wildcardWithExtendBound.expected.json", result);
    }

    @Test
    public void testCollection_Wildcard() throws IOException, JSONException {
        String name = SpecialCaseTestContainer.class.getName();
        Type pType = getFieldFromKlazz(name, "listOfAnything").type();
        OpenApiDataObjectScanner scanner = new OpenApiDataObjectScanner(context, pType);

        Schema result = scanner.process();

        printToConsole(name, result);
        assertJsonEquals(name, "special.wildcard.expected.json", result);
    }

}
