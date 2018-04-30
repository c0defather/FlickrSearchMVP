package c0defather.examplemvp

import c0defather.examplemvp.data.model.Size
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by kuanysh on 4/29/18.
 */
class SizeUnitTest {
    @Test
    fun sizeConstructor() {
        val s = Size("label", "source")
        assertEquals(s.label,"label")
        assertEquals(s.source,"source")
    }
}