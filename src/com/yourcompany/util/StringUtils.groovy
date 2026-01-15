// src/com/yourcompany/util/StringUtils.groovy
package com.yourcompany.util

class StringUtils {
    static String capitalize(String str) {
        if (!str) return str
        return str[0].toUpperCase() + str[1..-1]
    }
}
