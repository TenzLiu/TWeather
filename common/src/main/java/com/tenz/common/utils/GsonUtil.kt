package com.tenz.common.utils

import com.google.gson.Gson
import com.google.gson.JsonParser
import java.io.IOException
import java.io.StringWriter
import java.lang.reflect.Type

object GsonUtil {

    private var mGson: Gson? = null

    private val gson: Gson?
        get() {
            if (mGson == null) {
                mGson = Gson()
            }
            return mGson
        }

    /**
     * 转Json
     *
     * @param src 任意类型
     * @return json
     */
    @JvmStatic
    fun toJson(src: Any?): String {
        if (src == null) {
            return ""
        }
        var s = ""
        try {
            var writer: StringWriter? = StringWriter()
            gson!!.toJson(src, src.javaClass, writer)
            s = writer.toString()
            writer!!.close()
            writer = null
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return s
    }

    /**
     * json转成list
     * @param jsonString
     * @param cls
     * @return
     */
    fun <T> jsonToList(jsonString: String?, cls: Class<T>?): List<T>? {
        val list: MutableList<T> = ArrayList()
        try {
            val gson = Gson()
            val array = JsonParser().parse(jsonString).asJsonArray
            for (jsonElement in array) {
                list.add(gson.fromJson(jsonElement, cls))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    @JvmStatic
    fun <T> fromJson(content: String?, type: Type?):T {
        return gson!!.fromJson<T>(content, type)
    }

}