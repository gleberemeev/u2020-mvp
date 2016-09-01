package ru.ltst.u2020mvp.util;

import android.content.SharedPreferences;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import ru.ltst.u2020mvp.tests.base.BaseTest;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class EnumPreferencesTest extends BaseTest {
    @Inject
    SharedPreferences sharedPreferences;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        getApp().getTestComponent().inject(this);
    }

    @Test
    public void getEnumValue() throws Exception {
        sharedPreferences.edit()
                .putString("key1", "ITEM_TWO")
                .putString("key2", "ITEM_THREE")
                .apply();
        TestEnumeration defaultVal = TestEnumeration.ITEM_ONE;
        assertEquals(TestEnumeration.ITEM_TWO, EnumPreferences.getEnumValue(sharedPreferences,
                TestEnumeration.class, "key1", defaultVal));
        assertEquals(TestEnumeration.ITEM_THREE, EnumPreferences.getEnumValue(sharedPreferences,
                TestEnumeration.class, "key2", defaultVal));
        assertEquals(defaultVal, EnumPreferences.getEnumValue(sharedPreferences,
                TestEnumeration.class, "key3", defaultVal));
        expectedException.expect(NullPointerException.class);
        TestEnumeration enumeration = EnumPreferences.getEnumValue(sharedPreferences,
                null, "key2", defaultVal);
        enumeration = EnumPreferences.getEnumValue(null, null, "key2", defaultVal);
        assertNull(EnumPreferences.getEnumValue(sharedPreferences, TestEnumeration.class,
                "key4", null));
    }

    @Test
    public void saveEnumValue() throws Exception {
        EnumPreferences.saveEnumValue(sharedPreferences, "key", TestEnumeration.ITEM_ONE);
        String output = sharedPreferences.getString("key", null);
        assertEquals(output, TestEnumeration.ITEM_ONE.name());
        expectedException.expect(NullPointerException.class);
        EnumPreferences.saveEnumValue(null, "key", TestEnumeration.ITEM_ONE);
        EnumPreferences.saveEnumValue(sharedPreferences, null, TestEnumeration.ITEM_ONE);
        EnumPreferences.saveEnumValue(sharedPreferences, "key", null);
    }

    private enum TestEnumeration {
        ITEM_ONE,
        ITEM_TWO,
        ITEM_THREE
    }

}