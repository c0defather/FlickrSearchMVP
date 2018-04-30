package c0defather.flickr

import c0defather.flickr.data.model.Photo
import c0defather.flickr.data.model.Size
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Created by kuanysh on 4/29/18.
 */
class PhotoUnitTest {
    @Test
    fun photoConstructor() {
        val r = Random()
        for (i in 1..100) {
            val id = r.nextLong().toString()
            val photo = Photo(id, ArrayList())
            assertEquals(photo.id, id)
        }
    }

    @Test
    fun photoConstructorWithSizes() {
        val sizes = ArrayList<Size>()
        sizes.add(Size("label","source"))
        sizes.add(Size("",""))
        val photo = Photo("1", sizes)
        assertEquals(photo.sizes, sizes.clone())
    }
}
