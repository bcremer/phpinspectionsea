package com.kalessil.phpStorm.phpInspectionsEA.api;

import com.intellij.testFramework.fixtures.CodeInsightFixtureTestCase;
import com.kalessil.phpStorm.phpInspectionsEA.inspectors.apiUsage.arrays.ArraySearchUsedAsInArrayInspector;

final public class ArraySearchUsedAsInArrayInspectorTest extends CodeInsightFixtureTestCase {
    public void testIfFindsAllPatterns() {
        myFixture.enableInspections(new ArraySearchUsedAsInArrayInspector());

        myFixture.configureByFile("fixtures/api/array-search-used-as-in-array.php");
        myFixture.testHighlighting(true, false, true);
    }
}
