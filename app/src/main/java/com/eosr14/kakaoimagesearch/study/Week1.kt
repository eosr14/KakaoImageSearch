package com.eosr14.kakaoimagesearch.study

import android.content.Context
import android.util.TypedValue

class Week1(
    private val context: Context
) {
    /**
     * 변수 선언
     */

    /**
     * 기본 자료형
     */

    // 문자를 숫자로 사용 - String -> Int
    val yearString: String get() = "1990"
    val year: Int
        get() = yearString.toInt()

    // 문자를 숫자로 사용 - Char -> Int
    val char: Char get() = 'e'
    val ascCode: Int get() = char.toInt()

    // 숫자 타입 변환 - Int -> Float
    val dp10: Int get() = 10
    val itemSize: Float
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp10.toFloat(),
            context.resources.displayMetrics
        )


    /**
     * 자료형 (숫자)
     */
    val int = 123
    val float = 123F
    val double = 123.0
    val long = 123L

    // 16진수, 2진수 지원 - 8진수는 지원하지 않음
    val decimalNumber = 0x0F
    val binaryNumber = 0b00001011

    // 부동소수점 지원
    val floatingPointToDouble: Double get() = 123.5 // 123.5e10
    val floatingPointToFloat : Float get() = 123.5F // 123.5f

    /**
     * 자료형 (문자)
     */
    val yellowToString = "yellow"
    val yToChar = 'y'

}