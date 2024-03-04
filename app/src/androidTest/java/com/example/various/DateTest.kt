package com.example.various

import android.content.Context
import android.text.format.DateFormat
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

@RunWith(AndroidJUnit4::class)
class DateTest {

    @Test
    fun dateFormat() {
        // DateFormatはInstrumented Testでないとだめ
        val f = DateFormat.format("yyyy/MM/dd", Calendar.getInstance())
        Log.d("DateTest", "f: $f")
    }

    @Test
    fun zonedDateTimeWithZ() {
        //val context = ApplicationProvider.getApplicationContext<Context>()
        val s = "2022-07-09T08:05:23.653Z"
        val date = ZonedDateTime.parse(s)
        assertEquals(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), "2022-07-09T08:05:23.653")
    }

    @Test
    fun zonedDateTime() {
        val s = "2024-03-01T05:01:34.000+09:00"
        val date = ZonedDateTime.parse(s)
        Log.d("DateTest", date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))

        val c = Calendar.getInstance()
        c.time = Date.from(date.toInstant())
        Log.d("DateTest", "c: $c.")
    }

    @Test
    fun calendarWithDateFormat() {
        val calendar = Calendar.getInstance()
        // monthは0から始まるので注意
        calendar.set(2015, 2, 15, 0, 0, 0)
        val f = DateFormat.format("yyyy/MM/dd", calendar)
        Log.d("DateTest", "f: $f")
        assert(f == "2015/03/15")
    }
}