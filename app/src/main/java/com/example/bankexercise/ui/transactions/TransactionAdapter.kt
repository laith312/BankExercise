package com.example.bankexercise.ui.transactions

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.bankexercise.R
import com.example.bankexercise.data.Transaction
import com.example.bankexercise.data.TransactionType
import java.text.SimpleDateFormat
import java.util.Date


class TransactionAdapter(private val transactions: List<Transaction>) :
    RecyclerView.Adapter<AccountItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountItemViewHolder {
        return AccountItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_transaction, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AccountItemViewHolder, position: Int) {
        with(holder.itemView) {
            findViewById<TextView>(R.id.transactionDate).text =
                SimpleDateFormat("MMMM d YYYY").format(Date(transactions[position].dateInMillis))
                    .toString()

            findViewById<TextView>(R.id.transactionDescription).text =
                transactions[position].message

            if (transactions[position].transactionType == TransactionType.DEPOSIT) {
                findViewById<ImageView>(R.id.transactionImage)
                    .setImageResource(R.drawable.ic_add_green_round_24dp)

                val typedValue = TypedValue()
                val theme = context.theme
                theme.resolveAttribute(R.attr.primaryHighlightColor, typedValue, true)
                @ColorInt val color = typedValue.data


                findViewById<TextView>(R.id.transactionDate).setTextColor(color)
                findViewById<TextView>(R.id.transactionAmount).setTextColor(color)

                findViewById<TextView>(R.id.transactionAmount).text =
                    "$" + transactions[position].amount.toString()
            } else {
                findViewById<ImageView>(R.id.transactionImage)
                    .setImageResource(R.drawable.ic_remove_circle_outline_red_24dp)

                val typedValue = TypedValue()
                val theme = context.theme
                theme.resolveAttribute(R.attr.transactionTextColor, typedValue, true)
                @ColorInt val color = typedValue.data

                findViewById<TextView>(R.id.transactionDate).setTextColor(color)
                findViewById<TextView>(R.id.transactionAmount).setTextColor(color)

                findViewById<TextView>(R.id.transactionAmount).text =
                    "- $" + transactions[position].amount.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return transactions.size
    }
}

/**
 * Holds the view for the Account item.
 */
class AccountItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)