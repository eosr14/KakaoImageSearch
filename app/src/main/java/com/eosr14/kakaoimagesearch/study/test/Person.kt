package com.eosr14.kakaoimagesearch.study.test

data class Person(
    val age: Int
) {
    val adult: Boolean
        // 성인 여부를 age 값이 19보다 크거나 같을 경우에만 true로 반환
        get() = age >= 19

    var address: String = "a, b, c"
        get() = "주소 = $field"
        set(value) {
            // 인자로 들어온 문자열의 앞 10 자리만 필드에 저장
            field = value.substring(0..9)
        }
}