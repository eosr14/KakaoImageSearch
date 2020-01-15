package com.eosr14.kakaoimagesearch.study

import android.content.Context
import android.util.TypedValue
import com.eosr14.kakaoimagesearch.KakaoImageSearchApplication

object class Week1(
    private val context: Context
) {
    /**
     * 변수 선언
     */
    val value = ""
    var variable = 0L

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

    // 언더스코어로 자릿값 구분
    val number = 1_000_000_000
    val cardNum = 1234_1234_1234_1234L
    val hexVal = 0xAB_CD_EF_12

    /**
     * 자료형 (문자)
     */
    val yellowToString = "yellow"
    val yToChar = 'y'

    val str1 : String = "H"
    val str2 = "W"
    var str3 = "H"

    class inner1 {
        var userName = "kh.shin"
    }

    fun strPrint() {
        println("str1 == str3 : ${str1 === str2}") // false
        println("str1 == str3 : ${str1 === str3}") // true
    }

}

/**
 * typeAlias
 */
class A {
    inner class Inner
}

class B {
    inner class Inner
}

typealias set = Set<String>
typealias onClickListener = (Int, String, Any) -> Unit
typealias AInner = A.Inner
typealias BInner = B.Inner
typealias UserName = String
typealias Password = Int

  - 제네릭, 고차함수 및 람다식, 클래스 등에서 별칭을 사용할 수 있다.