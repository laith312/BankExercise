package com.example.bankexercise.other

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.NumberFormat

fun EditText.addCurrencyFormatter() {
    var ignoreUpdate = false
    fun setIgnore(ignore: Boolean) {
        ignoreUpdate = ignore
    }
    // Reference: https://stackoverflow.com/questions/5107901/better-way-to-format-currency-input-edittext/29993290#29993290
    this.addTextChangedListener(object : TextWatcher {
        private var current = ""
        private var ignoreUpdate = false


        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s.toString() != current && !ignoreUpdate) {
                this@addCurrencyFormatter.removeTextChangedListener(this)
                // Reference for this replace regex: https://stackoverflow.com/questions/5107901/better-way-to-format-currency-input-edittext/28005836#28005836
                val cleanString = s.toString().replace("\\D".toRegex(), "")
                val parsed = if (cleanString.isBlank()) 0.0 else cleanString.toDouble()
                // format the double into a currency format
                val formated = NumberFormat.getCurrencyInstance()
                    .format(parsed / 100)

                current = formated
                this@addCurrencyFormatter.setText(formated)
                this@addCurrencyFormatter.setSelection(formated.length)

                this@addCurrencyFormatter.addTextChangedListener(this)
            }
        }
    })

}